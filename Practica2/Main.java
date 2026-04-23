import java.util.Scanner;

/**
 * Clase principal que contiene el punto de entrada del programa.
 * Gestiona el menú interactivo para manipular la lista de reproducción
 * y coordina las acciones del usuario con la lógica de la clase Playlist.
 * * @author Luis Yamil Antonio Sanchez Segura
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int opcion = -1;

        do {
            // Despliegue visual del menú
            System.out.println("\n------- PLAYLIST -------");
            System.out.println("1. Agregar canción al inicio");
            System.out.println("2. Agregar canción al final");
            System.out.println("3. Eliminar canción");
            System.out.println("4. Buscar canción");
            System.out.println("5. Mostrar playlist");
            System.out.println("6. Duración total");
            System.out.println("7. Reproducir actual");
            System.out.println("8. Siguiente");
            System.out.println("9. Anterior");
            System.out.println("10. Marcar como favorita");
            System.out.println("11. Mostrar favoritas");
            System.out.println("12. Activar modo circular");
            System.out.println("13. Desactivar modo circular");
            System.out.println("14. Ir a canción por número");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            // Validación de la entrada de la opción
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Debes ingresar un número.");
                sc.nextLine();
                opcion = -1;
                continue;
            }

            // Procesamiento de la opción seleccionada
            switch (opcion) {

                case 1: {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Artista: ");
                    String artista = sc.nextLine();

                    System.out.print("Duración (segundos): ");
                    int duracion;

                    try {
                        duracion = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Duración inválida.");
                        sc.nextLine();
                        break;
                    }

                    playlist.agregarInicio(new Cancion(titulo, artista, duracion));
                    System.out.println("Canción agregada al inicio.");
                    break;
                }

                case 2: {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Artista: ");
                    String artista = sc.nextLine();

                    System.out.print("Duración (segundos): ");
                    int duracion;

                    try {
                        duracion = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Duración inválida.");
                        sc.nextLine();
                        break;
                    }

                    playlist.agregarFinal(new Cancion(titulo, artista, duracion));
                    System.out.println("Canción agregada al final.");
                    break;
                }

                case 3: {
                    System.out.print("Título a eliminar: ");
                    String titulo = sc.nextLine();
                    playlist.eliminar(titulo);
                    break;
                }

                case 4: {
                    System.out.print("Título a buscar: ");
                    String titulo = sc.nextLine();

                    Cancion c = playlist.buscar(titulo);

                    if (c != null) {
                        System.out.println("Encontrada: " + c);
                    } else {
                        System.out.println("No se encontró la canción.");
                    }
                    break;
                }

                case 5:
                    playlist.mostrar();
                    break;

                case 6:
                    System.out.println("Duración total: " + playlist.duracionTotal() + " s");
                    break;

                case 7:
                    playlist.reproducir();
                    break;

                case 8:
                    playlist.siguiente();
                    playlist.reproducir();
                    break;

                case 9:
                    playlist.anterior();
                    playlist.reproducir();
                    break;

                case 10: {
                    System.out.print("Título a marcar como favorita: ");
                    String titulo = sc.nextLine();
                    playlist.marcarFavorita(titulo);
                    break;
                }

                case 11:
                    playlist.mostrarFavoritas();
                    break;

                case 12:
                    playlist.activarModoCircular(true);
                    break;

                case 13:
                    playlist.activarModoCircular(false);
                    break;

                case 14: {
                    System.out.print("Número de canción: ");
                    int num;

                    try {
                        num = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Número inválido.");
                        sc.nextLine();
                        break;
                    }

                    playlist.irA(num);
                    break;
                }

                case 0:
                    System.out.println("Cerraste Playlist");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }// Fin del main

} // Fin de la clase Main