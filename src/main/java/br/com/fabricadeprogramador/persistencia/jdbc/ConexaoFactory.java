package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {	
		
		Connection con=null;
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb", "postgres", "postgres");
		} 
		catch (ClassNotFoundException e) {				
			System.out.println("Driver n�o encontrado.");
		}		
		catch (SQLException e) {			
			System.out.println("N�o pode conectar: "+e.getMessage());
		}
		
		return con;
	}	

}
