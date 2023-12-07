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
import java.awt.event.ActionEvent;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

public class RegPacientes extends JPanel {
    private JTextField idCita;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_5;
    private JTextField textField_2;

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

        idCita = new JTextField();
        idCita.setBounds(165, 96, 330, 32);
        add(idCita);
        idCita.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(1032, 13, 860, 870);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               	Doctor doctores = new Doctor(
            			txtId.getText(), 
            			txtPassword.getText(), 
            			txtCedula.getText(), 
            			txtNombre.getText(), 
            			txtApellido.getText(), 
            			txtTelefono.getText(), 
            			txtCorreo.getText(),
            			txtEspecialidad.getText(),
            			txtLicencia.getText()
            			);
            	Clinica.getInstance().agregarDoctor(doctores);
            	
                
                JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
                clean();
                loadDoctores();
            }
        });
        btnNewButton.setBounds(900, 617, 120, 32);
        add(btnNewButton);

        JButton btnModificar = new JButton("Guardar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnModificar.setBounds(768, 617, 120, 32);
        add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(636, 617, 120, 32);
        add(btnEliminar);

        JLabel lblFecha = new JLabel("Nombre");
        lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFecha.setBounds(28, 157, 56, 16);
        add(lblFecha);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(165, 205, 330, 32);
        add(textField);

        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblId.setBounds(28, 103, 135, 16);
        add(lblId);

        JButton button_1 = new JButton("Cita Nueva");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_1.setBounds(504, 617, 120, 32);
        add(button_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(165, 150, 330, 32);
        add(textField_1);

        JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
        lblCorreoElectrnico.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCorreoElectrnico.setBounds(28, 267, 147, 16);
        add(lblCorreoElectrnico);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(165, 260, 330, 32);
        add(textField_5);
        
		JLabel lblEspecialidad = new JLabel("Historial Cl\u00EDnico");
		lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspecialidad.setBounds(30, 389, 147, 16);
		add(lblEspecialidad);

		
		JButton btnConsultas = new JButton("Consultas");

		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalVisual.LlamarRegConsultas(contentPane, content);

			}

		});
		btnConsultas.setBounds(167, 382, 120, 32);
		add(btnConsultas);
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVacunas.setBounds(30, 444, 147, 16);
		add(lblVacunas);

		JButton btnAdministrar = new JButton("Administrar");

		btnAdministrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				listVacunasPuestas ventanaVacunas = new listVacunasPuestas();

				ventanaVacunas.setVisible(true);

			}

		});

		btnAdministrar.setBounds(167, 437, 120, 32);

		add(btnAdministrar);
		
		JLabel lblNumeroSeguro = new JLabel("Numero seguro");
		lblNumeroSeguro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumeroSeguro.setBounds(28, 320, 147, 16);
		add(lblNumeroSeguro);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(165, 313, 330, 32);
		add(textField_2);

	}

    private void limpiarCampos() {
        idCita.setText("");
        textField_1.setText("");
        textField.setText("");
        textField_5.setText("");
    }
}
