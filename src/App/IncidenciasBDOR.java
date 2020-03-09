/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Utils.Colors;
import Controller.Controller;
import Models.*;
import Utils.EntradaDatos;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class IncidenciasBDOR {

    public static ArrayList<Empleado> empleados = new ArrayList<>();
    public static ArrayList<Historial> historiales = new ArrayList<>();
    public static ArrayList<Incidencia> incidencias = new ArrayList<>();

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
                break;
                default:
                System.out.println("Error Option try again.");
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
        while (!salirInci) {
            switch (menuInci()) {
                case "a":
                break;
                case "b":
                break;
                case "c":
                break;
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
                System.out.println("Error Option try again.");
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
                System.out.println("Wrong Edit");
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
        Empleado empleado = Controller.getEmpleado(userName);
        String pass = EntradaDatos.pedirCadena("Nueva Contraseña: ");

        Controller.modifyEmpleado(empleado, "password", pass);
    }
}
