package iut.gon.gribouille_tp1.controleurs;

import java.util.List;

import iut.gon.gribouille_tp1.Dialogues;
import iut.gon.gribouille_tp1.modele.Dessin;
import iut.gon.gribouille_tp1.modele.Figure;
import iut.gon.gribouille_tp1.modele.Point;
import iut.gon.gribouille_tp1.modele.Trace;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Controleur {
    public final Dessin dessin = new Dessin();
    public Figure figureCourante;
    public final SimpleObjectProperty<Double> precX = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Double> precY = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);

    @FXML public MenusControleur menusControleur;
    @FXML public StatutControleur statutControleur;
    @FXML public CouleursControleur couleursControleur;
    @FXML public DessinControleur dessinControleur;

    @FXML
    public void initialize() {
        if (menusControleur != null) menusControleur.setControleur(this);
        if (statutControleur != null) statutControleur.setControleur(this);
        if (couleursControleur != null) couleursControleur.setControleur(this);
        if (dessinControleur != null) dessinControleur.setControleur(this);
    }

    public boolean onQuitter() {
        return Dialogues.confirmation();
    }
    
    public void onMousePress(double x, double y) {
        precX.set(x);
        precY.set(y);
        javafx.scene.paint.Color c = couleur.get();
        String couleurHex = String.format("#%02x%02x%02x",
            (int)(c.getRed()   * 255),
            (int)(c.getGreen() * 255),
            (int)(c.getBlue()  * 255));
        figureCourante = new Trace(epaisseur.get(), couleurHex, x, y);
        dessin.addFigure(figureCourante);
    }

    public void onMouseMove(double x, double y) {
        precX.set(x);
        precY.set(y);
    }

    public void onMouseDrag(double x, double y) {
        dessinControleur.trace(precX.get(), precY.get(), x, y);
        figureCourante.addPoint(x, y);
        precX.set(x);
        precY.set(y);
    }

    public void dessine() {
        dessinControleur.efface();
        for (Figure figure : dessin.getFigures()) {
            if (figure instanceof Trace) {
                List<Point> points = figure.getPoints();
                for (int i = 1; i < points.size(); i++) {
                    dessinControleur.trace(
                        points.get(i-1).getX(), points.get(i-1).getY(),
                        points.get(i).getX(),   points.get(i).getY()
                    );
                }
            }
        }
    }
}