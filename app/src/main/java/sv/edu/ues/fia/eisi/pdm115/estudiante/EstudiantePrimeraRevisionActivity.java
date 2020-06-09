package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pdm115.R;


public class EstudiantePrimeraRevisionActivity extends AppCompatActivity {
    Button boton3;
    Button boton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_primera_revision);
        boton3= findViewById(R.id.irCrearSolicitudRevision);
        boton4= findViewById(R.id.irConsultarSolicitudRevision);

        boton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent boton3 = new Intent(EstudiantePrimeraRevisionActivity.this, EstudianteSolicitudPrimeraRevisionActivity.class);
                startActivity(boton3);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent boton4 = new Intent(EstudiantePrimeraRevisionActivity.this, EstudianteConsultarSolicitudPrimeraRevisionActivity.class);
                startActivity(boton4);
            }
        });
    }
}