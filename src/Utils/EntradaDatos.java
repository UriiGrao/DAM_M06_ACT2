/*
 * Funciones para solicitar datos al usuario (por teclado)
 */
package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author mfontana
 */
public class EntradaDatos {

    public static String pedirCadena(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cadena = "";
        do {
            try {
                System.out.println(texto);
                cadena = br.readLine();
                if (cadena.equals("")) {
                    System.out.println("No se puede dejar el campo en blanco.");
                }

            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
            }
        } while (cadena.equals(""));
        return cadena;
    }

    public static double pedirDouble(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número.");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int pedirEntero(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número entero.");
                error = true;
            }
        } while (error);
        return num;
    }

    // Método que indica si un String es un entero o no
    public static boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // numero random con el maximo incluido.
    public static int numAleatorio(int max) {
        Random r = new Random();
        int num = r.nextInt(max + 1);
        return num;
    }

    /**
     * Request String from a ArrayList of String accepteds
     *
     * @param message String
     * @param wordsAccepted ArrayList of String
     * @return String
     */
    public static String askString(String message, ArrayList<String> wordsAccepted) {
        String answer;
        boolean wordOk;
        do {
            for (String word : wordsAccepted) {
                System.out.println(word);
            }
            answer = pedirCadena(message);
            wordOk = wordIsOk(answer, wordsAccepted);
            if (!wordOk) {
                System.out.println("Wrong answer!");
            }
        } while (!wordOk);
        return answer;
    }

    public static int askInt(String message, ArrayList<Integer> wordsAccepted) {
        int answer;
        boolean wordOk;
        do {
            for (int word : wordsAccepted) {
                System.out.println(word);
            }
            answer = pedirEntero(message);
            wordOk = wordIsOk(answer, wordsAccepted);
            if (!wordOk) {
                System.out.println("Wrong answer!");
            }
        } while (!wordOk);
        return answer;

    }

    /**
     * Returns true if the word exists in wordsAccepted.
     *
     * @param word
     * @param wordsAccepted
     * @return boolean
     */
    private static boolean wordIsOk(String word, ArrayList<String> wordsAccepted) {
        for (String w : wordsAccepted) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    private static boolean wordIsOk(int word, ArrayList<Integer> wordsAccepted) {
        for (int w : wordsAccepted) {
            if (w == word) {
                return true;
            }
        }
        return false;
    }
}
