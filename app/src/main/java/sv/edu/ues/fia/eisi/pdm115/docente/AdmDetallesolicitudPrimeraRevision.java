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
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudPrimeraRevision extends AppCompatActivity {
    Button btn;
    Button btn2;
    Button eliminar;
    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;
    EditText fechasolicitud;
    //id viene desde el intent detalle de primer revision
    String idPrimerRevision;
    ControlBdGrupo12 helper;
    //crear dialogo
    AlertDialog dialogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detallesolicitud_primera_revision);
        helper= new ControlBdGrupo12(this);
         btn = (Button) findViewById(R.id.irAorobarSolicitudPrimeraRevision);
         btn2= (Button) findViewById(R.id.darRevisionPrimeraRevision);
         carnet= (EditText) findViewById(R.id.carnetSolPrimeraRevision);
         nombre= (EditText) findViewById(R.id.nombreSolPrimeraRevision);
         materia=(EditText) findViewById(R.id.materiaSolPrimeraRevision);
         evaluacion=(EditText) findViewById(R.id.evaluacionSolPrimeraRevision);
         fechasolicitud= (EditText) findViewById(R.id.fechasolicitudSolPrimeraRevision);
         eliminar= (Button) findViewById(R.id.eliminarSolicitud);


        //ontener los datos de ADMPRIMERAREVISIONACTIVITY
        Bundle bundle= getIntent().getExtras();

        carnet.setText(bundle.getString("carnet"));
        nombre.setText(bundle.getString("nombre"));
        materia.setText(bundle.getString("materia"));
        evaluacion.setText(bundle.getString("evaluacion"));
        fechasolicitud.setText(bundle.getString("fechaSolicitud"));
        idPrimerRevision= bundle.getString("id");

        //aprobar Revision
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudPrimeraRevision.this, AdmAprobarSolPrimeraRevision.class);
                intent.putExtra("id",idPrimerRevision);
                startActivity(intent);
            }
        });
        //dar revision
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudPrimeraRevision.this, AdmDarRevisionPrimeraRevision.class);
                intent.putExtra("id",idPrimerRevision);
                startActivity(intent);
            }
        });

        //eliminar solicitud
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //crear dialogo y llamarlo
                crearDialog();
                dialogo.show();

            }
        });




    }
    public void crearDialog(){
        dialogo = new AlertDialog
                .Builder(AdmDetallesolicitudPrimeraRevision.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idprimerRev= idPrimerRevision;
                        PrimeraRevision primeraRevision=new PrimeraRevision();
                        primeraRevision.setIdPrimeraRevision(idprimerRev);
                        helper.abrir();
                        String resultado= helper.eliminar(primeraRevision);
                        helper.cerrar();
                       Intent intent= new Intent(AdmDetallesolicitudPrimeraRevision.this,AdmPrimeraRevisionActivity.class);

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
                .setMessage("¿Deseas eliminar esta Solicitud de Primera Revision?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }

}
