package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*import com.google.gson.Gson;*/

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.Impresion;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmConsultarSolImpresionesActivity extends AppCompatActivity {
   /* ControlBdGrupo12 helper = new ControlBdGrupo12(this);
    ListView listView;
    String idDocente;
    String[] lista;
    private SharedPreferences prefs;
    ArrayList<Impresion> impresiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_consultar_sol_impresiones);
        listView= findViewById(R.id.listViewImp);
        prefs= getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String usuarioDocente = prefs.getString("usuarioActual","ADMIN");
        helper.abrir();
        if (!usuarioDocente.equals("ADMIN")) {
            idDocente = helper.getDocenteActual(usuarioDocente).getIdDocente();
            llenadoNormal();
        }
        else {llenadoAdmin();}

        if (!impresiones.isEmpty()) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdmConsultarSolImpresionesActivity.this,
                    android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Gson gson = new Gson();
                    Intent intent = new Intent(AdmConsultarSolImpresionesActivity.this, AdmDetalleSolicitudImpresionActivity.class);
                    intent.putExtra("impresion", gson.toJson(impresiones.get(position)));
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this,"No hay solicitudes",Toast.LENGTH_SHORT).show();
        }
    }

    public void llenadoNormal(){
        impresiones = helper.consultarImpresionesDocente(idDocente);
        helper.cerrar();
        if (!impresiones.isEmpty()){
            lista = new String[impresiones.size()];
            for (int i=0; i<impresiones.size();i++){
               lista[i]= "Solicitud # "+i+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
        helper.cerrar();
    }

    public void llenadoAdmin(){
        impresiones = helper.obtenerImpresiones();
        lista = new String[impresiones.size()];
        if (!impresiones.isEmpty()){
            for (int i=0; i<impresiones.size();i++){
                lista[i]= "Solicitud # "+i+" ["+impresiones.get(i).getDescripcionSolicitud()+"]";
            }
        }
        helper.cerrar();
    }*/
}
