package iut.gon.TP3;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class GrilleController implements Initializable{
	@FXML
	private GridPane grille;
	private Label[][] labels = new Label[3][3];
	
	@Override
	public void initialize(URL url, ResourceBundle ressourceBundle) {
		grille.setStyle("-fx-background-color: seashell");
		
		for (int lg = 0; lg < 3; lg++) {
			for (int col = 0; col < 3; col++) {
				Label label = new Label(String.format("L%dC%d",lg,col));
				label.setMaxSize(1000, 1000);
				label.setAlignment(javafx.geometry.Pos.CENTER);
				label.setOnMouseClicked(event -> label.setText("bonjour"));
				labels[lg][col] = label;
				grille.add(label, col, lg);
			}
		}
	}
}
