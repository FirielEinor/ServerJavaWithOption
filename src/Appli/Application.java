package Appli;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Bibliotheque.Abonne;
import Bibliotheque.Bibliotheque;
import Bibliotheque.Livre;
import Serveur.IService;
import Serveur.Serveur;
import Serveur.ServiceEmprunter;
import Serveur.ServiceRendre;
import Serveur.ServiceReserver;

public class Application {
	
	public static final int _PORT_RESERVER = 2500;
	public static final int _PORT_EMPRUNTER = 2600;
	public static final int _PORT_RENDRE = 2700;
	
	public static Bibliotheque creerBibliotheque(){
		List<Livre> list = new ArrayList<Livre>();
		List<Abonne> listA = new ArrayList<Abonne>();
		
		Livre d1 = new Livre(1,"Livre1");
		list.add(d1);
		d1 = new Livre(2,"Livre2");
		list.add(d1);
		d1 = new Livre(3,"Livre3");
		list.add(d1);
		d1 = new Livre(4,"Livre4");
		list.add(d1);
		d1 = new Livre(5,"Livre5");
		list.add(d1);
		
		Abonne a = new Abonne(1,"abonne1");
		listA.add(a);
		a = new Abonne(2,"abonne2");
		listA.add(a);
		a = new Abonne(3,"abonne3");
		listA.add(a);
		a = new Abonne(4,"abonne4");
		listA.add(a);
		
		return new Bibliotheque(listA,list);
	}
	
	
	
	public static void main(String[] args) {
		try {
			Bibliotheque b = creerBibliotheque();
			System.out.println("Connection au serveur !");
	
			new Thread(new Serveur(_PORT_RESERVER,1,b)).start();
			new Thread(new Serveur(_PORT_EMPRUNTER,2,b)).start();
			new Thread(new Serveur(_PORT_RENDRE,3,b)).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
