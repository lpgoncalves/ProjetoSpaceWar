package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Vida {
	
	private Image vidaImg;
	private int x;
	private int y;
	private int altura;
	private int largura;
	
	private boolean isVisivel;
	
	private static final int COMPRIMENTO_TELA = 600;
	private static final int VELOCIDADE_VIDA = 1;
	
	public Vida (int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon ("res\\vida.gif");//Definimos o diretorio da imagem da nave.
		vidaImg = referencia.getImage();
		
		this.altura = vidaImg.getHeight(null);
		this.largura = vidaImg.getWidth(null);
		
		isVisivel = true;
	}
	
	public void mover() {
		
		if (this.y == 600){
			this.y = COMPRIMENTO_TELA;
		}
		else {
			this.y += VELOCIDADE_VIDA;
		}
			
	}
	
	public boolean isVisivel() {
	    return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getVidaImg() {
		return vidaImg;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
	}
	

}
