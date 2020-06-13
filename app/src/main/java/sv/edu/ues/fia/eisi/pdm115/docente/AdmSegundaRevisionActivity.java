package sv.edu.ues.fia.eisi.pdm115.docente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

public class AdmSegundaRevisionActivity extends ListActivity {


    String cantidad;
    //campos a mostrar
    String [] alumnos;
    String [] nombres;
    String [] materias;
    String [] evaluaciones;
    String [] fechaSolicitud;
    String [] fechaPrimeraRevision;
    String [] idSegundaRevision;

    //notas en dar revision
    String [] notaInicial;
    String [] notaDespuesPrimeraRevision;

    //campos a mostrar
    String [] opciones;
    ControlBdGrupo12 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper= new ControlBdGrupo12(this);

        helper.abrir();
        //cantidad= helper.consultarCantidadSolicitudesSegundaRevision();
        helper.cerrar();
        if(Integer.valueOf(cantidad)!=0){
            //llenar();
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
        Intent intent = new Intent(AdmSegundaRevisionActivity.this, AdmDetallesolicitudSegundaRevision.class);
        intent.putExtra("carnet",alumnos[position]);
        intent.putExtra("nombre",nombres[position]);
        intent.putExtra("materia",materias[position]);
        intent.putExtra("evaluacion",evaluaciones[position]);
        intent.putExtra("fechaSolicitud",fechaSolicitud[position]);
        intent.putExtra("fechaPrimeraRevision",fechaPrimeraRevision[position]);
        intent.putExtra("id", idSegundaRevision[position]);

        intent.putExtra("notaInicial", notaInicial[position]);
        intent.putExtra("notaPrimeraRevision", notaDespuesPrimeraRevision[position]);

        startActivity(intent);

    }
    /*protected  void llenar(){
        int contador=0;
        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesSegundaRevision();

        alumnos= new String[Integer.parseInt(cantidad)];
        alumnos= helper.alumnosSegundaRevision();

        nombres= new String[Integer.parseInt(cantidad)];
        nombres= helper.nombreEstudianteSegundaRevision();

        materias= new String[Integer.parseInt(cantidad)];
        materias= helper.materiasSegundaRevision();

        evaluaciones= new String[Integer.parseInt(cantidad)];
        evaluaciones= helper.nombreEvaluacionSegundaRevision();

        fechaSolicitud= new String[Integer.parseInt(cantidad)];
        fechaSolicitud= helper.fechaSolicitudSegundaRevision();

        idSegundaRevision = new String[Integer.parseInt(cantidad)];
        idSegundaRevision = helper.idSegundaRevision();

        fechaPrimeraRevision= new String[Integer.parseInt(cantidad)];
        fechaPrimeraRevision= helper.fechaPrimeraRevision();

        //notas en dar revison
        notaInicial= new String[Integer.parseInt(cantidad)];
        notaInicial= helper.notaInicial();

        notaDespuesPrimeraRevision=new String[Integer.parseInt(cantidad)];
        notaDespuesPrimeraRevision=helper.notaPrimeraRevision();

        opciones= new String[Integer.parseInt(cantidad)];



        helper.cerrar();

        for (int i=0; i<Integer.valueOf(cantidad);i++){
            contador= contador+1;
            String alumno= alumnos[i];

            String materia= materias[i];
            opciones[i]= "Solicitud "+contador+" ["+ alumno+" - "+materia+" ]";


        }

    }*/

}
