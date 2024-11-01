package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.TipoEnergia;
import clases.TipoEnergia;
import controlador.ConexionBase;

public class TipoEnergiaModel {
	
	private Connection connection;
	
    public TipoEnergiaModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }
	
	
	
	public List<TipoEnergia> listarTipoEnergia()throws SQLException {
		
	     List<TipoEnergia> energias = new ArrayList<>();
	     String query = "SELECT * FROM tipoenergia";
	     Statement st = connection.createStatement();
	     ResultSet rs = st.executeQuery(query);

	     while (rs.next()) {
	         TipoEnergia energia= new TipoEnergia();
	         energia.setId_tipoEnergia(rs.getInt("id_tipoenergia"));
	         energia.setNombreEnergia(rs.getString("nombre_tipoenergia"));
	         energia.setFuente(rs.getString("fuente"));
	         energias.add(energia);
	         
	     }
	     rs.close();
	     st.close();
	     return energias;

		}
	
	public TipoEnergia consultarTipoEnergia(int id_tipoEnergia) throws SQLException {
        String query = "SELECT * FROM tipoenergia WHERE id_tipoenergia = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_tipoEnergia);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre_tipoenergia");
            String fuente = resultSet.getString("fuente");
            TipoEnergia tipoEnergia = new TipoEnergia(0,nombre, fuente);
            return tipoEnergia;
        }   
        statement.close();      
        return null;
    }
	
	public void agregarTipoEnergia(TipoEnergia tipoEnergia) throws SQLException {
        String query = "INSERT INTO tipoenergia (nombre_tipoenergia,fuente) VALUES (?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, tipoEnergia.getNombreEnergia());
        ps.setString(2, tipoEnergia.getFuente());
        ps.executeUpdate();
        ps.close();
    }

	public void actualizarTipoEnergia(TipoEnergia tipoEnergia) throws SQLException {
        String query = "UPDATE tipoenergia SET nombre_tipoenergia = ?, fuente=?  WHERE id_tipoenergia = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, tipoEnergia.getNombreEnergia());
        ps.setString(2, tipoEnergia.getFuente());
        ps.setInt(3, tipoEnergia.getId_tipoEnergia());
        ps.executeUpdate();
        ps.close();
    }
	
	public void eliminarTipoEnergia(int id) throws SQLException {
        String query = "DELETE FROM tipoenergia WHERE id_tipoenergia = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
    
}
