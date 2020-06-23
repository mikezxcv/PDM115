package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.EncargadoDeImpresiones;
import sv.edu.ues.fia.eisi.pdm115.Escuela;
import sv.edu.ues.fia.eisi.pdm115.R;

public class NuevoEncargadoActivity extends AppCompatActivity {
    EditText txtEscuela, txtNombre, txtApellido, txtUsuario, txtPass, txtpass2;
    String usuario, nombreUsuario, idEscuela;
    Button guardar, cancelar, eliminar, actualizar;
    ControlBdGrupo12 helper;
    ArrayList<Escuela> escuelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_encargado);
        this.setTitle("Registrar nuevo encargado");

        txtEscuela = findViewById(R.id.editEscuelaEnc);
        txtNombre = findViewById(R.id.editNombreEnc);
        txtApellido = findViewById(R.id.editApellidoEnc);
        txtUsuario = findViewById(R.id.usuarioEncargado);
        txtPass = findViewById(R.id.passUsuario);
        txtpass2 = findViewById(R.id.confirmarPass);
        guardar = findViewById(R.id.guardarCambios);
        cancelar = findViewById(R.id.cancelar);

        helper = new ControlBdGrupo12(this);

        helper.abrir();
        escuelas = helper.consultarEscuelas();
        int ultimoUsuario = helper.obtenerUsuariosEncargados().length;
        helper.cerrar();
        usuario = "IMPRESIONADMIN"+(ultimoUsuario+1);
        nombreUsuario = "ADMIN IMPRESION "+(ultimoUsuario+1);
        txtUsuario.setText(usuario);
        txtEscuela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] opcionesEscuela = new String[escuelas.size()];
                for (int i=0; i<escuelas.size(); i++){
                    opcionesEscuela[i] = escuelas.get(i).getNombreEscuela();
                }
                AlertDialog.Builder seleccion =
                        new AlertDialog.Builder(NuevoEncargadoActivity.this);
                seleccion.setTitle("Seleccione una escuela:");
                seleccion.setItems(opcionesEscuela,
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int item) {
                                txtEscuela.setText(opcionesEscuela[item]);
                                idEscuela = escuelas.get(item).getIdEscuela();
                            }
                        });
                seleccion.show();
            }
        });
    }

    public void guardar(View view) {

        if ((txtNombre.getText().toString().isEmpty() ||
            txtApellido.getText().toString().isEmpty() ||
            txtPass.getText().toString().isEmpty() ||
            txtpass2.getText().toString().isEmpty())){

            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            if (!txtPass.getText().toString().equals(txtpass2.getText().toString())){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
            else{
                String pass = txtPass.getText().toString();
                helper.abrir();
                String resultadoUsuario = helper.crearUsuarioEncargado(usuario, nombreUsuario, pass);

                if (!resultadoUsuario.equals("Error")){
                    EncargadoDeImpresiones registro = new EncargadoDeImpresiones();
                    registro.setIdEscuela(idEscuela);
                    registro.setNombreEncargado(txtNombre.getText().toString());
                    registro.setApellidoEncargado(txtApellido.getText().toString());
                    registro.setUsuario(usuario);
                    String resultadoEncargado = helper.insertarEncargado(registro);
                    if(!resultadoEncargado.equals("Error")){
                        Toast.makeText(this, resultadoEncargado,Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(this, "Error: al registrar al nuevo encargado",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Error: al registrar el usuario",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void cancelar(View view) {
        finish();
    }
}
