package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Modelo.Persona.PersonaDAO;
import Vista.ConsultaPersona.ConfirmacionBorrarPersona;
import Vista.ConsultaPersona.ConsultaPersona;

public class ControladorConfirmacionBorrarPersona implements ActionListener,WindowListener {

	private ConfirmacionBorrarPersona vistaConfirmacionBorrarPersona;
	private ControladorConsultaPersona controladorConsultaPersona;
	private PersonaDAO personaDAO = new PersonaDAO();
	
	public ControladorConfirmacionBorrarPersona(ConfirmacionBorrarPersona vista, ControladorConsultaPersona controladorConsultaPersona) {
		vistaConfirmacionBorrarPersona = vista;
		this.controladorConsultaPersona = controladorConsultaPersona;
	}

	public void escucharEventos() {
		vistaConfirmacionBorrarPersona.btnNewButton.addActionListener(this);
		vistaConfirmacionBorrarPersona.btnNewButton_1.addActionListener(this);
		vistaConfirmacionBorrarPersona.frame.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nrodocumento;
		int tipodocumento;
		
		//Se confirma el borrado de la persona
		if(e.getSource().equals(vistaConfirmacionBorrarPersona.btnNewButton)) {
			
			//Se obtiene nro y tipo de documento de la persona por borrar
			nrodocumento = vistaConfirmacionBorrarPersona.personaPorBorrar.getNrodocumento();
			tipodocumento = vistaConfirmacionBorrarPersona.personaPorBorrar.getTipodocumento();
			
			//Se elimina a la persona
			personaDAO.deletePersona(nrodocumento,tipodocumento);
			
			//Para lo siguiente, se controla la vista de consulta de persona
			//Este control es derivado al controlador de consulta de persona.
			controladorConsultaPersona.realizarConsultaPersona(nrodocumento,tipodocumento);
			
			//Se cierra la vista de confirmacion de borrar persona
			vistaConfirmacionBorrarPersona.frame.dispose();
		}
		
		if(e.getSource().equals(vistaConfirmacionBorrarPersona.btnNewButton_1)) {
			vistaConfirmacionBorrarPersona.frame.dispose();
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
		vistaConfirmacionBorrarPersona.existeUnaVentanaAbierta = false;
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
