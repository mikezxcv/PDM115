package sv.edu.ues.fia.eisi.pdm115.impresionInalambrica;


import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AdaptadorImpresoraArchivoLocal extends PrintDocumentAdapter {

    private String path;

    public AdaptadorImpresoraArchivoLocal(String path){
        this.path = path;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        //Checa si el usuario o la aplicacion ha decidido cancelar el proceso de impresion
        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
        } else{
            //Se prepara la metadata a ser enviada a la empresora. Numero de paginas, tamaño de hoja, etc.
            PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("Prueba")
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

        try {
            File file = new File(path);
            input = new FileInputStream(file);
            output = new FileOutputStream(destino.getFileDescriptor());

            //Define el tamaño de buffer a utilizar.
            byte[] buf = new byte[1024];
            int bytesRead;

            //Transfiere el archivo al flujo de salida en la cola de impresión
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
            //Catch exception
        } catch (Exception ex) {
            //Catch exception
        } finally {
            try {
                assert input != null;
                assert output != null;
               // cierra flujo de entrada/salida de archivos
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
