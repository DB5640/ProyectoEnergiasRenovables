package clases;

import java.util.Date;

public class Planta extends Region {
    public double capacidadGeneracion;
    public String region;
    public Date anio;
    
	public Planta(double capacidadGeneracion, String region, Date anio) {
		super();
		this.capacidadGeneracion = capacidadGeneracion;
		this.region = region;
		this.anio = anio;
	}

	public double getCapacidadGeneracion() {
		return capacidadGeneracion;
	}

	public void setCapacidadGeneracion(double capacidadGeneracion) {
		this.capacidadGeneracion = capacidadGeneracion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getAnio() {
		return anio;
	}

	public void setAnio(Date anio) {
		this.anio = anio;
	}
    
	
    
}
