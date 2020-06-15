package sv.edu.ues.fia.eisi.pdm115.estudiante;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.Diferido;

public class EstudianteConsultarSolicitudDiferidoActivity extends Activity {

    ControlBdGrupo12 helper;
    EditText editCarnet;
    EditText editNombreMateria;
    EditText editNombreEvaluacion;
    EditText editEstado;
    EditText editFechaDiferido;
    EditText editHoraDiferido;
    EditText editLocal;
    EditText editNotaInicial;
    EditText editObservaciones;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar_solicitud_diferido);
        helper = new ControlBdGrupo12(this);

        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombreMateria = (EditText) findViewById(R.id.editNombreMateria);
        editNombreEvaluacion = (EditText) findViewById(R.id.editNombreEvaluacion);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editFechaDiferido = (EditText) findViewById(R.id.editFechaDiferido);
        editHoraDiferido = (EditText) findViewById(R.id.editHoraDiferido);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editNotaInicial = (EditText) findViewById(R.id.editNotaInicial);
        editObservaciones = (EditText) findViewById(R.id.editObservaciones);
    }

    public void consultarEstadoSolicitudDiferido(View v) {

        helper.abrir();
        Diferido diferido = helper.consultarEstadoSolicitudDiferido(
                editCarnet.getText().toString(), editNombreMateria.getText().toString(), editNombreEvaluacion.getText().toString());
        helper.cerrar();
        if(diferido == null)
        {
            Toast.makeText(this, "Solicitud de prueba diferida no encontrada", Toast.LENGTH_LONG).show();
        }
        else
        {
            editEstado.setText(diferido.getESTADODIFERIDO());
            editFechaDiferido.setText(diferido.getFECHADIFERIDO());
            editHoraDiferido.setText(diferido.getHORADIFERIDO());
            editLocal.setText(diferido.getIDLOCAL());
            editNotaInicial.setText(String.valueOf(diferido.getNOTADIFERIDO()));
            editObservaciones.setText(diferido.getOBSERVACIONESDIFERIDO());
            Toast.makeText(this, "Consulta exitosa!", Toast.LENGTH_LONG).show();
        }
    }

    public void nuevaConsultaDiferido(View v) {
        editCarnet.setText("");
        editNombreMateria.setText("");
        editNombreEvaluacion.setText("");
        editEstado.setText("");
        editFechaDiferido.setText("");
        editHoraDiferido.setText("");
        editLocal.setText("");
        editNotaInicial.setText("");
        editObservaciones.setText("");
    }
}
