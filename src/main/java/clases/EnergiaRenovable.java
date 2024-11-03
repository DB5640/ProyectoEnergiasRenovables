package clases;

import java.util.Date;

public class EnergiaRenovable extends Planta {
	
   
	public String tipoEnergia;
    public String fuente;

    public double producionHidraulicaAnual(double capacidad) {
    	double producionAnual=0;
    	double producionMensual;
    	
    	producionMensual= capacidad*720;
    	
    	producionAnual*=12;
    	
    	return producionAnual;
     }

    public double producionHidraulicaMensual(double capacidad) {

    	double producionMensual;
    	
    	producionMensual= capacidad*720;

    	return producionMensual;
     }

}
