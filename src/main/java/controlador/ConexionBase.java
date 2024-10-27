package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBase {
	
	private static Connection connection;
	
	   public static Connection getConenction() {
	        if(connection == null) {
	        	
	        	try {
	        		
	        		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC","root","");
	        	}catch (SQLException e) {
	        		e.printStackTrace();
	        	}
	        }
	        return connection;
	    }
	}


