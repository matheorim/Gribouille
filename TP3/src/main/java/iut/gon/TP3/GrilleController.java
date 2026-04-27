package iut.gon.TP3;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class GrilleController implements Initializable{
	@FXML
	private GridPane grille;
	
	@Override
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		grille.setStyle("-fx-background-color: seashell");
		
	}
}
