package iut.gon.gribouille_tp1.controleurs;

import java.util.List;

import iut.gon.gribouille_tp1.Dialogues;
import iut.gon.gribouille_tp1.modele.Dessin;
import iut.gon.gribouille_tp1.modele.Etoile;
import iut.gon.gribouille_tp1.modele.Figure;
import iut.gon.gribouille_tp1.modele.Point;
import iut.gon.gribouille_tp1.modele.Trace;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.application.Platform;

public class Controleur {
    public final Dessin dessin = new Dessin();
    public Figure figureCourante;
    public final SimpleObjectProperty<Double> precX = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Double> precY = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);

    @FXML public MenusControleur menusController;
    @FXML public StatutControleur statutController;
    @FXML public CouleursControleur couleursController;
    @FXML public DessinControleur dessinController;

    @FXML
    public void initialize() {
        if (menusController != null) menusController.setControleur(this);
        if (statutController != null) statutController.setControleur(this);
        if (couleursController != null) couleursController.setControleur(this);
        if (dessinController != null) dessinController.setControleur(this);
    }

    public boolean onQuitter() {
        return Dialogues.confirmation();
    }

    public Outil outil = new OutilCrayon(this);

    public void onMousePress(double x, double y) {
        precX.set(x);
        precY.set(y);
        outil.onMousePress(x, y);
    }

    public void onMouseMove(double x, double y) {
        precX.set(x);
        precY.set(y);
    }

    public void onMouseDrag(double x, double y) {
        outil.onMouseDrag(x, y);
        precX.set(x);
        precY.set(y);
    }

    public void onCrayon() {
        outil = new OutilCrayon(this);
        statutController.labelOutil.setText("Crayon");
    }

    public void onEtoile() {
        outil = new OutilEtoile(this);
        statutController.labelOutil.setText("Étoile");
    }

    public void dessine() {
        dessinController.efface();
        for (Figure figure : dessin.getFigures()) {
            if (figure instanceof Trace) {
                List<Point> points = figure.getPoints();
                for (int i = 1; i < points.size(); i++) {
                    dessinController.trace(
                        points.get(i-1).getX(), points.get(i-1).getY(),
                        points.get(i).getX(), points.get(i).getY()
                    );
                }
            } else if (figure instanceof Etoile) {
                Etoile etoile = (Etoile) figure;
                for (Point p : etoile.getPoints()) {
                    dessinController.trace(
                        etoile.getCentre().getX(), etoile.getCentre().getY(),
                        p.getX(), p.getY()
                    );
                }
            }
        }
    }
    
    public void setEpaisseur(int e) {
        System.out.println("setEpaisseur appelé avec " + e);
        epaisseur.set(e);
        dessinController.setEpaisseur(e);
    }
    
    public void setCouleur(Color c) {
        couleur.set(c);
        dessinController.setCouleur(c);
    }
}