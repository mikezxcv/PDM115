package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SegundaRevision;

public class AdmDetallesolicitudSegundaRevision extends AppCompatActivity {
    Button btn;
    Button btn2;
    Button eliminar;
    EditText carnet;
    EditText nombre;
    EditText materia;
    EditText evaluacion;
    EditText fechaSolicitud;
    EditText fechaPrimeraRevision;
    String idsegundaRevision;
    AlertDialog dialogo;
    ControlBdGrupo12 helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detallesolicitud_segunda_revision);
        helper=new ControlBdGrupo12(this);
        btn = (Button) findViewById(R.id.irAprobarSolicitudSegundaRevision);
        btn2= (Button) findViewById(R.id.irDarRevisionSegundaRevision);
        eliminar=(Button) findViewById(R.id.eliminarSolicitud);
        carnet= (EditText)findViewById(R.id.carnetSolSegundaRevision);
        nombre= (EditText) findViewById(R.id.nombreSolSegundaRevision);
        materia= (EditText) findViewById(R.id.materiaSolSegundaRevision);
        evaluacion= (EditText) findViewById(R.id.evaluacionSolSegundaRevision);
        fechaSolicitud= (EditText)findViewById(R.id.fechasolicitudSolSegundaRevision);
        fechaPrimeraRevision=(EditText) findViewById(R.id.fechaprimeraRevisionsolicitudSolSegundaRevision);
        //ontener los datos de ADMPRIMERAREVISIONACTIVITY
        Bundle bundle= getIntent().getExtras();

        carnet.setText(bundle.getString("carnet"));
        nombre.setText(bundle.getString("nombre"));
        materia.setText(bundle.getString("materia"));
        evaluacion.setText(bundle.getString("evaluacion"));
        fechaSolicitud.setText(bundle.getString("fechaSolicitud"));
        fechaPrimeraRevision.setText(bundle.getString("fechaPrimeraRevision"));

        idsegundaRevision= bundle.getString("id");

        //desabilitar-habilitar botones
        helper.abrir();
        List<String> datos= helper.verificarSolicitudSegundaRevision(idsegundaRevision);
        // String [] datos2= helper.docentes_segundarevision(Integer.valueOf(idsegundaRevision));

        helper.cerrar();
        if(!TextUtils.isEmpty(datos.get(0))){
            //solicitud aceptada
            btn2.setVisibility(View.VISIBLE);
            eliminar.setVisibility(View.VISIBLE);
            Toast.makeText(this, datos.get(0), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, datos.get(0), Toast.LENGTH_SHORT).show();
            btn2.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }


        //aprobar segunda revision
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudSegundaRevision.this, AdmAprobarSolSegundaRevision.class);
                intent.putExtra("id",idsegundaRevision);
                startActivity(intent);
                //desabilitar botones
                helper.abrir();
                String [] datos= helper.docentes_segundarevision(Integer.valueOf(idsegundaRevision));
                helper.cerrar();
                if(datos.length!=0){
                    btn2.setVisibility(View.VISIBLE);
                    eliminar.setVisibility(View.VISIBLE);
                }
            }
        });
        //calificar segunda revision
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1= getIntent().getExtras();
                String nota= bundle1.getString("notaInicial");
                String notaPrimeraRevision= bundle1.getString("notaPrimeraRevision");

                Intent intent = new Intent(AdmDetallesolicitudSegundaRevision.this, AdmDarRevisionSegundaRevision.class);
                intent.putExtra("id",idsegundaRevision);
                intent.putExtra("notaInicial",nota);
                intent.putExtra("notaPrimeraRevision",notaPrimeraRevision);
                startActivity(intent);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crear el dialog con las opciones y mostrarlo al clickerar boton eliminar
                crearDialog();
                dialogo.show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //desabilitar-habilitar botones
        helper.abrir();
        List<String> datos= helper.verificarSolicitudSegundaRevision(idsegundaRevision);
        // String [] datos2= helper.docentes_segundarevision(Integer.valueOf(idsegundaRevision));

        helper.cerrar();
        if(!TextUtils.isEmpty(datos.get(0))){
            //solicitud aceptada
            btn2.setVisibility(View.VISIBLE);
            eliminar.setVisibility(View.VISIBLE);
        }else{
            btn2.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }

    }

    public void crearDialog(){
        dialogo = new AlertDialog
                .Builder(AdmDetallesolicitudSegundaRevision.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String idSegundaRev = idsegundaRevision;
                        SegundaRevision segundaRevision=new SegundaRevision();
                        segundaRevision.setIdSegundaRevision(idSegundaRev);
                        helper.abrir();
                        String resultado= helper.eliminar(segundaRevision);
                        helper.cerrar();
                        Intent intent= new Intent(AdmDetallesolicitudSegundaRevision.this,AdmSegundaRevisionActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

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
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar esta Solicitud de Segunda Revision?") // El mensaje
                .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!

    }
}
