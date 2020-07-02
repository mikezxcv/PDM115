package sv.edu.ues.fia.eisi.pdm115.qr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sv.edu.ues.fia.eisi.pdm115.R;


public class EscanerActivity extends AppCompatActivity {

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private ToneGenerator tonoAlDetectar;
    SurfaceView surfaceView;
    TextView textViewQR;
    String intentData = "";
    int modo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);

        textViewQR = findViewById(R.id.textViewQR);
        surfaceView = findViewById(R.id.surfaceView);
        modo = getIntent().getIntExtra("modoEscaner", Barcode.QR_CODE);
        tonoAlDetectar = new ToneGenerator(AudioManager.STREAM_MUSIC,100);
        iniciarDeteccion();

    }
    private void iniciarDeteccion() {
        tonoAlDetectar.startTone(ToneGenerator.TONE_CDMA_PIP,150);
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(modo).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(EscanerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(EscanerActivity.this, new
                                String[]{Manifest.permission.CAMERA}, 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getApplicationContext(), "Se ha cancelado el escaneo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {

                    textViewQR.post(new Runnable() {
                        @Override
                        public void run() {
                            if (barcodes.valueAt(0).rawValue != null) {
                                textViewQR.removeCallbacks(null);
                                intentData = barcodes.valueAt(0).displayValue;
                                textViewQR.setText(intentData);
                            }
                        }
                    });

                    if (modo==Barcode.QR_CODE) {
                        if (barcodes.valueAt(0).geoPoint!=null){

                            Double lat = barcodes.valueAt(0).geoPoint.lat;
                            Double lng = barcodes.valueAt(0).geoPoint.lng;
                            enviarResultadoQR(lat, lng);

                        } else if (barcodes.valueAt(0).rawValue.contains("maps.google.com/")){

                            Pattern p = Pattern.compile("[-]?[0-9]*\\.?,?[0-9]+");
                            Matcher m = p.matcher(barcodes.valueAt(0).rawValue);
                            List<Double> coordenadas=new ArrayList<>();
                            while (m.find()){
                                coordenadas.add(Double.valueOf(m.group()));
                            }
                            enviarResultadoQR(coordenadas.get(0), coordenadas.get(1));
                        }
                    }else if (modo==Barcode.CODE_39 && barcodes.valueAt(0).rawValue!=null) {
                        Intent resultado = new Intent();
                        resultado.putExtra("carnet", barcodes.valueAt(0).rawValue);
                        setResult(RESULT_OK, resultado);
                        finish();
                    } else {
                        //No es del tipo requerido.
                    }
                }
            }
        });
    }

    void enviarResultadoQR(Double lat, Double lng){
        Intent resultado = new Intent();
        resultado.putExtra("latitud", lat);
        resultado.putExtra("longitud", lng);
        setResult(RESULT_OK, resultado);
        finish();
    }
}
//^[+-]?(([1-9]\d*)|0)(\.\d+)?