package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetalleSolicitudImpresionActivity extends AppCompatActivity {
    Impresion impresion;
    Context contexto = AdmDetalleSolicitudImpresionActivity.this;
    EditText editDocente, editExamenes, editHojas,editAprobacion, editDetalles,
            editEstadoImp, editMotivo, observaciones;
    TextView razonTitulo;
    TableRow tr1, tr2, tr3;
    LinearLayout li1, li2, li3;
    Button cancelar, guardar, eliminar, actualizar;
    final String[] estadoAprobacion = {"Pendiente", "Aprobada", "Rechazada"};
    final String[] estadoImpresion = {"Pendiente", "Aprobada", "No se realizó"};
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detalle_solicitud_impresion);
        this.setTitle("Detalles de la solicitud");

        tr1 = findViewById(R.id.tableRow1);
        li1 = findViewById(R.id.linearLayout1);
        tr2 = findViewById(R.id.tableRow2);
        li2 = findViewById(R.id.linearLayout2);
        tr3 = findViewById(R.id.tableRow3);
        li3 = findViewById(R.id.linearLayout3);
        razonTitulo = findViewById(R.id.tvRazonTitulo);
        editDocente = findViewById(R.id.editDocente);
        editExamenes = findViewById(R.id.cantidadExamenesSol);
        editHojas = findViewById(R.id.cantidadHojasEmpaqueSol);
        editDetalles = findViewById(R.id.detallesExtraSol);
        editAprobacion = findViewById(R.id.estadoAprobacionSol);
        editEstadoImp = findViewById(R.id.estadoImpresionSol);
        editMotivo = findViewById(R.id.editMotivoSol);
        observaciones = findViewById(R.id.detallesRechazoSol);
        cancelar = findViewById(R.id.cancelarmod);
        guardar = findViewById(R.id.guardarSol);
        eliminar = findViewById(R.id.eliminarSolicitud);
        actualizar = findViewById(R.id.actualizarSolicitud);

        int idSolicitud = getIntent().getIntExtra("idImpresion",0);
        String usuario = getIntent().getStringExtra("usuarioActual");
        helper.abrir();
        impresion = helper.getSolicitudImpresion(String.valueOf(idSolicitud));
        editMotivo.setText(helper.getMotivo(impresion.getIdMotivoNoImp()));
        if (usuario.equals("ADMIN")){
            Docente docente = helper.getDocenteAdmin(impresion.getIdDocente());
            String edDocente = docente.getNombreDocente()+" "+docente.getApellidoDocente();
            editDocente.setText(edDocente);
        }else{
            tr1.setVisibility(View.GONE);
            li1.setVisibility(View.GONE);
        }

        helper.cerrar();

        observaciones.setText(impresion.getDescripcionNoImp());
        editExamenes.setText(String.valueOf(impresion.getCantidadExamenes()));
        editHojas.setText(String.valueOf(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);

        if(impresion.getEstadoImpresion()!=2||impresion.getEstadoAprobacion()!=2){
            tr2.setVisibility(View.GONE);
            li2.setVisibility(View.GONE);
            tr3.setVisibility(View.GONE);
            li3.setVisibility(View.GONE);
            razonTitulo.setVisibility(View.GONE);
        }

    }

    public void actualizar(View view) {
        editExamenes.setEnabled(true);
        editHojas.setEnabled(true);
        editDetalles.setEnabled(true);
        guardar.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.GONE);
        actualizar.setVisibility(View.GONE);

    }

    public void eliminarSol(View view) {
        AlertDialog.Builder seleccion  =
                new AlertDialog.Builder(contexto);
        seleccion.setTitle("Seleccione un motivo:");
        seleccion.setMessage("¿Deseas eliminar esta Solicitud de Impresion?");
        seleccion.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                helper.abrir();
                String r2 = helper.eliminarSolicitudImpresion(
                        String.valueOf(impresion.getIdSolicitudImpresion()));
                helper.cerrar();
                Toast.makeText(contexto, r2, Toast.LENGTH_SHORT).show();
                if (!r2.equals("Error")) finish();
            }
        });
        seleccion.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        seleccion.show();
    }

    public void cancelarModificacion(View view) {
        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        eliminar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.VISIBLE);

        editExamenes.setText(String.valueOf(impresion.getCantidadExamenes()));
        editHojas.setText(String.valueOf(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        editExamenes.setEnabled(false);
        editHojas.setEnabled(false);
        editDetalles.setEnabled(false);

    }

    public void guardarSolDocente(View view) {
        impresion.setCantidadExamenes(Integer.parseInt(editExamenes.getText().toString()));
        impresion.setHojasEmpaque(Integer.parseInt(editHojas.getText().toString()));
        impresion.setDescripcionSolicitud(editDetalles.getText().toString());
        helper.abrir();
        String resultado = helper.actualizarSolicitudImpresion(impresion);
        helper.cerrar();
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
        editExamenes.setEnabled(false);
        editHojas.setEnabled(false);
        editDetalles.setEnabled(false);

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        eliminar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.VISIBLE);
        recreate();

    }
}
