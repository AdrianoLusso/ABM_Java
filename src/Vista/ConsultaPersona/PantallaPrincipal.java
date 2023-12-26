package Vista.ConsultaPersona;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import Controlador.ControladorPantallaPrincipal;
import Modelo.Persona.Persona;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class PantallaPrincipal {

	//controlador
	private ControladorPantallaPrincipal controlador;
	
	public JFrame frame;
	public JTextField txtGestosDePersonas;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1030, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtGestosDePersonas = new JTextField();
		txtGestosDePersonas.setEditable(false);
		txtGestosDePersonas.setFont(new Font("Monospaced", Font.BOLD, 26));
		txtGestosDePersonas.setHorizontalAlignment(SwingConstants.CENTER);
		txtGestosDePersonas.setText("Gestor de Personas");
		frame.getContentPane().add(txtGestosDePersonas, BorderLayout.NORTH);
		txtGestosDePersonas.setColumns(10);
		
		panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.WEST);
		
		panel_5 = new JPanel();
		frame.getContentPane().add(panel_5, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnNewButton = new JButton("Consulta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Carga");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel.add(btnNewButton_1);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		
		conectarControlador();
	}
	
	private void conectarControlador(){
		controlador = new ControladorPantallaPrincipal(this);
		controlador.escucharEventos();
	}
 

}
