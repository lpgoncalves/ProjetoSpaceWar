package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Tiro {

	private Image tiroImg;
	private int x;
	private int y;
	private int altura;
	private int largura;
	
	private boolean isVisivel;
	
	private static final int COMPRIMENTO_TELA = 600;
	private static final int VELOCIDADE_TIRO = 2;
	
	public Tiro(int x, int y){
		
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("res\\tiro.png");
		tiroImg = referencia.getImage();
		
		this.largura = tiroImg.getWidth(null);
		this.altura = tiroImg.getHeight(null);
		
		isVisivel = true;
		
	}
	
	public void mover() {
	
		this.y -= VELOCIDADE_TIRO;
		if(this.y > COMPRIMENTO_TELA) {
			isVisivel = false;		
		}	
			
	}
	
	public boolean isVisivel() {
	    return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getTiroImg() {
		return tiroImg;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle2D getBounds() {
		return new Rectangle (x, y, largura, altura);
	}
	
	public void Apagatiro() throws Throwable{
		this.finalize();
	}
	
}
