package Bibliotheque;
import java.util.TimerTask;

public class TimerRendre extends TimerTask {
	
	private Document l;
	
	public TimerRendre(Document l){
		this.l = l;
	}

	@Override
	public void run() {
		l.retour(false);
		System.out.println("Fin de la reservation du livre " + l.getId());
	}

}
