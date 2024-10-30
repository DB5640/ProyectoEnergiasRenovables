package clases;

import javax.swing.ListModel;

public class TipoEnergia {
 private int id_tipoEnergia;
 private String nombreEnergia;
 private String fuente;
 
 


public TipoEnergia(int id_tipoEnergia, String nombreEnergia) {
	
	this.id_tipoEnergia = id_tipoEnergia;
	this.nombreEnergia = nombreEnergia;
	
	
}


public TipoEnergia() {
	super();
}


public int getId_tipoEnergia() {
	
	return id_tipoEnergia;
}
public void setId_tipoEnergia(int id_tipoEnergia) {
	this.id_tipoEnergia = id_tipoEnergia;
}

public String getNombreEnergia() {
	return nombreEnergia;
}
public void setNombreEnergia(String nombreEnergia) {
	this.nombreEnergia = nombreEnergia;
}
public String getFuente() {
	return fuente;
}
public void setFuente(String fuente) {
	this.fuente = fuente;
}


@Override
public String toString() {
	return nombreEnergia;
}
 


 
}
