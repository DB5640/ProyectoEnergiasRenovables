package clases;

public class Pais  {
	private int  idPais;
    private String nombre;
    
    

	public Pais() {
		super();
	}



	public Pais(int idPais, String nombre) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
	}



	public int getIdpais() {
		return idPais;
	}



	public void setIdpais(int idpais) {
		this.idPais = idpais;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return nombre;
	}



	
    
}
