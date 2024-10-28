package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
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
}
