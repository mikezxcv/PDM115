package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmPrimeraRevisionActivity extends ListActivity {


    String[] activities={"AdmDetallesolicitudPrimeraRevision"};
    String cantidad;

    String [] alumnos;
    String [] materias;
    String [] opciones;





    ControlBdGrupo12 helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper= new ControlBdGrupo12(this);

                llenar();
                 setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, opciones));

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(AdmPrimeraRevisionActivity.this, AdmDetallesolicitudPrimeraRevision.class);
        startActivity(intent);



    }

    protected  void obtenerOpciones(){

    }
    protected  void llenar(){
        int contador=0;
        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesPrimeraRevision();
        alumnos= new String[Integer.parseInt(cantidad)];
        alumnos= helper.alumnosPrimeraRevision();
        materias= new String[Integer.parseInt(cantidad)];
        materias= helper.materiasPrimeraRevision();
        opciones= new String[Integer.parseInt(cantidad)];



        helper.cerrar();

        for (int i=0; i<Integer.valueOf(cantidad);i++){
            contador= contador+1;
            String alumno= alumnos[i];

            String materia= materias[i];
            opciones[i]= "Solicitud "+contador+" ["+ alumno+" - "+materia+" ]";


        }

    }


}
