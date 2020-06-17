package sv.edu.ues.fia.eisi.pdm115.webServices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sv.edu.ues.fia.eisi.pdm115.ControladorServicio;
import sv.edu.ues.fia.eisi.pdm115.R;
@SuppressLint("NewApi")
public class EliminarEstudiante extends AppCompatActivity {
    EditText editTextCarnet;
    private static String urlEliminarEstudiante="https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/eliminarEstudiante.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_estudiante);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        editTextCarnet= findViewById(R.id.carnetEliminar);
    }
    public void eliminarEstudiante(View view){

        if(!TextUtils.isEmpty(editTextCarnet.getText().toString())){
            String paramer="?carnet="+editTextCarnet.getText().toString();
            String datos= ControladorServicio.obtenerRespuestaPeticion(urlEliminarEstudiante+paramer,this);

            try {
                JSONArray jsonArray= new JSONArray(datos);
                JSONObject jsonObject= jsonArray.getJSONObject(0);
                String respuesta=jsonObject.getString("resultado");
                if(Integer.valueOf(respuesta)==1){
                    Toast.makeText(this,"Registro Eliminado",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Elemento no existe en servidor EISI",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this,respuesta,Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Ingrese un valor", Toast.LENGTH_LONG).show();
        }
    }

    public void verEstudiantes(View view){
        Intent intent= new Intent(this,ActualizarEstudiantes.class);
        startActivity(intent);
    }
}
