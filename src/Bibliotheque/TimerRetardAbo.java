package Bibliotheque;

import java.util.TimerTask;

public class TimerRetardAbo extends TimerTask {
	
	private Abonne ab;
	
	public TimerRetardAbo(Abonne a) {
		this.ab = a;
	}
	
	
	@Override
	public void run() {
		ab.ban();
		System.out.println("Le timer de retard se lance");
	}

}
