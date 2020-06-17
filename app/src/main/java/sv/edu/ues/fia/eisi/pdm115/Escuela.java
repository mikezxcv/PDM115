package sv.edu.ues.fia.eisi.pdm115;

public class Escuela {
    private String idEscuela;
    private String idarea;
    private String nombre;
    private String facultad;


    public Escuela(String idEscuela, String idarea, String nombre, String facultad) {
        this.idEscuela = idEscuela;
        this.idarea = idarea;
        this.nombre = nombre;
        this.facultad = facultad;
    }
    public Escuela(){

    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getIdarea() {
        return idarea;
    }

    public void setIdarea(String idarea) {
        this.idarea = idarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
