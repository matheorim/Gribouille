package iut.gon.gribouille_tp1.controleurs;

import iut.gon.gribouille_tp1.modele.Trace;

public class OutilCrayon extends Outil {
    private Trace traceEnCours;

    public OutilCrayon(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePress(double x, double y) {
        javafx.scene.paint.Color c = controleur.couleur.get();
        String couleurHex = String.format("#%02x%02x%02x",
            (int)(c.getRed()   * 255),
            (int)(c.getGreen() * 255),
            (int)(c.getBlue()  * 255));
        traceEnCours = new Trace(controleur.epaisseur.get(), couleurHex, x, y);
        controleur.dessin.addFigure(traceEnCours);
        controleur.figureCourante = traceEnCours;
    }

    @Override
    public void onMouseDrag(double x, double y) {
        controleur.dessinController.trace(controleur.precX.get(), controleur.precY.get(), x, y);
        traceEnCours.addPoint(x, y);
    }
}