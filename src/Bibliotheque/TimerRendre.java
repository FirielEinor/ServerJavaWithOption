package Bibliotheque;
import java.util.TimerTask;

public class TimerRendre extends TimerTask {
	
	private Livre l;
	
	public TimerRendre(Livre l){
		this.l = l;
	}

	@Override
	public void run() {
		l.retour();
	}

}
