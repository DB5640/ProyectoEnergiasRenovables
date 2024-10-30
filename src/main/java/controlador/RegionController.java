package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import clases.Region;
import model.RegionModel;


public class RegionController {

	private RegionModel regionModel;
	
	public RegionController() {
		
	    regionModel = new RegionModel();
		
	}
	
	public  List<Region> listarRegion()throws SQLException {
        
			
				return regionModel.listarRegion();
    }

	public Region consultarPais(String nombreRegion) throws SQLException {
        return regionModel.consultarRegion(nombreRegion);
    }

}
