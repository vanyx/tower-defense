package warcraftTD;

public class Rocket extends Projectile {

	public Rocket(Position p, Position nextP, Monster target, double damage) {
		super(0.02, damage, p, nextP);
		this.target = target;
	}

	@Override
	public void draw() {
		// on doit calculer l'angle de rotation de l'image à chaque update:
		// angle = arcTan(y/x) en radian
		double x = p.x - nextP.x;
		double y = p.y - nextP.y;
		double rotation;
		// si le monstre est à droite de la rocket, + PI pour la rotation
		// (definition d'arcTangente)
		if (x >= 0)
			rotation = Math.toDegrees(Math.atan(y / x));
		else
			rotation = Math.toDegrees(Math.atan(y / x) + Math.PI);

		StdDraw.picture(p.x, p.y, "images/rocket.png", 0.05, 0.05, rotation);
	}
}
