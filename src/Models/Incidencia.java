package Models;

/**
 *
 * @author uriishii
 */
public class Incidencia {

    String fechahora;
    String origen;
    String destino;
    String detalle;
    String tipo;

    public Incidencia(String fechahora, String origen, String destino, String detalle, String tipo) {
        this.fechahora = fechahora;
        this.origen = origen;
        this.destino = destino;
        this.detalle = detalle;
        this.tipo = tipo;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
