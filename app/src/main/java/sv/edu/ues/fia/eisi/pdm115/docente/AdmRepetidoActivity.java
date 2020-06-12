package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmRepetidoActivity extends ListActivity {

    String[] activities={"AdmDetallesolicitudRepetido"};

    //campos a mostrar
    String [] idDiferidos;
    String [] carnet;
    String [] nombre;
    String [] materias;
    String [] tipoEvaluacion;

    String [] opciones = {"Solicitud 1 [CARNET - MATERIA]", "Solicitud 1 [CARNET - MATERIA]", "Solicitud 1 [CARNET - MATERIA]"};
    String cantidad;
    ControlBdGrupo12 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper= new ControlBdGrupo12(this);

        //llenar();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, opciones));
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
                startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }


}
