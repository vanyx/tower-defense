package warcraftTD;

import java.util.*;

public class AirTower extends Tower {

	public AirTower(Position p, double squareWidth, double squareHeight) {
		super(60, 20, 0.2, p, 1, squareWidth, squareHeight);
		damage = 8;
	}

	@Override
	public Projectile radius(List<Monster> monsters, List<Position> path) {
		// si le compteur de chargement a atteint le temps entre chaque tir:
		if (reload >= speed) {
			for (Monster i : monsters) {
				// la tour aerienne ne peut tirer que sur les FlyingMonster
				if (i instanceof FlyingMonster) {

					// cacul la norme du vecteur entre la tour et le monstre
					// si elle est <= à la portée, on crée un projectile
					double x = i.p.x - p.x;
					double y = i.p.y - p.y;
					double norme = Math.sqrt(x * x + y * y);
					if (norme <= reach) {
						Position tower = new Position(this.p);
						// la position initiale du projectile est la position de la tour
						// nextP du missile teleguidé est la position du monstre à chaque uptdate
						Projectile shot = new Rocket(tower, i.p, i, damage);
						
						reload = 0;
						return shot;
					}
				}
			}
		}
		// si on ne peut pas tirer, on incremente le compteur du temps de chargement
		reload++;
		return null;
	}

	@Override
	public void draw() {
		StdDraw.picture(p.x, p.y, "images/airTower" + level + ".png", squareWidth, squareHeight);

		// affichage de la vie
		if (p.y <= 0.1)
			StdDraw.picture(p.x, p.y - 0.03, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);
		else
			StdDraw.picture(p.x, p.y - 0.05, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);
	}
}
