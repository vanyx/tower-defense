package warcraftTD;

public class Arrow extends Projectile {

	public Arrow(Position p, Position nextP, Monster target, double damage) {
		super(0.04, damage, p, nextP);
		this.target = target;
	}

	@Override
    public void draw() {
		StdDraw.picture(p.x, p.y, "images/arrow.png", 0.07,0.07,rotation);
	}
}
