package warcraftTD;

public class FlyingMonster extends Monster {

	public FlyingMonster(Position p) {
		super(p, 0.02, 8, 3);
	}

	@Override
	public void draw() {
		if (!rotationed)
			StdDraw.picture(p.x, p.y, "images/gargoyle.png", 0.05, 0.05);
		else
			StdDraw.picture(p.x, p.y, "images/gargoyle2.png", 0.05, 0.05);
	}
}
