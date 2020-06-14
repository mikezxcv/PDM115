package sv.edu.ues.fia.eisi.pdm115;

public class RepetidoTabla {
    String IDREPETIDO;
    String ID_DETALLEALUMNOSEVALUADOS;
    String FECHASOLICITUDREPETIDO;
    String ESTADOREPETIDO;
    String FECHAREPETIDO;
    String HORAREPETIDO;
    String NOTADESPUESREPETIDO;
    String NOTAANTESREPETIDO;
    String MATERIA;
    String LOCAL;
    String OBSERVACIONES;

    public RepetidoTabla(String IDREPETIDO, String ID_DETALLEALUMNOSEVALUADOS, String FECHASOLICITUDREPETIDO, String ESTADOREPETIDO, String FECHAREPETIDO, String HORAREPETIDO, String NOTADESPUESREPETIDO, String NOTAANTESREPETIDO, String MATERIA, String LOCAL, String OBSERVACIONES) {
        this.IDREPETIDO = IDREPETIDO;
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
        this.FECHASOLICITUDREPETIDO = FECHASOLICITUDREPETIDO;
        this.ESTADOREPETIDO = ESTADOREPETIDO;
        this.FECHAREPETIDO = FECHAREPETIDO;
        this.HORAREPETIDO = HORAREPETIDO;
        this.NOTADESPUESREPETIDO = NOTADESPUESREPETIDO;
        this.NOTAANTESREPETIDO = NOTAANTESREPETIDO;
        this.MATERIA = MATERIA;
        this.LOCAL = LOCAL;
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public RepetidoTabla() {
    }

    public String getLOCAL() {
        return LOCAL;
    }

    public void setLOCAL(String LOCAL) {
        this.LOCAL = LOCAL;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public String getIDREPETIDO() {
        return IDREPETIDO;
    }

    public void setIDREPETIDO(String IDREPETIDO) {
        this.IDREPETIDO = IDREPETIDO;
    }

    public String getID_DETALLEALUMNOSEVALUADOS() {
        return ID_DETALLEALUMNOSEVALUADOS;
    }

    public void setID_DETALLEALUMNOSEVALUADOS(String ID_DETALLEALUMNOSEVALUADOS) {
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
    }

    public String getFECHASOLICITUDREPETIDO() {
        return FECHASOLICITUDREPETIDO;
    }

    public void setFECHASOLICITUDREPETIDO(String FECHASOLICITUDREPETIDO) {
        this.FECHASOLICITUDREPETIDO = FECHASOLICITUDREPETIDO;
    }

    public String getESTADOREPETIDO() {
        return ESTADOREPETIDO;
    }

    public void setESTADOREPETIDO(String ESTADOREPETIDO) {
        this.ESTADOREPETIDO = ESTADOREPETIDO;
    }

    public String getFECHAREPETIDO() {
        return FECHAREPETIDO;
    }

    public void setFECHAREPETIDO(String FECHAREPETIDO) {
        this.FECHAREPETIDO = FECHAREPETIDO;
    }

    public String getHORAREPETIDO() {
        return HORAREPETIDO;
    }

    public void setHORAREPETIDO(String HORAREPETIDO) {
        this.HORAREPETIDO = HORAREPETIDO;
    }

    public String getNOTADESPUESREPETIDO() {
        return NOTADESPUESREPETIDO;
    }

    public void setNOTADESPUESREPETIDO(String NOTADESPUESREPETIDO) {
        this.NOTADESPUESREPETIDO = NOTADESPUESREPETIDO;
    }

    public String getNOTAANTESREPETIDO() {
        return NOTAANTESREPETIDO;
    }

    public void setNOTAANTESREPETIDO(String NOTAANTESREPETIDO) {
        this.NOTAANTESREPETIDO = NOTAANTESREPETIDO;
    }

    public String getMATERIA() {
        return MATERIA;
    }

    public void setMATERIA(String MATERIA) {
        this.MATERIA = MATERIA;
    }
}
