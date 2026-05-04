package iut.gon.tp4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;

public class MenusController {
	private Scores table;
	private GrilleModel modele;
	public MenusController() {
	}
	public void setParams(GrilleModel modele,Scores table) {
		this.modele = modele;
		this.table = table;
	}
	@FXML
	  public void onMenuNouvelle(ActionEvent evt) {
	    if(modele != null) {
	    	modele.nouvellePartie();
	    }
	  }
	  @FXML
	  public void onMenuTable(ActionEvent evt) {
		    try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
		        Parent tableRoot = loader.load();
		        TableController tableController = loader.getController();
		        tableController.setScores(table);
		        ((Node) evt.getSource()).getScene().setRoot(tableRoot);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	  @FXML
	  public void onMenuQuitter(ActionEvent evt) {
	    Platform.exit();
	  }
}
