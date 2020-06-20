package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.MotivoNoImpresion;
import sv.edu.ues.fia.eisi.pdm115.R;


public class DetalleSolicitudImpresionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper= new ControlBdGrupo12(this);
    Context contexto = DetalleSolicitudImpresionActivity.this;
    int modo;
    int aprobacionTmp;
    int estadoTmp;
    int motivoTmp;
    String observacionesTmp;
    Impresion impresion;
    String[] opcionesEstadoImp = {"Pendiente", "Realizada", "No se realizó"};
    String[] opcionesAprobacion = {"Pendiente","Aprobada","Rechazada"};
    ArrayList<MotivoNoImpresion> motivos;
    EditText editExamenes, editHojas, editDetalles, editAprobacion, editEstadoImp, editMotivo, observaciones;
    Button guardar, cancelar, modificarEstadoAprobacion, cambiarEstadoImpresion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_impresion);
        helper.abrir();
        motivos = helper.obtenerMotivos();
        helper.cerrar();
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
        guardar = findViewById(R.id.guardarCambios);
        cancelar = findViewById(R.id.cancelarCambios);
        modificarEstadoAprobacion = findViewById(R.id.modificarEstadoAprobacion);
        cambiarEstadoImpresion = findViewById(R.id.cambiarEstadoImpresion);

        editExamenes.setText(Integer.toString(impresion.getCantidadExamenes()));
        editHojas.setText(Integer.toString(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        if (impresion.getIdMotivoNoImp()!=0){
            editMotivo.setText(motivos.get(impresion.getIdMotivoNoImp()).getMotivoNoImpresion());
        }
        observaciones.setText(impresion.getDescripcionNoImp());
        switch (impresion.getEstadoAprobacion()){
            case 0:
                editAprobacion.setText("Pendiente");
                break;
            case 1:
                editAprobacion.setText("Aprobada");
                break;
            case 2:
                editAprobacion.setText("Rechazada");
        }
        switch (impresion.getEstadoImpresion()){
            case 0:
                editEstadoImp.setText("Pendiente");
                break;
            case 1:
                editEstadoImp.setText("Realizada");
                break;
            case 2:
                editEstadoImp.setText("No se realizó");
        }

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);

        editAprobacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder seleccion =
                        new AlertDialog.Builder(contexto);
                seleccion.setTitle("Seleccione una opcion:");
                seleccion.setItems(opcionesAprobacion,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {
                                editAprobacion.setText(opcionesAprobacion[item]);
                                impresion.setEstadoAprobacion(item);
                                switch (item){
                                    case 0:
                                    case 1:
                                        editEstadoImp.setText("Pendiente");
                                        impresion.setEstadoImpresion(0);
                                        impresion.setIdMotivoNoImp(0);
                                        impresion.setDescripcionNoImp("");
                                        observaciones.getText().clear();
                                        break;
                                    case 2:
                                        editEstadoImp.setText("No se realizó");
                                        impresion.setEstadoImpresion(2);
                                        impresion.setIdMotivoNoImp(0);
                                        observaciones.setText("El docente director no aprobó la solicitud");
                                        impresion.setDescripcionNoImp(observaciones.getText().toString());
                                }
                            }
                        });
                seleccion.show();
            }
        });

        editEstadoImp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (impresion.getEstadoAprobacion()) {
                    case 0:
                    case 1:
                        AlertDialog.Builder seleccion = new AlertDialog
                                .Builder(contexto);
                        seleccion.setTitle("Seleccione una opcion:");
                        seleccion.setItems(opcionesEstadoImp,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {
                                    editEstadoImp.setText(opcionesEstadoImp[item]);
                                    impresion.setEstadoImpresion(item);
                                    if (item==2){
                                        editMotivo.setEnabled(true);
                                        observaciones.setEnabled(true);
                                        observaciones.setClickable(true);
                                        observaciones.setFocusable(true);
                                    }else {
                                        editMotivo.setEnabled(false);
                                        observaciones.setEnabled(false);
                                    }

                                }
                            });
                        seleccion.show();
                        break;

                    case 2:
                        Toast.makeText(contexto, "La solicitud no se aprobó",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }
        });

        editMotivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] opciones = new String[motivos.size()];
                switch (impresion.getEstadoImpresion()){
                    case 0:
                        Toast.makeText(contexto,
                                "La impresion aun se encuestra pendiente",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(contexto,
                                "La impresion se realizó, no se puede modificar este campo",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        for (int i=0;i<motivos.size();i++){
                            opciones[i] = motivos.get(i).getMotivoNoImpresion();
                        }
                        AlertDialog.Builder seleccion  =
                                new AlertDialog.Builder(contexto);
                        seleccion.setTitle("Seleccione un motivo:");
                        seleccion.setItems(opciones,
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int item)
                                    {
                                        editMotivo.setText(opciones[item]);
                                        impresion.setIdMotivoNoImp(item);
                                    }
                                });
                        seleccion.show();
                        break;
                    default:
                        break;
                }


            }
        });
    }


    public void ActualizarAprobacion(View view) {
        modo=1;
        variablesTemporales();
        cancelar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.GONE);
        cambiarEstadoImpresion.setVisibility(View.GONE);
        editAprobacion.setEnabled(true);

    }
    public void cambiarEstadoImpresion(View view) {
        modo=2;
        variablesTemporales();
        cancelar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.GONE);
        cambiarEstadoImpresion.setVisibility(View.GONE);
        editEstadoImp.setEnabled(true);
    }

    public void guardar(View view) {
        switch (modo){
            case 1:
                helper.abrir();
                String res1 = helper.actualizarAprobacionSolicitudImpresion(impresion);
                helper.cerrar();
                Toast.makeText(contexto,res1,Toast.LENGTH_SHORT).show();
                break;
            case 2:
                impresion.setDescripcionNoImp(observaciones.getText().toString());
                helper.abrir();
                String res2 = helper.actualizarEstadoDeImpresion(impresion);
                helper.cerrar();
                Toast.makeText(contexto,res2,Toast.LENGTH_SHORT).show();
                break;

        }
        cambiarEstadoImpresion.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);

    }

    public void cancelar(View view) {
        editAprobacion.setEnabled(false);
        rollback();
        switch (impresion.getEstadoAprobacion()){
            case 0:
                editAprobacion.setText("Pendiente");
                break;
            case 1:
                editAprobacion.setText("Aprobada");
                break;
            case 2:
                editAprobacion.setText("Rechazada");
        }
        switch (impresion.getEstadoImpresion()){
            case 0:
                editEstadoImp.setText("Pendiente");
                break;
            case 1:
                editEstadoImp.setText("Realizada");
                break;
            case 2:
                editEstadoImp.setText("No se realizó");
        }


        if (impresion.getIdMotivoNoImp()!=0){
            editMotivo.setText(motivos.get(motivos.indexOf(impresion.getIdMotivoNoImp())).getMotivoNoImpresion());
        }
        observaciones.setText(impresion.getDescripcionNoImp());
        editEstadoImp.setEnabled(false);
        editMotivo.setEnabled(false);
        observaciones.setEnabled(false);
        cambiarEstadoImpresion.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);
        estadoTmp=-1;
        motivoTmp=-1;
        aprobacionTmp=-1;
        modo=0;
        }

    public void variablesTemporales(){
        aprobacionTmp = impresion.getEstadoAprobacion();
        estadoTmp = impresion.getEstadoImpresion() ;
        motivoTmp = impresion.getIdMotivoNoImp();
        observacionesTmp = impresion.getDescripcionNoImp();
    }

    public void rollback(){
        impresion.setEstadoAprobacion(aprobacionTmp);
        impresion.setEstadoImpresion(estadoTmp);
        impresion.setIdMotivoNoImp(motivoTmp);
        impresion.setDescripcionNoImp(observacionesTmp);
    }
}
