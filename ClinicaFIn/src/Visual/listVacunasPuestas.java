package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import logico.Clinica;
import logico.Vacuna;

public class listVacunasPuestas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnListo;
	private JButton btnPasar;
	private JTable tblVacunas;
	private JTable tblVacPuesta;
	
	private static ArrayList<Vacuna> listaVacunas = Clinica.getInstance().getLasVacunas();
	private static ArrayList<Vacuna> listaVacPuestas = new ArrayList<Vacuna>();
	private static DefaultTableModel modelVacuna;
	private static DefaultTableModel modelVacPuesta;
	private static Object[] rowVacuna;
	private static Object[] rowVacPuesta;
	private Vacuna vacSeleccionada = null;
	private Vacuna tratadaSeleccionada = null;

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
			listVacunasPuestas dialog = new listVacunasPuestas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listVacunasPuestas() {
		setResizable(false);
		setLocationRelativeTo(null); 
		setBounds(650, 200, 713, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelListado = new JPanel();
			panelListado.setBorder(new TitledBorder(null, "Administrar Vacunas Puestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelListado.setBounds(10, 11, 675, 240);
			contentPanel.add(panelListado);
			panelListado.setLayout(null);
			{
				JPanel panelVacunas = new JPanel();
				panelVacunas.setBorder(new TitledBorder(null, "Lista de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelVacunas.setBounds(10, 23, 298, 176);
				panelListado.add(panelVacunas);
				panelVacunas.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panelVacunas.add(scrollPane, BorderLayout.CENTER);
					
					String[] headerVacunas = {"Vacunas"};
					modelVacuna = new DefaultTableModel();
					modelVacuna.setColumnIdentifiers(headerVacunas);
					tblVacunas = new JTable();
					tblVacunas.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int index = tblVacunas.getSelectedRow();
							if(index>=0)
							{
								vacSeleccionada = listaVacunas.get(index);
								tratadaSeleccionada = null;
								btnPasar.setText(">>");
								btnPasar.setEnabled(true);
							}
						}
					});
					tblVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tblVacunas.setModel(modelVacuna);
					scrollPane.setViewportView(tblVacunas);
				}
			}
			{
				JPanel panelVacPuestas = new JPanel();
				panelVacPuestas.setBorder(new TitledBorder(null, "Vacunas Puestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelVacPuestas.setBounds(367, 23, 298, 176);
				panelListado.add(panelVacPuestas);
				panelVacPuestas.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelVacPuestas.add(scrollPane, BorderLayout.CENTER);
				
				tblVacPuesta = new JTable();
				String[] headerVacPuesta = {"Vacunas"};
				modelVacPuesta = new DefaultTableModel();
				modelVacPuesta.setColumnIdentifiers(headerVacPuesta);
				tblVacPuesta.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int index = tblVacPuesta.getSelectedRow();
						if(index>=0)
						{
							tratadaSeleccionada = listaVacPuestas.get(index);
							vacSeleccionada = null;
							btnPasar.setText("<<");
							btnPasar.setEnabled(true);
						}
					}
				});
				tblVacPuesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblVacPuesta.setModel(modelVacPuesta);
				scrollPane.setViewportView(tblVacPuesta);
			}
			{
				btnPasar = new JButton(">>");
				btnPasar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnPasar.setEnabled(false);
						btnPasar.setText("");
						if(vacSeleccionada!= null)
						{
							listaVacPuestas.add(vacSeleccionada);
							listaVacunas.remove(vacSeleccionada);
						}
						imprimirVacPuesta();
						imprimirVacunas();
						habilitarBotonListo();
					}
				});
				btnPasar.setEnabled(false);
				btnPasar.setBounds(313, 69, 49, 23);
				panelListado.add(btnPasar);
			}
			
			JButton btnDevolver = new JButton("<<");
			btnPasar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnPasar.setEnabled(false);
					btnPasar.setText("");
					if(vacSeleccionada != null) {
						listaVacunas.add(tratadaSeleccionada);
						listaVacPuestas.remove(tratadaSeleccionada);
					}
					imprimirVacPuesta();
					imprimirVacunas();
					habilitarBotonListo();
				}
			});
			btnDevolver.setEnabled(false);
			btnDevolver.setBounds(313, 143, 49, 23);
			panelListado.add(btnDevolver);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnListo = new JButton("Listo");
				btnListo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnListo.setEnabled(false);
				btnListo.setActionCommand("OK");
				buttonPane.add(btnListo);
				getRootPane().setDefaultButton(btnListo);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		imprimirVacPuesta();
		imprimirVacunas();
	}


	private void habilitarBotonListo() {
		if(tblVacPuesta.getRowCount() > 0)
		{
			btnListo.setEnabled(true);
			return;
		}
		btnListo.setEnabled(false);
	}
	
	public static void imprimirVacunas()
	{
		modelVacuna.setRowCount(0);
		rowVacuna = new Object[modelVacuna.getColumnCount()];
		for (Vacuna Vacuna : listaVacunas) {
			rowVacuna[0] = Vacuna.getClass().getSimpleName();
			modelVacuna.addRow(rowVacuna);
		}
	}
	
	public static void imprimirVacPuesta()
	{
		modelVacPuesta.setRowCount(0);
		rowVacPuesta = new Object[modelVacPuesta.getColumnCount()];
		for (Vacuna Vacuna : listaVacPuestas) {
			rowVacPuesta[0] = Vacuna.getClass().getSimpleName();
			modelVacPuesta.addRow(rowVacPuesta);
		}
	}
}