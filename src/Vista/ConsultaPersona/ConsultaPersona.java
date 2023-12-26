package Vista.ConsultaPersona;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Controlador.ControladorConsultaPersona;
import Modelo.Persona.Persona;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JToolBar;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ConsultaPersona {

	public static boolean existeUnaVentanaAbierta = false;
	public JFrame frmDdd;
	public JTable table;
	private ControladorConsultaPersona controlador;
	public JPanel panel;
	public JButton btnNewButton;
	public JTextField textField;
	public JComboBox comboBox;
	public JButton btnNewButton_1;
	public JButton btnNewButton_2;
	public JButton btnNewButton_3;
	
	//Datos de las personas obtenidas de la DB
	public LinkedList<Persona> personas;
	public int personasSize;
	public int personaElegida = -1;

	
	/**
	 * Create the application.
	 */
	public ConsultaPersona() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmDdd = new JFrame();
		frmDdd.setFont(new Font("Dyuthi", Font.PLAIN, 17));
		frmDdd.setTitle("Consultar persona");
		frmDdd.setBounds(100, 100, 1320, 716);
		frmDdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		frmDdd.getContentPane().add(panel, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		
		btnNewButton = new JButton("Buscar");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setColumns(30);
		panel.add(textField);
		panel.add(comboBox);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setEnabled(false);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.setEnabled(false);
		panel.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Cargar");
		btnNewButton_3.setEnabled(false);
		panel.add(btnNewButton_3);
				
		table = new JTable();
		
		ListSelectionModel modelo = table.getSelectionModel();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro. doc.", "Tipo doc.", "Apellido", "Nombres", "Fecha nac.", "Sexo", "Est. civil", "Telefono", "Email", "Ini. obra social", "Fin obra social", "Direcci\u00F3n", "Estado"
			}
		));
		JScrollPane scrollPane = new JScrollPane(table);
		frmDdd.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//agregado por mi
		frmDdd.setVisible(true);
		conectarControlador();
	}
	
	private void conectarControlador(){
		controlador = new ControladorConsultaPersona(this);
		controlador.escucharEventos();
	}
}
