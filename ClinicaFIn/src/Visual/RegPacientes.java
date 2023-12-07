package Visual;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logico.Paciente;

public class RegPacientes extends JPanel {
    private JTextField idCita;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_5;

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
                //agregarPaciente();
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
		lblEspecialidad.setBounds(28, 322, 147, 16);
		add(lblEspecialidad);

		
		JButton btnConsultas = new JButton("Consultas");

		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalVisual.LlamarRegConsultas(contentPane, content);

			}

		});
		btnConsultas.setBounds(165, 315, 120, 32);
		add(btnConsultas);
		JLabel lblVacunas = new JLabel("Vacunas");
		lblVacunas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVacunas.setBounds(28, 377, 147, 16);
		add(lblVacunas);

		JButton btnAdministrar = new JButton("Administrar");

		btnAdministrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				listVacunasPuestas ventanaVacunas = new listVacunasPuestas();

				ventanaVacunas.setVisible(true);

			}

		});

		btnAdministrar.setBounds(165, 370, 120, 32);

		add(btnAdministrar);

	}

    private void limpiarCampos() {
        idCita.setText("");
        textField_1.setText("");
        textField.setText("");
        textField_5.setText("");
    }
}
