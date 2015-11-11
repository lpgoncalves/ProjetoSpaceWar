package game;
import java.util.Timer;
import java.util.TimerTask;

public class Tempo {
	Timer timer;
	public int minutos = 0;
	public int segundos = 0;
	public int timerMulti = 0;
	public int multiPontos = 1;
	public int shadow = 0;

	public Tempo() {
		timer = new Timer();
		timer.schedule(new DisplayCountdown(), 0, 1000);
	}

	class DisplayCountdown extends TimerTask {
		public void run() {
				segundos++;
				timerMulti++;
				if(segundos == 60){
					segundos = 0;
					minutos++;
				}
				if(shadow > 0)
				{
					shadow--;
				}
				if(timerMulti % 10 == 0 && multiPontos < 4)
					multiPontos++;	
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