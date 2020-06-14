package sv.edu.ues.fia.eisi.pdm115.estudiante;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.Repetido;

public class EstudianteConsultarSolicitudRepetidoActivity extends Activity {

    ControlBdGrupo12 helper;
    EditText editCarnet;
    EditText editNombreMateria;
    EditText editNombreEvaluacion;
    EditText editEstado;
    EditText editFechaExamen;
    EditText editHoraExamen;
    EditText editLocal;
    EditText editNotaInicial;
    EditText editNotaFinal;
    EditText editObservaciones;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar_solicitud_repetido);
        helper = new ControlBdGrupo12(this);

        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombreMateria = (EditText) findViewById(R.id.editNombreMateria);
        editNombreEvaluacion = (EditText) findViewById(R.id.editNombreEvaluacion);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editFechaExamen = (EditText) findViewById(R.id.editFechaExamen);
        editHoraExamen = (EditText) findViewById(R.id.editHoraExamen);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editNotaInicial = (EditText) findViewById(R.id.editNotaInicial);
        editNotaFinal = (EditText) findViewById(R.id.editNotaFinal);
        editObservaciones = (EditText) findViewById(R.id.editObservaciones);
    }

    public void consultarEstadoSolicitudRepetido(View v) {

        helper.abrir();
        Repetido repetido = helper.consultarEstadoSolicitudRepetido(
                editCarnet.getText().toString(), editNombreMateria.getText().toString(), editNombreEvaluacion.getText().toString());
        helper.cerrar();
        if(repetido == null)
        {
            Toast.makeText(this, "Solicitud de prueba repetida no encontrada", Toast.LENGTH_LONG).show();
        }
        else
        {
            editEstado.setText(String.valueOf(repetido.getESTADOREPETIDO()));
            editFechaExamen.setText(repetido.getFECHAREPETIDO());
            editHoraExamen.setText(repetido.getHORAREPETIDO());
            editLocal.setText(String.valueOf(repetido.getLOCAL()));
            editNotaInicial.setText(String.valueOf(repetido.getNOTAANTESREPETIDO()));
            editNotaFinal.setText(String.valueOf(repetido.getNOTADESPUESREPETIDO()));
            editObservaciones.setText(repetido.getOBSERVACIONES());
            Toast.makeText(this, "Consulta exitosa!", Toast.LENGTH_LONG).show();
        }
    }

    public void nuevaConsulta(View v) {
        editCarnet.setText("");
        editNombreMateria.setText("");
        editNombreEvaluacion.setText("");
        editEstado.setText("");
        editFechaExamen.setText("");
        editHoraExamen.setText("");
        editLocal.setText("");
        editNotaInicial.setText("");
        editNotaFinal.setText("");
        editObservaciones.setText("");
    }
}

