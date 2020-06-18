package sv.edu.ues.fia.eisi.pdm115;

public class Escuela {

    private String idEscuela;
    private int idArea;
    private String nombreEscuela;
    private String facultad;

    Escuela(){}

    Escuela(String idEscuela, int idArea, String nombreEscuela, String facultad){
        this.idEscuela = idEscuela;
        this.idArea = idArea;
        this.nombreEscuela = nombreEscuela;
        this.facultad = facultad;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
