package sv.edu.ues.fia.eisi.pdm115;

import androidx.annotation.Nullable;

public class EncargadoDeImpresiones {

    private int idEncargado;
    private String idEscuela;
    private String nombreEncargado;
    private String apellidoEncargado;
    private String usuario;
    private String idOpcion;

    EncargadoDeImpresiones(){}

    EncargadoDeImpresiones(int idEncargado, String idEscuela, String nombreEncargado, String apellidoEncargado, String usuario, @Nullable String idOpcion){
        this.idEncargado = idEncargado;
        this.idEscuela = idEscuela;
        this.nombreEncargado = nombreEncargado;
        this.apellidoEncargado = apellidoEncargado;
        this.usuario = usuario;
        this.idOpcion = idOpcion;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getApellidoEncargado() {
        return apellidoEncargado;
    }

    public void setApellidoEncargado(String apellidoEncargado) {
        this.apellidoEncargado = apellidoEncargado;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

}
