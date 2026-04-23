import java.util.EmptyStackException;

/**
 * Implementación de pila basada en nodos
 */
public class Stack<T> implements TDAStack<T> {

    // Clase Node privada
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top;

    public Stack() {
        top = null;
    }

    @Override
    public void push(T e) {
        top = new Node<>(e, top);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void clear() {
        top = null;
    }

    @Override
    public void show() {
        if (isEmpty()) {
            System.out.println("La pila está vacía");
            return;
        }
        System.out.print("Pila (tope → fondo): ");
        Node<T> current = top;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println();
    }
}