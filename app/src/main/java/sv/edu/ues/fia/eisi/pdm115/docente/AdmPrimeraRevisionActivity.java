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
//campos a mostrar
    String [] alumnos;
    String [] nombres;
    String [] materias;
    String [] evaluaciones;
    String [] fechaSolicitud;
    String [] idPrimerRevision;

    //campos a mostrar
    String [] opciones;
    ControlBdGrupo12 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper= new ControlBdGrupo12(this);

        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesPrimeraRevision();
        helper.cerrar();
        if(Integer.valueOf(cantidad)!=0){
            llenar();
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, opciones));
        }
        else {
            Toast.makeText(this,"No hay solicitudes",Toast.LENGTH_LONG).show();
        }



    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


            Intent intent = new Intent(AdmPrimeraRevisionActivity.this, AdmDetallesolicitudPrimeraRevision.class);
            intent.putExtra("carnet",alumnos[position]);
            intent.putExtra("nombre",nombres[position]);
            intent.putExtra("materia",materias[position]);
            intent.putExtra("evaluacion",evaluaciones[position]);
            intent.putExtra("fechaSolicitud",fechaSolicitud[position]);
            intent.putExtra("id", idPrimerRevision[position]);

            startActivity(intent);
    }


    protected  void llenar(){
        int contador=0;
        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesPrimeraRevision();

        alumnos= new String[Integer.parseInt(cantidad)];
        alumnos= helper.alumnosPrimeraRevision();

        nombres= new String[Integer.parseInt(cantidad)];
        nombres= helper.nombreEstudiantePrimeraRevision();

        materias= new String[Integer.parseInt(cantidad)];
        materias= helper.materiasPrimeraRevision();

        evaluaciones= new String[Integer.parseInt(cantidad)];
        evaluaciones= helper.nombreEvaluacionPrimeraRevision();

        fechaSolicitud= new String[Integer.parseInt(cantidad)];
        fechaSolicitud= helper.fechaSolicitudPrimeraRevision();

        idPrimerRevision= new String[Integer.parseInt(cantidad)];
        idPrimerRevision= helper.idPrimeraRevision();

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
