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

public class RegAdministradores extends JPanel {
	private JTextField idCita;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public RegAdministradores() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrador");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 212, 56, 16);
		add(lblNewLabel_2);
		
		idCita = new JTextField();
		idCita.setBounds(165, 96, 330, 32);
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
		btnNewButton.setBounds(900, 685, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Guardar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(768, 685, 120, 32);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 685, 120, 32);
		add(btnEliminar);
		
		JLabel lblFecha = new JLabel("Nombre");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(165, 205, 330, 32);
		add(textField);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 135, 16);
		add(lblId);
		
		JButton button_1 = new JButton("Cita Nueva");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(504, 685, 120, 32);
		add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(165, 150, 330, 32);
		add(textField_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(165, 260, 330, 32);
		add(textField_4);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelfono.setBounds(28, 268, 126, 16);
		add(lblTelfono);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
		lblCorreoElectrnico.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCorreoElectrnico.setBounds(28, 322, 147, 16);
		add(lblCorreoElectrnico);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(165, 315, 330, 32);
		add(textField_5);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(520, 103, 135, 16);
		add(lblUsuario);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(657, 96, 330, 32);
		add(textField_6);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(520, 157, 88, 16);
		add(lblContrasea);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(657, 150, 330, 32);
		add(textField_7);
	}
}
