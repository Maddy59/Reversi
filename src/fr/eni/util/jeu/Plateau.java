package fr.eni.util.jeu;

import java.util.ArrayList;
import java.util.List;

public class Plateau<T extends Affichable> {
	private List<T> plateauDeJeu;
	private int hauteur;
	private int largeur;

	// Constructeur
	public Plateau(int hauteur, int largeur, T valeurInitiale) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.plateauDeJeu = new ArrayList<>(hauteur * largeur);
		for (int k = 0; k < largeur * hauteur; k++)
			this.plateauDeJeu.add(valeurInitiale);
	}

	// Afficher le plateau de jeu
	public void afficher() {
		System.out.print(" ");
		for (int i = 1; i <= this.largeur; i++)
			System.out.printf("%d ", i);
		System.out.println();
		for (int j = 0; j < hauteur; j++) {
			System.out.printf("%2d ", j + 1);
			for (int i = 0; i < largeur; i++) {
				System.out.printf("%s ", this.get(j, i).getSymbole());
			}
			System.out.println();
		}
	}

	/*
	 * Récupère la valeur positionnée dans la case de coordonnées [ligne,
	 * colonne]
	 */
	public T get(int ligne, int colonne) {
		return this.plateauDeJeu.get(ligne * this.hauteur + colonne);
	}

	// Positionne une valeur dans la case de coordonnées [ligne, colonne]
	public void set(int ligne, int colonne, T valeur) {
		this.plateauDeJeu.set(ligne * this.hauteur + colonne, valeur);
	}
}
