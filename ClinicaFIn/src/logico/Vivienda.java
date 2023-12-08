package logico;

import java.io.Serializable;

import java.util.ArrayList;

public class Vivienda implements Serializable {

	public Vivienda(String id, String nombre, ArrayList<Doctor> losDoctores, ArrayList<Paciente> losPacientes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.losDoctores = losDoctores;
		this.losPacientes = losPacientes;
	}
	private static final long serialVersionUID = 7998444807365673173L;
	
	private String id;
	private String nombre;
	private ArrayList<Doctor>losDoctores;
	private ArrayList<Paciente>losPacientes;
	
	
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
	public ArrayList<Doctor> getLosDoctores() {
		return losDoctores;
	}
	public void setLosDoctores(ArrayList<Doctor> losDoctores) {
		this.losDoctores = losDoctores;
	}
	public ArrayList<Paciente> getLosPacientes() {
		return losPacientes;
	}
	public void setLosPacientes(ArrayList<Paciente> losPacientes) {
		this.losPacientes = losPacientes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
