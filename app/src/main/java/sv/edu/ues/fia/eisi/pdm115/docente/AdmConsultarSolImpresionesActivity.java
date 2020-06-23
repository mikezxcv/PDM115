package sv.edu.ues.fia.eisi.pdm115.docente;

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

public class AdmConsultarSolImpresionesActivity extends AppCompatActivity {
    ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;
    String idDocente;
    String[] lista;
    private SharedPreferences prefs;
    ArrayList<Impresion> impresiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_consultar_sol_impresiones);
        this.setTitle("Lista de solicitudes");

        listView= findViewById(R.id.listViewImp);
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        final String usuarioActual = prefs.getString("usuarioActual","ADMIN");
        helper.abrir();
        if (!usuarioActual.equals("ADMIN")) {
            idDocente = helper.getDocenteActual(usuarioActual).getIdDocente();
            llenadoNormal();
        }
        else {llenadoAdmin();}

        if (lista.length>0) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdmConsultarSolImpresionesActivity.this,
                    android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(AdmConsultarSolImpresionesActivity.this, AdmDetalleSolicitudImpresionActivity.class);
                    intent.putExtra("idImpresion", impresiones.get(position).getIdSolicitudImpresion());
                    intent.putExtra("usuarioActual", usuarioActual);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this,"No hay solicitudes",Toast.LENGTH_LONG).show();
        }
        helper.cerrar();
    }

    public void llenadoNormal(){
        impresiones = helper.consultarImpresionesDocente(idDocente);
        if (!impresiones.isEmpty()){
            lista = new String[impresiones.size()];
            for (int i=0; i<impresiones.size();i++){
               lista[i]= "Solicitud # "+(i+1)+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
    }

    public void llenadoAdmin(){
        impresiones = helper.obtenerImpresiones();
        lista = new String[impresiones.size()];
        if (!impresiones.isEmpty()){
            for (int i=0; i<impresiones.size();i++){
                lista[i]= "Solicitud # "+(i+1)+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
