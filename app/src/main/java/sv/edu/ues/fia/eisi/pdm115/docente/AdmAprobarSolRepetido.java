package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.RepetidoTabla;
import sv.edu.ues.fia.eisi.pdm115.SolicitudDiferidoTabla;

public class AdmAprobarSolRepetido extends AppCompatActivity {
    Button btnGuardarSolRepetidio;
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;

    TextView fechaRealizarRepetido;
    TextView horaRepetido;
    TextView localRepetido;
    TextView observaciones;

    // Id Del intent
    String idRepetido;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";

    ControlBdGrupo12 helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_repetido);

        helper= new ControlBdGrupo12(this);
        estadoDenegado = (RadioButton)findViewById(R.id.radioRechazado);
        estadoAprobado = (RadioButton)findViewById(R.id.radioAprobado);

        fechaRealizarRepetido = (TextView) findViewById(R.id.editfechaEvaluacionRepetido);
        horaRepetido = (TextView)findViewById(R.id.editHoraRepetido);
        localRepetido = (TextView)findViewById(R.id.editLocalRepetido);
        observaciones = (TextView)findViewById(R.id.editObservacionesRepetido);



        btnGuardarSolRepetidio = (Button) findViewById(R.id.guardarRepetido);
        btnGuardarSolRepetidio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepetido(v);
            }
        });

        Bundle bundle=getIntent().getExtras();
        idRepetido= bundle.getString("idRepetido");
        Toast.makeText(this,idRepetido,Toast.LENGTH_LONG).show();
    }

    public void actualizarRepetido(View view){

        String fecha= fechaRealizarRepetido.getText().toString();
        String hora = horaRepetido.getText().toString();
        String local= localRepetido.getText().toString();
        String observacion= observaciones.getText().toString();


        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "Seleccione un estado",Toast.LENGTH_LONG).show();
        }
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos",Toast.LENGTH_LONG).show();
            }
            else{
                String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
                Toast.makeText(this, opcion,Toast.LENGTH_SHORT).show();

                RepetidoTabla repetidoTabla = new RepetidoTabla();

                repetidoTabla.setESTADOREPETIDO(opcion);
                repetidoTabla.setFECHAREPETIDO(fecha);
                repetidoTabla.setHORAREPETIDO(hora);
                repetidoTabla.setLOCAL(local);
                repetidoTabla.setOBSERVACIONES(observacion);

                // Pasamos el id que viene del intent
                repetidoTabla.setIDREPETIDO(idRepetido);
                helper.abrir();
                String resultado= helper.actualizar(repetidoTabla);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

            }
        }
    }

}
