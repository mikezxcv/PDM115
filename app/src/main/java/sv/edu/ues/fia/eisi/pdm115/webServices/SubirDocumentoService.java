package sv.edu.ues.fia.eisi.pdm115.webServices;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

public class SubirDocumentoService extends JobIntentService {

    ControlBdGrupo12 DB = new ControlBdGrupo12(SubirDocumentoService.this);

    private static final String TAG = "SubirDocumentoService";
    private static final int JOB_ID = 1000;
    public String urlServidor = "https://pdmgrupo12.000webhostapp.com/upload.php?directorio=%s&nombre=%s";
    HttpURLConnection conn = null;
    DataOutputStream output = null;
    FileInputStream fileInputStream = null;
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    String rutaArchivoLocal;
    String reply;
    int bytesRead, bytesAvailable, bufferSize;
    byte[] buffer;
    int maxBufferSize = 1024 * 1024;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, SubirDocumentoService.class, JOB_ID, intent);
    }
    @Override
    public void onCreate() {
        Toast.makeText(SubirDocumentoService.this, "Subiendo archivo...", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        rutaArchivoLocal = intent.getStringExtra("path");
        String dir = intent.getStringExtra("directorio");
        String nombre = intent.getStringExtra("nombre");
        String antiguo;
        if (intent.hasExtra("antiguo")) {
            antiguo = intent.getStringExtra("antiguo");
            urlServidor = urlServidor+"&antiguo="+antiguo;
        }
        try {
            //Prepara la url con los parametros enviados desde la actividad.
            //El directorio y nombre del documento en el servidor se mandan por variables GET
            urlServidor = String.format(urlServidor, dir,nombre);

            File sourceFile = new File(rutaArchivoLocal);

            if (sourceFile.isFile()){
                try{
                    fileInputStream = new FileInputStream(sourceFile);

                    // Abre conexion con el servidor
                    URL url = new URL(urlServidor);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("POST"); //Establece el metodo para enviar el documento.

                    //Necesario para mantener la conexion hasta que finalice el envio.
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("archivo", rutaArchivoLocal);

                    output = new DataOutputStream(conn.getOutputStream());

                    output.writeBytes(twoHyphens + boundary + lineEnd);
                    output.writeBytes("Content-Disposition: form-data; name=\"archivo\";filename=\"" + rutaArchivoLocal + "\"" + lineEnd);
                    output.writeBytes(lineEnd);

                    // Crea buffer para transferir el archivo local al contenido de la peticion
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    // Lee el archivo y lo envia añ cuerpo de la peticion
                    while (bytesRead > 0) {
                        output.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    output.writeBytes(lineEnd);
                    output.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Esperar respuesta del servidor
                    int serverResponseCode = conn.getResponseCode();

                    if (serverResponseCode != 200){throw new AssertionError();}
                    else {
                        InputStream in = conn.getInputStream();
                        StringBuffer sb = new StringBuffer();
                        try {
                            int chr;
                            while ((chr = in.read()) != -1) {
                                if('<'==(char)chr){
                                    break;
                                }else {
                                    sb.append((char) chr);
                                }
                            }
                            reply = sb.toString();
                        } finally {
                            in.close();
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(SubirDocumentoService.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }finally {
                    //Cerrar flujo de entrada/salida
                    fileInputStream.close();
                    output.flush();
                    output.close();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        //Si termina el proceso exitosamente, se muestra el toast.
        super.onDestroy();
        Toast.makeText(SubirDocumentoService.this, reply, Toast.LENGTH_SHORT).show();
        // Se debe mod Para pasarle la URL del la imagen del servidor Luego eliminar la URL de prueba
        //guardarURL();
    }

    private void guardarURL(){
        // URL DE PRUEBA
        String URL = "https://www.animemes.org/images/meme/es/anime-meme-F36BoGx9HC.jpg";
        DB.abrir();
        DB.actualizarURL(URL);
        DB.cerrar();
    }
}
