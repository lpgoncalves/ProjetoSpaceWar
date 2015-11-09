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
import com.sun.org.apache.xpath.internal.operations.Bool;

import music.AllMusic;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

public class Main_Frame extends JFrame {
	
	Font fonte =  new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);;
	Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
	Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
	public JLabel logo;
	
	public JLabel background;
	
	public AllMusic menuMusic = new AllMusic("res\\sons\\Menu.mp3");
	
	
    public void setLogoLabelVisible(Boolean flag) {
         this.logo.setVisible(flag);
    }
	
	public Main_Frame() {

		menuMusic.setloop(true);
		menuMusic.start();
		
/*		Menu LabelMenu = new Menu(this, logo);
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(80,250, 400, 600);
		LabelMenu.setVisible(true);
		add(LabelMenu);*/
	
		
		logo = new JLabel( new ImageIcon("res\\Logo.png")); //Setar o logo
		logo.setBounds(80, 50 , 450, 200); //Setar posição X, Y, Largura e Altura do logo
		add(logo);
		
		Menu LabelMenu = new Menu(this);
		LabelMenu.setForeground(SystemColor.textHighlight);
		LabelMenu.setBounds(65,250, 400, 600);
		LabelMenu.setVisible(true);
	
		add(LabelMenu);
		
		//JLabel background = new JLabel( new ImageIcon("res\\Menu.jpg"));
		background = new JLabel( new ImageIcon("res\\fase1.gif"));//Seta a imagem de Background para o Menu Inicial
		add(background);
		
		
		setTitle("Runiverse");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);//Seta o tamanho do frame.
	    setLocationRelativeTo(null);//Responsavel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.
	       
	}
       

}
