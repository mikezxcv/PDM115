package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.MotivoNoImpresion;
import sv.edu.ues.fia.eisi.pdm115.R;
import sv.edu.ues.fia.eisi.pdm115.docente.AdmRolActivity;
import sv.edu.ues.fia.eisi.pdm115.docente.AdmRolDetalle;

public class DetalleMotivoActivity extends AppCompatActivity {

    ControlBdGrupo12 helper;
    EditText textVnombre;
    Button actualizar;
    Button eliminar;
    int idMotivo;
    String nombreMotivo;
    MotivoNoImpresion motivo = new MotivoNoImpresion();

    AlertDialog dialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_motivo);

        helper= new ControlBdGrupo12(this);
        textVnombre= findViewById(R.id.editMotivoNoimp);
        actualizar= findViewById(R.id.actualizarMotivo);
        eliminar= findViewById(R.id.eliminarMotivo);

        nombreMotivo = getIntent().getStringExtra("descMotivo");
        idMotivo= getIntent().getIntExtra("idMotivo",-1);

        textVnombre.setText(nombreMotivo);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog();
                dialogo.show();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialog2();
                dialogo.show();
            }
        });

    }
    public void crearDialog(){
        dialogo = new AlertDialog
                .Builder(DetalleMotivoActivity.this)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.abrir();
                        String resultado= helper.eliminarMotivo(idMotivo);
                        helper.cerrar();
                        Toast.makeText(DetalleMotivoActivity.this, resultado, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(DetalleMotivoActivity.this, GestionarMotivosActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("Confirmar").setMessage("¿Deseas eliminar este registro?").create();

    }

    public void crearDialog2(){
        dialogo = new AlertDialog
                .Builder(DetalleMotivoActivity.this)
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String motNuevo = textVnombre.getText().toString();
                        if (motNuevo.isEmpty()){
                            Toast.makeText(DetalleMotivoActivity.this,"El campo esta vacio", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            motivo.setIdMotivo(idMotivo);
                            motivo.setMotivoNoImpresion(motNuevo);
                            helper.abrir();
                            String resultado= helper.actualizarMotivo(motivo);
                            helper.cerrar();
                            Toast.makeText(DetalleMotivoActivity.this,resultado, Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(DetalleMotivoActivity.this, GestionarEncargadosActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón negativo, no confirmaron
                        // Simplemente descartamos el diálogo
                        dialog.dismiss();
                    }
                }).setTitle("Confirmar").setMessage("¿Deseas Actualizar el registro?").create();

    }
}
