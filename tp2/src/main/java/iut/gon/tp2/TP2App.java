package iut.gon.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class TP2App extends Application {

  private BorderPane contenu;
  private ListView<String> gauche;
  private ListView<String> droite;
  private Button versGauche;
  private Button versDroite;
  private Button retireTout;
  private Button ajouteTout;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
    contenu = fxmlLoader.load();
    Scene scene = new Scene(contenu);
    extraitIds(scene);

    prepareMenus((MenuBar) scene.lookup("#menus"));
    prepareListe();
    prepareBoutons();
    prepareFermeture(stage);

    stage.setTitle("Gestion de groupe");
    stage.setScene(scene);
    stage.show();
  }

  /** Prépare la fenêtre pour demander confirmation avant fermeture */
  private void prepareFermeture(Stage stage) {
    stage.setOnCloseRequest(event -> {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment fermer la page", ButtonType.YES , ButtonType.NO);
    	alert.setTitle("Quitter");
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.orElse(ButtonType.NO) != ButtonType.YES) {
    		event.consume();
    	}
    });
  }

  /** Prépare les actions des boutons */
  private void prepareBoutons() {
    ajouteTout.setOnAction(this::onAjouteTout);
    retireTout.setOnAction(this::onRetireTout);
    versDroite.setOnAction(this::onVersDroite);
    versGauche.setOnAction(this::onVersGauche);
    
  }

  private void onVersDroite(ActionEvent actionEvent) {
	  int index = gauche.getSelectionModel().getSelectedIndex();
	  if(index != -1) {
		  String element = gauche.getItems().remove(index);
		  droite.getItems().add(element);
		  gauche.getSelectionModel().clearSelection();
	  }
  }
  
  private void onVersGauche(ActionEvent actionEvent) {
	  int index = droite.getSelectionModel().getSelectedIndex();
	  if(index != -1) {
		  String element = droite.getItems().remove(index);
		  gauche.getItems().add(element);
		  droite.getSelectionModel().clearSelection();
	  }
  }
  
  
  /** Ajoute tous les éléments de gauche dans la liste de droite
   Active le bouton "Retirer tout" et désactive le bouton "Ajouter tout" */
  private void onAjouteTout(ActionEvent actionEvent) {
    droite.getItems().addAll(gauche.getItems());
    gauche.getItems().clear();
    
	   ajouteTout.setDisable(true);
	   retireTout.setDisable(false);
  }

  /** Ajoute tous les éléments de droite dans la liste de gauche
   Active le bouton "Ajouter tout" et désactive le bouton "Retirer tout" */
  private void onRetireTout(ActionEvent actionEvent) {
	   gauche.getItems().addAll(droite.getItems());
	   droite.getItems().clear();
	   
	   ajouteTout.setDisable(false);
	   retireTout.setDisable(true);
  }

  /** Prépare les menus et leurs événements */
  private void prepareMenus(MenuBar menus) {
	Menu menuFichiers = new Menu("_Fichiers");
	Menu menuAide = new Menu("_Aide");
			
    menus.getMenus().addAll(menuFichiers, menuAide);
    
    MenuItem itemQuitter = new MenuItem("Quitter");
    MenuItem itemAPropos = new MenuItem("A propos");
    
    menuFichiers.getItems().add(itemQuitter);
    menuAide.getItems().add(itemAPropos);
    
    itemQuitter.setOnAction(e -> Platform.exit());
    itemAPropos.setOnAction(e -> {
    	Alert alert = new Alert(Alert.AlertType.NONE, "Fait par …vous !", ButtonType.CLOSE);
    	alert.setTitle("A propos");
    	alert.show();
    });
  }

  /**
   Remplit la liste de gauche avec des valeurs
   Active le bouton "Ajouter tout"
   */
  private void prepareListe() {
	  gauche.getItems().addAll(
			"Bob",
			"Patrice",
			"Stéphanie"
		);
	  
	  ajouteTout.setDisable(false);
	  
  }

  private void extraitIds(Scene scene) {
    gauche = (ListView<String>) scene.lookup("#gauche");
    droite = (ListView<String>) scene.lookup("#droite");
    versGauche = (Button) scene.lookup("#versGauche");
    versDroite = (Button) scene.lookup("#versDroite");
    retireTout = (Button) scene.lookup("#retireTout");
    ajouteTout = (Button) scene.lookup("#ajouteTout");
  }

  public static void main(String[] args) {
    launch();
  }
}
