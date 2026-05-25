package iut.gon.gribouille_tp1.controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MenusControleur implements Initializable {

    public Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void onQuitte() {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}