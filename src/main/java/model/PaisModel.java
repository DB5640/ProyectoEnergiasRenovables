package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import controlador.ConexionBase;

public class PaisModel {

	private Connection connection;
	
    public PaisModel() {
 	   
 	   connection= ConexionBase.getConenction();
    }

	
	public List<Pais> listarPais()throws SQLException {
		
		
     List<Pais> paises = new ArrayList<>();
     String query = "SELECT * FROM region";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
         Pais pais= new Pais();
         pais.setId(rs.getInt("id_pais"));
         pais.setCodigo(rs.getString("codigo"));
         pais.setPais(rs.getString("nombre"));
         paises.add(pais);
         
     }
     rs.close();
     st.close();
     return paises;

	}
	
	public Pais consultarPais(String nombre) throws SQLException {
        String query = "SELECT * FROM region WHERE nombre = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombre);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id_pais");
            String codigo = resultSet.getString("codigo");
            Pais pais = new Pais(id, codigo,null);
            return pais;
        }   
        statement.close();      
        return null;
    }

    
}
