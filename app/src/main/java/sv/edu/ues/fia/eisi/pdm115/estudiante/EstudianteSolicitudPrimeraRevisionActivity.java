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
    int fechaLimiteDetalleEvaluacion;
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
        nombre1 = (TextView) findViewById(R.id.editNombre);
        materia1 = (TextView) findViewById(R.id.editMateria);
        evaluacion1 = (TextView) findViewById(R.id.editEvaluacion);
        btnEnviarSolicitud = (Button) findViewById(R.id.enviarSolicitud);
        probarFechas = (Button)findViewById(R.id.probarFechas);

        btnEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearSolicitudPR();
            }
        });
        probarFechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compararFechas();
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



    public void compararFechas(){
        ControlBdGrupo12 helper = new ControlBdGrupo12(this);
        materia = materia1.getText().toString();
        evaluacion = evaluacion1.getText().toString();
        carnet  = carnet1.getText().toString();

        fechaLimiteDetalleEvaluacion = helper.fechaLimiteDetalleEvaluacion(materia, evaluacion, carnet);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            String fechaLocal= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());

            //Date fechaLimite = formateador.parse(fechaLimiteDetalleEvaluacion);
            Date fechaSolicitud = formateador.parse(fechaLocal);

            //calendar1.setTime(fechaLimite);
            calendar2.setTime(fechaSolicitud);

            System.out.println("Compare Result : " + calendar2.compareTo(calendar1));
            System.out.println("Compare Result : " + calendar1.compareTo(calendar2));
        }
        catch (ParseException e)
        {
            Toast.makeText(EstudianteSolicitudPrimeraRevisionActivity.this, "No extiste Fecha Limite de Evaluacion", Toast.LENGTH_SHORT).show();
        }

    }

public void crearSolicitudPR () {
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