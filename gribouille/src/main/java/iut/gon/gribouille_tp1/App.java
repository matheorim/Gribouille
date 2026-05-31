package iut.gon.gribouille_tp1;

import iut.gon.gribouille_tp1.controleurs.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;
import java.io.IOException;

import javafx.beans.binding.Bindings;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CadreGribouille.fxml"));
        scene = new Scene(loader.load(), 640, 480);

        Controleur controleur = loader.getController();

        stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
            controleur.onKeyPressed(evt.getText());
        });
        stage.titleProperty().bind(
        	    Bindings.concat(
        	        controleur.dessin.nomDuFichierProperty(),
        	        Bindings.when(controleur.dessin.estModifieProperty())
        	            .then(" *")
        	            .otherwise("")
        	    )
        	);        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            if (!controleur.onQuitter()) {
                event.consume();
            }
        });
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.ENGLISH);
        launch(args);
    }
}