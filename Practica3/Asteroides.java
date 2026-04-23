/**
 * Clase que contiene la lógica para resolver colisiones entre asteroides.
 */
public class Asteroides {

    /**
     * Procesa una lista de asteroides y determina cuáles sobreviven después
     * de todas las colisiones posibles.
     *
     * @param asteroides arreglo de enteros que representa los asteroides y sus
     *                   direcciones
     * @return un nuevo arreglo con los asteroides que sobreviven
     */
    public static int[] resolver(int[] asteroides) {
        Stack<Integer> pila = new Stack<>();

        for (int ast : asteroides) {
            boolean destruido = false;

            // Manejo de posibles colisiones
            while (!pila.isEmpty() && ast < 0 && pila.top() > 0) {

                int top = pila.top();

                if (Math.abs(ast) > top) {
                    // El asteroide nuevo destruye al de la pila
                    pila.pop();

                } else if (Math.abs(ast) == top) {
                    // Ambos se destruyen
                    pila.pop();
                    destruido = true;
                    break;

                } else {
                    // El nuevo asteroide es destruido
                    destruido = true;
                    break;
                }
            }

            if (!destruido) {
                pila.push(ast);
            }
        }

        // Convertir la pila a arreglo
        int size = 0;
        Stack<Integer> aux = new Stack<>();

        while (!pila.isEmpty()) {
            aux.push(pila.pop());
            size++;
        }

        int[] resultado = new int[size];
        int i = 0;

        while (!aux.isEmpty()) {
            resultado[i++] = aux.pop();
        }

        return resultado;
    }
}
