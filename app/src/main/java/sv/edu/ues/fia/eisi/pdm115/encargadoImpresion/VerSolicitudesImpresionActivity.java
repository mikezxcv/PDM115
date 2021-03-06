package sv.edu.ues.fia.eisi.pdm115.encargadoImpresion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;


public class VerSolicitudesImpresionActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;
    int idEncargado;
    String[] lista;
    private SharedPreferences prefs;
    ArrayList<Impresion> impresiones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_solicitudes_impresion);
        this.setTitle("PDM115: Solicitudes");

        listView = findViewById(R.id.listViewImpEnc);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        final String usuarioActual= prefs.getString("usuarioActual", "ADMIN");
        helper.abrir();
        if (!usuarioActual.equals("ADMIN")) {
            idEncargado = helper.getEncargadoActual(usuarioActual).getIdEncargado();
            llenadoNormal();
        } else {
            llenadoAdmin();
        }
        if (lista!=null && lista.length>0) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VerSolicitudesImpresionActivity.this,
                    android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(VerSolicitudesImpresionActivity.this, DetalleSolicitudImpresionActivity.class);
                    intent.putExtra("idImpresion", impresiones.get(position).getIdSolicitudImpresion());
                    intent.putExtra("usuarioActual", usuarioActual);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this, "No hay solicitudes", Toast.LENGTH_LONG).show();
        }
    }

    public void llenadoNormal(){
        impresiones = helper.consultarImpresionesEncargado(idEncargado);
        helper.cerrar();
        if (!impresiones.isEmpty()){
            lista = new String[impresiones.size()];
            for (int i=0; i<impresiones.size();i++){
                lista[i]= "Solicitud # "+(i+1)+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
        helper.cerrar();
    }
    public void llenadoAdmin(){
        impresiones = helper.obtenerImpresiones();
        if (!impresiones.isEmpty()){
            lista = new String[impresiones.size()];
            for (int i=0; i<impresiones.size();i++){
                lista[i]= "Solicitud # "+(i+1)+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
        helper.cerrar();
    }
}
