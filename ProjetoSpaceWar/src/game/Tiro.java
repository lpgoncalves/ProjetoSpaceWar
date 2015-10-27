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
	private static int VELOCIDADE_TIRO;
	
	public Tiro(int x, int y, int nivel){
		
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("res\\tiro.png");
		tiroImg = referencia.getImage();
		
		switch(nivel){
			case 0:
				nivel_0();
				break;
			case 1:
				nivel_1();
				break;
			default:
				nivel_1();
				break;
		}
		
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
	
	public void nivel_0(){
		VELOCIDADE_TIRO = 1;
	}
	
	public void nivel_1(){
		VELOCIDADE_TIRO = 4;
	}
}
