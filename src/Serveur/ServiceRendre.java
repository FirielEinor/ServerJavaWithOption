package Serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Bibliotheque.Bibliotheque;
import Bibliotheque.Document;


public class ServiceRendre implements IService{
	
	Socket s;
	Bibliotheque b;
	
	public ServiceRendre(Bibliotheque b, Socket s) {
		this.s = s;
		this.b = b;
	}
	
	@Override
	public void run() {
		try {
			PrintWriter socketOut = new PrintWriter(s.getOutputStream(),true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
			int numLivre;
			Document l = null;
			
			socketOut.println("Veuiller rentrer le numéro du livre à rendre");
			
			while (l == null){
				str = socketIn.readLine();
				numLivre = Integer.parseInt(str);
				l = b.getDoc(numLivre);
				if (l == null)
					socketOut.println("Ce Livre n'existe pas !##Veuillez rentrer le numéro du livre à rendre");
				
			}
			
			socketOut.println("Le Livre est il abimé ?(o/n)");
			str = socketIn.readLine();
			
			if (str.equals("o")){
				l.retour(true);
			}
				
			l.retour(false);
			
			s.close();
		} catch (IOException e) {
			System.out.println("Le client est partie");
		}	
	}



}
