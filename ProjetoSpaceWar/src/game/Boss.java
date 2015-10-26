package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Boss {

	private Image bossImg;
	private double x;
	private double y;
	private int altura;
	private int largura;
	private int vidaBoss;
	
	private boolean isVisivel;
	
	private static final int COMPRIMENTO_TELA = 600;
	private static final int Y_MAXIMO = 400;
	private static final int Y_MINIMO = 0;
	private static final double VELOCIDADE_BOSS = 0.5;
	
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
	
	public void Direita() {
		if (this.x == 600) {
			this.x = COMPRIMENTO_TELA;
		}
		else {
			this.x += VELOCIDADE_BOSS; 
		}
		
	}
	
	public void Esquerda() {
		if (this.x == 1) {
			this.x = COMPRIMENTO_TELA;
		}
		else {
			this.x -= VELOCIDADE_BOSS; 
		}
		
	}
	
	public void Baixo() {	
		if (this.y == 600) {
			this.y = COMPRIMENTO_TELA;
		}
		else {
			this.y += VELOCIDADE_BOSS; 
		}
		
	}
	
	public void Cima() {
		if (this.y == 1) {
			this.y = COMPRIMENTO_TELA;
		}
		else {
			this.y -= VELOCIDADE_BOSS; 
		}
		
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setVidaBoss(int vida){
		vidaBoss = vida;
	}
	
	public int getVidaBoss(){
		return vidaBoss;
	}
	
	public void removeVidaBoss(int vida){
		vidaBoss = vidaBoss - vida;
	}
	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, largura, altura);
	}
	
	
}
