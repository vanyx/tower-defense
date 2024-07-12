package warcraftTD;

import java.awt.Font;

public class Button {
    // position du bouton
    protected Position p;

    // largeur du bouton
    protected double width;

    // hauteur du bouton
    protected double height;

    // condition pour savoir si la souris est sur le bouton
    protected boolean above = false;

    // information du bouton
    protected String info;

    // condition pour savoir si le bouton a été selectionné
    protected boolean selected = false;

    /**
     * initialisation du bouton en fonction de:
     * 
     * @param p      sa position
     * @param width  sa largeur
     * @param height sa hauteur
     * @param info   son info
     */
    public Button(Position p, double width, double height, String info) {
        this.p = p;
        this.width = width;
        this.height = height;
        this.info = info;
    }

    /**
     * met à jour le bouton
     * 
     * @param mouse la position de la souris
     */
    public void update(Position mouse) {
        isAbove(mouse);
        draw();
    }

    /**
     * change la valeur du boolean isAbove si la souris est sur le bouton
     * 
     * @param mouse la position de la souris
     */
    public void isAbove(Position mouse) {
        if ((mouse.x < p.x + width && mouse.x > p.x - width) && (mouse.y < p.y + height && mouse.y > p.y - height)) {
            above = true;
        } else
            above = false;
    }

    /**
     * dessine le bouton
     */
    public void draw() {
        // si le bouton est selectionné on l'affiche en vert
        if (selected) {
            StdDraw.setPenColor(154, 252, 135);
            StdDraw.filledRectangle(p.x, p.y, width, height);
        } else {
            // affichage ou non de la mise en valeur blanche du bouton si la souris est
            // dessus
            if (above) {
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledRectangle(p.x, p.y, width + 0.002, height + 0.002);
            }

            // affichage du bouton
            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledRectangle(p.x, p.y, width, height);

        }
        // affichage de l'information du bouton
        StdDraw.setPenColor(StdDraw.BLACK);

        Font font = new Font("Calibri", Font.LAYOUT_NO_START_CONTEXT, 20);
        StdDraw.setFont(font);
        StdDraw.text(p.x, p.y, String.valueOf(info));
    }
}
