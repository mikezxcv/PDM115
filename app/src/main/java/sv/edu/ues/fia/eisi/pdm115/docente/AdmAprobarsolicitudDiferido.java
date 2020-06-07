package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmAprobarsolicitudDiferido extends AppCompatActivity {
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobarsolicitud_diferido);
        boton = (Button) findViewById(R.id.guardar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmAprobarsolicitudDiferido.this, AdmDiferidoActivity.class));
            }
        });

    }
}
