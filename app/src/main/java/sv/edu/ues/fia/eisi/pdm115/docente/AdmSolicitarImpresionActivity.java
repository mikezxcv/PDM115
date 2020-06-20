package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.utilidades.BuscarRuta;
import sv.edu.ues.fia.eisi.pdm115.webServices.SubirDocumentoService;

public class AdmSolicitarImpresionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);;
    EditText cantExamenes, cantHojasEmpaque, detExtras, editPath;
    Button buscar;
    int idEncargado;
    String idDocente;
    String path;
    String nombre;
    String directorio;
    String nuevoNombre;
    Docente docente;
    Impresion impresion;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_solicitar_impresion);
        cantExamenes = findViewById(R.id.cantidadExamenes);
        cantHojasEmpaque = findViewById(R.id.cantidadHojasEmpaque);
        detExtras = findViewById(R.id.detallesExtra);
        editPath = findViewById(R.id.path);
        buscar = findViewById(R.id.buscar);
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String usuarioDocente = prefs.getString("usuarioActual","");
        helper.abrir();
        docente = helper.getDocenteActual(usuarioDocente);
        idDocente = docente.getIdDocente();
        idEncargado = helper.getIdEncargadoImpresionesEscuela(docente.getIdEscuela());
        helper.cerrar();
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
            directorio = "documentos";
            nuevoNombre =  docente.getApellidoDocente();
            editPath.setText(nombre);

            try {
                Intent mIntent = new Intent(this, SubirDocumentoService.class);
                mIntent.putExtra("path", path);
                mIntent.putExtra("directorio", directorio);
                mIntent.putExtra("nombre", nuevoNombre);
                SubirDocumentoService.enqueueWork(getApplicationContext(), mIntent);

                copiarLocal(path, nombre, directorio);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void enviarSolicitud(View view) {
        if(cantExamenes.getText().toString().isEmpty()||cantHojasEmpaque.getText().toString().isEmpty()||detExtras.getText().toString().isEmpty()){
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            impresion = new Impresion();
            impresion.setIdDocente(idDocente);
            if(idEncargado!=0) {impresion.setIdEncargado(idEncargado);}
            impresion.setCantidadExamenes(Integer.parseInt(cantExamenes.getText().toString()));
            impresion.setHojasEmpaque(Integer.parseInt(cantHojasEmpaque.getText().toString()));
            impresion.setDescripcionSolicitud(detExtras.getText().toString());
            helper.abrir();
            String resultado = helper.insertarSolicitudImpresion(impresion);
            helper.cerrar();
            if(!resultado.equals("Error")){
                Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void copiarLocal(String origen, String directorio, String nombre) {

        InputStream in = null;
        OutputStream out = null;

        //Crear el directorio dentro de los archivos de la APP
        File dir = new File(getFilesDir(), (!directorio.isEmpty()) ? directorio : "documentos");

        if (!dir.exists()) {
            if (dir.mkdirs()) {
                Toast.makeText(this, "Directorio creado", Toast.LENGTH_SHORT).show();
            }
        }

        try {
            File src = new File(origen);
            File des = new File(dir.getPath() + "/" + nombre);
            if (src.isFile() && des.isFile()) {
                in = new FileInputStream(src);
                out = new FileOutputStream(des);

                //Copiar el documento
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void buscarArchivo(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar archivo"), 1);
        setResult(Activity.RESULT_OK);
    }

    public void cancelar(View view) {
        finish();
    }
}
