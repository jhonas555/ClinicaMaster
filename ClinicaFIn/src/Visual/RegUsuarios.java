package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import logico.Clinica;
import logico.User;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegUsuarios extends JPanel {
	private JTable table;
	private JTextField txtUser;
	private JTextField txtPass;
	private JComboBox<String> cmbtipo;
	private User user;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public RegUsuarios() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1032, 13, 860, 870);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkCampos()) {
					if (user == null) {
						registrarUsuario();
					} else {
						modificarUsuario();
						JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(900, 685, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(768, 685, 120, 32);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 685, 120, 32);
		add(btnEliminar);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(28, 98, 135, 16);
		add(lblUsuario);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(165, 92, 330, 32);
		add(txtUser);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(28, 159, 88, 16);
		add(lblContrasea);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(165, 152, 330, 32);
		add(txtPass);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(28, 218, 88, 16);
		add(lblTipo);
		
		comboBox = new JComboBox();
		comboBox.setBounds(165, 218, 149, 20);
		add(comboBox);
	}
	private void modificarUsuario() {
		user.setUsuario(txtUser.getText());
		user.setPassword(txtPass.getText());
		user.setTipo(cmbtipo.getSelectedItem().toString());
	}

	private void registrarUsuario() {
		User aux = new User(txtUser.getText(), txtPass.getText(), cmbtipo.getSelectedItem().toString());
		if (aux.getTipo() != null) {
			Clinica.getInstance().AgregarUser(aux);
			JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
			Clean();
		}
	}
	private boolean checkCampos() {
		if (txtUser.getText().isEmpty() || txtPass.getText().isEmpty() || cmbtipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void Clean() {
		txtUser.setText("");
		txtPass.setText("");
		cmbtipo.setSelectedIndex(0);
	}
}
