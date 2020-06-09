package sv.edu.ues.fia.eisi.pdm115;

public class PrimeraRevision {
    private String idPrimeraRevision;
    private String idLocal;
    private String idDocente;
    private String idDetalleAlumnosEvaluados;
    private String fechaSolicitudPrimeraRevision;
    private String estadoPrimeraRevision;
    private String fechaPrimeraRevision;
    private String horaPrimerarevision;
    private String notaAntesPrimeraRevision;
    private String notaDespuesPrimeraRevision;
    private String observacionesPrimeraRevision;


    public PrimeraRevision(String idPrimeraRevision, String idLocal, String idDocente, String idDetalleAlumnosEvaluados, String fechaSolicitudPrimeraRevision, String estadoPrimeraRevision, String fechaPrimeraRevision, String horaPrimerarevision, String notaAntesPrimeraRevision, String notaDespuesPrimeraRevision, String observacionesPrimeraRevision) {
        this.idPrimeraRevision = idPrimeraRevision;
        this.idLocal = idLocal;
        this.idDocente = idDocente;
        this.idDetalleAlumnosEvaluados = idDetalleAlumnosEvaluados;
        this.fechaSolicitudPrimeraRevision = fechaSolicitudPrimeraRevision;
        this.estadoPrimeraRevision = estadoPrimeraRevision;
        this.fechaPrimeraRevision = fechaPrimeraRevision;
        this.horaPrimerarevision = horaPrimerarevision;
        this.notaAntesPrimeraRevision = notaAntesPrimeraRevision;
        this.notaDespuesPrimeraRevision = notaDespuesPrimeraRevision;
        this.observacionesPrimeraRevision = observacionesPrimeraRevision;
    }
    public PrimeraRevision(){

    }

    public String getIdPrimeraRevision() {
        return idPrimeraRevision;
    }

    public void setIdPrimeraRevision(String idPrimeraRevision) {
        this.idPrimeraRevision = idPrimeraRevision;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getIdDetalleAlumnosEvaluados() {
        return idDetalleAlumnosEvaluados;
    }

    public void setIdDetalleAlumnosEvaluados(String idDetalleAlumnosEvaluados) {
        this.idDetalleAlumnosEvaluados = idDetalleAlumnosEvaluados;
    }

    public String getFechaSolicitudPrimeraRevision() {
        return fechaSolicitudPrimeraRevision;
    }

    public void setFechaSolicitudPrimeraRevision(String fechaSolicitudPrimeraRevision) {
        this.fechaSolicitudPrimeraRevision = fechaSolicitudPrimeraRevision;
    }

    public String getEstadoPrimeraRevision() {
        return estadoPrimeraRevision;
    }

    public void setEstadoPrimeraRevision(String estadoPrimeraRevision) {
        this.estadoPrimeraRevision = estadoPrimeraRevision;
    }

    public String getFechaPrimeraRevision() {
        return fechaPrimeraRevision;
    }

    public void setFechaPrimeraRevision(String fechaPrimeraRevision) {
        this.fechaPrimeraRevision = fechaPrimeraRevision;
    }

    public String getHoraPrimerarevision() {
        return horaPrimerarevision;
    }

    public void setHoraPrimerarevision(String horaPrimerarevision) {
        this.horaPrimerarevision = horaPrimerarevision;
    }

    public String getNotaAntesPrimeraRevision() {
        return notaAntesPrimeraRevision;
    }

    public void setNotaAntesPrimeraRevision(String notaAntesPrimeraRevision) {
        this.notaAntesPrimeraRevision = notaAntesPrimeraRevision;
    }

    public String getNotaDespuesPrimeraRevision() {
        return notaDespuesPrimeraRevision;
    }

    public void setNotaDespuesPrimeraRevision(String notaDespuesPrimeraRevision) {
        this.notaDespuesPrimeraRevision = notaDespuesPrimeraRevision;
    }

    public String getObservacionesPrimeraRevision() {
        return observacionesPrimeraRevision;
    }

    public void setObservacionesPrimeraRevision(String observacionesPrimeraRevision) {
        this.observacionesPrimeraRevision = observacionesPrimeraRevision;
    }
}
