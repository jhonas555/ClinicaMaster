package Visual;

import javax.swing.JPanel;


import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import logico.Persona;
import logico.Vivienda;

public class RegViviendas extends JPanel {
	private JTextField txtId;
	private JTable table;
	private JTextField txtNombre;
	private DefaultTableModel model;
	private Object row[];
	private Vivienda viviendaselec = null;
	
	private ArrayList<Doctor> listaDoctor = new ArrayList<Doctor>();
	private ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();

	/**
	 * Create the panel.
	 */
	public RegViviendas() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vivienda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(165, 95, 330, 32);
		txtId.setText("V-"+Clinica.getIdViviendas());
		add(txtId);
		txtId.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1032, 13, 860, 870);
		add(scrollPane);

		String[] header = {"Id", "Nombre"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vivienda vivienda = new Vivienda(txtId.getText(), txtNombre.getText(), listaDoctor, listaPaciente);
				System.out.println("Lista de Doctores:");
		        for (Doctor doctor : listaDoctor) {
		            System.out.println(doctor.getId().toString());
		        }

		        // Print the content of listaPaciente
		        System.out.println("Lista de Pacientes:");
		        for (Paciente paciente : listaPaciente) {
		            System.out.println(paciente.getId().toString());
		        }
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				
				Clinica.getInstance().agregarVivienda(vivienda);
				loadViviendas();
				clean();
			}
		});
		btnNewButton.setBounds(900, 512, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Guardar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnModificar.setBounds(768, 512, 120, 32);
		add(btnModificar);
		btnModificar.setEnabled(false);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 512, 120, 32);
		add(btnEliminar);
		btnEliminar.setEnabled(false);
		
		JLabel lblFecha = new JLabel("Nombre");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(165, 150, 330, 32);
		add(txtNombre);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 56, 16);
		add(lblId);
		
		JButton btnViviendaNueva = new JButton("Vivienda Nueva");
		btnViviendaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnViviendaNueva.setBounds(451, 512, 173, 32);
		add(btnViviendaNueva);
		
		JLabel lblPeronas = new JLabel("Personas");
		lblPeronas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeronas.setBounds(28, 212, 79, 16);
		add(lblPeronas);
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listViviendaPersonas ventanaViviendaPersonas = new listViviendaPersonas(txtId.getText(), txtNombre.getText(), listaDoctor, listaPaciente);
				ventanaViviendaPersonas.setVisible(true);
			}

			
		});
		btnAdministrar.setBounds(165, 205, 120, 32);
		add(btnAdministrar);
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = table.getSelectedRow();
				
				if (index >= 0) {
					btnNewButton.setEnabled(false);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
				
				Object idObject = table.getValueAt(index, 0);
				String id = String.valueOf(idObject);
				
				viviendaselec = Clinica.getInstance().buscarViviendaPorId(id);
				if (viviendaselec != null) {
					txtId.setText(String.valueOf(viviendaselec.getId()));
					txtNombre.setText(viviendaselec.getNombre());
					listaDoctor.addAll(viviendaselec.getLosDoctores());
					
					listaPaciente.addAll(viviendaselec.getLosPacientes());
					
				}			
			}
		});
		
		
		loadViviendas();
	}


	private void clean() {
		txtId.setText("");
		txtNombre.setText("");
		listaDoctor.clear();
		listaPaciente.clear();
		
	}
	
	private void loadViviendas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Vivienda vivienda : Clinica.getInstance().getLasViviendas()) {
			row[0] = vivienda.getId();
			row[1] = vivienda.getNombre();
			model.addRow(row);
		}		
	}

	
	
	
}
