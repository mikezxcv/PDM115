package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.estudiante.EstudianteConsultarSolicitudPrimeraRevisionActivity;

public class AdmListaEvaluacionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper;
    ListView listView;

    String [] datos;
    String [] idEvaluacion;
    FloatingActionButton btnCrear;

    public String fechaL;

    String idLocalRevision;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "AdmAprobarsolicitudDiferido";
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_lista_evaluacion);
        listView= (ListView) findViewById(R.id.listViewEvaluaciones);
        btnCrear= (FloatingActionButton) findViewById(R.id.botonEmergenteEvaluaciones);
        helper= new ControlBdGrupo12(this);
        helper.abrir();
        datos= helper.obtenerDetalleEvaluaciones();
        idEvaluacion=helper.IDDetalleEvaluaciones();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent= new Intent(AdmListaEvaluacionActivity.this,AdmDetalleEvaluacionActivity.class);
                intent.putExtra("id",idEvaluacion[position]);
                Toast.makeText(AdmListaEvaluacionActivity.this, idEvaluacion[position], Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        /// CREAR
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_SHORT).show();

                //implementat alert
                AlertDialog.Builder builder= new AlertDialog.Builder(AdmListaEvaluacionActivity.this);
                View viewInflated= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_crear_detalle_evaluacion,null);
                builder.setView(viewInflated);

                final EditText carnet=(EditText) viewInflated.findViewById(R.id.editCarnetEvaluacionCrear);
                final EditText materia=(EditText) viewInflated.findViewById(R.id.editMateriaEvaluacionCrear);
                final EditText evaluacion=(EditText) viewInflated.findViewById(R.id.editEvaluacionEvaluacionCrear);
                final EditText nota=(EditText) viewInflated.findViewById(R.id.editNotaEvaluacionCrear);
                final EditText asistencia=(EditText) viewInflated.findViewById(R.id.editAsistenciaEvaluacionCrear);
                final EditText fechaRealizacion=(EditText) viewInflated.findViewById(R.id.editFechaRealizacionEvaluacionCrear);
                final EditText fechaLim=(EditText) viewInflated.findViewById(R.id.editFechaLimRevisionEvaluacionCrear);
                final EditText docEncargado=(EditText) viewInflated.findViewById(R.id.editDoocenteEvaluacionCrear);
                final EditText localDiferido =(EditText) viewInflated.findViewById(R.id.editLocalEvaluacionCrear);

                docEncargado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String materiaE = materia.getText().toString();
                        if(materiaE.isEmpty()){
                            Toast.makeText(getApplicationContext(),"MATERIA Vacia",Toast.LENGTH_SHORT).show();
                        }else{
                            helper.abrir();
                            final String [] evaluaciones= helper.obtenerDocentesParaDetalle(materiaE);
                            helper.cerrar();

                            android.app.AlertDialog.Builder mensaseListaElementos =
                                    new android.app.AlertDialog.Builder(AdmListaEvaluacionActivity.this);
                            mensaseListaElementos.setTitle("Escoga una Docente encargado ");
                            mensaseListaElementos.setItems(evaluaciones,
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int item)
                                        {
                                            Toast.makeText(getApplicationContext(),
                                                    "Opción elegida: " + evaluaciones[item],
                                                    Toast.LENGTH_SHORT).show();
                                            helper.abrir();

                                            docEncargado.setText(evaluaciones[item]);
                                        }
                                    });
                            mensaseListaElementos.show();
                        }
                    }
                });

                materia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        evaluacion.setText(null);
                        docEncargado.setText(null);
                        helper.abrir();
                        final String [] locales= helper.obtenerMateriasCR();
                        helper.cerrar();

                        android.app.AlertDialog.Builder mensaseListaElementos =
                                new android.app.AlertDialog.Builder(AdmListaEvaluacionActivity.this);
                        mensaseListaElementos.setTitle("Escoga una materia ");
                        mensaseListaElementos.setItems(locales,
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int item)
                                    {
                                        Toast.makeText(getApplicationContext(),
                                                "Opción elegida: " + locales[item],
                                                Toast.LENGTH_SHORT).show();
                                        helper.abrir();

                                        materia.setText(locales[item]);

                                    }
                                });
                        mensaseListaElementos.show();
                    }
                });

                evaluacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String carnetGET = carnet.getText().toString();
                        String materiaGET = materia.getText().toString();

                        if(materiaGET.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Materia esta vacios",Toast.LENGTH_SHORT).show();
                        }else{
                            helper.abrir();
                            final String [] evaluaciones= helper.obtenerEvaluacioneParaDetalle(materiaGET);
                            helper.cerrar();

                            android.app.AlertDialog.Builder mensaseListaElementos =
                                    new android.app.AlertDialog.Builder(AdmListaEvaluacionActivity.this);
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

                                            evaluacion.setText(evaluaciones[item]);
                                            //idMateriaIngresada= helper.idMateriasCR();
                                            //Toast.makeText(getApplicationContext(),idMateriaIngresada,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            mensaseListaElementos.show();
                        }
                    }
                });

                asistencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String [] evaluaciones= {"SI, Asistio", "NO, Asistio"};

                        android.app.AlertDialog.Builder mensaseListaElementos =
                                new android.app.AlertDialog.Builder(AdmListaEvaluacionActivity.this);
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

                                        asistencia.setText(evaluaciones[item]);
                                    }
                                });
                        mensaseListaElementos.show();

                    }
                });

                fechaRealizacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar cal=Calendar.getInstance();
                        int year=cal.get(Calendar.YEAR);
                        int month=  cal.get(Calendar.MONTH);
                        int day= cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialogo = new DatePickerDialog(
                                AdmListaEvaluacionActivity.this,
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
                        fechaRealizacion.setText(date);
                    }
                };

                localDiferido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.abrir();
                        final String [] locales= helper.obtenerLocales();
                        helper.cerrar();

                        android.app.AlertDialog.Builder mensaseListaElementos =
                                new android.app.AlertDialog.Builder(AdmListaEvaluacionActivity.this);
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



                builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String carnetEva = carnet.getText().toString();
                        String materiaEva = materia.getText().toString();
                        String evaluacionEva = evaluacion.getText().toString();
                        String notaEva = nota.getText().toString();
                        String asistenciaEva = asistencia.getText().toString();
                        String localEva = localDiferido.getText().toString();
                        String docEncargadoEva = docEncargado.getText().toString();

                        helper.abrir();
                        String idDocenteEncargado = helper.obtenerIDDocentesParaDetalle(materiaEva, docEncargadoEva);
                        helper.cerrar();

                        if(asistenciaEva.equals("SI, Asistio")){
                            asistenciaEva = "1";
                        }else{
                            asistenciaEva = "0";
                        }

                        String fechaRealizacionEva = fechaRealizacion.getText().toString();
                        if(fechaRealizacionEva.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Fecha de Realizacion Vacia",Toast.LENGTH_SHORT).show();
                        }else{
                            Calendar calendar1 = Calendar.getInstance();
                            Calendar calendar2 = Calendar.getInstance();
                            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date fechaLimite = formateador.parse(fechaRealizacionEva);
                                calendar1.setTime(fechaLimite);
                                calendar1.add(Calendar.DAY_OF_MONTH, 3);

                                Date fechaLim = calendar1.getTime();
                                fechaL = formateador.format(fechaLim);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }

                        String fechaLimEva = fechaL;

                        if(carnetEva.isEmpty() || materiaEva.isEmpty() || evaluacionEva.isEmpty() || fechaRealizacionEva.isEmpty() || docEncargado.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"CARNET, ,MATERIA, EVALUAICON O FECHAREALIZACION Estan Vacios",Toast.LENGTH_SHORT).show();
                        }else{
                            helper.abrir();
                            String resultado= helper.insertarDetalleEvaluacion(carnetEva, materiaEva, evaluacionEva, notaEva, asistenciaEva, fechaRealizacionEva, fechaLimEva, idDocenteEncargado,  localEva );
                            //String resultado= helper.insertarDetalleEvaluacion(carnetEva, materiaEva, evaluacionEva, notaEva, asistenciaEva, fechaRealizacionEva, fechaLimEva, docEncargadoEva );
                            helper.cerrar();
                            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();
                        }

                        //recargar el activity para mostrar los datos
                        recreate();

                    }
                });
                builder.setTitle("Crear Nuevo Detalle Evaluacion");
                AlertDialog dialog= builder.create();
                dialog.show();

            }
        });


    }
    @Override
    protected void onResume(){
        super.onResume();
        helper.abrir();
        datos= helper.obtenerDetalleEvaluaciones();
        idEvaluacion=helper.IDDetalleEvaluaciones();
        helper.cerrar();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        //carga el listview con los datos
    }
}
