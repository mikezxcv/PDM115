package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;

public class AdmRepetidoActivity extends ListActivity {

    String[] activities={"AdmDetallesolicitudRepetido"};

    //campos a mostrar
    String [] idRepetido;
    String [] carnet;
    String [] nombre;
    String [] materias;
    String [] tipoEvaluacion;
    String [] fechaSolicitudRepetido;

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

        Intent intent = new Intent(this,AdmDetallesolicitudRepetido.class);
        intent.putExtra("idRepetido",idRepetido[position]);
        intent.putExtra("carnet",carnet[position]);
        intent.putExtra("nombre",nombre[position]);
        intent.putExtra("materia",materias[position]);
        intent.putExtra("evaluacion",tipoEvaluacion[position]);
        intent.putExtra("fechaSolicitud",fechaSolicitudRepetido[position]);
        this.startActivity(intent);

    }

    protected  void llenar(){
        int contador=0;
        helper.abrir();
        cantidad= helper.consultarCantidadSolicitudesRepetidos();

        idRepetido = new String[Integer.parseInt(cantidad)];
        carnet= new String[Integer.parseInt(cantidad)];
        nombre = new String[Integer.parseInt(cantidad)];
        materias = new String[Integer.parseInt(cantidad)];
        tipoEvaluacion = new String[Integer.parseInt(cantidad)];
        fechaSolicitudRepetido = new String[Integer.parseInt(cantidad)];

        idRepetido = helper.idSolicitudRepetido();
        carnet = helper.carnetSolicitudRepetido();
        nombre = helper.nombreSolicitudRepetido();
        materias = helper.idAsignaturaSolicitudRepetido();
        tipoEvaluacion = helper.tipoEvaluacionSolicitudRepetido();
        fechaSolicitudRepetido = helper.fechaSolicitudRepetido();
        
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
