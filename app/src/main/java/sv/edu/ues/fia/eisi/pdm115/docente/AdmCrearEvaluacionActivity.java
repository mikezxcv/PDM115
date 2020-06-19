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

import sv.edu.ues.fia.eisi.pdm115.R;
import java.sql.Date;
import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmCrearEvaluacionActivity extends Activity {
    ControlBdGrupo12 helper;
    TextView idEvaluacion;
    TextView tipoEvaluacion;
    TextView nombreEvaluacion;
    TextView fechaEvaluacion;
    Button crear;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    private static final String TAG = "AdmCrearEvaluacionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_evaluacion_crear);
        helper= new ControlBdGrupo12(this);
        nombreEvaluacion=(TextView) findViewById(R.id.nombreEvaluacion);
        fechaEvaluacion=(TextView) findViewById(R.id.fechaEvaluacion);
        crear= (Button) findViewById(R.id.crearEvaluacion);

        fechaEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmCrearEvaluacionActivity.this,
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

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(AdmCrearEvaluacionActivity.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón positivo, así que la acción está confirmada
                                    Evaluacion evaluacion = new Evaluacion();
                                    evaluacion.setNombreEvaluacion(nombreEvaluacion.getText().toString());
                                    evaluacion.setFechaEvaluacion(fechaEvaluacion.getText().toString());
                                    helper.abrir();
                                    String resultado = helper.insertar(evaluacion);
                                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
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
                        .setMessage("¿Está seguro de crear esta evaluación?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.create();
                dialogo.show();


            }
        });

    }
}
