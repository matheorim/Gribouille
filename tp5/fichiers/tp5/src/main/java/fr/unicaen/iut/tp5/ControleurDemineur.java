package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDemineur implements Initializable {

    private ModeleDemineur modele = new ModeleDemineur(10,10,10);

    @FXML
    private TextField NbInconnues;

    @FXML
    private TextField NbMarques;

    @FXML
    private ToggleGroup Difficulté;

    @FXML
    private GridPane GridPaneVue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NbInconnues.textProperty().bind(
                modele.nbInconnuesProperty().asString()
        );
        NbMarques.textProperty().bind(
                modele.nbMarquesProperty().asString()
        );
        Difficulté.selectedToggleProperty().addListener(
                (obs, ancien, nouveau) -> {
                    if (nouveau != null) {
                        String data = (String) nouveau.getUserData();
                        initGrille(data);
                    }
                }
        );
    }
    private void initGrille(String userData) {
    }
}