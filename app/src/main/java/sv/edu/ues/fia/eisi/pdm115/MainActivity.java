package sv.edu.ues.fia.eisi.pdm115;



import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    String[] menu={"Estudiante","Docente","Encargado de Impresion","LLenar Base de Datos"};
    String[]
            activities={"EstudianteMenuActivity","DocenteMenuActivity","EncargadoImpresionesMenuActivity"};
    ControlBdGrupo12 BDhelper;

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
        if(position!=3){

            String nombreValue=activities[position];

            try{
                Class<?>
                        clase=Class.forName("package sv.edu.ues.fia.eisi.pdm115."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            /*BDhelper.abrir();
            String tost=BDhelper.llenarBDCarnet();
            BDhelper.cerrar(); */
            Toast.makeText(this, "FALTA IMPLEMENTACION", Toast.LENGTH_SHORT).show();
        }
    }

}

