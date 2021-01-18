package fr.eni.util.jeu.deDes;

import java.util.Random;

public class De {
	private int nbFaces;
	private int faceTiree;
	private static Random rand = new Random();

	// Constructeur pour créer un dé avec le nombre de faces passé en paramètre
	public De(int nbFaces) {
		this.setNbFaces(nbFaces);
		this.lancer();
	}

	// Constructeur pour créer un dé à 6 faces
	public De() {
		// appel à l'autre constructeur
		// (obligatoirement la première instruction)
		this(6);
	}

	public int getNbFaces() {
		return nbFaces;
	}

	public void setNbFaces(int nbFaces) {
		De.verifNbFaces(nbFaces);
		this.nbFaces = nbFaces;
	}

	private static void verifNbFaces(int nbFaces) {
		if (nbFaces <= 1)
			throw new JeuException("Un dé doit avoir au moins deux faces");
	}

	public int lancer() {
		return this.faceTiree = De.rand.nextInt(this.nbFaces) + 1;
	}

	public int getFaceTiree() {
		return this.faceTiree;
	}

	// Méthode permattant de tester la classe De
	public static void main(String[] args) {
		// De d = De.creerDe(6);
		De d = new De(6);
		int[] occ = new int[6];
		for (int i = 0; i < 6000; i++) {
			occ[d.lancer() - 1]++;
		}
		for (int i = 0; i < occ.length; i++) {
			System.out.printf("%d : %d%n", i + 1, occ[i]);
		}

	}
}
