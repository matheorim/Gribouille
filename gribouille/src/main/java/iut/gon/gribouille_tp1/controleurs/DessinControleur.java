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
 
        controleur.dessin.getFigures(); 
        dessin.widthProperty().addListener((obs, oldVal, newVal) -> redessiner());
        dessin.heightProperty().addListener((obs, oldVal, newVal) -> redessiner());
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

    @FXML
    public void onMousePressed(MouseEvent evt) {
        if (controleur == null) return;
        controleur.precX.set(evt.getX());
        controleur.precY.set(evt.getY());

        javafx.scene.paint.Color c = controleur.couleur.get();
        String couleurHex = String.format("#%02x%02x%02x",
            (int) (c.getRed()   * 255),
            (int) (c.getGreen() * 255),
            (int) (c.getBlue()  * 255));

        controleur.figureCourante = new Trace(
            controleur.epaisseur.get(),
            couleurHex,
            evt.getX(),
            evt.getY()
        );
        controleur.dessin.addFigure(controleur.figureCourante);
    }

    @FXML
    public void onMouseDragged(MouseEvent evt) {
        if (controleur == null) return;
        GraphicsContext gc = dessin.getGraphicsContext2D();
        gc.setStroke(controleur.couleur.get());
        gc.setLineWidth(controleur.epaisseur.get());
        gc.strokeLine(
            controleur.precX.get(), controleur.precY.get(),
            evt.getX(), evt.getY()
        );
        controleur.figureCourante.addPoint(evt.getX(), evt.getY());
        controleur.precX.set(evt.getX());
        controleur.precY.set(evt.getY());
    }

    @FXML
    public void onMouseMoved(MouseEvent evt) {
        if (controleur == null) return;
        controleur.precX.set(evt.getX());
        controleur.precY.set(evt.getY());
    }
}