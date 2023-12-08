package logico;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Visual.PrincipalVisual;
import Visual.VisualMedico;


public class Clinica implements Serializable{
	
	private static final long serialVersionUID = 5307172532851770925L;

    private ArrayList<Doctor> losDoctores;
    private ArrayList<Vacuna> lasVacunas;
    private ArrayList<Vivienda> lasViviendas;
    private ArrayList<Cita> lasCitas;
    private ArrayList<Enfermedad> lasEnfermedades;
    private ArrayList<Persona> lasPersonas;
    private ArrayList<Paciente> losPacientes;
    private ArrayList<Consulta> lasConsultas;
    private ArrayList<User> losUsuarios;
    private static int idVacunas = 1;
    private static int idViviendas = 1;
    private static int idCitas = 1;
    private static int idEnfermedades = 1;
    private static int idPersonas = 1;
    private static int idDoctores = 1;
    private static int idPacientes = 1;
    private static int idConsultas = 1;
    private static int idUsuarios = 1;
    private static Clinica clinica;
	private static User loginUser;

    public Clinica() {
        super();
        this.losDoctores = new ArrayList<>();
        this.lasVacunas = new ArrayList<>();
        this.lasViviendas = new ArrayList<>();
        this.lasCitas = new ArrayList<>();
        this.lasEnfermedades = new ArrayList<>();
        this.lasPersonas = new ArrayList<>();
        this.losPacientes = new ArrayList<>();
        this.lasConsultas = new ArrayList<>();
		this.losUsuarios = new ArrayList<User>();
    }

    public static Clinica getInstance() {
        if (clinica == null) {
            clinica = new Clinica();
        }
        cargarPacientesDesdeArchivo();
        cargarEnfermedadesDesdeArchivo();
        cargarVacunasDesdeArchivo();
        cargarCitasDesdeArchivo();
        cargarViviendasDesdeArchivo();
        cargarDoctoresDesdeArchivo();
        cargarUsuarioDesdeArchivo();
        return clinica;
    }
  	
	public ArrayList<Doctor> getDoctores(){
		return losDoctores;
	}
	public void setDoctores(ArrayList<Doctor> losDoctores) {
		this.losDoctores = losDoctores;
	}

	public ArrayList<Consulta> getConsultas(){
		return lasConsultas;
	}
	public void setConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
	
	public ArrayList<Paciente> getPaciente(){
		return losPacientes;
	}
	public void setPaciente(ArrayList<Paciente> losPacientes) {
		this.losPacientes = losPacientes;
	}
	public ArrayList<Vacuna> getLasVacunas() {
		return lasVacunas;
	}
	public void setLasVacunas(ArrayList<Vacuna> lasVacunas) {
		this.lasVacunas = lasVacunas;
	}
	public ArrayList<Vivienda> getLasViviendas() {
		return lasViviendas;
	}
	public void setLasViviendas(ArrayList<Vivienda> lasViviendas) {
		this.lasViviendas = lasViviendas;
	}
	public ArrayList<Cita> getLasCitas() {
		return lasCitas;
	}
	public void setLasCitas(ArrayList<Cita> lasCitas) {
		this.lasCitas = lasCitas;
	}
	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}
	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}
	public void setLasPersonas(ArrayList<Persona> lasPersonas) {
		this.lasPersonas = lasPersonas;
	}	
	public ArrayList<User> getlosUsuarios() {
		return losUsuarios;
	}
	public void setMisUsuarios(ArrayList<User> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
	public static User getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(User loginUser) {
		Clinica.loginUser = loginUser;
	}
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}
	public static int getIdDoctores() {
		return idDoctores;
	}
	public static int getIdVacunas() {
		return idVacunas;
	}
	public static int getIdViviendas() {
		return idViviendas;
	}
	public static int getIdCitas() {
		return idCitas;
	}
	public static int getIdEnfermedades() {
		return idEnfermedades;
	}
	public static int getIdPersonas() {
		return idPersonas;
	}
	public static int getIdPcientes() {
		return idPacientes;
	}	
	
	public static int getIdConsultas() {
		return idConsultas;
	}
	
	
	public void AgregarUser(User user) {
		losUsuarios.add(user);
	}
	
	public void EliminarUser(User user) {
		losUsuarios.remove(user);
	}
	
	public boolean confirmLogin(String usuario, String password) {
		boolean login = false;
		for (User user : losUsuarios) {
			if (user.getUsuario().equals(usuario) && user.getPassword().equals(password)) {
				loginUser = user;
				login = true;
			}
		}
		return login;
	}

public User getUsuarioporUsuario(String string) {

		User temp = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < losUsuarios.size()) {
			if (losUsuarios.get(i).getUsuario().equalsIgnoreCase(string)) {
				temp = losUsuarios.get(i);
				encontrado = true;
			}
			i++;
		}
		return temp;
	}


public void agregarUser(User usuario) {
    losUsuarios.add(usuario);
    guardarUsuariosEnArchivo(); // Assuming there's a method to save the users to a file
}

public User buscarUsuarioPorNombre(String nombreUsuario) {
    for (User usuario : losUsuarios) {
        if (usuario.getUsuario().equalsIgnoreCase(nombreUsuario)) {
            return usuario;
        }
    }
    return null;
}

public void eliminarUsuario(String usuario) {
    losUsuarios.removeIf(user -> user.getUsuario().equals(usuario));
    guardarUsuariosEnArchivo(); 
}

private static void guardarUsuariosEnArchivo() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Usuarios.dat"))) {
        oos.writeObject(clinica.losUsuarios);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void cargarUsuarioDesdeArchivo() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("losUsuarios.dat"))) {
        ArrayList<User> loadedUsuarios = (ArrayList<User>) ois.readObject();
        if (loadedUsuarios != null && !loadedUsuarios.isEmpty()) {
            clinica.losUsuarios = loadedUsuarios;
        } else {
            clinica.losUsuarios = new ArrayList<>();
        }
    } catch (FileNotFoundException fileNotFoundException) {
        clinica.losUsuarios = new ArrayList<>();
    } catch (EOFException e) {
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}


public void actualizarUsuario(String usuario, User nuevoUsuario) {
    for (User usuarios : losUsuarios) {
        if (usuarios.getUsuario().equals(usuario)) {
            usuarios.setPassword(nuevoUsuario.getPassword());
            usuarios.setTipo(nuevoUsuario.getTipo());

            break;
        }
    }
    guardarUsuariosEnArchivo();
}



public void agregarConsulta(Consulta consulta) {
    consulta.setId(String.valueOf(idConsultas++));
    lasConsultas.add(consulta);
    guardarConsultasEnArchivo();
}

public Consulta buscarConsultaPorId(String id) {
    for (Consulta consulta : lasConsultas) {
        if (consulta.getId().equalsIgnoreCase(id)) {
            return consulta;
        }
    }
    return null;
}

public void eliminarConsultas(String id) {
    lasConsultas.removeIf(consulta -> consulta.getId().equals(id));
    guardarConsultasEnArchivo();
}

private static void guardarConsultasEnArchivo() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lasConsultas.dat"))) {
        oos.writeObject(clinica.lasConsultas);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void cargarConsultaDesdeArchivo() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lasConsultas.dat"))) {
        ArrayList<Consulta> loadedConsultas = (ArrayList<Consulta>) ois.readObject();
        if (loadedConsultas != null && !loadedConsultas.isEmpty()) {
            clinica.lasConsultas = loadedConsultas;

            int maxId = loadedConsultas.stream()
                    .map(consulta -> extractId(consulta.getId()))
                    .max(Integer::compare)
                    .orElse(0);

            idConsultas = maxId + 1;
        } else {
            clinica.lasConsultas = new ArrayList<>();
        }
    } catch (FileNotFoundException fileNotFoundException) {
        clinica.lasConsultas = new ArrayList<>();
    } catch (EOFException e) {
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

public void actualizarConsulta(String id, Consulta nuevoConsulta) {
    for (Consulta consulta : lasConsultas) {
        if (consulta.getId().equals(id)) {
        	consulta.setFecha(nuevoConsulta.getFecha());
        	consulta.setDiagnostico(nuevoConsulta.getDiagnostico());;
        	consulta.setMotivo(nuevoConsulta.getMotivo());
        	consulta.setLasEnfermedades(nuevoConsulta.getLasEnfermedades());

            break;
        }
    }
    guardarConsultasEnArchivo();
}

	
    public void agregarPacientes(Paciente paciente) {
        paciente.setId(String.valueOf(idPacientes++));
        losPacientes.add(paciente);
        guardarPacientesEnArchivo();
    }
    
    public void eliminarPaciente(String id) {
        losPacientes.removeIf(pacientes -> pacientes.getId().equals(id));
        guardarPacientesEnArchivo();
    }
	
    public void actualizarPaciente(String id, Paciente nuevoPaciente) {
        for (Paciente pacientes : losPacientes) {
            if (pacientes.getId().equals(id)) {
                pacientes.setNombre(nuevoPaciente.getNombre());
                pacientes.setApellido(nuevoPaciente.getApellido());
                pacientes.setTelefono(nuevoPaciente.getTelefono());
                pacientes.setCorreoElectronico(nuevoPaciente.getCorreoElectronico());
                pacientes.setCedula(nuevoPaciente.getCedula());
                pacientes.setNumeroSeguro(nuevoPaciente.getNumeroSeguro());;
                break;
            }
        }
        guardarPacientesEnArchivo();
    }
	
	  private static void guardarPacientesEnArchivo() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pacientes.dat"))) {
	            oos.writeObject(clinica.losPacientes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
    private static void cargarPacientesDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pacientes.dat"))) {
            ArrayList<Paciente> loadedPaciente = (ArrayList<Paciente>) ois.readObject();
            if (loadedPaciente != null && !loadedPaciente.isEmpty()) {
                clinica.losPacientes = loadedPaciente;

                int maxId = loadedPaciente.stream()
                        .map(paciente -> extractId(paciente.getId()))
                        .max(Integer::compare)
                        .orElse(0);

                idPacientes = maxId + 1;
            } else {
                clinica.losPacientes = new ArrayList<>();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            clinica.losPacientes = new ArrayList<>();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Paciente buscarPacientePorId(String id) {
        for (Paciente pacientes : losPacientes) {
            if (pacientes.getId().equalsIgnoreCase(id)) {
                return pacientes;
            }
        }
        return null;
    }
 
    
    public void agregarDoctor(Doctor doctor) {
        doctor.setId(String.valueOf(idDoctores++));
        losDoctores.add(doctor);
        guardarDoctoresEnArchivo();
    }

    public void eliminarDoctor(String id) {
        losDoctores.removeIf(doctor -> doctor.getId().equals(id));
        guardarDoctoresEnArchivo();
    }
    
    public void actualizarDoctor(String id, Doctor nuevoDoctor) {
        for (Doctor doctor : losDoctores) {
            if (doctor.getId().equals(id)) {
                doctor.setNombre(nuevoDoctor.getNombre());
                doctor.setApellido(nuevoDoctor.getApellido());
                doctor.setTelefono(nuevoDoctor.getTelefono());
                doctor.setCorreoElectronico(nuevoDoctor.getCorreoElectronico());
                doctor.setNumeroLicenciaMedica(nuevoDoctor.getNumeroLicenciaMedica());
                doctor.setEspecialidad(nuevoDoctor.getEspecialidad());
                break;
            }
        }
        guardarDoctoresEnArchivo();
    }
    
    private static void guardarDoctoresEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("losDoctores.dat"))) {
            oos.writeObject(clinica.losDoctores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void cargarDoctoresDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("losDoctores.dat"))) {
            ArrayList<Doctor> loadedDoctores = (ArrayList<Doctor>) ois.readObject();
            if (loadedDoctores != null && !loadedDoctores.isEmpty()) {
                clinica.losDoctores = loadedDoctores;

                int maxId = loadedDoctores.stream()
                        .map(doctor -> extractId(doctor.getId()))
                        .max(Integer::compare)
                        .orElse(0);

                idDoctores = maxId + 1;
            } else {
                clinica.losDoctores = new ArrayList<>();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            clinica.losDoctores = new ArrayList<>();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Doctor buscarDoctorPorId(String id) {
        for (Doctor doctor : losDoctores) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
    }

	public void agregarEnfermedad(Enfermedad enfermedad) {
	    enfermedad.setId(String.valueOf(idEnfermedades++));
	    lasEnfermedades.add(enfermedad);
	    guardarEnfermedadesEnArchivo();
	}


	public void eliminarEnfermedad(String id) {
	    lasEnfermedades.removeIf(enfermedad -> enfermedad.getId().equals(id));
	    guardarEnfermedadesEnArchivo();
	}
	
	public void actualizarEnfermedad(String id, Enfermedad nuevaEnfermedad) {
	    for (Enfermedad enfermedad : lasEnfermedades) {
	        if (enfermedad.getId().equals(id)) {

	            enfermedad.setNombre(nuevaEnfermedad.getNombre());
	            enfermedad.setDescripcion(nuevaEnfermedad.getDescripcion());
	            break;
	        }
	    }

	    guardarEnfermedadesEnArchivo();
	}

	private void guardarEnfermedadesEnArchivo() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enfermedades.dat"))) {
	        oos.writeObject(lasEnfermedades);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void cargarEnfermedadesDesdeArchivo() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enfermedades.dat"))) {
	        ArrayList<Enfermedad> loadedEnfermedades = (ArrayList<Enfermedad>) ois.readObject();
	        if (loadedEnfermedades != null && !loadedEnfermedades.isEmpty()) {
	            clinica.lasEnfermedades = loadedEnfermedades;

	            int maxId = loadedEnfermedades.stream()
	                    .map(enfermedad -> extractId(enfermedad.getId()))
	                    .max(Integer::compare)
	                    .orElse(0);

	            idEnfermedades = maxId + 1;
	        } else {
	            clinica.lasEnfermedades = new ArrayList<>();
	        }
	    } catch (FileNotFoundException fileNotFoundException) {
	        clinica.lasEnfermedades = new ArrayList<>();
	    } catch (EOFException e) {
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public Enfermedad buscarEnfermedadPorId(String id) {
		for (Enfermedad enfermedad : lasEnfermedades) {
			if(enfermedad.getId().equalsIgnoreCase(id))
				return enfermedad;
		}
		return null;
	}
	
	
	public void agregarVacuna(Vacuna vacuna) {
	    vacuna.setNumeroLote(String.valueOf(idVacunas++));
	    lasVacunas.add(vacuna);
	    guardarVacunasEnArchivo();
	}
	
	public void eliminarVacuna(String numeroLote) {
	    lasVacunas.removeIf(vacuna -> vacuna.getNumeroLote().equals(numeroLote));
	    guardarVacunasEnArchivo();
	}
	
	public void actualizarVacuna(String numeroLote, Vacuna nuevaVacuna) {
	    for (Vacuna vacuna : lasVacunas) {
	        if (vacuna.getNumeroLote().equalsIgnoreCase(numeroLote)) {
	        	vacuna.setNombre(nuevaVacuna.getNombre());
	        	vacuna.setFabricante(nuevaVacuna.getFabricante());
	        	vacuna.setEnfermedad(nuevaVacuna.getEnfermedad());
	            break;
	        }
	    }
	    guardarVacunasEnArchivo();
	}

	private void guardarVacunasEnArchivo() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vacunas.dat"))) {
	        oos.writeObject(lasVacunas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void cargarVacunasDesdeArchivo() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vacunas.dat"))) {
	        ArrayList<Vacuna> loadedVacunas = (ArrayList<Vacuna>) ois.readObject();
	        if (loadedVacunas != null && !loadedVacunas.isEmpty()) {
	            clinica.lasVacunas = loadedVacunas;

	            int maxId = loadedVacunas.stream()
	                    .map(vacuna -> extractNumeroLote(vacuna.getNumeroLote()))
	                    .max(Integer::compare)
	                    .orElse(0);
	            
	            idVacunas = maxId + 1;
	        } else {
	            clinica.lasVacunas = new ArrayList<>();
	        }
	    } catch (FileNotFoundException fileNotFoundException) {
	        clinica.lasVacunas = new ArrayList<>();
	    } catch (EOFException e) {
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
			
	public void agregarCita(Cita cita) {
	    cita.setId(String.valueOf(idCitas++));
	    lasCitas.add(cita);
	    guardarCitasEnArchivo();
	}
	
	public void eliminarCita(String id) {
	    lasCitas.removeIf(cita -> cita.getId().equals(id));
	    guardarCitasEnArchivo();
	}
	
	public void actualizarCita(String id, Cita nuevaCita) {
	    for (Cita cita : lasCitas) {
	        if (cita.getId().equalsIgnoreCase(id)) {
	        	cita.setFecha(nuevaCita.getFecha());
	        	cita.setDoctor(nuevaCita.getDoctor());
	        	cita.setPaciente(nuevaCita.getPaciente());
	            break;
	        }
	    }
	    guardarCitasEnArchivo();
	}
	
	public Cita buscarCitaPorId(String id) {
        for (Cita cita : lasCitas) {
            if (cita.getId().equalsIgnoreCase(id)) {
                return cita;
            }
        }
        return null;
    }
	
	private void guardarCitasEnArchivo() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("citas.dat"))) {
	        oos.writeObject(lasCitas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void agregarVivienda(Vivienda vivienda) {
	    vivienda.setId(String.valueOf(idViviendas++));
	    lasViviendas.add(vivienda);
	    
	    guardarViviendasEnArchivo();
	}
	
	public void eliminarVivienda(String id) {
	    lasViviendas.removeIf(vivienda -> vivienda.getId().equals(id));
	    guardarViviendasEnArchivo();
	}
	
	public void actualizarVivienda(String id, Vivienda nuevaVivienda) {
	    for (Vivienda vivienda : lasViviendas) {
	        if (vivienda.getId().equalsIgnoreCase(id)) {
	        	vivienda.setNombre(nuevaVivienda.getNombre());
	        	vivienda.setLosDoctores(nuevaVivienda.getLosDoctores());
	        	vivienda.setLosPacientes(nuevaVivienda.getLosPacientes());
	            break;
	        }
	    }
	    guardarViviendasEnArchivo();
	}
	
	private void guardarViviendasEnArchivo() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("viviendas.dat"))) {
	        oos.writeObject(lasViviendas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public Vivienda buscarViviendaPorId(String id) {
	    for (Vivienda vivienda : lasViviendas) {
	        if (vivienda.getId().equalsIgnoreCase(id)) {
	            return vivienda;
	        }
	    }
	    return null;
	}
	
	public static void cargarCitasDesdeArchivo() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("citas.dat"))) {
	        ArrayList<Cita> loadedCitas = (ArrayList<Cita>) ois.readObject();
	        if (loadedCitas != null && !loadedCitas.isEmpty()) {
	            clinica.lasCitas = loadedCitas;

	            int maxId = loadedCitas.stream()
	                    .map(cita -> extractId(cita.getId()))
	                    .max(Integer::compare)
	                    .orElse(0);

	            idCitas = maxId + 1;
	        } else {
	            clinica.lasCitas = new ArrayList<>();
	        }
	    } catch (FileNotFoundException fileNotFoundException) {
	        clinica.lasCitas = new ArrayList<>();
	    } catch (EOFException e) {
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void cargarViviendasDesdeArchivo() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("viviendas.dat"))) {
	        ArrayList<Vivienda> loadedVivienda = (ArrayList<Vivienda>) ois.readObject();
	        if (loadedVivienda != null && !loadedVivienda.isEmpty()) {
	            clinica.lasViviendas = loadedVivienda;

	            int maxId = loadedVivienda.stream()
	                    .map(vivienda -> extractId(vivienda.getId()))
	                    .max(Integer::compare)
	                    .orElse(0);
	            
	            idVacunas = maxId + 1;
	        } else {
	            clinica.lasVacunas = new ArrayList<>();
	        }
	    } catch (FileNotFoundException fileNotFoundException) {
	        clinica.lasVacunas = new ArrayList<>();
	    } catch (EOFException e) {
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	private static int extractId(String id) {
	    try {
	        return Integer.parseInt(id);
	    } catch (NumberFormatException e) {
	        return 0;
	    }
	}
	
	private static int extractNumeroLote(String numeroLote) {
	    try {
	        return Integer.parseInt(numeroLote);
	    } catch (NumberFormatException e) {
	        return 0;
	    }
	}
}

