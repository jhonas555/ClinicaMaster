package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegCitas extends JPanel {
	private JTextField idCita;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		
		idCita = new JTextField();
		idCita.setBounds(165, 95, 330, 32);
		add(idCita);
		idCita.setColumns(10);
		
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
		btnNewButton.setBounds(900, 525, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Guardar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(768, 525, 120, 32);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 525, 120, 32);
		add(btnEliminar);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(165, 150, 330, 32);
		add(textField);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 56, 16);
		add(lblId);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(165, 205, 120, 32);
		add(btnSeleccionar);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaciente.setBounds(28, 267, 80, 16);
		add(lblPaciente);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 205, 195, 32);
		add(textField_1);
		
		JButton button = new JButton("Seleccionar");
		button.setBounds(165, 260, 120, 32);
		add(button);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(300, 260, 195, 32);
		add(textField_2);
		
		JButton button_1 = new JButton("Cita Nueva");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(504, 525, 120, 32);
		add(button_1);
	}
}
