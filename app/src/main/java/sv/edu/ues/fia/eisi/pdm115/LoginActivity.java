package sv.edu.ues.fia.eisi.pdm115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText usuario;
    EditText password;
    Switch remeberData;
    ControlBdGrupo12 helper;
    Button ingresar;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper= new ControlBdGrupo12(this);
        usuario= (EditText) findViewById(R.id.editTextUser);
        password=(EditText) findViewById(R.id.editTextPassword);
        remeberData= (Switch) findViewById(R.id.switchRemember);
        ingresar= (Button) findViewById(R.id.buttonIngresar);
        prefs=getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredencial();
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new ControlBdGrupo12(LoginActivity.this);
                String user= usuario.getText().toString();
                String pass= password.getText().toString();

                if(validarEntrada(user,pass)){
                   goToMain(user);
                   saveOnPreferences(user,pass);
                    }
            }
        });


    }
    private boolean validarEntrada(String user,String password){
        if(!validarCorreo(user)){
            Toast.makeText(this,"Usuario No encontrado, intente de nuevo.",Toast.LENGTH_SHORT).show();
            return false;
        } else if(!validarContrasena(user,password)){
            Toast.makeText(this,"Contraseña No encontrado, intente de nuevo.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
    private boolean validarCorreo(String user){
        helper.abrir();
       return !TextUtils.isEmpty(user)  && helper.verificarUsuario(user);

    }
    private boolean validarContrasena(String user,String password){
        helper.abrir();
    return !TextUtils.isEmpty(password) && helper.verificarPassword(user,password);
    }
    private void goToMain(String user){

        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("usuario",user);
        //Necesario para asignar el IDDOCENTE en las solicitudes (y otros cruds)
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString("usuarioActual", user);
        editor.apply();
        startActivity(intent);
    }
    private void saveOnPreferences(String user, String pasword){
        SharedPreferences.Editor editor= prefs.edit();
        if(remeberData.isChecked()){
            editor.putString("user",user);
            editor.putString("password",pasword);
            editor.apply();
        }
    }
    private void setCredencial(){
        String email; String pass;
        email=prefs.getString("user","");
        pass=prefs.getString("password","");
        usuario.setText(email);
        password.setText(pass);

    }
}
