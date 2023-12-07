package logico;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente extends Persona {

	private static final long serialVersionUID = -5408621671738297914L;
	private String numeroSeguro;
	private boolean Sano;
	private Historial_Clinica historial;
	private ArrayList<Vacuna> lasVacunas;
	
	public Paciente(String id, String password, String cedula, String nombre, String apellido,
			String telefono, String correoElectronico) {
		super(id, password, cedula, nombre, apellido, telefono, correoElectronico);
	}
	
	public String getNumeroSeguro() {
		return numeroSeguro;
	}
	public void setNumeroSeguro(String numeroSeguro) {
		this.numeroSeguro = numeroSeguro;
	}
	public boolean isSano() {
		return Sano;
	}
	public void setSano(boolean sano) {
		Sano = sano;
	}
	public Historial_Clinica getHistorial() {
		return historial;
	}
	public void setHistorial(Historial_Clinica historial) {
		this.historial = historial;
	}
	public ArrayList<Vacuna> getLasVacunas() {
		return lasVacunas;
	}
	public void setLasVacunas(ArrayList<Vacuna> lasVacunas) {
		this.lasVacunas = lasVacunas;
	}
	
    public void guardarDatos() throws IOException {
        File archivo = new File("pacientes.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo, true));
        oos.writeObject(this);
        oos.close();
    }

    public static Paciente[] leerDatos() throws IOException, ClassNotFoundException {
        File archivo = new File("pacientes.dat");
        if (!archivo.exists()) {
            return new Paciente[0];
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
        Object obj;
        java.util.List<Paciente> pacientes = new java.util.ArrayList<>();
        try {
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Paciente) {
                    pacientes.add((Paciente) obj);
                }
            }
        } catch (EOFException e) {

        } finally {
            ois.close();
        }

        return pacientes.toArray(new Paciente[0]);
    }
    
    public static Paciente buscarPorId(String id) throws IOException, ClassNotFoundException {
        Paciente[] pacientes = leerDatos();
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    public static void eliminarPorId(String id) throws IOException, ClassNotFoundException {
        Paciente[] pacientes = leerDatos();
        List<Paciente> listaPacientes = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            if (!paciente.getId().equals(id)) {
                listaPacientes.add(paciente);
            }
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pacientes.dat"));
        for (Paciente paciente : listaPacientes) {
            oos.writeObject(paciente);
        }
        oos.close();
    }

    public static void actualizarPaciente(String id, Paciente nuevoPaciente) throws IOException, ClassNotFoundException {
        Paciente[] pacientes = leerDatos();
        List<Paciente> listaPacientes = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                listaPacientes.add(nuevoPaciente);
            } else {
                listaPacientes.add(paciente);
            }
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pacientes.dat"));
        for (Paciente paciente : listaPacientes) {
            oos.writeObject(paciente);
        }
        oos.close();
    }

}
