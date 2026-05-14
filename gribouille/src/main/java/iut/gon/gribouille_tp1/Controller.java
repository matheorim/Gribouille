package iut.gon.gribouille_tp1;

import iut.gon.gribouille_tp1.modele.Dessin;
import iut.gon.gribouille_tp1.modele.Figure;
import iut.gon.gribouille_tp1.modele.Trace;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane pane;
    @FXML private Canvas dessin;
    @FXML private ColorPicker colorPicker;
    @FXML private Label labelX;
    @FXML private Label labelY;
    @FXML private Label labelEpaisseur;
    @FXML private Label labelOutil;
    @FXML private Label labelCouleur;
    @FXML private Rectangle rectRouge;
    @FXML private Rectangle rectVert;
    @FXML private Rectangle rectBleu;
    @FXML private Rectangle rectCyan;
    @FXML private Rectangle rectRose;
    @FXML private Rectangle rectJaune;
    @FXML private Rectangle rectNoir;
    @FXML private Rectangle rectBlanc;

    private SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    private SimpleDoubleProperty prevY = new SimpleDoubleProperty();

    private Dessin monDessin;
    private Trace traceEnCours;

    public void setDessin(Dessin dessin) {
        this.monDessin = dessin;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dessin.widthProperty().bind(pane.widthProperty());
        dessin.heightProperty().bind(pane.heightProperty());

        labelX.textProperty().bind(prevX.asString("%.0f"));
        labelY.textProperty().bind(prevY.asString("%.0f"));

        dessin.widthProperty().addListener((obs, oldVal, newVal) -> redessiner());
        dessin.heightProperty().addListener((obs, oldVal, newVal) -> redessiner());
    }

    private void redessiner() {
        if (monDessin == null) return;
        GraphicsContext gc = dessin.getGraphicsContext2D();
        gc.clearRect(0, 0, dessin.getWidth(), dessin.getHeight());

        for (Figure figure : monDessin.getFigures()) {
            if (figure instanceof Trace) {
                List<iut.gon.gribouille_tp1.modele.Point> points = figure.getPoints();
                gc.setLineWidth(figure.getEpaisseur());
                for (int i = 1; i < points.size(); i++) {
                    gc.strokeLine(
                        points.get(i-1).getX(), points.get(i-1).getY(),
                        points.get(i).getX(), points.get(i).getY()
                    );
                }
            }
        }
    }

    @FXML
    public void onMousePressed(MouseEvent evt) {
        prevX.set(evt.getX());
        prevY.set(evt.getY());
        traceEnCours = new Trace(1, "black", prevX.get(), prevY.get());
        monDessin.addFigure(traceEnCours);
    }

    @FXML
    public void onMouseDragged(MouseEvent evt) {
        dessin.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY());
        traceEnCours.addPoint(evt.getX(), evt.getY());
        prevX.set(evt.getX());
        prevY.set(evt.getY());
    }

    @FXML
    public void onMouseMoved(MouseEvent evt) {
        prevX.set(evt.getX());
        prevY.set(evt.getY());
    }
}