package Visual;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IniciarSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Iniciar Sesion");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		lblNewLabel.setBounds(224, 39, 198, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(53, 151, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(53, 215, 82, 16);
		panel.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(169, 144, 320, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(169, 208, 320, 32);
		panel.add(textField_1);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(420, 298, 120, 32);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(IniciarSesion.class.getResource("/Fotos/medic_logo75px.png")));
		lblNewLabel_2.setBounds(120, 30, 75, 75);
		panel.add(lblNewLabel_2);		
	}
}
