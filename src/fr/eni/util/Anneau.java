package fr.eni.util;

public class Anneau<T> {
	private Maillon<T> ptEntree;
	private int nbElements = 0;

	public Maillon<T> ajouter(T element) {
		this.nbElements++;
		if (this.ptEntree == null) {
			this.ptEntree = new Maillon<T>(element, null);
			this.ptEntree.setSuivant(this.ptEntree);
		} else {
			Maillon<T> nouveau = new Maillon<T>(element,
					this.ptEntree.suivant());
			this.ptEntree.setSuivant(nouveau);
			this.ptEntree = nouveau;
		}
		return this.ptEntree;
	}

	public void retirer(T element) {
		if (this.nbElements > 0) {
			int i = 0;
			Maillon<T> courant = this.ptEntree;
			while (i < this.nbElements && courant.suivant().get() != element) {
				i++;
				courant = courant.suivant();
			}
			if (courant.suivant().get() == element) {
				this.nbElements--;
				if (this.nbElements == 0)
					this.ptEntree = null;
				else if (this.ptEntree == courant.suivant())
					this.ptEntree = courant;
				courant.setSuivant(courant.suivant().suivant());
			}
		}
	}

	public int getNbElements() {
		return nbElements;
	}

	@Override
	public String toString() {
		if (this.ptEntree == null)
			return "[vide]";
		return this.ptEntree.toString(this.nbElements);
	}

	public Maillon<T> getEntree() {
		return this.ptEntree;
	}
}