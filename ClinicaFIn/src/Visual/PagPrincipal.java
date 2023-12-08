package Visual;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PagPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public PagPrincipal() {
		
		setLayout(null);
		Dimension dimension = new Dimension(1904, 896);
		this.setPreferredSize(dimension);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PagPrincipal.class.getResource("/Fotos/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 1904, 883);
		add(lblNewLabel);

	}
}
