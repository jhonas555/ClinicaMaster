package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegEnfermedades extends JPanel {
	private JTextField txtId;
	private JTable table;
	private JTextField txtNombre;
	private JTextPane txtDescripcion;
	
	private DefaultTableModel model;
	private Object row[];
	private Enfermedad enfermedadselec = null;

	/**
	 * Create the panel.
	 */
	public RegEnfermedades() {		
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enfermedad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 212, 80, 16);
		add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(165, 96, 330, 32);
		add(txtId);
		txtId.setColumns(10);
		txtId.setEditable(false);
		
		
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
		
		btnNewButton.setBounds(900, 651, 120, 32);
		add(btnNewButton);
		btnNewButton.setEnabled(true);

		
		JButton btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(768, 651, 120, 32);
		add(btnModificar);
		btnModificar.setEnabled(false);
			
		JButton btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setBounds(636, 651, 120, 32);
		add(btnEliminar);
		btnEliminar.setEnabled(false);

		
		JLabel lblFecha = new JLabel("Nombre");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 135, 16);
		add(lblId);
		
		JButton btnEnfermedadNueva = new JButton("Enfermedad Nueva");
		btnEnfermedadNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
				table.clearSelection();
				clean();				
			}
		});
		btnEnfermedadNueva.setBounds(454, 651, 170, 32);
		add(btnEnfermedadNueva);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);
		txtNombre.setBounds(165, 150, 330, 32);
		add(txtNombre);
		
		txtDescripcion = new JTextPane();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescripcion.setBounds(165, 205, 330, 157);
		add(txtDescripcion);

		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Enfermedad enfermedad = new Enfermedad(txtId.getText(), txtNombre.getText(), txtDescripcion.getText());
				Clinica.getInstance().agregarEnfermedad(enfermedad);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				clean();
				loadEnfermedades();
				
			}	
		});
		
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
				
				enfermedadselec = Clinica.getInstance().buscarEnfermedadPorId(id);
				if (enfermedadselec != null) {
					txtId.setText(String.valueOf(enfermedadselec.getId()));
					txtNombre.setText(enfermedadselec.getNombre());
					txtDescripcion.setText(enfermedadselec.getDescripcion());					
				}			
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enfermedad enfermedad = new Enfermedad(txtId.getText(), txtNombre.getText(), txtDescripcion.getText());
				Clinica.getInstance().actualizarEnfermedad(enfermedad.getId(), enfermedad);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				clean();
				loadEnfermedades();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Enfermedad enfermedad = new Enfermedad(txtId.getText(), txtNombre.getText(), txtDescripcion.getText());
				Clinica.getInstance().eliminarEnfermedad(enfermedad.getId());
				clean();
				loadEnfermedades();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
			}
		});
		
		
		loadEnfermedades();
		txtId.setText(""+Clinica.getIdEnfermedades());
	}
	
	private void clean() {
		txtId.setText(""+Clinica.getIdEnfermedades());
		txtNombre.setText("");
		txtDescripcion.setText("");		
	}
		
	private void loadEnfermedades() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Enfermedad enfermedad : Clinica.getInstance().getLasEnfermedades()) {
			row[0] = enfermedad.getId();
			row[1] = enfermedad.getNombre();
			model.addRow(row);
		}		
	}	
}
