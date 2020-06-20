package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.RolTabla;

public class AdmRolDetalle extends AppCompatActivity {
    ControlBdGrupo12 helper;
    TextView idLocal;
    TextView textVnombre;
    TextView ubicacion;
    Button actualizar;
    Button eliminar;
    String id;
    String nombreRol;

    AlertDialog dialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_rol_detalle);

        helper= new ControlBdGrupo12(this);
        textVnombre= (TextView) findViewById(R.id.editNombreRol);
        actualizar= (Button) findViewById(R.id.ActualizarROL);
        eliminar=(Button) findViewById(R.id.EliminarROL);

        Bundle bundle=  getIntent().getExtras();
        id= bundle.getString("id");
        nombreRol = bundle.getString("nombre");

        textVnombre.setText(nombreRol);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog();
                dialogo.show();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog2();
                dialogo.show();
            }
        });

    }
    public void crearDialog(){
        dialogo = new AlertDialog
                .Builder(AdmRolDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idRol = id;
                        helper.abrir();
                        String resultado= helper.eliminarRol(id);
                        helper.cerrar();
                        Intent intent= new Intent(AdmRolDetalle.this,AdmRolActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón negativo, no confirmaron
                        // Simplemente descartamos el diálogo
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar este ROL?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }

    public void crearDialog2(){
        dialogo = new AlertDialog
                .Builder(AdmRolDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idRol = id;
                        String name = textVnombre.getText().toString();
                        helper.abrir();
                        String resultado= helper.ActualizarRol(name,id);
                        helper.cerrar();
                        Intent intent= new Intent(AdmRolDetalle.this,AdmRolActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón negativo, no confirmaron
                        // Simplemente descartamos el diálogo
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas Actualizar?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }
}
