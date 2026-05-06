/**
 * Interfaz que define el comportamiento dde un nodo en un árbol binario
 * 
 */
public interface VerticeArbolBinario<T> {

    /**
     * Método que nos idce si un vértice tiene un nodo padre
     * 
     * @return boolean True si tiene padre y False si no lo tiene
     */
    public boolean hayPadre();

    /**
     * Método que nos dice si un vértice tiene hijo izquierdo
     * 
     * @return boolean True si tiene hijo izquierdo
     */
    public boolean hayIzquierdo();

    /**
     * Método que nos dice si un vértice tiene hijo derecho
     * 
     * @return boolean True si tiene hijo derecho
     */
    public boolean hayDerecho();

    /**
     * Devuelve el vértice padre
     * 
     * @return Vertice padre
     */
    public VerticeArbolBinario<T> padre();

    /**
     * Devuelve el vértice hijo izquierdo
     * 
     * @return vértice izquierdo
     */
    public VerticeArbolBinario<T> izquierdo();

    /**
     * Devuelve el vértice hijo derecho
     * 
     * @return vértice derecho
     */
    public VerticeArbolBinario<T> derecho();

    /**
     * Devuelve la altura de un vértice
     * 
     * @return int distancia máxima desde un vértice hasta una hoja
     */
    public int altura();

    /**
     * Devuelve la profundidad del vértice
     * 
     * @return int distancia de aristas desde la raiz al vértice
     */
    public int profundidad();

    /**
     * Devuelve el elemento almacenado en el vértice
     * 
     * @return T elemento
     */
    public T get();
}
