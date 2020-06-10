package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SegundaRevision;

public class AdmAprobarSolSegundaRevision extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    Button asignarDocentes;
    Button verDocentes;
    TextView fechaRevison;
    TextView HoraRevison;
    TextView localRevison;
    TextView observaciones;
    String idSegundaRevicion;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";
    public String[] listaElementos;
    public String[] listaIdElementos;
    public String[] docentes_segundarevision;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_segunda_revision);
        asignarDocentes= (Button) findViewById(R.id.asignarDocentes);
        verDocentes=(Button) findViewById(R.id.verDocentesAsignados);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton) findViewById(R.id.denegado);
        estadoAprobado= (RadioButton) findViewById(R.id.aprobado);
        fechaRevison= (EditText)findViewById(R.id.fechaAsignarSegundaRevision);
        HoraRevison= (EditText)findViewById(R.id.horaSegundaRevision);
        localRevison= (EditText)findViewById(R.id.localAsignarSegundaRevision);
        observaciones= (EditText)findViewById(R.id.observacionesAsignarSegundaRevision);
        Bundle bundle=getIntent().getExtras();
        idSegundaRevicion = bundle.getString("id");

        helper.abrir();
        listaElementos= new String[helper.listaDocentes().length];
        listaElementos= helper.listaDocentes();
        listaIdElementos= new String[helper.listaDocentes().length];
        listaIdElementos=helper.listaIdDocentes();

        helper.cerrar();


        asignarDocentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evaluar si el elemento ya existe
                helper.abrir();
                String [] datos= helper.docentes_segundarevision(Integer.valueOf(idSegundaRevicion));
                helper.cerrar();
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
    }
    public void actualizarSegundaRevision(View view){

        String fecha= fechaRevison.getText().toString();
        String hora = HoraRevison.getText().toString();
        String local=localRevison.getText().toString();
        String observacion= observaciones.getText().toString();

        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "seleccione un estado",Toast.LENGTH_LONG).show();
        }
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
                segundaRevision.setLocalSegundaRevision(local);
                segundaRevision.setObservacionesSegundaRevision(observacion);

                //pasarle el id de primerrevision obtendio a  travez del intent
                segundaRevision.setIdSegundaRevision(idSegundaRevicion);
                helper.abrir();
                String resultado= helper.actualizar(segundaRevision);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
            }


        }

    }


}
