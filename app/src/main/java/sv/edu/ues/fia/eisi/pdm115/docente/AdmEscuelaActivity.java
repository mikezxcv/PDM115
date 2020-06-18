package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Escuela;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmEscuelaActivity extends AppCompatActivity {
    ControlBdGrupo12 helper;
    ListView listView;
    String [] datos;
    String [] idEscuelas;
    FloatingActionButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_escuela);
        listView= (ListView) findViewById(R.id.listViewv);
        boton= (FloatingActionButton) findViewById(R.id.botonEmergente);
        helper= new ControlBdGrupo12(this);
        helper.abrir();
        datos= helper.obtenerEscuelas();
        idEscuelas =helper.IDescuelas();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent= new Intent(AdmEscuelaActivity.this,AdmEscuelaDetalle.class);
                intent.putExtra("id", idEscuelas[position]);
                startActivity(intent);

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //implementar alert
                AlertDialog.Builder builder= new AlertDialog.Builder(AdmEscuelaActivity.this);
                View viewInflated= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_adm_escuela_crear,null);
                builder.setView(viewInflated);
                //  final EditText idLocal=(EditText) viewInflated.findViewById(R.id.idcrearLocal);
                final EditText idarea=(EditText) viewInflated.findViewById(R.id.escuela_idarea);
                final EditText nombre=(EditText) viewInflated.findViewById(R.id.escuela_nombre);
                final EditText facultad=(EditText) viewInflated.findViewById(R.id.escuela_facultad);

                builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(TextUtils.isEmpty(idarea.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(facultad.getText().toString())){
                            Toast.makeText(getApplicationContext(),"Rellene todos los campos",Toast.LENGTH_SHORT).show();
                        }else{
                            Escuela escuela= new Escuela();
                            // local.setIdLocal(idLocal.getText().toString());
                            escuela.setIdarea(idarea.getText().toString());
                            escuela.setNombre(nombre.getText().toString());
                            escuela.setFacultad(facultad.getText().toString());
                            helper.abrir();
                            String resultado= helper.insertar(escuela);
                            helper.cerrar();
                            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                            //recargar el activity para mostrar los datos
                            recreate();
                        }
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
        datos= helper.obtenerEscuelas();
        idEscuelas =helper.IDescuelas();
        helper.cerrar();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        //carga el listview con los datos

    }
}
