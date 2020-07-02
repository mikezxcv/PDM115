package sv.edu.ues.fia.eisi.pdm115.mapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.R;

public class MenuMapsActivity extends AppCompatActivity {
    ListView menuMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maps);
        String[] menu={"Mostrar Ubicaciones (MAPA)","Coordenads Geograficas (Codigo QR)"};
         menuMapa = (ListView) findViewById(R.id.listViewMenuMapa);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        menuMapa.setAdapter(arrayAdapter);
        menuMapa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(position==0){
                    Intent intent= new Intent(getApplicationContext(),MapsActivity2.class );
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent= new Intent(getApplicationContext(),CoordenadasGeograficasQR.class );
                    startActivity(intent);
                }
            }
        });
    }
}
