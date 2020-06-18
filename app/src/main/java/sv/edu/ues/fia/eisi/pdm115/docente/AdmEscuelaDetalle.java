package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Escuela;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmEscuelaDetalle extends AppCompatActivity {
    ControlBdGrupo12 helper;
    TextView idarea;
    TextView nombre;
    TextView facultad;
    Button actualizar;
    Button eliminar;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_escuela_detalle);
        helper= new ControlBdGrupo12(this);
        idarea= (TextView) findViewById(R.id.escuela_idarea);
        nombre= (TextView) findViewById(R.id.escuela_nombre);
        facultad=(TextView) findViewById(R.id.escuela_facultad);
        actualizar= (Button) findViewById(R.id.actualizarLocal);
        eliminar=(Button) findViewById(R.id.eliminarLocal);

        Bundle bundle=  getIntent().getExtras();
        id= bundle.getString("id");
        Escuela escuela;
        helper.abrir();
        escuela=helper.getDataEscuela(id);
        idarea.setText(String.valueOf(escuela.getIdArea()));
        nombre.setText(escuela.getNombreEscuela());
        facultad.setText(escuela.getFacultad());
        helper.cerrar();
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(AdmEscuelaDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada
                                String idEscuela= id;
                                Escuela escuela= new Escuela();
                                escuela.setIdEscuela(id);
                                escuela.setIdArea(Integer.valueOf(idarea.getText().toString()));
                                escuela.setNombreEscuela(nombre.getText().toString());
                                escuela.setFacultad(facultad.getText().toString());
                                helper.abrir();
                                String resultado=helper.actualizar(escuela);
                                Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                                helper.cerrar();





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
                        .setMessage("¿Desea Actualizar estos Datos?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.create();
                dialogo.show();


            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(AdmEscuelaDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Si, Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada--eliminar
                                helper.abrir();
                                String resultado=helper.eliminarEscuela(id);
                                helper.cerrar();
                                Intent intent= new Intent(AdmEscuelaDetalle.this,AdmEscuelaActivity.class);
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
                        .setTitle("Confirmar Eliminacion") // El título
                        .setMessage("¿Desea Eliminar estos Datos?") // El mensaje

                        .create(); // No olvides llamar a Create, ¡pues eso crea el AlertDialog!

                dialogo.create();
                dialogo.show();


            }
        });

    }
}
