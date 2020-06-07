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
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.Rol;

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
        if(position!=menu.size()){
            String nombreValue=activities[0];
            try{
                Class<?>
                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
