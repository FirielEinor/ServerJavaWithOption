package Bibliotheque;

import java.util.TimerTask;

public class TimerRetardAbo extends TimerTask {
	
	private Livre l;
	
	public TimerRetardAbo(Livre l) {
		this.l = l;
	}
	
	
	@Override
	public void run() {
		l.setRetard(true);
		System.out.println("Le timer de retard se lance");
	}

}
