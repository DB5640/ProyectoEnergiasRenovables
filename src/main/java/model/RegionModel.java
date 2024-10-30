package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Region;
import controlador.ConexionBase;

public class RegionModel {

	private Connection connection;
	
    public RegionModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Region> listarRegion()throws SQLException {
		
		
     List<Region> Regiones = new ArrayList<>();
     String query = "SELECT * FROM region";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
         Region Region= new Region();
         Region.setIdRegion(rs.getInt("id_region"));
         Region.setNombre(rs.getString("nombre_region"));
         Regiones.add(Region);
         
     }
     rs.close();
     st.close();
     return Regiones;

	}
	
	public Region consultarRegion(String nombre) throws SQLException {
        String query = "SELECT * FROM region WHERE nombre = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombre);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id_Region");
            String codigo = resultSet.getString("codigo");
            Region Region = new Region();
            return Region;
        }   
        statement.close();      
        return null;
    }

    
}
