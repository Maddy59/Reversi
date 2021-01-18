package fr.eni.util.jeu.deDes;

import java.util.Random;

public class De2 {
	private int nbFaces;
	private int faceTiree;
	private static Random rand = new Random();

	// Constructeur privé pour maitriser son appel (pas de constructeur par
	// défaut)
	private De2() {
	}

	public static De2 creerDe(int nbFaces) {
		verifNbFaces(nbFaces);
		De2 d = new De2();
		d.nbFaces = nbFaces;
		return d;
	}

	public int getNbFaces() {
		return nbFaces;
	}

	public void setNbFaces(int nbFaces) {
		De2.verifNbFaces(nbFaces);
		this.nbFaces = nbFaces;
	}

	private static void verifNbFaces(int nbFaces) {
		if (nbFaces <= 1)
			throw new JeuException("Un dé doit avoir au moins deux faces");
	}

	public int lancer() {
		return this.faceTiree = De2.rand.nextInt(this.nbFaces) + 1;
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
