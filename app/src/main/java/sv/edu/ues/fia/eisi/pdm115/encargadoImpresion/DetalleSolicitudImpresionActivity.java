package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.EncargadoDeImpresiones;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.MotivoNoImpresion;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.impresionInalambrica.AdaptadorImpresoraURL;
import sv.edu.ues.fia.eisi.pdm115.utilidades.BuscarRuta;
import sv.edu.ues.fia.eisi.pdm115.webServices.SubirDocumentoService;


public class DetalleSolicitudImpresionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper= new ControlBdGrupo12(this);
    Context contexto = DetalleSolicitudImpresionActivity.this;
    int modo, aprobacionTmp, estadoTmp, motivoTmp;
    long secs;
    String observacionesTmp, path, nombre, directorio, nuevoNombre, nombreTmp;
    Impresion impresion;
    String[] estadoAprobacion = {"Pendiente","Aprobada","Rechazada"};
    String[] estadoImpresion = {"En espera", "Realizada", "No se realizó"};
    ArrayList<MotivoNoImpresion> motivos;
    EditText editExamenes, editHojas, editDetalles, editAprobacion, editEstadoImp,
            editMotivo, observaciones, editDocente, editEncargado, editPath;
    Button guardar, cancelar, modificarEstadoAprobacion, cambiarEstadoImpresion, verArchivo, cambiarArchivo, imprimirBtn;
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
        verArchivo = findViewById(R.id.verArchivo);
        cambiarArchivo = findViewById(R.id.cambiarArchivo);
        editPath = findViewById(R.id.path);
        guardar = findViewById(R.id.guardarCambios);
        cancelar = findViewById(R.id.cancelarCambios);
        modificarEstadoAprobacion = findViewById(R.id.modificarEstadoAprobacion);
        cambiarEstadoImpresion = findViewById(R.id.cambiarEstadoImpresion);
        imprimirBtn = findViewById(R.id.imprimir);

        int idSolicitud = getIntent().getIntExtra("idImpresion",0);
        String usuario = getIntent().getStringExtra("usuarioActual");
        helper.abrir();
        motivos = helper.obtenerMotivos();
        impresion = helper.getSolicitudImpresion(String.valueOf(idSolicitud));
        Docente docente = helper.getDocenteAdmin(impresion.getIdDocente());
        String edDocente = docente.getNombreDocente()+" "+docente.getApellidoDocente();
        nuevoNombre = "formato-"+docente.getApellidoDocente();
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

        if (impresion.getIdMotivoNoImp()!=0){
            editMotivo.setText(helper.getMotivo(impresion.getIdMotivoNoImp()));
        }else {
            if (impresion.getEstadoAprobacion()==2){
                editMotivo.setText("Solicitud no aprobada");
            }
            else { editMotivo.setText("---");}
        }
        helper.cerrar();

        editExamenes.setText(String.valueOf(impresion.getCantidadExamenes()));
        editHojas.setText(String.valueOf(impresion.getHojasEmpaque()));
        editDetalles.setText(impresion.getDescripcionSolicitud());
        observaciones.setText(impresion.getDescripcionNoImp());
        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        if (impresion.getEstadoAprobacion()!=1){
            imprimirBtn.setEnabled(false);
        }
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);
        if(impresion.getUrl()!=null && !impresion.getUrl().equals("Sin archivo de formato")){
            editPath.setText(impresion.getUrl());
        }else{
            verArchivo.setEnabled(false);
            imprimirBtn.setEnabled(false);
            cambiarArchivo.setText("Agregar archivo");
        }

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);

        imprimirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imprimirFormato();
            }
        });

        editAprobacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder seleccion =
                        new AlertDialog.Builder(contexto);
                seleccion.setTitle("Seleccione una opcion:");
                seleccion.setItems(estadoAprobacion, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                editAprobacion.setText(estadoAprobacion[item]);
                                impresion.setEstadoAprobacion(item);
                                if (item!=2){
                                    editEstadoImp.setText(estadoImpresion[0]);
                                    impresion.setEstadoImpresion(0);
                                    impresion.setIdMotivoNoImp(0);
                                    impresion.setDescripcionNoImp("");
                                    editMotivo.setText("---");
                                    observaciones.getText().clear();
                                }else {
                                    editEstadoImp.setText("No se realizó");
                                    impresion.setEstadoImpresion(2);
                                    impresion.setIdMotivoNoImp(0);
                                    observaciones.setText("El docente director no aprobó la solicitud");
                                    editMotivo.setText("Solicitud no aprobada");
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
                if(impresion.getEstadoAprobacion()!=2) {
                    AlertDialog.Builder seleccion = new AlertDialog.Builder(contexto);
                    seleccion.setTitle("Seleccione una opcion:");
                    seleccion.setItems(estadoImpresion, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {
                                    editEstadoImp.setText(estadoImpresion[item]);
                                    impresion.setEstadoImpresion(item);
                                    if (item == 2) {
                                        editMotivo.setEnabled(true);
                                        observaciones.setEnabled(true);
                                        editMotivo.setText("No asignado");
                                    } else {
                                        editMotivo.setEnabled(false);
                                        observaciones.setEnabled(false);
                                    }
                                }
                            });
                    seleccion.show();
                }else {
                    Toast.makeText(contexto, "La solicitud no se aprobó", Toast.LENGTH_SHORT).show();
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
                                        impresion.setIdMotivoNoImp(motivos.get(item).getIdMotivo());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speech, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_speech){
            iniciarSpeech();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri uri = data.getData();
            path = BuscarRuta.getPath(this, uri);
            String[] partes = path.split("/");
            nombre = partes[partes.length - 1];
            directorio = "documentos/";
            editPath.setText(nombre);
        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK){
            if (imprimirBtn.isEnabled()) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String match = result.get(0);
                if (!match.contains("no") && match.contains("imprimir") && match.contains("formato")) {
                    imprimirFormato();
                }
            } else {
                Toast.makeText(this, "No se puede imprimir sin autorización o archivo disponible", Toast.LENGTH_LONG).show();
            }
        }
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
        if (impresion.getEstadoImpresion()==2){
            editMotivo.setEnabled(true);
            observaciones.setEnabled(true);
        }
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
            case 3:
                if (impresion.getUrl()!=editPath.getText().toString()) {
                    secs = (new Date().getTime())/1000;
                    try {
                        Intent mIntent = new Intent(this, SubirDocumentoService.class);
                        mIntent.putExtra("path", path);
                        mIntent.putExtra("directorio", directorio);
                        mIntent.putExtra("nombre", nuevoNombre+"-"+secs);
                        if (!impresion.getUrl().equals("Sin archivo de formato"))
                            mIntent.putExtra("antiguo", impresion.getUrl());

                        SubirDocumentoService.enqueueWork(getApplicationContext(), mIntent);
                        impresion.setUrl(nuevoNombre+"-"+secs+".pdf");
                        editPath.setText(impresion.getUrl());
                        // copiarLocal(path, nombre, directorio);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                }
                helper.abrir();
                String res3 = helper.actualizarDocumentoDeFormato(impresion);
                helper.cerrar();
                editPath.setText(impresion.getUrl());
                Toast.makeText(contexto,res3,Toast.LENGTH_SHORT).show();
                modo=0;
                break;
            default:
                break;
        }

        cambiarEstadoImpresion.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.VISIBLE);
        cambiarArchivo.setVisibility(View.VISIBLE);
        editEstadoImp.setEnabled(false);
        editMotivo.setEnabled(false);
        observaciones.setEnabled(false);
        cancelar.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);
        recreate();
    }

    public void cancelar(View view) {
        editAprobacion.setEnabled(false);
        rollback();
        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);


        if (impresion.getIdMotivoNoImp()!=0 && impresion.getIdMotivoNoImp()!=-1){
            helper.abrir();
            editMotivo.setText(helper.getMotivo(impresion.getIdMotivoNoImp()));
            helper.cerrar();
        }else {
            if (impresion.getEstadoAprobacion()!=2) editMotivo.setText("---");
            else {editMotivo.setText("Solicitud no aprobada");}
        }
        if(impresion.getUrl()!=null && !impresion.getUrl().equals("Sin archivo de formato")){
            editPath.setText(impresion.getUrl());
        }else{
            verArchivo.setEnabled(false);
            cambiarArchivo.setText("Agregar Archivo");
        }
        observaciones.setText(impresion.getDescripcionNoImp());
        editEstadoImp.setEnabled(false);
        editMotivo.setEnabled(false);
        observaciones.setEnabled(false);
        editPath.setText(impresion.getUrl());

        cambiarEstadoImpresion.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.VISIBLE);
        cambiarArchivo.setVisibility(View.VISIBLE);
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

    public void verPDF(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pdmgrupo12.000webhostapp.com/documentos/"+impresion.getUrl()));
        startActivity(browserIntent);
    }

    public void CambiarPDF(View view) {
        modo = 3;
        cancelar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);
        modificarEstadoAprobacion.setVisibility(View.GONE);
        cambiarEstadoImpresion.setVisibility(View.GONE);
        cambiarArchivo.setVisibility(View.GONE);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar archivo"), 1);
        setResult(Activity.RESULT_OK);
    }

    public void imprimirFormato() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printAdapter = new AdaptadorImpresoraURL(impresion.getUrl());
            assert printManager != null;
            printManager.print("PDM115-GPO12", printAdapter, new PrintAttributes.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarSpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-MX");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora");

        try{
            startActivityForResult(intent, 2);
        }catch(Exception e){
            Toast.makeText(this, "Algo salió mal",Toast.LENGTH_SHORT).show();
        }
    }
}
