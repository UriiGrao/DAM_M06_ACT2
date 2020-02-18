package Models;

/**
 *
 * @author uriishii
 */
public class Historial {
    String tipoEvento;
    String date;
    String username;

    public Historial(String tipoEvento, String date, String username) {
        this.tipoEvento = tipoEvento;
        this.date = date;
        this.username = username;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
