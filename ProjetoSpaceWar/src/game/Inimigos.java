package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigos {

	private Image inimigoImg;
	private int x;
	private int y;
	private int altura;
	private int largura;
	
	private boolean isVisivel;
	
	private static final int COMPRIMENTO_TELA = 600;
	private static final double VELOCIDADE_INIMIGO = 1;
	//private int VELOCIDADE_X = (int)((Math.random() * 4) - 2); 
	
	private static int contador = 0;
	
	public Inimigos(int x, int y){
		
		this.x = x;
		this.y = y;
		
		ImageIcon referencia;

		if(contador++ % 3 == 0){
			referencia = new ImageIcon("res\\inimigo2.png");
		
		} else {
			
			referencia = new ImageIcon("res\\inimigo2.png");
		}
		
		inimigoImg = referencia.getImage();
		
		this.altura = inimigoImg.getHeight(null);
		this.largura = inimigoImg.getWidth(null);
		
		isVisivel = true;
		
	}
	
	public void mover() {
	
		if ((this.y == 600 || this.x == 600)){
			this.y = COMPRIMENTO_TELA;
			this.x = COMPRIMENTO_TELA;
		}
		else {
			this.y += VELOCIDADE_INIMIGO;
		}
		//-----------MOVIMENTAÇÃO NO VETOR X-----------------
		/*else if (VELOCIDADE_X == 0) {
			this.y += VELOCIDADE_INIMIGO;
			this.x += 0;
		}
		else if (VELOCIDADE_X == -1) {
			this.y += VELOCIDADE_INIMIGO;
			this.x += -0.1;
		}
		else {
			this.y += VELOCIDADE_INIMIGO;
			this.x += 0.1;
		}*/
		//-----------------------------------------------------
	}
	
	public boolean isVisivel() {
	    return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getInimigosImg() {
		return inimigoImg;
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
