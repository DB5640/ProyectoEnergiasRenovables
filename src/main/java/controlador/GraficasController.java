package controlador;

import java.sql.SQLException;
import java.util.List;

import model.GraficasModel;



public class GraficasController {

	public GraficasModel graficasModel;
	
	public GraficasController() {
		
		graficasModel= new GraficasModel();
	}
	
	public  List<Object[]> listarTipoEnergia()throws SQLException {
        
		
		return graficasModel.listaCantidaPorTipoenergia();
    }
}
