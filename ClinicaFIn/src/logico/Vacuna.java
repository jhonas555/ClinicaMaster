package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacuna implements Serializable{

	private static final long serialVersionUID = -5213141717590670081L;
	
	private String numeroLote;
	private String nombre;
	private String fabricante;
	private ArrayList<Enfermedad> lasEnfermedades;
	
	public Vacuna(String numeroLote, String nombre, String fabricante, ArrayList<Enfermedad> lasEnfermedades) {
		super();
		this.numeroLote = numeroLote;
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.lasEnfermedades = lasEnfermedades;
	}
	
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}
	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
	
	
	
}
