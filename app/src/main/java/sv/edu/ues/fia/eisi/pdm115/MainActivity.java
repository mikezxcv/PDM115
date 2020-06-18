package sv.edu.ues.fia.eisi.pdm115;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] menuAdmin={"Estudiante","Docente","Encargado de Impresion","Web Services","LLenar Base de Datos"};
    String[] menuDocente={"Docente","Web Services","LLenar Base de Datos"};
    String[] menuEncargadoImpresion={"Encargado de Impresion","LLenar Base de Datos"};
    String[] menuEstudiante={"Estudiante","LLenar Base de Datos"};

    String[] activitiesAdmin={"EstudianteMenuActivity","DocenteMenuActivity","EncargadoImpresionesMenuActivity","webServices"};
    String[] activitiesDocente={"DocenteMenuActivity","webServices"};
    String[] activitiesEncargadoImpresion={"EncargadoImpresionesMenuActivity"};
    String[] activitiesEstudiante={"EstudianteMenuActivity"};

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
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuAdmin);
            listViewMain.setAdapter(arrayAdapter);
            listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(position!=4){
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
        if(user.contentEquals("DOCENTE") || shared.contentEquals("DOCENTE")){

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
        if(user.contentEquals("IMPRESIONADMIN") || shared.contentEquals("IMPRESIONADMIN")){

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

}

