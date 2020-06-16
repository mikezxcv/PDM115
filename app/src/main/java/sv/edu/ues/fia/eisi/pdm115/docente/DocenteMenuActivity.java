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

public class DocenteMenuActivity extends ListActivity {
    String[] menu={"Administrar Primera Revision","Administrar Segunda Revision","Administrar Diferiddos ","Administrar Repetidos ","Administrar Impresiones","Gestionar Locales  de Revision", "Gestionar ROLES de Docentes"};
    String[] activities={"AdmPrimeraRevisionActivity","AdmSegundaRevisionActivity","AdmDiferidoActivity","AdmRepetidoActivity","","AdmLocalActivity", "AdmRolActivity"};
    //ControlBdGrupo12 BDhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
        // BDhelper = new ControlBDGrupo12(this);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position!=7){

            String nombreValue=activities[position];
            String clases=("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
            Toast.makeText(getApplicationContext(),clases,Toast.LENGTH_LONG).show();


            try{
                Class<?>
                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);

                Intent inte = new Intent(this,clase);

                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }else{
          /*  BDhelper.abrir();
            String tost=BDhelper.llenarBDCarnet();
            BDhelper.cerrar(); */
            Toast.makeText(this, "falta implementacion", Toast.LENGTH_SHORT).show();

        }
    }
}
