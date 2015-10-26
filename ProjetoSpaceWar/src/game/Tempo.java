package game;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import game.Fase.criarInimigos;

public class Tempo {
	
	Timer timer;
	
	public int minutos = 0;
	public int segundos = 0;
	public int shadow = 0;

	public Tempo() {
		
		timer = new Timer();
		timer.schedule(new DisplayCountdown(), 0, 1000);
	}

	class DisplayCountdown extends TimerTask {

		public void run() {
			
				segundos++;
				if(segundos == 60){
					segundos = 0;
					minutos++;
				}
				if(shadow > 0)
				{
					shadow--;
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

	public void iniciarShadow(){
		shadow = 2;
	}
}