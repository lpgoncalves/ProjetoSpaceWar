package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLService;

import music.AllMusic;

public class Nave {

	private int idNave;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int altura;
	private int largura;
	private int nivelTiro;
	private boolean isVisivel;

	public ImageIcon referencia;

	private static int VELOCIDADE_NAVE = 2;

	private Image naveImg;
	private List<Tiro> tiros;
	
	private String pathTiroNave = "res\\sons\\shoot01.mp3";
	private AllMusic somTiroNave;

	public Nave(int idNave) {

		this.idNave = idNave;

		switch (idNave) {
		case 1:
			referencia = new ImageIcon("res\\nave.gif");// Definimos o diretorio
														// da imagem da nave.
			break;
		case 2:
			referencia = new ImageIcon("res\\nave2.gif");// Definimos o
															// diretorio da
															// imagem da nave.
			break;
		case 3:
			referencia = new ImageIcon("res\\nave3.gif");// Definimos o
															// diretorio da
															// imagem da nave.
			break;
		}

		naveImg = referencia.getImage();

		this.altura = naveImg.getHeight(null);
		this.largura = naveImg.getWidth(null);

		nivelTiro = 0;
		tiros = new ArrayList<Tiro>();

		isVisivel = true;

		// Setamos a posição inicial da nave.
		this.x = 275;
		this.y = 500;
	}

	public void mover() {

		x += dx; // somamos x com dx para que seja possivel realizar a
					// movimentação da nave.
		y += dy;

		// Os if's criados são para determinar o x limite e y limite que a nave
		// pode se locomover.
		if (this.x < 1) {
			x = 1;
		}

		if (this.x > 502) {
			x = 502;
		}

		if (this.y < 20) {
			y = 20;
		}

		if (this.y > 480) {
			y = 480;
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
		if (idNave == 1) {
			switch (nivelTiro) {
			case 0:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 8, getY() - altura + 100, 0, 1));
				break;
			case 1:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 10, getY() - altura + 100, 0, 2));
				break;
			case 2:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 10, getY() - altura + 100, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 100, 0, 2));
				break;
			case 3:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 0, getY() - altura + 100, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 100, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 50, getY() - altura + 100, 1, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 30, getY() - altura + 100, 2, 2));
				break;
			default:
				break;
			}
			
		} else if (idNave == 2) {
			switch (nivelTiro) {
			case 0:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 8, getY() - altura + 50, 0, 1));
				break;
			case 1:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 10, getY() - altura + 50, 0, 2));
				break;
			case 2:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 10, getY() - altura + 50, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 50, 0, 2));
				break;
			case 3:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 0, getY() - altura + 50, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 50, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 50, getY() - altura + 50, 1, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 30, getY() - altura + 50, 2, 2));
				break;
			default:
				break;
			}
			
		} else if (idNave == 3) {
			switch (nivelTiro) {
			case 0:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 8, getY() - altura + 70, 0, 1));
				break;
			case 1:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 10, getY() - altura + 70, 0, 2));
				break;
			case 2:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 10, getY() - altura + 70, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 70, 0, 2));
				break;
			case 3:
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 0, getY() - altura + 70, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 20, getY() - altura + 70, 0, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) - 50, getY() - altura + 70, 1, 2));
				this.tiros.add(new Tiro((int) getX() + (largura / 2) + 30, getY() - altura + 70, 2, 2));
				break;
			default:
				break;
			}
		}
	}

	public Rectangle2D getBounds() {
		return new Rectangle(x, y, largura - 20, altura - 20);
	}

	// O x começa da esquerda para direita e o y de cima para baixo.
	public void KeyPressed(KeyEvent tecla) { // Enquanto a tecla estiver sendo
												// pressionada, ele irá
												// movimenta-la conforme as
												// condições.

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			atirar();
			somTiroNave = new AllMusic(pathTiroNave);
			somTiroNave.setloop(false);
			somTiroNave.start();
		}

		if (codigo == KeyEvent.VK_UP) { // Tecla "Seta para cima" para
										// movimentar a nave para cima.
			dy = VELOCIDADE_NAVE - 4;
		}

		if (codigo == KeyEvent.VK_DOWN) {// Tecla "Seta para baixo" para
											// movimentar a nave para baixo.
			dy = VELOCIDADE_NAVE;
		}

		if (codigo == KeyEvent.VK_LEFT) {// Tecla "Seta para esquerda" para
											// movimentar a nave para esquerda.
			dx = VELOCIDADE_NAVE - 4;
		}

		if (codigo == KeyEvent.VK_RIGHT) {// Tecla "Seta para direita" para
											// movimentar a nave para direita.
			dx = VELOCIDADE_NAVE;
		}
	}

	public void KeyReleased(KeyEvent tecla) { // Quando a tecla não estiver
												// sendo mais pressionada, ele
												// irá deixar a nave parada
												// conforme as condições.

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

}
