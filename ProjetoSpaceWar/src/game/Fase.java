package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image background;
	private Nave nave;
	
	private Timer timer;
	private Timer novosEnemies;
	Tempo tempo;

	private List<Inimigos> inimigos;
	private List<Tiro> tiros;

	private boolean jogoAndamento;
	
	private int repetir = 0;
	
	public Fase() {

		setDoubleBuffered(true);// Responsável fazer o buffer da imagem com mais nitidez.
		setFocusable(true);// Seta a nave como foco.
		addKeyListener(new TeclaAdapter());// Adicionando uma ação listener para as teclas do teclado.

		ImageIcon referencia = new ImageIcon("res\\fundofase3.png");
		background = referencia.getImage();
		
		inimigos = new ArrayList <Inimigos>();
		
		tempo = new Tempo();
		
		Timer RepetirFundo = new Timer(20, new Repetir());
		RepetirFundo.start();
		
		novosEnemies = new Timer(600, new criarInimigos());
		novosEnemies.start();

		nave = new Nave();
		inicializarInimigos();

		jogoAndamento = true;

		timer = new Timer(5, this);// Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();

	}

	private void inicializarInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {
			inimigos.remove(i);

		}

	}
	
	public class criarInimigos implements ActionListener {
		public void actionPerformed (ActionEvent f) {
			
			inimigos.add(new Inimigos(1 + (int) (550 * Math.random()), -80));
			
		}
	}
	
	public void paint(Graphics g) { // Responsavel por mostrar na tela todos os objetos.

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, 0, repetir, null);
		graficos.drawImage(background, 0, repetir - 600, null); // Colocamos na tela o background da fase como estático, ou seja ele não irá se movimentar.

		if (jogoAndamento == true) {

			graficos.drawImage(nave.getNaveImg(), nave.getX(), nave.getY(), this);// Colocamos na tela a imagem da nave com suas devidas posições.
	
			tiros = nave.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				
				Tiro shoot = (Tiro) tiros.get(i);
				graficos.drawImage(shoot.getTiroImg(), shoot.getX(), shoot.getY(), this);
				
			}

			for (int i = 0; i < inimigos.size(); i++) {

				Inimigos enemies = inimigos.get(i);
				graficos.drawImage(enemies.getInimigosImg(), enemies.getX(), enemies.getY(), this);

			}
	
			graficos.setColor(Color.white);
			graficos.drawString("Inimigos Restantes: " + inimigos.size(), 10, 15);
			graficos.drawString("Tempo: " + tempo.minutos + ":" + tempo.segundos, 300, 15);

		} else {
			
			ImageIcon black = new ImageIcon("res\\black.png");
			graficos.drawImage(black.getImage(), 0, 0, null);
			ImageIcon gameover = new ImageIcon("res\\game_over.gif");
			graficos.drawImage(gameover.getImage(), 0, 100, null);
		}
		g.dispose();// Irá repintar a tela com as novas atualizações.
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ((tempo.minutos == 0) && (tempo.segundos == 0)) {

			jogoAndamento = false;
			timer.start();
		}

		tiros = nave.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			
			Tiro shoot = (Tiro) tiros.get(i);
			
			if (shoot.isVisivel()) {
				shoot.mover();
			} else {
				tiros.remove(i);
			}
			
		}
		
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigos in = inimigos.get(i);

			if (in.isVisivel()) {
				in.mover();
			} else {
				inimigos.remove(i);
			}

		}

		nave.mover(); //Responsavel por fazer a ação de se movimentar da nave.
		checarColisoes();
		repaint();
	}
	
	public void checarColisoes() {
		
		Rectangle retNave = nave.getBounds();
		Rectangle retTiro;
		Rectangle retInimigos;
		
		for (int i = 0; i < inimigos.size(); i++) {
			
			Inimigos tempInimigos = inimigos.get(i);
			retInimigos = tempInimigos.getBounds();
			
			if (retNave.intersects(retInimigos)) {
				
				nave.setVisivel(false);
				tempInimigos.setVisivel(false);
				jogoAndamento = false;
			}
			
		}
		
		tiros = nave.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			
			Tiro tempTiro = tiros.get(i);
			retTiro = tempTiro.getBounds();
			
			for (int j = 0; j < inimigos.size(); j++) {
				
				Inimigos tempInimigos = inimigos.get(j);
				retInimigos = tempInimigos.getBounds();
				
				if (retTiro.intersects(retInimigos)) {
					
					tempInimigos.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
			
		}
		
	}
	
	private class Repetir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// REPETIR FUNDO
			if (repetir < 600)
				repetir = repetir + 1;
			else
				repetir = 0;
		}
	}

	private class TeclaAdapter extends KeyAdapter { // Classe responsavel por pegas as teclas pressionadas.

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				jogoAndamento = true;
				tempo = new Tempo();
				nave = new Nave();
				inicializarInimigos();
			}
			
			nave.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.KeyReleased(e);
		}

	}

}
