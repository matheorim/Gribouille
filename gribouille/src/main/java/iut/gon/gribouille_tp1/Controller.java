package iut.gon.gribouille_tp1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dessin.widthProperty().bind(pane.widthProperty());
        dessin.heightProperty().bind(pane.heightProperty());
    }
}