package controlador;

import java.sql.SQLException;
import java.util.List;

import clases.TipoEnergia;
import model.PlantaModel;
import model.TipoEnergiaModel;



public class TipoEnergiaController {
	
    private TipoEnergiaModel TipoEnergiaModel;
	
	public TipoEnergiaController() {
		
		 TipoEnergiaModel = new TipoEnergiaModel();
		
	}

	public  List<TipoEnergia> listarTipoEnergia()throws SQLException {
	       
		return TipoEnergiaModel.listarTipoEnergia();
     }
}
