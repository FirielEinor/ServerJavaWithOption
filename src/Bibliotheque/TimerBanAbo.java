package Bibliotheque;

import java.util.TimerTask;

public class TimerBanAbo extends TimerTask {
	
	private Livre l;
	
	public TimerBanAbo(Livre l) {
		this.l = l;
	}
	
	
	@Override
	public void run() {
		l.getAb().ban();
		System.out.println("Le timer de ban se lance");
	}

}
