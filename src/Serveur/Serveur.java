package Serveur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur implements Runnable{
	
	private ServerSocket maSocketDeServer;
	private IService s;

	public Serveur(int port, IService s) throws IOException {
		maSocketDeServer = new ServerSocket(port);
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				Socket socketServer;		
				socketServer = maSocketDeServer.accept();
				s.setSocket(socketServer);
				new Thread(s).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
