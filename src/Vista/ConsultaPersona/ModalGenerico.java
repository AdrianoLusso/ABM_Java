package Vista.ConsultaPersona;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class ModalGenerico {

	public JFrame frame;
	public JTextField textField;

	

	/**
	 * Create the application.
	 */
	public ModalGenerico(String texto) {
		initialize(texto);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String texto) {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 318);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField(texto);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		frame.setVisible(true);
	}

}
