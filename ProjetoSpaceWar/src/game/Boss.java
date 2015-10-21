package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss {

	private Image bossImg;
	private int x;
	private int y;
	private int altura;
	private int largura;
	
	private boolean isVisivel;
	
	private static final int COMPRIMENTO_TELA = 600;
	private static final int Y_MAXIMO = 400;
	private static final int Y_MINIMO = 0;
	private static final double VELOCIDADE_BOSS = 2;
	
	private static int contador = 0;
	
	public Boss(int x, int y){
		
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon ("res\\boss.png");//Definimos o diretorio da imagem do boss.
	    bossImg = referencia.getImage();
		
		this.altura = bossImg.getHeight(null);
		this.largura = bossImg.getWidth(null);
		
		isVisivel = true;
		
	}
	
	public void mover() {
		if ((this.y == 600 || this.x == 600)){
			this.y = COMPRIMENTO_TELA;
			this.x = COMPRIMENTO_TELA;
		}
		else {
			this.y += VELOCIDADE_BOSS;
		}
/*		if (this.y == 400 ) {
			this.y = Y_MAXIMO;
			this.y -= VELOCIDADE_BOSS;
		}
		else if (this.y == 0) {
			this.y = Y_MINIMO;
			this.y += VELOCIDADE_BOSS;
		}
		else if (this.x == 600 ) {
			this.x = COMPRIMENTO_TELA;
			this.x -= VELOCIDADE_BOSS;
		}
		else if (this.x == 0) {
			this.x = Y_MINIMO;
			this.x += VELOCIDADE_BOSS;
		} */
	}
	
	public boolean isVisivel() {
	    return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getBossImg() {
		return bossImg;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura - 30, altura - 20);
	}
	
	
}
