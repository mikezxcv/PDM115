package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmRepetidoActivity extends ListActivity {

    //String[] menuX={"Solicitud 1 Repetido"};
    //String[] activities={"AdmDetallesolicitudRepetido"};

    ListView lv ;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_lista);
        lv = (ListView)findViewById(R.id.lista);
        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(getApplicationContext());
        //lista = BDhelper.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adaptador);
    }
}
