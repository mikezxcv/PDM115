package sv.edu.ues.fia.eisi.pdm115;

public class MotivoNoImpresion {
    private int idMotivo;
    private String motivoNoImpresion;

    MotivoNoImpresion(int idMotivo, String motivoNoImpresion){
        this.idMotivo = idMotivo;
        this.motivoNoImpresion = motivoNoImpresion;
    }

    public MotivoNoImpresion(){}

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getMotivoNoImpresion() {
        return motivoNoImpresion;
    }

    public void setMotivoNoImpresion(String motivoNoImpresion) {
        this.motivoNoImpresion = motivoNoImpresion;
    }
}
