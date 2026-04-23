/**
 * Excepción personalizada para capturar errores de lógica del juego.
 */
public class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}