package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetalleEvaluacionActivity extends AppCompatActivity {
    public String CarnetEVA;
    public String MateriaEVA;
    public String EvaluacionEVA;
    public String NotaEVA;
    public String AsistenciaEVA;
    public String FechaRealizacionDePruebaEVA;
    public String FechaLimiteParaRevisionEVA;
    public String DocenteEncargadoEVA;

    Button btnActualizar;
    Button btnEliminar;

    EditText edtCarnet;
    EditText edtMateria;
    EditText edtEvaluacion;
    EditText edtNota;
    EditText edtAsistencia;
    EditText edtFechaRealizacionDePrueba;
    EditText edtFechaLimiteParaRevision;
    EditText edtDocenteEncargado;
    ControlBdGrupo12 helper = new ControlBdGrupo12(AdmDetalleEvaluacionActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detalle_evaluacion);

        edtCarnet = (EditText)findViewById(R.id.editCarnetEvaluacion);
        edtMateria = (EditText)findViewById(R.id.editMateriaEvaluacion);
        edtEvaluacion = (EditText)findViewById(R.id.editEvaluacionEvaluacion);
        edtNota = (EditText)findViewById(R.id.editNotaEvaluacion);
        edtAsistencia = (EditText)findViewById(R.id.editAsistenciaEvaluacion);
        edtFechaRealizacionDePrueba = (EditText)findViewById(R.id.editFechaRealizacionEvaluacion);
        edtFechaLimiteParaRevision = (EditText)findViewById(R.id.editFechaLimRevisionEvaluacion);
        edtDocenteEncargado = (EditText)findViewById(R.id.editDoocenteEvaluacion);

        llenar();

        btnActualizar = (Button)findViewById(R.id.irActualizarEvaluacion);
        btnEliminar = (Button)findViewById(R.id.irEliminarEvaluacion);

    }

    public void llenar() {
        String Carnet = edtCarnet.getText().toString();
        String Materia = edtCarnet.getText().toString();
        String Evaluacion = edtCarnet.getText().toString();
        String Nota = edtCarnet.getText().toString();
        String Asistencia = edtCarnet.getText().toString();
        String FechaRealizacionDePrueba = edtCarnet.getText().toString();
        String FechaLimiteParaRevision = edtCarnet.getText().toString();
        String DocenteEncargado = edtCarnet.getText().toString();

        Bundle bundle=getIntent().getExtras();
        String idEvaluacion= bundle.getString("id");

        try {
            helper.abrir();
            CarnetEVA = helper.carnetDetalleEvaluacionesTODO(idEvaluacion);
            MateriaEVA = helper.materiaDetalleEvaluacionesTODO(idEvaluacion);
            EvaluacionEVA = helper.evaluacionDetalleEvaluacionesTODO(idEvaluacion);
            NotaEVA = helper.notaDetalleEvaluacionesTODO(idEvaluacion);
            AsistenciaEVA = helper.asistenciaDetalleEvaluacionesTODO(idEvaluacion);
            FechaRealizacionDePruebaEVA = helper.fechaPublicacionDetalleEvaluacionesTODO(idEvaluacion);
            FechaLimiteParaRevisionEVA = helper.fechaLimiteDetalleEvaluacionesTODO (idEvaluacion);
            DocenteEncargadoEVA = helper.docenteDetalleEvaluacionesTODO(idEvaluacion);
            helper.cerrar();

            edtCarnet.setText(CarnetEVA);
            edtMateria.setText(MateriaEVA);
            edtEvaluacion.setText(EvaluacionEVA);
            edtNota.setText(NotaEVA);
            if(AsistenciaEVA.equals("1")){
                edtAsistencia.setText("Asistio");
            }else{
                edtAsistencia.setText("No Asistio");
            }

            edtFechaRealizacionDePrueba.setText(FechaRealizacionDePruebaEVA);
            edtFechaLimiteParaRevision.setText(FechaLimiteParaRevisionEVA);
            helper.abrir();
            String nombreDocente = helper.nombredocenteDetalleEvaluacionesTODO(DocenteEncargadoEVA);
            helper.cerrar();
            edtDocenteEncargado.setText(nombreDocente);
        }catch (Exception e){
            Toast.makeText(AdmDetalleEvaluacionActivity.this, "Algun Campo es null", Toast.LENGTH_SHORT).show();
        }
    }
}
