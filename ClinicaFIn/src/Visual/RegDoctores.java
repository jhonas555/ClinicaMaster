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
import java.awt.event.ActionEvent;

import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;

public class RegDoctores extends JPanel {
    private JTextField txtId;
    private JTable table;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtEspecialidad;
    private JTextField txtLicencia;
    private JTextField txtPassword;
    private JTextField txtUsuario;
    private JTextField txtCedula;
    
	private DefaultTableModel model;
	private Object row[];

    /**
     * Create the panel.
     */
    public RegDoctores() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Doctor");
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
        txtId.setBounds(165, 96, 330, 32);
        add(txtId);
        txtId.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(1032, 13, 860, 870);
        add(scrollPane);
        
		String[] header = {"Id", "Nombre", "Especialidad"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agregarDoctor();
            }
        });
        btnNewButton.setBounds(900, 595, 120, 32);
        add(btnNewButton);
        btnNewButton.setEnabled(true);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        btnModificar.setBounds(768, 595, 120, 32);
        add(btnModificar);
        btnModificar.setEnabled(false);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(636, 595, 120, 32);
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

        JButton button_1 = new JButton("Cita Nueva");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_1.setBounds(504, 595, 120, 32);
        add(button_1);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(165, 150, 330, 32);
        add(txtNombre);

        txtTelefono = new JTextField();
        txtTelefono.setColumns(10);
        txtTelefono.setBounds(165, 260, 330, 32);
        add(txtTelefono);

        JLabel lblTelfono = new JLabel("Tel\u00E9fono");
        lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTelfono.setBounds(28, 268, 126, 16);
        add(lblTelfono);

        JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
        lblCorreoElectrnico.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCorreoElectrnico.setBounds(28, 322, 147, 16);
        add(lblCorreoElectrnico);

        txtCorreo = new JTextField();
        txtCorreo.setColumns(10);
        txtCorreo.setBounds(165, 315, 330, 32);
        add(txtCorreo);

        txtEspecialidad = new JTextField();
        txtEspecialidad.setColumns(10);
        txtEspecialidad.setBounds(165, 370, 330, 32);
        add(txtEspecialidad);

        JLabel lblEspecialidad = new JLabel("Especialidad");
        lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEspecialidad.setBounds(28, 377, 147, 16);
        add(lblEspecialidad);

        txtLicencia = new JTextField();
        txtLicencia.setColumns(10);
        txtLicencia.setBounds(165, 425, 330, 32);
        add(txtLicencia);

        JLabel lblLicenciaMdica = new JLabel("Licencia M\u00E9dica");
        lblLicenciaMdica.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLicenciaMdica.setBounds(28, 432, 147, 16);
        add(lblLicenciaMdica);

        JLabel label = new JLabel("Contrase\u00F1a");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(520, 157, 88, 16);
        add(label);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(657, 150, 330, 32);
        add(txtPassword);

        txtUsuario = new JTextField();
        txtUsuario.setColumns(10);
        txtUsuario.setBounds(657, 96, 330, 32);
        add(txtUsuario);

        JLabel label_1 = new JLabel("Usuario");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label_1.setBounds(520, 103, 135, 16);
        add(label_1);
        
        JLabel lblCdula = new JLabel("C\u00E9dula");
        lblCdula.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCdula.setBounds(28, 487, 147, 16);
        add(lblCdula);
        
        txtCedula = new JTextField();
        txtCedula.setColumns(10);
        txtCedula.setBounds(165, 480, 330, 32);
        add(txtCedula);
    }

    private void agregarDoctor() {
    	Doctor doctor = new Doctor(
    			txtId.getText(), 
    			txtPassword.getText(), 
    			txtCedula.getText(), 
    			txtNombre.getText(), 
    			txtApellido.getText(), 
    			txtTelefono.getText(), 
    			txtCorreo.getText()
    			);
    	
        
        JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
        clean();
        
    }

    private void clean() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        //txtFecha.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtEspecialidad.setText("");
        txtLicencia.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
    }
    
}