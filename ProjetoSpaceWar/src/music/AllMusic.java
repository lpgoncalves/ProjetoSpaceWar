package music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class AllMusic extends Thread {
	private static boolean loop;
	private File mp3;
	private Player player;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public AllMusic(String path){
		mp3 = new File(path);
	}
	
	public void run() {
		try {
			do {
				fis = new FileInputStream(mp3);
				bis = new BufferedInputStream(fis);
				this.player = new Player(bis);
				this.player.play();
			} while (loop);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problema ao tocar Musica do Cenário!" + mp3);
			e.printStackTrace();
		}
	}
	
	public boolean getloop() {
		return loop;
	}
	public void setloop(boolean l) {
		loop = l;
	}
	
	public void close() {
		loop = false;
		player.close();
		
	}	
			
}
