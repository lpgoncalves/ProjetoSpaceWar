package game;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Boss {
	private Nave nave;
	private Image bossImg;
	private double x;
	private double y;
	private int altura;
	private int largura;
	private int vidaBoss;
	private int tipoBoss;
	private boolean isVisivel;
	public double velocidade_boss = 0.5;
	public int dir;
	public int dir2;
	private ImageIcon referencia;
	
	public Boss(int x, int y, int tipoBoss){
		dir = 0;
		dir2 = 0;
		this.x = x;
		this.y = y;
		
	    switch(tipoBoss){
		    case 0:
		    	referencia = new ImageIcon ("res\\boss1.png");//Definimos o diretorio da imagem do boss.
		    	bossImg = referencia.getImage();
		    	break;
		    case 1:
		    	referencia = new ImageIcon ("res\\boss2.png");//Definimos o diretorio da imagem do boss.
		    	bossImg = referencia.getImage();
		    	break;
		    case 2:
		    	referencia = new ImageIcon ("res\\boss3.png");//Definimos o diretorio da imagem do boss.
		    	bossImg = referencia.getImage();
		    	System.out.println(tipoBoss);
		    	break;
		    case 3:
		    	referencia = new ImageIcon ("res\\boss-3.png");//Definimos o diretorio da imagem do boss.
		    	bossImg = referencia.getImage();
		    	break;
	    }
	  
	    this.tipoBoss = tipoBoss;
		
		this.altura = bossImg.getHeight(null);
		this.largura = bossImg.getWidth(null);
		isVisivel = true;
	}
	
	public int getTipoBoss() {
		return tipoBoss;
	}
	
	public void Direita() {
		if (this.x < Main_Frame.LARGURA_TELA - largura) {
			this.x += velocidade_boss; 
		}
		else {
			this.x -= velocidade_boss;
			this.y -= (velocidade_boss+1); 
			dir2 = 1;
		}
	}
	
	public void Esquerda() {
		if (this.x > 1) {
			this.x -= velocidade_boss;
		}
		else {
			this.x += velocidade_boss; 
			this.y += (velocidade_boss+1); 
			dir2 = 0;
		}
	}
	
	public void Baixo(int limiteTela) {	
		if (this.y < limiteTela- altura ) {
			this.y += velocidade_boss;
		}
		else {
			dir = 1;
		}
	}
	
	public void Cima() {
		if (this.y > 1) {
			this.y -= velocidade_boss; 
		}
		else {
			this.y += velocidade_boss; 
			this.x += (velocidade_boss+1);
			dir = 0;
		}
	}
	
	public void seguirPlayer() {
		if (x > nave.getX() - largura) {
			x -= velocidade_boss;
		} else {
			x += velocidade_boss;
		}
		if (y > nave.getY() - altura) {
			y -= velocidade_boss;
		} else {
			y += velocidade_boss;
		}
	} 
	
	public int GetAltura(){
		return altura;
	}
	
	public int GetLargura(){
		return largura;
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