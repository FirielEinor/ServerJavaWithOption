package Serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Bibliotheque.Abonne;
import Bibliotheque.AbonneBanEx;
import Bibliotheque.Bibliotheque;
import Bibliotheque.Document;
import Bibliotheque.PasLibreException;

public class ServiceEmprunter implements IService {
	
	private Socket s;
	private Bibliotheque b;
	
	public ServiceEmprunter(Bibliotheque b,Socket s) {
		this.b = b;
		this.s = s;
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
			Document l = null;
			
			socketOut.println("Veuillez rentrer votre num�ro d'abonn�");
			
			while(a == null){
				str = socketIn.readLine();
				numAbo = Integer.parseInt(str);
				a = b.getAbo(numAbo);
				if (a == null)
					socketOut.println("Cette Abonn� n'existe pas !##Veuillez rentrer votre num�ro d'abonn�");
			}
			
			
			socketOut.println("Bonjour Monsieur " + a.getNom() + ",##Veuillez rentrer le num�ro du livre � emprunter");
			
			while (l == null){
				numLivre = Integer.parseInt(socketIn.readLine());
				l = b.getDoc(numLivre);
				if (l == null )
					socketOut.println("Ce Livre n'existe pas !##Veuillez rentrer le num�ro du livre � emprunter");
				else {
					try {
						l.emprunter(a);
					} catch (PasLibreException e) {
						socketOut.println(e + "##Appuyer sur Entr�e");
						socketIn.readLine();
					} catch (AbonneBanEx e) {
						socketOut.println(e + "##Appuyer sur Entr�e"); //on attend la reponse du client avant de fermer le serveur pour eviter IOException cot� client
						socketIn.readLine();
					}
				}
			}
			
			s.close();
		} catch (IOException e) {
			System.out.println("Le client est partie");
		}
	}
}
