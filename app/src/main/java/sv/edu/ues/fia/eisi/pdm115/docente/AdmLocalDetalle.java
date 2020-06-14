package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmLocalDetalle extends AppCompatActivity {
    ControlBdGrupo12 helper;
    TextView idLocal;
    TextView nombre;
    TextView ubicacion;
    Button actualizar;
    Button eliminar;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_local_detalle);
        helper= new ControlBdGrupo12(this);
        idLocal= (TextView) findViewById(R.id.idLocal);
        nombre= (TextView) findViewById(R.id.nombreLocal);
        ubicacion=(TextView) findViewById(R.id.ubicacionLocal);
        actualizar= (Button) findViewById(R.id.actualizarLocal);
        eliminar=(Button) findViewById(R.id.eliminarLocal);

        Bundle bundle=  getIntent().getExtras();
      id= bundle.getString("id");

      Locales local;
      helper.abrir();
        local=helper.getDataLocales(id);
        idLocal.setText(local.getIdLocal().toString());
        nombre.setText(local.getNombreLocal());
        ubicacion.setText(local.getUbicacion());
      helper.cerrar();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AlertDialog dialogo = new AlertDialog
                        .Builder(AdmLocalDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada
                                String idLocal= id;
                                Locales local= new Locales();
                                local.setIdLocal(id);
                                local.setNombreLocal(nombre.getText().toString());
                                local.setUbicacion(ubicacion.getText().toString());
                                helper.abrir();
                                String resultado=helper.actualizar(local);
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
                        .Builder(AdmLocalDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Si, Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada--eliminar
                                helper.abrir();
                                String resultado=helper.eliminar(id);
                                helper.cerrar();
                                Intent intent= new Intent(AdmLocalDetalle.this,AdmLocalActivity.class);
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
