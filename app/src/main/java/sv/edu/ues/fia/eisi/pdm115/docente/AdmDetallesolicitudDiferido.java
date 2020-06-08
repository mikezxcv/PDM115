package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmDetallesolicitudDiferido extends AppCompatActivity {
    Button boton;
    EditText editTextCarnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_detallesolicitud_diferido);
        Bundle bundle = getIntent().getExtras();
        //String position = bundle.getString("index");


        editTextCarnet = (EditText)findViewById(R.id.editCarnet);
        boton = (Button) findViewById(R.id.irAprobarSolicitudDiferido);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmDetallesolicitudDiferido.this, AdmAprobarsolicitudDiferido.class));
            }
        });

        if(bundle != null && bundle.getString("index") != null){
            String index = bundle.getString("index");
            Toast.makeText(this,  index, Toast.LENGTH_SHORT).show();
            //editTextCarnet.setText(index);
            //int positionInt = Integer.parseInt(index);
            //ControlBdGrupo12 BDhelper = new ControlBdGrupo12(this);
            //List<String> menu  = BDhelper.llenar_listaCompleta(2);
            //String[] menuLista = menu.toArray(new String[menu.size()]);
            //editTextCarnet.setText(menuLista[0]);

        }else{
            Toast.makeText(this, "Index Vacio...", Toast.LENGTH_SHORT).show();
        }

    }
}
