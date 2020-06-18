package sv.edu.ues.fia.eisi.pdm115.qr;

import android.Manifest;
import android.content.pm.PackageManager;
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

import sv.edu.ues.fia.eisi.pdm115.R;


public class EscanerActivity extends AppCompatActivity {

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    SurfaceView surfaceView;
    TextView textViewQR;
    String intentData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);

        textViewQR = findViewById(R.id.textViewQR);
        surfaceView = findViewById(R.id.surfaceView);
        iniciarDeteccion();

    }
    private void iniciarDeteccion() {

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE|Barcode.CODE_39).build();

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
                }
            }
        });
    }
}
