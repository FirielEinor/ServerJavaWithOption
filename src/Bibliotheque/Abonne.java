package Bibliotheque;

import java.util.Calendar;
import java.util.Timer;

public class Abonne {
	private int id;
	private String nom;
	private boolean peutEmprunter;
	private Timer tempsBan;
	
	public Abonne(int id,String nom){
		this.id = id;
		this.nom = nom;
		peutEmprunter = true;
	}
	
	public boolean equals(Abonne a){
		if (a == null){
			return false;
		}
		return this.id == a.id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void ban(){
		peutEmprunter = false;
		tempsBan = new Timer();
		
		Calendar c = Calendar.getInstance();
			
		
		/*le timer est initialiser a 1 minute ici pour réaliser les tests
		 * pour passer au 1 mois demandé remplacer la ligne par :
		 *  c.add(Calendar.MONTH, 1);
		 */	
		c.add(Calendar.MINUTE, 1);
		Calendar now = Calendar.getInstance();
		
		
		
		tempsBan.schedule(new TimerUnbanAbo(this), c.getTimeInMillis() - now.getTimeInMillis());
	}
	
	public void unban(){
		peutEmprunter = true;
	}
}
