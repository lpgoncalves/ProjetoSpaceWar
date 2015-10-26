package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.TimerTask;

public class Fase extends JPanel implements ActionListener {

	private List<Image> background = new ArrayList<Image>();;
	private Nave nave;
	
	ImageIcon naveVisivel = new ImageIcon ("res\\nave.gif");
	ImageIcon naveInvisivel = new ImageIcon ("");
	
	private Timer timer;
	private Timer tempoShadow;
	private Timer novosEnemies;
	Tempo tempo;
	private Timer novasLifes;
	private Timer novoBoss;
	private Timer repetirFundo;
	private Timer mudarBack;

	private List<Inimigos> inimigos;
	private List<Tiro> tiros;
	private List<Vida> addVida;
	private List<Boss> addBoss;

	private boolean jogoAndamento;
	private boolean pause;
	private boolean up;
	private boolean down;
	
	private int repetir = 0;
	private int pontos = 0;
	private int vidas = 1;
	private int intShadow;
	private int indexBack = 0;

	
	private ImageIcon seta;

	
	private JLabel menu;

	
    Font pontuacaoFinal = new Font(FontGame.GetFontArcade(),Font.BOLD,15);
    Font pontimer = new Font("Century Schoolbook L", Font.PLAIN, 10);
    Graphics2D graficos;
	
	public Fase(JLabel menu) {
		
		setDoubleBuffered(true);// Respons�vel fazer o buffer da imagem com mais nitidez.
		setFocusable(true);// Seta a nave como foco.
		addKeyListener(new TeclaAdapter());// Adicionando uma a��o listener para as teclas do teclado.

		this.menu = menu;
		
		ImageIcon referencia1 = new ImageIcon("res\\background.png");
		ImageIcon referencia2 = new ImageIcon("res\\background2.jpg");
		ImageIcon referencia3 = new ImageIcon("res\\background3.jpg");
		ImageIcon referencia4 = new ImageIcon("res\\background4.png");
		ImageIcon referencia5 = new ImageIcon("res\\background5.png");
		
		background.add(referencia1.getImage());
		background.add(referencia2.getImage());
		background.add(referencia3.getImage());
		background.add(referencia4.getImage());
		background.add(referencia5.getImage());
		
		
		inimigos = new ArrayList <Inimigos>();
		addBoss = new ArrayList<Boss>();
		addVida = new ArrayList<Vida>();
		
		tempo = new Tempo();
		
		mudarBack = new Timer(30000,mudarBackground);
		mudarBack.start();
		
		tempoShadow = new Timer(200,piscar);
		
		repetirFundo = new Timer(20, new Repetir());
		repetirFundo.start();
		
		novosEnemies = new Timer(600, new criarInimigos());
		novosEnemies.start();
		
		novasLifes = new Timer(10000, new criarVidas());
		novasLifes.start();
		
		novoBoss = new Timer(10000, new criarBoss());
		novoBoss.start();

		nave = new Nave();
		inicializarInimigos();

		jogoAndamento = true;
		up = true;
		down = false;

		timer = new Timer(5, this);// Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();

	}
	
	private void StartFase() {
		jogoAndamento = true;
		tempoShadow.stop();
		nave.setNaveImg(naveVisivel);
		up = true;
		down = false;
		nave = new Nave();
		inicializarInimigos();
		indexBack = 0;
		pontos = 0;
		vidas = 1;
		novosEnemies.restart();
		novoBoss.restart();
		novasLifes.restart();
		repetirFundo.restart();
		timer.restart();
		mudarBack.restart();
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
			tempo.iniciarShadow();
			intShadow = 0;
			tempoShadow.start();	

	}
	
	ActionListener mudarBackground = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	  System.out.println("teste");
	    	  if(background.size() - 1 == indexBack){
	    		  indexBack = 0;  
	    	  }else{
	    		  indexBack++;
	    	  }
	    	  
	      }
	};
	
	ActionListener piscar = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	  if(nave.isVisivel()){
	    		  nave.setVisivel(false);
	    		  nave.setNaveImg(naveInvisivel);
	    	  }
	    	  else{
	    		  nave.setVisivel(true);
	    		  nave.setNaveImg(naveVisivel);
	    	  }
	    	  intShadow++;
	    	  if(intShadow == 10){
	    		  nave.setNaveImg(naveVisivel);
	    		  tempoShadow.stop();
	    	  }
	      }
	  };
	
	private void maisVidas(){
		vidas++;
	}
	
	private void inicializarInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {
			inimigos.remove(i);

		}
	}
	
	private void ApagaGame() throws Throwable{
		
		inimigos.clear();	
		tiros.clear();
		addVida.clear();
		addBoss.clear();
		
		mudarBack.stop();
		timer.stop();
		novosEnemies.stop();
		novasLifes.stop();
		novoBoss.stop();
		repetirFundo.stop();
		
	}
	
	public class criarInimigos implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			inimigos.add(new Inimigos(1 + (int) (550 * Math.random()), -80));
			
		}
	}
	
	public class criarBoss implements ActionListener {
		public void actionPerformed (ActionEvent e) {
				addBoss.add(new Boss(1 + (int) (550 * Math.random()), -80));		
				Boss tempBoss = addBoss.get(addBoss.size()-1);
				tempBoss.setVidaBoss(5);
		}
	}
	
	public class criarVidas implements ActionListener {
		public void actionPerformed (ActionEvent e) {
				addVida.add(new Vida(1 + (int) (550 * Math.random()), -80));				
	
		}
	}
	
	public void paint(Graphics g) { // Responsavel por mostrar na tela todos os objetos.

		
		graficos = (Graphics2D) g;
		graficos.setBackground(Color.BLACK);  
		graficos.drawImage(background.get(indexBack), 0, repetir, null);
		graficos.drawImage(background.get(indexBack), 0, repetir - 600, null); // Colocamos na tela o background da fase como est�tico, ou seja ele n�o ir� se movimentar.

		if (jogoAndamento == true) {

			graficos.drawImage(nave.getNaveImg(), nave.getX(), nave.getY(), this);// Colocamos na tela a imagem da nave com suas devidas posi��es.
	
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
			
			for (int i = 0; i < addBoss.size(); i++) {

				Boss bosses = addBoss.get(i);
				
				graficos.drawImage(bosses.getBossImg(), (int) bosses.getX(), (int) bosses.getY(), this);

			}
			
			ImageIcon menubar = new ImageIcon("res\\menubar.png");
	        graficos.drawImage(menubar.getImage(), 0, 0, null);
			graficos.setColor(Color.white);
			graficos.setFont(pontimer);
			graficos.drawString(" " + pontos, 20, 14);
			
			//--------------------------------------------------------------------------------
			//Caso seja menor que 10 segundos no timer ele ir� colocar um zero antes (EST�TICA)
			if (tempo.segundos < 10) {		
				graficos.drawString(" " + tempo.minutos + ":0" + tempo.segundos, 105, 14);				
			} else {
				graficos.drawString(" " + tempo.minutos + ":" + tempo.segundos, 105, 14);
			  }
			//---------------------------------------------------------------------------------
			graficos.drawString(" " + vidas, 198, 14);
			
			if (pause == true) {
				ImageIcon pause = new ImageIcon("res\\pause.png");
				graficos.drawImage(pause.getImage(), 275, 275, null);
			}
			
				
		} else {
			
			try {
				ApagaGame();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ImageIcon black = new ImageIcon("res\\black.png");
			graficos.drawImage(black.getImage(), 0, 0, null);
			ImageIcon gameover = new ImageIcon("res\\game_over.gif");
			graficos.drawImage(gameover.getImage(), 0, 100, null);
			graficos.setColor(Color.white);
			
			addKeyListener(new EventoMenuFinal());
			seta = new ImageIcon("res\\seta.gif");
			ImageIcon preto = new ImageIcon("res\\preto.png");
			
            if (up == true) {
			graficos.drawImage(seta.getImage(), 80, 457, null);
			setFocusable(true);
            } else {
            	
            }
            
            if (down == true) {
            graficos.drawImage(seta.getImage(), 80, 476, null);
			setFocusable(true);
            } else {
            	
            }
			
	        graficos.setFont(pontuacaoFinal);
	        graficos.drawString("Jogar Novamente", 170, 490);
	        graficos.drawString("Voltar ao Menu Principal", 170, 510);
			graficos.drawString("Voc� conseguiu incr�veis " + pontos + " pontos!", 170, 550);
			
			g.dispose();
		}
		
		g.dispose();// Ir� repintar a tela com as novas atualiza��es.
	}
	int r;
	@Override
	public void actionPerformed(ActionEvent e) {
			
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
		
		for (int i = 0; i < addBoss.size(); i++) {

			Boss bosses = addBoss.get(i);

			if (bosses.isVisivel()) {
				bosses.Baixo();
			} else {
				addBoss.remove(i);
			
			}

		}

		nave.mover(); //Responsavel por fazer a a��o de se movimentar da nave.
		checarColisoes();
		repaint();
		
	}
	
	public void checarColisoes() {
		
		Rectangle2D retNave = nave.getBounds();
		Rectangle2D retTiro;
		Rectangle2D retInimigos;
		Rectangle2D retBoss;
		Rectangle2D retVida;
		
		for (int i = 0; i < inimigos.size(); i++) {
			
			Inimigos tempInimigos = inimigos.get(i);
			retInimigos = tempInimigos.getBounds();
			
			if (retNave.intersects(retInimigos)) {
				if(tempo.shadow == 0){
					menosVidas();
					tempInimigos.setVisivel(false);
				}
				if (vidas < 0) {
					
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
			
			for (int k = 0; k < addBoss.size(); k++) {
				
				Boss tempBoss = addBoss.get(k);
				retBoss = tempBoss.getBounds();
				
				if (retTiro.intersects(retBoss)) {
					
					tempTiro.setVisivel(false);
					tempBoss.removeVidaBoss(1);
					
					if (tempBoss.getVidaBoss() == 0) {
						tempBoss.setVisivel(false);
						
					}
				}
			
		    }
		}
		
		for (int i = 0; i < addBoss.size(); i++) {
			
			Boss tempBoss = addBoss.get(i);
			retBoss = tempBoss.getBounds();
			
			if (retNave.intersects(retBoss)) {
				
				if(tempo.shadow == 0){
					menosVidas();
				}
				if (vidas < 0) {
					
					jogoAndamento = false;
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

	private class TeclaAdapter extends KeyAdapter { // Classe respons�vel por pegar as teclas pressionadas na fase.
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				jogoAndamento = true;
				pontos = 0;
				vidas = 1;
				tempo = new Tempo();
				nave = new Nave();
				novosEnemies.start();
				novasLifes.start();
				inicializarInimigos();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (pause == true) {
					pause = false;
					timer.start();
					novosEnemies.start();
					novasLifes.start();
					repetirFundo.start();
                    tempo.comecarTimer();
					
				} else {
					pause = true;
					timer.stop();
					novosEnemies.stop();
					novasLifes.stop();
					repetirFundo.stop();
					tempo.pararTimer();
				
				}
			}
			
			nave.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.KeyReleased(e);
		}

	}
	
	private class EventoMenuFinal extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			 
			if (e.getKeyCode() == KeyEvent.VK_W) {
				  up = true;
				  down = false;
				  }
			  
			  if (e.getKeyCode() == KeyEvent.VK_S) {
				  down = true;
				  up = false;
				  } 
			  
			  if(e.getKeyCode() == KeyEvent.VK_ENTER){
				  
				  if (up == true) {
					  
					  StartFase();
				  	}
				  
				  if (down == true) {
					  setVisible(false);
					  menu.setVisible(true);
				  	}
				   
			  	}
			}
	}

}
