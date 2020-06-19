package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmEvaluacionDetalle extends Activity {
    ControlBdGrupo12 helper;
    TextView idEvaluacion;
    TextView tipoEvaluacion;
    TextView nombreEvaluacion;
    TextView fechaEvaluacion;
    Button actualizar;
    Button eliminar;
    String id;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    private static final String TAG = "AdmEvaluacionDetalle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_evaluacion_detalle);
        helper= new ControlBdGrupo12(this);
        nombreEvaluacion=(TextView) findViewById(R.id.nombreEvaluacion);
        fechaEvaluacion=(TextView) findViewById(R.id.fechaEvaluacion);
        actualizar= (Button) findViewById(R.id.actualizarEvaluacion);
        eliminar=(Button) findViewById(R.id.eliminarEvaluacion);

        Bundle bundle=  getIntent().getExtras();
        id= bundle.getString("id");


        Evaluacion evaluacion;
        helper.abrir();
        evaluacion=helper.getDataEvaluacionCRUD(Integer.valueOf(id));
        nombreEvaluacion.setText(evaluacion.getNombreEvaluacion());
        fechaEvaluacion.setText(evaluacion.getFechaEvaluacion());
        helper.cerrar();
        fechaEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmEvaluacionDetalle.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogo.show();


            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("LongLogTag")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                Log.d(TAG,"onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                if(month<10){
                    date = year + "-" + "0"+month + "-" + day;
                }else{
                    date = year + "-" + month + "-" + day;
                }
                fechaEvaluacion.setText(date);
            }
        };

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(AdmEvaluacionDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada
                                String idEvaluacion= id;
                                Evaluacion evaluacion= new Evaluacion();
                                evaluacion.setIdEvaluacion(Integer.valueOf(id));
                                evaluacion.setNombreEvaluacion(nombreEvaluacion.getText().toString());
                                evaluacion.setFechaEvaluacion(fechaEvaluacion.getText().toString());
                                helper.abrir();
                                String resultado=helper.actualizar(evaluacion);
                                Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                                helper.cerrar();





                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón negativo, no confirmaron
                                // Simplemente descartamos el diálogo
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar") // El título
                        .setMessage("¿Desea Actualizar estos Datos?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.create();
                dialogo.show();


            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(AdmEvaluacionDetalle.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Si, Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada--eliminar
                                helper.abrir();
                                String resultado=helper.eliminarEvaluacion(Integer.valueOf(id));
                                helper.cerrar();
                                Intent intent= new Intent(AdmEvaluacionDetalle.this,AdmGestionarEvaluacionesActivity.class);
                                startActivity(intent);


                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón negativo, no confirmaron
                                // Simplemente descartamos el diálogo
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar Eliminacion") // El título
                        .setMessage("¿Desea Eliminar estos Datos?") // El mensaje

                        .create(); // No olvides llamar a Create, ¡pues eso crea el AlertDialog!

                dialogo.create();
                dialogo.show();


            }
        });

    }
}
