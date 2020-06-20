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
public class consultarUsuarios extends AppCompatActivity {
    ListView listView;
    List<String> USUARIOS;
    List<String> PASSWORD;
    List<String> listaUsuarios;
    private static String urlUsuarios="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarUsuarios.php";
    ControlBdGrupo12 helper;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        USUARIOS= new ArrayList<String>();
        PASSWORD= new ArrayList<String>();
        listaUsuarios= new ArrayList<String>();
        listView= findViewById(R.id.listViewUsuarios);
        helper=new ControlBdGrupo12(this);

    }

    public  void obtenerUsuarios(View view){
        String datos= ControladorServicio.obtenerRespuestaPeticion(urlUsuarios,this);
        try {
            JSONArray jsonArray= new JSONArray(datos);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                USUARIOS.add(jsonObject.getString("usuario"));
                PASSWORD.add(jsonObject.getString("contrasena"));


                listaUsuarios.add("usuario:"+USUARIOS.get(i)+"-"+"contraseña:" +PASSWORD.get(i));
            }
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaUsuarios);
            listView.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(listaUsuarios);
        listaUsuarios.clear();
        listaUsuarios.addAll(hashSet);
    }

   /* public void guardar(View view){

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
    }*/
}

