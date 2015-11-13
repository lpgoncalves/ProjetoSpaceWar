package game;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.Main_Frame;
import music.AllMusic;

public class SelectNave extends JPanel {
	private Main_Frame mFrame;
	private Menu mMenu;
	private AllMusic selectMusic;
	private String pathSelectNave = "res\\sons\\Menu_Select.mp3";
	private JLabel naveName;
	private JLabel nave1;
	private JLabel nave2;
	private JLabel nave3;
	
	public SelectNave(JFrame frame, Menu menu) {
		
		SelectNave.this.mFrame = (Main_Frame) frame;
		mMenu = menu;

		nave1 = new JLabel(new ImageIcon("res\\nave1.jpg"));
		nave1.setBounds(295, 380, 95, 95);
		nave2 = new JLabel(new ImageIcon("res\\nave2.jpg"));
		nave2.setBounds(395, 380, 95, 95);
		nave3 = new JLabel(new ImageIcon("res\\nave3.jpg"));
		nave3.setBounds(495, 380, 95, 95);
		add(nave1);
		add(nave2);
		add(nave3);
		
		naveName = new JLabel("");
		
		naveName.setForeground(SystemColor.WHITE);
		naveName.setVisible(true);
		add(naveName);
				
		nave1.addMouseListener(new jogar(nave1, 1));
		nave2.addMouseListener(new jogar(nave2, 2));
		nave3.addMouseListener(new jogar(nave3, 3));
		
		setBounds(280, 350, 300, 100);
		setBackground(new Color(0,0,0,65));
        setOpaque(false);
	}
	
	public class jogar implements MouseListener {
		private JLabel nave;
		private int tipoNave;
		
		public jogar(JLabel nave, int tipoNave){
			this.nave = nave;
			this.tipoNave = tipoNave;
		}
		
		public void mouseEntered(MouseEvent e) {
			selectMusic = new AllMusic(pathSelectNave);
			selectMusic.setloop(false);
			selectMusic.start();
			nave.setIcon(new ImageIcon("res\\nave"+tipoNave+"-borda.png"));	
			switch(tipoNave){
				case 1:
					naveName.setText("Galaxy Explorer");
					naveName.setBounds(295, 475, 200, 30);
					break;
				case 2:
					naveName.setText("Destroyer SpaceShip");
					naveName.setBounds(385, 475, 200, 30);
					break;
				case 3:
					naveName.setText("Intergallatic Commander");
					naveName.setBounds(495, 475, 200, 30);
					break;
			}
		}
		public void mouseExited(MouseEvent e) {
			nave.setIcon(new ImageIcon("res\\nave"+tipoNave+".jpg"));
			naveName.setText("");
		}
		public void mouseClicked(MouseEvent e) {
			Fase game = new Fase(mMenu, tipoNave);
			mFrame.add(game);
			setVisible(false);
			SelectNave.this.mFrame.background.setVisible(false);
			SelectNave.this.mMenu.menuMusic.close();
			game.requestFocusInWindow();
		}
		public void mousePressed(MouseEvent arg0) {			
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
}
