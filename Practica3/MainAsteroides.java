import java.util.Scanner;

/**
 * Clase principal que permite interactuar con el usuario para simular
 * colisiones entre asteroides. Solicita la cantidad de asteroides,
 * sus valores y muestra el resultado final después de procesar las colisiones.
 */
public class MainAsteroides {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int tamano;

        System.out.println("--- Simulador de Asteroides ---");

        // Validación del tamano del arreglo
        do {
            System.out.print("Ingrese la cantidad de asteroides: ");
            tamano = scan.nextInt();

            if (tamano <= 0) {
                System.out.println("El tamano debe ser mayor que 0.");
            }

        } while (tamano <= 0);

        int[] asteroides = new int[tamano];

        // Llenado del arreglo con validación
        for (int i = 0; i < tamano; i++) {
            int valor;

            do {
                System.out.print("Asteroide " + (i + 1) + ": ");
                valor = scan.nextInt();

                if (valor == 0) {
                    System.out.println("El valor no puede ser 0.");
                }

            } while (valor == 0);

            asteroides[i] = valor;
        }

        // Procesar colisiones
        int[] resultado = Asteroides.resolver(asteroides);

        // Mostrar resultado final
        System.out.print("\nAsteroides sobrevivientes: [");
        for (int i = 0; i < resultado.length; i++) {
            System.out.print(resultado[i]);
            if (i < resultado.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        scan.close();
    }
}
