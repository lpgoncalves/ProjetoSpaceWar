package game;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import music.AllMusic;

public class Nave {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int altura;
	private int largura;
	private int nivelTiro;
	private int alturaTiro;
	private int rastroX = 0;
	private int rastroY = 0;
	private long timeRastro = 0;
	private boolean isVisivel;
	private boolean mute = false;
	private long timeTiro = 0;

	public ImageIcon referencia;

	private static int VELOCIDADE_NAVE = 2;

	private Image naveImg;
	private List<Tiro> tiros;
	
	private String pathTiroNave = "res\\sons\\shoot01.mp3";
	private AllMusic somTiroNave;

	public Nave(int idNave) {
		switch (idNave) {
		case 1:
			referencia = new ImageIcon("res\\nave1.gif");
			alturaTiro = 30;
			break;
		case 2:
			referencia = new ImageIcon("res\\nave2.gif");
			alturaTiro = 25;
			break;
		case 3:
			referencia = new ImageIcon("res\\nave3.gif");
			alturaTiro = 25;
			break;
		}
		naveImg = referencia.getImage();

		altura = naveImg.getHeight(null);
		largura = naveImg.getWidth(null);

		nivelTiro = 0;
		tiros = new ArrayList<Tiro>();

		isVisivel = true;
		// Setamos a posição inicial da nave.
		this.x = 425;
		this.y = 680;
	}

	public void mover() {
		x += dx; // somamos x com dx para que seja possivel realizar a movimentação da nave.
		y += dy;
	
		if(timeRastro < System.currentTimeMillis()){
			rastroX = x;
			rastroY = y;
			timeRastro = System.currentTimeMillis() + 2000;
		}
		// Os if's criados são para determinar o x limite e y limite que a nave
		// pode se locomover.
		if (this.x < 1) {
			x = 1;
		}
		if (this.x > 802) {
			x = 802;
		}
		if (this.y < 20) {
			y = 20;
		}
		if (this.y > 680) {
			y = 680;
		}
	}

	public void upgradeVelocidade() {
		VELOCIDADE_NAVE++;
	}

	public void upgradeTiro() {
		nivelTiro++;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public int getRastroX(){
		return rastroY;
	}
	public int getRastroY(){
		return rastroY;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setNaveImg(ImageIcon img) {
		naveImg = img.getImage();
	}

	public Image getNaveImg() {
		return naveImg;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public void setNivelTiro(int nivel) {
		nivelTiro = nivel;
	}

	public void atirar() {
			switch (nivelTiro) {
			case 0:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 8, getY() - alturaTiro, 0, 1));
				break;
			case 1:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 10, getY() - alturaTiro, 0, 2));
				break;
			case 2:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 10, getY() - alturaTiro, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - alturaTiro, 0, 2));
				break;
			case 3:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 0, getY() - alturaTiro, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - alturaTiro, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 50, getY() - alturaTiro, 1, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 30, getY() - alturaTiro, 2, 2));
				break;
			default:
				break;
			}
	}

	public Rectangle2D getBounds() {
		return new Rectangle(x, y, largura - 20, altura - 20);
	}

	// O x começa da esquerda para direita e o y de cima para baixo.
	public void KeyPressed(KeyEvent tecla) { 
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_SPACE) {
			if(timeTiro < System.currentTimeMillis()){
				atirar();
				timeTiro = System.currentTimeMillis() + 300;
				if(!(mute)){
					somTiroNave = new AllMusic(pathTiroNave);
					somTiroNave.setloop(false);
					somTiroNave.start();
				}	
			}
		}
		if (codigo == KeyEvent.VK_UP) { // Tecla "Seta para cima" para movimentar a nave para cima.
			dy = VELOCIDADE_NAVE - 4;
		}
		if (codigo == KeyEvent.VK_DOWN) {// Tecla "Seta para baixo" para movimentar a nave para baixo.
			dy = VELOCIDADE_NAVE;
		}

		if (codigo == KeyEvent.VK_LEFT) {// Tecla "Seta para esquerda" para movimentar a nave para esquerda.
			dx = VELOCIDADE_NAVE - 4;
		}

		if (codigo == KeyEvent.VK_RIGHT) {// Tecla "Seta para direita" para movimentar a nave para direita.
			dx = VELOCIDADE_NAVE;
		}
	}

	public void KeyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public void ApagaNave() throws Throwable {
		this.finalize();
	}
	
	public void MuteNave(boolean mute){
		this.mute = mute;
	}
}