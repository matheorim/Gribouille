package iut.gon.gribouille_tp1.controleurs;

import iut.gon.gribouille_tp1.modele.Figure;
import iut.gon.gribouille_tp1.modele.Point;
import iut.gon.gribouille_tp1.modele.Trace;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DessinControleur implements Initializable {

    @FXML public Pane pane;
    @FXML public Canvas dessin;

    public Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        dessin.widthProperty().addListener((obs, oldVal, newVal) -> controleur.dessine());
        dessin.heightProperty().addListener((obs, oldVal, newVal) -> controleur.dessine());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dessin.widthProperty().bind(pane.widthProperty());
        dessin.heightProperty().bind(pane.heightProperty());
    }

    private void redessiner() {
        if (controleur == null || controleur.dessin == null) return;
        GraphicsContext gc = dessin.getGraphicsContext2D();
        gc.clearRect(0, 0, dessin.getWidth(), dessin.getHeight());

        for (Figure figure : controleur.dessin.getFigures()) {
            if (figure instanceof Trace) {
                List<Point> points = figure.getPoints();
                gc.setLineWidth(figure.getEpaisseur());

                gc.setStroke(javafx.scene.paint.Color.web(figure.getCouleur()));
                for (int i = 1; i < points.size(); i++) {
                    gc.strokeLine(
                        points.get(i - 1).getX(), points.get(i - 1).getY(),
                        points.get(i).getX(),     points.get(i).getY()
                    );
                }
            }
        }
    }

    public void efface() {
        dessin.getGraphicsContext2D().clearRect(0, 0, dessin.getWidth(), dessin.getHeight());
    }

    public void trace(double x1, double y1, double x2, double y2) {
        GraphicsContext gc = dessin.getGraphicsContext2D();
        gc.setStroke(controleur.couleur.get());
        gc.setLineWidth(controleur.epaisseur.get());
        gc.strokeLine(x1, y1, x2, y2);
    }
    
    @FXML
    public void onMousePressed(MouseEvent evt) {
        if (controleur == null) return;
        controleur.onMousePress(evt.getX(), evt.getY());
    }

    @FXML
    public void onMouseDragged(MouseEvent evt) {
        if (controleur == null) return;
        controleur.onMouseDrag(evt.getX(), evt.getY());
    }

    @FXML
    public void onMouseMoved(MouseEvent evt) {
        if (controleur == null) return;
        controleur.onMouseMove(evt.getX(), evt.getY());
    }
    public void setEpaisseur(int e) {
        dessin.getGraphicsContext2D().setLineWidth(e);
    }
}