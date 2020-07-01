package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.Escuela;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.utilidades.BuscarRuta;
import sv.edu.ues.fia.eisi.pdm115.webServices.SubirDocumentoService;

public class AdmSolicitarImpresionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);;
    EditText editEscuela, editDocente, cantExamenes, cantHojasEmpaque, detExtras, editPath;
    TableRow tr1, tr2;
    LinearLayout li1, li2;
    Button buscar;
    int idEncargado;
    long secs = (new Date().getTime())/1000;
    String idDocente, idEscuela, path, nombre, directorio, nuevoNombre;
    Impresion impresion;
    Docente docente;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_solicitar_impresion);
        this.setTitle("Nueva solicitud de impresión");

        tr1 = findViewById(R.id.tableRow1);
        tr2 = findViewById(R.id.tableRow2);
        li1 = findViewById(R.id.linearLayout1);
        li2 = findViewById(R.id.linearLayout2);
        editEscuela = findViewById(R.id.editEscuela);
        editDocente = findViewById(R.id.editDocente);
        cantExamenes = findViewById(R.id.cantidadExamenes);
        cantHojasEmpaque = findViewById(R.id.cantidadHojasEmpaque);
        detExtras = findViewById(R.id.detallesExtra);
        editPath = findViewById(R.id.path);
        buscar = findViewById(R.id.buscar);
        editDocente.setEnabled(false);

        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String usuario = prefs.getString("usuarioActual","");

        if (usuario.equals("ADMIN")){
            editEscuela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.abrir();
                    final ArrayList<Escuela> escuelas = helper.consultarEscuelas();
                    helper.cerrar();
                    final String[] opcionesEscuela = new String[escuelas.size()];
                    for (int i=0; i<escuelas.size(); i++){
                        opcionesEscuela[i] = escuelas.get(i).getNombreEscuela();
                    }
                    AlertDialog.Builder seleccion =
                            new AlertDialog.Builder(AdmSolicitarImpresionActivity.this);
                    seleccion.setTitle("Seleccione una escuela:");
                    seleccion.setItems(opcionesEscuela,
                            new DialogInterface.OnClickListener(){

                                public void onClick(DialogInterface dialog, int item) {

                                    editEscuela.setText(opcionesEscuela[item]);
                                    helper.abrir();
                                    idEscuela = escuelas.get(item).getIdEscuela();
                                    idEncargado = helper.getIdEncargadoImpresionesEscuela(idEscuela);
                                    helper.cerrar();
                                    editDocente.setEnabled(true);
                                }
                            });
                    seleccion.show();
                }
            });
            editDocente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.abrir();
                    final ArrayList<Docente> docentes = helper.getDocentes(idEscuela);
                    helper.cerrar();
                    final String[] opcionesDocente = new String[docentes.size()];
                    for (int i=0; i<docentes.size(); i++){
                        opcionesDocente[i] = docentes.get(i).getNombreDocente()+" "+docentes.get(i).getApellidoDocente();
                    }
                    AlertDialog.Builder seleccion =
                            new AlertDialog.Builder(AdmSolicitarImpresionActivity.this);
                    seleccion.setTitle("Seleccione un docente:");
                    seleccion.setItems(opcionesDocente,
                            new DialogInterface.OnClickListener(){

                                public void onClick(DialogInterface dialog, int item) {
                                    editDocente.setText(opcionesDocente[item]);
                                    idDocente = docentes.get(item).getIdDocente();
                                    nuevoNombre = "formato-"+ docentes.get(item).getApellidoDocente()+"-"+secs;
                                }
                            });
                    seleccion.show();
                }
            });
        }else {
            tr1.setVisibility(View.GONE);
            tr2.setVisibility(View.GONE);
            li1.setVisibility(View.GONE);
            li2.setVisibility(View.GONE);
            helper.abrir();
            docente = helper.getDocenteActual(usuario);
            idDocente = docente.getIdDocente();
            idEncargado = helper.getIdEncargadoImpresionesEscuela(docente.getIdEscuela());
            helper.cerrar();
        }

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
            String usuario = prefs.getString("usuarioActual","");
            if (!usuario.equals("ADMIN")){
                nuevoNombre = "formato-"+ docente.getApellidoDocente()+"-"+secs;
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

            if(nuevoNombre!=null){
                impresion.setUrl(nuevoNombre+".pdf");
                try {
                    Intent mIntent = new Intent(this, SubirDocumentoService.class);
                    mIntent.putExtra("path", path);
                    mIntent.putExtra("directorio", directorio);
                    mIntent.putExtra("nombre", nuevoNombre);
                    SubirDocumentoService.enqueueWork(getApplicationContext(), mIntent);

                    // copiarLocal(path, nombre, directorio);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                impresion.setUrl("Sin archivo de formato");
            }

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

    //PROYECTO 2
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
