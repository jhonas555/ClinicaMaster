package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;
import logico.Persona;

public class listViviendaPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnListo;
	private JButton btnPasar;
	private JButton btnDevolver;
	private JTable tblPersonas;
	private JTable tblVivePersona;
	private static String IdVivienda;
	private static String NombreVivienda;
	private static ArrayList<Doctor> listaDoctor = Clinica.getInstance().getDoctores();
	private static ArrayList<Paciente> listaPaciente = Clinica.getInstance().getPaciente();
	private static ArrayList<Doctor> listaViveDoctor = new ArrayList<Doctor>();
	private static ArrayList<Paciente> listaVivePaciente = new ArrayList<Paciente>();
	private static DefaultTableModel modelPersona;
	private static DefaultTableModel modelVivePersona;
	private static Object[] rowPersona;
	private static Object[] rowVivePersona;
	private Object row[];
	private DefaultTableModel model;
	private Doctor DoctorSeleccionado = null;
	private Paciente PacienteSeleccionado = null;
	private Persona PerSeleccionada = null;
	private Persona PersonaSeleccionada = null;
	private static String tipodePersona = null;

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
			listViviendaPersonas dialog = new listViviendaPersonas(IdVivienda, NombreVivienda, listaDoctor, listaPaciente);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param personas 
	 * @param string 
	 * @param string2 
	 * @param listaPaciente2 
	 * @param listaDoctor2 
	 */
	public listViviendaPersonas(String string, String string2, ArrayList<Doctor> listaDoctorReferencia, ArrayList<Paciente> listaPacienteReferencia) {
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
					
					String[] headerPersonas = {"Id", "Nombre", "Tipo"};
					modelPersona = new DefaultTableModel();
					modelPersona.setColumnIdentifiers(headerPersonas);
					tblPersonas = new JTable();
					
					tblPersonas.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							int index = tblPersonas.getSelectedRow();
							Object idObject = tblPersonas.getValueAt(index, 0);
							String id = String.valueOf(idObject);
							Object tipoObject = tblPersonas.getValueAt(index, 2);
							String tipo = String.valueOf(tipoObject);
							
							if(index>=0)
							{
								if(tipo.equalsIgnoreCase("Doctor")) {
									DoctorSeleccionado = listaDoctor.get(Integer.parseInt(id)-1);
									tipodePersona = "Doctor";
								} else if (tipo.equalsIgnoreCase("Paciente")) {
									PacienteSeleccionado = listaPaciente.get(Integer.parseInt(id)-1);
									tipodePersona = "Paciente";
								}
								btnPasar.setEnabled(true);
								btnDevolver.setEnabled(false);
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
				String[] headerVivePersona = {"Id", "Nombre", "Tipo"};
				modelVivePersona = new DefaultTableModel();
				modelVivePersona.setColumnIdentifiers(headerVivePersona);
				tblVivePersona.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						int index = tblVivePersona.getSelectedRow();
						Object idObject = tblVivePersona.getValueAt(index, 0);
						String id = String.valueOf(idObject);
						Object tipoObject = tblVivePersona.getValueAt(index, 2);
						String tipo = String.valueOf(tipoObject);
						
						if(index>=0)
						{
							if(tipo.equalsIgnoreCase("Doctor")) {
								DoctorSeleccionado = listaDoctor.get(Integer.parseInt(id)-1);
								tipodePersona = "Doctor";
							} else if (tipo.equalsIgnoreCase("Paciente")) {
								PacienteSeleccionado = listaPaciente.get(Integer.parseInt(id)-1);
								tipodePersona = "Paciente";
							}
							btnPasar.setEnabled(false);
							btnDevolver.setEnabled(true);
						}
					}
				});
				tblVivePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblVivePersona.setModel(modelVivePersona);
				scrollPane.setViewportView(tblVivePersona);
			}
			{
				btnDevolver = new JButton("<<");
				btnDevolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						btnDevolver.setEnabled(false);
						

						if (tipodePersona.equalsIgnoreCase("Doctor")) {
							listaViveDoctor.remove(DoctorSeleccionado);
						} else if (tipodePersona.equalsIgnoreCase("Paciente")) {
							listaVivePaciente.remove(PacienteSeleccionado);
						}

						
						
						
						
						imprimirVivePersona();
						imprimirPersonas();
						habilitarBotonListo();
						
					}
				});
				btnPasar = new JButton(">>");
				btnPasar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnPasar.setEnabled(false);
						

						if (tipodePersona.equalsIgnoreCase("Doctor")) {
							listaViveDoctor.add(DoctorSeleccionado);
						} else if (tipodePersona.equalsIgnoreCase("Paciente")) {
							listaVivePaciente.add(PacienteSeleccionado);
						}
						
						
						
						imprimirVivePersona();
						imprimirPersonas();
						habilitarBotonListo();
					}
				});
				btnPasar.setEnabled(false);
				btnPasar.setBounds(304, 69, 58, 36);
				panelListado.add(btnPasar);
			}
			
			
			
			btnDevolver.setEnabled(false);
			btnDevolver.setBounds(304, 131, 58, 36);
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
						listaDoctorReferencia.addAll(listaViveDoctor);
						listaPacienteReferencia.addAll(listaVivePaciente);
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
	
	public static void imprimirPersonas() {
	    modelPersona.setRowCount(0);
	    rowPersona = new Object[modelPersona.getColumnCount()];

	    // Printing persons from listaDoctor excluding those in listaViveDoctor
	    for (Persona persona : listaDoctor) {
	        if (!isPersonInList(persona, listaViveDoctor)) {
	            rowPersona[0] = persona.getId();
	            rowPersona[1] = persona.getNombre();
	            rowPersona[2] = "Doctor";
	            modelPersona.addRow(rowPersona);
	        }
	    }

	    // Printing persons from listaPaciente excluding those in listaViveciente
	    for (Persona persona : listaPaciente) {
	        if (!isPersonInList(persona, listaVivePaciente)) {
	            rowPersona[0] = persona.getId();
	            rowPersona[1] = persona.getNombre();
	            rowPersona[2] = "Paciente";
	            modelPersona.addRow(rowPersona);
	        }
	    }
	}

	// Helper method to check if a person is in the exclusion list
	private static boolean isPersonInList(Persona persona, List<? extends Persona> exclusionList) {
	    return exclusionList.contains(persona);
	}
	
	public static void imprimirVivePersona()
	{
		modelVivePersona.setRowCount(0);
		rowVivePersona = new Object[modelVivePersona.getColumnCount()];
		
		for (Persona persona : listaViveDoctor) {
			rowVivePersona[0] = persona.getId();
			rowVivePersona[1] = persona.getNombre();
			rowVivePersona[2] = "Doctor";
			modelVivePersona.addRow(rowVivePersona);
		}
		
		for (Persona persona : listaVivePaciente) {
			rowVivePersona[0] = persona.getId();
			rowVivePersona[1] = persona.getNombre();
			rowVivePersona[2] = "Paciente";
			modelVivePersona.addRow(rowVivePersona);
		}
	}
	
	
}