package iut.gon.gribouille_tp1.controleurs;

import iut.gon.gribouille_tp1.modele.Etoile;
import iut.gon.gribouille_tp1.modele.Figure;

public class OutilEtoile extends Outil {
    public OutilEtoile(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePress(double x, double y) {
        javafx.scene.paint.Color c = controleur.couleur.get();
        String couleurHex = String.format("#%02x%02x%02x",
            (int)(c.getRed()   * 255),
            (int)(c.getGreen() * 255),
            (int)(c.getBlue()  * 255));
        Etoile etoile = new Etoile(controleur.epaisseur.get(), couleurHex, x, y);
        controleur.dessin.addFigure(etoile);
        controleur.figureCourante = etoile;
    }

    @Override
    public void onMouseDrag(double x, double y) {
        Etoile etoile = (Etoile) controleur.figureCourante;
        controleur.dessinController.trace(
            etoile.getCentre().getX(), etoile.getCentre().getY(),
            x, y
        );
        etoile.addPoint(x, y);
    }

    @Override
    public void setFigureCourante(Figure figure) {
    }
}