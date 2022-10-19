import java.io.IOException;
import java.util.Date;
/**
 * Controlador del sistema Modelo Vista Controlador, comunica la vista con los datos
 * @author Javier Cereceda
 *
 */
public class Controlador {
	private Modelo modelo;
	private Vista vista;

	/**
	 * Setter del modelo por defecto
	 * @param modelo2
	 */
	public void setModelo(Modelo modelo2) {
		modelo = modelo2;
	}

	/**
	 * Setter de la vista en este caso una
	 * @param vista2
	 */
	public void setVista(Vista vista2) {
		vista = vista2;
	}

	/**
	 * Metodo para pedir datos a la vista y enviarselos al modelo
	 */
	public void guardar() {
		String nota = vista.getNota();
		Date fecha = (Date) vista.getFecha();
		modelo.guardarNota(nota,fecha);
	}


	/**
	 * Llamada al modelo para actualizar una nota
	 */
	public void modificarNota() {
		String nota = vista.getNota();
		Date fecha = (Date) vista.getFecha();
		modelo.modificarNota(nota,fecha);
	}

	/**
	 * Llamada al modelo para eliminar un registro
	 */
	public void eliminarNota() {
		int filaAEliminar = vista.getClave();
		modelo.eliminarFila(filaAEliminar);
	}

	/**
	 * Llamada al modelo para poner una nota en la vista
	 */
	public void notaSeleccionada() {
		int filaAPoner = vista.getClave();
		modelo.ponerNota(filaAPoner);
		
	}

	

}
