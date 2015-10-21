package game;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tempo {
	
	Timer timer;
	
	public int minutos = 0;
	public int segundos = 40;

	public Tempo() {
		
		timer = new Timer();
		timer.schedule(new DisplayCountdown(), 0, 1000);
	}

	class DisplayCountdown extends TimerTask {

		public void run() {
			
			if (segundos >= 0) {
				segundos--;
				
			} else if (minutos > 0) {
				minutos--;
				segundos = 59;
			  } 
		}
	}	
	
	public void pararTimer() {
		timer.cancel();
	}
	
	public void comecarTimer() {
	    this.timer = new Timer();
	    this.timer.schedule( new DisplayCountdown(), 0, 1000 );
	}

}