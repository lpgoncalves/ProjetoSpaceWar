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

	private List<Inimigos> inimigos;
	private List<Tiro> tiros;

	private boolean jogoAndamento;
	
	private int[][] coordenadas = { { 400, 29 }, { 500, 59 }, { 300, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } 
	};
	//int rx = (int) (1 + Math.random() * 400);  
	//int ry = (int) (-1 - Math.random() * -400);
	//private int[][] coordenadas = { { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry }, { rx, ry }, { rx, ry },
	//		{ rx, ry }, { rx, ry }, { rx, ry } };
	
	public Fase() {

		setDoubleBuffered(true);// Responsável fazer o buffer da imagem com mais nitidez.

		setFocusable(true);// Seta a nave como foco.
		addKeyListener(new TeclaAdapter());// Adicionando uma ação listener para as teclas do teclado.

		ImageIcon referencia = new ImageIcon("res\\fundofase.png");
		background = referencia.getImage();

		nave = new Nave();
		inicializarInimigos();

		jogoAndamento = true;

		timer = new Timer(5, this);// Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();

	}

	public void inicializarInimigos() {
		
		inimigos = new ArrayList<Inimigos>();
		
		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigos(coordenadas[i][0], coordenadas[i][1]));

		}
	}
	
	public void paint(Graphics g) { // Responsavel por mostrar na tela todos os objetos.

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, -400, -500, null); // Colocamos na tela o background da fase como estático, ou seja ele não irá se movimentar.

		if (jogoAndamento) {

			graficos.drawImage(nave.getNaveImg(), nave.getX(), nave.getY(), this);// Colocamos na tela a imagem da nave com suas devidas posições.
	
			tiros = nave.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				
				Tiro shoot = (Tiro) tiros.get(i);
				graficos.drawImage(shoot.getImagem(), shoot.getX(), shoot.getY(), this);
				
			}

			for (int i = 0; i < inimigos.size(); i++) {

				Inimigos in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}
	
			graficos.setColor(Color.white);
			graficos.drawString("Inimigos Restantes:" + inimigos.size(), 10, 15);

		} else {
			
			ImageIcon gameover = new ImageIcon("res\\game_over.gif");
			graficos.drawImage(gameover.getImage(), 0, 0, null);
		}
		g.dispose();// Irá repintar a tela com as novas atualizações.
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (inimigos.size() == 0) {

			jogoAndamento = false;
		}

		tiros = nave.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			
			Tiro st = (Tiro) tiros.get(i);
			
			if (st.isVisivel()) {
				st.mover();
			} else {
				tiros.remove(i);
			}
			
		}
		
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigos enemies = (Inimigos) inimigos.get(i);

			if (enemies.isVisivel()) {
				enemies.mover();
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
				retInimigos = tempTiro.getBounds();
				
				if (retTiro.intersects(retInimigos)) {
					
					tempInimigos.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
			
		}
		
	}

	private class TeclaAdapter extends KeyAdapter { // Classe responsavel por pegas as teclas pressionadas.

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				jogoAndamento = true;
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
