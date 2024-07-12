package warcraftTD;

public abstract class Projectile {

	// vitesse du projectile
	protected double speed;

	// degats infigés par le projectile
	protected double damage;

	// position du projectile
	protected Position p;

	// position du projectile à l'insant t+1
	protected Position nextP;

	// angle de rotation pour l'affichage de l'image, pour les fleches et les fusées
	protected Double rotation;

	// monstre ciblé par le projectile
	protected Monster target;

	/**
	 * initialise le projectile en fonction de:
	 * 
	 * @param speed
	 * @param damage
	 * @param p
	 * @param nextP
	 */
	public Projectile(double speed, double damage, Position p, Position nextP) {
		this.speed = speed;
		this.damage = damage;
		this.p = p;
		this.nextP = nextP;
	}

	/**
	 * Déplace le projectile en fonction de sa vitesse sur l'axe des x et des y et
	 * de sa prochaine position
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
	 * dessine le projectile
	 */
	public abstract void draw();

	/**
	 * met à jour le projectile à chaque update
	 */
	public void update() {
		move();
		draw();
	}
}
