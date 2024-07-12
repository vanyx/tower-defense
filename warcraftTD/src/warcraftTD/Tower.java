package warcraftTD;

import java.util.*;

public abstract class Tower {

	// prix
	protected int price;

	// temps entre chaque tir
	protected int speed;

	// portée de la tour
	protected double reach;

	// position de la tour
	protected Position p;

	// niveau de la tour
	protected int level;

	// compteur, utlisé comme chargement entre chaque tir
	protected int reload;

	// vie de la tour
	protected int health = 3;

	// degat qu'inflige un projectile tiré par la tour
	protected double damage;

	// dimensions d'une case du plateau
	protected double squareWidth;
	protected double squareHeight;

	/**
	 * initialise une tour en fonction de:
	 * 
	 * @param price
	 * @param speed
	 * @param reach
	 * @param p
	 * @param level
	 * @param squareWidth
	 * @param squareHeight
	 */
	public Tower(int price, int speed, double reach, Position p, int level, double squareWidth, double squareHeight) {
		this.price = price;
		this.speed = speed;
		this.reload = speed;
		this.reach = reach;
		this.p = p;
		this.level = level;
		this.squareWidth = squareWidth;
		this.squareHeight = squareHeight;
	}

	/**
	 * dessine la tour ainsi que sa barre de vie
	 */
	public abstract void draw();

	/**
	 * @param monsters la liste des monstres du plateau
	 * @param path     la liste des cases de chemin
	 * @return un Projectile si un monstre se trouve dans le rayon de la tour, null
	 *         sinon
	 */
	public abstract Projectile radius(List<Monster> monsters, List<Position> path);

	/**
	 * @param path la liste des cases de chemin
	 * @param m    un monstre
	 * @return une Position, qui correspond à une eventuelle anticipation de la
	 *         prochaine position du monstre, la position du monstre sinon
	 */
	public Position anticipate(List<Position> path, Position m) {
		// parcourt de la liste de case de chemin
		for (int i = 0; i < path.size(); i++) {
			// calcul de la norme du vecteur entre la case de chemin et le monstre
			double x = path.get(i).x - m.x;
			double y = path.get(i).y - m.y;
			double norme = Math.sqrt(x * x + y * y);
			// si la norme est égale à 0, avec une tolerance de 0.01
			// c'est que le monstre est sur cette case de chemin
			// et si la case i+1 existe, on renvoie la position du chemin à i+1
			if (norme <= 0.01 && path.get(i + 1) != null)
				return path.get(i + 1);
		}
		// si aucune anticipation n'est trouvée, on renvoie la position du monstre
		return m;
	}
}
