package sv.edu.ues.fia.eisi.pdm115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    ControlBdGrupo12 BDhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BDhelper = new ControlBdGrupo12(this);
        BDhelper.abrir();
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Intent login= new Intent(this,LoginActivity.class);
        Intent main= new Intent(this,MainActivity.class);
        if(!TextUtils.isEmpty(prefs.getString("user","")) && !TextUtils.isEmpty(prefs.getString("password",""))){
            startActivity(main);
        }else{
            startActivity(login);
        }
        finish();
    }
}
