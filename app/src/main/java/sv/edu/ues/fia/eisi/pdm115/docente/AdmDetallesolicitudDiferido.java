package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudDiferido extends AppCompatActivity {

    // ----------------------------
    Button btnEliminar;
    Button btnAprobar;
    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;

    //id viene desde el intent detalle de primer revision
    String idDiferido;
    ControlBdGrupo12 helper;


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



        // Obteniendo datos de AdmDiferidoActivity
        Bundle bundle= getIntent().getExtras();
        idDiferido= bundle.getString("idDiferido");

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

    }
}
