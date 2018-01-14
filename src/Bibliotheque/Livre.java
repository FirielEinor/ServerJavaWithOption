package Bibliotheque;
import java.util.Calendar;
import java.util.Timer;

public class Livre implements Document {
	private int id;
	private String nom;
	private int etat; // 0  = libre, 1 = reservé, 2 = emprunté
	private Abonne ab;
	private Timer timerReserve;
	private Timer timerBanAbo;
	
	public Livre(int id, String nom){
		this.id = id;
		this.nom = nom;
		this.etat = 0;
		ab = null;
		timerReserve = null;
		timerBanAbo = null;
	}
	
	
	@Override
	public int numero() {
		return id;
	}

	@Override
	public synchronized void reserver(Abonne ab) throws PasLibreException, AbonneBanEx {
		if ( (this.etat == 1 && !ab.equals(this.ab)) || this.etat == 2)
			throw new PasLibreException(this);
		
		synchronized(ab){
			if (!ab.isPeutEmprunter())
				throw new AbonneBanEx();
		}
		
		this.etat = 1;
		this.ab = ab;
		long delay = 30000; //delai avant le lancement de la tache programmer par le timer(ici 30 sec), pour deux heure remplacer par 7200000
		timerReserve = new Timer();
		timerReserve.schedule(new TimerRendre(this), delay);
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws PasLibreException, AbonneBanEx {
		if ((this.etat == 1 && !ab.equals(this.ab)) || this.etat == 2)
			throw new PasLibreException(this);
		
		synchronized(ab){
			if (!ab.isPeutEmprunter())
				throw new AbonneBanEx();
		}
		this.etat = 2;
		this.ab = ab;
		
		Calendar c = Calendar.getInstance();
			
		
		/*le timer est initialiser a 1 minute ici pour réaliser les tests
		 * pour passer au 14 jour demandé remplacer la ligne par :
		 *  c.add(Calendar.DAY_OF_YEAR, 14);
		 */	
		c.add(Calendar.MINUTE, 1);
		Calendar now = Calendar.getInstance();
		
		timerBanAbo = new Timer();
		timerBanAbo.schedule(new TimerRetardAbo(ab),c.getTimeInMillis() - now.getTimeInMillis());
		
		if (timerReserve != null)
			timerReserve.cancel();
	}

	@Override
	public void retour(boolean abime) {
		if (ab != null){
			timerBanAbo.cancel();
			if (abime)
				ab.ban();
			if (!ab.isPeutEmprunter()){
				Timer tempsBan = new Timer();
			
				Calendar c = Calendar.getInstance();		
		
				/*
				* le timer est initialiser a 1 minute ici pour réaliser les tests
			 	* pour passer au 1 mois demandé remplacer la ligne par :
			 	*  c.add(Calendar.MONTH, 1);
			 	*/	
				c.add(Calendar.MINUTE, 1);
				Calendar now = Calendar.getInstance();
				tempsBan.schedule(new TimerUnbanAbo(ab), c.getTimeInMillis() - now.getTimeInMillis());
			}
		}
		etat = 0;
		ab = null;
	}
	
}
