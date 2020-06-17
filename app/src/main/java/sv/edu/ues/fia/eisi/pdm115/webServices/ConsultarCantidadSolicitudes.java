package sv.edu.ues.fia.eisi.pdm115.webServices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControladorServicio;
import sv.edu.ues.fia.eisi.pdm115.R;
@SuppressLint("NewApi")
public class ConsultarCantidadSolicitudes extends AppCompatActivity {
    public static String urlRepetido="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarCantidadRepetidos.php";
    public static String urlDiferido="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarCantidadDiferidos.php";
    public static String urlPrimeraRevision="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarCantidadPrimeraRevision.php";
    public static String urlSegundaRevision="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/consultarCantidadSegundaRevision.php";
    EditText editTextCarnet;
    List<String> resultado;
    ListView listViewDatos;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cantidad_solicitudes);
        listViewDatos= findViewById(R.id.listViewCantidad);
        resultado= new ArrayList<String>();
        editTextCarnet= findViewById(R.id.carnet);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
    public void diferidos(View view){

        if(!TextUtils.isEmpty(editTextCarnet.getText().toString())){
            String request="?carnet="+editTextCarnet.getText().toString();
            String datos= ControladorServicio.obtenerRespuestaPeticion(urlDiferido+request,this);
            try {
                JSONArray jsonArray= new JSONArray(datos);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    resultado.add("Nº SOLICITUDES DIFERIDOS "+editTextCarnet.getText().toString()+" : "+jsonObject.getString("resultado"));
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, resultado);
                listViewDatos.setAdapter(arrayAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Ingrese un valor", Toast.LENGTH_LONG).show();

       /*
       */
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(resultado);
        resultado.clear();
        resultado.addAll(hashSet);
    }
    public void repetidos(View view){

        if(!TextUtils.isEmpty(editTextCarnet.getText().toString())){
            String request="?carnet="+editTextCarnet.getText().toString();
            String datos= ControladorServicio.obtenerRespuestaPeticion(urlRepetido+request,this);
            try {
                JSONArray jsonArray= new JSONArray(datos);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    resultado.add("Nº SOLICITUDES REPETIDOS "+editTextCarnet.getText().toString()+" : "+jsonObject.getString("resultado"));
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, resultado);
                listViewDatos.setAdapter(arrayAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Ingrese un valor", Toast.LENGTH_LONG).show();
       /*
       */
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(resultado);
        resultado.clear();
        resultado.addAll(hashSet);
    }
    public void primeraRevision(View view){

        if(!TextUtils.isEmpty(editTextCarnet.getText().toString())){
            String request="?carnet="+editTextCarnet.getText().toString();
            String datos= ControladorServicio.obtenerRespuestaPeticion(urlPrimeraRevision+request,this);
            try {
                JSONArray jsonArray= new JSONArray(datos);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    resultado.add("Nº SOLICITUDES PRIMERA REVISION "+editTextCarnet.getText().toString()+" : "+jsonObject.getString("resultado"));
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, resultado);
                listViewDatos.setAdapter(arrayAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Ingrese un valor", Toast.LENGTH_LONG).show();

       /*

       */
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(resultado);
        resultado.clear();
        resultado.addAll(hashSet);
    }
    public void segundaRevision(View view){


        if(!TextUtils.isEmpty(editTextCarnet.getText().toString())){
            String request="?carnet="+editTextCarnet.getText().toString();
            String datos= ControladorServicio.obtenerRespuestaPeticion(urlSegundaRevision+request,this);
            try {
                JSONArray jsonArray= new JSONArray(datos);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    resultado.add("Nº SOLICITUDES SEGUNDA REVISION "+editTextCarnet.getText().toString()+" : "+jsonObject.getString("resultado"));
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, resultado);
                listViewDatos.setAdapter(arrayAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Ingrese un valor", Toast.LENGTH_LONG).show();
       /*
       */
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(resultado);
        resultado.clear();
        resultado.addAll(hashSet);
    }
    public void limpiarDatos(View view){
        recreate();
    }

}
