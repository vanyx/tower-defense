package warcraftTD;

public class Main {

	/*
	 * L2 -PO Warcraft - Java Defense
	 * Auteurs: Thomas BENALOUANE & Fabien LE MORZADEC
	 */

	public static void main(String[] args) {
		int width = 1120;
		int height = 750;
		int nbSquareX = 10;
		int nbSquareY = 10;

		// creation du menu et lancement de la boucle principale du jeu
		Menu m = new Menu(width, height, nbSquareX, nbSquareY);
		m.run();
	}
}
