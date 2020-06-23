package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.EncargadoDeImpresiones;
import sv.edu.ues.fia.eisi.pdm115.R;

public class DetalleEncargadoActivity extends AppCompatActivity {
    EditText txtEscuela, txtNombre, txtApellido, txtUsuario, txtPass;
    String idEscuela;
    Button guardar, cancelar, actualizar, eliminar;
    CheckBox checkBox;
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    EncargadoDeImpresiones encargado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_encargado);
        this.setTitle("Datos del encargado");

        txtEscuela = findViewById(R.id.editEscuelaEnc);
        txtNombre = findViewById(R.id.editNombreEnc);
        txtApellido = findViewById(R.id.editApellidoEnc);
        txtUsuario = findViewById(R.id.usuarioEncargado);
        txtPass = findViewById(R.id.passUsuario);
        checkBox = findViewById(R.id.checkboxPass);
        guardar = findViewById(R.id.guardarCambios);
        cancelar = findViewById(R.id.cancelar);
        actualizar = findViewById(R.id.modificarEncargado);
        eliminar = findViewById(R.id.eliminarEncargado);

        int idEncargado = getIntent().getIntExtra("idEncargado",-1);
        helper.abrir();
        encargado = helper.consultarEncargado(String.valueOf(idEncargado));
        idEscuela = encargado.getIdEscuela();
        String escuela = helper.consultarEscuela(idEscuela).getNombreEscuela();
        String pass = helper.obtenerPassEncargado(encargado.getUsuario());
        txtPass.setText(pass);
        helper.cerrar();
        txtEscuela.setText(escuela);
        txtNombre.setText(encargado.getNombreEncargado());
        txtApellido.setText(encargado.getApellidoEncargado());
        txtUsuario.setText(encargado.getUsuario());
        txtEscuela.setEnabled(false);
    }

    public void actualizar(View view){
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        guardar.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.GONE);
        eliminar.setVisibility(View.GONE);

    }

    public void guardar(View view) {

        if (txtNombre.getText().toString().isEmpty() || txtApellido.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            encargado.setIdEscuela(idEscuela);
            encargado.setNombreEncargado(txtNombre.getText().toString());
            encargado.setApellidoEncargado(txtApellido.getText().toString());
            helper.abrir();
            String resultadoEncargado = helper.actualizarEncargado(encargado);
            helper.cerrar();
            if(!resultadoEncargado.equals("Error")){
                Toast.makeText(this, resultadoEncargado,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error: al actualizar el registro",Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void eliminarEncargado(View view){
        helper.abrir();
        int solicitudes = helper.consultarImpresionesEncargado(encargado.getIdEncargado()).size();
        helper.cerrar();
        if (solicitudes>0){
            Toast.makeText(this, "No se puede eliminar el encargado, tiene solicitudes asignadas", Toast.LENGTH_LONG).show();
        }else {
            helper.abrir();
            String resultado = helper.eliminarEncargado(String.valueOf(encargado.getIdEncargado()),encargado.getUsuario());
            helper.cerrar();
            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void cancelar(View view) {
        recreate();
    }

    public void mostrar(View view) {
       boolean checked = checkBox.isChecked();
       if (checked){
           txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
       }else {
           txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
       }
    }
}
