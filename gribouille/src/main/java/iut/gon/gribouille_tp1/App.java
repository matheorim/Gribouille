package iut.gon.gribouille_tp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private double prevX;
    private double prevY;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setScene(scene);

        // Fermeture avec confirmation
        stage.setOnCloseRequest(event -> {
            if (!Dialogues.confirmation()) {
                event.consume();
            }
        });

        // Extraction du Canvas
        Canvas dessin = (Canvas) scene.lookup("Canvas");

        // Lier la taille du canvas à son conteneur
        Pane pane = (Pane) dessin.getParent();
        dessin.widthProperty().bind(pane.widthProperty());
        dessin.heightProperty().bind(pane.heightProperty());

        // Event-filter sur le Pane pour le clic droit
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            if (evt.getButton() == MouseButton.SECONDARY) {
                Circle cercle = new Circle(evt.getX(), evt.getY(), 5);
                cercle.setMouseTransparent(true);
                pane.getChildren().add(cercle);
                evt.consume();
            }
        });

        // Mémoriser la position quand on clique
        dessin.addEventHandler(MouseEvent.MOUSE_PRESSED, evt -> {
            prevX = evt.getX();
            prevY = evt.getY();
        });

        // Tracer un segment ET mettre à jour prevX/prevY à chaque mouvement
        dessin.addEventHandler(MouseEvent.MOUSE_DRAGGED, evt -> {
            dessin.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
            prevX = evt.getX();
            prevY = evt.getY();
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
        launch(args);
    }
}