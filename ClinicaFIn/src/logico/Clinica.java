package logico;

import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Clinica implements Serializable{
	
	private static final long serialVersionUID = 5307172532851770925L;

    private ArrayList<Doctor> losDoctores;
    private ArrayList<Vacuna> lasVacunas;
    private ArrayList<Vivienda> lasViviendas;
    private ArrayList<Cita> lasCitas;
    private ArrayList<Enfermedad> lasEnfermedades;
    private ArrayList<Persona> lasPersonas;
    private static int idVacunas = 1;
    private static int idViviendas = 1;
    private static int idCitas = 1;
    private static int idEnfermedades = 1;
    private static int idPersonas = 1;
    private static int idDoctores = 1;
    private static Clinica clinica = null;

    public Clinica() {
        super();
        this.losDoctores = new ArrayList<>();
        this.lasVacunas = new ArrayList<>();
        this.lasViviendas = new ArrayList<>();
        this.lasCitas = new ArrayList<>();
        this.lasEnfermedades = new ArrayList<>();
        this.lasPersonas = new ArrayList<>();
    }

    public static Clinica getInstance() {
        if (clinica == null) {
            clinica = new Clinica();
        }
        cargarEnfermedadesDesdeArchivo();
        cargarVacunasDesdeArchivo();
        cargarCitasDesdeArchivo();
        cargarViviendasDesdeArchivo();
        cargarDoctoresDesdeArchivo();
        return clinica;
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

    private static void guardarDoctoresEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("losDoctores.dat"))) {
            oos.writeObject(clinica.losDoctores);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Doctor buscarDoctorPorId(String id) {
        for (Doctor doctor : losDoctores) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
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
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vacunas.dat"))) {
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
	public ArrayList<Doctor> getDoctores(){
		return losDoctores;
	}
	public void setDoctores(ArrayList<Doctor> losDoctores) {
		this.losDoctores = losDoctores;
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
	        	vacuna.setLasEnfermedades(nuevaVacuna.getLasEnfermedades());
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
	        	vivienda.setLasPersonas(nuevaVivienda.getLasPersonas());
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
}

