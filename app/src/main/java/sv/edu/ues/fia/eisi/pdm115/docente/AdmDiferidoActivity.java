package sv.edu.ues.fia.eisi.pdm115.docente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

public class AdmDiferidoActivity extends ListActivity {

    String[] activities={"AdmDetallesolicitudDiferido"};

    //campos a mostrar
    String [] idDiferidos;
    String [] carnet;
    String [] nombre;
    String [] materias;
    String [] tipoEvaluacion;

    String [] opciones;
    String cantidad;
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

        Intent intent = new Intent(this, AdmDetallesolicitudDiferido.class);
        intent.putExtra("idDiferido",idDiferidos[position]);
        intent.putExtra("carnet",carnet[position]);
        intent.putExtra("nombre",nombre[position]);
        intent.putExtra("materia",materias[position]);
        intent.putExtra("evaluacion",tipoEvaluacion[position]);
        startActivity(intent);
    }

    protected  void llenar(){
        int contador=0;
        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesDiferidos();

        idDiferidos= new String[Integer.parseInt(cantidad)];
        idDiferidos= helper.idDiferido();

        carnet= new String[Integer.parseInt(cantidad)];
        carnet= helper.carnetDiferido();

        nombre= new String[Integer.parseInt(cantidad)];
        nombre= helper.NombreEstudianteDiferido();

        materias= new String[Integer.parseInt(cantidad)];
        materias= helper.NombreMateriaDiferido();

        tipoEvaluacion= new String[Integer.parseInt(cantidad)];
        tipoEvaluacion= helper.nombreEvaluacionDiferido();

        opciones= new String[Integer.parseInt(cantidad)];

        helper.cerrar();

        for (int i=0; i<Integer.valueOf(cantidad);i++){
            contador= contador+1;
            String alumno= carnet[i];
            String materia= materias[i];
            opciones[i]= "Solicitud Diferido "+contador+" ["+ alumno+" - "+materia+" ]";
        }

    }
}
