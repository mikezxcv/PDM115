package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudRepetido extends AppCompatActivity {
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_repetido);
        boton = (Button) findViewById(R.id.irAprobarSolicitudRepetido);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmDetallesolicitudRepetido.this, AdmAprobarSolRepetido.class));
            }
        });
    }
}
