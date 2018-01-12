package Bibliotheque;

public interface Document {
	int numero();
	void reserver(Abonne ab) throws PasLibreException, AbonneBanEx ; //Exception rajout� pour le bannissement de l'abonn�
	void emprunter(Abonne ab) throws PasLibreException, AbonneBanEx;
	void retour(boolean abime); // document rendu ou annulation r�servation
}
