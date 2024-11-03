package clases;

import java.util.Date;

public class Produccion extends Planta implements IEnergia {
    public Pais pais;
    public EnergiaRenovable tipoEnergiaRenovable;
    public double cantidadEnergia;

// public void calcularProduccion(){};
    
   // public void calcularPorcentajeProduccion(){};
    
   
    
   public Produccion(int id_planta, double capacidad, java.sql.Date año, int id_tipoEnergia, String nombreEnergia,
		Pais pais) {
	super(id_planta, capacidad, año, id_tipoEnergia, nombreEnergia);
	this.pais = pais;
	
	produccionAnual(capacidad);
	
	produccionMensual(capacidad);
	
}

public Produccion() {
	
    }

@Override
    public double produccionAnual(double capacidad) {
    	double produccionAnual=0;
    	double produccionMensual;
    	
    	produccionMensual= capacidad*720;
    	
    	produccionAnual = produccionMensual * 12;
    	
    	
    	return produccionAnual;
     }
   @Override
    public double produccionMensual(double capacidad) {

    	double producionMensual;
    	
    	producionMensual= capacidad*720;

    	return producionMensual;
     }

	
	

}
