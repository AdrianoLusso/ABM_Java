package Modelo.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import Modelo.BaseDatos;

public class PersonaDAO {
	
	public PersonaDAO() {}
	/*
	Obtiene persona a partir de un numero de documento:
	SELECT p.nrodoc, p.tipodoc, p.apellido, p.nombres, p.fechanac, p.sexo,
	p.estcivil, p.telefono,p.email,p.fechainios, p.fechafinos,d.calle,d.nro,e.estadoasi
	FROM public.persona p
	LEFT JOIN
	public.direccion d ON d.iddireccion = p.iddireccion
	RIGHT JOIN
	public.afilsosunc a ON p.nrodoc = a.nrodoc
	LEFT JOIN
	public.estados e ON a.idestado = e.idestado
	WHERE p.nrodoc = 'nrodocumento' AND p.tipodoc = tipodocumento;
	 */
	public LinkedList<Persona> getPersona(String nrodocumento,int tipodocumento) {
		Persona persona = null;
		LinkedList personas = new LinkedList();
		
		
		ResultSet resultado = BaseDatos.consultar(
				"SELECT p.nrodoc, p.tipodoc, p.apellido, p.nombres, p.fechanac, p.sexo,\n"
				+ "p.estcivil, p.telefono,p.email,p.fechainios, p.fechafinos,d.calle,d.nro,e.estadoasi\n"
				+ "FROM public.persona p\n"
				+ "LEFT JOIN\n"
				+ "public.direccion d ON d.iddireccion = p.iddireccion\n"
				+ "RIGHT JOIN\n"
				+ "public.afilsosunc a ON p.nrodoc = a.nrodoc\n"
				+ "LEFT JOIN\n"
				+ "public.estados e ON a.idestado = e.idestado"
				+ "	WHERE p.nrodoc = '"+nrodocumento+"' AND p.tipodoc = "+tipodocumento+""
				+ "LIMIT 1;"
				);
		
		try {	
						
			//Creo las personas con los datos encontrados 
			while(resultado.next()) {
				persona = new Persona(resultado.getString("nrodoc"),resultado.getInt("tipodoc"));
				persona.setApellido(resultado.getString("apellido"));
				persona.setNombre(resultado.getString("nombres"));
				persona.setFechaNacimiento(resultado.getDate("fechanac"));
				persona.setSexo(resultado.getString("sexo"));
				persona.setEstadoCivil(resultado.getString("estcivil"));
				persona.setTelefono(resultado.getString("telefono"));
				persona.setEmail(resultado.getString("email"));
				persona.setIniOS(resultado.getDate("fechainios"));
				persona.setFinOS(resultado.getDate("fechafinos"));
				persona.setCalleDireccion(resultado.getString("calle"));
				persona.setNumeroDireccion(resultado.getInt("nro"));
				persona.setDescripEstado(resultado.getString("estadoasi"));
				personas.add(persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return personas;
	}
	
	public boolean createPersona(
	String nrodoc, String apellido, String nombres, Date fechanac,String sexo,int estcivil,String telefono,
	String email, Date fechainios, Date fechafinos,String calleDireccion,String nroDireccion, int tipodoc
	)
	{
		boolean result,result1,result2;
		ResultSet resultGetIddireccion;
		int iddireccion=-1;
		
		/*
		SELECT iddireccion FROM "public"."direccion"
		where calle = 'BOLIVIA' and nro =11
		order by direccioncc desc
		limit 1;
		buscamos la direccion con la calle y nro ingresada
		 */
		resultGetIddireccion = BaseDatos.consultar(""
				+ "SELECT iddireccion FROM \"public\".\"direccion\"\n"
				+ "where calle = '"+calleDireccion+"' and nro ="+nroDireccion+"\n"
				+ "order by direccioncc desc\n"
				+ "limit 1;");
		
		try {
		if(resultGetIddireccion.next()) {
			// si se encontro una direccion con el nro y calle ingresados,se usa ese iddireccion			
				iddireccion = resultGetIddireccion.getInt("iddireccion");
		}else {
			// caso contrario, se debe crear una direccion nueva y volver a tratar de obtener la tupla
			BaseDatos.post("INSERT INTO direccion (calle, nro,idprovincia,idlocalidad)\n"
					+ "VALUES ("
					+ " '"+calleDireccion+"',"
					+ "'"+nroDireccion+"',"
					+ "1,1"
					+ ")");
			
			// en este caso, como la tupla con esa direccion fue creada, el get deberia devolver una tupla
			resultGetIddireccion = BaseDatos.consultar(""
					+ "SELECT iddireccion FROM \"public\".\"direccion\"\n"
					+ "where calle = '"+calleDireccion+"' and nro ="+nroDireccion+"\n"
					+ "order by direccioncc desc\n"
					+ "limit 1;");
			resultGetIddireccion.next();
			iddireccion = resultGetIddireccion.getInt("iddireccion");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		result1= BaseDatos.post(
				"INSERT INTO Persona (nrodoc, apellido,nombres,fechanac,sexo,estcivil,telefono,email,fechainios,fechafinos,iddireccion,tipodoc"
				+ ") "      
				        + "VALUES ('" + nrodoc + "', "
				        + "'" + apellido + "', "
				        + "'" + nombres + "', "
				        + "'" + fechanac + "', "
				        + "'" + sexo + "', "
				        + "'" + estcivil + "', "
				        + "'" + telefono + "', "
				        + "'" + email + "', "
				        + "'" + fechainios + "', "
				        + "'"+fechafinos + "', "
				        + "'"+iddireccion + "',"
				        	+ "'" +tipodoc+ "')"
				);
		
		result2 = BaseDatos.post(
				"INSERT INTO afilsosunc (nrodoc,nrocuilini,nrocuildni,nrocuilfin,tipodoc,idestado)"
				+ "VALUES ("
				+ "'"+nrodoc+"',"
				+ "'0',"
				+ "'"+nrodoc+"',"
				+ "'0',"
				+ "'"+tipodoc+"',"
				+ "2"
				+ ")"
				);
		result = result1 && result2;
		
		return result;
			
	}
	
	public boolean createPersona(String nrodocumento,int tipodocumento) {
		boolean resultado = BaseDatos.post("UPDATE \"public\".\"afilsosunc\""
				+"SET idestado = 2"+
				"WHERE nrodoc = '"+nrodocumento+"' AND tipodoc ="+tipodocumento+";");
		
		return resultado;
	}
	
	public boolean updatePersona(String nrodoc, String apellido, String nombres, Date fechanac,String sexo,int estcivil,String telefono,
	String email, Date fechainios, Date fechafinos,String calleDireccion,String nroDireccion, int tipodoc) {
		
		boolean result,result1,result2;
		ResultSet resultGetIddireccion;
		int iddireccion=-1;
		
		/*
		SELECT iddireccion FROM "public"."direccion"
		where calle = 'BOLIVIA' and nro =11
		order by direccioncc desc
		limit 1;		
		buscamos la direccion con la calle y nro ingresada
		 */
		resultGetIddireccion = BaseDatos.consultar(""
				+ "SELECT iddireccion FROM \"public\".\"direccion\"\n"
				+ "where calle = '"+calleDireccion+"' and nro ="+nroDireccion+"\n"
				+ "order by direccioncc desc\n"
				+ "limit 1;");
		
		try {
		if(resultGetIddireccion.next()) {
			// si se encontro una direccion con el nro y calle ingresados,se usa ese iddireccion			
				iddireccion = resultGetIddireccion.getInt("iddireccion");
		}else {
			// caso contrario, se debe crear una direccion nueva y volver a tratar de obtener la tupla
			BaseDatos.post("INSERT INTO direccion (calle, nro,idprovincia,idlocalidad)\n"
					+ "VALUES ("
					+ " '"+calleDireccion+"',"
					+ "'"+nroDireccion+"',"
					+ "1,1"
					+ ")");
			
			// en este caso, como la tupla con esa direccion fue creada, el get deberia devolver una tupla
			resultGetIddireccion = BaseDatos.consultar(""
					+ "SELECT iddireccion FROM \"public\".\"direccion\"\n"
					+ "where calle = '"+calleDireccion+"' and nro ="+nroDireccion+"\n"
					+ "order by direccioncc desc\n"
					+ "limit 1;");
			resultGetIddireccion.next();
			iddireccion = resultGetIddireccion.getInt("iddireccion");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		 * UPDATE Persona
		SET apellido = '',
		nombres = '',
		fechanac = '',
		sexo ='',
		estcivil = '',
		telefono = '',
		email = '',
		fechainios = ,
		fechafinos = ,
		iddireccion = 
		WHERE nrodoc = ''  AND tipodoc = ;
		 */
		result1 = BaseDatos.post("UPDATE Persona "
				+ "SET apellido = '"+apellido+"',"
				+ "nombres = '"+nombres+"',"
				+ "fechanac = '"+fechanac+"',"
				+ "sexo ='"+sexo+"',"
				+ "estcivil = '"+estcivil+"',"
				+ "telefono = '"+telefono+"',"
				+ "email = '"+email+"',"
				+ "fechainios = '"+fechainios+"',"
				+ "fechafinos = '"+fechafinos+"',"
				+ "iddireccion = "+iddireccion+" "
				+ "WHERE nrodoc = '"+nrodoc+"'  AND tipodoc = "+tipodoc+"");
		
		return result1;
	}
	
	/*
	UPDATE "public"."afilsosunc"
	SET idestado = 54
	WHERE nrodoc = 'nrodocumento' AND tipodoc = tipodocumento;
	Realiza un borrado logico de una persona. Para eso, se modifica
	*/
	public boolean deletePersona(String nrodocumento,int tipodocumento) {
		boolean resultado = BaseDatos.post("UPDATE \"public\".\"afilsosunc\""
				+"SET idestado = 4"+
				"WHERE nrodoc = '"+nrodocumento+"' AND tipodoc ="+tipodocumento+";");
		
		return resultado;
	}
}
