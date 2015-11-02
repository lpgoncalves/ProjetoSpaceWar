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

import com.sun.org.apache.xerces.internal.util.NamespaceContextWrapper;

import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

public class Main_Frame extends JFrame {
	
	Font fonte =  new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	public JLabel logo;
	
	public Main_Frame() {

		
		Menu LabelMenu = new Menu(this);
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(80,250, 400, 600);
		LabelMenu.setVisible(true);
		add(LabelMenu);
	
		
		this.logo = new JLabel( new ImageIcon("res\\Warcade.png")); //Setar o logo
		logo.setBounds(130, 50 , 350, 100); //Setar posição X, Y, Largura e Altura do logo
		add(logo);
		
		JLabel background = new JLabel( new ImageIcon("res\\Menu.jpg")); //Seta a imagem de Background para o Menu Inicial
		add(background);
		
		
		setTitle("Runiverse");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);//Seta o tamanho do frame.
	    setLocationRelativeTo(null);//Responsavel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.
	       
	}
       

}
