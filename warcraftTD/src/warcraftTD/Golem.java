package warcraftTD;

public class Golem extends Monster {

	public Golem(Position p) {
		super(p, 0.0035, 50, 400);
	}

	@Override
	public void draw() {
		if (!rotationed)
			StdDraw.picture(p.x, p.y, "images/golem.png", 0.1, 0.1);
		else
			StdDraw.picture(p.x, p.y, "images/golem2.png", 0.1, 0.1);
	}
}
