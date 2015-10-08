package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	
	private Image background;
	private Nave nave;
	private Timer timer;
	
	public Fase(){
		
		setDoubleBuffered(true);//Responsável fazer o buffer da imagem com mais nitidez.
		setFocusable(true);//Seta a nave como foco.
		addKeyListener(new TeclaAdapter());//Adicionando uma ação listener para as teclas do teclado.
		
		ImageIcon referencia = new ImageIcon ("res\\fundofase.png");
		background = referencia.getImage();
		
		nave = new Nave();
		
		timer = new Timer(5, this);//Responsavel por chamar o action performed, chamando-o de 5 em 5 milisegundos.
		timer.start();
		
	}
	
	public void paint (Graphics g){ //Responsavel por mostrar na tela todos os objetos.
		
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, 0, 0, null); //Colocamos na tela o background da fase como estático, ou seja ele não irá se movimentar.
		graficos.drawImage(nave.getNaveImg(), nave.getX(), nave.getY(), this);//Colocamos na tela a imagem da nave com suas devidas posições.
		g.dispose();//Irá repintar a tela com as novas atualizações.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nave.mover(); //Responsavel por fazer a ação de se movimentar da nave.
		repaint();	
	}
	
	private class TeclaAdapter extends KeyAdapter { //Classe responsavel por pegar as teclas pressionadas.

		@Override
		public void keyPressed(KeyEvent e) {
			nave.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.KeyReleased(e);
		}	
		
	}	

}
