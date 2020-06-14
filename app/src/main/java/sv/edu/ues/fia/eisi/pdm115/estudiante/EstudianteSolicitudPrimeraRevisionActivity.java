package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;


public class EstudianteSolicitudPrimeraRevisionActivity extends AppCompatActivity {
String carnet;
String nombre;
String materia;
String evaluacion;
String fecha="2020-12-31";

TextView carnet1;
TextView nombre1;
TextView materia1;
TextView evaluacion1;

Button btnEnviarSolicitud;

    ControlBdGrupo12 helper;
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

public void crearSolicitudPR (View view)
{

 carnet  = carnet1.getText().toString();
 nombre  = nombre1.getText().toString();
 materia = materia1.getText().toString();
 evaluacion = evaluacion1.getText().toString();

    if(carnet.isEmpty()||nombre.isEmpty()||materia.isEmpty()||evaluacion.isEmpty()){
        Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
    }
    else{
       PrimeraRevision primeraRevisionTabla = new PrimeraRevision();

        primeraRevisionTabla.setFechaPrimeraRevision(fecha);

        // Pasamos el id que viene del intent
       /* String id=String.valueOf(helper.idPrimeraRevisionSolicitudCV(carnet, nombre, materia, evaluacion));
        primeraRevisionTabla.setIdDetalleAlumnosEvaluados(id);
        helper.abrir();
        String resultado= helper.actualizar(primeraRevisionTabla);
        helper.cerrar();
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();*/
    }

}

}