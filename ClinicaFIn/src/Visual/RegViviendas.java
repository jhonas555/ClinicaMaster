package Visual;

import javax.swing.JPanel;


import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import logico.Persona;

public class RegViviendas extends JPanel {
	private JTextField txtId;
	private JTable table;
	private JTextField txtNombre;
	
	private static ArrayList<Doctor> listaDoctor = Clinica.getInstance().getDoctores();
	private static ArrayList<Paciente> listaPaciente = Clinica.getInstance().getPaciente();

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
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 512, 120, 32);
		add(btnEliminar);
		
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
		
		JButton button = new JButton("Cita Nueva");
		button.setBounds(504, 512, 120, 32);
		add(button);
		
		JLabel lblPeronas = new JLabel("Personas");
		lblPeronas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeronas.setBounds(28, 212, 79, 16);
		add(lblPeronas);
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Persona> personas = new ArrayList<Persona>();
				personas = Clinica.getInstance().getLasPersonas();
				
				
				listViviendaPersonas ventanaViviendaPersonas = new listViviendaPersonas(txtId.getText(), txtNombre.getText());
				ventanaViviendaPersonas.setVisible(true);
			}
		});
		btnAdministrar.setBounds(165, 205, 120, 32);
		add(btnAdministrar);
	}
}
