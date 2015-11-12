package game;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Inimigos {
	private int vidaInimigo;
	private Image inimigoImg;
	private int x;
	private int y;
	private int altura;
	private int largura;
	private boolean isVisivel;
	private static double velocidade_inimigo = 1;
	public int dir;
	public int dir2;
	private int tipoInimigo;
	
	public Inimigos(int x, int y, int tipoInimigo, int vida){
		dir = 0;
		this.x = x;
		this.y = y;
		vidaInimigo = vida;
		ImageIcon referencia;
	
		switch(tipoInimigo){
	    case 0:
	    	referencia = new ImageIcon ("res\\inimigo5.png");//Definimos o diretorio da imagem da nave inimiga.
	    	inimigoImg = referencia.getImage();
	    	break;
	    case 1:
	    	referencia = new ImageIcon ("res\\asteroide.gif");//Definimos o diretorio da imagem do asteroide
	    	inimigoImg = referencia.getImage();
	    	break;
    }
		this.tipoInimigo = tipoInimigo;
		this.altura = inimigoImg.getHeight(null);
		this.largura = inimigoImg.getWidth(null);
		isVisivel = true;
	}
	
	public void MenosVida(){
		vidaInimigo--;
	}
	
	public int GetVida(){
		return vidaInimigo;
	}
	
	public static void SetVel(double velocidade){
		velocidade_inimigo = velocidade;
	}
	
	public int getTipoInimigo () {
		return tipoInimigo;
	}
	
	public void mover() {
		if ((this.y == Main_Frame.COMPRIMENTO_TELA || this.x == Main_Frame.COMPRIMENTO_TELA)){
			this.y = Main_Frame.COMPRIMENTO_TELA;
			this.x = Main_Frame.COMPRIMENTO_TELA;
		}
		else {
			this.y += velocidade_inimigo;
		}
	}
	
	public void Direita() {
		if (this.x < Main_Frame.COMPRIMENTO_TELA - largura) {
			this.x += velocidade_inimigo; 
		}
		else {
			this.x -= velocidade_inimigo; 
			dir2 = 1;
		}
	}
	
	public void Esquerda() {
		if (this.x > 1) {
			this.x -= velocidade_inimigo;
		}
		else {
			this.x += velocidade_inimigo; 
			dir2 = 0;
		}
	}
	
	public void Baixo() {	
		if (this.y < Main_Frame.COMPRIMENTO_TELA - altura ) {
			this.y += velocidade_inimigo;
		}
		else {
			this.y -= velocidade_inimigo; 
			dir = 1;
		}
	}
	
	public void Cima() {
		if (this.y > 1) {
			this.y -= velocidade_inimigo; 
		}
		else {
			dir = 0;
		}
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
	
	public Rectangle2D getBounds() {
		return new Rectangle (x, y, largura - 30, altura - 20);
	}
}
