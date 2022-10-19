import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Javier Cereceda
 * @Description - vista y elementos de la vista o pantalla
 */
public class Vista extends JFrame {

	private Modelo modelo;
	private Controlador controlador;
	private static Container contenedorPrincipal;
	private JScrollPane scrollPaneTabla;
	private JButton btnGuardar;
	private JLabel lblNotaGuardada;
	private JTable table;
	private JTextArea txtANota;
	private JSpinner spinnerFecha;
	private JButton btnModificar;
	private JButton btnEliminar;
	private int fila;

	/**
	 * Constructor por defecto
	 */
	public Vista() {
		getContentPane().setBackground(new Color(255, 250, 205));
		getContentPane().setForeground(new Color(0, 0, 0));
		setTitle("Agenda JDBC");
		modelo = new Modelo();
		controlador = new Controlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 411);
		setLocationRelativeTo(null);

		contenedorPrincipal = getContentPane();
		contenedorPrincipal.setLayout(null);

		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTabla.setBounds(39, 29, 524, 191);
		getContentPane().add(scrollPaneTabla);

		table = new JTable();
		table.setModel(modelo.getModeloTabla());
		scrollPaneTabla.setViewportView(table);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(0, 255, 0));
		btnGuardar.setForeground(new Color(0, 0, 0));
		btnGuardar.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.guardar();
			}
		});
		btnGuardar.setBounds(229, 319, 124, 21);
		getContentPane().add(btnGuardar);

		lblNotaGuardada = new JLabel("");
		lblNotaGuardada.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNotaGuardada.setBounds(61, 335, 121, 39);
		getContentPane().add(lblNotaGuardada);

		JScrollPane scrollPaneTexto = new JScrollPane();
		scrollPaneTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTexto.setBounds(41, 242, 160, 98);
		getContentPane().add(scrollPaneTexto);

		txtANota = new JTextArea();
		txtANota.setWrapStyleWord(true);
		txtANota.setLineWrap(true);
		scrollPaneTexto.setViewportView(txtANota);

		spinnerFecha = new JSpinner();
		spinnerFecha.setFont(new Font("Calibri", Font.PLAIN, 13));
		spinnerFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		spinnerFecha.setBounds(228, 247, 125, 27);
		getContentPane().add(spinnerFecha);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(0, 255, 255));
		btnModificar.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.modificarNota();
			}
		});
		btnModificar.setBounds(416, 319, 124, 21);
		getContentPane().add(btnModificar);
		btnModificar.setEnabled(false);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarNota();
			}
		});
		btnEliminar.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(255, 0, 0));
		btnEliminar.setBounds(416, 246, 124, 23);
		getContentPane().add(btnEliminar);
		btnEliminar.setEnabled(false);

		// Al hacer click sobre el fondo
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.clearSelection();
				fila = -1;
				habilitarBoton();
				lblNotaGuardada.setText("");
			}

		}

		);
		// Al hacer click sobre la tabla
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				controlador.notaSeleccionada();
				habilitarBoton();
				lblNotaGuardada.setText("");
			}

		});

	}

	/**
	 * Método para habilitar botones en caso de tener fila seleccionada
	 */
	private void habilitarBoton() {
		if (fila == -1) {
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
		} else {
			btnEliminar.setEnabled(true);
			btnModificar.setEnabled(true);
		}
	}

	/**
	 * Setter del controlador por defecto
	 * 
	 * @param controlador2
	 */
	public void setControlador(Controlador controlador2) {
		controlador = controlador2;
	}

	/**
	 * Setter del modelo por defecto
	 * 
	 * @param modelo2
	 */
	public void setModelo(Modelo modelo2) {
		modelo = modelo2;
	}

	/**
	 * Getter del controlador para nuevas notas
	 * 
	 * @return nota
	 */
	public String getNota() {
		return txtANota.getText();
	}

	/**
	 * Getter llamado para la fecha de la nueva nota
	 * 
	 * @return fecha
	 */

	public Date getFecha() {

		return (Date) spinnerFecha.getValue();
	}

	/**
	 * Metodo para confirmar que una nota esta guardada
	 */
	public void notaGuardada() {
		txtANota.setText("");
		lblNotaGuardada.setText("Nota Guardada");
		table.setModel(modelo.getModeloTabla());
		spinnerFecha.setValue(new Date());
	}


	/**
	 * 
	 * @return fila seleccionada
	 */
	public int getClave() {
		int fila = table.getSelectedRow();
		return fila;
	}

	/**
	 * Actualiza vista al eliminar fila
	 */
	public void filaEliminada() {
		txtANota.setText("");
		lblNotaGuardada.setText("Nota Eliminada");
		table.setModel(modelo.getModeloTabla());
		spinnerFecha.setValue(new Date());
	}

	/**
	 * Pone los valores de la fila seleccionada en los elementos de entrada
	 */
	public void ponerNotaSeleccionada() {
		txtANota.setText(modelo.getNotaSeleccionada());
		String fechaSeleccionada = (String) modelo.getFechaSeleccionada();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dataFormateada = formato.parse(fechaSeleccionada);
			spinnerFecha.setValue(dataFormateada);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * Actualiza la vista al modificar fila
	 */
	public void filaModificada() {
		txtANota.setText("");
		lblNotaGuardada.setText("Nota Modificada");
		table.setModel(modelo.getModeloTabla());
		spinnerFecha.setValue(new Date());
	}

}
