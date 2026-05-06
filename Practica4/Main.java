/**
 * Clase principal del proyecto.
 * Se encarga de inicializar el sistema de 20 preguntas, ejecutar el juego
 * y guardar los cambios realizados durante la sesión.
 *
 */
public class Main {
    public static void main(String[] args) {

        Sistema s = new Sistema();

        System.out.println("Bienvenido al sistema de 20 preguntas.");
        System.out.println("Piensa en un objeto y responde únicamente con 'si' o 'no'.");
        System.out.println("-----------------------------------------------------------");

        s.iniciar();

        System.out.println("Guardando información...");
        s.cerrar();

        System.out.println("El programa ha finalizado.");
    }
} // Fin de la clase Main