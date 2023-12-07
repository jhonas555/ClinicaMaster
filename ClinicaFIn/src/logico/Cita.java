package logico;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Cita implements Serializable{
	
	private static final long serialVersionUID = 6871317562401895570L;

	private String id;
	private Date fecha;
	private Boolean estado;
	private Paciente paciente;
	private Doctor doctor;
	
	public Cita(Paciente paciente, Doctor doctor, Date fecha) {
		super();
		this.id = (String) id;
		this.fecha = fecha;
		this.paciente = paciente;
		this.doctor = doctor;
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
