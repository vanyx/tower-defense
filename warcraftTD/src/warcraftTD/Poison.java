package warcraftTD;

public class Poison extends Projectile {

	public Poison(Position p, Position nextP, Monster target) {
		super(0.04, 2.0, p, nextP);
		this.target = target;
	}

	@Override
	public void draw() {
		StdDraw.picture(p.x, p.y, "images/poison.png", 0.04, 0.04, rotation);
	}
}
