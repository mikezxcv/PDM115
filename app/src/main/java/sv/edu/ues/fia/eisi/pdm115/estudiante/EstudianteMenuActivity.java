package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteMenuActivity extends ListActivity {

    String[] activities={"EstudiantePrimeraRevisionActivity","EstudianteSegundaRevisionActivity","EstudianteDiferidoActivity", "EstudianteRepetidoActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menu={getString(R.string.PrimeraRevisionESTU),getString(R.string.SegundaRevisionESTU),getString(R.string.DiferidoESTU), getString(R.string.RepetidoESTU)};

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        if(position!=5){

            String nombreValue=activities[position];

            try{
                Class<?>
                        clase=Class.forName("sv.edu.ues.fia.eisi.pdm115.estudiante."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
          /* BDhelper.abrir();
            String tost=BDhelper.llenarBDCarnet();
            BDhelper.cerrar(); */
            Toast.makeText(this, "falta implementacion", Toast.LENGTH_SHORT).show();
        }
    }
}
