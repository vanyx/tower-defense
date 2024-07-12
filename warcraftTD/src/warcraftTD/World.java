package warcraftTD;

import java.util.*;
import java.awt.Font;

public class World {
	// l'ensemble des monstres
	protected List<Monster> monsters = new ArrayList<Monster>();

	// l'ensemble des "cases" de chemin
	protected List<Position> path = new ArrayList<Position>();

	// l'ensemble des tours
	protected List<Tower> towers = new ArrayList<Tower>();

	// l'ensemble des mine d'or
	protected List<GoldMine> goldmines = new ArrayList<GoldMine>();

	// l'ensemble des projectiles
	protected List<Projectile> projectiles = new ArrayList<Projectile>();

	// l'ensemble des projectiles tirés par les monstres
	protected List<Projectile> shots = new ArrayList<Projectile>();

	// l'ensemble des monstres generés pour une vague
	protected List<Monster> waveMonsters = new ArrayList<Monster>();

	// Position par laquelle les monstres vont venir
	protected Position spawn;

	// Position finale
	protected Position castle;

	// mode de jeu
	protected int mode;

	// Information sur la taille du plateau de jeu
	protected int width;
	protected int height;
	protected int nbSquareX;
	protected int nbSquareY;
	protected double squareWidth;
	protected double squareHeight;

	// Nombre de points de vie du joueur
	protected int life = 2000000;

	// nombre de pièces d'or du joueur
	protected int gold = 100;

	// Commande sur laquelle le joueur appuie (sur le clavier)
	protected char key;

	// Condition pour terminer la partie
	protected boolean end = false;

	// "temps" entre chaque apparition de monstre
	protected int monsterTime = 0;

	// index de monstres à ajouter
	protected int indexMonster = 0;

	// Condition pour connaitre la fin d'une vague
	protected boolean endWave = false;

	// Compteur de round
	protected int round = 0;

	// nombre de round maximum
	protected int roundMax;

	// temps
	protected int time = 0;

	// temps utilisé entre chaque manche
	protected int waiting = 0;

	// condition pour recevoir de l'or après chaque vague
	protected boolean needGold = false;

	// condition pour savoir si la parti est finie
	protected boolean finished = false;

	// condition pour savoir si un golem est arrivé au chateau
	protected boolean golem = false;

	// condition pour savoir si le jeu peut commencer
	protected boolean canStart = false;

	// vie supplementaire ajouté aux monstres à chaque vague
	protected double difficulty = 0;

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases données
	 * 
	 * création du chemin
	 * 
	 * @param width        largeur du plateau
	 * @param height       hauteur du plateau
	 * @param nbSquareX    nombre de case horizontales
	 * @param nbSquareY    nombre de cases verticales
	 * @param startSquareX abscisse du spawn
	 * @param startSquareY ordonnée du spawn
	 * @param mode
	 * @param map
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY, int mode,
			int map) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		this.mode = mode;

		spawn = new Position(startSquareX * squareWidth + squareWidth / 2,
				startSquareY * squareHeight + squareHeight / 2);
		StdDraw.enableDoubleBuffering();
		roundMax = 10;
		// création de la map en fonction de celle choisie
		if (map == 1) {
			buildPath();
		} else {
			buildPath2();
		}
	}

	/**
	 * création du chemin 1
	 */
	public void buildPath() {

		path.add(spawn);

		for (int i = 2; i < 5; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 9 * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 8; i >= 6; i--) {
			Position part = new Position(4 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 3; i >= 1; i--) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 6 * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 5; i >= 2; i--) {
			Position part = new Position(1 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 2; i < 8; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 2 * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 3; i < 7; i++) {
			Position part = new Position(7 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 8; i < 10; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 6 * squareHeight + squareHeight / 2);
			path.add(part);
		}
		for (int i = 5; i > 0; i--) {
			Position part = new Position(9 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}
		castle = new Position(9 * squareWidth + squareWidth / 2, 0 * squareHeight + squareHeight / 2);

		path.add(castle);
	}

	/**
	 * création du chemin 2
	 */
	public void buildPath2() {
		path.add(spawn);

		for (int i = 8; i >= 4; i--) {
			Position part = new Position(1 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 2; i < 5; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 4 * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 5; i <= 6; i++) {
			Position part = new Position(4 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 5; i < 8; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 6 * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 5; i >= 2; i--) {
			Position part = new Position(7 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 6; i > 1; i--) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 2 * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 1; i >= 0; i--) {
			Position part = new Position(2 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 3; i <= 9; i++) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 0 * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 1; i <= 8; i++) {
			Position part = new Position(9 * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2);
			path.add(part);
		}

		for (int i = 8; i > 5; i--) {
			Position part = new Position(i * squareWidth + squareWidth / 2, 8 * squareHeight + squareHeight / 2);
			path.add(part);
		}

		castle = new Position(6 * squareWidth + squareWidth / 2, 9 * squareHeight + squareHeight / 2);

		path.add(castle);
	}

	/**
	 * Définit le décors du plateau de jeu.
	 */
	public void drawBackground() {

		StdDraw.setPenColor(79, 202, 213);
		for (int i = 0; i < nbSquareX; i++)
			for (int j = 0; j < nbSquareY; j++)
				StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
						"images/grass.jpg", squareWidth, squareHeight);
	}

	/**
	 * affiche toutes les cases de chemin
	 */
	public void drawPath() {

		for (Position i : path) {
			StdDraw.picture(i.x, i.y, "images/path.png", squareWidth, squareHeight);
		}

		// affichage des images du spawn et du chateau
		StdDraw.picture(spawn.x, spawn.y, "images/camp.png", squareWidth - 0.01, squareHeight - 0.01);
		StdDraw.picture(castle.x, castle.y, "images/castle.png", squareWidth * 1.3, squareHeight * 1.3);
	}

	/**
	 * Affiche les informations à l'ecran
	 */
	public void drawInfos() {

		StdDraw.setPenColor(StdDraw.WHITE);
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);

		// affichage de la vie
		StdDraw.text(0.95, 0.95, String.valueOf(life));
		StdDraw.picture(8.5 * squareWidth + squareWidth / 2, 9 * squareHeight + squareHeight / 2, "images/heart.png",
				squareWidth / 4, squareHeight / 4);

		// affichage du gold
		StdDraw.text(0.95, 0.91, String.valueOf(gold));
		StdDraw.picture(8.5 * squareWidth + squareWidth / 2, 8.6 * squareHeight + squareHeight / 2, "images/gold.png",
				squareWidth / 4, squareHeight / 4);

		// affichage du round
		if (mode == 1)
			StdDraw.text(0.93, 0.87, "round " + String.valueOf(round + 1) + "/" + roundMax);
		else
			StdDraw.text(0.93, 0.87, "round " + String.valueOf(round + 1));

		// affichage des informations de depart
		if (!canStart) {
			StdDraw.setPenColor(StdDraw.WHITE);
			font = new Font("Arial", Font.ITALIC, 17);
			StdDraw.setFont(font);
			StdDraw.text(0.5, 0.52, "Appuyez sur S pour lancer la premiere vague");
			StdDraw.text(0.5, 0.48, "Appuyez sur Q pour quitter");
		}
	}

	/**
	 * Fonction qui récupère le positionnement de la souris et permet d'afficher une
	 * image de tour en temps réél lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {
		switch (key) {
			case 'a':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/arrowTower1.png", squareWidth,
						squareHeight);
				break;
			case 'b':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/cannonTower.png", squareWidth,
						squareHeight);
				break;
			case 'c':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/airTower1.png", squareWidth, squareHeight);
				break;
			case 'd':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/goldMine1.png", squareWidth, squareHeight);
				break;
			case 'e':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/potion.png", squareWidth / 3,
						squareHeight / 3);
				break;
			case 'w':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/wizardTower1.png", squareWidth,
						squareHeight);
				break;
			case 't':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/tesla.png", squareWidth - 0.03,
						squareHeight - 0.03);
				break;
			case 'r':
				StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "images/hammer.png", squareWidth / 3,
						squareHeight / 3);
				break;
		}
	}

	/**
	 * met à jour chaque monstre de la liste des monstres à chaque update
	 */
	public void updateMonsters() {
		Iterator<Monster> i = monsters.iterator();
		Monster m;
		while (i.hasNext()) {
			m = i.next();

			// si le monstre est mort
			if (m.health <= 0) {
				gold += m.reward;
				i.remove();
			}

			// si le monstre est arrivé au chateau:
			double x = castle.x - m.p.x;
			double y = castle.y - m.p.y;
			double norme = Math.sqrt(x * x + y * y);
			if (norme <= 0.01) {
				// mode 1: si le golem arrive au chateau le joueur a perdu
				if (m instanceof Golem && round + 1 == roundMax && mode == 1) {
					i.remove();
					golem = true;
					life = 0;
					finished = true;
					break;
					// sinon le joueur perd de la vie
				} else {
					i.remove();
					life--;
					break;
				}
			}
			m.updateNextP(path);// eventuel update de nextP avant de faire bouger le monstre
			m.update();
		}
	}

	/**
	 * met à jour chaque projectile de la liste des projectiles à chaque update
	 */
	public void updateProjectiles() {

		// parcourt de la liste des tours
		for (Tower i : towers) {
			// eventuelle création d'un projectile
			Projectile m = i.radius(monsters, path);
			if (m != null)
				projectiles.add(m);
		}

		Iterator<Projectile> i = projectiles.iterator();
		Projectile m;
		while (i.hasNext()) {
			m = i.next();
			boolean touch = false;

			// si la cible du projectile est morte, le projectile est detruit
			if (m.target == null) {
				i.remove();
				break;
				// cas du poison:
			} else if (m instanceof Poison) {
				if (m.target == null) {
					i.remove();
					touch = true;
					break;
				}
				double x = m.target.p.x - m.p.x;
				double y = m.target.p.y - m.p.y;
				double norme = Math.sqrt(x * x + y * y);
				if (norme <= 0.05) {
					m.target.poisoned = true;
					i.remove();
					touch = true;
					break;
				}
				// cas du rayon electrique:
			} else if (m instanceof ElectricLine) {

				// si sa cible n'est pas detruite:
				if (m.target != null) {

					double x = m.target.p.x - m.p.x;
					double y = m.target.p.y - m.p.y;
					double norme = Math.sqrt(x * x + y * y);

					// si le monstre est en dehors de la portée de la tesla
					if (norme > ((ElectricLine) m).tower.reach || ((ElectricLine) m).tower == null) {
						((ElectricLine) m).tower.canShot = true;
						i.remove();
						touch = true;
						break;

					} else {
						// si le degat va tuer le monstre
						if (m.target.health - ((ElectricLine) m).tower.damage <= 0) {
							m.target.health -= ((ElectricLine) m).tower.damage;
							((ElectricLine) m).tower.canShot = true;
							i.remove();
							touch = true;
							break;

						} else {
							m.target.health -= ((ElectricLine) m).tower.damage;
						}
					}
					// sinon la tesla peut à nouveau tirer
				} else {
					((ElectricLine) m).tower.canShot = true;
					i.remove();
					touch = true;
					break;
				}

				// autres projectiles:
			} else {

				double x = m.target.p.x - m.p.x;
				double y = m.target.p.y - m.p.y;
				double norme = Math.sqrt(x * x + y * y);

				// si le projectile touche sa cible
				// hitbox = à 0.05
				if (norme <= 0.05) {
					m.target.health -= m.damage;
					i.remove();
					touch = true;
					break;
				}
			}

			// si le projectile n'a pas touché sa cible
			if (!touch) {
				// calcul de la norme du vecteur entre p et nextP
				// si elle est égale à 0, avec une tolerance de 0.02, c'est que le projectile
				// est arrivé
				double x = m.p.x - m.nextP.x;
				double y = m.p.y - m.nextP.y;
				double norme = Math.sqrt(x * x + y * y);
				if (norme <= 0.02) {
					i.remove();
					break;
				}
			}
			m.update();
		}
	}

	/**
	 * met à jour chaque tir de la liste des tirs à chaque update
	 */
	public void updateShot() {
		// parcourt de la liste des monstres
		for (Monster i : monsters) {
			if (i instanceof Witch) {
				// eventuelle creation d'un tir
				Projectile m = ((Witch) i).shot(towers);
				if (m != null)
					shots.add(m);
			}
		}
		Iterator<Projectile> i = shots.iterator();
		Projectile m;
		while (i.hasNext()) {
			m = i.next();

			boolean touch = false;

			for (Tower t : towers) {
				// calcul de la norme du vecteur entre p et nextP
				// si elle est égale à 0, avec une tolerance de 0.02, c'est que le projectile
				// est arrivé
				double x = m.p.x - t.p.x;
				double y = m.p.y - t.p.y;
				double norme = Math.sqrt(x * x + y * y);
				if (norme <= 0.02) {
					t.health -= 1;
					if (t.health <= 0)
						towers.remove(t);
					i.remove();
					touch = true;
					break;
				}
			}

			// si le tir n'a pas détruit sa cible
			if (!touch) {
				// calcul de la norme du vecteur entre p et nextP
				// si elle est égale à 0, avec une tolerance de 0.02, c'est que le projectile
				// est arrivé
				double x = m.p.x - m.nextP.x;
				double y = m.p.y - m.nextP.y;
				double norme = Math.sqrt(x * x + y * y);
				if (norme <= 0.02) {
					i.remove();
					break;
				}
			}
			m.update();
		}
	}

	/**
	 * affiche les tours à chaque update
	 */
	public void updateTower() {
		Iterator<Tower> i = towers.iterator();
		Tower m;
		while (i.hasNext()) {
			m = i.next();
			m.draw();
		}
	}

	/**
	 * met à jour chaque mine de la liste des mines d'or à chaque update
	 */
	public void uptdateGoldMines() {
		Iterator<GoldMine> i = goldmines.iterator();
		GoldMine m;
		while (i.hasNext()) {
			m = i.next();
			gold += m.gain();
			m.draw();
		}
	}

	/**
	 * met à jour toutes les informations du plateau de jeu, et update chaque tours,
	 * monstre, mine d'or et projectile
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
		drawBackground();
		drawPath();
		updateTower();
		drawMouse();
		uptdateGoldMines();
		drawInfos();

		if (!finished && canStart) {
			updateMonsters();
			updateProjectiles();
			updateShot();
		}

		// condition de victoire
		if (!golem && round == roundMax - 1 && monsters.size() == 0 && mode == 1 && canStart) {
			finished = true;
			win();
		}
		// condition de defaite
		if (life <= 0 && canStart) {
			finished = true;
			gameOver();
		}
		return life;
	}

	/**
	 * affiche l'ecran de victoire
	 */
	public void win() {
		StdDraw.setPenColor(StdDraw.RED);
		Font font = new Font("Arial", Font.BOLD, 50);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.5, "Victoire !");

		font = new Font("Arial", Font.BOLD, 25);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.45, "Vous avez survécu à " + (round + 1) + " vagues");

		StdDraw.setPenColor(StdDraw.WHITE);
		font = new Font("Arial", Font.ITALIC, 15);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.4, "Appuyez sur Q pour quitter");
	}

	/**
	 * affiche l'ecran de defaite
	 */
	public void gameOver() {
		StdDraw.setPenColor(StdDraw.RED);
		Font font = new Font("Arial", Font.BOLD, 50);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.5, "Game Over !");

		font = new Font("Arial", Font.BOLD, 25);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.45, "Vous avez survécu à " + (round + 1) + " vagues");

		StdDraw.setPenColor(StdDraw.WHITE);
		font = new Font("Arial", Font.ITALIC, 15);
		StdDraw.setFont(font);
		StdDraw.text(0.5, 0.4, "Appuyez sur Q pour quitter");
	}

	/**
	 * Récupère la touche appuyée par l'utilisateur
	 * 
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
			case 's':
				canStart = true;
				break;
			case 'q':
				end = true;
				break;
			// cheatcodes:
			case 'o':
				life = 10000;
				break;
			case 'k':
				gold = 10000;
				break;
		}
	}

	/**
	 * Effectue differentes actions en fonction de @key à chaque clic de souris
	 * 
	 * @param x abscisse de la souris
	 * @param y ordonnee de la souris
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int) (x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (y / squareHeight) * squareHeight + squareHeight / 2;
		// position de la souris
		Position p = new Position(normalizedX, normalizedY);
		switch (key) {
			// pose d'une tour
			case 'a': {
				ArrowTower arrowTower = new ArrowTower(p, squareWidth, squareHeight);
				// si le joueur a assez d'or et si la case est libre
				if (gold >= arrowTower.price && free(p)) {
					towers.add(arrowTower);
					gold -= arrowTower.price;
					key = ' '; // quand la tour est posée on enlève l'image qui suit la souris
				}
			}
				break;
			// pose d'un canon
			case 'b':
				CannonTower cannonTower = new CannonTower(p, squareWidth, squareHeight);
				if (gold >= cannonTower.price && free(p)) {
					towers.add(cannonTower);
					gold -= cannonTower.price;
					key = ' ';
				}

				break;
			// pose d'une mine d'or
			case 'd':
				GoldMine goldmine = new GoldMine(p, squareWidth, squareHeight);
				if (gold >= goldmine.price && free(p)) {
					goldmines.add(goldmine);
					gold -= goldmine.price;
					key = ' ';
				}

				break;
			// pose d'une tour aerienne
			case 'c':
				AirTower airTower = new AirTower(p, squareWidth, squareHeight);
				if (gold >= airTower.price && free(p)) {
					towers.add(airTower);
					gold -= airTower.price;
					key = ' ';
				}

				break;
			// pose d'une tour de sorcier
			case 'w':
				WizardTower wizardTower = new WizardTower(p, squareWidth, squareHeight);
				if (gold >= wizardTower.price && free(p)) {
					towers.add(wizardTower);
					gold -= wizardTower.price;
					key = ' ';
				}

				break;
			// pose d'une tesla
			case 't':
				Tesla tesla = new Tesla(p, squareWidth, squareHeight);
				if (gold >= tesla.price && free(p)) {
					towers.add(tesla);
					gold -= tesla.price;
					key = ' ';
				}

				break;
			// ameliorations:
			case 'e':
				// amelioration des tours
				for (Tower i : towers) {
					// si la tour est à la position de la souris et que son lvl < 3
					if (i.p.equals(p) && i.level < 3 && !(i instanceof Tesla)) {
						// si on a assez d'or en fonction de son lvl:
						if (gold >= 30 + i.level * 10) {
							gold -= 30 + i.level * 10;
							i.level++;
							i.health = 3;
							i.damage += 1.5;
							i.speed -= 3;
							i.reach += 0.05;
						}
					}
				}

				// ameliroration des mines d'or
				for (GoldMine i : goldmines) {
					if (i.p.equals(p) && i.level < 3) {
						if (gold >= 30 + i.level * 10) {
							gold -= 30 + i.level * 10;
							i.level++;
							i.speed -= 15;
							i.gain += 15;
						}
					}
				}

				break;

			// réparation d'un batiment
			case 'r':
				for (Tower i : towers) {
					if (i.p.equals(p) && i.health < 3) {
						// si on a assez d'or
						if (gold >= 30) {
							gold -= 30;
							i.health = 3;
						}
					}
				}
				break;
		}
	}

	/**
	 * @param p une position à verifier
	 * @return vrai si la position est libre, faux sinon
	 */
	public boolean free(Position p) {

		// on regarde si il y a une tour sur la position
		for (Tower i : towers) {
			if (i.p.equals(p))
				return false;
		}

		// on regarde si il a une mine d'or sur la position
		for (GoldMine i : goldmines) {
			if (i.p.equals(p))
				return false;
		}

		// on regarde si il a du chemin sur la position
		for (Position i : path) {
			if (i.equals(p))
				return false;
		}
		return true;
	}

	/**
	 * met à jour les informations de jeu à chaque nouvelle vague
	 */
	public void resetWave() {
		endWave = false;
		monsterTime = 0;
		indexMonster = 0;
		round++;
		if (round % 2 == 0)
			difficulty++;
	}

	/**
	 * met à jour la vague à chaque update
	 */
	public void updateWave() {
		if (!endWave) {
			List<Monster> waveMonsters = null;
			// envoi d'un monstre tout les 20 updates
			if (monsterTime == 20)
				monsterTime = 0;

			// règles de vague en fonction de l'avancé du joueur
			if (round == 0) {
				Wave w = new Wave(2, spawn, 0);
				waveMonsters = w.generateBase();
			} else if (round == 1) {
				Wave w = new Wave(2, spawn, 0);
				waveMonsters = w.generateFlying();
			} else if (round == 2) {
				Wave w = new Wave(4, spawn, 1);
				waveMonsters = w.generateBaseFlying();
			} else if (round == 3) {
				difficulty = 0;
				Wave w = new Wave(5, spawn, 1);
				waveMonsters = w.generateBaseFlying();
			} else if (round == 4) {
				Wave w = new Wave(6, spawn, difficulty);
				waveMonsters = w.generateRandom();
			} else if (round == roundMax - 1) {
				Wave w = new Wave(10, spawn, difficulty);
				waveMonsters = w.generateGolem();
			} else if (round > 30) {
				Wave w = new Wave(5 + round, spawn, difficulty);
				waveMonsters = w.generateRandomWithGolem();
			} else {
				Wave w = new Wave(5 + round, spawn, difficulty);
				waveMonsters = w.generateRandom();
			}

			// ajoute d'un monstre à la liste de monstre
			if (monsterTime == 0 && indexMonster < waveMonsters.size()) {
				monsters.add(waveMonsters.get(indexMonster));
				indexMonster++;
			}

			monsterTime++;

			// condition de fin de manche
			if (monsters.size() == 0 && indexMonster >= waveMonsters.size()) {
				endWave = true;
				needGold = true;
			}
		}
	}

	/**
	 * affiche dans le terminal les informations pour jouer
	 */
	public void printCommands() {
		System.out.println("/**********Commandes***********/");
		System.out.println("A pour selectionner une tour d'archers (coût: 50 d'or).");
		System.out.println("B pour selectionner un canon (coût: 60 d'or).");
		System.out.println("C pour selectionner une tour anti-aerienne (coût: 60 d'or).");
		System.out.println("D pour selectionner une mine d'or (coût: 50 d'or).");
		System.out.println("W pour selectionner une tour de sorcier (coût: 50 d'or).");
		System.out.println("T pour selectionner une tesla (coût: 50 d'or).");
		System.out.println("E pour ameliorer une tour (coût: 40 d'or pour le lvl 2, et 60 d'or pour le lvl 3).");
		System.out.println("R pour reparer une tour (coût: 30 d'or).");
		System.out.println();
	}

	/**
	 * Récupère la position de la souris et met à jour le plateau en fonction de ses
	 * interactions
	 */
	public void run() {
		printCommands();
		while (!end) {

			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}

			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			// update d'une vague si le jeu a comencé et si il n'est pas fini
			if (!finished && canStart) {

				if (endWave) {
					waiting++;
				}
				if (needGold) {
					if (round == 8)
						gold += 120;
					else
						gold += 50;
					needGold = false;
				}
				// quand le compteur est arrivé à 150 iterations, on lance une vague
				if (waiting == 150) {
					waiting = 0;
					resetWave();
				}
				updateWave();
			}
			update();

			StdDraw.show();
			StdDraw.pause(20);
		}
	}
}
