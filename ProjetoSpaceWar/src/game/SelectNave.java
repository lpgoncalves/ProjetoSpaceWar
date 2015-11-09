package game;

import java.awt.Color;

import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Main_Frame;
import music.AllMusic;

public class SelectNave extends JPanel {

	private Main_Frame mFrame;
	
	private AllMusic selectNave;
	
	private String pathSelectNave = "res\\sons\\Menu_Select.mp3";
	
	private JLabel naveName;
	
	public SelectNave(JFrame frame, Menu menu) {
		
		SelectNave.this.mFrame = (Main_Frame) frame;
		
		JLabel nave1 = new JLabel(new ImageIcon("res\\nave.jpg"));
		nave1.setBounds(170, 270, 95, 95);
		JLabel nave2 = new JLabel(new ImageIcon("res\\nave2.jpg"));
		nave2.setBounds(270, 270, 95, 95);
		JLabel nave3 = new JLabel(new ImageIcon("res\\nave3.png"));
		nave3.setBounds(370, 270, 95, 95);
		add(nave1);
		add(nave2);
		add(nave3);
		
		naveName = new JLabel("");
		
		naveName.setForeground(SystemColor.WHITE);
		naveName.setVisible(true);
		add(naveName);
		
		nave1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectNave = new AllMusic(pathSelectNave);
				selectNave.setloop(false);
				selectNave.start();
				nave1.setIcon(new ImageIcon("res\\nave-1-borda.png"));				
				naveName.setText("Galaxy Explorer");
				naveName.setBounds(173, 370, 200, 30);
			}

			public void mouseExited(MouseEvent e) {
				nave1.setIcon(new ImageIcon("res\\nave.jpg"));
				naveName.setText("");
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 1);
				frame.add(game);
				setVisible(false);
				SelectNave.this.mFrame.background.setVisible(false);
				SelectNave.this.mFrame.menuMusic.close();
				game.setFocusable(true);
				game.grabFocus();
				game = null;
		
			}
		});
		
		nave2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectNave = new AllMusic(pathSelectNave);
				selectNave.setloop(false);
				selectNave.start();
				nave2.setIcon(new ImageIcon("res\\nave-2-borda.png"));
				naveName.setText("Destroyer SpaceShip");
				naveName.setBounds(255, 370, 200, 30);
			}

			public void mouseExited(MouseEvent e) {
				nave2.setIcon(new ImageIcon("res\\nave2.jpg"));
				naveName.setText("");
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 2);
				frame.add(game);
				setVisible(false);
				SelectNave.this.mFrame.background.setVisible(false);
				SelectNave.this.mFrame.menuMusic.close();
				game.setFocusable(true);
				game.grabFocus();
				game = null;
			}
		});
		
		nave3.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectNave = new AllMusic(pathSelectNave);
				selectNave.setloop(false);
				selectNave.start();
				nave3.setIcon(new ImageIcon("res\\nave-3-borda.png"));	
				naveName.setText("Intergallatic Commander");
				naveName.setBounds(350, 370, 200, 30);
			}

			public void mouseExited(MouseEvent e) {
				nave3.setIcon(new ImageIcon("res\\nave3.png"));
				naveName.setText("");
			}

			public void mouseClicked(MouseEvent e) {
				Fase game = new Fase(menu, 3);
				frame.add(game);
				setVisible(false);
				SelectNave.this.mFrame.background.setVisible(false);
				SelectNave.this.mFrame.menuMusic.close();
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
