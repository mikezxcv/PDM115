package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudRepetido extends AppCompatActivity {
    Button btnEliminar;
    Button btnAprobar;
    Button btnasignarNota;

    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;
    EditText fechasolicitud;

    //id viene desde el intent detalle de primer revision
    String idDiferido;
    String notaAntesRepetido;
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);

    AlertDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_repetido);


        helper= new ControlBdGrupo12(this);

        btnEliminar = (Button) findViewById(R.id.irEliminarSolicitidDiferido);
        btnAprobar= (Button) findViewById(R.id.irAprobarSolicitudDiferido);
        btnasignarNota = (Button)findViewById(R.id.asignarNotaRepetido);

        carnet= (EditText) findViewById(R.id.carnetSolicitudRepetido);
        nombre= (EditText) findViewById(R.id.nombreSolicitudRepetido);
        materia=(EditText) findViewById(R.id.materiaSolicitudRepetido);
        evaluacion=(EditText) findViewById(R.id.evaluacionSolicitudRepetido);
        fechasolicitud=(EditText) findViewById(R.id.fechaSolicitudRepetido);

        // Obteniendo datos de AdmDiferidoActivity
        Bundle bundle= getIntent().getExtras();
        idDiferido= bundle.getString("idRepetido");
        notaAntesRepetido= bundle.getString("notaAntesRepetido");

        carnet.setText(bundle.getString("carnet"));
        nombre.setText(bundle.getString("nombre"));
        materia.setText(bundle.getString("materia"));
        evaluacion.setText(bundle.getString("evaluacion"));
        fechasolicitud.setText(bundle.getString("fechaSolicitud"));


        btnAprobar = (Button) findViewById(R.id.irAprobarSolicitudRepetido);
        btnEliminar = (Button)findViewById(R.id.eliminarSolicitud);

        btnAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudRepetido.this, AdmAprobarSolRepetido.class);
                intent.putExtra("idRepetido",idDiferido);
                startActivity(intent);
            }
        });

        btnasignarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControlBdGrupo12 helper = new ControlBdGrupo12(AdmDetallesolicitudRepetido.this);
                helper.abrir();
                String estadoDiferido = helper.estadoSolicitudRepetido(idDiferido);
                helper.cerrar();

                if("APROBADO".equals(estadoDiferido)){
                    Intent intent = new Intent(AdmDetallesolicitudRepetido.this, AdmAsignarNotaRepetido.class);
                    intent.putExtra("idRepetido",idDiferido);
                    intent.putExtra("notaAntesRepetido",notaAntesRepetido);
                    startActivity(intent);
                }else{
                    Toast.makeText(AdmDetallesolicitudRepetido.this, "La Solicitud no fue aprobada", Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog();
                dialogo.show();
            }
        });
    }
    public void crearDialog(){
        dialogo = new AlertDialog
                .Builder(AdmDetallesolicitudRepetido.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idRepetido = idDiferido;

                        helper.abrir();
                        String resultado= helper.eliminarRepetido(idRepetido);
                        helper.cerrar();
                        Intent intent= new Intent(AdmDetallesolicitudRepetido.this,AdmRepetidoActivity.class);
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
                .setMessage("¿Deseas eliminar esta Solicitud de Repetido?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }
}
