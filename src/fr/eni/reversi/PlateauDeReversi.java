package fr.eni.reversi;

import fr.eni.util.jeu.Plateau;

public class PlateauDeReversi extends Plateau<Pion> {
	public static final int TAILLE = 8;

	/*
	 * Constructeur : Initialise le plateau de jeu dans la configuration de
	 * début de partie <br> · · · · · · · · <br> · · · · · · · · <br> · · · · ·
	 * · · · <br> · · · o ● · · · <br> · · · ● o · · · <br> · · · · · · · · <br>
	 * · · · · · · · · <br> · · · · · · · ·
	 */
	private PlateauDeReversi() {
		super(TAILLE, TAILLE, Pion.LIBRE);
		this.set(TAILLE / 2, TAILLE / 2, Pion.BLANC);
		this.set(TAILLE / 2 - 1, TAILLE / 2, Pion.NOIR);
		this.set(TAILLE / 2, TAILLE / 2 - 1, Pion.NOIR);
		this.set(TAILLE / 2 - 1, TAILLE / 2 - 1, Pion.BLANC);
	}

	// Méthode permettant d'effectuer une partie de Reversi
	private void jouer() {
		Pion.NOIR.choixJoueur();
		Pion.BLANC.choixJoueur();
		Pion courant = Pion.NOIR;
		int nbPasseTour = 0;
		while (nbPasseTour < 2 && Pion.BLANC.getNombre()
				+ Pion.NOIR.getNombre() < TAILLE * TAILLE) {
			System.out.printf("Au tour de %s (%s)...%n",
					courant.getJoueur().getNom(), courant.getSymbole());
			int nbRetournes = 0;
			boolean ok = false;
			do {
				this.afficher();
				if (this.peutJouer(courant)) {
					int[] coord = courant.getJoueur().jouer(this, courant);
					nbRetournes = this.tester(courant, coord[Joueur.LIGNE],
							coord[Joueur.COLONNE]);
					if (nbRetournes > 0) {
						this.poser(courant, coord[Joueur.LIGNE],
								coord[Joueur.COLONNE]);
						courant.gagne(nbRetournes);
						nbPasseTour = 0;
						ok = true;
					} else {
						System.err.println("Position illégale");
					}
				} else {
					System.out.printf(
							"%s n'a aucune position où poser un de ses pions. Il passe sont tour.%n",
							courant.getSymbole());
					nbPasseTour++;
					ok = true;
				}
			} while (!ok);
			// changement de joueur
			courant = courant.autrePion();
		}
		if (Pion.BLANC.getNombre() > Pion.NOIR.getNombre()) {
			System.out.printf("%s (%s) gagne !%n",
					Pion.BLANC.getJoueur().getNom(), Pion.BLANC.getSymbole());
		} else if (Pion.BLANC.getNombre() < Pion.NOIR.getNombre()) {
			System.out.printf("%s (%s) gagne !%n",
					Pion.NOIR.getJoueur().getNom(), Pion.NOIR.getSymbole());
		} else {
			System.out.println("Égalité !");
		}
		this.afficher();
	}

	// Affiche le plateau de jeu et le score
	@Override
	public void afficher() {
		// Affichage des scores
		System.out.printf("%2d %s%n%2d %s%n", Pion.NOIR.getNombre(),
				Pion.NOIR.getSymbole(), Pion.BLANC.getNombre(),
				Pion.BLANC.getSymbole());
		// Affichage du plateau de jeu
		super.afficher();
	}

	/*
	 * Indique le nombre de pions changeant de couleur si le joueur ayant les
	 * pions de la couleur passée en paramètre pose un pion sur la case dont les
	 * coordonnées sont passées en paramètres
	 */
	public int tester(Pion couleur, int y, int x) {
		int nbPions = 0;
		if (this.get(y, x) == Pion.LIBRE)
			for (int dy = -1; dy <= 1; dy++)
				for (int dx = -1; dx <= 1; dx++)
					if (dx != 0 || dy != 0)
						nbPions += testerDirection(couleur, y, x, dy, dx);
		return nbPions;
	}

	/*
	 * Indique le nombre de pions changeant de couleur si le joueur ayant les
	 * pions de la couleur passée en paramètre pose un pion sur la case dont les
	 * coordonnées sont passées en paramètres pour une direction donnée avec les
	 * paramètres dx et dy
	 */
	private int testerDirection(Pion couleur, int y, int x, int dy, int dx) {
		Pion couleurOpp = couleur.autrePion();
		int nbAutres = 0;
		int i = x + dx;
		int j = y + dy;
		while (0 <= i && i < TAILLE && 0 <= j && j < TAILLE
				&& this.get(j, i) == couleurOpp) {
			nbAutres++;
			i += dx;
			j += dy;
		}
		if (i < 0 || i >= TAILLE || j < 0 || j >= TAILLE
				|| this.get(j, i) != couleur)
			nbAutres = 0;
		return nbAutres;
	}

	/*
	 * Cherche s'il y a au moins une case sur laquelle le joueur passé en
	 * paramètre peut jouer
	 */
	private boolean peutJouer(Pion joueur) {
		boolean positionJouable = false;
		int j = 0;
		while (j < TAILLE && !positionJouable) {
			int i = 0;
			while (i < TAILLE && !positionJouable) {
				positionJouable = tester(joueur, j, i) > 0;
				i++;
			}
			j++;
		}
		return positionJouable;
	}

	/*
	 * Pose un pion sur le plateau et change la couleur des pions nouvellement
	 * encadrés entre ce pion et un autre de la même couleur dans toutes les
	 * directions
	 */
	private void poser(Pion couleur, int y, int x) {
		this.set(y, x, couleur);
		int nbPions;
		for (int dy = -1; dy <= 1; dy++)
			for (int dx = -1; dx <= 1; dx++) {
				nbPions = 0;
				if (dx != 0 || dy != 0) {
					nbPions += testerDirection(couleur, y, x, dy, dx);
					for (int k = 1; k <= nbPions; k++)
						this.set(y + dy * k, x + dx * k, couleur);
				}
			}
	}

	// Méthode principale : lance une partie de Reversi
	public static void main(String[] args) {
		PlateauDeReversi p = new PlateauDeReversi();
		p.jouer();
	}
}
