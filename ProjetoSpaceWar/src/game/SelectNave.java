package game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectNave extends JPanel {

	public SelectNave(JFrame frame, JLabel menu) {
		
		JLabel nave1 = new JLabel(new ImageIcon("res\\nave.jpg"));
		JLabel nave2 = new JLabel(new ImageIcon("res\\nave.jpg"));
		JLabel nave3 = new JLabel(new ImageIcon("res\\nave.jpg"));
		add(nave1);
		add(nave2);
		add(nave3);
		
		nave1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				
				game = null;
			}
		});
		
		nave2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mouseClicked(MouseEvent e) {

			}
		});
		
		nave3.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mouseClicked(MouseEvent e) {

			}
		});
		
		setBounds(40, 250, 500, 100);	
		setBackground(new Color(0,0,0,65));
	}
	
}