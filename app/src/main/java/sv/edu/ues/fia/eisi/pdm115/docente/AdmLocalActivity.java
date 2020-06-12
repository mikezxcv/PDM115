package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmLocalActivity extends AppCompatActivity {
    ControlBdGrupo12 helper;
    ListView listView;

    String [] datos;
    String [] idLocales;
    FloatingActionButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_local);
        listView= (ListView) findViewById(R.id.listViewv);
        boton= (FloatingActionButton) findViewById(R.id.botonEmergente);
        helper= new ControlBdGrupo12(this);
        helper.abrir();
        datos= helper.obtenerLocales();
        idLocales=helper.IDLocales();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent= new Intent(AdmLocalActivity.this,AdmLocalDetalle.class);
                intent.putExtra("id",idLocales[position]);
                startActivity(intent);

            }
        });

        //agregar nuevo local
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_SHORT).show();


                //implementat alert
                AlertDialog.Builder builder= new AlertDialog.Builder(AdmLocalActivity.this);
                View viewInflated= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_adm_local_crear,null);
                builder.setView(viewInflated);
                final EditText idLocal=(EditText) viewInflated.findViewById(R.id.idcrearLocal);
                final EditText nombreLocal=(EditText) viewInflated.findViewById(R.id.nombrecrearLocal);
                final EditText ubicacion=(EditText) viewInflated.findViewById(R.id.ubicacioncrearLocal);
                builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Locales local= new Locales();
                        local.setIdLocal(idLocal.getText().toString());
                        local.setNombreLocal(nombreLocal.getText().toString());
                        local.setUbicacion(ubicacion.getText().toString());
                        helper.abrir();
                        String resultado= helper.insertar(local);
                        helper.cerrar();
                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                        //recargar el activity para mostrar los datos
                        recreate();



                    }
                });
                builder.setTitle("Crear Nuevo Local");
                builder.setMessage("Ingrese los datos del local");
                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.abrir();
        datos= helper.obtenerLocales();
        idLocales=helper.IDLocales();
        helper.cerrar();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        //carga el listview con los datos

    }
}
