package sv.edu.ues.fia.eisi.pdm115.mapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.R;

public class CoordenadasGeograficasQR extends AppCompatActivity {
    EditText latitud;
    EditText longitud;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordenadas_geograficas_q_r);
        latitud= findViewById(R.id.editLongitud);
        longitud= findViewById(R.id.editLatitud);
        button= findViewById(R.id.goToMap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(latitud.getText().toString()) &&  TextUtils.isEmpty(longitud.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Sin Coordendas Geograficas Encontradas", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent= new Intent(getApplicationContext(),CoordenadasGeograficasQrMapa.class );
                    intent.putExtra("latitud",latitud.getText().toString());
                    intent.putExtra("longitud",longitud.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
