package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmSegundaRevisionActivity extends ListActivity {
    String[] menu={"Solicitud uno","solicitud 2","solicitud 3"};
    int cantidad= menu.length;
    String [] opciones;

    String[] activities={"AdmDetallesolicitudSegundaRevision"};
    ControlBdGrupo12 BDhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        llenarOpciones();

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, opciones));

        // BDhelper = new ControlBDGrupo12(this);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(AdmSegundaRevisionActivity.this, AdmDetallesolicitudSegundaRevision.class);
        startActivity(intent);


    }
    protected void llenarOpciones(){
        opciones= new String[3];
        for(int i=0; i< menu.length; i++){
            opciones[i]= "solicitud "+i;
        }
    }
}
