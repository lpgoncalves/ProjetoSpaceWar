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
import music.AllMusic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fase extends JPanel implements ActionListener {
	private Nave nave;
	private Boss addBoss;
	
	private ImageIcon naveInvisivel = new ImageIcon("");
	public ImageIcon sound = new ImageIcon("res\\Speaker-26.png");
	public ImageIcon noSound = new ImageIcon("res\\Mute-26.png");

	private Timer timer;
	private Timer tempoShadow;
	private static Timer novosEnemies, novosEnemies2;
	private Tempo tempo;
	private Timer repetirFundo;
	private Timer novosTirosBoss;
	
	private List<Image> background = new ArrayList<Image>();;
	private List<Inimigos> inimigos;
	private List<Tiro> tiros;
	private List<Vida> addVida;
	private List<Tiro> tirosBoss;
	private List<Explosao> explosoes;
	private List<Timer> tempoExplosao;

	private boolean jogoAndamento;
	private boolean pause;
	private boolean up;
	private boolean down;
	private boolean boolFrenesi;
	private boolean gravadoRc;
	private boolean jogoApagado;
	private boolean mute = false;
	private boolean bossIsDead = false;
	private boolean eventKey = false;
	
	private int inimigoQnt = 900;
	private int inimigoFrenesiQnt = 750;
	private int delayTiroBoss = 1000;
	private double velInimigo = 1;
	private double velFrenesiInimigo = 1;
	private int velTiroBoss = 2;
	private int repetir = 0;
	private int pontos = 0;
	private int vidas = 3;
	private int intShadow;
	private int indexBack = 0;
	private int nivelTiro = 0;
	private int vidaBoss = 5;
	private int vidaInimigo = 1;
	private int tipoBoss = 0;
	private int idNave;
	private int multiPontos = 1;
	private int qntInimigosDestruido = 0;

	private String tempoString;
	private String pathMusica = "res\\sons\\Musica_Fase.mp3";
	private String pathExplosaoNave = "res\\sons\\Explosao_Nave.mp3";
	private String pathTiroBoss = "res\\sons\\8bit_Boss_Shoot.mp3";
	
	private AllMusic somFundo;
	private AllMusic somExplosao;
	private AllMusic somTiroBoss;

	private ImageIcon seta;
	private ImageIcon hundredPoints = new ImageIcon("res\\MoreHundred.png");
	private ImageIcon frenesiIMG = new ImageIcon("res\\modofrenezi.gif");
	private ImageIcon x2 = new ImageIcon("res\\x2.gif");
	private ImageIcon x3 = new ImageIcon("res\\x3.gif");
	private ImageIcon x4 = new ImageIcon("res\\x4.gif");
	
	private Menu menu;
	public JLabel somLabel;
	
	public long dataMudo;
	public long dataHundred;

	Font pontuacaoFinal = new Font(FontGame.GetFontArcade(), Font.BOLD, 18);
	Font pontimer = new Font("Century Schoolbook L", Font.PLAIN, 10);
	public Graphics2D graficos;
	private EventoMenuFinal keyGameOver;

	public Fase(Menu menu, int idNave) {
		setDoubleBuffered(true);// Responsável fazer o buffer da imagem com mais nitidez.
		setVisible(true);
		addKeyListener(new ControleGame());// Adicionando uma ação listener para  as teclas do teclado.

		this.idNave = idNave;
		this.menu = menu;

		somLabel = new JLabel();
		somLabel.setBounds(400, 40, 30, 30);
		somLabel.setVisible(false);
		add(somLabel);

		// ImageIcon referencia1 = new ImageIcon("res\\background.png");
		ImageIcon referencia1 = new ImageIcon("res\\fase1.gif");
		ImageIcon referencia2 = new ImageIcon("res\\b1.jpg");
		ImageIcon referencia3 = new ImageIcon("res\\b2.jpg");
		ImageIcon referencia4 = new ImageIcon("res\\b7.jpg");

		background.add(referencia1.getImage());
		background.add(referencia2.getImage());
		background.add(referencia3.getImage());
		background.add(referencia4.getImage());

		inimigos = new ArrayList<Inimigos>();
		addVida = new ArrayList<Vida>();
		tirosBoss = new ArrayList<Tiro>();
		explosoes = new ArrayList<Explosao>();
		tempoExplosao = new ArrayList<Timer>();

		tempo = new Tempo();

		tempoShadow = new Timer(200, piscar);

		repetirFundo = new Timer(20, new Repetir());
		repetirFundo.start();

		novosEnemies = new Timer(inimigoQnt, new criarInimigos());
		novosEnemies2 = new Timer(5000, new criarInimigos2());
		novosEnemies.start();
		novosEnemies2.start();
		
		novosTirosBoss = new Timer(delayTiroBoss, new criarTirosBoss());

		nave = new Nave(idNave);
		inicializarInimigos();
		Inimigos.SetVel(velInimigo);

		gravadoRc = false;
		jogoApagado = false;
		boolFrenesi = false;
		jogoAndamento = true;
		up = true;
		down = false;

		timer = new Timer(5, this);// Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();
		somFundo = new AllMusic(pathMusica);
		somFundo.setloop(true);
		somFundo.start();
	}

	private void StartFase() {
		this.removeKeyListener(keyGameOver);
		somFundo = new AllMusic(pathMusica);
		somFundo.setloop(true);
		somFundo.start();
		
		tempo = new Tempo();
		jogoAndamento = true;
		jogoApagado = false;
		gravadoRc = false;
		boolFrenesi = false;
		eventKey = false;
		tempoShadow.stop();
		nave.setNaveImg(nave.referencia);
		up = true;
		down = false;
		nave = new Nave(idNave);
		inicializarInimigos();
		nave.MuteNave(false);

		velFrenesiInimigo = 1;
		velInimigo = 1;
		velTiroBoss = 2;
		delayTiroBoss = 1000;
		Inimigos.SetVel(velInimigo);
		indexBack = 0;
		pontos = 0;
		vidas = 3;
		inimigoFrenesiQnt = 900;
		inimigoQnt = 1000;
		nivelTiro = 0;
		vidaInimigo = 1;
		multiPontos = 1;
		qntInimigosDestruido = 0;

		novosEnemies.restart();
		novosEnemies2.restart();
		repetirFundo.restart();
		timer.restart();
		novosTirosBoss.stop();
	}

	private void ApagaGame() throws Throwable {
		inimigos.clear();
		tiros.clear();
		addVida.clear();
		addBoss = null;
		tirosBoss.clear();
		tiros.clear();
		explosoes.clear();
		tempoExplosao.clear();
		tipoBoss = 0;

		nave.MuteNave(true);
		if(!mute)
			somFundo.close();
		tempo.pararTimer();
		timer.stop();
		novosEnemies.stop();
		novosEnemies2.stop();
		repetirFundo.stop();
		novosTirosBoss.stop();
	}
	
	private void maisPontos() {
		pontos = pontos + (10*multiPontos);
		if(pontos % 350 == 0){
			criarVidas();
		}
	}
	
	private void maisPontosBoss(){
		pontos = pontos + (100*multiPontos);
			criarVidas();
	}

	private void menosVidas() {
		vidas--;
		qntInimigosDestruido = 0;
		multiPontos = 1;
		/*if (nivelTiro > 0) {
			nivelTiro--;
			nave.setNivelTiro(nivelTiro);
		}*/
		tempo.iniciarShadow();
		intShadow = 0;
		tempoShadow.start();
	}
	
	private void maisVidas() {
		vidas++;
	}

	private void modeFrenesi(boolean frenesi) {
		if (frenesi == true) {
			velFrenesiInimigo = velFrenesiInimigo + 0.7;
			Inimigos.SetVel(velFrenesiInimigo);
			vidaBoss = vidaBoss + 10;
			novosEnemies.setDelay(inimigoFrenesiQnt);
			criarBoss();
			boolFrenesi = frenesi;
		} else {
			velInimigo = velInimigo + 0.25;
			Inimigos.SetVel(velInimigo);
			novosEnemies.setDelay(inimigoQnt);
			boolFrenesi = frenesi;
			novosTirosBoss.stop();
			addBoss = null;
				
			if (tipoBoss < 3) {
				tipoBoss++;
			} else {
				tipoBoss = 0;
			}
			if(tempo.minutos % 2 == 0){
				vidaInimigo++;
				velTiroBoss++;
				delayTiroBoss = delayTiroBoss - 200;
				novosTirosBoss.setDelay(delayTiroBoss);
			}
			mudarBack();
		}
	}

	private void mudarBack() {
		if (background.size() - 1 == indexBack) {
			indexBack = 0;
		} else {
			indexBack++;
		}
	}

	ActionListener piscar = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if (nave.isVisivel()) {
				nave.setVisivel(false);
				nave.setNaveImg(naveInvisivel);
			} else {
				nave.setVisivel(true);
				nave.setNaveImg(nave.referencia);
			}
			intShadow++;
			if (intShadow == 10) {
				nave.setNaveImg(nave.referencia);
				tempoShadow.stop();
			}
		}
	};

	private void inicializarInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {
			inimigos.remove(i);
		}
	}

	public class criarInimigos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			inimigos.add(new Inimigos(1 + (int) (820 * Math.random()), -30, 0, vidaInimigo));		
		}
	}

	public class criarInimigos2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			inimigos.add(new Inimigos(1 + (int) (850 * Math.random()), -30, 1, vidaInimigo));
		}
	}
	
	public class criarTirosBoss implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tirosBoss.add(new Tiro(addBoss.getX() + (addBoss.GetLargura() / 2) - 35,
					addBoss.getY() + addBoss.GetAltura(), 0, velTiroBoss));
			tirosBoss.add(new Tiro(addBoss.getX() + (addBoss.GetLargura() / 2) + 25,
					addBoss.getY() + addBoss.GetAltura(), 0, velTiroBoss));
			if (!mute) {
				somTiroBoss = new AllMusic(pathTiroBoss);
				somTiroBoss.setloop(false);
				somTiroBoss.start();
			}
		}
	}
	public void criarBoss() {
		addBoss = new Boss(1 + (int) (850 * Math.random()), -80, tipoBoss);
		addBoss.setVidaBoss(vidaBoss);
		novosTirosBoss.start();
	}

	public void criarVidas(){
		addVida.add(new Vida(1 + (int) (850 * Math.random()), -80));
	}

	public void paint(Graphics g) { // Responsavel por mostrar na tela todos os objetos.
		graficos = (Graphics2D) g;
		graficos.setBackground(Color.BLACK);
			graficos.drawImage(background.get(indexBack), 0, repetir, null);
			graficos.drawImage(background.get(indexBack), 0, repetir - 800, null); 
		if (jogoAndamento == true) {
			if (boolFrenesi) {
				graficos.drawImage(frenesiIMG.getImage(), 500, 730,null);
			}
			// Colocamos na tela a imagem da nave com suas evidas posições.
			graficos.drawImage(nave.getNaveImg(), nave.getX(), nave.getY(), this);
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

			for (int i = 0; i < tirosBoss.size(); i++) {
				Tiro shootBoss = (Tiro) tirosBoss.get(i);
				graficos.drawImage(shootBoss.getTiroBossImg(), shootBoss.getX(), shootBoss.getY(), this);
			}

			Timer tempoTemp;
			Explosao expTemp;

			for (int i = 0; i < tempoExplosao.size(); i++) {
				tempoTemp = tempoExplosao.get(i);
				expTemp = explosoes.get(i);
				if (expTemp.getContador() == 5) {
					tempoTemp.stop();
					tempoExplosao.remove(i);
					explosoes.remove(i);
				} else {
					graficos.drawImage(expTemp.getExplosaoImg(), expTemp.getX(), expTemp.getY(), this);
				}
			}

			if (boolFrenesi && addBoss != null)
				graficos.drawImage(addBoss.getBossImg(), (int) addBoss.getX(), (int) addBoss.getY(), this);

			ImageIcon menubar = new ImageIcon("res\\menubar.png");
			graficos.drawImage(menubar.getImage(), 0, 0, null);
			graficos.setColor(Color.white);
			graficos.setFont(pontimer);
			graficos.drawString(" " + pontos, 20, 14);
			// Caso seja menor que 10 segundos no timer ele irá colocar um zero antes (ESTÉTICA)
			if (tempo.segundos < 10) {
				graficos.drawString(" " + tempo.minutos + ":0" + tempo.segundos, 105, 14);
			} else {
				graficos.drawString(" " + tempo.minutos + ":" + tempo.segundos, 105, 14);
			}
		
			graficos.drawString(" " + vidas, 198, 14);
			if (pause == true) {
				ImageIcon pause = new ImageIcon("res\\Pause2.png");
				graficos.drawImage(pause.getImage(), 275, 275, null);
			}
			if (mute == true) {
				long end = dataMudo + 3000;
				if (System.currentTimeMillis() < end) {
					graficos.drawImage(noSound.getImage(), 850, 45, null);
				}
			} else {
				long end = dataMudo + 3000;
				if (System.currentTimeMillis() < end) {
					graficos.drawImage(sound.getImage(), 850, 45, null);
				}
			}
			
			if (bossIsDead) {
				long end = dataHundred + 1500;
				if (System.currentTimeMillis() < end) {
				graficos.drawImage(hundredPoints.getImage(), 730, 45, null);
				}
			}
			if(multiPontos > 1){
				switch(multiPontos){
					case 2:graficos.drawImage(x2.getImage(), 10, 45, null);
						break;
					case 3:graficos.drawImage(x3.getImage(), 10, 45, null);
						break;
					case 4:graficos.drawImage(x4.getImage(), 10, 45, null);
						break;
				}
			}
		} else {
			try {
				if (jogoApagado == false) {
					ApagaGame();
					jogoApagado = true;
				}
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			ImageIcon black = new ImageIcon("res\\black.png");
			graficos.drawImage(black.getImage(), 0, 0, null);
			ImageIcon gameover = new ImageIcon("res\\game-over.gif");
			graficos.drawImage(gameover.getImage(), 160, 190, null);
			graficos.setColor(Color.white);
			
			if(!eventKey){
				keyGameOver = new EventoMenuFinal();
				addKeyListener(keyGameOver);
				eventKey = true;
			}
			seta = new ImageIcon("res\\seta.gif");
			try {
				if (gravadoRc == false) {
					gravadoRc = true;
					if(tempo.segundos < 9 || tempo.segundos == 0)
						tempoString = Integer.toString(tempo.minutos)+":0"+Integer.toString(tempo.segundos);
					else
						tempoString = Integer.toString(tempo.minutos)+":"+Integer.toString(tempo.segundos);
					GravarRecord(pontos);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (up == true) {
				graficos.drawImage(seta.getImage(), 230, 515, null);
				setFocusable(true);
			}
			if (down == true) {
				graficos.drawImage(seta.getImage(), 230, 545, null);
				setFocusable(true);
			}
			graficos.setFont(pontuacaoFinal);
			graficos.drawString("Jogar Novamente", 330, 550);
			graficos.drawString("Voltar ao Menu Principal", 330, 580);
			graficos.drawString("Você conseguiu incríveis " + pontos + " pontos!", 330, 650);
			g.dispose();
		}
		g.dispose();// Irá repintar a tela com as novas atualizações.
	}

	int r;
	private int gY;
	private int gX;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (tempo.segundos == 30 && boolFrenesi == false) {
			modeFrenesi(true);
		}

		if (tempo.segundos == 0 && boolFrenesi == true) {
			modeFrenesi(false);
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
		
		for (int i = 0; i < tirosBoss.size(); i++) {
			Tiro shootsboss = (Tiro) tirosBoss.get(i);
			if (shootsboss.isVisivel()) {
				shootsboss.moverTiroBoss();
			} else {
				tirosBoss.remove(i);
			}
		}

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigos enemies = inimigos.get(i);
			if (enemies.isVisivel()) {
				if (enemies.getTipoInimigo() == 0) {
					enemies.mover();
				}
				if (enemies.getTipoInimigo() == 1) {
					switch (enemies.dir) {
					case 0:
						enemies.Baixo();
						break;
					case 1:
						enemies.Cima();
						break;
					}

					switch (enemies.dir2) {
					case 0:
						enemies.Direita();
						break;
					case 1:
						enemies.Esquerda();
						break;
					}
				}
			} else {
				inimigos.remove(i);
				// -- Som --
				if (!mute) {
					somExplosao = new AllMusic(pathExplosaoNave);
					somExplosao.setloop(false);
					somExplosao.start();
				}
				// -- Explosao --
				gX = enemies.getX();
				gY = enemies.getY();
				explosoes.add(new Explosao(gX, gY, 0));
				tempoExplosao.add(new Timer(60, explosoes.get(explosoes.size() - 1)));
				Timer tempoTemp = tempoExplosao.get(tempoExplosao.size() - 1);
				tempoTemp.start();
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

		if (boolFrenesi && addBoss != null) {
			if (addBoss.isVisivel()) {
				if ((tempo.segundos > 40) && (tempo.segundos < 50) && addBoss.getTipoBoss() != 2) {
					addBoss.velocidade_boss = 3;
				} else 
					addBoss.velocidade_boss = 0.5;
						
				switch(addBoss.getTipoBoss()){
					case 0:
						addBoss.mover(150);
						break;
					case 1:
						addBoss.mover(550);
						break;
					case 2:
						addBoss.seguirPlayer(nave);
						break;
					case 3:
						addBoss.mover(800);
						break;
				}
			} else {
				gX =(int) addBoss.getX();
				gY =(int) addBoss.getY();
				// -- Som --
				if (!mute) {
					somExplosao = new AllMusic(pathExplosaoNave);
					somExplosao.setloop(false);
					somExplosao.start();
				}
				// -- Explosao --
				explosoes.add(new Explosao(gX, gY, 1));
				tempoExplosao.add(new Timer(60, explosoes.get(explosoes.size() - 1)));
				Timer tempoTemp = tempoExplosao.get(tempoExplosao.size() - 1);
				tempoTemp.start();
				
				addBoss = null;
			}
		}
		nave.mover(); // Responsavel por fazer a ação de se movimentar da nave.
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle2D retNave = nave.getBounds();
		Rectangle2D retTiro;
		Rectangle2D retInimigos;
		Rectangle2D retBoss;
		Rectangle2D retVida;
		Rectangle2D retTiroBoss;

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigos tempInimigos = inimigos.get(i);
			retInimigos = tempInimigos.getBounds();

			if (retNave.intersects(retInimigos)) {
				if (tempo.shadow == 0) {
					menosVidas();
					tempInimigos.MenosVida();
				}
				if(tempInimigos.GetVida() == 0){
					tempInimigos.setVisivel(false);
				}
				if (vidas < 0) {
					jogoAndamento = false;
				}
			}
		}

		for (int i = 0; i < tirosBoss.size(); i++) {
			Tiro tempTiroBoss = tirosBoss.get(i);
			retTiroBoss = tempTiroBoss.getBoundsBoss();

			if (retTiroBoss.intersects(retNave)) {
				if (tempo.shadow == 0) {
					menosVidas();
				}
			}
			if (vidas < 0) {
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
					tempInimigos.MenosVida();
					tempTiro.setVisivel(false);
					if(tempInimigos.GetVida() == 0){
						tempInimigos.setVisivel(false);
						qntInimigosDestruido++;
						if(qntInimigosDestruido % 10 == 0){
							if(multiPontos < 4)
								multiPontos++;
						}
						maisPontos();
					}
				}
			}

			if (boolFrenesi && addBoss != null) {
				retBoss = addBoss.getBounds();

				if (retTiro.intersects(retBoss)) {
					tempTiro.setVisivel(false);
					addBoss.removeVidaBoss(1);

					if (addBoss.getVidaBoss() == 0) {
						if (nivelTiro < 3)
							nivelTiro++;
						
						nave.setNivelTiro(nivelTiro);
						addBoss.setVisivel(false);
						novosTirosBoss.stop();
						maisPontosBoss();
						bossIsDead = true;
						dataHundred = System.currentTimeMillis();
					}
				}
			}
		}

		if (boolFrenesi && addBoss != null) {
			retBoss = addBoss.getBounds();
			
			if (retNave.intersects(retBoss)) {
				if (tempo.shadow == 0) {
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
			if (repetir < 800)
				repetir = repetir + 1;
			else
				repetir = 0;
		}
	}

	private void GravarRecord(int pontos) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader("res\\recorde.txt"));
		String linha;
		String[] separadorLinha;
		int[] pontoRecord = new int[] { 0, 0, 0, 0, 0 };
		String[] tempoRecord = new String[] {"","","","",""};
		int i = 0;
		while ((linha = buffRead.readLine()) != null) {
			separadorLinha = linha.split(";");
			pontoRecord[i] = Integer.parseInt(separadorLinha[0]);
			tempoRecord[i] = separadorLinha[1];
			i++;
		}
		buffRead.close();

		BufferedWriter arquivo = new BufferedWriter(new FileWriter("res\\recorde.txt"));
		for (i = 0; i < 5; i++) {
			if (pontoRecord[i] > pontos) {
				linha = Integer.toString(pontoRecord[i]);
				arquivo.append(linha+";"+tempoRecord[i]);
				arquivo.newLine();
			} else {
				linha = Integer.toString(pontos);
				arquivo.append(linha+";"+tempoString);
				arquivo.newLine();
				break;
			}
		}

		while (i < 4) {
			linha = Integer.toString(pontoRecord[i]);
			arquivo.append(linha+";"+tempoRecord[i]);
			arquivo.newLine();
			i++;
		}
		arquivo.close();
	}

	public void Mute() {
		if (!pause) {
			if (mute) {
				nave.MuteNave(false);
				mute = false;
				somFundo = new AllMusic(pathMusica);
				somFundo.setloop(true);
				somFundo.start();
			} else {
				nave.MuteNave(true);
				mute = true;
				somFundo.close();
			}
			dataMudo = System.currentTimeMillis();
		}
	}

	public void Mute(boolean flag) {
		if (!flag) {
			mute = false;
			somFundo = new AllMusic(pathMusica);
			somFundo.setloop(true);
			somFundo.start();
		} else {
			mute = true;
			somFundo.close();
		}
	}
	
	// Classe responsável por  pegar as teclas pressionadas na fase.
	private class ControleGame extends KeyAdapter { 
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (pause == true) {
					pause = false;
					timer.start();
					novosEnemies.start();
					novosEnemies2.start();
					repetirFundo.start();
					tempo.comecarTimer();
					Mute(false);

				} else {
					pause = true;
					timer.stop();
					novosEnemies.stop();
					novosEnemies2.stop();
					repetirFundo.stop();
					tempo.pararTimer();
					Mute(true);
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_M) {
				Mute();
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
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up = true;
				down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down = true;
				up = false;
			}
			
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (up == true) {
					StartFase();
				}
				if (down == true) {
					setVisible(false);
					menu.MostraMenu();
				}
			}
		}
	}
}