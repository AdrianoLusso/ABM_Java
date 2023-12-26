package Controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modelo.Estadocivil.Estadocivil;
import Modelo.Persona.Persona;
import Modelo.Persona.PersonaDAO;
import Modelo.Tiposdoc.Tiposdoc;
import Modelo.Tiposdoc.TiposdocDAO;
import Vista.ConsultaPersona.*;

public class ControladorConsultaPersona implements ActionListener,WindowListener,ListSelectionListener {

	private ConsultaPersona vistaConsultaPersona;
	private ConfirmacionBorrarPersona vistaConfirmacionBorrado;
	private ConfirmacionAltaPersona vistaConfirmacionAltaPersona;
	private CargaPersona vistaModificacionPersona;
	private PersonaDAO personaDAO;

	
	public ControladorConsultaPersona(ConsultaPersona vpp){
		// A La variable vistaPintarVentana se le asignara
        // un objeto de la clase PintarVentana llamado vPintar
        // y el cual se le esta pasando por parámetro
        // en el constructor de esta clase
		vistaConsultaPersona = vpp;
		personaDAO = new PersonaDAO();
		
		//Definimos el estado por default de la tabla de personas
		DefaultTableModel modelo = (DefaultTableModel) vistaConsultaPersona.table.getModel();
		vistaConsultaPersona.personas = personaDAO.getPersona("00000001", 1);
		vistaConsultaPersona.personasSize = vistaConsultaPersona.personas.size();
		
		//Para cada persona obtenida, se la agrega como una fila
		Object[] datosPersona;
		for(int i=0;i<vistaConsultaPersona.personasSize;i++) {
			datosPersona = vistaConsultaPersona.personas.get(i).getRow();
			datosPersona[1] = Tiposdoc.obtenerPorId((int)datosPersona[1]);
			datosPersona[5] = (datosPersona[5].equals("M"))?"Masculino":"Femenino";
			datosPersona[6] = Estadocivil.obtenerPorId(Integer.parseInt((String)datosPersona[6]));
			modelo.addRow(datosPersona);
		}
		
		//Cargo en el combobox cada ComboItem con los datos de los tipos de documento
		for(int i = 0; i<Tiposdoc.length();i++) {
			vistaConsultaPersona.comboBox.addItem(
					new ComboItem(Tiposdoc.obtenerId(i),Tiposdoc.obtenerDescripcion(i))
					);
		}
	}

	
	/**
     * ESTE MÉTODO PONE A LA ESCUCHA DE LOS EVENTOS
     * DE CLIC DE RATON A CADA UNO DE LOS RADIOBUTTON
     * DE LA VENTANA CREADA EN EL PAQUETE VISTA
     */
	public void escucharEventos(){
		vistaConsultaPersona.btnNewButton.addActionListener(this);
		vistaConsultaPersona.btnNewButton_1.addActionListener(this);
		vistaConsultaPersona.btnNewButton_2.addActionListener(this);
		vistaConsultaPersona.btnNewButton_3.addActionListener(this);
		vistaConsultaPersona.frmDdd.addWindowListener(this);
		vistaConsultaPersona.table.getSelectionModel().addListSelectionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		String nrodocumento;
		int tipodocumento;
		
		//Se presiono boton de busqueda de persona
		if(e.getSource().equals(vistaConsultaPersona.btnNewButton)) {		
			
			//se obtiene numero y tipo de documento ingresado por usuario
			nrodocumento = vistaConsultaPersona.textField.getText();
			tipodocumento = (int)((ComboItem) vistaConsultaPersona.comboBox.getSelectedItem()).getValue();
			
			//Entra al metodo que encapsula la logica de consulta de persona
			this.realizarConsultaPersona(nrodocumento,tipodocumento);
		}
		
		//Se presiono el boton para confirmar el borrado de una persona
		if(e.getSource().equals(vistaConsultaPersona.btnNewButton_1) && !vistaConfirmacionBorrado.existeUnaVentanaAbierta) {
			//Cuando se abre la unica ventana de confirmacion de borrar persona que puede existir, se modifica la flag que lo indica
			vistaConfirmacionBorrado.existeUnaVentanaAbierta = true;
			vistaConfirmacionBorrado = new ConfirmacionBorrarPersona(vistaConsultaPersona.personas.get(vistaConsultaPersona.personaElegida),this);
			vistaConfirmacionBorrado.frame.setVisible(true);
		}
		
		//Se presiono el boton para confirmar el alta de una persona
		if(e.getSource().equals(vistaConsultaPersona.btnNewButton_3) && !vistaConfirmacionAltaPersona.existeUnaVentanaAbierta) {
			//Cuando se abre la unica ventana de confirmacion de alta persona que puede existir, se modifica la flag que lo indica
			vistaConfirmacionAltaPersona.existeUnaVentanaAbierta = true;
			vistaConfirmacionAltaPersona = new ConfirmacionAltaPersona(vistaConsultaPersona.personas.get(vistaConsultaPersona.personaElegida),this);
			vistaConfirmacionAltaPersona.frame.setVisible(true);
		}
		
		//Se presiono el boton para modificar una persona
		if(e.getSource().equals(vistaConsultaPersona.btnNewButton_2) && !vistaModificacionPersona.existeUnaVentanaAbierta_modificacion) {
			//Cuando se abre la unica ventana de modificacion de persona que puede existir, se modifica la flag que lo indica
			vistaModificacionPersona.existeUnaVentanaAbierta_modificacion = true;
			vistaModificacionPersona = new CargaPersona(vistaConsultaPersona.personas.get(vistaConsultaPersona.personaElegida),this);
			vistaModificacionPersona.frmCargaDePersona.setVisible(true);

		}
	}

	//Este metodo encapsula la logica de obtener una persona segun un nro y tipo de dni, y de actualizar la vista correspondientemente
	//Pensado para ser invocado desde este controlador y el controlador de confirmacion de borrado de persona
	public void realizarConsultaPersona(String nrodocumento, int tipodocumento) {
		DefaultTableModel modelo;
		
		//se obtienen a la personas con ese documento
		vistaConsultaPersona.personas = (personaDAO.getPersona(nrodocumento,tipodocumento));
		vistaConsultaPersona.personasSize = vistaConsultaPersona.personas.size();

		//se obtiene el modelo de la tabla de personas
		modelo = (DefaultTableModel) vistaConsultaPersona.table.getModel();
		modelo.setRowCount(0);
		
		//si se obtuvo al menos una persona con ese documento, se ingresan en la tabla.
		if(vistaConsultaPersona.personasSize != 0) {
			//Para cada persona obtenida, se la agrega como una fila
			Object[] datosPersona;
			for(int i=0;i<vistaConsultaPersona.personasSize;i++) {
				datosPersona = vistaConsultaPersona.personas.get(i).getRow();
				datosPersona[1] = Tiposdoc.obtenerPorId((int)datosPersona[1]);
				datosPersona[5] = (datosPersona[5].equals("M"))?"Masculino":"Femenino";
				datosPersona[6] = Estadocivil.obtenerPorId(Integer.parseInt((String)datosPersona[6]));
				modelo.addRow(datosPersona);
			}
		}
		
		//Se resetea el indice que marca que persona esta elegida;
		vistaConsultaPersona.personaElegida = -1;
		vistaConsultaPersona.btnNewButton_1.setEnabled(false);
		vistaConsultaPersona.btnNewButton_2.setEnabled(false);
		vistaConsultaPersona.btnNewButton_3.setEnabled(false);
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Cuando se cierra la unica ventana de consulta de persona existente, se modifica la flag que lo indica
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		vistaConsultaPersona.existeUnaVentanaAbierta = false;
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


	// ListSelectionListener
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int personaElegida = vistaConsultaPersona.table.getSelectedRow();
		
		//Si se selecciona una persona
		if(personaElegida >= 0) {
			vistaConsultaPersona.btnNewButton_2.setEnabled(true);
		}
		
			//Si se selecciona una persona con estado ACTIVA
		if (personaElegida >= 0  
			&& vistaConsultaPersona.personas.get(personaElegida).getDescripEstado().equals("Activo")
			) {
			//Se guarda la persona elegida
			vistaConsultaPersona.personaElegida = personaElegida;
			vistaConsultaPersona.btnNewButton_1.setEnabled(true);
			vistaConsultaPersona.btnNewButton_2.setEnabled(true);
		}	
		
		//Si se selecciona una persona estado PASIVA
		if (personaElegida >= 0  
				&& vistaConsultaPersona.personas.get(personaElegida).getDescripEstado().equals("Pasivo")
				) {
				//Se guarda la persona elegida
				vistaConsultaPersona.personaElegida = personaElegida;
				vistaConsultaPersona.btnNewButton_3.setEnabled(true);
			}
	}
}
