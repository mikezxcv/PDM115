package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteSegundaRevisionActivity extends AppCompatActivity {
    Button boton3;
    Button boton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_segunda_revision);
        boton3=(Button)findViewById(R.id.irCrearSolicitudSegundaRevision);
        boton4=(Button)findViewById(R.id.irConsultarSolicitudSegundaRevision);

        boton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent boton3 = new Intent(EstudianteSegundaRevisionActivity.this, EstudianteSolicitudSegundaRevisionActivity.class);
                startActivity(boton3);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent boton4 = new Intent(EstudianteSegundaRevisionActivity.this, EstudianteConsultarSolicitudSegundaRevision.class);
                startActivity(boton4);
            }
        });
    }
}
