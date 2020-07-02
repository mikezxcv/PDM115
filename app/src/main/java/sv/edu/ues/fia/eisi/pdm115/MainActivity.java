package sv.edu.ues.fia.eisi.pdm115;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    String[] activitiesAdmin={"EstudianteMenuActivity","DocenteMenuActivity","EncargadoImpresionesMenuActivity","webServices","Mostrar Ubicaciones (MAPA)"};
    String[] activitiesDocente={"DocenteMenuActivity","webServices"};
    String[] activitiesEncargadoImpresion={"EncargadoImpresionesMenuActivity"};
    String[] activitiesEstudiante={"EstudianteMenuActivity"};
    String[] impresionUsuarios;

    String[] permisos = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            // Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private  static final int REQUEST_CODE = 1001;

    ControlBdGrupo12 BDhelper;
    ListView listViewMain;
    TextView loginUser;
    TextView sharedPrefsUser;
    private SharedPreferences prefs;
    private int datoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] menuAdmin={ getString(R.string.estudianteMENU),getString(R.string.DocenteMENU),getString(R.string.EncargadoImpresionMENU), getString(R.string.WebServicesMENU),"Edificios de Ingenieria (MAPA)"};
        String[] menuDocente={"Docente","Web Services","LLenar Base de Datos"};
        String[] menuEncargadoImpresion={"Encargado de Impresion"};
        String[] menuEstudiante={"Estudiante"};


        BDhelper = new ControlBdGrupo12(this);
        loginUser= findViewById(R.id.textloginUser);
        listViewMain= (ListView) findViewById(R.id.listViewMainMenu);
        sharedPrefsUser= findViewById(R.id.textSharedPrefsUser);
        //OBTENER DATOS DEL SHARED PREFERNCES Y DEL LOGIN
        Bundle bundle= getIntent().getExtras();
       String user= bundle.getString("usuario");
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String shared=prefs.getString("user","");

        //menu de admin
        if(user.contentEquals("ADMIN") || shared.contentEquals("ADMIN")){
            checarPermisos();

            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuAdmin);
            listViewMain.setAdapter(arrayAdapter);
            listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(position!=5){
                        String nombreValue=activitiesAdmin[position];
                        if(position==0){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.estudiante."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }
                        if(position==1){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }
                        if(position==2){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }
                        if(position==3){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.webServices.MenuWebServices");
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }
                        if(position==4){
                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.mapa.MenuMapsActivity");
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }
                        }


                    }else{
                        BDhelper.abrir();
                        String mensaje =BDhelper.llenarBDCarnet();
                        BDhelper.cerrar();
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        //menu estudiante
        if(user.contentEquals("ESTUDIANTE") || shared.contentEquals("ESTUDIANTE")){

            ArrayAdapter<String>  arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuEstudiante);
            listViewMain.setAdapter(arrayAdapter);
            listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(position!=1){
                        String nombreValue=activitiesEstudiante[position];
                        if(position==0){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.estudiante."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }



                    }else{
                        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(MainActivity.this);
                        BDhelper.abrir();
                        String mensaje =BDhelper.llenarBDCarnet();
                        BDhelper.cerrar();
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        //menu docente
        if((user.contentEquals("DOCENTE") || shared.contentEquals("DOCENTE")) ||
                (user.contentEquals("DOCENTE2") || shared.contentEquals("DOCENTE2")) ||
                (user.contentEquals("DOCENTE3") || shared.contentEquals("DOCENTE3"))){
            checarPermisos();
            ArrayAdapter<String>  arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuDocente);
            listViewMain.setAdapter(arrayAdapter);
            listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(position!=2){
                        String nombreValue=activitiesDocente[position];
                        if(position==0){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }
                        if(position==1){


                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.webServices.MenuWebServices");
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }

                        }




                    }else{
                        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(MainActivity.this);
                        BDhelper.abrir();
                        String mensaje =BDhelper.llenarBDCarnet();
                        BDhelper.cerrar();
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        //menu encargado impresiones
        BDhelper.abrir();
        impresionUsuarios = BDhelper.obtenerUsuariosEncargados();
        BDhelper.cerrar();
        if(Arrays.asList(impresionUsuarios).contains(user) || Arrays.asList(impresionUsuarios).contains(shared)){
            checarPermisos();
            ArrayAdapter<String>  arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuEncargadoImpresion);
            listViewMain.setAdapter(arrayAdapter);
            listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(position!=1){
                        String nombreValue=activitiesEncargadoImpresion[position];
                        if(position==0){

                            try{
                                Class<?>
                                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion."+nombreValue);
                                Intent inte = new Intent(getApplicationContext(),clase);
                                startActivity(inte);
                            }catch(ClassNotFoundException e){
                                e.printStackTrace();
                            }
                        }
                    }else{
                        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(MainActivity.this);
                        BDhelper.abrir();
                        String mensaje =BDhelper.llenarBDCarnet();
                        BDhelper.cerrar();
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_logout){ logOut(); }
        return super.onOptionsItemSelected(item);

    }
    private void logOut(){
        prefs.edit().clear().apply();
        Intent intent= new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public boolean checarPermisos() {
        // Checar permisos de almacenamiento (Escritura) y camara.
        List<String> listaPermisos = new ArrayList<>();

        for(String perm : permisos){
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED){
                listaPermisos.add(perm);
            }
        }
        if (!listaPermisos.isEmpty()){
            ActivityCompat.requestPermissions(this, listaPermisos.toArray(new String[listaPermisos.size()]),REQUEST_CODE);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE){
            HashMap<String, Integer> permResult = new HashMap<>();
            int deniedCount = 0;

            for (int i=0; i<grantResults.length; i++){
                if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                    permResult.put(permissions[i], grantResults[i]);
                    deniedCount++;
                }
            }
            if (deniedCount==0){
                //Terminar
            }
            else {
                showDialog("Permisos necesarios", "Se necesitan permisos de almacenamiento y " +
                                "acceso a la camara. Al rechazarlos, algunas funciones pueden no estar disponibles",
                        "Otorgar permisos", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        checarPermisos();
                    }
                    },"Continuar sin permisos", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                    },false);
            }
        }
    }

    private AlertDialog showDialog(String title, String msg, String positiveLabel,
                                   DialogInterface.OnClickListener positiveOnClick,
                                   String negativeLabel, DialogInterface.OnClickListener negativeOnClick,
                                   boolean isCancelable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(isCancelable);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveLabel, positiveOnClick);
        builder.setNegativeButton(negativeLabel, negativeOnClick);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return  alertDialog;
    }
}

