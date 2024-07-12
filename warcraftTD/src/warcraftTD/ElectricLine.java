package warcraftTD;

public class ElectricLine extends Projectile {

    // tesla qui tire l'instance du rayon electrique
    protected Tesla tower;

    public ElectricLine(Position p, Position nextP, Monster target, Tesla tower) {
        super(0, tower.damage, p, nextP);
        this.target = target;
        this.tower = tower;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(0, 239, 255);
        StdDraw.setPenRadius(0.006);
        StdDraw.line(p.x, p.y, nextP.x, nextP.y);
    }

    @Override
    public void update() {
        draw();
    }
}
