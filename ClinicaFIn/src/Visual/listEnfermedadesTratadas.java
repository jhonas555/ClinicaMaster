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
import logico.Enfermedad;

public class listEnfermedadesTratadas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnListo;
	private JButton btnPasar;
	private JTable tblEnfermedades;
	private JTable tblenfTratada;
	
	private static ArrayList<Enfermedad> listaEnfermedades = Clinica.getInstance().getLasEnfermedades();
	private static ArrayList<Enfermedad> listaEnfTratadas = new ArrayList<Enfermedad>();
	private static DefaultTableModel modelEnfermedad;
	private static DefaultTableModel modelenfTratada;
	private static Object[] rowEnfermedad;
	private static Object[] rowenfTratada;
	private Enfermedad enfSeleccionada = null;
	private Enfermedad tratadaSeleccionada = null;

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
			listEnfermedadesTratadas dialog = new listEnfermedadesTratadas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listEnfermedadesTratadas() {
		setResizable(false);
		setLocationRelativeTo(null); 
		setBounds(650, 200, 713, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelListado = new JPanel();
			panelListado.setBorder(new TitledBorder(null, "Administrar Enfermedades Tratadas Por Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelListado.setBounds(10, 11, 675, 240);
			contentPanel.add(panelListado);
			panelListado.setLayout(null);
			{
				JPanel panelEnfermedades = new JPanel();
				panelEnfermedades.setBorder(new TitledBorder(null, "Lista de Enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelEnfermedades.setBounds(10, 23, 298, 176);
				panelListado.add(panelEnfermedades);
				panelEnfermedades.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panelEnfermedades.add(scrollPane, BorderLayout.CENTER);
					
					String[] headerEnfermedades = {"Enfermedades"};
					modelEnfermedad = new DefaultTableModel();
					modelEnfermedad.setColumnIdentifiers(headerEnfermedades);
					tblEnfermedades = new JTable();
					tblEnfermedades.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int index = tblEnfermedades.getSelectedRow();
							if(index>=0)
							{
								enfSeleccionada = listaEnfermedades.get(index);
								tratadaSeleccionada = null;
								btnPasar.setText(">>");
								btnPasar.setEnabled(true);
							}
						}
					});
					tblEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tblEnfermedades.setModel(modelEnfermedad);
					scrollPane.setViewportView(tblEnfermedades);
				}
			}
			{
				JPanel panelEnfTratadas = new JPanel();
				panelEnfTratadas.setBorder(new TitledBorder(null, "Enfermedades Tratadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelEnfTratadas.setBounds(367, 23, 298, 176);
				panelListado.add(panelEnfTratadas);
				panelEnfTratadas.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelEnfTratadas.add(scrollPane, BorderLayout.CENTER);
				
				tblenfTratada = new JTable();
				String[] headerenfTratada = {"Enfermedades"};
				modelenfTratada = new DefaultTableModel();
				modelenfTratada.setColumnIdentifiers(headerenfTratada);
				tblenfTratada.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int index = tblenfTratada.getSelectedRow();
						if(index>=0)
						{
							tratadaSeleccionada = listaEnfTratadas.get(index);
							enfSeleccionada = null;
							btnPasar.setText("<<");
							btnPasar.setEnabled(true);
						}
					}
				});
				tblenfTratada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblenfTratada.setModel(modelenfTratada);
				scrollPane.setViewportView(tblenfTratada);
			}
			{
				btnPasar = new JButton(">>");
				btnPasar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnPasar.setEnabled(false);
						btnPasar.setText("");
						if(enfSeleccionada!= null)
						{
							listaEnfTratadas.add(enfSeleccionada);
							listaEnfermedades.remove(enfSeleccionada);
						}
						imprimirenfTratada();
						imprimirEnfermedades();
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
					if(enfSeleccionada != null) {
						listaEnfermedades.add(tratadaSeleccionada);
						listaEnfTratadas.remove(tratadaSeleccionada);
					}
					imprimirenfTratada();
					imprimirEnfermedades();
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
		imprimirenfTratada();
		imprimirEnfermedades();
	}


	private void habilitarBotonListo() {
		if(tblenfTratada.getRowCount() > 0)
		{
			btnListo.setEnabled(true);
			return;
		}
		btnListo.setEnabled(false);
	}
	
	public static void imprimirEnfermedades()
	{
		modelEnfermedad.setRowCount(0);
		rowEnfermedad = new Object[modelEnfermedad.getColumnCount()];
		for (Enfermedad Enfermedad : listaEnfermedades) {
			rowEnfermedad[0] = Enfermedad.getClass().getSimpleName();
			modelEnfermedad.addRow(rowEnfermedad);
		}
	}
	
	public static void imprimirenfTratada()
	{
		modelenfTratada.setRowCount(0);
		rowenfTratada = new Object[modelenfTratada.getColumnCount()];
		for (Enfermedad Enfermedad : listaEnfTratadas) {
			rowenfTratada[0] = Enfermedad.getClass().getSimpleName();
			modelenfTratada.addRow(rowenfTratada);
		}
	}
}