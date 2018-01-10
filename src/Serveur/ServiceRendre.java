package Serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Bibliotheque.Bibliotheque;
import Bibliotheque.Livre;

public class ServiceRendre implements IService{
	
	Socket s;
	Bibliotheque b;
	
	public ServiceRendre(Bibliotheque b) {
		this.b = b;
	}
	
	@Override
	public void run() {
		try {
			PrintWriter socketOut = new PrintWriter(s.getOutputStream(),true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
			int numLivre;
			Livre l = null;
			
			socketOut.println("Veuiller rentrer le numéro du livre à rendre");
			
			while (l == null){
				str = socketIn.readLine();
				numLivre = Integer.parseInt(str);
				l = b.getDoc(numLivre);
				if (l == null)
					socketOut.println("Ce Livre n'existe pas !##Veuillez rentrer le numéro du livre à rendre");
				else 
					l.retour();
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
