package sv.edu.ues.fia.eisi.pdm115.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;

import sv.edu.ues.fia.eisi.pdm115.R;

public class EstudianteMenuActivity extends ListActivity {
    String[] menu={"Primera Revision","Segunda Revision","Diferido", "Repetido"};
    String[] activities={"EstudiantePrimeraRevisionActivity","EstudianteSegundaRevisionActivity","AlumnoConsultarActivity", "AlumnoActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_estudiante_menu);
    }
}
