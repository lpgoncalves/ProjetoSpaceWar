package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu_Help extends JLabel {

	public Menu_Help(JFrame frame) {
		
		JLabel keysWASD = new JLabel();
		keysWASD.setIcon(new ImageIcon("res\\Keys WASD.png"));
		keysWASD.setBounds(0, 0, 130, 20);
		
		JLabel menu = this;
		menu.add(keysWASD);
		MostraMenu();
	}
	
	public void MostraMenu(){
		setVisible(true);
		
	}
	
}
