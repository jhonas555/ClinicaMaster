package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegVacunas extends JPanel {
	private JTextField txtNumLote;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtFabricante;
	private JTextField txtEnfermedad;
	
	final static Enfermedad[] enfermedadHolder = {null};
	private Enfermedad enfermedadSelec = null;

	
	private DefaultTableModel model;
	private Object row[];
	/**
	 * Create the panel.
	 */
	public RegVacunas() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vacuna");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 208, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fabricante");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 212, 80, 16);
		add(lblNewLabel_2);
		
		txtNumLote = new JTextField();
		txtNumLote.setBounds(165, 96, 330, 32);
		txtNumLote.setText("V-"+Clinica.getIdVacunas());
		add(txtNumLote);
		txtNumLote.setColumns(10);
		
		String[] header = {"Lote", "Nombre", "Fabricante", "Enfermedad"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1032, 13, 860, 870);
		add(scrollPane);
		
		table = new JTable();
	
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vacuna vacuna = new Vacuna(txtNumLote.getText(), txtNombre.getText(), txtFabricante.getText(), enfermedadSelec);
				Clinica.getInstance().agregarVacuna(vacuna);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
				clean();
				loadVacunas();
			}

			
		});
		btnNewButton.setBounds(900, 573, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(768, 573, 120, 32);
		add(btnModificar);
		btnModificar.setEnabled(false);
		
		JButton btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setBounds(636, 573, 120, 32);
		add(btnEliminar);
		btnEliminar.setEnabled(false);
		
		JLabel lblFecha = new JLabel("Nombre");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		JLabel lblId = new JLabel("N\u00FAmero Lote");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 135, 16);
		add(lblId);
		
		JButton btnVacunaNueva = new JButton("Vacuna Nueva");
		btnVacunaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
				table.clearSelection();
				clean();	
			}
		});
		btnVacunaNueva.setBounds(504, 573, 120, 32);
		add(btnVacunaNueva);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(165, 150, 330, 32);
		add(txtNombre);
		
		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(165, 205, 330, 32);
		add(txtFabricante);
		
		JLabel lblEnfermedades = new JLabel("Enfermedad");
		lblEnfermedades.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnfermedades.setBounds(28, 267, 99, 16);
		add(lblEnfermedades);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setEnabled(false);
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBounds(303, 260, 192, 32);
		add(txtEnfermedad);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeleccionarEnfermedad seleccionarenfermedad = new SeleccionarEnfermedad(enfermedadHolder);
				seleccionarenfermedad.setVisible(true);
				enfermedadSelec = enfermedadHolder[0];
				txtEnfermedad.setText(enfermedadSelec.getNombre());
			}
		});
		btnSeleccionar.setBounds(165, 260, 120, 32);
		add(btnSeleccionar);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = table.getSelectedRow();
				
				if (index >= 0) {
					btnNewButton.setEnabled(false);
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
				
				Object idObject = table.getValueAt(index, 0);
				String id = String.valueOf(idObject);
				
				Vacuna vacuna = Clinica.getInstance().buscarVacunaPorNumeroLote(id);
				if (vacuna != null) {
					txtNumLote.setText(vacuna.getNumeroLote());
					txtNombre.setText(vacuna.getNombre());
					txtFabricante.setText(vacuna.getFabricante());		
					txtEnfermedad.setText(vacuna.getEnfermedad().getNombre());
					enfermedadSelec = vacuna.getEnfermedad();
				}		
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vacuna vacuna = new Vacuna(txtNumLote.getText(), txtNombre.getText(), txtFabricante.getText(), enfermedadSelec);
				Clinica.getInstance().actualizarVacuna(txtNumLote.getText(), vacuna);
				
				clean();
				loadVacunas();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vacuna vacuna = new Vacuna(txtNumLote.getText(), txtNombre.getText(), txtFabricante.getText(), enfermedadSelec);
				Clinica.getInstance().eliminarVacuna(txtNumLote.getText());
				
				clean();
				loadVacunas();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
					
		loadVacunas();
	}
	
	
	private void clean() {
		txtNumLote.setText(""+Clinica.getIdVacunas());		
		txtNombre.setText("");
		txtFabricante.setText("");
		txtEnfermedad.setText("");
		enfermedadSelec = null;
	}
	
	
	private void loadVacunas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Vacuna vacuna : Clinica.getInstance().getLasVacunas()) {
			row[0] = vacuna.getNumeroLote();
			row[1] = vacuna.getNombre();
			row[2] = vacuna.getFabricante();
			row[3] = vacuna.getEnfermedad().getNombre();
			model.addRow(row);
		}		
	}	
}
