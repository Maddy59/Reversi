package fr.eni.util.jeu.deDes;

public class TestDe {
	public static void main(String[] args) {
		De de1 = new De(100);
		De de2 = new De(100);
		for (int i = 0; i < 3; i++) {
			System.out.println("Le dé1 a fait un " + de1.lancer());
			System.out.println("Le dé2 a fait un " + de2.lancer());
		}
	}
}
