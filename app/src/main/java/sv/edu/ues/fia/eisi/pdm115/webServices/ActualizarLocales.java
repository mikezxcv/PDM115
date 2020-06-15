package sv.edu.ues.fia.eisi.pdm115.webServices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.ControladorServicio;
import sv.edu.ues.fia.eisi.pdm115.R;
@SuppressLint("NewApi")
public class ActualizarLocales extends AppCompatActivity {
    private  final String urlServidorEisi="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarLocales.php";
    ListView listViewLocales;
    List<String> listaLocales;
    ControlBdGrupo12 helper;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_locales);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listViewLocales= findViewById(R.id.listViewLocales);
        listaLocales= new ArrayList<String>();
    }
    public void obtenerLocales(View view){
        String datos= ControladorServicio.obtenerRespuestaPeticion(urlServidorEisi,this);
        try {
            JSONArray jsonArray= new JSONArray(datos);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                listaLocales.add(jsonObject.getString("NOMBRELOCAL")+" "+jsonObject.getString("UBICACION"));
            }
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaLocales);
            listViewLocales.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public  void guardar(View view){
        helper.abrir();
        helper.cerrar();
    }
}
