package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
public class EstudianteConsultarSolicitudPrimeraRevisionActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_estudiante_consultar_solicitud_primera_revision);

        edtCarnetRevision = (TextView)findViewById(R.id.CarnetCR);
        edtMateriaRevision = (TextView)findViewById(R.id.MateriaCR);
        edtEvaluacionRevision = (TextView)findViewById(R.id.evaluacionnCR);

        edtEstadoRevision = (TextView)findViewById(R.id.estadoCR);
        edtFechaRevision = (TextView)findViewById(R.id.fechaRevisionCR);
        edtHoraRevision = (TextView)findViewById(R.id.horaRevisionCR);
        edtLocalRevision = (TextView)findViewById(R.id.localRevisionCR);
        edtNotaAntesRevision = (TextView)findViewById(R.id.notaAntesCR);
        edtNotaDespuesRevision = (TextView)findViewById(R.id.notaDespuesCR);
        edtObsevacionesRevision = (TextView)findViewById(R.id.observacionesCR);

        btnConsultar = (Button) findViewById(R.id.consultarCR);

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
                        new AlertDialog.Builder(EstudianteConsultarSolicitudPrimeraRevisionActivity.this);
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
                            new AlertDialog.Builder(EstudianteConsultarSolicitudPrimeraRevisionActivity.this);
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
                estadoR = helper.estadoSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);

                edtEstadoRevision.setText(estadoR);
                if(estadoR == null){
                    edtEstadoRevision.setText("---");
                }else{
                    edtEstadoRevision.setText(estadoR);
                }

                fechaR = helper.fechaSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
                if(fechaR == null){
                    edtFechaRevision.setText("---");
                }else{
                    edtFechaRevision.setText(fechaR);
                }

                horaR = helper.horaSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
                if(horaR == null){
                    edtHoraRevision.setText("---");
                }else{
                    edtHoraRevision.setText(horaR);
                }

                localR = helper.localSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
                if(localR == null){
                    edtLocalRevision.setText("---");
                }else{
                    edtLocalRevision.setText(localR);
                }

                notaAntesR = helper.notaAntesSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
                if(notaAntesR == null){
                    edtNotaAntesRevision.setText("---");
                }else{
                    edtNotaAntesRevision.setText(notaAntesR);
                }

                notaDespuesR = helper.notaDespuesSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
                if(notaDespuesR == null){
                    edtNotaDespuesRevision.setText("---");
                }else{
                    edtNotaDespuesRevision.setText(notaDespuesR);
                }

                observR = helper.observacionesSolicitudesPrimeraRevisionCR(carnet, materia, evaluacion);
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
