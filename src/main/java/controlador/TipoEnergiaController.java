package controlador;

import java.sql.SQLException;
import java.util.List;

import clases.TipoEnergia;
import clases.TipoEnergia;
import model.PlantaModel;
import model.TipoEnergiaModel;



public class TipoEnergiaController {
	
    private TipoEnergiaModel tipoEnergiaModel;
	
	public TipoEnergiaController() {
		
		 tipoEnergiaModel = new TipoEnergiaModel();
		
	}

	public  List<TipoEnergia> listarTipoEnergia()throws SQLException {
	       
		return tipoEnergiaModel.listarTipoEnergia();
     }
	public TipoEnergia consultarTipoEnergia(int idTipoEnergia) throws SQLException {
        return tipoEnergiaModel.consultarTipoEnergia(idTipoEnergia);
    }

	public void agregarTipoEnergia(TipoEnergia tipoEnergia) throws SQLException {
        tipoEnergiaModel.agregarTipoEnergia(tipoEnergia);
    }
	public void actualizarTipoEnergia(TipoEnergia tipoEnergia) throws SQLException {
        tipoEnergiaModel.actualizarTipoEnergia(tipoEnergia);
    }
	
	public void eliminarTipoEnergia(int id) throws SQLException {
        tipoEnergiaModel.eliminarTipoEnergia(id);
    }
}
