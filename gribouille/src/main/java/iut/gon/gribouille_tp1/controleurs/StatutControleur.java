package iut.gon.gribouille_tp1.controleurs;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatutControleur implements Initializable {

    @FXML public Label labelX;
    @FXML public Label labelY;
    @FXML public Label labelEpaisseur;
    @FXML public Label labelOutil;
    @FXML public Label labelCouleur;

    public Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;

        labelX.textProperty().bind(Bindings.format("%.0f", controleur.precX));
        labelY.textProperty().bind(Bindings.format("%.0f", controleur.precY));

        labelEpaisseur.textProperty().bind(controleur.epaisseur.asString());

        labelCouleur.textProperty().bind(Bindings.createStringBinding(
            () -> {
                javafx.scene.paint.Color c = controleur.couleur.get();
                if (c == null) return "-";
                return String.format("#%02x%02x%02x",
                    (int) (c.getRed()   * 255),
                    (int) (c.getGreen() * 255),
                    (int) (c.getBlue()  * 255));
            },
            controleur.couleur
        ));

        labelOutil.setText("Crayon");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelX.setText("0");
        labelY.setText("0");
        labelEpaisseur.setText("1");
        labelOutil.setText("-");
        labelCouleur.setText("#000000");
    }
}