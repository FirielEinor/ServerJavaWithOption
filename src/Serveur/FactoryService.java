package Serveur;

import java.net.Socket;

import Bibliotheque.Bibliotheque;

public class FactoryService {
	
	public static IService creerService(int numService, Bibliotheque b,Socket s){
		
		switch (numService) {
		case 1:
			return new ServiceReserver(b,s);
		case 2:
			return new ServiceEmprunter(b,s);
		case 3:
			return new ServiceRendre(b,s);

		default:
			return null;
		}
		
	}
	
}
