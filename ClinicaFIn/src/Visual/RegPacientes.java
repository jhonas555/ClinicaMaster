package Visual;
import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.Vacuna;

public class RegPacientes extends JPanel {
    private JTextField txtId;
    private JTable table;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtNumSeg;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    
	private DefaultTableModel model;
	private Object row[];
	private JButton btnEliminar;
	private JButton btnModificar;
	private Paciente pacienteselec = null;
	
	private ArrayList<Vacuna> listaVacunas = new ArrayList<Vacuna>();

    /**
     * Create the panel.
     * @param contentPane 
     * @param content 
     */
    public RegPacientes(JPanel contentPane, JPanel content) {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Paciente");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(28, 26, 208, 23);
        add(lblNewLabel);
        this.setPreferredSize(new Dimension(1904, 896));

        JLabel lblNewLabel_1 = new JLabel("_____________________________________________");
        lblNewLabel_1.setBounds(28, 47, 494, 16);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Apellido");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(28, 212, 56, 16);
        add(lblNewLabel_2);

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBounds(165, 96, 330, 32);
        txtId.setText("P-"+Clinica.getIdPcientes());
        add(txtId);
        txtId.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(1032, 13, 860, 870);
        add(scrollPane);

		String[] header = {"Id", "Nombre", "Cedula"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               	Paciente pacientes = new Paciente(
            			txtId.getText(),
            			txtNombre.getText(), 
            			txtApellido.getText(), 
            			txtTelefono.getText(),
            			txtCedula.getText(),  
            			txtCorreo.getText(),
            			txtNumSeg.getText()
            			);
            	Clinica.getInstance().agregarPacientes(pacientes);
            	
                
                JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                loadPaciente();
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
				
				pacienteselec = Clinica.getInstance().buscarPacientePorId(id);
				if (pacienteselec != null) {
					txtId.setText(String.valueOf(pacienteselec.getId()));
					txtNombre.setText(pacienteselec.getNombre());
					txtApellido.setText(pacienteselec.getApellido());
					txtTelefono.setText(pacienteselec.getTelefono());
					txtCedula.setText(pacienteselec.getCedula());
					txtCorreo.setText(pacienteselec.getCorreoElectronico());
					txtNumSeg.setText(pacienteselec.getNumeroSeguro());
				}			
			}
		});
        btnNewButton.setBounds(900, 617, 120, 32);
        add(btnNewButton);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               	Paciente pacientes = new Paciente(
            			txtId.getText(), 
            			txtCedula.getText(), 
            			txtNombre.getText(), 
            			txtApellido.getText(), 
            			txtTelefono.getText(), 
            			txtCorreo.getText(),
            			txtNumSeg.getText()
            			);
				Clinica.getInstance().actualizarPaciente(pacientes.getId(), pacientes);;
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Modificar", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				loadPaciente();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);

            }
        });
        btnModificar.setBounds(768, 617, 120, 32);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
               	Paciente pacientes = new Paciente(
            			txtId.getText(), 
            			txtCedula.getText(), 
            			txtNombre.getText(), 
            			txtApellido.getText(), 
            			txtTelefono.getText(), 
            			txtCorreo.getText(),
            			txtNumSeg.getText()
            			);
				Clinica.getInstance().eliminarPaciente(pacientes.getId());
				JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				loadPaciente();
				
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnNewButton.setEnabled(true);
        	}
        });
        btnEliminar.setEnabled(false);
        btnEliminar.setBounds(636, 617, 120, 32);
        add(btnEliminar);

        JLabel lblFecha = new JLabel("Nombre");
        lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFecha.setBounds(28, 157, 56, 16);
        add(lblFecha);

        txtApellido = new JTextField();
        txtApellido.setColumns(10);
        txtApellido.setBounds(165, 205, 330, 32);
        add(txtApellido);

        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblId.setBounds(28, 103, 135, 16);
        add(lblId);

        JButton btnPacienteNuevo = new JButton("Paciente Nuevo");
        btnPacienteNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnPacienteNuevo.setBounds(504, 617, 120, 32);
        add(btnPacienteNuevo);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(165, 150, 330, 32);
        add(txtNombre);

        JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
        lblCorreoElectrnico.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCorreoElectrnico.setBounds(28, 386, 147, 16);
        add(lblCorreoElectrnico);

        txtCorreo = new JTextField();
        txtCorreo.setColumns(10);
        txtCorreo.setBounds(165, 379, 330, 32);
        add(txtCorreo);
        
		JLabel lblEspecialidad = new JLabel("Historial Cl\u00EDnico");
		lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspecialidad.setBounds(28, 507, 147, 16);
		add(lblEspecialidad);
		
		JButton btnConsultas = new JButton("Consultas");

		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalVisual.LlamarRegConsultas(contentPane, content);
			}
		});
		btnConsultas.setBounds(167, 501, 120, 32);
		add(btnConsultas);
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVacunas.setBounds(30, 563, 147, 16);
		add(lblVacunas);

		JButton btnAdministrar = new JButton("Administrar");

		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listVacunasPuestas ventanaVacunas = new listVacunasPuestas(txtId.getText(), txtNombre.getText(), listaVacunas);
				ventanaVacunas.setVisible(true);
			}
		});

		btnAdministrar.setBounds(167, 556, 120, 32);

		add(btnAdministrar);
		
		JLabel lblNumeroSeguro = new JLabel("N\u00FAmero seguro");
		lblNumeroSeguro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumeroSeguro.setBounds(28, 439, 147, 16);
		add(lblNumeroSeguro);
		
		txtNumSeg = new JTextField();
		txtNumSeg.setColumns(10);
		txtNumSeg.setBounds(165, 432, 330, 32);
		add(txtNumSeg);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(165, 317, 330, 32);
		add(txtCedula);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(165, 262, 330, 32);
		add(txtTelefono);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(28, 269, 81, 16);
		add(lblTelefono);
		
		JLabel lblCedula = new JLabel("C\u00E9dula");
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCedula.setBounds(28, 324, 56, 16);
		add(lblCedula);
		loadPaciente();
	}

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
		txtCedula.setText("");
		txtTelefono.setText(""); 
		txtNumSeg.setText("");
    }
	private void loadPaciente() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Paciente pacientes : Clinica.getInstance().getPaciente()) {
			row[0] = pacientes.getId();
			row[1] = pacientes.getNombre();
			row[2] = pacientes.getCedula();
			model.addRow(row);
		}		
	}
}
