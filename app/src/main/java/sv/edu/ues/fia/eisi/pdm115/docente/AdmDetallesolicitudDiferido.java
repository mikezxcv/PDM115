package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudDiferido extends AppCompatActivity {

    // ----------------------------
    Button btnAprobar;
    Button btnAsignarNota;
    Button btnEliminar;

    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;

    //id viene desde el intent detalle de primer revision
    String idDiferido;
    String idDetalleEva;
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);

    AlertDialog dialogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detallesolicitud_diferido);
        helper= new ControlBdGrupo12(this);

        btnEliminar = (Button) findViewById(R.id.irEliminarSolicitidDiferido);
        btnAprobar= (Button) findViewById(R.id.irAprobarSolicitudDiferido);
        carnet= (EditText) findViewById(R.id.editCarnetDiferido);
        nombre= (EditText) findViewById(R.id.editNombreDiferido);
        materia=(EditText) findViewById(R.id.editMateriaDiferido);
        evaluacion=(EditText) findViewById(R.id.editEvaluacionDiferido);
        btnAsignarNota = (Button)findViewById(R.id.asignarNotaSolicitudDiferido);


        // Obteniendo datos de AdmDiferidoActivity
        Bundle bundle= getIntent().getExtras();
        idDiferido= bundle.getString("idDiferido");
        idDetalleEva = bundle.getString("idDetalle");

        carnet.setText(bundle.getString("carnet"));
        nombre.setText(bundle.getString("nombre"));
        materia.setText(bundle.getString("materia"));
        evaluacion.setText(bundle.getString("evaluacion"));


        btnAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudDiferido.this, AdmAprobarsolicitudDiferido.class);
                intent.putExtra("idDiferido",idDiferido);
                startActivity(intent);
            }
        });

        btnAsignarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControlBdGrupo12 helper = new ControlBdGrupo12(AdmDetallesolicitudDiferido.this);
                helper.abrir();
                String estadoDiferido = helper.estadoSolicitudDiferido(idDiferido);
                helper.cerrar();
                if("APROBADO".equals(estadoDiferido)){
                    Intent intent = new Intent(AdmDetallesolicitudDiferido.this, AdmAsignarNotaDiferido.class);
                    intent.putExtra("idDiferido",idDiferido);
                    intent.putExtra("idDetalle",idDetalleEva);
                    startActivity(intent);
                }else{
                    Toast.makeText(AdmDetallesolicitudDiferido.this, "La solicitud no Fue aprobada", Toast.LENGTH_SHORT).show();
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
                .Builder(AdmDetallesolicitudDiferido.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idprimerRev = idDiferido;

                        helper.abrir();
                        String resultado= helper.eliminarDiferido(idDiferido);
                        helper.cerrar();
                        Intent intent= new Intent(AdmDetallesolicitudDiferido.this,AdmDiferidoActivity.class);
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
                .setMessage("¿Deseas eliminar esta Solicitud Diferido?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }
}
