package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {
	
	Font fonte =  new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	
	public Menu() {

		JLabel LabelMenu = new JLabel();
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(220,250, 210, 513);
		add(LabelMenu);
		
		JLabel background = new JLabel( new ImageIcon("res\\fundofase3.png"));
		background.setVisible(true);
		add(background);
		
		JButton jogar = new JButton("Jogar");
		jogar.setSelected(true);
		jogar.setForeground(corNaoSelecionado);
		jogar.setFont(fonte);
		jogar.setBounds(0, 0, 130, 20);
		LabelMenu.add(jogar);
		jogar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				jogar.setForeground(corSelecionado);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				jogar.setForeground(corNaoSelecionado);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				background.setVisible(false);
				add(new Fase()); // Inicia o Game
			}
		});
		
		JButton recordes = new JButton("Recordes");
		recordes.setFont(fonte);
		recordes.setBounds(0, 30, 130, 20);
		LabelMenu.add(recordes);
		recordes.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				recordes.setForeground(corSelecionado);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				recordes.setForeground(corNaoSelecionado);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JButton opcoes = new JButton("Opções");
		opcoes.setFont(fonte);
		opcoes.setBounds(0, 60, 130, 20);
		LabelMenu.add(opcoes);
		opcoes.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				opcoes.setForeground(corSelecionado);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcoes.setForeground(corNaoSelecionado);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
				
		setTitle("Space War");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);//Seta o tamanho do frame.
	    setLocationRelativeTo(null);//Responsavel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.
	       
	}
       
}
