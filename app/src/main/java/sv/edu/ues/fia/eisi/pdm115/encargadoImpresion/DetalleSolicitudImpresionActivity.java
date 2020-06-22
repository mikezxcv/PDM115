package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.EncargadoDeImpresiones;
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
    String[] estadoAprobacion = {"Pendiente","Aprobada","Rechazada"};
    String[] estadoImpresion = {"Pendiente", "Realizada", "No se realizó"};
    ArrayList<MotivoNoImpresion> motivos;
    EditText editExamenes, editHojas, editDetalles, editAprobacion, editEstadoImp,
            editMotivo, observaciones, editDocente, editEncargado;
    Button guardar, cancelar, modificarEstadoAprobacion, cambiarEstadoImpresion;
    TableRow tr1;
    LinearLayout li1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_impresion);
        this.setTitle("Detalle de la solicitud");

        tr1 = findViewById(R.id.tableRow1);
        li1 = findViewById(R.id.linearLayout1);
        editDocente = findViewById(R.id.editDocente);
        editEncargado = findViewById(R.id.editEncargado);
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

        int idSolicitud = getIntent().getIntExtra("idImpresion",0);
        String usuario = getIntent().getStringExtra("usuarioActual");
        helper.abrir();
        motivos = helper.obtenerMotivos();
        impresion = helper.getSolicitudImpresion(String.valueOf(idSolicitud));
        Docente docente = helper.getDocenteAdmin(impresion.getIdDocente());
        String edDocente = docente.getNombreDocente()+" "+docente.getApellidoDocente();
        editDocente.setText(edDocente);
        if (usuario.equals("ADMIN")){
            EncargadoDeImpresiones encargado = helper.consultarEncargado(
                    String.valueOf(impresion.getIdEncargado()));
            String edEncargado = encargado.getNombreEncargado()+" "+encargado.getApellidoEncargado();
            editEncargado.setText(edEncargado);

        }else{
            tr1.setVisibility(View.GONE);
            li1.setVisibility(View.GONE);
        }

        helper.cerrar();


        editExamenes.setText(String.valueOf(impresion.getCantidadExamenes()));
        editHojas.setText(String.valueOf(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        if (impresion.getIdMotivoNoImp()!=0){
            editMotivo.setText(motivos.get(impresion.getIdMotivoNoImp()).getMotivoNoImpresion());
        }else {
            editMotivo.setText("No asignado");
        }
        observaciones.setText(impresion.getDescripcionNoImp());
        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);

        editAprobacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder seleccion =
                        new AlertDialog.Builder(contexto);
                seleccion.setTitle("Seleccione una opcion:");
                seleccion.setItems(estadoAprobacion,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int item)
                            {
                                editAprobacion.setText(estadoAprobacion[item]);
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
                        seleccion.setItems(estadoImpresion,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {
                                    editEstadoImp.setText(estadoImpresion[item]);
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
        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);


        if (impresion.getIdMotivoNoImp()!=0 || impresion.getIdMotivoNoImp()!=-1){
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
