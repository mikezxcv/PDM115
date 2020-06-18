package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteConsultarSolicitudSegundaRevision extends AppCompatActivity {
    TextView edtCarnetRevision;
    TextView edtMateriaRevision;
    TextView edtEvaluacionRevision;
    TextView edtEstadoRevision;
    TextView edtFechaRevision;
    TextView edtHoraRevision;
    TextView edtLocalRevision;
    TextView edtNotaAntesRevision;
    TextView edtNotaDespuesRevision;
    TextView edtObsevacionesRevision;

    Button btnConsultar;

    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar_solicitud_segunda_revision);

        edtCarnetRevision = (TextView)findViewById(R.id.CarnetCRConsultaSegundaR);
        edtMateriaRevision = (TextView)findViewById(R.id.MateriaCRConsultaSegundaR);
        edtEvaluacionRevision = (TextView)findViewById(R.id.evaluacionnCRConsultaSegundaR);

        edtEstadoRevision = (TextView)findViewById(R.id.estadoCRConsultaSegundaR);
        edtFechaRevision = (TextView)findViewById(R.id.fechaRevisionCRConsultaSegundaR);
        edtHoraRevision = (TextView)findViewById(R.id.horaRevisionCRConsultaSegundaR);
        edtLocalRevision = (TextView)findViewById(R.id.localRevisionCRConsultaSegundaR);

        edtNotaDespuesRevision = (TextView)findViewById(R.id.notaDespuesCRConsultaSegundaR);
        edtObsevacionesRevision = (TextView)findViewById(R.id.observacionesCRConsultaSegundaR);

        btnConsultar = (Button) findViewById(R.id.consultarCRConsultaSegundaR);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarSolicitudesPR(v);
            }
        });

        edtMateriaRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMateriaRevision.setText(null);
                helper.abrir();
                final String [] materias= helper.obtenerMateriasCR();
                helper.cerrar();

                AlertDialog.Builder mensaseListaElementos =
                        new AlertDialog.Builder(EstudianteConsultarSolicitudSegundaRevision.this);
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
                                edtMateriaRevision.setText(materias[item]);

                            }
                        });
                mensaseListaElementos.show();
            }
        });

        edtEvaluacionRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carnetGET = edtCarnetRevision.getText().toString();
                String materiaGET = edtMateriaRevision.getText().toString();

                if(carnetGET.isEmpty() || materiaGET.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Carnet o Materia estan vacios",Toast.LENGTH_SHORT).show();
                }else{
                    helper.abrir();
                    final String [] evaluaciones= helper.obtenerEvaluacionesCR(carnetGET, materiaGET);
                    helper.cerrar();

                    AlertDialog.Builder mensaseListaElementos =
                            new AlertDialog.Builder(EstudianteConsultarSolicitudSegundaRevision.this);
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

                                    edtEvaluacionRevision.setText(evaluaciones[item]);
                                    //idMateriaIngresada= helper.idMateriasCR();
                                    //Toast.makeText(getApplicationContext(),idMateriaIngresada,Toast.LENGTH_SHORT).show();
                                }
                            });
                    mensaseListaElementos.show();
                }
            }
        });
    }

    public void ConsultarSolicitudesPR(View v){
        try{
            String carnet  = edtCarnetRevision.getText().toString();
            String materia = edtMateriaRevision.getText().toString();
            String evaluacion = edtEvaluacionRevision.getText().toString();

            String estadoR;
            String fechaR;
            String horaR;
            String localR;
            String notaAntesR;
            String notaDespuesR;
            String observR;

            if(carnet.isEmpty()||materia.isEmpty()||evaluacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
            }
            else{
                estadoR = helper.estadoSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);

                edtEstadoRevision.setText(estadoR);
                if(estadoR == null){
                    edtEstadoRevision.setText("---");
                }else{
                    edtEstadoRevision.setText(estadoR);
                }

                fechaR = helper.fechaSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);
                if(fechaR == null){
                    edtFechaRevision.setText("---");
                }else{
                    edtFechaRevision.setText(fechaR);
                }

                horaR = helper.horaSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);
                if(horaR == null){
                    edtHoraRevision.setText("---");
                }else{
                    edtHoraRevision.setText(horaR);
                }

                localR = helper.localSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);
                if(localR == null){
                    edtLocalRevision.setText("---");
                }else{
                    edtLocalRevision.setText(localR);
                }

                notaDespuesR = helper.notaDespuesSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);
                if(notaDespuesR == null){
                    edtNotaDespuesRevision.setText("---");
                }else{
                    edtNotaDespuesRevision.setText(notaDespuesR);
                }

                observR = helper.observacionSolicitudesSegundaRevisionCR(carnet, materia, evaluacion);
                if(observR == null){
                    edtObsevacionesRevision.setText("---");
                }else{
                    edtObsevacionesRevision.setText(observR);
                }

            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
