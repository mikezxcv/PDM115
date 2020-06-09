package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDarRevisionPrimeraRevision extends AppCompatActivity {
    ControlBdGrupo12 helper;
    TextView notaAntes;
    TextView notaDespues;
    String idprimerRevision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_dar_revision_primera_revision);
        helper= new ControlBdGrupo12(this);
        notaAntes= (TextView) findViewById(R.id.notaAntesAsignarPrimeraRevision);
        notaDespues=(TextView) findViewById(R.id.notaDespuesAsignarPrimeraRevision);
        Bundle bundle=getIntent().getExtras();
        idprimerRevision= bundle.getString("id");
        Toast.makeText(this,idprimerRevision,Toast.LENGTH_LONG).show();
    }
    public void darRevision(View view){
        String notaantes= notaAntes.getText().toString();
        String notadespues= notaDespues.getText().toString();
        if(notaantes.isEmpty() || notadespues.isEmpty()){
            Toast.makeText(this,"rellene todos los campos",Toast.LENGTH_LONG).show();
        }
        else{
            if(Float.valueOf(notadespues)>=Float.valueOf(notaantes)){
                //nota valida
                PrimeraRevision primeraRevision= new PrimeraRevision();


                primeraRevision.setNotaAntesPrimeraRevision(notaantes);
                primeraRevision.setNotaDespuesPrimeraRevision(notadespues);
                primeraRevision.setIdPrimeraRevision(idprimerRevision);
                helper.abrir();
                String resultado= helper.actualizar(primeraRevision);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this,"Ingrese nota valida. NOTA DESPUES de la revision debe ser MAYOR o al menos IGUAL a NOTA ORIGINAL",Toast.LENGTH_LONG).show();

            }

        }
    /*   */


    }
}
