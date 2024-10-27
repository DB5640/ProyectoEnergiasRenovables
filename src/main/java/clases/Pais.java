package clases;

public class Pais {
	private int  id;
	private String codigo;
    private String pais;
    
    

	public Pais() {
		super();
	}

	public Pais(int id, String codigo, String pais) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.pais = pais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
    
}
