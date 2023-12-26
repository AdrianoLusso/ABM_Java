package Modelo.Estadocivil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Modelo.BaseDatos;

public class EstadocivilDAO {

	public EstadocivilDAO() {}
	
	public void getAllEstadocivil(){
		LinkedList<Integer> id = new LinkedList();
		LinkedList<String> descrip = new LinkedList();
		ResultSet resultado = BaseDatos.consultar(
				"SELECT * "
				+ "FROM \"public\".\"testadocivil\";"
				);
	
		try {
			while(resultado.next()) {
				id.add(resultado.getInt("estcivil"));
				descrip.add(resultado.getString("descestcivilasi"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Estadocivil.cargarEstadocivil(id, descrip);
	}
}
