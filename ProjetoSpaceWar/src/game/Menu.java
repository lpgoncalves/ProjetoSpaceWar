package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NetworkChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.util.calendar.LocalGregorianCalendar;

public class Menu extends JLabel {

	/*
	 * Font fonte = new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	 * Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item
	 * selecionado Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA
	 * para o item que não está selecionado
	 */

	public Menu(JFrame frame) {
		
		JLabel menu = this;
		
		JLabel keysMovement = new JLabel(new ImageIcon("res\\teste.png"));
		keysMovement.setBounds(200, 50, 300, 300);	
		keysMovement.setVisible(false);
		frame.add(keysMovement);
		
		SelectNave panel = new SelectNave(frame, menu);
		panel.setVisible(false);
		frame.add(panel);

		JLabel jogar = new JLabel("");
		jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar2.png"));
		jogar.setFont(FontGame.Get());
		jogar.setBounds(100, 0, 310, 40);
		
		
		menu.add(jogar);

		jogar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				//jogar.setForeground(FontGame.SelectedColor());
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar1.png"));
			}

			public void mouseExited(MouseEvent e) {
				//jogar.setForeground(FontGame.DefaultColor());
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar2.png"));
			}

			public void mouseClicked(MouseEvent e) {

				panel.setVisible(true);
				setVisible(false);
				/*Fase game = new Fase(menu);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				game = null;*/

			}
		});

		JLabel recordes = new JLabel("");
		recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes1.png"));
		recordes.setFont(FontGame.Get());
		recordes.setBounds(100, 45, 320, 40);
		add(recordes);
		recordes.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				//recordes.setForeground(FontGame.SelectedColor());
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes2.png"));
			}

			public void mouseExited(MouseEvent e) {
				//recordes.setForeground(FontGame.DefaultColor());
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes1.png"));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		JLabel ajuda = new JLabel("");
		ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda1.png"));
		ajuda.setFont(FontGame.Get());
		ajuda.setBounds(100, 90, 320, 40);
		add(ajuda);
		ajuda.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				//ajuda.setForeground(FontGame.SelectedColor());
				ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda2.png"));
			}

			public void mouseExited(MouseEvent e) {
				//ajuda.setForeground(FontGame.DefaultColor());
				ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda1.png"));
			}

			public void mouseClicked(MouseEvent e) {

				setVisible(false);
				keysMovement.setVisible(true);
			}
		});

	}

	public void MostraMenu() {
		setVisible(true);
	}

}
