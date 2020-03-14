package App;

import Controller.Controller;
import Models.*;
import Utils.Colors;
import Utils.EntradaDatos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class HistorialBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";

    public static List<Historial> historiales = new ArrayList<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            switch (menu()) {
                case 1:
                    System.out.println("En mantenimiento");
                    break;
                case 2:
                    getLastConnect();
                    break;
                case 3:
                    getRankigEmpleados();
                    break;
                case 4:
                    System.out.println("En mantenimiento");
                    break;
                case 5:
                    System.out.println("See You!!");
                    System.exit(0);
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }
        }
    }

    private static int menu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("1. Insertar evento");
        System.out.println("2. Obtener ultima conexion");
        System.out.println("3. Obtener ranking");
        System.out.println("4. Obtener posicion dentro del ranking");
        System.out.println("5. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

    private static void getLastConnect() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");

        if (Controller.getEmpleado(userName) == null) {
            System.out.println("No existe usuario con ese Nombre de Usuario");
            return;
        }
        historiales = Controller.queryLastConnectEmpleado(userName);
        if (historiales.isEmpty()) {
            System.out.println("Este empleado no tiene una hora de conexion");
            return;
        }
        System.out.println(historiales.get(0).toStringFechaHora());
    }

    private static void getRankigEmpleados() {
        historiales = Controller.queryRankingEmpleados();
        historiales.forEach((historial -> {
            System.out.println(historial.toString());
        }));
    }
}
