package sv.edu.ues.fia.eisi.pdm115;

public class DocenteSegundaRevision {
    private String idDocente;
    private  String idSegundaRevision;

    public DocenteSegundaRevision(String idDocente, String idSegundaRevision) {
        this.idDocente = idDocente;
        this.idSegundaRevision = idSegundaRevision;
    }
    public DocenteSegundaRevision(){

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
