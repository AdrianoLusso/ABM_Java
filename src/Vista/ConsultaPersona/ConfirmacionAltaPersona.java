package Vista.ConsultaPersona;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ControladorConfirmacionAltaPersona;
import Controlador.ControladorConfirmacionBorrarPersona;
import Controlador.ControladorConsultaPersona;
import Modelo.Persona.Persona;
import Modelo.Tiposdoc.Tiposdoc;

public class ConfirmacionAltaPersona {

	public JFrame frame;
	public JTextField text;
	public static boolean existeUnaVentanaAbierta = false;
	public Persona personaPorDarAlta;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	
	private ControladorConfirmacionAltaPersona controlador;
	
	/**
	 * Create the application.
	 */
	public ConfirmacionAltaPersona(Persona personaPorDarAlta, ControladorConsultaPersona controladorConsultaPersona) {
		this.personaPorDarAlta = personaPorDarAlta;
		initialize();
		conectarControlador(controladorConsultaPersona);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 170);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		text = new JTextField();
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setEditable(false);
		text.setText("¿Está seguro que quiere dar de alta a la persona con "+
		Tiposdoc.obtenerPorId(personaPorDarAlta.getTipodocumento())+" "+personaPorDarAlta.getNrodocumento()+"?");
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
		controlador = new ControladorConfirmacionAltaPersona(this,controladorConsultaPersona);
		controlador.escucharEventos();
	}
	

}
