package logico;

import java.io.Serializable;

import java.util.ArrayList;

public class Vivienda implements Serializable {

	private static final long serialVersionUID = 7998444807365673173L;
	
	private String id;
	private String nombre;
	private ArrayList<Persona>lasPersonas;

	public Vivienda(String id, String nombre, ArrayList<Persona> lasPersonas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.lasPersonas = lasPersonas;
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}
	public void setLasPersonas(ArrayList<Persona> lasPersonas) {
		this.lasPersonas = lasPersonas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
