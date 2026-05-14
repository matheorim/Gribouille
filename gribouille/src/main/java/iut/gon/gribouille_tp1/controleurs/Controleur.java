package iut.gon.gribouille_tp1.controleurs;

import iut.gon.gribouille_tp1.modele.Dessin;
import iut.gon.gribouille_tp1.modele.Figure;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Controleur {

    public final Dessin dessin = new Dessin();
    public Figure figureCourante;
    public final SimpleObjectProperty<Double> precX = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Double> precY = new SimpleObjectProperty<>(0.0);
    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);

    @FXML public MenusControleur menusControleur;
    @FXML public StatutControleur statutControleur;
    @FXML public CouleursControleur couleursControleur;
    @FXML public DessinControleur dessinControleur;

    @FXML
    public void initialize() {
        if (menusControleur != null) menusControleur.setControleur(this);
        if (statutControleur != null) statutControleur.setControleur(this);
        if (couleursControleur != null) couleursControleur.setControleur(this);
        if (dessinControleur != null) dessinControleur.setControleur(this);
    }
}