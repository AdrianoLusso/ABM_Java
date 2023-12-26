package Modelo.Tiposdoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

import Modelo.BaseDatos;
import Modelo.Persona.Persona;

public class TiposdocDAO {

	public TiposdocDAO() {}
	
	//Retorna un ResultSet con todos los tipos de documento
	public void getAllTiposdoc() {
		
		LinkedList<Integer> id = new LinkedList();
		LinkedList<String> descrip = new LinkedList();
		ResultSet resultado = BaseDatos.consultar(
				"SELECT *"
				+"FROM \"public\".\"tiposdoc\" ");
		
		try {
			while(resultado.next()) {
				id.add(resultado.getInt("tipodoc"));
				descrip.add(resultado.getString("descrip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Tiposdoc.cargarTiposdoc(id,descrip);
	}
}
