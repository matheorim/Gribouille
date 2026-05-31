package iut.gon.gribouille_tp1.controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MenusControleur implements Initializable {
    public Controleur controleur;

    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;
    @FXML public ToggleGroup outils;
    @FXML public ToggleGroup epaisseur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        outils.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == menuCrayon) controleur.onCrayon();
            else if (newVal == menuEtoile) controleur.onEtoile();
        });
        epaisseur.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RadioMenuItem item = (RadioMenuItem) newVal;
                controleur.setEpaisseur(Integer.parseInt(item.getText()));
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void onSauvegarder() {
        controleur.sauvegarde();
    }

    @FXML
    public void onQuitte() {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}