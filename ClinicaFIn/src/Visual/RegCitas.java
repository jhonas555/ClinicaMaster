package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class RegCitas extends JPanel {
	private JTextField txtId;
	private JTable table;
	private JTextField txtFecha;
	private JTextField txtDoctor;
	private JTextField txtPaciente;
	final static Doctor[] doctorHolder = {null};
	final static Paciente[] pacienteHolder = {null};
	private Doctor doctorSelec = null;
	private Paciente pacienteSelec = null;

	/**
	 * Create the panel.
	 */
	public RegCitas() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cita");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doctor");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 212, 56, 16);
		add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(165, 95, 330, 32);
		txtId.setText("C-"+Clinica.getIdCitas());
		add(txtId);
		txtId.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1032, 13, 860, 870);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date fecha = null;
				try {
					fecha = validateAndConvertToDate(txtFecha.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					fecha = null;
				}
				if (fecha != null) {
					Cita cita = new Cita(txtId.getText(), fecha ,doctorSelec, pacienteSelec);
					Clinica.getInstance().agregarCita(cita);
					clean();
				} else {
					JOptionPane.showMessageDialog(null, "La fecha no es valida", "Fehca", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				//Clinica.getInstance().agregarEnfermedad(enfermedad);
				//JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				//clean();
				//loadEnfermedades();
				
			}

			
		});
		btnNewButton.setBounds(900, 525, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(768, 525, 120, 32);
		add(btnModificar);
		btnModificar.setEnabled(false);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 525, 120, 32);
		add(btnEliminar);
		btnEliminar.setEnabled(false);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(165, 150, 330, 32);
		add(txtFecha);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 56, 16);
		add(lblId);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarDoctor seleccionardoctor = new SeleccionarDoctor(doctorHolder);
				seleccionardoctor.setVisible(true);
				doctorSelec = doctorHolder[0];
				txtDoctor.setText(doctorSelec.getNombre());
				
			}
		});
		btnSeleccionar.setBounds(165, 205, 120, 32);
		add(btnSeleccionar);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaciente.setBounds(28, 267, 80, 16);
		add(lblPaciente);
		
		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setEnabled(false);
		txtDoctor.setColumns(10);
		txtDoctor.setBounds(300, 205, 195, 32);
		add(txtDoctor);
		
		JButton button = new JButton("Seleccionar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarPaciente seleccionarpaciente = new SeleccionarPaciente(pacienteHolder);
				seleccionarpaciente.setVisible(true);
				pacienteSelec = pacienteHolder[0];
				txtPaciente.setText(pacienteSelec.getNombre());
			}
		});
		button.setBounds(165, 260, 120, 32);
		add(button);
		
		txtPaciente = new JTextField();
		txtPaciente.setEnabled(false);
		txtPaciente.setEditable(false);
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(300, 260, 195, 32);
		add(txtPaciente);
		
		JButton button_1 = new JButton("Cita Nueva");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(504, 525, 120, 32);
		add(button_1);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				if (index >= 0) {
					btnNewButton.setEnabled(false);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
				
				Object idObject = table.getValueAt(index, 0);
				String id = String.valueOf(idObject);
				
				Cita citaselec = Clinica.getInstance().buscarCitaPorId(id);
				if (citaselec != null) {
					txtId.setText(String.valueOf(citaselec.getId()));
					txtFecha.setText(citaselec.getFecha().toString());
					txtDoctor.setText(citaselec.getDoctor().getNombre());		
					txtPaciente.setText(citaselec.getPaciente().getNombre());	
				}			
				
			}
		});
		
		
	}
	
	public static Date validateAndConvertToDate(String inputDate) throws ParseException {
        // Define el formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Para asegurar una validación estricta de la fecha

        // Define el patrón regex para dd/mm/yyyy
        String datePattern = "\\b(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(\\d{4})\\b";
        if (!inputDate.matches(datePattern)) {
            throw new ParseException("Formato de fecha inválido", 0);
        }

        // Intenta analizar la fecha con SimpleDateFormat
        return dateFormat.parse(inputDate);
    }
	
	
	private void clean() {
		txtId.setText(""+Clinica.getIdCitas());
		txtFecha.setText("");
		txtDoctor.setText("");
		txtPaciente.setText(null);
		doctorSelec = null;
		
		pacienteSelec = null;
		
		
	}
}
