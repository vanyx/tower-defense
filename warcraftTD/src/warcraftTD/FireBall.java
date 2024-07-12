package warcraftTD;

public class FireBall extends Projectile {

	public FireBall(Position p, Position nextP) {
		super(0.04, 2.0, p, nextP);
	}

	@Override
	public void draw() {
		StdDraw.picture(p.x, p.y, "images/fireBall.png", 0.04, 0.04);
	}
}
