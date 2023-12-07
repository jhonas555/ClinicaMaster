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
import logico.Persona;

public class listViviendaPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnListo;
	private JButton btnPasar;
	private JTable tblPersonas;
	private JTable tblVivePersona;
	
	private static ArrayList<Persona> listaPersonas = Clinica.getInstance().getLasPersonas();
	private static ArrayList<Persona> listaVivePersonas = new ArrayList<Persona>();
	private static DefaultTableModel modelPersona;
	private static DefaultTableModel modelVivePersona;
	private static Object[] rowPersona;
	private static Object[] rowVivePersona;
	private Persona PerSeleccionada = null;
	private Persona PersonaSeleccionada = null;

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
			listViviendaPersonas dialog = new listViviendaPersonas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listViviendaPersonas() {
		setResizable(false);
		setLocationRelativeTo(null); 
		setBounds(650, 200, 713, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelListado = new JPanel();
			panelListado.setBorder(new TitledBorder(null, "Administrar Personas en la Vivienda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelListado.setBounds(10, 11, 675, 240);
			contentPanel.add(panelListado);
			panelListado.setLayout(null);
			{
				JPanel panelPersonas = new JPanel();
				panelPersonas.setBorder(new TitledBorder(null, "Lista de Personas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelPersonas.setBounds(10, 23, 298, 176);
				panelListado.add(panelPersonas);
				panelPersonas.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panelPersonas.add(scrollPane, BorderLayout.CENTER);
					
					String[] headerPersonas = {"Personas"};
					modelPersona = new DefaultTableModel();
					modelPersona.setColumnIdentifiers(headerPersonas);
					tblPersonas = new JTable();
					tblPersonas.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int index = tblPersonas.getSelectedRow();
							if(index>=0)
							{
								PerSeleccionada = listaPersonas.get(index);
								PersonaSeleccionada = null;
								btnPasar.setText(">>");
								btnPasar.setEnabled(true);
							}
						}
					});
					tblPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tblPersonas.setModel(modelPersona);
					scrollPane.setViewportView(tblPersonas);
				}
			}
			{
				JPanel panelVivePersonas = new JPanel();
				panelVivePersonas.setBorder(new TitledBorder(null, "Pertenecen a esta vivienda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelVivePersonas.setBounds(367, 23, 298, 176);
				panelListado.add(panelVivePersonas);
				panelVivePersonas.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelVivePersonas.add(scrollPane, BorderLayout.CENTER);
				
				tblVivePersona = new JTable();
				String[] headerVivePersona = {"Personas"};
				modelVivePersona = new DefaultTableModel();
				modelVivePersona.setColumnIdentifiers(headerVivePersona);
				tblVivePersona.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int index = tblVivePersona.getSelectedRow();
						if(index>=0)
						{
							PersonaSeleccionada = listaVivePersonas.get(index);
							PerSeleccionada = null;
							btnPasar.setText("<<");
							btnPasar.setEnabled(true);
						}
					}
				});
				tblVivePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblVivePersona.setModel(modelVivePersona);
				scrollPane.setViewportView(tblVivePersona);
			}
			{
				btnPasar = new JButton(">>");
				btnPasar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnPasar.setEnabled(false);
						btnPasar.setText("");
						if(PerSeleccionada!= null)
						{
							listaVivePersonas.add(PerSeleccionada);
							listaPersonas.remove(PerSeleccionada);
						}
						imprimirVivePersona();
						imprimirPersonas();
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
					if(PerSeleccionada != null) {
						listaPersonas.add(PersonaSeleccionada);
						listaVivePersonas.remove(PersonaSeleccionada);
					}
					imprimirVivePersona();
					imprimirPersonas();
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
		imprimirVivePersona();
		imprimirPersonas();
	}


	private void habilitarBotonListo() {
		if(tblVivePersona.getRowCount() > 0)
		{
			btnListo.setEnabled(true);
			return;
		}
		btnListo.setEnabled(false);
	}
	
	public static void imprimirPersonas()
	{
		modelPersona.setRowCount(0);
		rowPersona = new Object[modelPersona.getColumnCount()];
		for (Persona Persona : listaPersonas) {
			rowPersona[0] = Persona.getClass().getSimpleName();
			modelPersona.addRow(rowPersona);
		}
	}
	
	public static void imprimirVivePersona()
	{
		modelVivePersona.setRowCount(0);
		rowVivePersona = new Object[modelVivePersona.getColumnCount()];
		for (Persona Persona : listaVivePersonas) {
			rowVivePersona[0] = Persona.getClass().getSimpleName();
			modelVivePersona.addRow(rowVivePersona);
		}
	}
}