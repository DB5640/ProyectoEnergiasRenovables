package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import clases.Planta;
import model.RegionModel;


public class RegionController {

	private RegionModel regionModel;
	
	public RegionController() {
		
	    regionModel = new RegionModel();
		
	}
	
	public  List<Pais> listarRegion()throws SQLException {
        
			
				return regionModel.listarRegion();
    }

	public Pais consultarPais(int idPais) throws SQLException {
        return regionModel.consultarPais(idPais);
    }

	public void agregarPais(Pais pais) throws SQLException {
        regionModel.agregarPais(pais);
    }
	public void actualizarPais(Pais pais) throws SQLException {
        regionModel.actualizarPais(pais);
    }
	
	public void eliminarPais(int id) throws SQLException {
        regionModel.eliminarPais(id);
    }
}
