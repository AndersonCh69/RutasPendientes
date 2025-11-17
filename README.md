**Aplicación de Escritorio para Análisis de Pendientes (RutasPendientes)**

**Proyecto Integrador**

**ESPOCH Sede Orellana, 2025**


-----------------------------------------------
>Descripcion del proyecto

El análisis de pendientes es un factor crítico en el diseño y planificación de carreteras, impactando la seguridad y sostenibilidad de los proyectos viales . Los métodos tradicionales de cálculo son manuales, lentos y propensos a errores, dificultando la evaluación de alternativas.


Este proyecto (RutasPendientes) es una aplicación de escritorio que automatiza este proceso. La herramienta permite a los ingenieros y estudiantes cargar datos topográficos desde un archivo CSV y obtener un análisis estadístico inmediato de las pendientes, facilitando la toma de decisiones .

>Caracteristicas principales

- Carga de Datos: Importación y validación de archivos .csv que contienen datos de coordenadas y elevación .


- Cálculo Automático: Determina automáticamente la pendiente máxima, pendiente mínima y pendiente promedio del conjunto de datos .


- Clasificación: Clasifica cada segmento de ruta según su pendiente (Baja, Moderada, Alta, Muy Alta) .


- Visualización: Muestra todos los resultados en una interfaz gráfica de usuario (GUI) clara e intuitiva .

>Stack Tecnologico

Este proyecto fue desarrollado cumpliendo las siguientes restricciones tecnológicas:

- Lenguaje: Java 17

- Interfaz Gráfica (GUI): JavaFX

- Gestión de Dependencias: Apache Maven


- IDE: NetBeans 


- Diseño y Arquitectura: UML (Modelo-Vista-Controlador) 


- Control de Versiones: Git & GitHub

>Requisitos previos

Para ejecutar el proyecto, necesitarás tener instalado:

- JDK (Java Development Kit) - Versión 17 o superior.

- Apache Maven (solo si deseas compilar desde la fuente).

>Instrucciones de ejecucion

- Opción 1: Ejecutar el .jar (Recomendado)
Esta es la forma más fácil de probar la aplicación funcional.

1. Descarga el archivo RutasPendientes-1.0.jar desde la sección "Releases" de este repositorio.

2. Abre una terminal (CMD, PowerShell, o Terminal de Linux/Mac).

3. Navega hasta la carpeta donde descargaste el archivo (ej. cd Downloads).

4. Ejecuta el siguiente comando:

**Bash**

" java -jar RutasPendientes-1.0.jar "


- Opción 2: Compilar y Ejecutar desde el Código Fuente
Si deseas compilar el proyecto tú mismo:

1. Clona este repositorio en tu máquina local:

**Bash**

" git clone [URL_DE_TU_REPOSITORIO_GITHUB] "

2. Navega al directorio raíz del proyecto:

**Bash**

" cd [NOMBRE_DE_LA_CARPETA_DEL_PROYECTO] "

3. Usa Maven para compilar el proyecto y crear el paquete (esto descargará las dependencias de JavaFX):

**Bash**

" mvn clean package "

4. Una vez que la compilación sea exitosa, navega a la carpeta target:

**Bash**

" cd target "

5. Ejecuta el archivo .jar que se acaba de crear:

**Bash**

" java -jar RutasPendientes-1.0.jar "


-----------------------------------------------
**Autores:**

Anderson Ariel Chimborazo Revelo (Líder del Proyecto) 


Alex Jair Vicente Cumbicus 


German Teofilo Gaibor Recalde 


Jair Josue Pezo Mamallacta 


Arthur Sebastián Briones Zambrano
