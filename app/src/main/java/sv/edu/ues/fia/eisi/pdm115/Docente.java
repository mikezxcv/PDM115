package sv.edu.ues.fia.eisi.pdm115;

public class Docente {
    private String idDocente;
    private String idTipoDocenteCiclo;
    private String idEscuela;
    private String idAsignatura;
    private String idCiclo;
    private String usuario;
    private String idOpcion;
    private String nombreDocente;
    private String apellidoDocente;
    private int idRol;

    Docente(String idDocente, String idTipoDocenteCiclo, String idEscuela, String idAsignatura,
            String idCiclo, String usuario, String idOpcion, String nombreDocente, String apellidoDocente,
            int idRol){

        this.idDocente = idDocente;
        this.idTipoDocenteCiclo = idTipoDocenteCiclo;
        this.idEscuela = idEscuela;
        this.idAsignatura = idAsignatura;
        this.idCiclo = idCiclo;
        this.usuario = usuario;
        this.idOpcion = idOpcion;
        this.nombreDocente = nombreDocente;
        this.apellidoDocente = apellidoDocente;
        this.idRol = idRol;
    }

    Docente(){

    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getIdTipoDocenteCiclo() {
        return idTipoDocenteCiclo;
    }

    public void setIdTipoDocenteCiclo(String idTipoDocenteCiclo) {
        this.idTipoDocenteCiclo = idTipoDocenteCiclo;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(String idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(String idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(String apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
