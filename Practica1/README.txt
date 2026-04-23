Sanchez Segura Luis Yamil Antonio
No. cuenta 323028774
yamil.segura.sanchez@ciencias.unam.mx

Funcionamiento del programa:

El programa es una implementación del juego de lógica Picross (Nonogram) en un entorno de consola, desarrollado bajo el paradigma de Programación Orientada a Objetos. El sistema se basa en los siguientes pilares técnicos:

1. Estructura de Datos: Se utilizan arreglos bidimensionales (matrices) de tipo entero para gestionar tanto la solución oculta del juego como el progreso en tiempo real del jugador. Las pistas de filas y columnas se almacenan en arreglos unidimensionales de Strings.

2. Generación Automática de Pistas: Al iniciar el programa, un algoritmo recorre la matriz solución para identificar bloques de celdas marcadas (1s) y espacios (0s). Este proceso traduce secuencias binarias en las pistas numéricas que guían al usuario, cumpliendo con el requerimiento de automatización.

3. Gestión de Excepciones: Se implementó la clase personalizada 'EntradaInvalidaException' para validar la integridad de las jugadas. El programa detecta y notifica errores si el usuario ingresa coordenadas fuera del rango 0-4 o acciones no válidas, evitando el cierre inesperado del sistema.

4. Ciclo de Juego y Victoria: El usuario interactúa mediante un menú que permite realizar jugadas (marcar 'X' o borrar '.'). Tras cada movimiento, el programa compara de forma automática el estado del tablero del jugador con la matriz solución; en caso de coincidencia total, se despliega un mensaje de felicitación y el juego concluye exitosamente.

