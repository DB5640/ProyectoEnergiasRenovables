package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import clases.Planta;

import controlador.ConexionBase;

public class PlantaModel {

	private Connection connection;
	
    public PlantaModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Object[]> listarPlantaTabla()throws SQLException {
		
		
     List<Object[]> lista = new ArrayList<>();
    // String query = "SELECT * FROM planta";
     String query ="SELECT id_planta,tipo.nombre_tipoenergia as nombreenergia, capacidad, anio_planta, regi.nombre_region as nombreregion "
     		+ " FROM planta as plan, region as regi, tipoenergia as tipo  WHERE id_planta = regi.id_region and id_planta = tipo.id_tipoenergia "
     		+ " and plan.id_planta = plan.id_planta ";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
         lista.add( new Object[] {
         rs.getInt("id_planta"),
         rs.getString("nombreenergia"),
         rs.getDouble("capacidad"),
         rs.getDate("anio_planta"),
         rs.getString("nombreregion")
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
            Date año =resultSet.getDate("anio_planta");
            String tipoenergia = resultSet.getString("nombre_tipoenergia");
            String nombreRegion=resultSet.getString("nombre_region");
            System.out.print(nombreRegion);
            int idTipoEnergia =resultSet.getInt("id_tipoenergia");
            Planta planta = new Planta(0,capacidad,año,idTipoEnergia,tipoenergia);
            Pais region = new Pais(0,nombreRegion);
            return planta;
        }  
        resultSet.close();
        statement.close();      
        return null;
    }

	public void agregarPlanta(Planta planta,Pais region) throws SQLException {
        String query = "INSERT INTO planta (capacidad,anio_planta,id_tipoenergia,id_region) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDouble(1, planta.getCapacidad());
        ps.setDate(2, (Date) planta.getAño());
        ps.setInt(3, planta.getId_tipoEnergia());
        ps.setInt(4, region.getIdpais());
        ps.executeUpdate();
        ps.close();
    }
	
	public void actualizarPlanta(Planta planta) throws SQLException {
        String query = "UPDATE planta SET capacidad = ?  WHERE id_planta = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDouble(1, planta.getCapacidad());
        ps.setInt(2, planta.getId_planta());
        ps.executeUpdate();
        ps.close();
    }
	
	public void eliminarPlanta(int id) throws SQLException {
        String query = "DELETE FROM planta WHERE id_planta = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }



}
