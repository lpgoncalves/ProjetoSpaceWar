package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.spi.ior.iiop.IIOPProfileTemplate;

import sun.java2d.pipe.AAShapePipe;
import sun.swing.SwingAccessor;
import sun.text.resources.cldr.mer.FormatData_mer;
import sun.util.calendar.LocalGregorianCalendar;
import sun.util.resources.cldr.am.CalendarData_am_ET;

import game.Main_Frame;

public class Menu extends JLabel implements ActionListener {

	/*
	 * Font fonte = new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	 * Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item
	 * selecionado Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA
	 * para o item que n�o est� selecionado
	 */

	// JFrame frame;

	private Main_Frame mFrame;

	public JLabel returnLabel;
	public JLabel menuAjuda;
	public JLabel menuRecordes;
	public JLabel tipoLabel;
	public JPanel panelRecordes;

	public Menu(JFrame frame) {

		Menu.this.mFrame = (Main_Frame) frame;

		Menu.this.mFrame.addKeyListener(new TeclaAdapter());

		JLabel menu = this;
		
		panelRecordes = new JPanel();
		panelRecordes.setLayout(null);
		panelRecordes.setBounds(0, 0, 600, 600);
		panelRecordes.setVisible(false);
		panelRecordes.setOpaque(false);
		

		Menu.this.menuAjuda = new JLabel(new ImageIcon("res\\Menu Inicial\\MenuAjuda.png"));
		menuAjuda.setBounds(0, 20, 600, 600);
		menuAjuda.setVisible(false);
		frame.add(menuAjuda);

		Menu.this.menuRecordes = new JLabel(new ImageIcon("res\\Menu Inicial\\MenuRecordes.png"));
		menuRecordes.setBounds(0, 20, 600, 600);
		menuRecordes.setVisible(true);
		//frame.add(menuRecordes);
		panelRecordes.add(menuRecordes);
		frame.add(panelRecordes);

		Menu.this.returnLabel = new JLabel("Pressione ESC para voltar");
		returnLabel.setBounds(400, 500, 150, 50);
		returnLabel.setForeground(SystemColor.WHITE);
		returnLabel.setVisible(false);
		frame.add(returnLabel);

		SelectNave panel = new SelectNave(frame, menu);
		panel.setVisible(false);
		frame.add(panel);

		JLabel jogar = new JLabel("");
		jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar.png"));
		jogar.setFont(FontGame.Get());
		jogar.setBounds(100, 0, 310, 40);

		menu.add(jogar);

		jogar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				// jogar.setForeground(FontGame.SelectedColor());
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar2.png"));
			}

			public void mouseExited(MouseEvent e) {
				// jogar.setForeground(FontGame.DefaultColor());
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar.png"));
			}

			public void mouseClicked(MouseEvent e) {

				panel.setVisible(true);
				setVisible(false);
				/*
				 * Fase game = new Fase(menu); frame.add(game);
				 * setVisible(false); game.setFocusable(true); game = null;
				 */

			}
		});


		
		
		JLabel recordes = new JLabel("");
		recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes.png"));
		recordes.setFont(FontGame.Get());
		recordes.setBounds(100, 45, 320, 40);
		add(recordes);
		recordes.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				// recordes.setForeground(FontGame.SelectedColor());
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes2.png"));
			}

			public void mouseExited(MouseEvent e) {
				// recordes.setForeground(FontGame.DefaultColor());
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes.png"));
			}

			public void mouseClicked(MouseEvent e) {
				mFrame.setLogoLabelVisible(false);
				setVisible(false);
				//menuRecordes.setVisible(true);
				panelRecordes.setVisible(true);
				returnLabel.setVisible(true);
				
				CreateLabels(panelRecordes, "posicao",  new String[]{ "1", "2", "3","4","5" });
				CreateLabels(panelRecordes, "tempo",  new String[]{ "1:30", "2:02", "3:10","4:55","5:22" });
				CreateLabels(panelRecordes, "pontos",  new String[]{ "112", "235", "373","412","587" });
			}
		});

		JLabel ajuda = new JLabel("");
		ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda.png"));
		ajuda.setFont(FontGame.Get());
		ajuda.setBounds(100, 90, 320, 40);
		add(ajuda);
		ajuda.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				// ajuda.setForeground(FontGame.SelectedColor());
				ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda2.png"));
			}

			public void mouseExited(MouseEvent e) {
				// ajuda.setForeground(FontGame.DefaultColor());
				ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda.png"));
			}

			public void mouseClicked(MouseEvent e) {
				mFrame.setLogoLabelVisible(false);
				setVisible(false);
				menuAjuda.setVisible(true);
				returnLabel.setVisible(true);
			}
		});

	}

	public void MostraMenu() {
		setVisible(true);
	}

	private void CreateLabels(JPanel panel, String tipo, String[] values) {
		//JLabel tipoLabel;
		int x = 0;
		int y = 0;
		
		switch (tipo) {
		case "posicao":
			x = 100;
			y = 285;
			
			for (int i = 0; i < values.length; i++) {
				tipoLabel = new JLabel(values[i]);
				tipoLabel.setFont(FontGame.Get());
				tipoLabel.setBounds(x, y, 320, 40);
				tipoLabel.setForeground(SystemColor.WHITE);
				tipoLabel.setVisible(true);
				panel.add(tipoLabel);
				
				y = y + 30;
			}
			break;

		case "tempo":
			x = 200;
			y = 285;
			
			for (int i = 0; i < values.length; i++) {
				tipoLabel = new JLabel(values[i]);
				tipoLabel.setFont(FontGame.Get());
				tipoLabel.setBounds(x, y, 320, 40);
				tipoLabel.setForeground(SystemColor.WHITE);
				tipoLabel.setVisible(true);
				panel.add(tipoLabel);
				y = y + 30;
			}
			break;
			
		case "pontos":
			x = 400;
			y = 285;
			
			for (int i = 0; i < values.length; i++) {
				tipoLabel = new JLabel(values[i]);
				tipoLabel.setFont(FontGame.Get());
				tipoLabel.setBounds(x, y, 320, 40);
				tipoLabel.setForeground(SystemColor.WHITE);
				tipoLabel.setVisible(true);
				panel.add(tipoLabel);
				y = y + 30;
			}
			break;
			
		default: 
			break;
		}
	}

	private class TeclaAdapter extends KeyAdapter { // Classe respons�vel por
													// pegar as teclas
													// pressionadas na fase.

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (!Menu.this.mFrame.logo.isVisible()) {
					Menu.this.mFrame.setLogoLabelVisible(true);
					Menu.this.menuAjuda.setVisible(false);
					Menu.this.panelRecordes.setVisible(false);
					returnLabel.setVisible(false);
					setVisible(true);
				}
			}

			Menu.this.processKeyEvent(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (!Menu.this.mFrame.logo.isVisible()) {
/*					Menu.this.mFrame.setLogoLabelVisible(true);
					Menu.this.menuAjuda.setVisible(false);*/
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
