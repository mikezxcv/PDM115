package sv.edu.ues.fia.eisi.pdm115;

public class Impresion {
    private int idSolicitudImpresion;
    private String idDocente;
    private int idEncargado;
    private int idMotivoNoImp;
    private String descripcionNoImp;
    private String descripcionSolicitud;
    private int cantidadExamenes;
    private int hojasEmpaque;
    private int estadoAprobacion;
    private int estadoImpresion;

    Impresion(int idSolicitudImpresion, String idDocente, int idEncargado, int idMotivoNoImp,
              String descripcionNoImp, String descripcionSolicitud, int cantidadExamenes,
              int hojasEmpaque, int estadoAprobacion, int estadoImpresion){

        this.idSolicitudImpresion = idSolicitudImpresion;
        this.idDocente = idDocente;
        this.idEncargado = idEncargado;
        this.idMotivoNoImp = idMotivoNoImp;
        this.descripcionNoImp = descripcionNoImp;
        this.descripcionSolicitud = descripcionSolicitud;
        this.cantidadExamenes = cantidadExamenes;
        this.hojasEmpaque = hojasEmpaque;
        this.estadoAprobacion = estadoAprobacion;
        this.estadoImpresion = estadoImpresion;

    }

    public int getIdSolicitudImpresion() {
        return idSolicitudImpresion;
    }

    public void setIdSolicitudImpresion(int idSolicitudImpresion) {
        this.idSolicitudImpresion = idSolicitudImpresion;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public int getIdMotivoNoImp() {
        return idMotivoNoImp;
    }

    public void setIdMotivoNoImp(int idMotivoNoImp) {
        this.idMotivoNoImp = idMotivoNoImp;
    }

    public String getDescripcionNoImp() {
        return descripcionNoImp;
    }

    public void setDescripcionNoImp(String descripcionNoImp) {
        this.descripcionNoImp = descripcionNoImp;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public int getCantidadExamenes() {
        return cantidadExamenes;
    }

    public void setCantidadExamenes(int cantidadExamenes) {
        this.cantidadExamenes = cantidadExamenes;
    }

    public int getHojasEmpaque() {
        return hojasEmpaque;
    }

    public void setHojasEmpaque(int hojasEmpaque) {
        this.hojasEmpaque = hojasEmpaque;
    }

    public int getEstadoAprobacion() {
        return estadoAprobacion;
    }

    public void setEstadoAprobacion(int estadoAprobacion) {
        this.estadoAprobacion = estadoAprobacion;
    }

    public int getEstadoImpresion() {
        return estadoImpresion;
    }

    public void setEstadoImpresion(int estadoImpresion) {
        this.estadoImpresion = estadoImpresion;
    }

}
