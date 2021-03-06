package sv.edu.ues.fia.eisi.pdm115;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.File;

import sv.edu.ues.fia.eisi.pdm115.utilidades.BuscarRuta;
import sv.edu.ues.fia.eisi.pdm115.webServices.SubirDocumentoService;

/*import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;*/


public class Perfil extends AppCompatActivity {

    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private ImageView mSetImage;
    private Button mOptionButton;
    private RelativeLayout mRlView;

    private String mPath, usuarioActual;
    private SharedPreferences prefs;
    private ControlBdGrupo12 DBHelper;
    //-------------------------------------
    Button TomarFoto, verImagen;
    ImageView image;
    String urlUsuario;
   // ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;
    Button SeleccionarFoto;
    private String pathFinal;

    RequestQueue request;
    ControlBdGrupo12 BD = new ControlBdGrupo12(Perfil.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // -----------------------------------------------------------------
        // Politicas para uso de camara
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // -----------------------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        DBHelper = new ControlBdGrupo12(this);
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String usuario = prefs.getString("usuarioActual","");
        usuarioActual = usuario;
        DBHelper.abrir();
        urlUsuario = DBHelper.obtenerURLImagen(usuarioActual);
        DBHelper.cerrar();
        mSetImage = (ImageView) findViewById(R.id.set_picture);
        mOptionButton = (Button) findViewById(R.id.show_options_button);
        mRlView = (RelativeLayout) findViewById(R.id.rl_view);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                mSetImage.setImageURI(Uri.parse(savedInstanceState.getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }


        //-------------------------------------------------------------------
        mSetImage = (ImageView) findViewById(R.id.set_picture);
        mOptionButton = (Button) findViewById(R.id.show_options_button);
        verImagen = (Button) findViewById(R.id.verimagen);
        mRlView = (RelativeLayout) findViewById(R.id.rl_view);

        request = Volley.newRequestQueue(Perfil.this);
        //mSetImage.setImageResource(android.R.drawable.ic_lock_power_off);

        //cargarImagenDB();
        mOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
        verImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verImagen();
            }
        });

        if (urlUsuario.isEmpty() || !urlUsuario.equals("Sin imagen")){
            Picasso.get().load("https://pdmgrupo12.000webhostapp.com/imagenes/"+urlUsuario).into(mSetImage);
            verImagen.setVisibility(View.VISIBLE);
        }else {
          Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png").into(mSetImage);
            verImagen.setVisibility(View.GONE);
        }
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (file!=null){
            outState.putString("Foto", file.toString());
        }
        //------
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });

                    Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                    mSetImage.setImageBitmap(bitmap);
                    pathFinal = mPath;
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    mSetImage.setImageURI(path);
                    pathFinal = BuscarRuta.getPath(this,path);
                    break;
            }
            try {
                Intent mIntent = new Intent(this, SubirDocumentoService.class);

                Long timestamp = System.currentTimeMillis() / 1000;
                String nuevoNombre = usuarioActual+"-"+timestamp+".jpg";
                mIntent.putExtra("path", pathFinal);
                mIntent.putExtra("directorio", "imagenes/");
                mIntent.putExtra("nombre", nuevoNombre);
                DBHelper.abrir();
                String resultado = DBHelper.actualizarUrlImagen(nuevoNombre, usuarioActual);
                DBHelper.cerrar();
                Toast.makeText(this, resultado,Toast.LENGTH_SHORT).show();
                SubirDocumentoService.enqueueWork(getApplicationContext(), mIntent);
                verImagen.setVisibility(View.VISIBLE);
                urlUsuario = nuevoNombre;


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                verImagen.setVisibility(View.GONE);
            }

        }
    }



    private void cargarImagenDB(){
        //SubirDocumentoService URL = new SubirDocumentoService();
        BD.abrir();
        String url = BD.cargarURL();
        BD.cerrar();

        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mSetImage.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Perfil.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        request.add(imageRequest);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "Permisos aceptados", Toast.LENGTH_SHORT).show();
                mOptionButton.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();
    }

    public void verImagen() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pdmgrupo12.000webhostapp.com/imagenes/"+urlUsuario));
        startActivity(browserIntent);
    }

}