package warcraftTD;

import java.util.List;

public abstract class Monster {
	// Position du monstre à l'instant t
	Position p;

	// Vitesse du monstre
	double speed;

	// Recompense lorsque le monstre est tué
	int reward;

	// Points de vie du monstre
	double health;

	// Position du monstre à l'instant t+1
	Position nextP;

	// indique si le monstre à atteint le chateau
	boolean reached;

	// Compteur de déplacement pour savoir si le monstre à atteint le chateau
	int checkpoint = 0;

	// Compteur de "case de chemin" atteints
	int comptPath = 0;

	// indique si le monstre est empoisonné
	boolean poisoned = false;

	// utilisé pour savoir quelle image de monstre afficher, en fonction de son sens
	// de deplacement
	boolean rotationed = false;

	/**
	 * initialise le monstre, en fonction de:
	 * 
	 * @param p
	 * @param speed
	 * @param reward
	 * @param health
	 */
	public Monster(Position p, double speed, int reward, int health) {
		this.p = p;
		this.nextP = new Position(p);
		this.speed = speed;
		this.reward = reward;
		this.health = health;
	}

	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de
	 * sa prochaine position.
	 */
	public void move() {

		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0) {
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx / (Math.abs(dx) + Math.abs(dy));
			double ratioY = dy / (Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}

	/**
	 * met à jour le monstre à chaque update
	 */
	public void update() {
		if (poisoned)
			health -= 0.03;
		rotation();
		move();
		draw();
		checkpoint++;
	}

	/**
	 * attribu le prochain nextP lorsque le monstre est arrivé à nextP
	 * 
	 * @param path la liste des cases de chemin
	 */
	public void updateNextP(List<Position> path) {
		// si la norme du vecteur entre p et nexP vaut 0 avec une tolerance de 0.01,
		// c'est que le monstre est arrivé à nextP, donc on change son prochain nextP
		double x = p.x - nextP.x;
		double y = p.y - nextP.y;
		double norme = Math.sqrt(x * x + y * y);
		if (norme <= 0.01) {
			nextP = path.get(comptPath);
			comptPath++;
		}
	}

	/**
	 * determine dans quelle sens en abscisse se deplace le monstre, utilisé pour
	 * savoir quelle image de monstre afficher
	 */
	public void rotation() {
		if (p.x - nextP.x <= 0)
			rotationed = false;
		else
			rotationed = true;
	}

	/**
	 * dessine le monstre
	 */
	public abstract void draw();
}
