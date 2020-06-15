package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmAsignarNotaRepetido extends AppCompatActivity {
    Button asignarNota;

    String idRepetido;
    String notaAntesRepetido;
    EditText notaDespuesRepetido;
    EditText editNotaAntesRepetido;

    ControlBdGrupo12 helper = new ControlBdGrupo12(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_asignar_nota_repetido);

        Bundle bundle= getIntent().getExtras();
        idRepetido= bundle.getString("idRepetido");
        notaAntesRepetido= bundle.getString("notaAntesRepetido");

        asignarNota = (Button)findViewById(R.id.cambiarNotaRepetidoAprobar);
        notaDespuesRepetido = (EditText)findViewById(R.id.editNotaDespuesRepetido);
        editNotaAntesRepetido = (EditText)findViewById(R.id.editNotaAntesRepetido);

        editNotaAntesRepetido.setText(notaAntesRepetido);

        asignarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarNotaRepetido(v);
            }
        });
    }
    public void asignarNotaRepetido(View v){

        String notaDespuesR = notaDespuesRepetido.getText().toString();
        String notaAntesR = editNotaAntesRepetido.getText().toString();

        float notaDespuesR1 = Float.valueOf(notaDespuesR);
        int idDetalle1 = Integer.valueOf(idRepetido);
        float notaAntesR1 = Float.valueOf(notaAntesR);



        if (notaDespuesR.isEmpty()) {
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            if(notaDespuesR1 >= 0 && notaDespuesR1 <= 10){
                helper.abrir();
                String resultado= helper.actualizarNotaRepetido(notaDespuesR1, notaAntesR1, idDetalle1 );
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Ingrese una nota entre 0 y 10 ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
