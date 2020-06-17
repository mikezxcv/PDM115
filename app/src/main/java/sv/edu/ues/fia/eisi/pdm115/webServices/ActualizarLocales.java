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
import java.util.HashSet;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.ControladorServicio;
import sv.edu.ues.fia.eisi.pdm115.R;
@SuppressLint("NewApi")
public class ActualizarLocales extends AppCompatActivity {
    private  final String urlServidorEisi="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarLocales.php";
    ListView listViewLocales;
    List<String> localNombre;
    List<String> localUbicacion;
    List<String> locales;
    ControlBdGrupo12 helper;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_locales);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper= new ControlBdGrupo12(this);
        listViewLocales= findViewById(R.id.listViewLocales);
        localNombre = new ArrayList<String>();
        localUbicacion= new ArrayList<String>();
        locales= new ArrayList<String>();
    }
    public void obtenerLocales(View view){


        String datos= ControladorServicio.obtenerRespuestaPeticion(urlServidorEisi,this);
        try {
            JSONArray jsonArray= new JSONArray(datos);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                localNombre.add(jsonObject.getString("NOMBRELOCAL"));
                localUbicacion.add(jsonObject.getString("UBICACION"));
                locales.add(localNombre.get(i)+"--"+localUbicacion.get(i));
            }
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, locales);
            listViewLocales.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(locales);
        locales.clear();
        locales.addAll(hashSet);


    }
    public  void guardar(View view){
        recreate();
        helper.abrir();
        for(int i=0; i<localNombre.size();i++){

            helper.guardarLocalesExterno(localNombre.get(i),localUbicacion.get(i));

        }
        helper.cerrar();
        localNombre.clear();
        localUbicacion.clear();

    }
}
