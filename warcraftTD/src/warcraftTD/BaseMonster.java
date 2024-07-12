package warcraftTD;

public class BaseMonster extends Monster {

	public BaseMonster(Position p) {
		super(p, 0.01, 5, 5);
	}

	@Override
	public void draw() {
		if (!rotationed)
			StdDraw.picture(p.x, p.y, "images/barbare.png", 0.06, 0.06);
		else
			StdDraw.picture(p.x, p.y, "images/barbare2.png", 0.06, 0.06);
	}
}
