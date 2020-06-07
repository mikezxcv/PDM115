package sv.edu.ues.fia.eisi.pdm115;

public class AreaTabla {
    private String ID_AREA;
    private String ID_ROL;
    private String NOMBRE_AREA;

    public AreaTabla() {
    }

    public AreaTabla(String ID_AREA, String ID_ROL, String NOMBRE_AREA) {
        this.ID_AREA = ID_AREA;
        this.ID_ROL = ID_ROL;
        this.NOMBRE_AREA = NOMBRE_AREA;
    }

    public String getID_AREA() {
        return ID_AREA;
    }

    public void setID_AREA(String ID_AREA) {
        this.ID_AREA = ID_AREA;
    }

    public String getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(String ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public String getNOMBRE_AREA() {
        return NOMBRE_AREA;
    }

    public void setNOMBRE_AREA(String NOMBRE_AREA) {
        this.NOMBRE_AREA = NOMBRE_AREA;
    }
}
