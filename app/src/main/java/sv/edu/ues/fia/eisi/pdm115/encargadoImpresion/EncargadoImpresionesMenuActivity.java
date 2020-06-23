package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.R;

public class EncargadoImpresionesMenuActivity extends AppCompatActivity {
    String[] menu={"Administrar solicitudes de impresión", "Administrar Encargados de Impresiones","Administrar Motivos"};
    String[] actividades ={"VerSolicitudesImpresionActivity","GestionarEncargadosActivity","GestionarMotivosActivity"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_impresiones_menu);
        this.setTitle("PDM115: Impresiones");

        listView = findViewById(R.id.listViewMenuEncargado);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EncargadoImpresionesMenuActivity.this,
                android.R.layout.simple_list_item_1, menu);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion."+actividades[position]);
                    Intent inte = new Intent(EncargadoImpresionesMenuActivity.this,clase);
                    startActivity(inte);
                }catch(ClassNotFoundException e){ e.printStackTrace();
                }
            }
        });
    }
}
