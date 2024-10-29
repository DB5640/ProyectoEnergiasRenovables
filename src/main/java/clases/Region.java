package clases;

public class Region implements IEnergia {
    private int idRegion;
	public String nombre;

    


	


	public Region(int idRegion) {
		super();
		this.idRegion = idRegion;
	}


	public void calcularConsumoVsProduccion() {
        System.out.println("controller.Consumo vs produccion");
    }

    
    public void calcularProduccion() {

    }

   
    public void calcularConsumo() {

    }

    
    public void calcularPorcentaje() {

    }


	public int getIdRegion() {
		return idRegion;
	}


	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    
}
