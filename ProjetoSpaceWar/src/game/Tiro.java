package game;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Tiro {
	private Image tiroImg;
	private Image tiroBossImg;
	private int x;
	private int y;
	private int direcao;
	private int altura, alturaBoss;
	private int largura, larguraBoss;
	private boolean isVisivel;
	private int VELOCIDADE_TIRO;
	
	public Tiro(double x, double y, int nivel,int velocidade){
		
		this.x = (int) x;
		this.y = (int) y;
		VELOCIDADE_TIRO = velocidade;
		
		ImageIcon referencia1 = new ImageIcon("res\\tiro.png");
		ImageIcon referencia2 = new ImageIcon("res\\tiroboss.png");
		tiroImg = referencia1.getImage();
		tiroBossImg = referencia2.getImage();

		direcao = nivel;

		this.largura = tiroImg.getWidth(null);
		this.altura = tiroImg.getHeight(null);
		this.larguraBoss = tiroBossImg.getWidth(null);
		this.alturaBoss = tiroBossImg.getHeight(null);
		isVisivel = true;
	}
	
	public void mover() {
		switch(direcao){
			case 0: this.y -= VELOCIDADE_TIRO;
					if(this.y > Main_Frame.COMPRIMENTO_TELA) {
						isVisivel = false;		
					}	
					break;
			case 1:
					this.y -= VELOCIDADE_TIRO;
					this.x -= VELOCIDADE_TIRO;
					if(this.y > Main_Frame.COMPRIMENTO_TELA) {
						isVisivel = false;		
					}
					break;
			case 2:
				this.y -= VELOCIDADE_TIRO;
				this.x += VELOCIDADE_TIRO;
				if(this.y > Main_Frame.COMPRIMENTO_TELA) {
					isVisivel = false;		
				}
				break;
				default:
					break;
		}
	}
	
	public void moverTiroBoss() {
		this.y += VELOCIDADE_TIRO;
		if(this.y > Main_Frame.COMPRIMENTO_TELA) {
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
	
	public Image getTiroBossImg() {
		return tiroBossImg;
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
	
	public Rectangle2D getBoundsBoss() {
		return new Rectangle (x, y, larguraBoss, alturaBoss);
	}
	
	public void Apagatiro() throws Throwable{
		this.finalize();
	}
}