package iut.gon.gribouille_tp1.controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CouleursControleur implements Initializable {

    @FXML public ColorPicker colorPicker;
    @FXML public Rectangle rectRouge;
    @FXML public Rectangle rectVert;
    @FXML public Rectangle rectBleu;
    @FXML public Rectangle rectCyan;
    @FXML public Rectangle rectRose;
    @FXML public Rectangle rectJaune;
    @FXML public Rectangle rectNoir;
    @FXML public Rectangle rectBlanc;

    public Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;

        colorPicker.valueProperty().bindBidirectional(controleur.couleur);

        rectRouge.setOnMouseClicked(e  -> controleur.couleur.set(Color.RED));
        rectVert.setOnMouseClicked(e   -> controleur.couleur.set(Color.GREEN));
        rectBleu.setOnMouseClicked(e   -> controleur.couleur.set(Color.BLUE));
        rectCyan.setOnMouseClicked(e   -> controleur.couleur.set(Color.CYAN));
        rectRose.setOnMouseClicked(e   -> controleur.couleur.set(Color.HOTPINK));
        rectJaune.setOnMouseClicked(e  -> controleur.couleur.set(Color.YELLOW));
        rectNoir.setOnMouseClicked(e   -> controleur.couleur.set(Color.BLACK));
        rectBlanc.setOnMouseClicked(e  -> controleur.couleur.set(Color.WHITE));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}