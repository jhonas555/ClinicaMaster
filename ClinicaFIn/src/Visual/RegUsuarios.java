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
import logico.Enfermedad;
import logico.User;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class RegUsuarios extends JPanel {
	private JTextField txtUser;
	private JTextField txtPass;
	private User user;
	private JComboBox<String> cmbtipo;
	
	private DefaultTableModel model;
	private Object row[];
	private User Userselec = null;

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
		
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnModificar = new JButton("Modificar");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1032, 13, 860, 870);
		add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] header = {"Usuario", "Tipo"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		scrollPane.setViewportView(table);

		
		JButton btnRegist = new JButton("Registrar");
		btnRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				User usuario = new User(txtUser.getText(), txtPass.getText(), cmbtipo.getSelectedItem().toString());
				Clinica.getInstance().agregarUser(usuario);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				Clean();
				loadUsuarios();
				
			}
		});
		
		
		
		//
		
		
		/*	if (checkCampos()) {
				if (user == null) {
					registrarUsuario();
				} else {
					modificarUsuario();
					JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			*/
		btnRegist.setBounds(900, 685, 120, 32);
		add(btnRegist);
		


	
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User usuario = new User(txtUser.getText(), txtPass.getText(), cmbtipo.getSelectedItem().toString());
				Clinica.getInstance().eliminarUsuario(usuario.getUsuario());
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
				Clean();
				loadUsuarios();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnRegist.setEnabled(true);
			}
		});
		
		
		btnEliminar.setBounds(636, 685, 120, 32);
		add(btnEliminar);
		
		
		
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User usuario = new User(txtUser.getText(), txtPass.getText(), cmbtipo.getSelectedItem().toString());
				Clinica.getInstance().actualizarUsuario(usuario.getUsuario(), usuario);;
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
				Clean();
				loadUsuarios();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnRegist.setEnabled(true);
			}
		});
		
		btnModificar.setBounds(768, 685, 120, 32);
		add(btnModificar);
		
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
		
		cmbtipo = new JComboBox<String>();
		cmbtipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Administrador", "M\u00E9dico"}));
		cmbtipo.setBounds(165, 218, 149, 20);
		add(cmbtipo);
		
		
		/*
		table.addMouseListener(new MouseAdapter() {

			  @Override
			  public void mousePressed(MouseEvent e) {
			    
			    try {
			      int index = table.getSelectedRow();
			      
			      if (index < 0) {
			        return;  
			      }
			      
			      btnRegist.setEnabled(false);
			      btnModificar.setEnabled(true);
			      btnEliminar.setEnabled(true);
			      
			      Object idObject = table.getValueAt(index, 0);
			      String user = idObject.toString();
			      
			      Userselec = Clinica.getInstance().buscarUsuarioPorNombre(user);
			      
			      if (Userselec != null) {
			        txtUser.setText(Userselec.getUsuario());
			        txtPass.setText(Userselec.getPassword());
			        cmbtipo.setSelectedItem(Userselec.getTipo());
			      }
			      
			    } catch (Exception ex) {
			      // Manejar exception aqui
			    }
			  }
			  
			});

		*/
		
		
		
		loadUsuarios();
	}
	
	
//	private void modificarUsuario() {
	//	user.setUsuario(txtUser.getText());
		//user.setPassword(txtPass.getText());
		//user.setTipo(cmbtipo.getSelectedItem().toString());
	//}

	//private void registrarUsuario() {
		//User aux = new User(txtUser.getText(), txtPass.getText(), cmbtipo.getSelectedItem().toString());
		//if (aux.getTipo() != null) {
			//Clinica.getInstance().AgregarUser(aux);
			//JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
			//Clean();
		//}
	//}
	//private boolean checkCampos() {
		//if (txtUser.getText().isEmpty() || txtPass.getText().isEmpty() || cmbtipo.getSelectedIndex() == 0) {
			//JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Error",
				//	JOptionPane.ERROR_MESSAGE);
		//	return false;
		//}
		//return true;
	//}
	
	private void loadUsuarios() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (User usuario : Clinica.getInstance().getlosUsuarios()) {
			row[0] = usuario.getUsuario();
			row[1] = usuario.getTipo();
			model.addRow(row);
		}		
	}

	private void Clean() {
		txtUser.setText("");
		txtPass.setText("");
		cmbtipo.setSelectedIndex(0);
	}
}
