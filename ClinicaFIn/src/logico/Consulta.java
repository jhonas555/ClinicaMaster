package logico;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;

public class Consulta implements Serializable{
	
	private static final long serialVersionUID = -1856229194597506735L;
	
	private String id;
	private Date fecha;
	private String motivo; 
	private String diagnostico;
	private ArrayList<Enfermedad> lasEnfermedades;
	
	public Consulta(String id, Date fecha, String motivo, String diagnostico, ArrayList<Enfermedad> lasEnfermedades) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.lasEnfermedades = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}
	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
    public Paciente getPaciente() {
        return getPaciente();
    }

}
