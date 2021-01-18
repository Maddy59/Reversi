package fr.eni.reversi;

import fr.eni.util.Outils;

public class JoueurHumain implements Joueur {

	private String nom;

	// Constructeur
	public JoueurHumain() {
		this.nom = Outils.saisie("Nom du joueur ?");
	}

	@Override
	public int[] jouer(PlateauDeReversi p, Pion couleur) {
		int[] coordonnees = new int[2];
		coordonnees[LIGNE] = Outils.saisie("ligne", 1, PlateauDeReversi.TAILLE)
				- 1;
		coordonnees[COLONNE] = Outils.saisie("colonne", 1,
				PlateauDeReversi.TAILLE) - 1;
		return coordonnees;
	}

	@Override
	public String getNom() {
		return this.nom;
	}
}