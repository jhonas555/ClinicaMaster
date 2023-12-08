package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


import logico.Clinica;

public class VisualMedico extends JFrame {

	private JPanel contentPane;

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
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualMedico frame = new VisualMedico();
					frame.setVisible(true);
				//	IniciarSesion frame = new IniciarSesion();			Sirve para que muestre el inicio de sesión siempre de primero
				//    frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VisualMedico() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		setTitle("ClinicaJDC");
		setResizable(false);
		Dimension dim = getToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setSize(dim.width, dim.height-50);
		setLocationRelativeTo(null);
		
		JPanel content = new JPanel();
		contentPane.add(content, BorderLayout.CENTER);
		content.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Component rigidArea0 = Box.createRigidArea(new Dimension(5,0));
		menuBar.add(rigidArea0);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/medic_logoMenu.png")));
		menuBar.add(lblNewLabel);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(5,0));
		menuBar.add(rigidArea2);
		
		JMenu mnNewMenu_1 = new JMenu("P\u00E1gina Principal");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PagPrincipal pagprincipal = new PagPrincipal();
				pagprincipal.setSize(1904,908);
				pagprincipal.setLocation(0,0);
				
				content.removeAll();
				content.add(pagprincipal, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_1.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/menu-burger.png")));
		
		menuBar.add(mnNewMenu_1);
		//mnNewMenu_1.setSelected(true);
		
		Dimension currentSize = mnNewMenu_1.getPreferredSize();
		int additionalWidth = 20; // You can adjust this value
		int newWidth = currentSize.width + additionalWidth;
		mnNewMenu_1.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnNewMenu = new JMenu("Citas");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegCitas regcitas = new RegCitas();
				regcitas.setSize(1904,908);
				regcitas.setLocation(0,0);
				
				content.removeAll();
				content.add(regcitas, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		
		mnNewMenu.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/bookmark.png")));
		
		menuBar.add(mnNewMenu);
		
		currentSize = mnNewMenu.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnViviendas = new JMenu("Viviendas");
		mnViviendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegViviendas regviviendas = new RegViviendas();
				regviviendas.setSize(1904,908);
				regviviendas.setLocation(0,0);
				
				content.removeAll();
				content.add(regviviendas, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnViviendas.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/home.png")));
		menuBar.add(mnViviendas);
		
		currentSize = mnViviendas.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnViviendas.setPreferredSize(new Dimension(newWidth, currentSize.height));
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		
		JMenu mnNewMenu_3 = new JMenu("Pacientes");
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegPacientes regpacientes = new RegPacientes(contentPane, content);
				regpacientes.setSize(1904,908);
				regpacientes.setLocation(0,0);
				
				content.removeAll();
				content.add(regpacientes, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_3.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/user.png")));
		menuBar.add(mnNewMenu_3);
		
		currentSize = mnNewMenu_3.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_3.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnNewMenu_4 = new JMenu("Enfermedades");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegEnfermedades regenfermedades = new RegEnfermedades();
				regenfermedades.setSize(1904,908);
				regenfermedades.setLocation(0,0);
				
				content.removeAll();
				content.add(regenfermedades, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_4.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/bacterium.png")));
		menuBar.add(mnNewMenu_4);
		
		currentSize = mnNewMenu_4.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_4.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnNewMenu_5 = new JMenu("Vacunas ");
		mnNewMenu_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegVacunas regvacunas = new RegVacunas();
				regvacunas.setSize(1904,908);
				regvacunas.setLocation(0,0);
				
				content.removeAll();
				content.add(regvacunas, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_5.setIcon(new ImageIcon(VisualMedico.class.getResource("/Fotos/syringe.png")));
		menuBar.add(mnNewMenu_5);
		
		currentSize = mnNewMenu_5.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_5.setPreferredSize(new Dimension(newWidth, currentSize.height));
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		
		JMenu mnNewMenu_7 = new JMenu("    Apariencia");
		menuBar.add(mnNewMenu_7);
		
		currentSize = mnNewMenu_7.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_7.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Blanco");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FlatMaterialLighterIJTheme.setup();
		        
		        // Create a new instance of VisualMedico
				VisualMedico newFrame = new VisualMedico();
		        
		        // Set the new frame visible
		        newFrame.setVisible(true);
		        
		        // Dispose the current frame (optional)
		        dispose();
			}
		});
		
		
		mnNewMenu_7.add(mntmNewMenuItem);
		
		mntmNewMenuItem.setPreferredSize(new Dimension(150, 36));
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Negro");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        // Create a new instance of VisualMedico
				VisualMedico newFrame = new VisualMedico();
		        
		        // Set the new frame visible
		        newFrame.setVisible(true);
		        
		        // Dispose the current frame (optional)
		        dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_1.setPreferredSize(new Dimension(150, 36));
		
		Component rigidArea = Box.createRigidArea(new Dimension(5,68));
		menuBar.add(rigidArea);
		setContentPane(contentPane);
		
		
		
		PagPrincipal pagprincipal = new PagPrincipal();
		pagprincipal.setSize(1904,908);
		pagprincipal.setLocation(0,0);
		
		content.removeAll();
		content.add(pagprincipal, BorderLayout.CENTER);
		content.revalidate();
		content.repaint();	
	}
	
	public static void LlamarRegConsultas(JPanel contentPane2, JPanel content /*poner el id del paciente*/) {// primer argumento es la clase del Jpane principal
		// segundo argumento del Jpanel interior
		RegConsultas regconsultas = new RegConsultas();
		regconsultas.setSize(1904,908);
		regconsultas.setLocation(0,0);
		
		content.removeAll();
		content.add(regconsultas, BorderLayout.CENTER);
		content.revalidate();
		content.repaint();
		
	}
	
	
	

}
