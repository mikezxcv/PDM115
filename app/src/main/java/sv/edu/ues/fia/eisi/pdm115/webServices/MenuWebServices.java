package sv.edu.ues.fia.eisi.pdm115.webServices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sv.edu.ues.fia.eisi.pdm115.R;

public class MenuWebServices extends AppCompatActivity {
    String [] opciones={"Actualizar Lista de Locales","Cantidad Solicitudes de Procesos Academicos","Actualizar Lista de Estudiantes","Eliminar Estudiante","Ingresar materia","Consultar Lista de Usuarios"};
    String []activity={"sv.edu.ues.fia.eisi.pdm115.webServices.ActualizarLocales",
            "sv.edu.ues.fia.eisi.pdm115.webServices.ConsultarCantidadSolicitudes",
            "sv.edu.ues.fia.eisi.pdm115.webServices.ActualizarEstudiantes",
            "sv.edu.ues.fia.eisi.pdm115.webServices.EliminarEstudiante",
            "sv.edu.ues.fia.eisi.pdm115.webServices.IngresarMateriaActivity",
            "sv.edu.ues.fia.eisi.pdm115.webServices.consultarUsuarios"};

    ListView listView;
    Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_web_services);
        listView=(ListView) findViewById(R.id.listVievWebServices);

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,opciones);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String nombreValue=activity[position];
                try {
                    intent= new Intent(getApplicationContext(), Class.forName(nombreValue) );
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
