package Bibliotheque;
public class PasLibreException extends Exception {
	
	private Document l;
	
	public PasLibreException(Document l){
		super();
		this.l = l;
	}
	
	public String toString(){
		return ("Ce document n'est pas disponible");
	}
}
