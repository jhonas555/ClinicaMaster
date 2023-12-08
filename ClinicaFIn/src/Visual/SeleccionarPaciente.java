package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class SeleccionarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//FlatMaterialLighterIJTheme.setup();
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			SeleccionarPaciente dialog = new SeleccionarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarPaciente() {
		setTitle("Seleccionar Paciente");
		setBounds(100, 100, 595, 385);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane.setViewportView(scrollPane_1);
				{
					String[] header = {"Id", "Nombre", "Apellido"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.setModel(model);
					scrollPane_1.setViewportView(table);
					
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Seleccionar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		loadDoctores();
	}

	private void loadDoctores() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Paciente paciente : Clinica.getInstance().getPaciente()) {
			row[0] = paciente.getId();
			row[1] = paciente.getNombre();
			row[2] = paciente.getApellido();
			model.addRow(row);
		}		
	}
	
	

}
