package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Diferido;
import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteSolicitudDiferidoActivity extends Activity implements
View.OnClickListener{

    ControlBdGrupo12 helper;
    EditText editCarnet;
    EditText editNombreMateria;
    EditText editNombreEvaluacion;
    RadioGroup grupo;
    Button opcion1,opcion2,opcion3;
    String motivoInasistencia=" ";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitud_diferido);

        helper = new ControlBdGrupo12(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombreMateria = (EditText) findViewById(R.id.editNombreMateria);
        editNombreEvaluacion = (EditText) findViewById(R.id.editNombreEvaluacion);
        opcion1=(RadioButton)findViewById(R.id.opcionTrabajo);
        opcion2=(RadioButton)findViewById(R.id.opcionChoqueEvaluaciones);
        opcion3=(RadioButton)findViewById(R.id.opcionEnfermedad);

    }
    public void enviarSolicitudDiferido(View v) {

        String regInsertados;
        String carnet=editCarnet.getText().toString();
        String nombremateria=editNombreMateria.getText().toString();
        String nombreevaluacion=editNombreEvaluacion.getText().toString();
        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((RadioButton) v).isChecked();
                if (checked){
                    motivoInasistencia="Trabajo";
                }
            }
        });
        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((RadioButton) v).isChecked();
                if (checked){
                    motivoInasistencia="Choque de evaluaciones";
                }
            }
        });
        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((RadioButton) v).isChecked();
                if (checked){
                    motivoInasistencia="Enfermedad";
                }
            }
        });
        if (motivoInasistencia==" ")Toast.makeText(this, "Seleccione un motivo de inasistencia", Toast.LENGTH_SHORT).show();
        else{
            Diferido diferido= new Diferido();
            String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            diferido.setFECHASOLICITUDDIFERIDO(fechaActual);
            diferido.setMATERIADIFERIDO(nombremateria);
            diferido.setMOTIVODIFERIDO(motivoInasistencia);
            helper.abrir();
            regInsertados=helper.enviarSolicitudDiferido(diferido,carnet,nombreevaluacion);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void nuevaSolicitudDiferido(View v) {

        editCarnet.setText("");
        editNombreMateria.setText("");
        editNombreEvaluacion.setText("");
    }

    @Override
    public void onClick(View v) {

    }
}
