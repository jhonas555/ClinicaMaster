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

public class Doctor extends Persona {

	private static final long serialVersionUID = -4029156010399063867L;
	
	private String numeroLicenciaMedica;
	private String Especialidad;

	public Doctor(String id, String password, String cedula, String nombre, String apellido,
			String telefono, String correoElectronico) {
		super(id, password, cedula, nombre, apellido, telefono, correoElectronico);
	}	
	


	public String getNumeroLicenciaMedica() {
		return numeroLicenciaMedica;
	}
	public void setNumeroLicenciaMedica(String numeroLicenciaMedica) {
		this.numeroLicenciaMedica = numeroLicenciaMedica;
	}
	public String getEspecialidad() {
		return Especialidad;
	}
	public void setEspecialidad(String especialidad) {
		Especialidad = especialidad;
	}
	
    public void guardarDatos() throws IOException {
        File archivo = new File("doctores.dat");
        FileOutputStream fos = new FileOutputStream(archivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }
    
    public static Doctor leerDatos() throws IOException, ClassNotFoundException {
        File archivo = new File("doctores.dat"); 
        FileInputStream fis = new FileInputStream(archivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Doctor doctor = (Doctor) ois.readObject(); 
        ois.close();
        return doctor;
    }
    
    public void actualizarDoctor(String id, Doctor nuevoDoctor) {
        ArrayList<Doctor> doctores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("doctores.dat"))) {
            Doctor doctor;
            while ((doctor = (Doctor) ois.readObject()) != null) {
                if (doctor.getId().equals(id)) {
                    doctores.add(nuevoDoctor);
                } else {
                    doctores.add(doctor);
                }
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doctores.dat"))) {
            for (Doctor doctor : doctores) {
                oos.writeObject(doctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDoctor(String id) {
        ArrayList<Doctor> doctores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("doctores.dat"))) {
            Doctor doctor;
            while ((doctor = (Doctor) ois.readObject()) != null) {
                if (!doctor.getId().equals(id)) {
                    doctores.add(doctor);
                }
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doctores.dat"))) {
            for (Doctor doctor : doctores) {
                oos.writeObject(doctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
