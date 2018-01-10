package Serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Bibliotheque.Abonne;
import Bibliotheque.Bibliotheque;
import Bibliotheque.Livre;
import Bibliotheque.PasLibreException;

public class ServiceReserver implements IService {
	
	Socket s;
	Bibliotheque b;
	
	public ServiceReserver(Bibliotheque b) {
		this.b = b;
	}
	
	@Override
	public void run() {
		try {
			PrintWriter socketOut = new PrintWriter(s.getOutputStream(),true);	
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str;
			int numAbo;
			int numLivre;
			Abonne a = null;
			Livre l = null;
			
			socketOut.println("Veuillez rentrer votre numéro d'abonné");
			
			while(a == null){
				str = socketIn.readLine();
				numAbo = Integer.parseInt(str);
				a = b.getAbo(numAbo);
				if (a == null)
					socketOut.println("Cette Abonné n'existe pas !##Veuillez rentrer votre numéro d'abonné");
			}
			
			socketOut.println("Bonjour Monsieur " + a.getNom() + ",##Veuillez rentrer le numéro du livre à reserver");
			
			while (l == null){
				str = socketIn.readLine();
				numLivre = Integer.parseInt(str);
				l = b.getDoc(numLivre);		
				if (l == null )
					socketOut.println("Ce Livre n'existe pas !##Veuillez rentrer le numéro du livre à reserver");
				else
					try {
						l.reserver(a);
					} catch (PasLibreException e) {			
						socketOut.println(e + "##Appuyer sur Entrée");
						socketIn.readLine();
					}			
			}
			
			s.close();
		} catch (IOException e) {
			System.out.println("Le client est partie");
		}
	}

	@Override
	public void setSocket(Socket s) {
		this.s = s;
	}

}
