package controlador;

import java.sql.SQLException;

import clases.Produccion;
import model.ProduccionModel;

public class ProduccionController {

	private ProduccionModel produccionModel;
	
	
	
	public ProduccionController() {
		
		produccionModel = new ProduccionModel();
	}



	public Produccion producionHidraulicaAnual(int id)throws SQLException {
		
		return produccionModel.produccionHidraulicaModel(id);
	}
}
