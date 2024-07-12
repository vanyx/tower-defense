package warcraftTD;

import java.util.*;

public class WizardTower extends Tower {

	public WizardTower(Position p, double squareWidth, double squareHeight) {
		super(50, 15, 0.3, p, 1, squareWidth, squareHeight);
	}

	@Override
	public Projectile radius(List<Monster> monsters, List<Position> path) {
		// si le compteur de chargement a atteint le temps entre chaque tir:
		if (reload >= speed) {
			for (Monster i : monsters) {
				// un monstre ne peut pas etre empoisonné plusieurs fois
				if (!i.poisoned) {

					// cacul la norme du vecteur entre la tour et le monstre
					// si elle est <= à la portée, on crée un projectile
					double x = i.p.x - p.x;
					double y = i.p.y - p.y;
					double norme = Math.sqrt(x * x + y * y);

					if (norme <= reach) {
						Position tower = new Position(this.p);
						Position monster = new Position(i.p);
						Position nextMove = anticipate(path, monster);
						// la position initiale du projectile est la position de la tour
						// nextP du projectile correspond à une eventuelle anticipation de la prochaine
						// position du monstre, sa position actuelle sinon
						Projectile shot = new Poison(tower, nextMove, i);

						// calcul de l'angle de rotation pour l'affichage de l'image du poison
						// angle = arcTan(y/x) en radian
						double xshot = shot.p.x - shot.nextP.x;
						double yshot = shot.p.y - shot.nextP.y;
						double rotation;
						// si le monstre est à droite de la tour, + PI pour la rotation
						// (definition d'arcTangente)
						if (xshot >= 0)
							rotation = Math.toDegrees(Math.atan(yshot / xshot));
						else
							rotation = Math.toDegrees(Math.atan(yshot / xshot) + Math.PI);
						shot.rotation = rotation;

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
		StdDraw.picture(p.x, p.y, "images/wizardTower" + level + ".png", squareWidth, squareHeight);

		// affichage de la vie
		if (p.y <= 0.1)
			StdDraw.picture(p.x, p.y - 0.03, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);
		else
			StdDraw.picture(p.x, p.y - 0.05, "images/health" + health + ".png", squareWidth / 2, squareHeight / 2);
	}
}
