package sv.edu.ues.fia.eisi.pdm115.docente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import sv.edu.ues.fia.eisi.pdm115.ControlBdGrupo12;
import sv.edu.ues.fia.eisi.pdm115.R;

public class AdmGestionarEvaluacionesActivity extends Activity {
    ControlBdGrupo12 helper;
    ListView listView;

    String[] datos;
    int[] idEvaluaciones;
    FloatingActionButton boton;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String date;
    private static final String TAG = "AdmGestionarEvaluacionesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_gestionar_evaluaciones);
        listView = (ListView) findViewById(R.id.listViewev);
        boton = (FloatingActionButton) findViewById(R.id.botonEmergente);
        helper = new ControlBdGrupo12(this);
        helper.abrir();
        datos = helper.obtenerEvaluacionesCRUD();
        idEvaluaciones = helper.IDEvaluacionesCRUD();
        helper.cerrar();
        //carga el listview con los datos
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent = new Intent(AdmGestionarEvaluacionesActivity.this, AdmEvaluacionDetalle.class);
                intent.putExtra("id", String.valueOf(idEvaluaciones[position]));
                startActivity(intent);

            }
        });

        //agregar nueva evaluacion
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmGestionarEvaluacionesActivity.this,AdmCrearEvaluacionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.abrir();
        datos = helper.obtenerEvaluacionesCRUD();
        idEvaluaciones = helper.IDEvaluacionesCRUD();
        helper.cerrar();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listView.setAdapter(arrayAdapter);
        //carga el listview con los datos

    }
}

