package Models;

/**
 *
 * @author uriishii
 */
public class Empleado {

    String username;
    String password;
    String completName;
    String phone;

    public Empleado(String username, String password, String completName, String phone) {
        this.username = username;
        this.password = password;
        this.completName = completName;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompletName() {
        return completName;
    }

    public void setCompletName(String completName) {
        this.completName = completName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
