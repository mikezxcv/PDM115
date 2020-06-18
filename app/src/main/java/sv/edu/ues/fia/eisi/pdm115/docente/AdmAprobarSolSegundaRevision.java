package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SegundaRevision;

public class AdmAprobarSolSegundaRevision extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    Button asignarDocentes;
    Button verDocentes;
    TextView fechaRevison;
    Button guardar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    TextView HoraRevison;
    TextView localRevison;
    String idLocalRevision;
    TextView observaciones;
    String idSegundaRevicion;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";
    public String[] listaElementos;
    public String[] listaIdElementos;
    public String[] docentes_segundarevision;
    String date;

    private static final String TAG = "AdmAprobarSolSegundaRevision";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_segunda_revision);
        asignarDocentes= (Button) findViewById(R.id.asignarDocentes);
        verDocentes=(Button) findViewById(R.id.verDocentesAsignados);
        guardar= (Button) findViewById(R.id.irAorobarSolicitudSegundaRevision);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton) findViewById(R.id.denegado);
        estadoAprobado= (RadioButton) findViewById(R.id.aprobado);
        fechaRevison= (TextView) findViewById(R.id.fechaAsignarSegundaRevision);
        HoraRevison= (EditText)findViewById(R.id.horaSegundaRevision);
        localRevison= (TextView) findViewById(R.id.localAsignarSegundaRevision);
        observaciones= (EditText)findViewById(R.id.observacionesAsignarSegundaRevision);
        Bundle bundle=getIntent().getExtras();
        idSegundaRevicion = bundle.getString("id");

        helper.abrir();
        listaElementos= new String[helper.listaDocentes().length];
        listaElementos= helper.listaDocentes();
        listaIdElementos= new String[helper.listaDocentes().length];
        listaIdElementos=helper.listaIdDocentes();

        //habilitar-desabilitar botones
        List<String> datos1=helper.verificarSolicitudSegundaRevision(idSegundaRevicion);
        String [] datos= helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion));
        helper.cerrar();
        if(!TextUtils.isEmpty(datos1.get(0)) && datos.length==0){
            //solicitud aceptada sin docentes asignados
            guardar.setVisibility(View.INVISIBLE);
            asignarDocentes.setVisibility(View.VISIBLE);
            verDocentes.setVisibility(View.INVISIBLE);
        }
        if(!TextUtils.isEmpty(datos1.get(0)) && datos.length!=0){
            //solicitud aceptada y docentes asignados
            guardar.setVisibility(View.INVISIBLE);
            asignarDocentes.setVisibility(View.VISIBLE);
            verDocentes.setVisibility(View.VISIBLE);
        }

        if(TextUtils.isEmpty(datos1.get(0))){
            //solicitud no aceptada
            guardar.setVisibility(View.VISIBLE);
            asignarDocentes.setVisibility(View.INVISIBLE);
            verDocentes.setVisibility(View.INVISIBLE);
        }




        asignarDocentes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //evaluar si el elemento ya existe
                helper.abrir();
                String [] datos= helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion));
                helper.cerrar();
                //desabilitar boton guardar
                guardar.setVisibility(View.INVISIBLE);
                for(int j=0;j<Integer.valueOf(listaElementos.length);j++){
                    for(int i=0;i< Integer.valueOf(datos.length);i++) {
                        if (listaElementos[j].contentEquals(datos[i])) {
                            //se encontro coincidencia, asignar ese item a null
                            listaElementos[j] = "";

                        }
                    }
                }


                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(AdmAprobarSolSegundaRevision.this);
                mensaseListaElementos.setTitle("Seleccione Elemento");
                mensaseListaElementos.setItems(listaElementos,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {



                                if(listaElementos[item]==""){
                                    Toast.makeText(getApplicationContext(),"Por favor seleccione un DOCENTE de la lista",Toast.LENGTH_LONG).show();
                                }else{

                                          //ejecutar metodo normal
                                          Toast.makeText(getApplicationContext(),
                                                  "Opción elegida: " + listaElementos[item],

                                                  Toast.LENGTH_SHORT).show();


                                          //implementacion delmetodo insertar en docente-segundarevision
                                          String idDocente= listaIdElementos[item];
                                          String idSegundaRev= idSegundaRevicion;
                                          Docente docente= new Docente();
                                          docente.setIdDocente(idDocente);
                                          docente.setIdSegundaRevision(idSegundaRev);
                                          helper.abrir();
                                          String resultado= helper.insertar(docente);
                                          helper.cerrar();
                                          Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
                                          listaElementos[item]= "";

                                    helper.abrir();
                                    String [] data= helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion));
                                    helper.cerrar();
                                    if(data.length!=0){
                                        verDocentes.setVisibility(View.VISIBLE);
                                    }

                                }
                            }
                        });

                mensaseListaElementos.show();


            }

        });

        verDocentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                docentes_segundarevision= new String[helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion)).length];
                docentes_segundarevision= helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion));
                helper.cerrar();
                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(AdmAprobarSolSegundaRevision.this);
                mensaseListaElementos.setTitle("Lista de  Docentes ASIGNADOS a esta solicitud");
                mensaseListaElementos.setItems(docentes_segundarevision,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {
                                Toast.makeText(getApplicationContext(),
                                        "DOCENTES ASIGNADOS",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                mensaseListaElementos.show();

            }
        });


        fechaRevison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=  cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(
                        AdmAprobarSolSegundaRevision.this,
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
                        new AlertDialog.Builder(AdmAprobarSolSegundaRevision.this);
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



    public void actualizarSegundaRevision(View view){

        String fecha= fechaRevison.getText().toString();
        String hora = HoraRevison.getText().toString();
        String local=localRevison.getText().toString();
        String observacion= observaciones.getText().toString();

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
            SegundaRevision segundaRevision= new SegundaRevision();
            segundaRevision.setObservacionesSegundaRevision(observacion);
            segundaRevision.setEstadoSegundaRevision(opcion);
            //pasarle el id de primerrevision obtendio a  travez del intent
            segundaRevision.setIdSegundaRevision(idSegundaRevicion);
            helper.abrir();
            String resultado= helper.actualizarNegado(segundaRevision);
            helper.cerrar();
            Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
        }
        //aprobado
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los capos",Toast.LENGTH_LONG).show();
            }
            else{
                String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
                Toast.makeText(this, opcion,Toast.LENGTH_LONG).show();
                SegundaRevision segundaRevision= new SegundaRevision();

                segundaRevision.setEstadoSegundaRevision(opcion);
                segundaRevision.setFechaSegundaRevision(fecha);
                segundaRevision.setHoraSegundaRevision(hora);
                segundaRevision.setLocalSegundaRevision(idLocalRevision);
                segundaRevision.setObservacionesSegundaRevision(observacion);

                //pasarle el id de primerrevision obtendio a  travez del intent
                segundaRevision.setIdSegundaRevision(idSegundaRevicion);

                helper.abrir();
                String resultado= helper.actualizar(segundaRevision);
                helper.cerrar();
                Toast.makeText(this, resultado,Toast.LENGTH_LONG).show();
                if(estadoAprobado.isChecked()){
                    asignarDocentes.setVisibility(View.VISIBLE);
                }
            }


        }

    }


}
