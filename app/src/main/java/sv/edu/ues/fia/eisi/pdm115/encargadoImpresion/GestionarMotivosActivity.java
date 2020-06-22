package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.MotivoNoImpresion;
import sv.edu.ues.fia.eisi.pdm115.R;

public class GestionarMotivosActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;
    ArrayList<MotivoNoImpresion> motivos;
    String[] lista;
    FloatingActionButton boton;
    final Context contexto = GestionarMotivosActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_motivos);
        this.setTitle("Gestion de motivos");

        listView = (ListView) findViewById(R.id.listViewMot);
        boton = (FloatingActionButton) findViewById(R.id.botonEmergente);
        helper = new ControlBdGrupo12(this);
        helper.abrir();
        motivos = helper.obtenerMotivos();
        helper.cerrar();
        //Carga la lista con los motivos
        if (!motivos.isEmpty()) {
            lista = new String[motivos.size()];
            for (int i = 0; i < motivos.size(); i++) {
                lista[i] = "" + motivos.get(i).getIdMotivo() + "-" + motivos.get(i).getMotivoNoImpresion();
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(contexto, DetalleMotivoActivity.class);
                    intent.putExtra("idMotivo", motivos.get(position).getIdMotivo());
                    intent.putExtra("descMotivo", motivos.get(position).getMotivoNoImpresion());
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(contexto, "Lista vacia", Toast.LENGTH_LONG).show();
        }

        //Agregar nuevo motivo
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implementar alert
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                @SuppressLint("InflateParams") View viewInflated = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_nuevo_motivo, null);
                builder.setView(viewInflated);

                final EditText nombreMotivo = viewInflated.findViewById(R.id.nuevoMotivoNoImp);

                builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MotivoNoImpresion motivo = new MotivoNoImpresion();

                        motivo.setMotivoNoImpresion(nombreMotivo.getText().toString());
                        helper.abrir();
                        String resultado = helper.insertarMotivo(motivo);
                        helper.cerrar();
                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                });
                builder.setTitle("Crear Nuevo Motivo");
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
