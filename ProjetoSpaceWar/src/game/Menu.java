package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	 * para o item que n�o est� selecionado
	 */

	public Menu(JFrame frame) {

		JButton jogar = new JButton("JOGAR");
		jogar.setForeground(FontGame.DefaultColor());
		jogar.setFont(FontGame.Get());
		jogar.setBounds(0, 0, 130, 20);
		JLabel menu = this;
		menu.add(jogar);

		jogar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				jogar.setForeground(FontGame.SelectedColor());
			}

			public void mouseExited(MouseEvent e) {
				jogar.setForeground(FontGame.DefaultColor());
			}

			public void mouseClicked(MouseEvent e) {

				Fase game = new Fase(menu);
				frame.add(game);
				setVisible(false);
				game.setFocusable(true);
				game = null;

			}
		});

		JButton recordes = new JButton("RECORDES");
		recordes.setFont(FontGame.Get());
		recordes.setBounds(0, 30, 130, 20);
		add(recordes);
		recordes.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				recordes.setForeground(FontGame.SelectedColor());
			}

			public void mouseExited(MouseEvent e) {
				recordes.setForeground(FontGame.DefaultColor());
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		JButton opcoes = new JButton("AJUDA");
		opcoes.setFont(FontGame.Get());
		opcoes.setBounds(0, 60, 130, 20);
		add(opcoes);
		opcoes.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				opcoes.setForeground(FontGame.SelectedColor());
			}

			public void mouseExited(MouseEvent e) {
				opcoes.setForeground(FontGame.DefaultColor());
			}

			public void mouseClicked(MouseEvent e) {

				Menu_Help help = new Menu_Help(frame);
				frame.add(help);
				setVisible(false);
			}
		});

	}

	public void MostraMenu() {
		setVisible(true);
	}

}
