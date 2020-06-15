package sv.edu.ues.fia.eisi.pdm115;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] menu={"Estudiante","Docente","Encargado de Impresion","Web Services","LLenar Base de Datos"};
    String[]
            activities={"EstudianteMenuActivity","DocenteMenuActivity","EncargadoImpresionesMenuActivity","webServices"};
    ControlBdGrupo12 BDhelper;
    ListView listViewMain;
    private int datoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDhelper = new ControlBdGrupo12(this);
        listViewMain= (ListView) findViewById(R.id.listViewMainMenu);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        listViewMain.setAdapter(arrayAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position!=4){
                    String nombreValue=activities[position];
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
        Intent intent= new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

