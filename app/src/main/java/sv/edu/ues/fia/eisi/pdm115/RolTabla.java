package sv.edu.ues.fia.eisi.pdm115;

public class RolTabla {
    private String ID_ROL;
    private String NOMBRE_ROL;

    public RolTabla() {}
    public RolTabla(String ID_ROL, String NOMBRE_ROL) {
        this.ID_ROL = ID_ROL;
        this.NOMBRE_ROL = NOMBRE_ROL;
    }

    public String getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(String ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public String getNOMBRE_ROL() {
        return NOMBRE_ROL;
    }

    public void setNOMBRE_ROL(String NOMBRE_ROL) {
        this.NOMBRE_ROL = NOMBRE_ROL;
    }
}
