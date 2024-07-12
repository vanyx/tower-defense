package warcraftTD;

public class GoldMine {

    // position de la mine dor
    protected Position p;

    // prix
    protected int price = 50;

    // niveau
    protected int level = 1;

    // temps entre chaque gain
    protected int speed = 150;

    // gain
    protected int gain = 20;

    // compteur pour le "chargement" avant d'avoir le gain
    protected int reload;

    // dimensions d'une case de chemin
    protected double squareWidth;
    protected double squareHeight;

    /**
     * initialisation de la mine d'or en fonction de:
     * 
     * @param p            sa position
     * @param squareWidth
     * @param squareHeight
     */
    public GoldMine(Position p, double squareWidth, double squareHeight) {
        this.p = p;
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
    }

    /**
     * @return le gain ou non de la mine d'or
     */
    public int gain() {
        // si le compteur a atteint le "temps de rechargement" le joueur gagne le gain
        if (reload >= speed) {
            reload = 0;
            return gain;
        }
        reload++;
        return 0;
    }

    /**
     * dessine la mine d'or, en fonction de son niveau
     */
    public void draw() {
        StdDraw.picture(p.x, p.y, "images/goldMine" + level + ".png", squareWidth, squareHeight);
    }
}
