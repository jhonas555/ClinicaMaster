package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
import javax.swing.SwingConstants;


import logico.Clinica;

public class PrincipalVisual extends JFrame {

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
					PrincipalVisual frame = new PrincipalVisual();
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
	public PrincipalVisual() {
		
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
		lblNewLabel.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/medic_logoMenu.png")));
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
		
		mnNewMenu.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/bookmark.png")));
		
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
		mnViviendas.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/home.png")));
		menuBar.add(mnViviendas);
		
		currentSize = mnViviendas.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnViviendas.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnNewMenu_2 = new JMenu("Doctores");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegDoctores regdoctores = new RegDoctores();
				regdoctores.setSize(1904,908);
				regdoctores.setLocation(0,0);
				
				content.removeAll();
				content.add(regdoctores, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_2.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/user-md.png")));
		menuBar.add(mnNewMenu_2);
		
		currentSize = mnNewMenu_2.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_2.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
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
		mnNewMenu_3.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/user.png")));
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
		mnNewMenu_4.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/bacterium.png")));
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
		mnNewMenu_5.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/syringe.png")));
		menuBar.add(mnNewMenu_5);
		
		currentSize = mnNewMenu_5.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_5.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
		JMenu mnNewMenu_6 = new JMenu("Reg Usuario");
		mnNewMenu_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RegUsuarios regUsuarios = new RegUsuarios();
				regUsuarios.setSize(1904,908);
				regUsuarios.setLocation(0,0);
				
				content.removeAll();
				content.add(regUsuarios, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		mnNewMenu_6.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Fotos/admin-alt.png")));
		menuBar.add(mnNewMenu_6);
		
		currentSize = mnNewMenu_6.getPreferredSize();
		additionalWidth = 20; // You can adjust this value
		newWidth = currentSize.width + additionalWidth;
		mnNewMenu_6.setPreferredSize(new Dimension(newWidth, currentSize.height));
		
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
		        
		        // Create a new instance of PrincipalVisual
		        PrincipalVisual newFrame = new PrincipalVisual();
		        
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
		        
		        // Create a new instance of PrincipalVisual
		        PrincipalVisual newFrame = new PrincipalVisual();
		        
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
