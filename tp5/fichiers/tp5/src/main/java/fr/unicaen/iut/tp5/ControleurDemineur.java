package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDemineur implements Initializable {

	private final Background inconnu = new Background(new BackgroundFill(
	        Color.AQUA,
	        new CornerRadii(20, true),
	        Insets.EMPTY
	));

	private final Background libre = new Background(new BackgroundFill(
	        Color.LIGHTGRAY,
	        CornerRadii.EMPTY,
	        Insets.EMPTY
	));

	private final Background echec = new Background(new BackgroundFill(
	        Color.RED,
	        CornerRadii.EMPTY,
	        Insets.EMPTY
	));

	private final Background marquee = new Background(new BackgroundFill(
	        Color.LEMONCHIFFON,
	        CornerRadii.EMPTY,
	        Insets.EMPTY
	));
	
    private ModeleDemineur modele = new ModeleDemineur(10,10,10);

    @FXML
    private TextField NbInconnues;

    @FXML
    private TextField NbMarques;

    @FXML
    private ToggleGroup Difficulte;

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
        Difficulte.selectedToggleProperty().addListener(
                (obs, ancien, nouveau) -> {
                    if (nouveau != null) {
                        String data = (String) nouveau.getUserData();
                        initGrille(data);
                    }
                }
        );
    }
    private void updateBackground(Label label, int y, int x) {

        if (modele.estPerdu() && modele.estRevelee(y, x) && label.getText().equals("X")) {
            label.setBackground(echec);
        }
        else if (modele.estRevelee(y, x)) {
            label.setBackground(libre);
        }
        else if (modele.estMarquee(y, x)) {
            label.setBackground(marquee);
        }
        else {
            label.setBackground(inconnu);
        }
    }
    private void initGrille(String userData) {
    	GridPaneVue.getRowConstraints().clear();
    	GridPaneVue.getColumnConstraints().clear();
    	GridPaneVue.getChildren().clear();
    	int[] valeurs = ModeleDemineur.parseUserData(userData);
        int nbLignes = valeurs[1];
        int nbColonnes = valeurs[0];
        int nbMines = valeurs[2];
        modele.setTaille(nbLignes, nbColonnes, nbMines);
    	for (int i = 0; i < nbLignes; i++) {
            RowConstraints rc = new RowConstraints(32);
            GridPaneVue.getRowConstraints().add(rc);
        }
        for (int i = 0; i < nbColonnes; i++) {
            ColumnConstraints cc = new ColumnConstraints(32);
            GridPaneVue.getColumnConstraints().add(cc);
        }
        for (int y = 0; y < nbLignes; y++) {
            for (int x = 0; x < nbColonnes; x++) {

                Label label = new Label();
                label.setPrefSize(31, 31);
                label.setAlignment(javafx.geometry.Pos.CENTER);
                label.setBackground(inconnu);

                label.textProperty().bind(modele.texteProperty(y, x));

                int fy = y;
                int fx = x;

                label.setOnMouseClicked(e -> {

                    if (modele.estPerdu()) return;

                    if (e.getButton() == javafx.scene.input.MouseButton.PRIMARY) {
                        modele.revele(fy, fx);
                    }

                    if (e.getButton() == javafx.scene.input.MouseButton.SECONDARY) {
                        modele.marque(fy, fx);
                    }

                    updateBackground(label, fy, fx);
                });

                GridPaneVue.add(label, x, y);

                updateBackground(label, y, x);
            }
        }
    }
}