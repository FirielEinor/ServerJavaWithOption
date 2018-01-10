package Serveur;
import java.net.Socket;

public interface IService extends Runnable{
	public void setSocket(Socket s);
}
