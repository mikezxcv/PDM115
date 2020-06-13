package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SolicitudDiferidoTabla;

public class AdmAprobarsolicitudDiferido extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    TextView fechaDiferido;
    TextView HoraDiferido;
    TextView localDiferido;
    TextView observaciones;
    String idDiferido;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";

    Button guardar;
    String idLocalRevision;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    String date;

    String time;
    private static final String TAG = "AdmAprobarsolicitudDiferido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobarsolicitud_diferido);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton)findViewById(R.id.radioRechazadoAprobar);
        estadoAprobado= (RadioButton) findViewById(R.id.radioAprobadoAprobar);
        fechaDiferido= (TextView) findViewById(R.id.editfechaDiferidoAprobar);
        HoraDiferido=(TextView) findViewById(R.id.editHoraAprobar);
        localDiferido= (TextView) findViewById(R.id.editLocalAprobar);
        observaciones=(TextView) findViewById(R.id.editObservacionesAprobar);
        guardar = (Button)findViewById(R.id.guardarDiferidoAprobar);

        Bundle bundle=getIntent().getExtras();
        idDiferido= bundle.getString("idDiferido");
        Toast.makeText(this,idDiferido,Toast.LENGTH_LONG).show();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDiferido(v);
            }
        });

        fechaDiferido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmAprobarsolicitudDiferido.this,
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
                fechaDiferido.setText(date);
            }
        };

        localDiferido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                final String [] locales= helper.obtenerLocales();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(AdmAprobarsolicitudDiferido.this);
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

                                localDiferido.setText(locales[item]);
                                idLocalRevision= helper.IDLocales()[item];
                                Toast.makeText(getApplicationContext(),idLocalRevision,Toast.LENGTH_SHORT).show();
                            }
                        });
                mensaseListaElementos.show();
            }
        });
    }

    public void actualizarDiferido(View view){

        String fecha= fechaDiferido.getText().toString();
        String hora = HoraDiferido.getText().toString();
        String local=localDiferido.getText().toString();
        String observacion= observaciones.getText().toString();


        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "Seleccione un estado",Toast.LENGTH_SHORT).show();

        }
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos",Toast.LENGTH_SHORT).show();

            }
            else{
                String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
                Toast.makeText(this, opcion,Toast.LENGTH_SHORT).show();

                SolicitudDiferidoTabla solicitudDiferido = new SolicitudDiferidoTabla();

                solicitudDiferido.setESTADODIFERIDO(opcion);
                solicitudDiferido.setFECHADIFERIDO(fecha);
                solicitudDiferido.setHORADIFERIDO(hora);
                solicitudDiferido.setLOCALDIFERIDO(local);
                solicitudDiferido.setOBSERVACIONESDIFERIDO(observacion);

                //pasarle el id de primerrevision obtendio a  travez del intent
                solicitudDiferido.setIDDIFERIDO(idDiferido);
                helper.abrir();
                String resultado= helper.actualizar(solicitudDiferido);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();


            }
        }
    }
}
