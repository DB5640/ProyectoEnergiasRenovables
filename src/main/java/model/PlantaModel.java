package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Planta;
import controlador.ConexionBase;

public class PlantaModel {

	private Connection connection;
	
    public PlantaModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Object[]> listarPlantaTabla()throws SQLException {
		
		
     List<Object[]> lista = new ArrayList<>();
     String query = "SELECT * FROM planta";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
         lista.add( new Object[] {
         rs.getInt("id_planta"),
         rs.getString("id_tipoenergia"),
         rs.getDouble("capacidad"),
         rs.getDate("anio_planta"),
         rs.getInt("id_region")
         });
         
     }
     rs.close();
     st.close();
     return lista;

	}
	public List<Planta> listarPlanta()throws SQLException {
		
		
	     List<Planta> plantas = new ArrayList<>();
	     String query = "SELECT * FROM planta";
	     Statement st = connection.createStatement();
	     ResultSet rs = st.executeQuery(query);

	     while (rs.next()) {
	    	 
	    	 
	         Planta planta= new Planta();
	         planta.setId_planta(rs.getInt("id_planta"));
	         planta.setCapacidad(rs.getDouble("capacidad"));
	         planta.setAño(rs.getDate("anio_planta"));
	         planta.setId_tipoEnergia(rs.getInt("id_tipoenergia"));
	         plantas.add(planta);
	         
	     }
	     for (Planta item : plantas) {
	        	System.out.println(item.getId_tipoEnergia());
	        	
	        }
	     rs.close();
	     st.close();
	     return plantas;

		}
	
	public Planta consultarPlanta(int id) throws SQLException {
        //String query = "SELECT * FROM planta WHERE id_planta = ?";
		/*String query= "SELECT id_planta,capacidad,anio_planta,nombre_region,nombre_tipoenergia "
				+ "FROM planta as pla,region as regi,tipoenergia as tipen where pla.id_planta = tipen.id_tipoenergia AND  pla.id_planta = regi.id_region"
				+ "AND id_planta = ?";*/
		String query = "SELECT id_planta, capacidad, anio_planta, nombre_region, nombre_tipoenergia,tipen.id_tipoenergia " +
	               "FROM planta AS pla " +
	               "JOIN region AS regi ON pla.id_planta = regi.id_region " +
	               "JOIN tipoenergia AS tipen ON pla.id_planta = tipen.id_tipoenergia " +
	               "WHERE id_planta = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int capacidad = resultSet.getInt("capacidad");
            Date año = resultSet.getDate("anio_planta");
            String tipoenergia = resultSet.getString("nombre_tipoenergia");
            System.out.println(tipoenergia +"model");
            int idTipoEnergia =resultSet.getInt("id_tipoenergia");
            Planta planta = new Planta(0,capacidad,año,idTipoEnergia,tipoenergia);
            return planta;
        }   
        statement.close();      
        return null;
    }

    
}
