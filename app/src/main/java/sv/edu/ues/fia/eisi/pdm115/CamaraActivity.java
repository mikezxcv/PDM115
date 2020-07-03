package sv.edu.ues.fia.eisi.pdm115;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.util.Calendar;

//----------------------------------------
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.provider.Settings;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import sv.edu.ues.fia.eisi.pdm115.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CamaraActivity extends Activity {
    Button TomarFoto;
    Button SeleccionarFoto;
    ImageView image;
    final int FOTOGRAFIA = 654;
    private final int SELECT_PICTURE = 300;
    Uri file;

    // ----------------------------------
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;

    public int parametro;


    private ImageView mSetImage;
    private Button mOptionButton;
    private RelativeLayout mRlView;

    private String mPath;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // -----------------------------------------------------------------
        // Politicas para uso de camara
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // -----------------------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        TomarFoto = (Button) findViewById(R.id.mainbttomarfoto);
        SeleccionarFoto = (Button) findViewById(R.id.seleccionarFoto);
        image = (ImageView) findViewById(R.id.mainimage);

        mRlView = (RelativeLayout) findViewById(R.id.rl_view);


        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState.getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }

        TomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo =new File(Environment.getExternalStorageDirectory(),String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg");
                file=Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent,FOTOGRAFIA);
               }
        });

        SeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent = intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);


            }
        });

    }


    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        bundle.putString("file_path", mPath);
        super.onSaveInstanceState(bundle);

        //------

    }

    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        if(ResultCode == RESULT_OK){
            image.setImageURI(file);

            switch (RequestCode){
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
                    break;
                case SELECT_PICTURE:
                    Uri path = intent.getData();
                    mSetImage.setImageURI(path);
                    break;

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "fotografia No tomada",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
