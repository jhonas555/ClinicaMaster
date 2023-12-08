package logico;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Cita implements Serializable{
	
	public Cita(String id, Date string, Doctor doctorSelec, Paciente pacienteSelec) {
		super();
		this.id = id;
		this.fecha = string;
		this.paciente = pacienteSelec;
		this.doctor = doctorSelec;
	}


	private static final long serialVersionUID = 6871317562401895570L;

	private String id;
	private Date fecha;
	private Paciente paciente;
	private Doctor doctor;
	

	
	
	


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


}
