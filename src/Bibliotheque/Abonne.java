package Bibliotheque;

import java.util.Calendar;
import java.util.Timer;

public class Abonne {
	private int id;
	private String nom;
	private boolean peutEmprunter;
	
	public Abonne(int id,String nom){
		this.id = id;
		this.nom = nom;
		peutEmprunter = true;
	}
	
	public boolean equals(Abonne a){
		if (a == null){
			return false;
		}
		return this.id == a.id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public boolean isPeutEmprunter(){
		return peutEmprunter;
	}
	
	public void ban(){
		peutEmprunter = false;
		
		
		
		
		
	}
	
	public void unban(){
		peutEmprunter = true;
	}
}
