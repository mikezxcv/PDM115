package sv.edu.ues.fia.eisi.pdm115;

public class Docente {
    private String idDocente;
    private  String idSegundaRevision;

    public Docente(String idDocente, String idSegundaRevision) {
        this.idDocente = idDocente;
        this.idSegundaRevision = idSegundaRevision;
    }
    public Docente(){

    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getIdSegundaRevision() {
        return idSegundaRevision;
    }

    public void setIdSegundaRevision(String idSegundaRevision) {
        this.idSegundaRevision = idSegundaRevision;
    }
}
