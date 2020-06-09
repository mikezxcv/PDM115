package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmAprobarSolPrimeraRevision extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    TextView fechaRevison;
    TextView HoraRevison;
    TextView localRevison;
    TextView observaciones;
    String idPrimerRevision;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobar_sol_primera_revision);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton)findViewById(R.id.estadoDenegado);
        estadoAprobado= (RadioButton) findViewById(R.id.estadoAprobado);
        fechaRevison= (TextView) findViewById(R.id.fechaAsignarPrimeraRevision);
        HoraRevison=(TextView) findViewById(R.id.horaAsignarPrimeraRevision);
        localRevison= (TextView) findViewById(R.id.localAsignarPrimeraRevision);
        observaciones=(TextView) findViewById(R.id.observacionesAsignarPrimeraRevision);
        Bundle bundle=getIntent().getExtras();
        idPrimerRevision= bundle.getString("id");
        Toast.makeText(this,idPrimerRevision,Toast.LENGTH_LONG).show();


    }
    public void actualizarPrimeraRevision(View view){

        String fecha= fechaRevison.getText().toString();
        String hora = HoraRevison.getText().toString();
        String local=localRevison.getText().toString();
        String observacion= observaciones.getText().toString();


        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "seleccione un estado",Toast.LENGTH_LONG).show();
        }
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los capos",Toast.LENGTH_LONG).show();
            }
            else{
                 String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
            Toast.makeText(this, opcion,Toast.LENGTH_LONG).show();
            PrimeraRevision primeraRevision= new PrimeraRevision();

            primeraRevision.setEstadoPrimeraRevision(opcion);
            primeraRevision.setFechaPrimeraRevision(fecha);
            primeraRevision.setHoraPrimerarevision(hora);
            primeraRevision.setIdLocal(local);
            primeraRevision.setObservacionesPrimeraRevision(observacion);

            //pasarle el id de primerrevision obtendio a  travez del intent
            primeraRevision.setIdPrimeraRevision(idPrimerRevision);
            helper.abrir();
            String resultado= helper.actualizar(primeraRevision);
            helper.cerrar();
            Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();
            }




        }


    }
}
