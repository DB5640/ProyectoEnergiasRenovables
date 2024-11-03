package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.EnergiaHidroelectrica;
import clases.Pais;
import clases.Planta;
import clases.Produccion;
import controlador.ConexionBase;

public class ProduccionModel {

	private Connection connection;
	
	private EnergiaHidroelectrica energiaHidroelectica;
	
    public ProduccionModel() {
 	   
 	   connection= ConexionBase.getConenction();
 	   energiaHidroelectica= new EnergiaHidroelectrica();
    }
    
	public Produccion produccionModel(int id)throws SQLException {
		
	 String query = "SELECT id_planta, capacidad, anio_planta,p.id_region,nombre_region,nombre_tipoenergia,t.id_tipoenergia " +
             "FROM planta AS p "+
             "JOIN region AS r ON r.id_region= p.id_region " +
             "JOIN tipoenergia AS t ON t.id_tipoenergia= p.id_tipoenergia "+
             "where id_planta= ?";
     PreparedStatement statement = connection.prepareStatement(query);
     statement.setInt(1, id);
     ResultSet resultSet = statement.executeQuery();
     
     if (resultSet.next()) {
         int capacidad = resultSet.getInt("capacidad");
         Date año =resultSet.getDate("anio_planta");
         int idTipoEnergia =resultSet.getInt("id_tipoenergia");
         String nombreEnergia = resultSet.getString("nombre_tipoenergia");
         String nombrePais=resultSet.getString("nombre_region");
         int idRegion = resultSet.getInt("id_region");
         System.out.println(idRegion+ "produ");
         Pais pais = new Pais(idRegion,nombrePais);
         Produccion produccion = new Produccion(0,capacidad,año,idTipoEnergia,nombreEnergia,pais);
        
        return produccion;
     }  
     resultSet.close();
     statement.close();      
     return null;
	}
	
	public Produccion produccionPorPaisModel(int id)throws SQLException {
		
		 String query = " SELECT id_planta,SUM(capacidad)as sumCapacidad, anio_planta,p.id_region,nombre_region "
		 		+ "FROM planta as p join region as r on p.id_region=r.id_region WHERE r.id_region= ?";
	     PreparedStatement statement = connection.prepareStatement(query);
	     statement.setInt(1, id);
	     ResultSet resultSet = statement.executeQuery();
	     
	     if (resultSet.next()) {
	         int capacidad = resultSet.getInt("sumCapacidad");
	         Date año =resultSet.getDate("anio_planta");
	        // int idTipoEnergia =resultSet.getInt("id_tipoenergia");
	        // String nombreEnergia = resultSet.getString("nombre_tipoenergia");
	         String nombrePais=resultSet.getString("nombre_region");
	         int idRegion = resultSet.getInt("id_region");
	         System.out.println(idRegion+ "produ");
	         Pais pais = new Pais(idRegion,nombrePais);
	         Produccion produccion = new Produccion(0,capacidad,año,0,null,pais);
	        
	        return produccion;
	     }  
	     resultSet.close();
	     statement.close();      
	     return null;
		}
}
