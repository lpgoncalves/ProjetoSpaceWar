package music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class SomFundo {

	private static boolean loop;
	private static boolean andamentoMusica;

	public boolean getloop() {
		return loop;
	}

	public void setloop(boolean l) {
		loop = l;
	}
	
	public boolean getAndamentoMusica(){
		return andamentoMusica;
	}
	
    public void setAndamentoMusica(boolean andMusica) {
    	andamentoMusica = andMusica;
    }

	public void main(String[] args) {

		// String com o caminho do arquivo MP3 a ser tocado
		String path = "res\\sons\\Musica_Fase.mp3";

		// Instancia o objeto File com o arquivo MP3
		File mp3File = new File(path);

		// Instanciar o Objeto MP3, a qual criamos a classe.
		MP3MusicaFundo musica = new MP3MusicaFundo();
		musica.tocarMusicaFundo(mp3File);

		// Finalmente a chamada do método que toca a música
		musica.start();
		
	
	}

	/**
	 * ====================================================================
	 * ====================================CLASS INTERNA MP3 MUSICA CENARIO
	 * ====================================================================
	 */
	public static class MP3MusicaFundo extends Thread {

		// Objeto para nosso arquivo MP3 a ser tocado
		private File mp3;
		

		// Objeto Player da biblioteca jLayer. Ele tocará o arquivo MP3
		private Player player;

		/*
		 * Construtor que recebe o objeto File referenciando o arquivo MP3 a ser
		 * tocado e atribui ao atributo MP3 da classe.
		 * 
		 * @param mp3
		 */
		public void tocarMusicaFundo(File mp3) {
			this.mp3 = mp3;
		}

		/*
		 * ==================================================================
		 * =============================================METODO QUE TOCA O MP3
		 * ==================================================================
		 */
		public void run() {
			try {
				do {
					FileInputStream fis = new FileInputStream(mp3);
					BufferedInputStream bis = new BufferedInputStream(fis);
					this.player = new Player(bis);
					this.player.play();
					
					if (andamentoMusica == false) {
						andamentoMusica = false;
						player.close();
					}
					
				} while (loop);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problema ao tocar Musica do CenÃ¡rio!" + mp3);
				e.printStackTrace();
			}
		}

		public void close() {
				loop = false;
				player.close();
				this.interrupt();
				
			}
		}

}