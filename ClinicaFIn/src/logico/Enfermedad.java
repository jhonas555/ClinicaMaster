package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	
	private static final long serialVersionUID = 9006561590184928182L;
	
	private String id;
	private String nombre;
	private String descripcion;
	
	public Enfermedad(String id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
