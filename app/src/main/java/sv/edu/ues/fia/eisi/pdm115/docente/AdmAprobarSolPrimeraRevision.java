package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmAprobarSolPrimeraRevision extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    TextView fechaRevison;
    TextView HoraRevison;
    TextView localRevison;
    TextView observaciones;
    String idPrimerRevision;
    String idLocalRevision;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    private static final String TAG = "AdmAprobarSolPrimeraRevision";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_primera_revision);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton)findViewById(R.id.estadoDenegado);
        estadoAprobado= (RadioButton) findViewById(R.id.estadoAprobado);
        fechaRevison= (TextView) findViewById(R.id.fechaAsignarPrimeraRevision);
        HoraRevison=(TextView) findViewById(R.id.horaAsignarPrimeraRevision);
        localRevison= (TextView) findViewById(R.id.localAsignarPrimeraRevision);
        observaciones=(TextView) findViewById(R.id.observacionesAsignarPrimeraRevision);
        Bundle bundle=getIntent().getExtras();
        idPrimerRevision= bundle.getString("id");
        Toast.makeText(this,idPrimerRevision,Toast.LENGTH_LONG).show();
        fechaRevison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmAprobarSolPrimeraRevision.this,
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
                fechaRevison.setText(date);
            }
        };


        localRevison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                final String [] locales= helper.obtenerLocales();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(AdmAprobarSolPrimeraRevision.this);
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

                                localRevison.setText(locales[item]);
                                idLocalRevision= helper.IDLocales()[item];
                                Toast.makeText(getApplicationContext(),idLocalRevision,Toast.LENGTH_SHORT).show();
                            }
                        });
                mensaseListaElementos.show();
            }
        });





    }
    public void actualizarPrimeraRevision(View view){

        String fecha= fechaRevison.getText().toString();
        String hora = HoraRevison.getText().toString();
        String local=localRevison.getText().toString();
        String observacion= observaciones.getText().toString();

        //ningun estado seleccionado
        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "seleccione un estado",Toast.LENGTH_LONG).show();
        }
        //estado negado seleccionado pero observacion vacio
        if(estadoDenegado.isChecked() &&observacion.isEmpty()){
            Toast.makeText(this, "DENEGADO, campo OBSERVACIONES no puede estar vacio",Toast.LENGTH_LONG).show();
        }
        //estado negado y observacion lleno
        if(estadoDenegado.isChecked() && !observacion.isEmpty()){
            String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
            Toast.makeText(this, opcion,Toast.LENGTH_LONG).show();
            PrimeraRevision primeraRevision= new PrimeraRevision();
            primeraRevision.setObservacionesPrimeraRevision(observacion);
            primeraRevision.setEstadoPrimeraRevision(opcion);

            //pasarle el id de primerrevision obtendio a  travez del intent
            primeraRevision.setIdPrimeraRevision(idPrimerRevision);
            helper.abrir();
            String resultado= helper.actualizarNegado(primeraRevision);
            helper.cerrar();
            Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
        }
        //opcion aprobado
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los capos",Toast.LENGTH_LONG).show();
            }
            else{
                 String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
            Toast.makeText(this, opcion,Toast.LENGTH_LONG).show();
            PrimeraRevision primeraRevision= new PrimeraRevision();

            primeraRevision.setEstadoPrimeraRevision(opcion);
            primeraRevision.setFechaPrimeraRevision(fecha);
            primeraRevision.setHoraPrimerarevision(hora);
            primeraRevision.setIdLocal(idLocalRevision);
            primeraRevision.setObservacionesPrimeraRevision(observacion);

            //pasarle el id de primerrevision obtendio a  travez del intent
            primeraRevision.setIdPrimeraRevision(idPrimerRevision);
            helper.abrir();
            String resultado= helper.actualizar(primeraRevision);
            helper.cerrar();
            Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
            }




        }


    }
}
