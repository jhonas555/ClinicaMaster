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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Enfermedad;

public class RegConsultas extends JPanel {
	private JTextField txtId;
	private JTable table;
	private JTextField textDiagnos;
	private JTextField textFecha;
	private JTextPane textMotivo;
	
	private DefaultTableModel model;
	private Object row[];

	/**
	 * Create the panel.
	 */
	public RegConsultas() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consultas del Paciente: NOMBRE DEL PACIENTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 26, 494, 23);
		add(lblNewLabel);
		this.setPreferredSize(new Dimension(1904, 896));
		
		JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
		lblNewLabel_1.setBounds(28, 47, 494, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Diagnostico");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 212, 109, 16);
		add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(165, 96, 330, 32);
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
				ArrayList<Enfermedad> enf = new ArrayList<>();
            	Consulta consultas = new Consulta(
            			txtId.getText(), 
            			textFecha.getText(),
            			textDiagnos.getText(),
            			textMotivo.getText(),
            			enf
            			);
            	Clinica.getInstance().agregarConsulta(consultas);
            	
                
                JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
                clean();
                loadConsulta();
                
			}
		});
		btnNewButton.setBounds(900, 559, 120, 32);
		add(btnNewButton);
		
		JButton btnModificar = new JButton("Guardar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(768, 559, 120, 32);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(636, 559, 120, 32);
		add(btnEliminar);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(28, 157, 56, 16);
		add(lblFecha);
		
		textDiagnos = new JTextField();
		textDiagnos.setColumns(10);
		textDiagnos.setBounds(165, 205, 330, 32);
		add(textDiagnos);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(28, 103, 135, 16);
		add(lblId);
		
		JLabel lblPaciente = new JLabel("Enfermedad");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaciente.setBounds(28, 267, 109, 16);
		add(lblPaciente);
		
		JButton button_1 = new JButton("Cita Nueva");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(504, 559, 120, 32);
		add(button_1);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(165, 150, 330, 32);
		add(textFecha);
		
		JLabel lblFechaNacimiento = new JLabel("Motivo");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaNacimiento.setBounds(520, 103, 126, 16);
		add(lblFechaNacimiento);
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.setBounds(165, 260, 120, 32);
		add(btnAdministrar);
		
		JTextPane textMotivo = new JTextPane();
		textMotivo.setBounds(658, 96, 330, 196);
		add(textMotivo);
	}
	
	private void clean() {
		txtId.setText(""+Clinica.getIdEnfermedades());
		//txtNombre.setText("");
		//txtDescripcion.setText("");		
	}
		
	private void loadConsulta() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Consulta consulta : Clinica.getInstance().getConsultas()) {
			//row[0] = enfermedad.getId();
			//row[1] = enfermedad.getNombre();
			model.addRow(row);
		}		
	}
}
