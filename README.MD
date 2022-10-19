# AGENDA JDBC V.1

Sean bienvenidos a la aplicación "Agenda de JDBC" en la que se pone en práctica el uso de Java con bases de datos MySQL. Se trata de guardar notas junto a una fecha en una tabla de una base de datos. Sobre esa tabla se harán consultas, se insertarán datos, se modificarán y eliminarán.
* Desarrollador: Javier Cereceda
* Tecnologías empleadas:
	* Java
	* Librería Swing
	* Eclipse
	* MySql
	* Mysql Workbench
	* Xampp

## Instalación y uso
Por ahora no hay ejecutable por lo que para su uso, será necesario un compilador como eclipse para su ejecución. Por otro lado será necesario un servidor MySQL, bastará con uno tipo Xampp que abra el puerto 3306. 
Previo a su uso es necesario la creación de la base de datos, se puede apoyar en una interfaz como MySQL workbench para la creación. El código de creación se encuentra en el archivo "BD.sql" del proyecto. Dentro del mismo también se encontrarán las querys utilizadas durante el proceso de desarrollo. 
Una vez realizados estos pasos, se puede ejecutar la clase "main" desde eclipse.
Una vez se tiene el proyecto, no será necesario referenciar la librería de mysql, pero a tener en cuenta por posibles actualizaciones.

## Mantenimiento

Este proyecto está desarrollado en Java con el sistema Modelo, Vista, controlador. El modelo es el que gestiona los datos de la base de datos y los devuelve a la vista, también los recoge, hace todas las operaciones con la base de datos. El controlador comunica la vista con el modelo.
En la vista encontraremos todos los elementos de swing, extiende de JFrame, en ninguna otra clase se hará. 
Cada clase tiene su constructor y sus respectivos métodos comentados en el javadoc con sus  respectivas funciones.

### Nota
Esta es la versión original, en caso de ser modificado indicar y modificar la versión en el título. La conexión con la base de datos se hace por defecto con el usuario y contraseña normales (root, "") por lo que si necesita cambiarlos está en la línea 37 de la clase modelo.