import java.util.Iterator;

public interface Listable<T>{

    /**
     * Método para agregar elemento a una lista
     * 
     * @param T elemento a agregar
     */
    public void agregar(T elem);

    /**
     * Método para buscar si un elemento se enceuntra en la lista
     * 
     * @param T elemento a buscar 
     * @return boolean true si lo encuentra.
     */
    public boolean contiene(T elem);

    /**
     * Metodo para verificar si una lista esta vacia
     * 
     * @return boolean, true esta vacia, false tiene un elemento
     */
    public boolean estaVacio();

    /**
     * Metodo para vaciar la lista
     */
    public void vacia();

    /**
     * Metodo para encontrar el primer elemento de una lista
     * 
     * @return T elemento en primera posición
     */
    public T primerElemento();

    /**
     * Metodo para eliminar un elemento de la lista
     * 
     * @param T elemento a eliminar
     */
    public void eliminar(T elem);


    /**
     * Iterador para recorrer la lista
     * @return iterator
     */
    Iterator<T> iterador();



    
}