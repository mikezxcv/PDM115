package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudPrimeraRevision extends AppCompatActivity {
    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detallesolicitud_primera_revision);
         btn = (Button) findViewById(R.id.irAorobarSolicitudPrimeraRevision);
         btn2= (Button) findViewById(R.id.darRevisionPrimeraRevision);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(AdmDetallesolicitudPrimeraRevision.this, AdmAprobarSolPrimeraRevision.class);
                 startActivity(intent);
             }
         });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmDetallesolicitudPrimeraRevision.this, AdmDarRevisionPrimeraRevision.class);
                startActivity(intent);
            }
        });
    }

}
