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

public class Menu extends JLabel{
	
	Font fonte =  new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	public Menu(JFrame frame){
		

		JButton jogar = new JButton("Jogar");
		jogar.setForeground(corNaoSelecionado);
		jogar.setFont(fonte);
		jogar.setBounds(0, 0, 130, 20);
		add(jogar);
		
		jogar.addMouseListener(new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e) {
				jogar.setForeground(corSelecionado);
			}
		
			public void mouseExited(MouseEvent e) {
				jogar.setForeground(corNaoSelecionado);
			}
			
			public void mouseClicked(MouseEvent e) {			
				        Fase game = new Fase();
				        frame.add(game);
						setVisible(false);
						game.setFocusable(true);
			}
		});

		
		JButton recordes = new JButton("Recordes");
		recordes.setFont(fonte);
		recordes.setBounds(0, 30, 130, 20);
		add(recordes);
		recordes.addMouseListener(new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e) {
				recordes.setForeground(corSelecionado);
			}
			
			public void mouseExited(MouseEvent e) {
				recordes.setForeground(corNaoSelecionado);
			}
			
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		JButton opcoes = new JButton("Opções");
		opcoes.setFont(fonte);
		opcoes.setBounds(0, 60, 130, 20);
		add(opcoes);
		opcoes.addMouseListener(new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e) {
				opcoes.setForeground(corSelecionado);
			}
			
			public void mouseExited(MouseEvent e) {
				opcoes.setForeground(corNaoSelecionado);
			}
			
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		
		
	}

}
