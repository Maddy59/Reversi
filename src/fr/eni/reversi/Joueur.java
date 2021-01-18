package fr.eni.reversi;

public interface Joueur {
	static final int LIGNE = 0;
	static final int COLONNE = 1;

	/*
	 * Méthode retourant les coordonnées où le joueur souhaite positionner son
	 * pion
	 */
	int[] jouer(PlateauDeReversi p, Pion couleur);

	// Donne le nom du joueur
	String getNom();
}
