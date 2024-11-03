package clases;

import java.sql.Date;



public class Planta extends TipoEnergia {
    private double capacidad;
    private int id_planta;
    public Date año;
    String nombreEnergia;
    
    
	public Planta( int id_planta,double capacidad, Date año,int id_tipoEnergia,String nombreEnergia) {
		super(id_tipoEnergia,nombreEnergia);
		this.capacidad = capacidad;
		this.id_planta = id_planta;
		this.año = año;
		this.nombreEnergia= nombreEnergia;
		
	}

	
	

	public Planta() {
		super();
	}

	public String getNombreEnergia() {
		return nombreEnergia;
	}

	public void setNombreEnergia(String nombreEnergia) {
		this.nombreEnergia = nombreEnergia;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public int getId_planta() {
		return id_planta;
	}

	public void setId_planta(int id_planta) {
		this.id_planta = id_planta;
	}




	public Date getAño() {
		return año;
	}




	public void setAño(Date año) {
		this.año = año;
	}



	
    
}
