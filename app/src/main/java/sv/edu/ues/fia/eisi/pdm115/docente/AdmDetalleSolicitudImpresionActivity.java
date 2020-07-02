package sv.edu.ues.fia.eisi.pdm115.docente;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.impresionInalambrica.AdaptadorImpresoraURL;
import sv.edu.ues.fia.eisi.pdm115.utilidades.BuscarRuta;
import sv.edu.ues.fia.eisi.pdm115.webServices.SubirDocumentoService;

public class AdmDetalleSolicitudImpresionActivity extends AppCompatActivity {
    Impresion impresion;
    String path, nombre, directorio, nuevoNombre;
    long secs;
    Context contexto = AdmDetalleSolicitudImpresionActivity.this;
    EditText editDocente, editExamenes, editHojas,editAprobacion, editDetalles,
            editEstadoImp, editMotivo, observaciones, editPath;
    TextView razonTitulo;
    TableRow tr1, tr2, tr3;
    LinearLayout li1, li2, li3;
    Button cancelar, guardar, eliminar, actualizar, verArchivo, cambiarArchivo, imprimirBtn;
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
        verArchivo = findViewById(R.id.verArchivo);
        cambiarArchivo = findViewById(R.id.cambiarArchivo);
        editPath = findViewById(R.id.path);
        cancelar = findViewById(R.id.cancelarmod);
        guardar = findViewById(R.id.guardarSol);
        eliminar = findViewById(R.id.eliminarSolicitud);
        actualizar = findViewById(R.id.actualizarSolicitud);
        imprimirBtn = findViewById(R.id.imprimir);

        int idSolicitud = getIntent().getIntExtra("idImpresion",0);
        String usuario = getIntent().getStringExtra("usuarioActual");
        helper.abrir();
        impresion = helper.getSolicitudImpresion(String.valueOf(idSolicitud));

        if (impresion.getEstadoAprobacion()==2){
            editMotivo.setText("Solicitud no aprobada");
        }else{
            editMotivo.setText(helper.getMotivo(impresion.getIdMotivoNoImp()));
        }
        Docente docente = helper.getDocenteAdmin(impresion.getIdDocente());
        nuevoNombre = "formato-"+docente.getApellidoDocente();
        if (usuario.equals("ADMIN")){
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

        if(impresion.getUrl()!=null && !impresion.getUrl().equals("Sin archivo de formato")){
            editPath.setText(impresion.getUrl());
        }else{
            verArchivo.setEnabled(false);
            imprimirBtn.setEnabled(false);
        }

        editAprobacion.setText(estadoAprobacion[impresion.getEstadoAprobacion()]);
        editEstadoImp.setText(estadoImpresion[impresion.getEstadoImpresion()]);

        if(impresion.getEstadoImpresion()!=2){
            tr2.setVisibility(View.GONE);
            li2.setVisibility(View.GONE);
            tr3.setVisibility(View.GONE);
            li3.setVisibility(View.GONE);
            razonTitulo.setVisibility(View.GONE);
        }

        imprimirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imprimirFormato();
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

    public void actualizar(View view) {
        editExamenes.setEnabled(true);
        editHojas.setEnabled(true);
        editDetalles.setEnabled(true);
        verArchivo.setVisibility(View.GONE);
        cambiarArchivo.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.GONE);
        actualizar.setVisibility(View.GONE);

    }

    public void eliminarSol(View view) {
        AlertDialog.Builder seleccion  =
                new AlertDialog.Builder(contexto);
        seleccion.setTitle("Confirmar:");
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
        verArchivo.setVisibility(View.VISIBLE);
        cambiarArchivo.setVisibility(View.GONE);
    }

    public void guardarSolDocente(View view) {
        impresion.setCantidadExamenes(Integer.parseInt(editExamenes.getText().toString()));
        impresion.setHojasEmpaque(Integer.parseInt(editHojas.getText().toString()));
        impresion.setDescripcionSolicitud(editDetalles.getText().toString());

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
        String resultado = helper.actualizarSolicitudImpresion(impresion);
        helper.cerrar();
        if(!resultado.equals("Error")){
            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }

        editExamenes.setEnabled(false);
        editHojas.setEnabled(false);
        editDetalles.setEnabled(false);
        verArchivo.setVisibility(View.VISIBLE);
        cambiarArchivo.setVisibility(View.GONE);

        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        eliminar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.VISIBLE);
        recreate();
    }

    public void verPDF(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pdmgrupo12.000webhostapp.com/documentos/"+impresion.getUrl()));
        startActivity(browserIntent);
    }

    public void CambiarPDF(View view) {
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
