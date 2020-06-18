package sv.edu.ues.fia.eisi.pdm115.webServices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import sv.edu.ues.fia.eisi.pdm115.ControladorServicio;
import sv.edu.ues.fia.eisi.pdm115.R;

public class IngresarMateriaActivity extends Activity {
    EditText codigoTxt;
    EditText nombreTxt;

    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi12/GRUPO12/insertarMateria.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_materia);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        codigoTxt = (EditText) findViewById(R.id.editText_codigo);
        nombreTxt = (EditText) findViewById(R.id.editText_nombre);
    }
    public void insertarMateria(View v) {
        String codigo = codigoTxt.getText().toString();
        String nombre= nombreTxt.getText().toString();
        String url = null;
        JSONObject datosMateria = new JSONObject();
        JSONObject materia = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_materiaPublicoUES:
                url = urlPublicoUES+ "?codigomateria=" + codigo + "&nombremateria="
                        + nombre;
                ControladorServicio.insertarMateriaEISI(url, this);
                break;
        }
    }
}


