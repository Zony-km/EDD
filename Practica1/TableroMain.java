import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase principal que ejecuta el menú del juego.
 */
public class TableroMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero miJuego = new Tablero();
        int opcion = -1;

        System.out.println("=== PICROSS 5x5 ===");
        System.out.println("El juego termina cuando hayas completado el tablero correcto.");

        // Bucle principal del menú
        do {
            try {
                miJuego.mostrarTablero();

                System.out.println("\n--- Menú de Juego ---");
                System.out.println("1. Realizar Jugada");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                // Validamos si la entrada es un número antes de leerlo
                if (!scanner.hasNextInt()) {
                    System.out.println("\n>>> ERROR: Entrada no válida. Ingrese un número.");
                    scanner.next(); // Limpia la entrada incorrecta
                    continue;
                }

                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Fila (0-4): ");
                        int fila = scanner.nextInt();
                        System.out.print("Columna (0-4): ");
                        int col = scanner.nextInt();
                        System.out.print("Acción (1: Marcar, 2: Borrar): ");
                        int accion = scanner.nextInt();
                        scanner.nextLine();

                        miJuego.realizarJugada(fila, col, accion);

                        // Verificación automática de victoria
                        if (miJuego.verificarVictoria()) {
                            miJuego.mostrarTablero();
                            System.out.println("\n¡FELICIDADES! Has ganado el juego.");
                            opcion = 0;
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no reconocida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n>>> ERROR: Se esperaba un número entero.");
                scanner.nextLine(); // Limpiar entrada en caso de error
            } catch (EntradaInvalidaException e) {
                System.out.println("\n>>> ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n>>> ERROR INESPERADO: " + e.getMessage());
                if (scanner.hasNext()) scanner.nextLine();
            }

        } while (opcion != 0);

        scanner.close();
    }
}