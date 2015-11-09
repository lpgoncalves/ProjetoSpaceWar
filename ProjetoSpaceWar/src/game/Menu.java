package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.spi.ior.iiop.IIOPProfileTemplate;

import sun.java2d.pipe.AAShapePipe;
import sun.swing.SwingAccessor;
import sun.text.resources.cldr.mer.FormatData_mer;
import sun.util.calendar.LocalGregorianCalendar;
import sun.util.resources.cldr.am.CalendarData_am_ET;

import game.Main_Frame;
import music.AllMusic;

public class Menu extends JLabel implements ActionListener {

	/*
	 * Font fonte = new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	 * Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item
	 * selecionado Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA
	 * para o item que não está selecionado
	 */

	// JFrame frame;

	private Main_Frame mFrame;

	public JLabel returnLabel;
	public JLabel menuAjuda;
	public JLabel menuRecordes;
	public JLabel row;
	public JPanel panelRecordes;
	
	private AllMusic selectOption;
	private String pathSelectOption = "res\\sons\\Menu_Select.mp3";
	
	//public AllMusic menuMusic;
	//private String pathMenuMusic = "res\\sons\\Menu.mp3";

	public Menu(JFrame frame) {
		
		/*menuMusic = new AllMusic(pathMenuMusic);
		menuMusic.setloop(true);
		menuMusic.start();*/
		
		Menu.this.mFrame = (Main_Frame) frame;
		Menu.this.mFrame.addKeyListener(new TeclaAdapter());

		JLabel menu = this;
		
		panelRecordes = new JPanel();
		panelRecordes.setLayout(null);
		panelRecordes.setBounds(0, 0, 600, 600);
		panelRecordes.setVisible(false);
		panelRecordes.setOpaque(false);

		Menu.this.menuAjuda = new JLabel(new ImageIcon("res\\Menu Inicial\\menu-ajuda.png"));
		menuAjuda.setBounds(70, 25, 500, 500);
		menuAjuda.setVisible(false);
		frame.add(menuAjuda);

		Menu.this.menuRecordes = new JLabel(new ImageIcon("res\\Menu Inicial\\menu-recordes.png"));
		menuRecordes.setBounds(70, 25, 500, 500);
		menuRecordes.setVisible(true);
		panelRecordes.add(menuRecordes);
		frame.add(panelRecordes);

		Menu.this.returnLabel = new JLabel("Pressione ESC para voltar");
		returnLabel.setBounds(400, 500, 150, 50);
		returnLabel.setForeground(SystemColor.WHITE);
		returnLabel.setVisible(false);
		frame.add(returnLabel);

		SelectNave panel = new SelectNave(frame, menu);
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setVisible(false);
		frame.add(panel);

		JLabel jogar = new JLabel("");
		jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar.png"));
		jogar.setFont(FontGame.Get());
		jogar.setBounds(100, 0, 310, 40);

		menu.add(jogar);

		jogar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectOption = new AllMusic(pathSelectOption);
				selectOption.setloop(false);
				selectOption.start();
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar2.png"));
			}

			public void mouseExited(MouseEvent e) {
				jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar.png"));
			}

			public void mouseClicked(MouseEvent e) {
				panel.setVisible(true);
				setVisible(false);
			}
		});		
		
		JLabel recordes = new JLabel("");
		recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes.png"));
		recordes.setFont(FontGame.Get());
		recordes.setBounds(100, 45, 320, 40);
		add(recordes);
		recordes.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectOption = new AllMusic(pathSelectOption);
				selectOption.setloop(false);
				selectOption.start();
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes2.png"));
			}

			public void mouseExited(MouseEvent e) {
				recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes.png"));
			}

			public void mouseClicked(MouseEvent e) {
				mFrame.setLogoLabelVisible(false);
				setVisible(false);
				panelRecordes.setVisible(true);
				returnLabel.setVisible(true);
				String[][] pontuacao = new String[2][5];
				try {
					pontuacao = GetRecord();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				CreateLabels(panelRecordes, "posicao",  new String[]{ "1º", "2º", "3º","4º","5º" }); 			/// QUANTIDADE DE REGISTROS (LINHAS)
				CreateLabels(panelRecordes, "tempo",  pontuacao[1]); 	/// TEMPO
				CreateLabels(panelRecordes, "pontos",  pontuacao[0]);		/// PONTUAÇÃO
			}
		});

		JLabel ajuda = new JLabel("");
		ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda.png"));
		ajuda.setFont(FontGame.Get());
		ajuda.setBounds(100, 90, 320, 40);
		add(ajuda);
		ajuda.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				selectOption = new AllMusic(pathSelectOption);
				selectOption.setloop(false);
				selectOption.start();
				ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda2.png"));
			}

			public void mouseExited(MouseEvent e) {
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
	
	public String[][] GetRecord() throws IOException{
		BufferedReader buffRead = new BufferedReader(new FileReader("res\\recorde.txt"));
		String linha;
		String[] separadorLinha;
		String[][] dados = new String[2][5];
		String[] pontoRecord = new String[] { "0", "0", "0", "0", "0" };
		String[] tempoRecord = new String[] {"","","","",""};
		int i = 0;
		while ((linha = buffRead.readLine()) != null) {
			separadorLinha = linha.split(";");
			pontoRecord[i] = separadorLinha[0];
			tempoRecord[i] = separadorLinha[1];
			i++;
		}
		buffRead.close();
		dados[0] = pontoRecord;
		dados[1] = tempoRecord;
		return dados;
	}

	///Método responsável por criar os labels dos recordes, com as "colunas" posicao, tempo e pontos, para preencher a tabela fictícia
	private void CreateLabels(JPanel panel, String coluna, String[] values) {
		int x = 0;
		int y = 0;
		
		switch (coluna) {
		case "posicao":
			x = 140;
			y = 230;
			
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
				row.setFont(FontGame.Get());
				row.setBounds(x, y, 320, 40);
				row.setForeground(SystemColor.WHITE);
				row.setVisible(true);
				panel.add(row);
				
				y = y + 30;
			}
			break;

		case "tempo":
			x = 240;
			y = 230;
			
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
				row.setFont(FontGame.Get());
				row.setBounds(x, y, 320, 40);
				row.setForeground(SystemColor.WHITE);
				row.setVisible(true);
				panel.add(row);
				y = y + 30;
			}
			break;
			
		case "pontos":
			x = 410;
			y = 230;
			
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
				row.setFont(FontGame.Get());
				row.setBounds(x, y, 320, 40);
				row.setForeground(SystemColor.WHITE);
				row.setVisible(true);
				panel.add(row);
				y = y + 30;
			}
			break;
			
		default: 
			break;
		}
	}

	private class TeclaAdapter extends KeyAdapter { // Classe responsável por
													// pegar as teclas
													// pressionadas no menu.

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (!Menu.this.mFrame.logo.isVisible()) {
					Menu.this.mFrame.setLogoLabelVisible(true);
					//Menu.this.mFrame.background.setVisible(false);
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
