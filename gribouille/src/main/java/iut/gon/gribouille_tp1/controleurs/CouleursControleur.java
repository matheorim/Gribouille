package iut.gon.gribouille_tp1.controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class CouleursControleur implements Initializable {

    @FXML public ColorPicker colorPicker;
    @FXML public VBox vbox;
    @FXML public Rectangle rectRouge;
    @FXML public Rectangle rectVert;
    @FXML public Rectangle rectBleu;
    @FXML public Rectangle rectCyan;
    @FXML public Rectangle rectRose;
    @FXML public Rectangle rectJaune;
    @FXML public Rectangle rectNoir;
    @FXML public Rectangle rectBlanc;

    public Controleur controleur;
    private Rectangle rectSelectionne = null;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        colorPicker.valueProperty().bindBidirectional(controleur.couleur);

        vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
            if (evt.getTarget() instanceof Rectangle) {
                Rectangle rect = (Rectangle) evt.getTarget();

                if (rectSelectionne != null) {
                    rectSelectionne.setArcWidth(5.0);
                    rectSelectionne.setArcHeight(5.0);
                    rectSelectionne.setStrokeWidth(1.0);
                }

                rect.setArcWidth(10.0);
                rect.setArcHeight(10.0);
                rect.setStrokeWidth(5.0);
                rectSelectionne = rect;

                controleur.setCouleur((Color) rect.getFill());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}