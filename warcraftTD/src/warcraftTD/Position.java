package warcraftTD;

public class Position {

	// abscisse
	protected double x;

	// ordonnee
	protected double y;

	/**
	 * initialise une position en fonction de ses coordonnées
	 * 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * initialise une position à partir d'une autre position
	 * 
	 * @param p l'autre position
	 */
	public Position(Position p) {
		x = p.x;
		y = p.y;
	}

	/**
	 * @param p une position
	 * @return vrai si la position p est egal à celle de l'instance, faux sinon
	 */
	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * 
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
}
