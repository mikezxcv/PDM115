package sv.edu.ues.fia.eisi.pdm115.utilidades;

import java.util.HashMap;
import java.util.Map;

/**
 * Retorna la actividad detectada por el reconocimiento de voz.
 */
public class BuscarActividad {
    private static Map<String,Map<String, String>> actividades;
    static {
        actividades = new HashMap<>();
        actividades.put("revisión", new HashMap<String, String>() {{
            put("insertar", "");
            put("consultar", "");
            put("modificar", "");
            put("eliminar", "");
        }});
        actividades.put("segunda", new HashMap<String, String>() {{
            put("insertar", "");
            put("consultar", "");
            put("modificar", "");
            put("eliminar", "");
        }});
        actividades.put("diferido", new HashMap<String, String>() {{
            put("insertar", "");
            put("consultar", "");
            put("modificar", "");
            put("eliminar", "");
        }});
        actividades.put("repetido", new HashMap<String, String>() {{
            put("insertar", "");
            put("consultar", "");
            put("modificar", "");
            put("eliminar", "");
        }});
        actividades.put("imprimir", new HashMap<String, String>() {{
            put("insertar", "");
            put("consultar", "");
            put("modificar", "");
            put("eliminar", "");
        }});
    }

    static String get(String crud, String accion){
        try {
            return actividades.get(crud).get(accion);
        }catch (Exception e){
            e.printStackTrace();
            return "Algo no funcionó";
        }
    }
}
