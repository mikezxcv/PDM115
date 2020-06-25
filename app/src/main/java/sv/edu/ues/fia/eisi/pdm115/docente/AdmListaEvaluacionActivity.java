package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmListaEvaluacionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper;
    ListView listView;

    String [] datos;
    String [] idEvaluacion;
    FloatingActionButton boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_lista_evaluacion);
        listView= (ListView) findViewById(R.id.listViewEvaluaciones);
        boton= (FloatingActionButton) findViewById(R.id.botonEmergenteEvaluaciones);
        helper= new ControlBdGrupo12(this);
        helper.abrir();
        datos= helper.obtenerDetalleEvaluaciones();
        idEvaluacion=helper.IDDetalleEvaluaciones();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent= new Intent(AdmListaEvaluacionActivity.this,AdmDetalleEvaluacionActivity.class);
                intent.putExtra("id",idEvaluacion[position]);
                Toast.makeText(AdmListaEvaluacionActivity.this, idEvaluacion[position], Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
