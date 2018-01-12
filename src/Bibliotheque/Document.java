package Bibliotheque;

public interface Document {
	int numero();
	void reserver(Abonne ab) throws PasLibreException, AbonneBanEx ;
	void emprunter(Abonne ab) throws PasLibreException, AbonneBanEx;
	void retour(boolean abime); // document rendu ou annulation réservation
}
