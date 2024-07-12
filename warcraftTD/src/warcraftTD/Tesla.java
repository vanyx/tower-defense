package warcraftTD;

import java.util.*;

public class Tesla extends Tower {

    boolean canShot = true;

    public Tesla(Position p, double squareWidth, double squareHeight) {
        super(50, 0, 0.2, p, 1, squareWidth, squareHeight);
        damage = 0.1;
    }

    @Override
    public Projectile radius(List<Monster> monsters, List<Position> path) {
        // si la tesla n'est pas deja en train de tirer un rayon:
        if (canShot) {
            for (Monster i : monsters) {

                // cacul la norme du vecteur entre la tour et le monstre
                // si elle est <= à la portée, on crée un projectile
                double x = i.p.x - p.x;
                double y = i.p.y - p.y;
                double norme = Math.sqrt(x * x + y * y);
                if (norme <= reach) {
                    // la tesla ne peut donc plus tirer d'autres rayon
                    canShot = false;
                    Position tower = new Position(p.x, p.y + 0.015);
                    // nextP du rayon correspond à la position du monstre à chaque update
                    Projectile shot = new ElectricLine(tower, i.p, i, this);
                    return shot;
                }
            }
        }
        return null;
    }

    @Override
    public void draw() {
        StdDraw.picture(p.x, p.y, "images/tesla.png", squareWidth - 0.03, squareHeight - 0.03);

        // affichage de la vie
        if (p.y <= 0.1)
            StdDraw.picture(p.x, p.y - 0.03, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);
        else
            StdDraw.picture(p.x, p.y - 0.05, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);

    }
}
