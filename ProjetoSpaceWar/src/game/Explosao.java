package game;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Explosao implements ActionListener {
	private Image explosaoImg;
	private int x;
	private int y;
	private int contador;

	public Explosao(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		ImageIcon referencia;
		
		if(tipo == 0)
			referencia = new ImageIcon("res\\explosao.gif");
		else
			referencia = new ImageIcon("res\\explosao-Boss.gif");
		
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