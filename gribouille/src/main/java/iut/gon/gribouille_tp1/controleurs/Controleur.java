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
        if (figureCourante != null) {
            outil.onMousePress(precX.get(), precY.get());
        }
        statutController.labelOutil.setText("Crayon");
    }

    public void onEtoile() {
        outil = new OutilEtoile(this);
        if (figureCourante != null) {
            outil.onMousePress(precX.get(), precY.get());
        }
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
        epaisseur.set(e);
        dessinController.setEpaisseur(e);        if (figureCourante != null) {
            Figure nouvelleFigure = figureCourante.changeEpaisseur(e);
            dessin.addFigure(nouvelleFigure);
            figureCourante = nouvelleFigure;
            outil.setFigureCourante(nouvelleFigure);
        }
    }    
    public void setCouleur(Color c) {
        couleur.set(c);
        dessinController.setCouleur(c);
        if (figureCourante != null) {
            Figure nouvelleFigure = figureCourante.changeCouleur(
                String.format("#%02x%02x%02x",
                    (int)(c.getRed()   * 255),
                    (int)(c.getGreen() * 255),
                    (int)(c.getBlue()  * 255))
            );
            dessin.addFigure(nouvelleFigure);
            figureCourante = nouvelleFigure;
            outil.setFigureCourante(nouvelleFigure);
        }
    }    
    public void onKeyPressed(String text) {
        switch (text) {
            case "1": setEpaisseur(1); break;
            case "2": setEpaisseur(2); break;
            case "3": setEpaisseur(3); break;
            case "4": setEpaisseur(4); break;
            case "5": setEpaisseur(5); break;
            case "6": setEpaisseur(6); break;
            case "7": setEpaisseur(7); break;
            case "8": setEpaisseur(8); break;
            case "9": setEpaisseur(9); break;

            case "c": onCrayon(); break;
            case "e": onEtoile(); break;

            case "r": setCouleur(javafx.scene.paint.Color.RED); break;
            case "v": setCouleur(javafx.scene.paint.Color.GREEN); break;
            case "b": setCouleur(javafx.scene.paint.Color.BLUE); break;
            case "n": setCouleur(javafx.scene.paint.Color.BLACK); break;
            case "w": setCouleur(javafx.scene.paint.Color.WHITE); break;
        }
    }
}