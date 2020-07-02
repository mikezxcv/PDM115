package sv.edu.ues.fia.eisi.pdm115.mapa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.Docente;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.qr.EscanerActivity;

public class CoordenadasGeograficasQR extends AppCompatActivity {
    EditText latitud;
    EditText longitud;
    Button button, escanearBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordenadas_geograficas_q_r);
        latitud= findViewById(R.id.editLongitud);
        longitud= findViewById(R.id.editLatitud);
        button= findViewById(R.id.goToMap);
        escanearBtn = findViewById(R.id.escanearQR);

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

        escanearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoordenadasGeograficasQR.this, EscanerActivity.class);
                startActivityForResult(intent,1);
                setResult(Activity.RESULT_OK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == Activity.RESULT_OK && data!=null){
            if (data.hasExtra("latitud") && data.hasExtra("longitud")){
                String lat = Double.toString(data.getDoubleExtra("latitud",-1));
                String lng = Double.toString(data.getDoubleExtra("longitud",-1));
                latitud.setText(lat);
                longitud.setText(lng);
            } else {
                Toast.makeText(this, "No se recibieron los datos", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
        }

    }
}
