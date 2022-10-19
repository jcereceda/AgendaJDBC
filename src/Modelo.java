import java.io.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * 
 * @author Javier Cereceda
 * @Desccription - Modelo del sistema modelo vista controlador. Tiene
 *               interacción con los datos externos al programa y contacta con
 *               la vista
 *
 */
public class Modelo {
	private Vista vista;
	private Connection conexion;
	private DefaultTableModel modeloTabla;
	private String notaSeleccionada, fechaSeleccionada;

	/**
	 * Constructor por defecto
	 */
	public Modelo() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ud1_2_jdbc", "root", "");
			cargarTabla();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga la tabla de la vista donde se encuentran todas las notas, mediante una
	 * cosulta a la base de datos
	 */
	private void cargarTabla() {
		String consulta = "Select nota, fecha from notas";
		String[] cabecera = new String[2];
		cabecera[0] = "Nota";
		cabecera[1] = "Fecha";

		try {
			PreparedStatement stm = conexion.prepareStatement(consulta);
			ResultSet rs = stm.executeQuery();
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int numFilas = calcNumFilas(consulta);
			Object[][] contenido = new Object[numFilas][2];
			int fila = 0;
			while (rs.next()) {
				for (int col = 1; col <= 2; col++) {
					contenido[fila][col - 1] = rs.getString(col);
				}
				fila++;
			}
			modeloTabla = new DefaultTableModel(contenido, cabecera);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param consulta
	 * @return numero de filas que saca una consutla a una tabla
	 */
	private int calcNumFilas(String consulta) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(consulta);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	/**
	 * Asigna unica vista de la aplicacion
	 * 
	 * @param vista2
	 */
	public void setVista(Vista vista2) {
		vista = vista2;
	}

	/**
	 * Introduce en la BD la nota y fecha introducidas por el usuario
	 * 
	 * @param nota
	 * @param fecha
	 */
	@SuppressWarnings("deprecation")
	public void guardarNota(String nota, Date fecha) {

		String insertar = "Insert into notas (nota,fecha) values (?,?)";
		int dia = fecha.getDate();
		int mes = fecha.getMonth() + 1;
		int ano = fecha.getYear() + 1900;
		int hora = fecha.getHours();
		int minuto = fecha.getMinutes();
		String fechaForm = ano + "-" + mes + "-" + dia + " " + hora + ":" + minuto;
		try {
			PreparedStatement insercion = conexion.prepareStatement(insertar);
			insercion.setString(1, nota);
			insercion.setString(2, fechaForm);
			int res = insercion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarTabla();
		vista.notaGuardada();
	}

	/**
	 * @return datos de tabla de la vista
	 */
	public TableModel getModeloTabla() {
		return modeloTabla;
	}

	/**
	 * Método para eliminar registros de la BD
	 * @param filaAEliminar
	 */
	public void eliminarFila(int filaAEliminar) {
		String codigo = (String) modeloTabla.getValueAt(filaAEliminar, 0);
		String eliminacion = "Delete from notas where nota = '" + codigo + "'";
		try {
			PreparedStatement query = conexion.prepareStatement(eliminacion);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarTabla();
		vista.filaEliminada();
	}

	/**
	 * Método para devolver a la vista los datos de la fila seleccionada
	 * @param filaAPoner
	 */
	public void ponerNota(int filaAPoner) {
		notaSeleccionada = (String) modeloTabla.getValueAt(filaAPoner, 0);
		fechaSeleccionada = (String) modeloTabla.getValueAt(filaAPoner, 1);
		vista.ponerNotaSeleccionada();
	}

	/**
	 * @return texto de la nota seleccionada
	 */
	public String getNotaSeleccionada() {
		return notaSeleccionada;
	}
	
	/**
	 * 
	 * @return Fecha seleccionada en tabla
	 */
	public Object getFechaSeleccionada() {
		return fechaSeleccionada;
	}
	
	/**	
	 * Metodo para modificar una nota seleccionada
	 */
	public void modificarNota(String nota, Date fecha) {
		int year = fecha.getYear() + 1900;
		int mes = fecha.getMonth() + 1;
		int day = fecha.getDate();
		String fechaForm = year + "-" + mes + "-" + day;
		String query = "update notas set nota = '" + nota + "', fecha = '" + fechaForm + "' where nota = '"
				+ notaSeleccionada + "'";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarTabla();
		vista.filaModificada();
	}

}
