package sv.edu.ues.fia.eisi.pdm115;

public class Diferido {
    private int IDDIFERIDO;
    private int ID_DETALLEALUMNOSEVALUADOS;
    private String FECHASOLICITUDDIFERIDO;
    private String ESTADODIFERIDO;
    private String FECHADIFERIDO;
    private int NOTADIFERIDO;
    private String OBSERVACIONESDIFERIDO;
    private String MATERIADIFERIDO;
    private String IDLOCAL;
    private String MOTIVODIFERIDO;
    private String HORADIFERIDO;

    public Diferido() {
    }

    public Diferido(int IDDIFERIDO, int ID_DETALLEALUMNOSEVALUADOS, String FECHASOLICITUDDIFERIDO, String ESTADODIFERIDO, String FECHADIFERIDO, int NOTADIFERIDO, String OBSERVACIONESDIFERIDO, String MATERIADIFERIDO, String IDLOCAL, String MOTIVODIFERIDO, String HORADIFERIDO) {
        this.IDDIFERIDO = IDDIFERIDO;
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
        this.FECHASOLICITUDDIFERIDO = FECHASOLICITUDDIFERIDO;
        this.ESTADODIFERIDO = ESTADODIFERIDO;
        this.FECHADIFERIDO = FECHADIFERIDO;
        this.NOTADIFERIDO = NOTADIFERIDO;
        this.OBSERVACIONESDIFERIDO = OBSERVACIONESDIFERIDO;
        this.MATERIADIFERIDO = MATERIADIFERIDO;
        this.IDLOCAL = IDLOCAL;
        this.MOTIVODIFERIDO = MOTIVODIFERIDO;
        this.HORADIFERIDO = HORADIFERIDO;
    }

    public int getIDDIFERIDO() {
        return IDDIFERIDO;
    }

    public void setIDDIFERIDO(int IDDIFERIDO) {
        this.IDDIFERIDO = IDDIFERIDO;
    }

    public int getID_DETALLEALUMNOSEVALUADOS() {
        return ID_DETALLEALUMNOSEVALUADOS;
    }

    public void setID_DETALLEALUMNOSEVALUADOS(int ID_DETALLEALUMNOSEVALUADOS) {
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

    public int getNOTADIFERIDO() {
        return NOTADIFERIDO;
    }

    public void setNOTADIFERIDO(int NOTADIFERIDO) {
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

    public String getIDLOCAL() {
        return IDLOCAL;
    }

    public void setIDLOCAL(String IDLOCAL) {
        this.IDLOCAL = IDLOCAL;
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
