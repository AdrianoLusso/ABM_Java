package Modelo;
import java.sql.*;

public class BaseDatos{

	//PREGUNTA: Como conviene trabajar aca en SOSUNC? Creando y cerrando una conexion por cada acceso, o manteniendo una unica conexion por DAO todo
	//el tiempo, y que se cierre al finalizar el programa?
	private static final String driver = "org.postgresql.Driver";
	private static final String bbdd = "jdbc:postgresql://192.9.200.9:5432/SOSSIGES_BETA";
	private static final String usuario = "postgres";
	private static final String clave = "postgrespostgres";	

	/* Creamos el método para conectarnos a la base de datos. Este método
    devolverá un objeto de tipo Connection.*/
	public static Connection abrirConexion() {
	    /*Primero la iniciamos en null.*/
		Connection conex = null;
		
	    //Controlamos la excepciones que puedan surgir al conectarnos a la BBDD
		try {
	        //Registrar el driver
			Class.forName(driver);
	        //Creamos una conexión a la Base de Datos-
			conex = DriverManager.getConnection(bbdd,usuario,clave);
		// Si hay errores informamos al usuario. 
		}catch(Exception e) {
			System.out.println("Error al conectar con la base de datos:\n"
					+ e.getMessage().toString());
			System.out.println(e);
		}
		
	    // Devolvemos la conexión.
		return conex;		
	}
		
	public static void cerrarConexion(Connection conex){
	    try{
	        // Cerramos la conexión
	        conex.close();    
	    }catch(SQLException e){
	       /* Controlamos excepción en caso de que se pueda producir
	        a la hora de cerrar la conexión*/
	        System.out.println(e.getMessage().toString());
	    }
	}

	//Realiza lectura
	public static ResultSet consultar(String sql){
		ResultSet resultado;
		Statement sentencia;
		Connection conex = abrirConexion();
		
		//Se realiza la consulta sql y se guarda el resultado.
		try {
			sentencia = conex.createStatement();
			resultado = sentencia.executeQuery(sql);
		}catch(SQLException e) {
			resultado = null;
		}
				
		cerrarConexion(conex);
		return resultado;
	}
	
	//Realiza escritura
	public static boolean post(String sql) {
		Statement sentencia;
		Connection conex = abrirConexion();
		boolean exito ;

		
		try {
			sentencia = conex.createStatement();
			sentencia.execute(sql);
			exito = true;
		} catch (SQLException e) {
			exito = false;
		}
		
		return exito;
	}
	
}
