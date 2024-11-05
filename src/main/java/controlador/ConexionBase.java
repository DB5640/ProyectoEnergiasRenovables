package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionBase{
	
	private static Connection connection;

	   public static Connection getConenction() {
		   String pass = System.getenv("DBPass") == null ? "" : System.getenv("DBPass");
		   String schema = System.getenv("SCHEMA") == null ? "energiarenovable" : System.getenv("SCHEMA");
		   return getConnection(pass, schema);
	    }

    public static Connection getConnection(String pass, String schema) {

		   if(connection == null) {
	        	try {
	        		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema+"?serverTimezone=UTC","root",pass);
                    if (connection != null) {
                        System.out.println("Se conectó exitosamente a la base de datos: "+schema);
                    } else {
                      throw new SQLException("No se pudo conectar");
                    }
                }catch (SQLException e) {
					Logger.getLogger(ConexionBase.class.getName()).warning(e.getMessage());
	        	}
	        }
	        return connection;
	    }
	}


