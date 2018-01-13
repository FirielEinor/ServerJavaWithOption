package Serveur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Bibliotheque.Bibliotheque;


public class Serveur implements Runnable{
	
	private ServerSocket maSocketDeServer;
	private int numService;
	private Bibliotheque b;

	public Serveur(int port, int numService, Bibliotheque b) throws IOException {
		maSocketDeServer = new ServerSocket(port);
		this.numService = numService;
		this.b = b;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				Socket socketServer;		
				socketServer = maSocketDeServer.accept();
				new Thread(FactoryService.creerService(numService, b,socketServer)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
