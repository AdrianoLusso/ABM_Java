package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import Modelo.Estadocivil.EstadocivilDAO;
import Modelo.Persona.Persona;
import Modelo.Persona.PersonaDAO;
import Modelo.Tiposdoc.TiposdocDAO;
import Vista.ConsultaPersona.CargaPersona;
import Vista.ConsultaPersona.ComboItem;
import Vista.ConsultaPersona.ConsultaPersona;
import Vista.ConsultaPersona.PantallaPrincipal;

public class ControladorPantallaPrincipal implements ActionListener{
	//creamos la pantalla principal
		private PantallaPrincipal vistaPantallaPrincipal;
		private ConsultaPersona vistaConsultaPersona;
		private CargaPersona vistaCargaPersona;
		private TiposdocDAO tiposdocDAO = new TiposdocDAO();
		private EstadocivilDAO estadocivilDAO = new EstadocivilDAO();
		
		public ControladorPantallaPrincipal(PantallaPrincipal vpp){
			// A La variable vistaPintarVentana se le asignara
	        // un objeto de la clase PintarVentana llamado vPintar
	        // y el cual se le esta pasando por parámetro
	        // en el constructor de esta clase
			vistaPantallaPrincipal = vpp;
			vistaPantallaPrincipal.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);;
			
			//Obtiene y guarda los tipos de documento en una clase estatica
			tiposdocDAO.getAllTiposdoc();
			estadocivilDAO.getAllEstadocivil();
			}
		
		
		/**
	     * ESTE MÉTODO PONE A LA ESCUCHA DE LOS EVENTOS
	     * DE CLIC DE RATON A CADA UNO DE LOS RADIOBUTTON
	     * DE LA VENTANA CREADA EN EL PAQUETE VISTA
	     */
		public void escucharEventos(){
			vistaPantallaPrincipal.btnNewButton.addActionListener(this);
			vistaPantallaPrincipal.btnNewButton_1.addActionListener(this);
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Se presiono el boton para ir a consulta de persona
			if(e.getSource().equals(vistaPantallaPrincipal.btnNewButton) && !vistaConsultaPersona.existeUnaVentanaAbierta) {
				//Cuando se abre la unica ventana de consulta de persona que puede existir, se modifica la flag que lo indica
				vistaConsultaPersona.existeUnaVentanaAbierta = true;
				vistaConsultaPersona = new ConsultaPersona();
				vistaConsultaPersona.frmDdd.setVisible(true);
				}
			//Se presiono el boton para ir a carga de persona
			if(e.getSource().equals(vistaPantallaPrincipal.btnNewButton_1) && !vistaCargaPersona.existeUnaVentanaAbierta_carga) {
				//Cuando se abre la unica ventana de consulta de persona que puede existir, se modifica la flag que lo indica
				vistaCargaPersona.existeUnaVentanaAbierta_carga = true;
				vistaCargaPersona = new CargaPersona();
				vistaCargaPersona.frmCargaDePersona.setVisible(true);
				}
			}
}

