package Bibliotheque;

import java.util.TimerTask;

public class TimerUnbanAbo extends TimerTask {
	
	private Abonne ab;
	
	public TimerUnbanAbo(Abonne a) {
		this.ab = a;
	}
	
	
	@Override
	public void run() {
		ab.unban();
	}

}
