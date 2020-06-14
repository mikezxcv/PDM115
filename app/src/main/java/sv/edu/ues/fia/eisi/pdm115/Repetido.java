package sv.edu.ues.fia.eisi.pdm115;

public class Repetido {
    int IDREPETIDO;
    int ID_DETALLEALUMNOSEVALUADOS;
    String FECHASOLICITUDREPETIDO;
    int ESTADOREPETIDO;
    String FECHAREPETIDO;
    String HORAREPETIDO;
    int NOTADESPUESREPETIDO;
    int NOTAANTESREPETIDO;
    String OBSERVACIONES;
    String MATERIA;
    String LOCAL;

    public Repetido() {
    }

    public Repetido(int IDREPETIDO, int ID_DETALLEALUMNOSEVALUADOS, String FECHASOLICITUDREPETIDO, int ESTADOREPETIDO, String FECHAREPETIDO, String HORAREPETIDO, int NOTADESPUESREPETIDO, int NOTAANTESREPETIDO, String OBSERVACIONES, String MATERIA, String LOCAL) {
        this.IDREPETIDO = IDREPETIDO;
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
        this.FECHASOLICITUDREPETIDO = FECHASOLICITUDREPETIDO;
        this.ESTADOREPETIDO = ESTADOREPETIDO;
        this.FECHAREPETIDO = FECHAREPETIDO;
        this.HORAREPETIDO = HORAREPETIDO;
        this.NOTADESPUESREPETIDO = NOTADESPUESREPETIDO;
        this.NOTAANTESREPETIDO = NOTAANTESREPETIDO;
        this.OBSERVACIONES = OBSERVACIONES;
        this.MATERIA = MATERIA;
        this.LOCAL = LOCAL;
    }

    public int getIDREPETIDO() {
        return IDREPETIDO;
    }

    public void setIDREPETIDO(int IDREPETIDO) {
        this.IDREPETIDO = IDREPETIDO;
    }

    public int getID_DETALLEALUMNOSEVALUADOS() {
        return ID_DETALLEALUMNOSEVALUADOS;
    }

    public void setID_DETALLEALUMNOSEVALUADOS(int ID_DETALLEALUMNOSEVALUADOS) {
        this.ID_DETALLEALUMNOSEVALUADOS = ID_DETALLEALUMNOSEVALUADOS;
    }

    public int getESTADOREPETIDO() {
        return ESTADOREPETIDO;
    }

    public void setESTADOREPETIDO(int ESTADOREPETIDO) {
        this.ESTADOREPETIDO = ESTADOREPETIDO;
    }

    public int getNOTADESPUESREPETIDO() {
        return NOTADESPUESREPETIDO;
    }

    public void setNOTADESPUESREPETIDO(int NOTADESPUESREPETIDO) {
        this.NOTADESPUESREPETIDO = NOTADESPUESREPETIDO;
    }

    public int getNOTAANTESREPETIDO() {
        return NOTAANTESREPETIDO;
    }

    public void setNOTAANTESREPETIDO(int NOTAANTESREPETIDO) {
        this.NOTAANTESREPETIDO = NOTAANTESREPETIDO;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public String getMATERIA() {
        return MATERIA;
    }

    public void setMATERIA(String MATERIA) {
        this.MATERIA = MATERIA;
    }

    public String getLOCAL() {
        return LOCAL;
    }

    public void setLOCAL(String LOCAL) {
        this.LOCAL = LOCAL;
    }

    public String getFECHASOLICITUDREPETIDO() {
        return FECHASOLICITUDREPETIDO;
    }

    public void setFECHASOLICITUDREPETIDO(String FECHASOLICITUDREPETIDO) {
        this.FECHASOLICITUDREPETIDO = FECHASOLICITUDREPETIDO;
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
}
