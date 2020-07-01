package sv.edu.ues.fia.eisi.pdm115.impresionInalambrica;

import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;

import androidx.annotation.RequiresApi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AdaptadorImpresoraURL extends PrintDocumentAdapter {

    private String downloadServiceURL = "https://pdmgrupo12.000webhostapp.com/filestream.php?directorio=documentos/&archivo=%s";
    private String archivo;
    private StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    public AdaptadorImpresoraURL(String archivo){
        this.archivo = archivo;
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        //Checa si el usuario o la aplicacion ha decidido cancelar el proceso de impresion
        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
        } else{
            //Se prepara la metadata a ser enviada a la empresora. Numero de paginas, tamaño de hoja, etc.
            PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("PDM115-GPO12")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN)
                    .build();
            callback.onLayoutFinished(pdi,true);
        }
    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destino, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        InputStream input = null;
        OutputStream output = null;
        downloadServiceURL = String.format(downloadServiceURL, archivo);

        try {
            URL url = new URL(downloadServiceURL);
            //Se establece una conexion al servidor de 000Webhost para cargar el archivo.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            input = conn.getInputStream();
            output = new FileOutputStream(destino.getFileDescriptor());

            //Define el tamaño de buffer a utilizar.
            byte[] buf = new byte[1024];
            int bytesRead;

            //Transfiere el archivo del servidor al flujo de salida en la cola de impresión.
            while ((bytesRead = input.read(buf)) > 0 && !cancellationSignal.isCanceled()) {
                output.write(buf, 0, bytesRead);
            }
            if (cancellationSignal.isCanceled()) {
                callback.onWriteCancelled();
            }
            else {
                callback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
            }

        } catch (FileNotFoundException e){
            e.fillInStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // cierra flujo de entrada/salida de archivos
                assert input != null;
                assert output != null;
                input.close();
                output.close();
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
