package Vista.ConsultaPersona;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import Controlador.ControladorConfirmacionBorrarPersona;
import Controlador.ControladorConsultaPersona;
import Modelo.Persona.Persona;
import Modelo.Tiposdoc.Tiposdoc;

import javax.swing.JPanel;
import javax.swing.JButton;

public class ConfirmacionBorrarPersona {

	public JFrame frame;
	public JTextField text;
	public static boolean existeUnaVentanaAbierta = false;
	public Persona personaPorBorrar;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	
	private ControladorConfirmacionBorrarPersona controlador;
	
	/**
	 * Create the application.
	 */
	public ConfirmacionBorrarPersona(Persona personaPorBorrar,ControladorConsultaPersona controladorConsultaPersona) {
		this.personaPorBorrar = personaPorBorrar;
		initialize();
		conectarControlador(controladorConsultaPersona);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 529, 153);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		text = new JTextField();
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setEditable(false);
		text.setText("¿Está seguro que quiere borrar a la persona con "+
		Tiposdoc.obtenerPorId(personaPorBorrar.getTipodocumento())+" "+personaPorBorrar.getNrodocumento()+"?");
		frame.getContentPane().add(text, BorderLayout.CENTER);
		text.setColumns(10);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Si");
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("No");
		panel.add(btnNewButton_1);		
	}

	private void conectarControlador(ControladorConsultaPersona controladorConsultaPersona) {
		controlador = new ControladorConfirmacionBorrarPersona(this,controladorConsultaPersona);
		controlador.escucharEventos();
	}
	
}
