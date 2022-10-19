-- Base de datos para aplicación de agenda
CREATE DATABASE UD1_2_JDBC character set latin1 collate latin1_spanish_ci;
use UD1_2_JDBC;
-- crear tabla notas
CREATE TABLE notas (
	codigo int auto_increment primary key,
	nota VARCHAR(150) ,
    fecha DATE
)Engine = InnoDB;

-- Querys a utilizar 

-- Seleccionar todas las notas
Select * from notas;

-- Insertar notas
Insert into notas (nota,fecha) values ("",curdate);

-- Eliminar notas
DELETE FROM notas where nota = "";

-- Modificar notas
update notas set nota = "", fecha = curdate() where nota = "";


