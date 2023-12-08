package Visual;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import logico.Clinica;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

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
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					FileInputStream clinica;
					FileOutputStream clinica2;
					ObjectInputStream clinicaRead;
					ObjectOutputStream clinicaWrite;
					try {
						clinica = new FileInputStream("clinica1.dat");
						clinicaRead = new ObjectInputStream(clinica);
						Clinica temp = (Clinica) clinicaRead.readObject();
						Clinica.setClinica(temp);
						clinica.close();
						clinicaRead.close();
					} catch (FileNotFoundException e) {
						try {
							clinica2 = new FileOutputStream("clinica1.dat");
							clinicaWrite = new ObjectOutputStream(clinica2);
							User admin = new User("admin", "admin", "Administrador");
							Clinica.getInstance().AgregarUser(admin);
							clinicaWrite.writeObject(Clinica.getInstance());
							clinica2.close();
							clinicaWrite.close();
						} catch (FileNotFoundException e1) {

						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
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
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(53, 151, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setBounds(53, 215, 82, 16);
		panel.add(lblPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(169, 144, 320, 32);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Clinica.getInstance().confirmLogin(txtUser.getText(), String.valueOf(txtPass.getPassword()))) {
					PrincipalVisual frame = new PrincipalVisual();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "El usuario ingresado no es válido", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(420, 298, 120, 32);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(IniciarSesion.class.getResource("/Fotos/medic_logo75px.png")));
		lblNewLabel_2.setBounds(120, 30, 75, 75);
		panel.add(lblNewLabel_2);		
		
		txtPass = new JPasswordField();
		txtPass.setBounds(169, 209, 320, 32);
		panel.add(txtPass);
	}
}
