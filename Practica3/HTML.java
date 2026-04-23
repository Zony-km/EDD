/**
 * Clase que proporciona un método para verificar si una cadena HTML
 * contiene etiquetas correctamente balanceadas.
 */
public class HTML {

    /**
     * Verifica si las etiquetas HTML dentro de una cadena están correctamente
     * emparejadas y anidadas. Solo considera etiquetas simples de apertura y
     * cierre,
     *
     * @param html cadena que contiene el código HTML a evaluar
     * @return true si todas las etiquetas están correctamente balanceadas,
     *         false en caso contrario
     */
    public static boolean isHTMLMatched(String html) {
        Stack<String> pila = new Stack<>();

        int i = 0;

        while (i < html.length()) {

            // Detectar inicio de etiqueta
            if (html.charAt(i) == '<') {

                int j = html.indexOf('>', i);

                if (j == -1) {
                    // Etiqueta mal formada
                    return false;
                }

                String etiqueta = html.substring(i + 1, j).trim();

                // Etiqueta de cierre
                if (etiqueta.startsWith("/")) {
                    String nombre = etiqueta.substring(1);

                    if (pila.isEmpty()) {
                        return false;
                    }

                    String abierta = pila.pop();

                    if (!abierta.equals(nombre)) {
                        return false;
                    }

                } else {
                    // Etiqueta de apertura
                    pila.push(etiqueta);
                }

                i = j + 1;

            } else {
                i++;
            }
        }

        // Si la pila queda vacía, todas las etiquetas fueron cerradas correctamente
        return pila.isEmpty();
    }
}
