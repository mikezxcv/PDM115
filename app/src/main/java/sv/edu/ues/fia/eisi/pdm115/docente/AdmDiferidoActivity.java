package sv.edu.ues.fia.eisi.pdm115.docente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

public class AdmDiferidoActivity extends ListActivity {

    String[] activities={"AdmDetallesolicitudDiferido"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(this);
        List<String> menu  = BDhelper.llenar_lv();
        String[] menuLista = menu.toArray(new String[menu.size()]);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuLista));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ControlBdGrupo12 BDhelper = new ControlBdGrupo12(this);
        List<String> menu  = BDhelper.llenar_lv();
        String[] menuLista = menu.toArray(new String[menu.size()]);
        if(position!=menu.size()){
            String nombreValue=activities[0];
            try{
                //String positionInt = String.valueOf(position);
                Class<?> clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
                Intent intent = new Intent(this,clase);
                intent.putExtra("index", String.valueOf(menuLista[position]));
                this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
