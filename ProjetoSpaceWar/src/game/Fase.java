package game;

import java.awt.Color;
import java.awt.Font;
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
	private Timer novasLifes;

	private List<Inimigos> inimigos;
	private List<Tiro> tiros;
	private List<Vida> addVida;

	private boolean jogoAndamento;
	
	private int repetir = 0;
	private int pontos = 0;
	private int vidas = 1;
	
    Font pontuacaoFinal = new Font("SansSerif",Font.BOLD,15);
    Font pontimer = new Font("Century Schoolbook L", Font.PLAIN, 10);
	
	public Fase() {

		setDoubleBuffered(true);// Responsável fazer o buffer da imagem com mais nitidez.
		setFocusable(true);// Seta a nave como foco.
		addKeyListener(new TeclaAdapter());// Adicionando uma ação listener para as teclas do teclado.

		ImageIcon referencia = new ImageIcon("res\\fundofase3.png");
		background = referencia.getImage();
		
		inimigos = new ArrayList <Inimigos>();
		addVida = new ArrayList<Vida>();
		
		tempo = new Tempo();
		
		Timer RepetirFundo = new Timer(20, new Repetir());
		RepetirFundo.start();
		
		novosEnemies = new Timer(600, new criarInimigos());
		novosEnemies.start();
		
		novasLifes = new Timer(2000, new criarVidas());
		novasLifes.start();

		nave = new Nave();
		inicializarInimigos();

		jogoAndamento = true;

		timer = new Timer(5, this);// Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();

	}
	
	private void menosPontos(){
		if (pontos < 0) {
			pontos = 0;
		} else {
			pontos--;
		}
	}
	
	private void maisPontos(){
		pontos++;
	}
	
	private void menosVidas(){
		vidas--;
	}
	
	private void maisVidas(){
		vidas++;
	}

	private void inicializarInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {
			inimigos.remove(i);

		}
	}
	
	public class criarInimigos implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			inimigos.add(new Inimigos(1 + (int) (550 * Math.random()), -80));
			
		}
	}
	
	public class criarVidas implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			if ((pontos == 30) || (pontos == 60)) {
				addVida.add(new Vida(1 + (int) (550 * Math.random()), -80));				
			}
			
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
			
			for (int i = 0; i < addVida.size(); i++) {

				Vida lifes = addVida.get(i);
				graficos.drawImage(lifes.getVidaImg(), lifes.getX(), lifes.getY(), this);

			}
			
			ImageIcon menubar = new ImageIcon("res\\menubar.png");
	        graficos.drawImage(menubar.getImage(), 0, 0, null);
			graficos.setColor(Color.white);
			graficos.setFont(pontimer);
			graficos.drawString(" " + pontos, 20, 14);
			
			//--------------------------------------------------------------------------------
			//Caso seja menor que 10 segundos no timer ele irá colocar um zero antes (ESTÉTICA)
			if (tempo.segundos < 10) {		
				graficos.drawString(" " + tempo.minutos + ":0" + tempo.segundos, 105, 14);				
			} else {
				graficos.drawString(" " + tempo.minutos + ":" + tempo.segundos, 105, 14);
			  }
			//---------------------------------------------------------------------------------
			graficos.drawString(" " + vidas, 198, 14);
				
		} else {
			
			ImageIcon black = new ImageIcon("res\\black.png");
			graficos.drawImage(black.getImage(), 0, 0, null);
			ImageIcon gameover = new ImageIcon("res\\game_over.gif");
			graficos.drawImage(gameover.getImage(), 0, 100, null);
			graficos.setColor(Color.white);
	        graficos.setFont(pontuacaoFinal);
			graficos.drawString("Você conseguiu incríveis " + pontos + " pontos!", 170, 500);
		}
		
		g.dispose();// Irá repintar a tela com as novas atualizações.
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ((tempo.minutos == 0) && (tempo.segundos == 0)) {

			jogoAndamento = false;
		}

		tiros = nave.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			
			Tiro shoots = (Tiro) tiros.get(i);
			
			if (shoots.isVisivel()) {
				shoots.mover();
			} else {
				tiros.remove(i);
			}
			
		}
		
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigos enemies = inimigos.get(i);

			if (enemies.isVisivel()) {
				enemies.mover();
			} else {
				inimigos.remove(i);
			}

		}
		
		for (int i = 0; i < addVida.size(); i++) {

			Vida lifes = addVida.get(i);

			if (lifes.isVisivel()) {
				lifes.mover();
			} else {
				addVida.remove(i);
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
		Rectangle retVida;
		
		for (int i = 0; i < inimigos.size(); i++) {
			
			Inimigos tempInimigos = inimigos.get(i);
			retInimigos = tempInimigos.getBounds();
			
			if (retNave.intersects(retInimigos)) {
				
				menosVidas();
				tempInimigos.setVisivel(false);
				
				if (vidas < 0) {
					nave.setVisivel(false);
					jogoAndamento = false;
				}
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
					maisPontos();
				}
			}
			
		}
		
		for (int i = 0; i < addVida.size(); i++) {
			
			Vida tempVida = addVida.get(i);
			retVida = tempVida.getBounds();
			
			if (retNave.intersects(retVida)) {
				
				tempVida.setVisivel(false);
				maisVidas();
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

	private class TeclaAdapter extends KeyAdapter { // Classe responsável por pegar as teclas pressionadas.

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				jogoAndamento = true;
				pontos = 0;
				vidas = 3;
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
