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

	public void agregarTipoEnergia(TipoEnergia pais) throws SQLException {
        tipoEnergiaModel.agregarTipoEnergia(pais);
    }
	public void actualizarTipoEnergia(TipoEnergia pais) throws SQLException {
        tipoEnergiaModel.actualizarTipoEnergia(pais);
    }
	
	public void eliminarTipoEnergia(int id) throws SQLException {
        tipoEnergiaModel.eliminarTipoEnergia(id);
    }
}
