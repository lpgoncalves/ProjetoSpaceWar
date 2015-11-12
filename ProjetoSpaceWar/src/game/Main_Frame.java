package game;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import music.AllMusic;

public class Main_Frame extends JFrame {
	Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	public JLabel logo;
	public JLabel background;
	public static final int COMPRIMENTO_TELA = 800;
	public static final int LARGURA_TELA = 900;
	
	public Main_Frame() {
		logo = new JLabel( new ImageIcon("res\\Logo.png")); //Setar o logo
		logo.setBounds(160, 155 , 645, 195); //Setar posição X, Y, Largura e Altura do logo
		add(logo);
		
		Menu LabelMenu = new Menu(this);
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(222,360, 400, 600);
		LabelMenu.setVisible(true);
		add(LabelMenu);
		
		//JLabel background = new JLabel( new ImageIcon("res\\Menu.jpg"));
		background = new JLabel( new ImageIcon("res\\fase1.gif"));//Seta a imagem de Background para o Menu Inicial
		add(background);

		setTitle("Runiverse");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(LARGURA_TELA, COMPRIMENTO_TELA);//Seta o tamanho do frame.
	    setLocationRelativeTo(null);//Responsavel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.   
	} 
}