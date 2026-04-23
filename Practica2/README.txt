Sanchez Segura Luis Yamil Antonio 
No. cuenta 323028774
yamil.segura.sanchez@cienicas.unam.mx

Funcionamineto:

Este proyecto implementa un reproductor de música básico utilizando una lista doblemente ligada como estructura principal.
El objetivo es simular el funcionamiento de una playlist real, permitiendo agregar, eliminar, buscar y navegar entre canciones, 
además de incorporar funciones como favoritos, selección por número y un modo de reproducción circular.

Estructura del Sistema

El programa se organiza en cuatro componentes:

    Cancion: modelo que representa una canción con título, artista, duración y estado de favorito.

    ListaDoblementeLigada: estructura de datos personalizada que permite recorrer elementos en ambas direcciones.

    Playlist: contiene toda la lógica del reproductor, incluyendo navegación, control de la canción actual y manejo del modo circular.

    Main: interfaz de usuario por consola con manejo de excepciones para garantizar estabilidad.

Funcionalidad Implementada
Gestión de canciones

    Agregar canciones al inicio o al final.

    Eliminar canciones por título.

    Buscar la primera coincidencia por título.

    Mostrar la playlist completa con indicación de la canción actual.

Navegación

    Reproducir la canción actual.

    Avanzar a la siguiente o retroceder a la anterior.

    Modo circular opcional: al activarlo, “siguiente” desde la última vuelve al inicio y “anterior” desde la primera vuelve al final.

    Ir directamente a una canción por número.

Favoritos

    Marcar canciones como favoritas.

    Mostrar únicamente las canciones marcadas.



Manejo de Errores y Validaciones

El programa incorpora:

    Validación de lista vacía antes de cualquier operación.

    Manejo de excepciones en el menú para evitar fallos por entradas inválidas.

    Limpieza del buffer del Scanner para mantener un flujo estable.

    Mensajes claros para guiar al usuario en caso de errores o acciones no permitidas.
