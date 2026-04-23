/**
 * Clase que gestiona la lógica interna, el estado de los arreglos 
 * y las validaciones del juego Picross (Nonogram) de 5x5.
 * * @author Luis Yamil Antonio Sanchez Segura
 */
public class Tablero {

    private int[][] solucion;
    private int[][] progresoJugador;
    private String[] pistasFilas;
    private String[] pistasColumnas;

    /**
     * Constructor de la clase. Inicializa la solución, el tablero del jugador,
     * los arreglos de pistas y ejecuta el cálculo automático de las mismas.
     */
    public Tablero() {
        this.solucion = createSolution();
        this.progresoJugador = new int[5][5]; // Inicializado con ceros por defecto
        this.pistasFilas = new String[5];
        this.pistasColumnas = new String[5];
        generarPistas();
    }

    /**
     * Define la matriz solución predeterminada para el juego de 5x5.
     * * @return Una matriz de 5x5 con la configuración de celdas marcadas (1) y vacías (0).
     */
    public static int[][] createSolution() {
        return new int[][] {
            {1,0,1,1,0},
            {1,1,1,0,0},
            {1,1,0,0,1},
            {0,1,0,1,1},
            {1,0,0,1,0}
        };
    }

    /**
     * Coordina la generación de pistas numéricas analizando tanto filas como columnas
     * a partir de la matriz solución.
     */
    private void generarPistas() {
        for (int i = 0; i < 5; i++) {
            // Generación de pistas por fila
            pistasFilas[i] = calcularLinea(solucion[i]);
            
            // Extracción de columna para generación de pistas vertical
            int[] col = new int[5];
            for (int j = 0; j < 5; j++) {
                col[j] = solucion[j][i];
            }
            pistasColumnas[i] = calcularLinea(col);
        }
    }

    /**
     * Algoritmo que analiza un arreglo unidimensional para contar bloques 
     * de celdas consecutivas con valor 1.
     * * @param linea Arreglo de enteros que representa una fila o columna.
     * @return Una cadena con los números de las pistas separados por espacios.
     */
    private String calcularLinea(int[] linea) {
        String resultado = "";
        int contador = 0;
        for (int celda : linea) {
            if (celda == 1) {
                contador++;
            } else if (contador > 0) {
                resultado += contador + " ";
                contador = 0;
            }
        }
        if (contador > 0) resultado += contador;
        return resultado.isEmpty() ? "0" : resultado.trim();
    }

    /**
     * Registra el movimiento del jugador en el tablero de progreso, validando 
     * los límites del arreglo y la validez de la acción.
     * * @param f Índice de la fila (0-4).
     * @param c Índice de la columna (0-4).
     * @param accion Código de acción: 1 para Marcar (X), 2 para Borrar (.).
     * @throws EntradaInvalidaException Si los índices están fuera de rango o la acción es desconocida.
     */
    public void realizarJugada(int f, int c, int accion) throws EntradaInvalidaException {
        if (f < 0 || f > 4 || c < 0 || c > 4) {
            throw new EntradaInvalidaException("Coordenadas fuera de rango (0-4).");
        }
        if (accion == 1) {
            progresoJugador[f][c] = 1;
        } else if (accion == 2) {
            progresoJugador[f][c] = 0;
        } else {
            throw new EntradaInvalidaException("Acción inválida (debe ser 1 o 2).");
        }
    }

    /**
     * Compara celda por celda el tablero del jugador contra la solución.
     * * @return true si ambos tableros son idénticos, false en caso contrario.
     */
    public boolean verificarVictoria() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (progresoJugador[i][j] != solucion[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Imprime en la consola el estado visual del tablero, incluyendo 
     * las pistas de columnas y filas para guiar al usuario.
     */
    public void mostrarTablero() {
        System.out.println("\n--- Pistas Columnas ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Col " + i + ": " + pistasColumnas[i]);
        }

        System.out.println("\nTablero Actual:");
        System.out.println("    0 1 2 3 4  | Pistas Filas");
        System.out.println("   ----------- | ------------");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 5; j++) {
                System.out.print((progresoJugador[i][j] == 1 ? "X " : ". "));
            }
            System.out.println("| " + pistasFilas[i]);
        }
    }
}