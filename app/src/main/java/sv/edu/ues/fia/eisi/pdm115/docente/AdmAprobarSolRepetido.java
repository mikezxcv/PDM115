package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.RepetidoTabla;
import sv.edu.ues.fia.eisi.pdm115.SolicitudDiferidoTabla;

public class AdmAprobarSolRepetido extends AppCompatActivity {
    Button btnGuardarSolRepetidio;
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;

    TextView fechaRealizarRepetido;
    TextView horaRepetido;
    TextView localRepetido;
    TextView observaciones;

    // Id Del intent
    String idRepetido;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    private static final String TAG = "AdmAprobarsolicitudDiferido";
    String idLocalRevision;

    ControlBdGrupo12 helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_repetido);

        helper= new ControlBdGrupo12(this);
        estadoDenegado = (RadioButton)findViewById(R.id.radioRechazado);
        estadoAprobado = (RadioButton)findViewById(R.id.radioAprobado);

        fechaRealizarRepetido = (TextView) findViewById(R.id.editfechaEvaluacionRepetido);
        horaRepetido = (TextView)findViewById(R.id.editHoraRepetido);
        localRepetido = (TextView)findViewById(R.id.editLocalRepetido);
        observaciones = (TextView)findViewById(R.id.editObservacionesRepetido);



        btnGuardarSolRepetidio = (Button) findViewById(R.id.guardarRepetido);
        btnGuardarSolRepetidio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepetido(v);
            }
        });

        Bundle bundle=getIntent().getExtras();
        idRepetido= bundle.getString("idRepetido");
        Toast.makeText(this,idRepetido,Toast.LENGTH_LONG).show();


        // Selecciones
        fechaRealizarRepetido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmAprobarSolRepetido.this,
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
                fechaRealizarRepetido.setText(date);
            }
        };

        localRepetido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                final String [] locales= helper.obtenerLocales();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(AdmAprobarSolRepetido.this);
                mensaseListaElementos.setTitle("Escoga un Local ");
                mensaseListaElementos.setItems(locales,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Opción elegida: " + locales[item],
                                        Toast.LENGTH_SHORT).show();
                                helper.abrir();

                                localRepetido.setText(locales[item]);
                                idLocalRevision= helper.IDLocales()[item];
                                Toast.makeText(getApplicationContext(),idLocalRevision,Toast.LENGTH_SHORT).show();
                            }
                        });
                mensaseListaElementos.show();
            }
        });
    }

    public void actualizarRepetido(View view){

        String fecha= fechaRealizarRepetido.getText().toString();
        String hora = horaRepetido.getText().toString();
        String local= localRepetido.getText().toString();
        String observacion= observaciones.getText().toString();


        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "Seleccione un estado",Toast.LENGTH_LONG).show();
        }
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos",Toast.LENGTH_LONG).show();
            }
            else{
                String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
                Toast.makeText(this, opcion,Toast.LENGTH_SHORT).show();

                RepetidoTabla repetidoTabla = new RepetidoTabla();

                repetidoTabla.setESTADOREPETIDO(opcion);
                repetidoTabla.setFECHAREPETIDO(fecha);
                repetidoTabla.setHORAREPETIDO(hora);
                repetidoTabla.setLOCAL(local);
                repetidoTabla.setOBSERVACIONES(observacion);

                // Pasamos el id que viene del intent
                repetidoTabla.setIDREPETIDO(idRepetido);
                helper.abrir();
                String resultado= helper.actualizar(repetidoTabla);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

            }
        }
    }

}
