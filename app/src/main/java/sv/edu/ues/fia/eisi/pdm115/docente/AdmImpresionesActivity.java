package sv.edu.ues.fia.eisi.pdm115.docente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmImpresionesActivity extends ListActivity {
    String[] menu={"Solicitar Impresión","Consultar solicitudes de impresión"};
    String[] activities={"AdmSolicitarImpresionActivity","AdmConsultarSolImpresionesActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String actividadInte = activities[position];

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+actividadInte);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
