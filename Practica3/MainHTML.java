import java.util.Scanner;

/**
 * Clase principal que permite al usuario ingresar una cadena HTML
 * y valida si sus etiquetas están correctamente balanceadas.
 */
public class MainHTML {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("--- Validador HTML ---");
        System.out.println("Ingresa la cadena HTML:");

        scan.nextLine();
        String html = scan.nextLine();

        boolean resultado = HTML.isHTMLMatched(html);

        if (resultado) {
            System.out.println("Las etiquetas están balanceadas.");
        } else {
            System.out.println("Las etiquetas no están balanceadas.");
        }

        scan.close();
    }
}
