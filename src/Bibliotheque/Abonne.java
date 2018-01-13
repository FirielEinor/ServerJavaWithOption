package Bibliotheque;

import java.util.Calendar;
import java.util.Timer;

public class Abonne {
	private int id;
	private String nom;
	private int peutEmprunter;
	
	public Abonne(int id,String nom){
		this.id = id;
		this.nom = nom;
		peutEmprunter = 0;
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
		return peutEmprunter == 0;
	}
	
	public void ban(){
		++peutEmprunter;	
	}
	
	public void unban(){
		--peutEmprunter;
	}
}
