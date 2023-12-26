package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modelo.Estadocivil.Estadocivil;
import Modelo.Persona.Persona;
import Modelo.Persona.PersonaDAO;
import Modelo.Tiposdoc.Tiposdoc;
import Modelo.Tiposdoc.TiposdocDAO;
import Vista.ConsultaPersona.CargaPersona;
import Vista.ConsultaPersona.ComboItem;
import Vista.ConsultaPersona.ConsultaPersona;
import Vista.ConsultaPersona.ModalGenerico;

public class ControladorCargaPersona implements ActionListener,WindowListener,PropertyChangeListener,KeyListener{


	private CargaPersona vistaCargaPersona;
	private PersonaDAO personaDAO = new PersonaDAO();
	private boolean esModificacion;
	private ControladorConsultaPersona controladorConsultaPersona;
	
	public ControladorCargaPersona(CargaPersona vista,ControladorConsultaPersona controladorConsultaPersona,boolean esModificacion){
		vistaCargaPersona = vista;
		this.esModificacion = esModificacion;
		this.controladorConsultaPersona = controladorConsultaPersona;
		
		
		//Cargo en el combobox cada ComboItem con los datos de los tipos de documento
				for(int i = 0; i<Tiposdoc.length();i++) {
					vistaCargaPersona.comboBox_2.addItem(
							new ComboItem(Tiposdoc.obtenerId(i),Tiposdoc.obtenerDescripcion(i))
							);
				}
		
		//Cargo en el combobox cada ComboItem con los datos de estado civil
				for(int i = 0; i<Estadocivil.length();i++) {
					vistaCargaPersona.comboBox_1.addItem(
							new ComboItem(Estadocivil.obtenerId(i),Estadocivil.obtenerDescripcion(i))
							);
				}
				
		if(esModificacion) {
			cargarPersonaAModificar();
			vistaCargaPersona.formattedTextField.setEnabled(false);
			vistaCargaPersona.comboBox_2.setEnabled(false);
		}
	}
	
	private void cargarPersonaAModificar() {
		Persona persona = vistaCargaPersona.personaAModificar;
		int lengthTipodocCombobox = vistaCargaPersona.comboBox_2.getItemCount(),
				lengthSexoCombobox = vistaCargaPersona.comboBox.getItemCount(),
				lengthEstadocivilCombobox = vistaCargaPersona.comboBox_1.getItemCount();
		ComboItem currentComboItem;
		
		// carga nro documento
		vistaCargaPersona.formattedTextField.setText(persona.getNrodocumento());
		
		// carga el item seleccionado del tipo de documento
		for(int i = 0;i<lengthTipodocCombobox;i++) {
			currentComboItem = (ComboItem) vistaCargaPersona.comboBox_2.getItemAt(i);
			if((int)currentComboItem.getValue() == persona.getTipodocumento()){
				vistaCargaPersona.comboBox_2.setSelectedIndex(i);
			}
		}

		// carga apellido
		vistaCargaPersona.textField_3.setText(persona.getApellido());
		
		// carga nombres
		vistaCargaPersona.textField_4.setText(persona.getNombre());
		
		// carga la fecha de nacimiento
		vistaCargaPersona.dateChooser.setDate(persona.getFechaNacimiento());
	
		// carga el sexo
		for(int i = 0;i<lengthSexoCombobox;i++) {
			currentComboItem = (ComboItem) vistaCargaPersona.comboBox.getItemAt(i);
			if(((String)currentComboItem.getValue()).equals(persona.getSexo())){
				vistaCargaPersona.comboBox.setSelectedIndex(i);
			}
		}
		
		// carga el estado civil
				for(int i = 0;i<lengthEstadocivilCombobox;i++) {
					currentComboItem = (ComboItem) vistaCargaPersona.comboBox_1.getItemAt(i);
					if((int)currentComboItem.getValue() == (Integer.parseInt(persona.getEstadoCivil()))){
						vistaCargaPersona.comboBox_1.setSelectedIndex(i);
					}
				}
				
		// carga telefono
		vistaCargaPersona.textField_5.setText(persona.getTelefono());

		// carga email
		vistaCargaPersona.textField_6.setText(persona.getEmail());
		
		// carga fecha inicio de obra social
		vistaCargaPersona.dateChooser_1.setDate(persona.getIniOS());
		
		// carga fecha fin de obra social
		vistaCargaPersona.dateChooser_2.setDate(persona.getFinOS());
	
		// carga calle de la direccion
		vistaCargaPersona.formattedTextField_1.setText(persona.getCalleDireccion());
		
		// carga nro de la direccion
		vistaCargaPersona.formattedTextField_2.setText(String.valueOf(persona.getNumeroDireccion()));
	}
	
	
	/**
     * ESTE MÉTODO PONE A LA ESCUCHA DE LOS EVENTOS
     * DE CLIC DE RATON A CADA UNO DE LOS RADIOBUTTON
     * DE LA VENTANA CREADA EN EL PAQUETE VISTA
     */
	public void escucharEventos(){
		vistaCargaPersona.btnNewButton.addActionListener(this);
		
		vistaCargaPersona.formattedTextField.addKeyListener(this);
		vistaCargaPersona.textField_3.addKeyListener(this);
		vistaCargaPersona.textField_4.addKeyListener(this);
		vistaCargaPersona.textField_5.addKeyListener(this);
		vistaCargaPersona.textField_6.addKeyListener(this);
		vistaCargaPersona.dateChooser. addPropertyChangeListener(this);
		vistaCargaPersona.dateChooser_1.addPropertyChangeListener(this);
		vistaCargaPersona.dateChooser_2.addPropertyChangeListener(this);
		vistaCargaPersona.comboBox.addActionListener(this);
		vistaCargaPersona.comboBox_1.addActionListener(this);
		vistaCargaPersona.comboBox_2.addActionListener(this);
		vistaCargaPersona.formattedTextField_1.addKeyListener(this);
		vistaCargaPersona.formattedTextField_2.addKeyListener(this);

		
		vistaCargaPersona.frmCargaDePersona.addWindowListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(cargaHabilitada()) {
			vistaCargaPersona.btnNewButton.setEnabled(true);
		}
		else {
			vistaCargaPersona.btnNewButton.setEnabled(false);
		}
		
		//Se confirma la carga o modificacion de la persona
		if(e.getSource().equals(vistaCargaPersona.btnNewButton)) {
			boolean res;
			if(!esModificacion) {
				//si estamos en un controlador de carga de persona
					res = personaDAO.createPersona(
						vistaCargaPersona.formattedTextField.getText(),
						vistaCargaPersona.textField_3.getText(),
						vistaCargaPersona.textField_4.getText(),
						vistaCargaPersona.dateChooser.getDate(),
						(String)((ComboItem) vistaCargaPersona.comboBox.getSelectedItem()).getValue(),
						(int)((ComboItem) vistaCargaPersona.comboBox_1.getSelectedItem()).getValue(),
						vistaCargaPersona.textField_5.getText(),
						vistaCargaPersona.textField_6.getText(),
						vistaCargaPersona.dateChooser_1.getDate(),
						vistaCargaPersona.dateChooser_2.getDate(),
						vistaCargaPersona.formattedTextField_1.getText(),
						vistaCargaPersona.formattedTextField_2.getText(),
						(int)((ComboItem) vistaCargaPersona.comboBox_2.getSelectedItem()).getValue()
						);
					
					if(res) {
						new ModalGenerico("EXITO");
					}{
						new ModalGenerico("ERROR\n"
								+ "Verifique que no exista una persona\n"
								+ "con los datos de documento ingresados\n");
					}
			}else {
				//si estamos en un controlador de modificacion de persona
					res = personaDAO.updatePersona(
						vistaCargaPersona.formattedTextField.getText(),
						vistaCargaPersona.textField_3.getText(),
						vistaCargaPersona.textField_4.getText(),
						vistaCargaPersona.dateChooser.getDate(),
						(String)((ComboItem) vistaCargaPersona.comboBox.getSelectedItem()).getValue(),
						(int)((ComboItem) vistaCargaPersona.comboBox_1.getSelectedItem()).getValue(),
						vistaCargaPersona.textField_5.getText(),
						vistaCargaPersona.textField_6.getText(),
						vistaCargaPersona.dateChooser_1.getDate(),
						vistaCargaPersona.dateChooser_2.getDate(),
						vistaCargaPersona.formattedTextField_1.getText(),
						vistaCargaPersona.formattedTextField_2.getText(),
						(int)((ComboItem) vistaCargaPersona.comboBox_2.getSelectedItem()).getValue()
						);
					
					if(res) {
						new ModalGenerico("EXITO");
						//Para lo siguiente, se controla la vista de consulta de persona
						//Este control es derivado al controlador de consulta de persona.
						controladorConsultaPersona.realizarConsultaPersona(
								vistaCargaPersona.formattedTextField.getText(),
								(int)((ComboItem) vistaCargaPersona.comboBox_2.getSelectedItem()).getValue());
					}else{
						new ModalGenerico("ERROR");
					}
			}
			

			
		}
	}

	//Verifica que se cumplan las restricciones sobre los datos de entrada para permitir cargar persona en la DB
	private boolean cargaHabilitada() {
		boolean notNull,soloNumeros,inicioOSMenorFinOS;
		
		// Verifica que se cumplan las restricciones de NOT NULL de la DB sobre los atributos correspondientes
		notNull =(
				vistaCargaPersona.dateChooser.getDate() != null &&
				vistaCargaPersona.dateChooser_1.getDate() != null &&
				vistaCargaPersona.dateChooser_2.getDate() != null &&
				!vistaCargaPersona.formattedTextField.getText().equals("") &&
				!vistaCargaPersona.formattedTextField_1.getText().equals("") &&
				!vistaCargaPersona.formattedTextField_2.getText().equals("") &&
				!vistaCargaPersona.textField_3.getText().equals("") && 
				!vistaCargaPersona.textField_4.getText().equals("") && 
				!vistaCargaPersona.textField_5.getText().equals("") &&
				!vistaCargaPersona.textField_6.getText().equals("")

				);
		
		// Verifica que se cumplan restricciones de solo escribir enteros en los atributos correspondientes
		try {
			Integer.parseInt(vistaCargaPersona.formattedTextField.getText());
			Integer.parseInt(vistaCargaPersona.textField_5.getText());
			Integer.parseInt(vistaCargaPersona.formattedTextField_2.getText());
			soloNumeros = true;
		}catch(NumberFormatException e) {
			soloNumeros = false;
		}
		

		
		 if(notNull && soloNumeros) {
				return inicioOSMenorFinOS = (
						vistaCargaPersona.dateChooser_1.getDate().before(
								vistaCargaPersona.dateChooser_2.getDate())
						);
		 }else {
			 return false;
		 }
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Cuando se cierra la unica ventana de carga de persona existente, se modifica la flag que lo indica
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
		if(esModificacion) {
			vistaCargaPersona.existeUnaVentanaAbierta_modificacion = false;

		}else {
			vistaCargaPersona.existeUnaVentanaAbierta_carga = false;

		}
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(cargaHabilitada()) {
			vistaCargaPersona.btnNewButton.setEnabled(true);
		}
		else {
			vistaCargaPersona.btnNewButton.setEnabled(false);
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(cargaHabilitada()) {
			vistaCargaPersona.btnNewButton.setEnabled(true);
		}
		else {
			vistaCargaPersona.btnNewButton.setEnabled(false);
		}
	}
}
