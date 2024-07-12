package warcraftTD;

public class Bomb extends Projectile {

	public Bomb(Position p, Position nextP, Monster target, double damage) {
		super(0.02, damage, p, nextP);
		this.target = target;
	}

	@Override
	public void draw() {
		StdDraw.picture(p.x, p.y, "images/bomb.png", 0.07, 0.07);
	}
}
