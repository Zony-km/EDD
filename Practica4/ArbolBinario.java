import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario<T extends Comparable<T>> implements TDAArbolBinario<T>, Serializable {

    private class Vertice implements VerticeArbolBinario<T>, Serializable {
        public T elemento;
        public Vertice padre, izquierdo, derecho;

        public Vertice(T elemento, Vertice padre) {
            this.elemento = elemento;
            this.padre = padre;
        }

        @Override
        public boolean hayPadre() {
            return padre != null;
        }

        @Override
        public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        @Override
        public boolean hayDerecho() {
            return derecho != null;
        }

        @Override
        public VerticeArbolBinario<T> padre() {
            return padre;
        }

        @Override
        public VerticeArbolBinario<T> izquierdo() {
            return izquierdo;
        }

        @Override
        public VerticeArbolBinario<T> derecho() {
            return derecho;
        }

        @Override
        public T get() {
            return elemento;
        }

        @Override
        public int altura() {
            return 1 + Math.max(
                    izquierdo == null ? -1 : izquierdo.altura(),
                    derecho == null ? -1 : derecho.altura());
        }

        @Override
        public int profundidad() {
            return padre == null ? 0 : 1 + padre.profundidad();
        }
    }

    private Vertice raiz;

    @Override
    public void agregar(T elemento) {
        if (raiz == null) {
            raiz = new Vertice(elemento, null);
            return;
        }

        Queue<Vertice> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Vertice actual = cola.poll();

            if (actual.izquierdo == null) {
                actual.izquierdo = new Vertice(elemento, actual);
                return;
            } else {
                cola.add(actual.izquierdo);
            }

            if (actual.derecho == null) {
                actual.derecho = new Vertice(elemento, actual);
                return;
            } else {
                cola.add(actual.derecho);
            }
        }
    }

    /**
     * Metodo para agregar nuevo vértice en un lugar especifico
     * 
     * @param elemento      por agregar
     * @param padreElemento padre del nuevo vértice
     */
    public void agregar(T elemento, T padreElemento) {
        if (padreElemento == null) {
            if (raiz == null) {
                raiz = new Vertice(elemento, null);
            } else {
                throw new IllegalStateException("La raiz ya existe");
            }
            return;
        }
        Vertice padre = buscar(raiz, padreElemento);

        if (padre == null) {
            throw new IllegalArgumentException("Padre no existe");
        }

        Vertice nuevo = new Vertice(elemento, padre);

        if (!padre.hayIzquierdo()) {
            padre.izquierdo = nuevo;
        } else if (!padre.hayDerecho()) {
            padre.derecho = nuevo;
        } else {
            throw new IllegalStateException("El padre ya tiene dos hijos");
        }
    }

    @Override
    public VerticeArbolBinario<T> raiz() {
        return raiz;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public void limpiar() {
        raiz = null;
    }

    @Override
    public int altura() {
        return raiz == null ? -1 : raiz.altura();
    }

    @Override
    public int profundidad() {
        // Nota: La profundidad del árbol es igual a su altura.
        return raiz == null ? -1 : raiz.altura();
    }

    private Vertice buscar(Vertice actual, T elemento) {
        if (actual == null)
            return null;
        if (actual.elemento.equals(elemento))
            return actual;

        Vertice izq = buscar(actual.izquierdo, elemento);
        if (izq != null)
            return izq;

        return buscar(actual.derecho, elemento);
    }

    @Override
    public void eliminar(T elemento) {
        if (raiz == null)
            return;

        Queue<Vertice> cola = new LinkedList<>();
        cola.add(raiz);

        Vertice nodoEliminar = null;
        Vertice ultimo = null;

        while (!cola.isEmpty()) {
            ultimo = cola.poll();

            if (ultimo.elemento.equals(elemento)) {
                nodoEliminar = ultimo;
            }
            if (ultimo.izquierdo != null) {
                cola.add(ultimo.izquierdo);
            }
            if (ultimo.derecho != null) {
                cola.add(ultimo.derecho);
            }
        }

        if (nodoEliminar != null) {
            nodoEliminar.elemento = ultimo.elemento;
            eliminarUltimo(raiz, ultimo);
        }
    }

    private void eliminarUltimo(Vertice actual, Vertice objetivo) {
        if (actual == null)
            return;
        if (actual.izquierdo == objetivo) {
            actual.izquierdo = null;
            return;
        }
        if (actual.derecho == objetivo) {
            actual.derecho = null;
            return;
        }
        eliminarUltimo(actual.izquierdo, objetivo);
        eliminarUltimo(actual.derecho, objetivo);
    }

    @Override
    public String toString() {
        if (raiz == null)
            return "Árbol vacío";
        StringBuilder sb = new StringBuilder();
        imprimirArbol(raiz, 0, sb);
        return sb.toString();
    }

    private void imprimirArbol(Vertice v, int nivel, StringBuilder sb) {
        if (v == null)
            return;
        imprimirArbol(v.derecho, nivel + 1, sb);

        sb.append("\n");
        for (int i = 0; i < nivel; i++) {
            sb.append("        ");
        }
        sb.append("|-").append(v.elemento);

        imprimirArbol(v.izquierdo, nivel + 1, sb);
    }

    /**
     * Recorrido PreOrden: Raiz, Izquierda, Derecha
     * 
     * @return String con los vértices en el orden del recorrido
     */
    public String preOrden() {
        return preOrden(raiz).trim();
    }

    private String preOrden(Vertice v) {
        if (v == null)
            return "";
        return v.elemento + " " + preOrden(v.izquierdo) + preOrden(v.derecho);
    }

    /**
     * Recorrido InOrden: Izquierdo, Raiz, Derecho
     * 
     * @return String del recorrido
     */
    public String inOrden() {
        return inOrden(raiz).trim();
    }

    private String inOrden(Vertice v) {
        if (v == null)
            return "";
        return inOrden(v.izquierdo) + v.elemento + " " + inOrden(v.derecho);
    }

    /**
     * Recorrido PosOrden: Izquierdo, Derecho, Raiz
     * 
     * @return String del recorrido
     */
    public String posOrden() {
        return posOrden(raiz).trim();
    }

    private String posOrden(Vertice v) {
        if (v == null)
            return "";
        return posOrden(v.izquierdo) + posOrden(v.derecho) + v.elemento + " ";
    }

    public String bfs() {
        if (raiz == null)
            return "";
        Queue<Vertice> cola = new LinkedList<>();
        cola.add(raiz);

        String resultado = "";

        while (!cola.isEmpty()) {
            Vertice v = cola.poll();
            resultado += v.elemento + " ";

            if (v.izquierdo != null)
                cola.add(v.izquierdo);
            if (v.derecho != null)
                cola.add(v.derecho);
        }
        return resultado.trim();
    }

}