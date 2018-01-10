package Bibliotheque;
import java.util.Calendar;
import java.util.Timer;

public class Livre implements Document {
	private int id;
	private String nom;
	private int etat; // 0  = libre, 1 = reserv�, 2 = emprunt�
	private Abonne ab;
	private Timer timerReserve;
	private Timer timerRendre;
	
	public Livre(int id, String nom){
		this.id = id;
		this.nom = nom;
		this.etat = 0;
		ab = null;
		timerReserve = null;
		timerRendre = null;
	}
	
	
	@Override
	public int numero() {
		return id;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException {
		if ( (this.etat == 1 && !ab.equals(this.ab)) || this.etat == 2)
			throw new PasLibreException(this);
		this.etat = 1;
		this.ab = ab;
		long delay = 30000; //delai avant le lancement de la tache programmer par le timer(ici 30 sec), pour deux heure remplacer par 7200000
		timerReserve = new Timer();
		timerReserve.schedule(new TimerRendre(this), delay);
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException {
		if ((this.etat == 1 && !ab.equals(this.ab)) || this.etat == 2)
			throw new PasLibreException(this);
		this.etat = 2;
		this.ab = ab;
		
		Calendar c = Calendar.getInstance();
			
		
		/*le timer est initialiser a 1 minute ici pour r�aliser les tests
		 * pour passer au 14 jour demand� remplacer la ligne par :
		 *  c.add(Calendar.DAY_OF_YEAR, 14);
		 */	
		c.add(Calendar.MINUTE, 1);
		Calendar now = Calendar.getInstance();
		
		timerRendre = new Timer();
		timerRendre.schedule(new TimerBanAbo(this),c.getTimeInMillis() - now.getTimeInMillis());
		
		if (timerReserve != null)
			timerReserve.cancel();
	}

	@Override
	public void retour() {
		etat = 0;
		ab = null;
	}

	public int getEtat() {
		return etat;
	}
	
	public Abonne getAb(){
		return ab;
	}
	
	
	public int getId(){
		return this.id;
	}
}
