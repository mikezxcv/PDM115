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
        BDhelper = new ControlBdGrupo12(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position!=3){
            String nombreValue=activities[position];
            if(position==0){


                try{
                    Class<?>
                            clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.estudiante."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
            if(position==1){


                try{
                    Class<?>
                            clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.docente."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
            if(position==2){


                try{
                    Class<?>
                            clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }

            }


        }else{
            BDhelper.abrir();
            String mensaje =BDhelper.llenarBDCarnet();
            BDhelper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

    }

}

