package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudRepetido extends AppCompatActivity {
    Button btnEliminar;
    Button btnAprobar;
    Button asignarNota;

    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;
    EditText fechasolicitud;

    //id viene desde el intent detalle de primer revision
    String idDiferido;
    ControlBdGrupo12 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_repetido);


        helper= new ControlBdGrupo12(this);

        btnEliminar = (Button) findViewById(R.id.irEliminarSolicitidDiferido);
        btnAprobar= (Button) findViewById(R.id.irAprobarSolicitudDiferido);

        carnet= (EditText) findViewById(R.id.carnetSolicitudRepetido);
        nombre= (EditText) findViewById(R.id.nombreSolicitudRepetido);
        materia=(EditText) findViewById(R.id.materiaSolicitudRepetido);
        evaluacion=(EditText) findViewById(R.id.evaluacionSolicitudRepetido);
        fechasolicitud=(EditText) findViewById(R.id.fechaSolicitudRepetido);

        // Obteniendo datos de AdmDiferidoActivity
        Bundle bundle= getIntent().getExtras();
        idDiferido= bundle.getString("idRepetido");

        carnet.setText(bundle.getString("carnet"));
        nombre.setText(bundle.getString("nombre"));
        materia.setText(bundle.getString("materia"));
        evaluacion.setText(bundle.getString("evaluacion"));
        fechasolicitud.setText(bundle.getString("fechaSolicitud"));


        btnAprobar = (Button) findViewById(R.id.irAprobarSolicitudRepetido);

        btnAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudRepetido.this, AdmAprobarSolRepetido.class);
                intent.putExtra("idRepetido",idDiferido);
                startActivity(intent);
            }
        });
    }
}
