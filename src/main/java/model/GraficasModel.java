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

public class GraficasModel {

	private Connection connection;
	
    public GraficasModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Object[]> listaCantidaPorTipoenergia()throws SQLException {
		
		
     List<Object[]> lista = new ArrayList<>();
     String query = "SELECT nombre_tipoenergia, COUNT(p.id_tipoenergia) as cantidad "
     		+ "from planta as p join tipoenergia as t on p.id_tipoenergia = t.id_tipoenergia  GROUP by p.id_tipoenergia";
     Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery(query);
     while (rs.next()) {
         lista.add(new Object[]{
             rs.getString("nombre_tipoenergia"),
             rs.getInt("cantidad")
         });
     }
     rs.close();
     st.close();
     return lista;

	}
	


    
}
