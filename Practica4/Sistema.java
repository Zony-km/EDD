import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Clase que administra el funcionamiento del juego de 20 preguntas.
 * Gestiona la carga, ejecución, aprendizaje y almacenamiento del árbol binario
 * utilizado para adivinar el objeto pensado por el usuario.
 */
public class Sistema implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Árbol binario que almacena preguntas y respuestas. */
    private ArbolBinario<String> base;

    /** Ruta del archivo donde se guarda el árbol serializado. */
    private final String ruta = "datos.ser";

    /** Lector para entrada del usuario. */
    private transient Scanner lector = new Scanner(System.in);

    /**
     * Constructor. Intenta cargar el árbol desde archivo; si no existe,
     * genera un árbol inicial por defecto.
     */
    public Sistema() {
        base = abrir();
    }

    /**
     * Carga el árbol desde archivo o genera uno nuevo si no existe.
     * 
     * @return Árbol binario cargado o uno nuevo.
     */
    private ArbolBinario<String> abrir() {
        File f = new File(ruta);

        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                return (ArbolBinario<String>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar archivo. Se generará un árbol nuevo.");
            }
        }

        ArbolBinario<String> nuevo = new ArbolBinario<>();

        nuevo.agregar("¿Es tecnología?");
        nuevo.agregar("¿Es portátil?", "¿Es tecnología?");
        nuevo.agregar("¿Es un mueble?", "¿Es tecnología?");
        nuevo.agregar("Laptop", "¿Es portátil?");
        nuevo.agregar("Computadora", "¿Es portátil?");
        nuevo.agregar("Sofá", "¿Es un mueble?");
        nuevo.agregar("Mesa", "¿Es un mueble?");

        return nuevo;
    }

    /**
     * Inicia el juego desde la raíz del árbol.
     */
    public void iniciar() {
        ejecutar(base.raiz(), 0);
    }

    /**
     * Ejecuta el proceso de preguntas y navegación dentro del árbol.
     * 
     * @param cursor   Nodo actual.
     * @param contador Número de preguntas realizadas.
     */
    private void ejecutar(VerticeArbolBinario<String> cursor, int contador) {

        if (cursor == null || contador == 20) {
            System.out.println("No fue posible adivinar.");
            return;
        }

        boolean esHoja = !cursor.hayIzquierdo() && !cursor.hayDerecho();

        if (esHoja) {
            System.out.println("¿Es " + cursor.get() + "?");
            boolean r = leerSiNo();

            if (r) {
                System.out.println("Respuesta correcta.");
            } else {
                expandir(cursor);
            }
            return;
        }

        System.out.println(cursor.get());
        boolean r = leerSiNo();

        if (r) {
            ejecutar(cursor.izquierdo(), contador + 1);
        } else {
            ejecutar(cursor.derecho(), contador + 1);
        }
    }

    /**
     * Inicia el proceso de aprendizaje cuando el sistema falla en adivinar.
     * 
     * @param hoja Nodo hoja donde ocurrió el fallo.
     */
    private void expandir(VerticeArbolBinario<String> hoja) {

        System.out.println("¿Qué objeto estabas pensando?");
        String nuevo = lector.nextLine();

        System.out.println("Escribe una pregunta que los diferencie:");
        String filtro = lector.nextLine();

        System.out.println("Para \"" + nuevo + "\", la respuesta es (si/no):");
        boolean afirmativo = leerSiNo();

        reconstruirNodo(hoja, filtro, nuevo, afirmativo);

        System.out.println("La información ha sido registrada.");
    }

    /**
     * Reconstruye un nodo hoja convirtiéndolo en pregunta y agregando dos hijos.
     * No modifica la estructura interna del árbol binario.
     *
     * @param hoja       Nodo hoja a transformar.
     * @param filtro     Nueva pregunta.
     * @param nuevo      Nueva respuesta.
     * @param afirmativo Indica si la respuesta al filtro para el nuevo objeto es
     *                   "si".
     */
    private void reconstruirNodo(VerticeArbolBinario<String> hoja,
            String filtro,
            String nuevo,
            boolean afirmativo) {

        String viejo = hoja.get();

        // Cambiar el texto del nodo hoja usando reflexión
        try {
            Field campo = hoja.getClass().getDeclaredField("elemento");
            campo.setAccessible(true);
            campo.set(hoja, filtro);
        } catch (Exception e) {
            System.out.println("No fue posible actualizar el nodo.");
            return;
        }

        // Agregar hijos usando el método permitido
        if (afirmativo) {
            base.agregar(nuevo, filtro);
            base.agregar(viejo, filtro);
        } else {
            base.agregar(viejo, filtro);
            base.agregar(nuevo, filtro);
        }
    }

    /**
     * Solicita al usuario una respuesta válida de tipo "si" o "no".
     * Repite la petición hasta obtener una entrada correcta.
     *
     * @return true si la respuesta es "si", false si es "no".
     */
    private boolean leerSiNo() {
        while (true) {
            String r = lector.nextLine().trim().toLowerCase();

            if (r.equals("si"))
                return true;
            if (r.equals("no"))
                return false;

            System.out.println("Entrada inválida. Responde únicamente con 'si' o 'no'.");
        }
    }

    /**
     * Guarda el estado actual del árbol en un archivo mediante serialización.
     */
    public void cerrar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(base);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo.");
        }
    }
}
