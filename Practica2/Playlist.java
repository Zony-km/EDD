import java.util.Iterator;

/**
 * Clase que gestiona una lista de reproducción musical (Playlist).
 * Utiliza una estructura de Lista Doblemente Ligada para permitir la navegación 
 * bidireccional, gestión de favoritos y control de reproducción circular.
 * * @author Luis Yamil Antonio Sanchez Segura
 */
public class Playlist {

    private ListaDoblementeLigada<Cancion> lista;
    private Cancion actual;
    private boolean modoCircular;

    /**
     * Constructor de la clase. Inicializa la lista interna de canciones
     * y establece el estado de reproducción inicial como nulo.
     */
    public Playlist() {
        this.lista = new ListaDoblementeLigada<>();
        this.actual = null;
        this.modoCircular = false;
    }


    /**
     * Verifica si la lista de reproducción carece de elementos.
     * @return true si la lista está vacía, false en caso contrario.
     */
    private boolean validarVacio() {
        if (lista.estaVacio()) {
            System.out.println("La playlist está vacía.");
            return true;
        }
        return false;
    }

    /**
     * Configura el comportamiento de la lista al llegar al final de la misma.
     * @param valor true para activar el bucle infinito, false para detenerse al final.
     */
    public void activarModoCircular(boolean valor) {
        this.modoCircular = valor;
        System.out.println("Modo circular: " + (modoCircular ? "ACTIVADO" : "DESACTIVADO"));
    }


    /**
     * Inserta una nueva canción al principio de la lista de reproducción.
     * @param c Objeto Cancion a integrar.
     */
    public void agregarInicio(Cancion c) {
        lista.agregar(0, c);
        if (actual == null) actual = c;
    }

    /**
     * Inserta una nueva canción al final de la lista de reproducción.
     * @param c Objeto Cancion a integrar.
     */
    public void agregarFinal(Cancion c) {
        lista.agregar(c);
        if (actual == null) actual = c;
    }

    /**
     * Elimina una canción de la lista basándose en su título.
     * Si la canción eliminada es la que se está reproduciendo, avanza a la siguiente.
     * @param titulo Nombre de la canción a remover.
     */
    public void eliminar(String titulo) {
        if (validarVacio()) return;

        Cancion encontrada = buscar(titulo);
        if (encontrada == null) {
            System.out.println("No existe una canción con ese título.");
            return;
        }

        if (actual != null && actual.equals(encontrada)) {
            avanzarAutomatico();
        }

        lista.eliminar(encontrada);
        System.out.println("Canción eliminada.");
    }


    /**
     * Inicia la reproducción de la canción marcada como actual.
     */
    public void reproducir() {
        if (validarVacio()) return;

        System.out.println("Reproduciendo:");
        System.out.println(actual);
    }

    /**
     * Realiza un avance de pista de forma automática.
     */
    public void avanzarAutomatico() {
        siguiente();
    }

    /**
     * Desplaza el puntero de reproducción a la siguiente canción en la lista.
     * Considera el modo circular para retornar al inicio si es necesario.
     */
    public void siguiente() {
        if (lista.estaVacio()) return;

        Iterator<Cancion> it = lista.iterador();
        while (it.hasNext()) {
            Cancion c = it.next();
            if (c.equals(actual)) {
                if (it.hasNext()) {
                    actual = it.next();
                } else if (modoCircular) {
                    actual = lista.primerElemento();
                } else {
                    System.out.println("No hay más canciones después de esta.");
                }
                return;
            }
        }
    }

    /**
     * Desplaza el puntero de reproducción a la canción previa en la lista.
     * Considera el modo circular para saltar al final si se está en la primera pista.
     */
    public void anterior() {
        if (lista.estaVacio()) return;

        // Recuperación de la última canción para soporte circular
        Iterator<Cancion> it = lista.iterador();
        Cancion ultima = null;
        while (it.hasNext()) {
            ultima = it.next();
        }

        it = lista.iterador();
        Cancion previo = null;

        while (it.hasNext()) {
            Cancion c = it.next();
            if (c.equals(actual)) {
                if (previo != null) {
                    actual = previo;
                } else if (modoCircular) {
                    actual = ultima;
                } else {
                    System.out.println("No hay canciones antes de esta.");
                }
                return;
            }
            previo = c;
        }
    }

    /**
     * Salta directamente a una canción específica basándose en su posición numérica.
     * @param numero Índice visual (empezando en 1) de la canción.
     */
    public void irA(int numero) {
        if (validarVacio()) return;

        if (numero < 1) {
            System.out.println("El número debe ser mayor o igual a 1.");
            return;
        }

        Iterator<Cancion> it = lista.iterador();
        int i = 1;

        while (it.hasNext()) {
            Cancion c = it.next();
            if (i == numero) {
                actual = c;
                System.out.println("Ahora reproduciendo:");
                System.out.println(actual);
                return;
            }
            i++;
        }
        System.out.println("No existe una canción con ese número.");
    }


    /**
     * Busca una canción dentro de la lista por su título.
     * @param titulo Nombre de la canción.
     * @return El objeto Cancion si se encuentra, null en caso contrario.
     */
    public Cancion buscar(String titulo) {
        Iterator<Cancion> it = lista.iterador();
        while (it.hasNext()) {
            Cancion c = it.next();
            if (c.obtenerTitulo().equalsIgnoreCase(titulo)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Calcula la suma de las duraciones de todas las canciones en la lista.
     * @return Total de segundos de la playlist.
     */
    public int duracionTotal() {
        if (validarVacio()) return 0;

        Iterator<Cancion> it = lista.iterador();
        int suma = 0;
        while (it.hasNext()) {
            suma += it.next().obtenerDuracion();
        }
        return suma;
    }

    /**
     * Despliega en consola la lista completa de canciones, marcando 
     * con un indicador la canción que se encuentra en reproducción.
     */
    public void mostrar() {
        if (validarVacio()) return;

        Iterator<Cancion> it = lista.iterador();
        int i = 1;
        while (it.hasNext()) {
            Cancion c = it.next();
            if (actual != null && c.equals(actual)) {
                System.out.print("> ");
            }
            System.out.println(i + ". " + c);
            i++;
        }
    }

    // Seccion de Favorito

    /**
     * Marca una canción como favorita buscando por su título.
     * @param titulo Nombre de la canción a destacar.
     */
    public void marcarFavorita(String titulo) {
        Cancion c = buscar(titulo);
        if (c != null) {
            c.marcarFavorita(true);
            System.out.println("Marcada como favorita.");
        } else {
            System.out.println("No existe esa canción.");
        }
    }

    /**
     * Filtra y muestra únicamente las canciones que han sido marcadas como favoritas.
     */
    public void mostrarFavoritas() {
        if (validarVacio()) return;

        Iterator<Cancion> it = lista.iterador();
        boolean hayFavoritas = false;

        System.out.println("Canciones favoritas:");
        while (it.hasNext()) {
            Cancion c = it.next();
            if (c.esFavorita()) {
                System.out.println(c);
                hayFavoritas = true;
            }
        }

        if (!hayFavoritas) {
            System.out.println("No hay canciones favoritas.");
        }
    }

}// Fin de la clase Playlist