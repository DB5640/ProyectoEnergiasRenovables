package controlador;

import java.sql.SQLException;

import clases.Produccion;
import model.ProduccionModel;

public class ProduccionController {

	private ProduccionModel produccionModel;
	
	
	
	public ProduccionController() {
		
		produccionModel = new ProduccionModel();
	}



	public Produccion producionAnual(int id)throws SQLException {
		
		return produccionModel.produccionModel(id);
	}
	
  public Produccion producionPais(int id)throws SQLException {
		
		return produccionModel.produccionPorPaisModel(id);
	}
}
