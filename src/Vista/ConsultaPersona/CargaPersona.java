package Vista.ConsultaPersona;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import java.awt.TextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import com.toedter.calendar.JDayChooser;

import Controlador.ControladorCargaPersona;
import Controlador.ControladorConsultaPersona;
import Modelo.Persona.Persona;
import Modelo.Tiposdoc.Tiposdoc;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

public class CargaPersona {

	private ControladorCargaPersona controlador;
	public Persona personaAModificar;
	public JFrame frmCargaDePersona;
	public JFormattedTextField textField_3;
	public JTextField textField_4;
	public JFormattedTextField textField_5;
	public JTextField textField_6;
	public JComboBox comboBox_2;
	public static boolean existeUnaVentanaAbierta_carga = false;
	public static boolean existeUnaVentanaAbierta_modificacion = false;
	public JFormattedTextField formattedTextField;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JFormattedTextField formattedTextField_2;
	public JFormattedTextField formattedTextField_1;
	public JButton btnNewButton;
	public JDateChooser dateChooser;
	public JDateChooser dateChooser_1;
	public JDateChooser dateChooser_2; 
	
	/**
	 * @wbp.parser.constructor
	 */
	public CargaPersona() {
		initialize("Carga de persona");
		conectarControlador(false,null);
	}
	
	public CargaPersona(Persona personaAModificar,ControladorConsultaPersona controladorConsultaPersona) {
		initialize("Modificación de persona");
		this.personaAModificar = personaAModificar;
		conectarControlador(true,controladorConsultaPersona);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title) {
		frmCargaDePersona = new JFrame();
		frmCargaDePersona.setTitle(title);
		frmCargaDePersona.setBounds(100, 100, 743, 363);
		frmCargaDePersona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel_10 = new JPanel();
		
		Box columna1 = Box.createVerticalBox();
		panel_10.add(columna1);
		
		Box boxNrodocumento = Box.createVerticalBox();
		columna1.add(boxNrodocumento);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Nùmero de documento");
		boxNrodocumento.add(lblNewJgoodiesLabel);
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewJgoodiesLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		NumberFormatter numberFormatter;
		NumberFormat format = NumberFormat.getIntegerInstance();
		numberFormatter = new NumberFormatter(format);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setMinimum(0); // Puedes ajustar el rango según tus necesidades
		numberFormatter.setMaximum(Integer.MAX_VALUE);
		numberFormatter.setAllowsInvalid(false);

		formattedTextField = new JFormattedTextField();
		formattedTextField.setColumns(25);

	    // If you want the value to be committed on each keystroke instead of focus lost

		boxNrodocumento.add(formattedTextField);
		
		JPanel panel_1 = new JPanel();
		columna1.add(panel_1);
		
		Box boxTipodocumento = Box.createVerticalBox();
		columna1.add(boxTipodocumento);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de documento");
		lblNewLabel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		boxTipodocumento.add(lblNewLabel_1);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox_2 = new JComboBox();
		boxTipodocumento.add(comboBox_2);
		
		JPanel panel_2 = new JPanel();
		columna1.add(panel_2);
		
		Box boxApellido = Box.createVerticalBox();
		columna1.add(boxApellido);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Apellido");
		boxApellido.add(lblNewJgoodiesLabel_2);
		
		textField_3 = new JFormattedTextField();
		
		boxApellido.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		columna1.add(panel_3);
		
		Box boxNombres = Box.createVerticalBox();
		columna1.add(boxNombres);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Nombres");
		boxNombres.add(lblNewJgoodiesLabel_3);

		textField_4 = new JFormattedTextField();

		boxNombres.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		columna1.add(panel_4);
		
		Box boxFechanacimiento = Box.createVerticalBox();
		columna1.add(boxFechanacimiento);
		
		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel("Fecha de nacimiento");
		boxFechanacimiento.add(lblNewJgoodiesLabel_4);
		
		dateChooser = new JDateChooser();
		boxFechanacimiento.add(dateChooser);
		
		JPanel panel_5 = new JPanel();
		columna1.add(panel_5);
		
		Box boxSexo = Box.createVerticalBox();
		columna1.add(boxSexo);
		
		JLabel lblNewJgoodiesLabel_5 = DefaultComponentFactory.getInstance().createLabel("Sexo");
		boxSexo.add(lblNewJgoodiesLabel_5);
		
		comboBox = new JComboBox();
		boxSexo.add(comboBox);
		
		
		comboBox.addItem(
				new ComboItem("M","Masculino")
				);
		comboBox.addItem(
				new ComboItem("F","Femenino")
				);
		
		JPanel panel_8 = new JPanel();
		
		Box columna3 = Box.createVerticalBox();
		panel_8.add(columna3);
		
		Box boxEstadocivil = Box.createVerticalBox();
		columna3.add(boxEstadocivil);
		
		JLabel lblNewJgoodiesLabel_6 = DefaultComponentFactory.getInstance().createLabel("Estado civil");
		boxEstadocivil.add(lblNewJgoodiesLabel_6);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel());
		boxEstadocivil.add(comboBox_1);
		
		JPanel panel_6 = new JPanel();
		columna3.add(panel_6);
		
		Box BoxTelefono = Box.createVerticalBox();
		columna3.add(BoxTelefono);
		
		JLabel lblNewJgoodiesLabel_7 = DefaultComponentFactory.getInstance().createLabel("Teléfono");
		BoxTelefono.add(lblNewJgoodiesLabel_7);
		
		textField_5 = new JFormattedTextField();		
		BoxTelefono.add(textField_5);
		textField_5.setColumns(25);
		
		JPanel panel_12 = new JPanel();
		columna3.add(panel_12);
		
		Box boxEmail = Box.createVerticalBox();
		columna3.add(boxEmail);
		
		JLabel lblNewLabel = new JLabel("Email");
		boxEmail.add(lblNewLabel);
		
		textField_6 = new JTextField();
		boxEmail.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		columna3.add(panel_9);
		
		Box boxFechainios = Box.createVerticalBox();
		columna3.add(boxFechainios);
		
		JLabel lblNewLabel_2 = new JLabel("Inicio de obra social");
		boxFechainios.add(lblNewLabel_2);
		
		dateChooser_1 = new JDateChooser();
		boxFechainios.add(dateChooser_1);
		
		JPanel panel_7 = new JPanel();
		columna3.add(panel_7);
		
		Box boxFechafinos = Box.createVerticalBox();
		columna3.add(boxFechafinos);
		
		JLabel lblNewLabel_3 = new JLabel("Fn de obra social");
		boxFechafinos.add(lblNewLabel_3);
		
		dateChooser_2 = new JDateChooser();
		boxFechafinos.add(dateChooser_2);
		
		JPanel panel_13 = new JPanel();
		columna3.add(panel_13);
		
		Box boxIddireccion = Box.createVerticalBox();
		columna3.add(boxIddireccion);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Calle y número");
		boxIddireccion.add(lblNewJgoodiesLabel_1);
		
		JPanel panel = new JPanel();
		boxIddireccion.add(panel);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setColumns(22);
		panel.add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setColumns(4);
		panel.add(formattedTextField_2);
		
		JPanel panel_11 = new JPanel();
		
		btnNewButton = new JButton("Cargar");
		btnNewButton.setEnabled(false);
		panel_11.add(btnNewButton);
		frmCargaDePersona.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frmCargaDePersona.getContentPane().add(panel_10);
		frmCargaDePersona.getContentPane().add(panel_8);
		frmCargaDePersona.getContentPane().add(panel_11);
		
	}
	
	private void conectarControlador(boolean esModificacion,ControladorConsultaPersona controladorConsultaPersona){
		controlador = new ControladorCargaPersona(this,controladorConsultaPersona,esModificacion);
		controlador.escucharEventos();
	}

}
