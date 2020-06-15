package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteSolicitudPrimeraRevisionActivity extends AppCompatActivity {
String carnet;
String nombre;
String materia;
String evaluacion;
String fecha= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());

TextView carnet1;
TextView nombre1;
TextView materia1;
TextView evaluacion1;

Button btnEnviarSolicitud;

    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitud_primera_revision);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.fecha_evaluacion);
        textViewDate.setText(currentDate);


        carnet1 = (TextView) findViewById(R.id.editCarnet);
        nombre1 = (TextView) findViewById(R.id.editNombre);
        materia1 = (TextView) findViewById(R.id.editMateria);
        evaluacion1 = (TextView) findViewById(R.id.editEvaluacion);
        btnEnviarSolicitud = (Button) findViewById(R.id.enviarSolicitud);
        btnEnviarSolicitud.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                crearSolicitudPR();
                //
                /*helper = new ControlBdGrupo12(EstudianteSolicitudPrimeraRevisionActivity.this);

                if (helper.verificarIntegridad(carnet, nombre, materia, evaluacion, 1)) {
                    crearSolicitudPR(v);
                } else {
                    Toast.makeText(EstudianteSolicitudPrimeraRevisionActivity.this, "No asistio o datos no coincidenntes", Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

public void crearSolicitudPR ()
{
try {
    carnet  = carnet1.getText().toString();
    nombre  = nombre1.getText().toString();
    materia = materia1.getText().toString();
    evaluacion = evaluacion1.getText().toString();

    if(carnet.isEmpty()||nombre.isEmpty()||materia.isEmpty()||evaluacion.isEmpty()){
        Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
    }
    else{
        helper.insertPrimerRevision(fecha, carnet, materia, evaluacion);
    }
}
 catch (Exception e) {
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
 }
}

}