package App;

import Utils.Colors;
import Controller.Controller;
import Models.*;
import Utils.EntradaDatos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class IncidenciasBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";

    public static ArrayList<Empleado> empleados = new ArrayList<>();
    public static List<Incidencia> incidencias = new ArrayList<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            switch (menu()) {
                case 1:
                    workWithIncidencias();
                    break;
                case 2:
                    workWithEmpleados();
                    break;
                case 3:
                    System.out.println("See You!!");
                    salir = true;
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
        System.out.println("1. Incidencias");
        System.out.println("2. Empleados");
        System.out.println("3. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

    private static void workWithIncidencias() {
        boolean salirInci = false;
        String table = "";
        while (!salirInci) {
            switch (menuInci()) {
                case "a":
                    getIncidenciaByID();
                    break;
                case "b":
                    getAllIncidencias();
                    break;
                case "c":
                    createIncidencia();
                    break;
                case "d":
                    table = "destino";
                    showAllIncidenciasFrom(table);
                    break;
                case "e":
                    table = "origen";
                    showAllIncidenciasFrom(table);
                    break;
                case "f":
                    salirInci = true;
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }
        }
    }

    private static void workWithEmpleados() {
        boolean salirEmp = false;
        while (!salirEmp) {
            switch (menuEmp()) {
                case "a":
                    createEmpleado();
                    break;
                case "b":
                    loginEmpleado();
                    break;
                case "c":
                    modifyEmpleado();
                    break;
                case "d":
                    changePasswordEmpleado();
                    break;
                case "e":
                    deleteEmpleado();
                    break;
                case "f":
                    salirEmp = true;
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }
        }

    }

    private static String menuEmp() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("a. Insertar");
        System.out.println("b. Login");
        System.out.println("c. Modificar");
        System.out.println("d. Cambiar password");
        System.out.println("e. Eliminar ");
        System.out.println("f. Exit");
        return entrada.next();
    }

    private static String menuInci() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("a. Mostrar 1 Incidencia");
        System.out.println("b. Mostrar Incidencias");
        System.out.println("c. Insertar Incidencia");
        System.out.println("d. Mostrar Incidencia gestionadas por Empleado");
        System.out.println("e. Mostrar Incidencia creadas por Empleado");
        System.out.println("f. Exit");
        return entrada.next();
    }

    private static void getAllIncidencias() {
        incidencias = Controller.queryAllIncidencias();
        incidencias.forEach((incidencia) -> {
            System.out.println(incidencia.toString());
        });
    }

    private static void getIncidenciaByID() {
        int idInci = EntradaDatos.pedirEntero("Numero de inci1dencia: ");
        incidencias = Controller.queryIncidenciaByID(idInci);
        System.out.println(incidencias);
    }

    private static void createIncidencia() {
        String fechahora = EntradaDatos.pedirCadena("Fecha de la incidencias: YY/MM/DD ");
        String detalle = EntradaDatos.pedirCadena("Detalles de la incidencia: ");
        String userOrigen = EntradaDatos.pedirCadena("Nombre Usuario del origen: ");
        String userDestino = EntradaDatos.pedirCadena("Nombre Usuario del destino: ");
        ArrayList<String> wordsAccepted = new ArrayList<>();
        wordsAccepted.add("Urgente");
        wordsAccepted.add("Normal");
        String tipo = EntradaDatos.askString("Tipo de incidencia: ", wordsAccepted);

        if (Controller.getEmpleado(userOrigen) == null || Controller.getEmpleado(userDestino) == null) {
            System.out.println("ERROR: user Origen or user Destino no exist.");
            return;
        }
        Empleado empleadoByOrigen = Controller.getEmpleado(userOrigen);
        Empleado empleadoByDestino = Controller.getEmpleado(userDestino);
        Incidencia incidencia = new Incidencia(empleadoByOrigen, empleadoByDestino, fechahora, detalle, tipo);
        Controller.addIncidencia(incidencia);
    }

    private static void createEmpleado() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        String password = EntradaDatos.pedirCadena("Password: ");
        String completName = EntradaDatos.pedirCadena("Nombre completo: ");
        String phone = EntradaDatos.pedirCadena("Telefono:");
        if (Controller.getEmpleado(userName) != null) {
            System.out.println("Ya existe un Usuario con ese Nombre de Usuario!");
            return;
        }

        Controller.addEmpleado(userName, password, completName, phone);
    }

    private static void deleteEmpleado() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario a eliminar: ");

        if (Controller.getEmpleado(userName) != null) {
            Empleado empleado = Controller.getEmpleado(userName);
            Controller.deleteEmpleado(empleado);
        } else {
            System.out.println("No existe usuario con ese Nombre de Usuario");
        }
    }

    private static void loginEmpleado() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        String password = EntradaDatos.pedirCadena("Password: ");

        Empleado empleado = Controller.getEmpleado(userName);
        if (empleado.getPassword().equalsIgnoreCase(password)) {
            System.out.println("you entered correctly!");
        } else {
            System.out.println("wrong password!");
        }
    }

    private static void modifyEmpleado() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        Empleado empleado = Controller.getEmpleado(userName);

        boolean salirEdit = false;

        while (!salirEdit) {
            switch (menuEdit(empleado)) {
                case 1:
                    String completName = EntradaDatos.pedirCadena("Nuevo Nombre Completo: ");
                    Controller.modifyEmpleado(empleado, "nombrecompleto", completName);
                    break;
                case 2:
                    String telefono = EntradaDatos.pedirCadena("Nuevo Telefono: ");
                    Controller.modifyEmpleado(empleado, "telefono", telefono);
                    break;
                case 3:
                    salirEdit = true;
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }
        }
    }

    private static int menuEdit(Empleado empleado) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- EDIT --");
        System.out.println("1. Nombre: " + Colors.returnBlue(empleado.getNombrecompleto()));
        System.out.println("2. Telefono: " + Colors.returnBlue(empleado.getTelefono()));
        System.out.println("3. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

    private static void changePasswordEmpleado() {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        if (Controller.getEmpleado(userName) == null) {
            System.out.println("No existe usuario con ese Nombre de Usuario");
            return;
        }
        Empleado empleado = Controller.getEmpleado(userName);
        String pass = EntradaDatos.pedirCadena("Nueva Contraseña: ");

        Controller.modifyEmpleado(empleado, "password", pass);
    }

    private static void showAllIncidenciasFrom(String table) {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario del Destinatario: ");
        if (Controller.getEmpleado(userName) == null) {
            System.out.println("No existe usuario con ese Nombre de Usuario");
            return;
        }
        incidencias = Controller.queryAllIncidenciasFromDestino(table, userName);

        if (incidencias.isEmpty()) {
            System.out.println("Este usuario no tiene Incidencias para " + table);
        } else {
            incidencias.forEach((incidencia) -> {
                System.out.println(incidencia.toStringDestino());
            });
        }
    }
}
