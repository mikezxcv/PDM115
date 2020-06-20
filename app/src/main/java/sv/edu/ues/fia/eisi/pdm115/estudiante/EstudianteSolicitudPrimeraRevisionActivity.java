package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.docente.AdmAprobarsolicitudDiferido;

public class EstudianteSolicitudPrimeraRevisionActivity extends AppCompatActivity {
    String carnet;
    String nombre;
    String materia;
    String evaluacion;
    String fecha= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
    String fechaLimiteDetalleEvaluacion;
    TextView carnet1;
    TextView nombre1;
    TextView materia1;
    TextView evaluacion1;

    Button btnEnviarSolicitud;
    Button probarFechas;

    String idMateriaIngresada;

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
        materia1 = (TextView) findViewById(R.id.editMateria);
        evaluacion1 = (TextView) findViewById(R.id.editEvaluacion);
        btnEnviarSolicitud = (Button) findViewById(R.id.enviarSolicitud);

        btnEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(compararFechas()){
                    crearSolicitudPR();
                }else{
                    Toast.makeText(getApplicationContext(),"El tiempo para hacer la solicitud a expirado",Toast.LENGTH_SHORT).show();
                }

            }
        });

        evaluacion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carnetGET = carnet1.getText().toString();
                String materiaGET = materia1.getText().toString();

                if(carnetGET.isEmpty() || materiaGET.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Carnet o Materia estan vacios",Toast.LENGTH_SHORT).show();
                }else{
                    helper.abrir();
                    final String [] evaluaciones= helper.obtenerEvaluacionesCR(carnetGET, materiaGET);
                    helper.cerrar();

                    AlertDialog.Builder mensaseListaElementos =
                            new AlertDialog.Builder(EstudianteSolicitudPrimeraRevisionActivity.this);
                    mensaseListaElementos.setTitle("Escoga una Evaluacion ");
                    mensaseListaElementos.setItems(evaluaciones,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int item)
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "Opción elegida: " + evaluaciones[item],
                                            Toast.LENGTH_SHORT).show();
                                    helper.abrir();

                                    evaluacion1.setText(evaluaciones[item]);
                                    //idMateriaIngresada= helper.idMateriasCR();
                                    //Toast.makeText(getApplicationContext(),idMateriaIngresada,Toast.LENGTH_SHORT).show();
                                }
                            });
                    mensaseListaElementos.show();
                }
            }
        });

        materia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluacion1.setText(null);
                helper.abrir();
                final String [] materias= helper.obtenerMateriasCR();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(EstudianteSolicitudPrimeraRevisionActivity.this);
                mensaseListaElementos.setTitle("Escoga una materia ");
                mensaseListaElementos.setItems(materias,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Opción elegida: " + materias[item],
                                        Toast.LENGTH_SHORT).show();
                                helper.abrir();
                                materia1.setText(materias[item]);

                            }
                        });
                mensaseListaElementos.show();
            }
        });
    }

    public boolean compararFechas(){
        boolean lapsoValido = false;
        ControlBdGrupo12 helper = new ControlBdGrupo12(EstudianteSolicitudPrimeraRevisionActivity.this);
        materia = materia1.getText().toString();
        evaluacion = evaluacion1.getText().toString();
        carnet  = carnet1.getText().toString();

        helper.abrir();
        fechaLimiteDetalleEvaluacion = helper.fechaLimiteDetalleEvaluacionPRIMERREVICION(materia, evaluacion, carnet);
        helper.cerrar();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendarLimiteSegundaRevision = Calendar.getInstance();

        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            String fechaLocal= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
            if(fechaLimiteDetalleEvaluacion  == null){
                Toast.makeText(EstudianteSolicitudPrimeraRevisionActivity.this, "No se encontro fecha asignada Con los datos Proposionados Para hacer usta revicion ", Toast.LENGTH_SHORT).show();
            }else{
                Date fechaLimite = formateador.parse(fechaLimiteDetalleEvaluacion);

                Date fechaSolicitud = formateador.parse(fechaLocal);

                calendar2.setTime(fechaLimite);
                int yearLimite=calendar1.get(Calendar.YEAR);
                int monthLimite=  calendar1.get(Calendar.MONTH) + 1;
                int dayLimite= calendar1.get(Calendar.DAY_OF_MONTH);


                calendar1.setTime(fechaSolicitud);
                int yearLocal=calendar1.get(Calendar.YEAR);
                int monthLocal=  calendar1.get(Calendar.MONTH) + 1;
                int dayLocal= calendar1.get(Calendar.DAY_OF_MONTH);


                long tiempoLocal = calendar1.getTimeInMillis();
                long tiempoLimite = calendar2.getTimeInMillis();


                long dias = ((tiempoLimite - tiempoLocal) / (1000*60*60*24));

                if(dias >= 0){
                    lapsoValido = true;
                }

                calendarLimiteSegundaRevision = calendar1;
                calendarLimiteSegundaRevision.add(Calendar.DAY_OF_MONTH, 3);
            }

        }
        catch (ParseException e)
        {
            Toast.makeText(EstudianteSolicitudPrimeraRevisionActivity.this, "No extiste Fecha Limite de Evaluacion", Toast.LENGTH_SHORT).show();
        }
        return lapsoValido;
    }

public void crearSolicitudPR () {
    try {
        carnet  = carnet1.getText().toString();
        materia = materia1.getText().toString();
        evaluacion = evaluacion1.getText().toString();

        if(carnet.isEmpty()||materia.isEmpty()||evaluacion.isEmpty()){
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
        }
        else{
            helper.abrir();
            if(helper.insertPrimerRevision(fecha, carnet, materia, evaluacion) == null){
                Toast.makeText(this, "Ya existe una revision con estos datos", Toast.LENGTH_LONG).show();
                helper.cerrar();
            }else{
                if(helper.insertPrimerRevision(fecha, carnet, materia, evaluacion).equals("1")){
                    helper.insertPrimerRevision(fecha, carnet, materia, evaluacion);
                    helper.cerrar();
                    Toast.makeText(this, "Datos Insertados", Toast.LENGTH_LONG).show();
                }
            }


        }
    }
     catch (Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
     }
    }



}