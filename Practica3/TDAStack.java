import java.util.EmptyStackException;

/**
 * Interfaz para pilas
 */

public interface TDAStack<T> {
    /**
     * Agregar un elmento en el tope
     * 
     * @param e el elemtno que se va agregar
     */
    public void push(T e);

    /**
     * Elimina y regresa el elemento en el tope de la pila
     * 
     * @return elemento en el tope
     * @throws EmptyStackException en caso de tener la pila vacía
     */
    public T pop() throws EmptyStackException;

    /**
     * Regresa el tope de la pila
     * 
     * @return el elemento en el tope
     * @throws EmptyStackException en caso de tner la pila vacía.
     */
    public T top() throws EmptyStackException;

    /**
     * Verifica que la lista sea vacía
     * 
     * @return true en caso de que sea vacía, false en cualquier otro caso
     */
    public boolean isEmpty();

    /**
     * Limpia la pila
     */
    public void clear();

    /**
     * Permite visualizar los elementos de la pila
     */
    public void show();

}