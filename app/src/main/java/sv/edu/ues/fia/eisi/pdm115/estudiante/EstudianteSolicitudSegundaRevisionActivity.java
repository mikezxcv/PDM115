package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteSolicitudSegundaRevisionActivity extends AppCompatActivity {
    String carnet;
    String materia;
    String evaluacion;
    String fecha= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
    int fechaLimiteDetalleEvaluacion;

    TextView carnet2;
    TextView materia2;
    TextView evaluacion2;

    Button btnEnviarSolicitudSR;

    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitud_segunda_revision);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.fecha_evaluacionSR);
        textViewDate.setText(currentDate);
        carnet2=(TextView) findViewById(R.id.editCarnet2);
        materia2=(TextView) findViewById(R.id.editMateria2);
        evaluacion2=(TextView) findViewById(R.id.editEvaluacion2);
        btnEnviarSolicitudSR=(Button) findViewById(R.id.enviarSolicitud2R);

        btnEnviarSolicitudSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(compararFechas()){
                    crearSolicitudSR();
                }else{
                    Toast.makeText(getApplicationContext(),"Fecha para hacer Segunda Revicion Caducada",Toast.LENGTH_SHORT).show();
                }
            }
        });

        materia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluacion2.setText(null);
                helper.abrir();
                final String [] materias= helper.obtenerMateriasCR();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(EstudianteSolicitudSegundaRevisionActivity.this);
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
                                materia2.setText(materias[item]);

                            }
                        });
                mensaseListaElementos.show();
            }
        });

        materia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluacion2.setText(null);
                helper.abrir();
                final String [] materias= helper.obtenerMateriasCR();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(EstudianteSolicitudSegundaRevisionActivity.this);
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
                                materia2.setText(materias[item]);

                            }
                        });
                mensaseListaElementos.show();
            }
        });

        evaluacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carnetGET = carnet2.getText().toString();
                String materiaGET = materia2.getText().toString();

                if(carnetGET.isEmpty() || materiaGET.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Carnet o Materia estan vacios",Toast.LENGTH_SHORT).show();
                }else{
                    helper.abrir();

                    final String [] evaluaciones= helper.obtenerEvaluacionesCR(carnetGET, materiaGET);
                    helper.cerrar();

                    AlertDialog.Builder mensaseListaElementos =
                            new AlertDialog.Builder(EstudianteSolicitudSegundaRevisionActivity.this);
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

                                    evaluacion2.setText(evaluaciones[item]);
                                    //idMateriaIngresada= helper.idMateriasCR();
                                    //Toast.makeText(getApplicationContext(),idMateriaIngresada,Toast.LENGTH_SHORT).show();
                                }
                            });
                    mensaseListaElementos.show();
                }
            }
        });

    }

    public boolean compararFechas(){
        boolean lapsoValido = false;
        ControlBdGrupo12 helper = new ControlBdGrupo12(EstudianteSolicitudSegundaRevisionActivity.this);
        materia = materia2.getText().toString();
        evaluacion = evaluacion2.getText().toString();
        carnet  = carnet2.getText().toString();

        helper.abrir();
        String fechaLimiteDetalleEvaluacion = helper.fechaLimiteParaSegundaRevicion(materia, evaluacion, carnet);
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
                Toast.makeText(EstudianteSolicitudSegundaRevisionActivity.this, "O No se encontro fecha asignada Con los datos Proposionados Para hacer usta revicion ", Toast.LENGTH_SHORT).show();
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
            }

        }
        catch (ParseException e)
        {
            Toast.makeText(EstudianteSolicitudSegundaRevisionActivity.this, "No extiste Fecha Limite de Evaluacion", Toast.LENGTH_SHORT).show();
        }
        return lapsoValido;
    }

    public void crearSolicitudSR(){

        // Consultar primeraRevision
        try {
            carnet=carnet2.getText().toString();
            materia=materia2.getText().toString();
            evaluacion=evaluacion2.getText().toString();
            helper.abrir();
            int idPrimeraRev = helper.idSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
            String idENVIAR = String.valueOf(idPrimeraRev);
            helper.cerrar();
            if(carnet.isEmpty()||materia.isEmpty()||evaluacion.isEmpty())
            {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }else{
                if(idPrimeraRev == 0){
                    Toast.makeText(this, "No realizo Primera rev con los datos insertados", Toast.LENGTH_SHORT).show();
                }else{
                    String result =  helper.insertSegundaRevision(fecha, carnet, materia, evaluacion, idENVIAR);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }





    }
}

