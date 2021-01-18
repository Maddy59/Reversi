package fr.eni.util;

public class Maillon<T> {
	private T valeur;
	private Maillon<T> suivant;

	public Maillon(T valeur, Maillon<T> suivant) {
		this.valeur = valeur;
		this.suivant = suivant;
	}

	public T get() {
		return this.valeur;
	}

	public Maillon<T> suivant() {
		return this.suivant;
	}

	public String toString(int max) {
		if (max > 0)
			return this.valeur + " > " + this.suivant.toString(max - 1);
		else
			return "...";
	}

	public void setSuivant(Maillon<T> maillon) {
		this.suivant = maillon;
	}
}
