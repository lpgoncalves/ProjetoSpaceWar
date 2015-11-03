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
		JLabel nave2 = new JLabel(new ImageIcon("res\\nave2.jpg"));
		JLabel nave3 = new JLabel(new ImageIcon("res\\nave3.png"));
		add(nave1);
		add(nave2);
		add(nave3);
		
		nave1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				nave1.setIcon(new ImageIcon("res\\nave-1-borda.png"));
			}

			public void mouseExited(MouseEvent e) {
				nave1.setIcon(new ImageIcon("res\\nave.jpg"));
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 1);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				game.grabFocus();
				game = null;
			}
		});
		
		nave2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				nave2.setIcon(new ImageIcon("res\\nave-2-borda.png"));
			}

			public void mouseExited(MouseEvent e) {
				nave2.setIcon(new ImageIcon("res\\nave2.jpg"));
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 2);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				game.grabFocus();
				game = null;
			}
		});
		
		nave3.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				nave3.setIcon(new ImageIcon("res\\nave-3-borda.png"));
			}

			public void mouseExited(MouseEvent e) {
				nave3.setIcon(new ImageIcon("res\\nave3.png"));
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 3);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				game.grabFocus();
				game = null;
			}
		});
		
		setBounds(150, 250, 300, 100);
		setBackground(new Color(0,0,0,65));
        setOpaque(false);
	}
	
}
