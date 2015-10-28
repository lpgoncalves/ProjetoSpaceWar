package game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Explosao implements ActionListener {
	
	private Image explosaoImg;
	
	private int x;
	private int y;
	private int altura;
	private int largura;
	private int contador;

	public Explosao(int x, int y) {

		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("res\\explosao.gif");
		explosaoImg = referencia.getImage();

		contador = 0;
	}

	public void actionPerformed(ActionEvent e) {
		contador++;
	}
	
	public Image getExplosaoImg() {
		return explosaoImg;
	}

	public int getContador() {
		return contador;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
