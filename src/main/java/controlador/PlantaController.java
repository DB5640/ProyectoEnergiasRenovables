package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Pais;
import clases.Planta;
import model.PlantaModel;



public class PlantaController {

	private PlantaModel plantaModel;
	
	public PlantaController() {
		
		plantaModel = new PlantaModel();
		
	}
	
	public  List<Object[]> listarPlantaTabla()throws SQLException {
        
			
				return plantaModel.listarPlantaTabla();
    }
	
	
	public  List<Planta> listarPlanta()throws SQLException {
       
		return plantaModel.listarPlanta();
     }

	
	public Planta consultarPlanta(int idPlanta) throws SQLException {
        return plantaModel.consultarPlanta(idPlanta);
    }

}
