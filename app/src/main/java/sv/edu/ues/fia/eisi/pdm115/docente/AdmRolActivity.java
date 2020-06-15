package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import sv.edu.ues.fia.eisi.pdm115.RolTabla;

public class AdmRolActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;

    String [] datos;
    String [] idRoles;
    FloatingActionButton boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_rol);

        listView= (ListView) findViewById(R.id.listViewv);
        boton= (FloatingActionButton) findViewById(R.id.botonEmergenteRol);
        helper= new ControlBdGrupo12(this);
        helper.abrir();
        //-----------
        datos= helper.obtenerRoles();
        idRoles=helper.idRoles();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(AdmRolActivity.this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent= new Intent(AdmRolActivity.this,AdmRolDetalle.class);
                intent.putExtra("id",idRoles[position]);
                intent.putExtra("nombre",datos[position]);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),idRoles[position],Toast.LENGTH_SHORT).show();

            }
        });

        //Agregar nuevo rol
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_SHORT).show();

                //implementat alert
                AlertDialog.Builder builder= new AlertDialog.Builder(AdmRolActivity.this);
                View viewInflated= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_adm_rol_crear,null);
                builder.setView(viewInflated);

                final EditText nombreLocal=(EditText) viewInflated.findViewById(R.id.editNombreRol);

                builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RolTabla rol= new RolTabla();

                        rol.setNOMBRE_ROL(nombreLocal.getText().toString());
                        ControlBdGrupo12 helper = new ControlBdGrupo12(AdmRolActivity.this);
                        helper.abrir();
                        // --------------
                        String resultado= helper.insertar(rol);
                        helper.cerrar();
                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                        //recargar el activity para mostrar los datos
                        recreate();
                    }
                });
                builder.setTitle("Crear Nuevo ROL");
                builder.setMessage("Ingrese los datos del ROL");
                AlertDialog dialog= builder.create();
                dialog.show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.abrir();
        datos= helper.obtenerRoles();
        idRoles=helper.idRoles();
        helper.cerrar();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        //carga el listview con los datos
    }
}
