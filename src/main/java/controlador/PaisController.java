package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import model.PaisModel;


public class PaisController {

	private PaisModel paisModel;
	
	public PaisController() {
		
		paisModel = new PaisModel();
		
	}
	
	public  List<Pais> listarPais()throws SQLException {
        
			
				return paisModel.listarPais();
    }

	public Pais consultarPais(String nombrePais) throws SQLException {
        return paisModel.consultarPais(nombrePais);
    }

}
