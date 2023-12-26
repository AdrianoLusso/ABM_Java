
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Modelo.BaseDatos;
import Modelo.Persona.Persona;
import Modelo.Persona.PersonaDAO;
import Vista.ConsultaPersona.ConsultaPersona;
import Vista.ConsultaPersona.PantallaPrincipal;
import Modelo.WebService;


public class Main {
	
	public static void main(String[] args){
		Connection conex = null;
		PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
		
		PersonaDAO personaDAO = new PersonaDAO();	
		Persona persona;
		
		//TEST CONEXION
		conex = BaseDatos.abrirConexion();
		try {
			if(!conex.isClosed()){
				    // Informamos que la conexión es correcta
				System.out.println("Conectado correctamente");
			}else{ // Sino informamos que no nos podemos conectar.
				System.out.println("No has podido conectarte");
			}
			
			BaseDatos.cerrarConexion(conex);

			if(conex.isClosed()){
				// Informamos que la conexión es correcta
				System.out.println("Se ha cerrado correctamente");
			}else{ // Sino informamos que no nos podemos conectar.
				System.out.println("No se ha podido cerrar");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TEST PERSONA DAO
		//System.out.println(personaDAO.getPersona("00000001", 1));
		//System.out.println(personaDAO.deletePersona("04040791", 2));
		Date d =new Date(System.currentTimeMillis());
		System.out.println(
				personaDAO.createPersona("11342344", "lusso", "adri", d, "M", 1,
						"299608", "adri@gmail", d, d, "aa","12", 1));
		
		
		//TEST WEB SERVICE
		WebService ws = new WebService();
		
	    HashMap params = new HashMap<>();
		//params.put("op","{\"wsnombre\":\"datoafiliado\",\"doc\":\"27091730\",\"uwnombre\":\"usuario\"}");
		params.put("wsnombre","datoafiliado");
		params.put("doc", "27091730");
		params.put("uwnombre","usuario");
	    System.out.println(params);
		
	    // Agregar otros parámetros necesarios según la operación del WS
	    HashMap<String, Object> respuesta = ws.llamar(params);
	    System.out.println(respuesta);
	}
}
