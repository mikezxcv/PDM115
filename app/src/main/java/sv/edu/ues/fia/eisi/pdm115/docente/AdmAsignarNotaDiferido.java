package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmAsignarNotaDiferido extends AppCompatActivity {
    Button btnCambiarNota;
    String idDiferido;
    String estadoDiferido;
    EditText notaDiferido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_asignar_nota_diferido);

        btnCambiarNota = (Button)findViewById(R.id.cambiarNotaDiferidoAprobar);
        notaDiferido = (EditText)findViewById(R.id.editNotaDiferido);

        btnCambiarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarNotaDiferido(v);
            }
        });
    }

    public void asignarNotaDiferido(View view) {

        String notaFinal = notaDiferido.getText().toString();

        Bundle bundle= getIntent().getExtras();
        idDiferido= bundle.getString("idDiferido");

        ControlBdGrupo12 helper = new ControlBdGrupo12(this);
        helper.abrir();
        String estadoDiferido = helper.estadoSolicitudDiferido(idDiferido);
        helper.cerrar();

        if("APROBADO".equals(estadoDiferido)){
            if (notaFinal.isEmpty()) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                if(Float.valueOf(notaFinal)>= 0 && Float.valueOf(notaFinal) <= 10){
                    float notaFinal1 = Float.valueOf(notaFinal);
                    int idDetalle1 = Integer.valueOf(idDiferido);
                    helper.abrir();
                    String resultado= helper.actualizarDetalleAlumnosEvaluados2(notaFinal1, idDetalle1 );
                    helper.cerrar();
                    Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Ingrese una nota entre 0 y 10 ", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "La solicitud no Fue aprobada", Toast.LENGTH_SHORT).show();
        }



    }
}
