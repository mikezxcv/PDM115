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
public class ActualizarEstudiantes extends AppCompatActivity {
    ListView listView;
    List<String> carnet;
    List<String> nombre;
    List<String> apellido;
    List<String> carrera;
    List<String> listaEstudiantes;
    private static String urlEstudiantes="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarEstudiantes.php";
    ControlBdGrupo12 helper;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_estudiantes);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnet= new ArrayList<String>();
        nombre= new ArrayList<String>();
        apellido= new ArrayList<String>();
        carrera= new ArrayList<String>();
        listaEstudiantes= new ArrayList<String>();
        listView= findViewById(R.id.listViewEstdiantes);
        helper=new ControlBdGrupo12(this);

    }

    public  void obtenerEstudiantes(View view){
          String datos= ControladorServicio.obtenerRespuestaPeticion(urlEstudiantes,this);
        try {
            JSONArray jsonArray= new JSONArray(datos);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                carnet.add(jsonObject.getString("CARNET"));
                nombre.add(jsonObject.getString("NOMBREESTUDIANTE"));
                apellido.add(jsonObject.getString("APELLIDOESTUDIANTE"));
                carrera.add(jsonObject.getString("CARRERA"));

                listaEstudiantes.add(carnet.get(i)+"-" +nombre.get(i)+"-" +apellido.get(i)+"-" +carrera.get(i));
            }
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaEstudiantes);
            listView.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(listaEstudiantes);
        listaEstudiantes.clear();
        listaEstudiantes.addAll(hashSet);
    }

    public void guardar(View view){

        helper.abrir();
        for(int i=0; i<nombre.size();i++){
            helper.guardarEstudianteExterno(carnet.get(i),nombre.get(i),apellido.get(i),carrera.get(i));
        }
        helper.cerrar();
        carnet.clear();
        nombre.clear();
        apellido.clear();
        carrera.clear();
        recreate();
    }
}
