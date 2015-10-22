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

		
		SubMenu LabelMenu = new SubMenu(this);
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(220,250, 210, 513);
		LabelMenu.setVisible(true);
		add(LabelMenu);
		
		JLabel background = new JLabel( new ImageIcon("res\\fundofase3.png"));

		add(background);

				
		setTitle("Space War");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);//Seta o tamanho do frame.
	    setLocationRelativeTo(null);//Responsavel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.
	       
	}
       
}
