package sv.edu.ues.fia.eisi.pdm115.docente;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SegundaRevision;

public class AdmDarRevisionSegundaRevision extends AppCompatActivity {

    ControlBdGrupo12 helper;
    EditText notaInicial;
    EditText notaPrimeraRevision;
    EditText notaDespuesSegundaRevision;
    String idSegundaRevicion;
    String notaI;
    String PrimeraRevision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_dar_revision_segunda_revision);
        helper= new ControlBdGrupo12(this);
        notaInicial= findViewById(R.id.notaInicialEstudiante);
        notaPrimeraRevision=findViewById(R.id.notaConsultarPrimeraRevision);
        notaDespuesSegundaRevision= findViewById(R.id.notaAsignarSegundaRevision);
        Bundle bundle=getIntent().getExtras();
        idSegundaRevicion = bundle.getString("id");

         notaI= bundle.getString("notaInicial");
        PrimeraRevision= bundle.getString("notaPrimeraRevision");
        notaInicial.setText(notaI);
        notaPrimeraRevision.setText(PrimeraRevision);

        Toast.makeText(this, idSegundaRevicion,Toast.LENGTH_LONG).show();


    }
    public void darRevisionSegunda(View view){
        String nota= notaDespuesSegundaRevision.getText().toString();
        if(!nota.isEmpty()){
            if(Float.valueOf(nota) >= Float.valueOf(PrimeraRevision)){
                //nota valida
                SegundaRevision segundaRevision= new SegundaRevision();
                segundaRevision.setNotaDespuesSegundaRevision(nota);
                segundaRevision.setIdSegundaRevision(idSegundaRevicion);

                helper.abrir();
                //String resultado= helper.actualizar1R(segundaRevision);
                helper.cerrar();
                //Toast.makeText(this, resultado,Toast.LENGTH_LONG).show();

            }else{ Toast.makeText(this,"Ingrese nota valida. NOTA DESPUES de la revision debe ser MAYOR o al menos IGUAL a NOTA PRIMERA REVISION",Toast.LENGTH_LONG).show(); }
        }
        else{Toast.makeText(this, "Ingrese una nota en segunda revision",Toast.LENGTH_LONG).show(); }


    }
}
