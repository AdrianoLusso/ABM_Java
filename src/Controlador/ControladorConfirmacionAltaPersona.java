package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Modelo.Persona.PersonaDAO;
import Vista.ConsultaPersona.ConfirmacionAltaPersona;
import Vista.ConsultaPersona.ConfirmacionBorrarPersona;

public class ControladorConfirmacionAltaPersona implements ActionListener,WindowListener{

	private ConfirmacionAltaPersona vistaConfirmacionAltaPersona;
	private ControladorConsultaPersona controladorConsultaPersona;
	private PersonaDAO personaDAO = new PersonaDAO();
	
	public ControladorConfirmacionAltaPersona(ConfirmacionAltaPersona vista,ControladorConsultaPersona controladorConsultaPersona) {
		vistaConfirmacionAltaPersona = vista;
		this.controladorConsultaPersona = controladorConsultaPersona;
	}

	public void escucharEventos() {
		vistaConfirmacionAltaPersona.btnNewButton.addActionListener(this);
		vistaConfirmacionAltaPersona.btnNewButton_1.addActionListener(this);
		vistaConfirmacionAltaPersona.frame.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nrodocumento;
		int tipodocumento;
		
		//Se confirma el alta de la persona
		if(e.getSource().equals(vistaConfirmacionAltaPersona.btnNewButton)) {
			//Se obtiene el nro y tipo de documento
			nrodocumento = vistaConfirmacionAltaPersona.personaPorDarAlta.getNrodocumento();
			tipodocumento = vistaConfirmacionAltaPersona.personaPorDarAlta.getTipodocumento();
			
			//Se da de alta a la persona
			personaDAO.createPersona(nrodocumento,tipodocumento);
			
			//Para lo siguiente, se controla la vista de consulta de persona
			//Este control es derivado al controlador de consulta de persona.
			controladorConsultaPersona.realizarConsultaPersona(nrodocumento,tipodocumento);
			
			//Se cierra la vista de confirmacion de borrar persona
			vistaConfirmacionAltaPersona.frame.dispose();
		}
		
		//Se deniega el alta de la persona
		if(e.getSource().equals(vistaConfirmacionAltaPersona.btnNewButton_1)) {
			vistaConfirmacionAltaPersona.frame.dispose();
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

	@Override
	public void windowClosed(WindowEvent e) {
		vistaConfirmacionAltaPersona.existeUnaVentanaAbierta = false;
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
}
