package logico;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable{
	
	private static final long serialVersionUID = 9151878833473137070L;
	
	protected String id;
	protected String password;
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String telefono;
	protected String correoElectronico;
	
	public Persona(String id, String password, String cedula, String nombre, String apellido,
			String telefono, String correoElectronico) {
		super();
		this.id = id;
		this.password = password;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
