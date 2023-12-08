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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	final static Enfermedad[] enfermedadHolder = {null};

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
			SeleccionarEnfermedad dialog = new SeleccionarEnfermedad(enfermedadHolder);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param doctorholder2 
	 */
	public SeleccionarEnfermedad(Enfermedad[] enfermedadholder2) {
		setTitle("Seleccionar Enfermedad");
		setBounds(100, 100, 595, 385);
		setLocationRelativeTo(null);
		setModal(true);
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
					String[] header = {"Id", "Nombre"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent arg0) {
							int index = table.getSelectedRow();
							Object idObject = table.getValueAt(index, 0);
							String id = String.valueOf(idObject);
							
							enfermedadholder2[0] = Clinica.getInstance().buscarEnfermedadPorId(id);
							
						}
					});
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
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
		
		for (Enfermedad enfermedad : Clinica.getInstance().getLasEnfermedades()) {
			row[0] = enfermedad.getId();
			row[1] = enfermedad.getNombre();
			model.addRow(row);
		}		
	}
	
	

}
