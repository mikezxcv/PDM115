package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.PrimeraRevision;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.SolicitudDiferidoTabla;

public class AdmAprobarsolicitudDiferido extends AppCompatActivity {
    RadioButton estadoAprobado;
    RadioButton estadoDenegado;
    TextView fechaDiferido;
    TextView HoraDiferido;
    TextView localDiferido;
    TextView observaciones;
    String idDiferido;
    ControlBdGrupo12 helper;
    final static String DENEGADO="DENEGADO";
    final static String APROBADO="APROBADO";

    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_aprobarsolicitud_diferido);
        helper= new ControlBdGrupo12(this);
        estadoDenegado= (RadioButton)findViewById(R.id.radioRechazadoAprobar);
        estadoAprobado= (RadioButton) findViewById(R.id.radioAprobadoAprobar);
        fechaDiferido= (TextView) findViewById(R.id.editfechaDiferidoAprobar);
        HoraDiferido=(TextView) findViewById(R.id.editHoraAprobar);
        localDiferido= (TextView) findViewById(R.id.editLocalAprobar);
        observaciones=(TextView) findViewById(R.id.editObservacionesAprobar);
        guardar = (Button)findViewById(R.id.guardarDiferidoAprobar);

        Bundle bundle=getIntent().getExtras();
        idDiferido= bundle.getString("idDiferido");
        Toast.makeText(this,idDiferido,Toast.LENGTH_LONG).show();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDiferido(v);
            }
        });

    }
    public void actualizarDiferido(View view){

        String fecha= fechaDiferido.getText().toString();
        String hora = HoraDiferido.getText().toString();
        String local=localDiferido.getText().toString();
        String observacion= observaciones.getText().toString();


        if(!estadoAprobado.isChecked() && !estadoDenegado.isChecked()){
            Toast.makeText(this, "Seleccione un estado",Toast.LENGTH_LONG).show();

        }
        else{
            if(fecha.isEmpty()||hora.isEmpty()||local.isEmpty()||observacion.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos",Toast.LENGTH_LONG).show();

            }
            else{
                String opcion= (estadoAprobado.isChecked())?  APROBADO:DENEGADO;
                Toast.makeText(this, opcion,Toast.LENGTH_LONG).show();

                SolicitudDiferidoTabla solicitudDiferido = new SolicitudDiferidoTabla();

                solicitudDiferido.setESTADODIFERIDO(opcion);
                solicitudDiferido.setFECHADIFERIDO(fecha);
                solicitudDiferido.setHORADIFERIDO(hora);
                solicitudDiferido.setLOCALDIFERIDO(local);
                solicitudDiferido.setOBSERVACIONESDIFERIDO(observacion);

                //pasarle el id de primerrevision obtendio a  travez del intent
                solicitudDiferido.setIDDIFERIDO(idDiferido);
                helper.abrir();
                String resultado= helper.actualizar(solicitudDiferido);
                helper.cerrar();
                Toast.makeText(this,resultado,Toast.LENGTH_LONG).show();


            }
        }
    }
}
