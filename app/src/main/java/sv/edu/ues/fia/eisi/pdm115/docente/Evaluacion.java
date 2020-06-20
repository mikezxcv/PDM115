package sv.edu.ues.fia.eisi.pdm115.docente;

public class Evaluacion {
    private int idEvaluacion;
    private String tipoEvaluacion;
    private String nombreEvaluacion;
    private String fechaEvaluacion;

    public Evaluacion() {
    }

    public Evaluacion(int idEvaluacion, String tipoEvaluacion, String nombreEvaluacion,String fechaEvaluacion) {
        this.idEvaluacion = idEvaluacion;
        this.tipoEvaluacion = tipoEvaluacion;
        this.nombreEvaluacion = nombreEvaluacion;
        this.fechaEvaluacion=fechaEvaluacion;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }
}
