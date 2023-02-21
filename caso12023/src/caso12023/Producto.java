package caso12023;

public class Producto {
	
	public Producto(int id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}

	private int id;
	
	private String tipo;
	
	private String mensaje;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
