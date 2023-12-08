package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacuna implements Serializable{

	public Vacuna(String numeroLote, String nombre, String fabricante, logico.Enfermedad enfermedad) {
		super();
		this.numeroLote = numeroLote;
		this.nombre = nombre;
		this.fabricante = fabricante;
		Enfermedad = enfermedad;
	}
	private static final long serialVersionUID = -5213141717590670081L;
	
	private String numeroLote;
	private String nombre;
	private String fabricante;
	private Enfermedad Enfermedad;
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
	public Enfermedad getEnfermedad() {
		return Enfermedad;
	}
	public void setEnfermedad(Enfermedad enfermedad) {
		Enfermedad = enfermedad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}
