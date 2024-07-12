package warcraftTD;

import java.util.*;

public class Witch extends BaseMonster {

    // temps entre chaque tir
    protected int speedShot;

    // compteur, utlisé comme chargement entre chaque tir
    protected int reload;

    // portée de la sorciere
    protected double reach;

    public Witch(Position p) {
        super(p);
        speedShot = 20;
        reach = 0.2;
        reward = 7;
    }

    @Override
    public void draw() {
        if (!rotationed)
            StdDraw.picture(p.x, p.y, "images/witch.png", 0.04, 0.04);
        else
            StdDraw.picture(p.x, p.y, "images/witch2.png", 0.04, 0.04);
    }

    /**
     * @param towers la liste des tours
     * @return un projectile si possible, null sinon
     */
    public Projectile shot(List<Tower> towers) {
        // si le compteur de chargement a atteint le temps entre chaque tir:
        if (reload >= speedShot) {
            for (Tower i : towers) {

                // cacul la norme du vecteur entre la sorciere et la tour
                // si elle est <= à la portée, on crée un projectile
                double x = i.p.x - p.x;
                double y = i.p.y - p.y;
                double norme = Math.sqrt(x * x + y * y);

                if (norme <= reach) {
                    Position archer = new Position(this.p);
                    Position tower = new Position(i.p);
                    Projectile shot = new FireBall(archer, tower);

                    reload = 0;
                    return shot;
                }
            }
        }
        // si on ne peut pas tirer, on incremente le compteur du temps de chargement
        reload++;
        return null;
    }
}
