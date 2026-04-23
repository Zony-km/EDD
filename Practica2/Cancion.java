/**
 * Clase que representa una entidad de audio musical (Canción). 
 * Permite gestionar la información básica como título, artista, 
 * duración y el estado de preferencia del usuario.
 * * @author Luis Yamil Antonio Sanchez Segura
 */
public class Cancion {

    // Atributos de la clase
    private String titulo;
    private String artista;
    private int duracion; // Representada en segundos
    private boolean favorita;

    /**
     * Constructor de la clase. Inicializa los atributos principales de la canción
     * y establece por defecto el estado de favorita como falso.
     * * @param titulo   Nombre de la pieza musical.
     * @param artista  Nombre del intérprete o agrupación.
     * @param duracion Tiempo total de la pista en segundos.
     */
    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.favorita = false;
    }

    // Metodos de acceso.

    /**
     * Recupera el título de la canción.
     * @return String con el título.
     */
    public String obtenerTitulo() {
        return titulo;
    }

    /**
     * Recupera el nombre del artista.
     * @return String con el artista.
     */
    public String obtenerArtista() {
        return artista;
    }

    /**
     * Recupera la duración total de la pista.
     * @return Entero que representa los segundos.
     */
    public int obtenerDuracion() {
        return duracion;
    }

    /**
     * Verifica si la canción ha sido marcada como favorita.
     * @return true si es favorita, false en caso contrario.
     */
    public boolean esFavorita() {
        return favorita;
    }

    // Metodos modificadores

    /**
     * Actualiza el título de la canción.
     * @param titulo Nuevo título a asignar.
     */
    public void asignarTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Actualiza el nombre del artista.
     * @param artista Nuevo artista a asignar.
     */
    public void asignarArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Actualiza la duración de la pista.
     * @param duracion Nueva duración en segundos.
     */
    public void asignarDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Modifica el estado de preferencia de la canción.
     * @param valor true para marcar como favorita, false para desmarcar.
     */
    public void marcarFavorita(boolean valor) {
        this.favorita = valor;
    }

    /**
     * Genera una representación textual de la canción, incluyendo un 
     * indicador visual si la canción es favorita.
     * @return Cadena formateada con la información de la canción.
     */
    @Override
    public String toString() {
        String fav = favorita ? " ★" : "";
        return titulo + " - " + artista + " (" + duracion + " s)" + fav;
    }

    /**
     * Compara la canción actual con otro objeto para determinar su igualdad
     * basándose en el contenido (título, artista y duración).
     * * @param obj Objeto a comparar.
     * @return true si tienen los mismos datos, false si son distintos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cancion otra = (Cancion) obj;
        return this.titulo.equalsIgnoreCase(otra.titulo)
            && this.artista.equalsIgnoreCase(otra.artista)
            && this.duracion == otra.duracion;
    }
    
} // Fin de la clase Cancion