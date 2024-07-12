package warcraftTD;

import java.util.*;

import java.awt.Font;

public class Menu {

	// l'ensemble des boutons
	protected List<Button> buttons = new ArrayList<Button>();

	// Information sur la taille du plateau de jeu
	protected int width;
	protected int height;
	protected int nbSquareX;
	protected int nbSquareY;
	protected double squareWidth;
	protected double squareHeight;

	// mode de jeu; 1 mode classique, 2 mode survie
	protected int mode = -1;

	// carte de jeu
	protected int map = -1;

	// condition pour afficher les informations pour selectionner les parametres
	protected boolean needInfos = false;

	// Commande sur laquelle le joueur appuie (sur le clavier)
	protected char key;

	// Condition pour terminer la partie
	protected boolean end = false;

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases données
	 * 
	 * @param width     largeur du plateau
	 * @param height    hauteur du plateau
	 * @param nbSquareX nombre de case horizontales
	 * @param nbSquareY nombre de cases verticales
	 */
	public Menu(int width, int height, int nbSquareX, int nbSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();

		// création de tout les boutons
		Position startP = new Position(0.3, 0.3 + 0.2);
		Button start = new Button(startP, 0.17, 0.03, "START");
		buttons.add(start);

		Position exitP = new Position(0.3, 0.223 + 0.2);
		Button exit = new Button(exitP, 0.17, 0.03, "EXIT");
		buttons.add(exit);

		Position normalP = new Position(0.212, 0.458 + 0.2);
		Button normal = new Button(normalP, 0.082, 0.03, "CLASSIQUE");
		buttons.add(normal);

		Position survieP = new Position(0.388, 0.458 + 0.2);
		Button survie = new Button(survieP, 0.082, 0.03, "SURVIE");
		buttons.add(survie);

		Position basiqueP = new Position(0.212, 0.378 + 0.2);
		Button basique = new Button(basiqueP, 0.082, 0.03, "CARTE 1");
		buttons.add(basique);

		Position randomP = new Position(0.388, 0.378 + 0.2);
		Button random = new Button(randomP, 0.082, 0.03, "CARTE 2");
		buttons.add(random);
	}

	/**
	 * Définit le décors du plateau de jeu
	 */
	public void drawBackground() {
		StdDraw.picture(0.5, 0.5, "images/wallpaper.png");
	}

	/**
	 * affiche ou non les informations pour selectionner les parametres
	 */
	public void drawInfos() {
		if (mode != -1 && map != -1)
			needInfos = false;

		if (needInfos) {
			StdDraw.setPenColor(StdDraw.RED);
			Font font = new Font("Arial", Font.ITALIC, 20);
			StdDraw.setFont(font);
			StdDraw.text(0.2, 0.1, "Veuillez choisir un mode de jeu et une carte");
		}
	}

	/**
	 * met à jour le menu
	 */
	public void update() {
		drawBackground();
		drawInfos();
		updateButtons();
	}

	/**
	 * met à jour chaque bouton de la liste de boutons à chaque update
	 */
	public void updateButtons() {
		Iterator<Button> i = buttons.iterator();
		Button m;
		while (i.hasNext()) {
			m = i.next();
			Position mouse = new Position(StdDraw.mouseX(), StdDraw.mouseY());
			m.update(mouse);
		}
	}

	/**
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
	}

	/**
	 * création du plateau de jeu, et lancement du jeu
	 */
	public void runGame() {
		World w = new World(width, height, nbSquareX, nbSquareY, 1, 9, mode, map);
		w.run();
	}

	/**
	 * Effectue differentes actions en fonction de @key à chaque clic de souris
	 * 
	 * @param x abscisse de la souris
	 * @param y ordonnee de la souris
	 */
	public void mouseClick(double x, double y) {

		// parcourt de la liste des boutons
		for (int i = 0; i < buttons.size(); i++) {
			// si la souris du joueur est sur un bouton:
			if (buttons.get(i).above) {
				// bouton start
				if (i == 0) {
					if (mode != -1 && map != -1)
						runGame();
					else
						needInfos = true;
				}
				// bouton exit
				if (i == 1) {
					end = true;
					// ferme la fenetre
					System.exit(0);
				}
				// bouton classique
				if (i == 2) {
					mode = 1;
					buttons.get(i).selected = true;
					buttons.get(3).selected = false;
				}
				// bouton survie
				if (i == 3) {
					mode = 2;
					buttons.get(i).selected = true;
					buttons.get(2).selected = false;
				}
				// bouton carte 1
				if (i == 4) {
					map = 1;
					buttons.get(i).selected = true;
					buttons.get(5).selected = false;
				}
				// bouton carte 2
				if (i == 5) {
					map = 2;
					buttons.get(i).selected = true;
					buttons.get(4).selected = false;
				}
			}
		}
	}

	/**
	 * Récupère la position de la souris et met à jour le menu en fonction de ses
	 * interactions
	 */
	public void run() {
		while (!end) {

			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}

			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

			update();
			StdDraw.show();
			StdDraw.pause(20);
		}
	}
}
