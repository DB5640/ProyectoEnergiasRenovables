package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import clases.Planta;
import controlador.ConexionBase;

public class RegionModel {

	private Connection connection;
	
    public RegionModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Pais> listarRegion()throws SQLException {
		
		
     List<Pais> regiones = new ArrayList<>();
     String query = "SELECT * FROM region";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
         Pais region= new Pais();
         region.setIdpais(rs.getInt("id_region"));
         region.setNombre(rs.getString("nombre_region"));
         regiones.add(region);
         
     }
     rs.close();
     st.close();
     return regiones;

	}
	
	public Pais consultarPais(int idPais) throws SQLException {
        String query = "SELECT * FROM region WHERE id_region = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idPais);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre_region");
            Pais Region = new Pais(0,nombre);
            return Region;
        }   
        statement.close();      
        return null;
    }
	
	public void agregarPlanta(Pais pais) throws SQLException {
        String query = "INSERT INTO region (nombre_region) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, pais.getNombre());
        ps.executeUpdate();
        ps.close();
    }

	public void actualizarPais(Pais pais) throws SQLException {
        String query = "UPDATE region SET nombre_region = ?  WHERE id_region = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, pais.getNombre());
        ps.executeUpdate();
        ps.close();
    }
	
	public void eliminarPais(int id) throws SQLException {
        String query = "DELETE FROM region WHERE id_region = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
    
}
