package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EncargadoImpresionesMenuActivity extends ListActivity {
    String[] menu={"Administrar solicitudes de impresión", "Administrar Encargados de Impresiones","Administrar Motivos"};
    String[] actividades ={"VerSolicitudesImpresionActivity","GestionarEncargadosActivity","GestionarMotivosActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

            try{
                Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion."+actividades[position]);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){ e.printStackTrace();
            }
    }
}
