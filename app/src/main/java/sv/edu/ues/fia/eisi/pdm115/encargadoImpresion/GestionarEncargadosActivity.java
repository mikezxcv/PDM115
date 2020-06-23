package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.EncargadoDeImpresiones;

import sv.edu.ues.fia.eisi.pdm115.R;

public class GestionarEncargadosActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;
    ArrayList<EncargadoDeImpresiones> encargados;
    String[] lista;
    FloatingActionButton boton;
    final Context contexto = GestionarEncargadosActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_motivos);
        this.setTitle("Gestion de Encargados");

        listView = findViewById(R.id.listViewMot);
        boton = findViewById(R.id.botonEmergente);
        helper.abrir();
        encargados = helper.obtenerEncargados();
        helper.cerrar();

        if (!encargados.isEmpty()) {
            lista = new String[encargados.size()];
            for (int i = 0; i < encargados.size(); i++) {
                lista[i] = "" + encargados.get(i).getIdEncargado() + "- " +
                        encargados.get(i).getNombreEncargado() +" "+encargados.get(i).getApellidoEncargado();
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(contexto, DetalleEncargadoActivity.class);
                    intent.putExtra("idEncargado", encargados.get(position).getIdEncargado());
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(contexto, "Lista vacia", Toast.LENGTH_LONG).show();
        }

        //Agregar nuevo
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, NuevoEncargadoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
