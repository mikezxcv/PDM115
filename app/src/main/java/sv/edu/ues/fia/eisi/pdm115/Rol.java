package sv.edu.ues.fia.eisi.pdm115;

public class Rol {
    String ID_ROL;
    String NOMBRE_ROL;

    public Rol() {}
    public Rol(String ID_ROL, String NOMBRE_ROL) {
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
