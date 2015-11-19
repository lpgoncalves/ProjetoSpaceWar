package game;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.Main_Frame;
import music.AllMusic;

public class Menu extends JLabel {
	private JLabel jogar;
	private JLabel recordes;
	private JLabel ajuda;
	private JLabel returnLabel;
	private JLabel menuAjuda;
	private JLabel menuRecordes;
	private JLabel row;
	private JPanel panelRecordes;
	private Main_Frame mFrame;
	private AllMusic selectOption;
	private String pathSelectOption = "res\\sons\\Menu_Select.mp3";
	public AllMusic menuMusic = new AllMusic("res\\sons\\Menu.mp3");
	
	public Menu(Main_Frame frame) {
		
		menuMusic.setloop(true);
		menuMusic.start();
		
		mFrame = frame;
		mFrame.addKeyListener(new VoltarEsc());

		panelRecordes = new JPanel();
		panelRecordes.setLayout(null);
		panelRecordes.setBounds(0, 0, 900, 800);
		panelRecordes.setVisible(false);
		panelRecordes.setOpaque(false);

		menuAjuda = new JLabel(new ImageIcon("res\\Menu Inicial\\menu-ajuda.png"));
		menuAjuda.setBounds(55, 35, 800, 700);
		menuAjuda.setVisible(false);
		frame.add(menuAjuda);

		menuRecordes = new JLabel(new ImageIcon("res\\Menu Inicial\\menu-Recovered.png"));
		menuRecordes.setBounds(200, 160, 500, 500);
		menuRecordes.setVisible(true);
		frame.add(panelRecordes);

		returnLabel = new JLabel("Pressione ESC para voltar");
		returnLabel.setBounds(700, 650, 150, 50);
		returnLabel.setForeground(SystemColor.WHITE);
		returnLabel.setVisible(false);
		frame.add(returnLabel);

		SelectNave panel = new SelectNave(frame, this);
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setVisible(false);
		frame.add(panel);

		jogar = new JLabel("");
		jogar.setIcon(new ImageIcon("res\\Menu Inicial\\iniciar.png"));
		jogar.setBounds(100, 0, 310, 40);
		add(jogar);

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
		
		recordes = new JLabel("");
		recordes.setIcon(new ImageIcon("res\\Menu Inicial\\recordes.png"));
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
				mFrame.logo.setVisible(false);
				setVisible(false);
				panelRecordes.setVisible(true);
				returnLabel.setVisible(true);
				String[][] pontuacao = new String[2][5];
				try {
					pontuacao = GetRecord();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				panelRecordes.add(menuRecordes);
				CreateLabels(panelRecordes, "posicao",  new String[]{ "1º", "2º", "3º","4º","5º" }); 			/// QUANTIDADE DE REGISTROS (LINHAS)
				CreateLabels(panelRecordes, "tempo",  pontuacao[1]); 	/// TEMPO
				CreateLabels(panelRecordes, "pontos",  pontuacao[0]);		/// PONTUAÇÃO
			}
		});

		ajuda = new JLabel("");
		ajuda.setIcon(new ImageIcon("res\\Menu Inicial\\ajuda.png"));
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
				mFrame.logo.setVisible(false);
				setVisible(false);
				menuAjuda.setVisible(true);
				returnLabel.setVisible(true);
			}
		});
	}

	public void MostraMenu() {
		setVisible(true);
		mFrame.requestFocusInWindow();
		mFrame.background.setVisible(true);
		menuMusic = new AllMusic("res\\sons\\Menu.mp3");
		menuMusic.setloop(true);
		menuMusic.start();
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
			x = 272;
			y = 370;
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
				row.setBounds(x, y, 320, 40);
				row.setForeground(SystemColor.WHITE);
				row.setVisible(true);
				panel.add(row);
				y = y + 30;
			}
			break;
		case "tempo":
			x = 365;
			y = 370;
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
				row.setBounds(x, y, 320, 40);
				row.setForeground(SystemColor.WHITE);
				row.setVisible(true);
				panel.add(row);
				y = y + 30;
			}
			break;
		case "pontos":
			x = 535;
			y = 370;
			for (int i = 0; i < values.length; i++) {
				row = new JLabel(values[i]);
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

	private class VoltarEsc extends KeyAdapter { 
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (!Menu.this.mFrame.logo.isVisible()) {
					panelRecordes.removeAll();
					mFrame.logo.setVisible(true);
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
	
}
