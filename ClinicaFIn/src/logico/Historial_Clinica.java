package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Historial_Clinica implements Serializable{
	
	private static final long serialVersionUID = -1735516965450146267L;
	
	private String id;
	private ArrayList<Consulta> lasConsultas;
	
	public Historial_Clinica(String id, ArrayList<Consulta> lasConsultas) {
		super();
		this.id = id;
		this.lasConsultas = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
}
