/**
 * 
 * @author Javier Cereceda
 * @description - Crea los objetos del sistema modelo vista controlador y les asigna a cada uno sus atributos
 *
 */
public class Main {
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		vista.setVisible(true);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.setControlador(controlador);
		vista.setModelo(modelo);
		modelo.setVista(vista);
	}
}
