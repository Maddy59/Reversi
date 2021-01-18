package fr.eni.reversi;

import fr.eni.util.Outils;
import fr.eni.util.jeu.Affichable;

public enum Pion implements Affichable {
	LIBRE, BLANC, NOIR;

	// nombre de pion de cette couleur
	private int nombre = 2;
	// joueur associé à cette couleur
	private Joueur joueur;

	public Joueur getJoueur() {
		return joueur;
	}

	public void choixJoueur() {
		String m = String.format(
				"Quel joueur pour les pions %s ?%n 1 - Humain%n 2 - Ordinateur",
				this.getSymbole());
		int c = Outils.saisie(m, 1, 2);
		if (c == 1)
			this.joueur = new JoueurHumain();
		else
			this.joueur = new IAalea();
	}

	public int getNombre() {
		return nombre;
	}

	/*
	 * Modifie l'attribut nombre pour ajouter le nombre de pions acquis grâce au
	 * coup du joueur L'adversaire perd ce même nombre de pions L'attribut
	 * nombre de ce pion est augmenté de un en raison du pion posé par le joueur
	 */
	public void gagne(int nombre) {
		this.nombre += nombre + 1;
		this.autrePion().nombre -= nombre;
	}

	// Méthode pour connaitre le pion opposé
	public Pion autrePion() {
		Pion autre;
		switch (this) {
			case BLANC :
				autre = Pion.NOIR;
				break;
			case NOIR :
				autre = Pion.BLANC;
				break;
			default :
				autre = Pion.LIBRE;
				break;
		}
		return autre;
	}

	// Donne le symbole utilisé pour afficher ce pion
	@Override
	public char getSymbole() {
		char ret;
		switch (this) {
			case BLANC :
				ret = 'o';
				break;
			case NOIR :
				ret = '●';
				break;
			default :
				ret = '·';
				break;
		}
		return ret;
	}
}
