package sv.edu.ues.fia.eisi.pdm115.docente;

public class Locales {
    private String idLocal;
    private String nombreLocal;
    private String ubicacion;


    public Locales(String idLocal, String nombreLocal, String ubicacion) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.ubicacion = ubicacion;
    }
    public Locales(){

    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
