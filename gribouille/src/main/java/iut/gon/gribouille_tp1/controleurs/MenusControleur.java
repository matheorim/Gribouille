package iut.gon.gribouille_tp1.controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.util.ResourceBundle;

public class MenusControleur implements Initializable {

    public Controleur controleur;

    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;
    @FXML public ToggleGroup outils;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outils.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == menuCrayon) {
                controleur.onCrayon();
            } else if (newVal == menuEtoile) {
                controleur.onEtoile();
            }
        });
    }

    @FXML
    public void onQuitte() {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}