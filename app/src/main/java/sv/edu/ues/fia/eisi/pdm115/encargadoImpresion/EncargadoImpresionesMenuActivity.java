package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.fia.eisi.pdm115.R;

public class EncargadoImpresionesMenuActivity extends AppCompatActivity {

    String[] actividades ={"VerSolicitudesImpresionActivity","GestionarMotivosActivity"};
    String[] actividadesAdmin ={"VerSolicitudesImpresionActivity","GestionarEncargadosActivity","GestionarMotivosActivity"};
    private SharedPreferences prefs;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_impresiones_menu);

        String[] menu={getString(R.string.AdministrarsolicitudesimpresionIMP),getString(R.string.AdministrarMotivosIMP)};
        String[] menuAdmin={getString(R.string.AdministrarsolicitudesimpresionIMP),getString(R.string.AdministrarEncargadosImpresionesIMP), getString(R.string.AdministrarMotivosIMP)};

        this.setTitle("PDM115: Impresiones");
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String usuarioActual = prefs.getString("usuarioActual", "");
        listView = findViewById(R.id.listViewMenuEncargado);
        if (usuarioActual.contentEquals("ADMIN")) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EncargadoImpresionesMenuActivity.this,
                android.R.layout.simple_list_item_1, menuAdmin);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion." + actividadesAdmin[position]);
                    Intent inte = new Intent(EncargadoImpresionesMenuActivity.this, clase);
                    startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EncargadoImpresionesMenuActivity.this,
                    android.R.layout.simple_list_item_1, menu);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.pdm115.encargadoImpresion." + actividades[position]);
                        Intent inte = new Intent(EncargadoImpresionesMenuActivity.this, clase);
                        startActivity(inte);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
