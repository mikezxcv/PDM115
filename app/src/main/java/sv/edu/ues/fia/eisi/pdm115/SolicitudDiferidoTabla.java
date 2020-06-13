package sv.edu.ues.fia.eisi.pdm115;

public class SolicitudDiferidoTabla {
    //Comentario MP16001
    private String IDDIFERIDO;
    private String ID_DETALLEALUMNOSEVALUADOS;
    private String FECHASOLICITUDDIFERIDO;
    private String ESTADODIFERIDO;
    private String FECHADIFERIDO;
    private String NOTADIFERIDO;
    private String OBSERVACIONESDIFERIDO;
    private String MATERIADIFERIDO;
    private String MOTIVODIFERIDO;
    private String HORADIFERIDO;
    private String LOCALDIFERIDO;

    public SolicitudDiferidoTabla() {
    }

    public SolicitudDiferidoTabla(String IDDIFERIDO, String ID_DETALLEALUMNOSEVALUADOS, String FECHASOLICITUDDIFERIDO, String ESTADODIFERIDO, String FECHADIFERIDO, String NOTADIFERIDO, String OBSERVACIONESDIFERIDO, String MATERIADIFERIDO, String MOTIVODIFERIDO, String HORADIFERIDO, String LOCALDIFERIDO) {
        this.IDDIFERIDO = IDDIFERIDO;
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
        this.FECHASOLICITUDDIFERIDO = FECHASOLICITUDDIFERIDO;
        this.ESTADODIFERIDO = ESTADODIFERIDO;
        this.FECHADIFERIDO = FECHADIFERIDO;
        this.NOTADIFERIDO = NOTADIFERIDO;
        this.OBSERVACIONESDIFERIDO = OBSERVACIONESDIFERIDO;
        this.MATERIADIFERIDO = MATERIADIFERIDO;
        this.MOTIVODIFERIDO = MOTIVODIFERIDO;
        this.HORADIFERIDO = HORADIFERIDO;
        this.LOCALDIFERIDO = LOCALDIFERIDO;
    }

    public String getLOCALDIFERIDO() {
        return LOCALDIFERIDO;
    }

    public void setLOCALDIFERIDO(String LOCALDIFERIDO) {
        this.LOCALDIFERIDO = LOCALDIFERIDO;
    }

    public String getIDDIFERIDO() {
        return IDDIFERIDO;
    }

    public void setIDDIFERIDO(String IDDIFERIDO) {
        this.IDDIFERIDO = IDDIFERIDO;
    }

    public String getID_DETALLEALUMNOSEVALUADOS() {
        return ID_DETALLEALUMNOSEVALUADOS;
    }

    public void setID_DETALLEALUMNOSEVALUADOS(String ID_DETALLEALUMNOSEVALUADOS) {
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
    }

    public String getFECHASOLICITUDDIFERIDO() {
        return FECHASOLICITUDDIFERIDO;
    }

    public void setFECHASOLICITUDDIFERIDO(String FECHASOLICITUDDIFERIDO) {
        this.FECHASOLICITUDDIFERIDO = FECHASOLICITUDDIFERIDO;
    }

    public String getESTADODIFERIDO() {
        return ESTADODIFERIDO;
    }

    public void setESTADODIFERIDO(String ESTADODIFERIDO) {
        this.ESTADODIFERIDO = ESTADODIFERIDO;
    }

    public String getFECHADIFERIDO() {
        return FECHADIFERIDO;
    }

    public void setFECHADIFERIDO(String FECHADIFERIDO) {
        this.FECHADIFERIDO = FECHADIFERIDO;
    }

    public String getNOTADIFERIDO() {
        return NOTADIFERIDO;
    }

    public void setNOTADIFERIDO(String NOTADIFERIDO) {
        this.NOTADIFERIDO = NOTADIFERIDO;
    }

    public String getOBSERVACIONESDIFERIDO() {
        return OBSERVACIONESDIFERIDO;
    }

    public void setOBSERVACIONESDIFERIDO(String OBSERVACIONESDIFERIDO) {
        this.OBSERVACIONESDIFERIDO = OBSERVACIONESDIFERIDO;
    }

    public String getMATERIADIFERIDO() {
        return MATERIADIFERIDO;
    }

    public void setMATERIADIFERIDO(String MATERIADIFERIDO) {
        this.MATERIADIFERIDO = MATERIADIFERIDO;
    }

    public String getMOTIVODIFERIDO() {
        return MOTIVODIFERIDO;
    }

    public void setMOTIVODIFERIDO(String MOTIVODIFERIDO) {
        this.MOTIVODIFERIDO = MOTIVODIFERIDO;
    }

    public String getHORADIFERIDO() {
        return HORADIFERIDO;
    }

    public void setHORADIFERIDO(String HORADIFERIDO) {
        this.HORADIFERIDO = HORADIFERIDO;
    }
}
