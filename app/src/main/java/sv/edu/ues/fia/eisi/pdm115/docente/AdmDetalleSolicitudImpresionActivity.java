package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetalleSolicitudImpresionActivity extends AppCompatActivity {
    Impresion impresion;
    EditText editExamenes, editHojas,editAprobacion, editDetalles,
            editEstadoImp, editMotivo, observaciones;
    Button cancelar, guardar, eliminar, actualizar;
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detalle_solicitud_impresion);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("impresion");
        impresion = gson.fromJson(strObj, Impresion.class);
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
        editExamenes.setText(Integer.toString(impresion.getCantidadExamenes()));
        editHojas.setText(Integer.toString(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        switch (impresion.getEstadoAprobacion()){
            case 0: editAprobacion.setText("Pendiente");
            case 1: editAprobacion.setText("Aprobada");
            case 2: editAprobacion.setText("Rechazada");
        }
        switch (impresion.getEstadoAprobacion()){
            case 0: editEstadoImp.setText("Pendiente");
            case 1: editEstadoImp.setText("Realizada");
            case 2: editEstadoImp.setText("No se realizó");
        }
        helper.abrir();
        editMotivo.setText(helper.getMotivo(impresion.getIdMotivoNoImp()));
        observaciones.setText(impresion.getDescripcionNoImp());

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
        helper.abrir();
        String r2 = helper.eliminarSolicitudImpresion(
                String.valueOf(impresion.getIdSolicitudImpresion()));
        helper.cerrar();
        Toast.makeText(this, r2, Toast.LENGTH_SHORT).show();
        finish();


    }

    public void cancelarModificacion(View view) {
        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        eliminar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.VISIBLE);

        editExamenes.setText(Integer.toString(impresion.getCantidadExamenes()));
        editHojas.setText(Integer.toString(impresion.getHojasEmpaque()));
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
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
        editExamenes.setEnabled(false);
        editHojas.setEnabled(false);
        editDetalles.setEnabled(false);

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        eliminar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.VISIBLE);

    }
}
