package sv.edu.ues.fia.eisi.pdm115.estudiante;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.Repetido;

public class EstudianteSolicitudRepetidoActivity extends Activity {

    ControlBdGrupo12 helper;
    EditText editCarnet;
    EditText editNombreMateria;
    EditText editNombreEvaluacion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitud_repetido);

        helper = new ControlBdGrupo12(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombreMateria = (EditText) findViewById(R.id.editNombreMateria);
        editNombreEvaluacion = (EditText) findViewById(R.id.editNombreEvaluacion);
    }
    public void enviarSolicitudRepetido(View v) {

        String regInsertados;
        String carnet=editCarnet.getText().toString();
        String nombremateria=editNombreMateria.getText().toString();
        String nombreevaluacion=editNombreEvaluacion.getText().toString();
        Repetido repetido= new Repetido();
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        //repetido.setID_DETALLEALUMNOSEVALUADOS();
        repetido.setFECHASOLICITUDREPETIDO(fechaActual);
        repetido.setMATERIA(nombremateria);
        helper.abrir();
        regInsertados=helper.enviarSolicitudRepetido(repetido,carnet,nombreevaluacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void nuevaSolicitudRepetido(View v) {

        editCarnet.setText("");
        editNombreMateria.setText("");
        editNombreEvaluacion.setText("");
    }
}
