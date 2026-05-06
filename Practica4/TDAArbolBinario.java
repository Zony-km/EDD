/**
 * Interfaz que define el TDA para un Árbol Binario
 */
public interface TDAArbolBinario<T> {
    /**
     * Método para agregar un nuevo elemento al arbol
     * 
     * @param T elemento por agregar
     */
    public void agregar(T elemento);

    /**
     * Método para eliminar un elemento del árbol
     * 
     * @param T elemento que se va a eliminar
     */
    public void eliminar(T elemento);

    /**
     * Método para limpiar un árbol
     */
    public void limpiar();

    /**
     * Metodo para saber si un arbol es vacio
     * 
     * @return boolean True si es vacío
     */
    public boolean esVacio();

    /**
     * Devuelve el vértices raíz de un árbol
     * 
     * @return VerticeArbolBinario que es la raíz
     */
    public VerticeArbolBinario<T> raiz();

    public int altura();

    public int profundidad();

    /**
     * Representar en cadena el árbol
     * 
     * @return String del arbol
     */
    @Override
    public String toString();

}