package iut.gon.gribouille_tp1.modele;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Dessin {
    private final SimpleBooleanProperty estModifie = new SimpleBooleanProperty(false);
    private final SimpleStringProperty nomDuFichier = new SimpleStringProperty("Sans Nom");
    private final SimpleObjectProperty<File> fichier = new SimpleObjectProperty<>(null);
    private List<Figure> figures;

    public Dessin() {
        figures = FXCollections.observableArrayList();
        fichier.addListener((obs, oldVal, newVal) -> {
            if (newVal != null) nomDuFichier.set(newVal.getName());
        });
    }

    public List<Figure> getFigures() { return figures; }
    public void setFigures(List<Figure> figures) { this.figures = figures; }

    public void addFigure(Figure figure) {
        figures.add(figure);
        estModifie.set(true);
    }

    public boolean getEstModifie() { return estModifie.get(); }
    public void setEstModifie(boolean valeur) { estModifie.set(valeur); }
    public BooleanExpression estModifieProperty() { return estModifie; }

    public String getNomDuFichier() { return nomDuFichier.get(); }
    public void setNomDuFichier(String nomDuFichier) { this.nomDuFichier.set(nomDuFichier); }
    public StringExpression nomDuFichierProperty() { return nomDuFichier; }

    public File getFichier() { return fichier.get(); }
    public SimpleObjectProperty<File> fichierProperty() { return fichier; }

    public void sauveSous(String nom) {
        try {
            File nouveauFichier = new File(nom);
            PrintWriter out = new PrintWriter(nouveauFichier);
            out.format(java.util.Locale.ENGLISH, "");
            figures.forEach(f -> f.sauve(out));
            out.close();
            this.fichier.set(nouveauFichier);
            estModifie.set(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void charge(String nom) {
        File nouveauFichier = new File(nom);
        if (nouveauFichier.exists() && nouveauFichier.canRead()) {
            figures.clear();
            try {
                Scanner scanner = new Scanner(nouveauFichier);
                scanner.useLocale(java.util.Locale.ENGLISH);
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    if (!ligne.isBlank())
                        figures.add(Figure.charge(ligne));
                }
                this.fichier.set(nouveauFichier);
                estModifie.set(false);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Ce fichier est introuvable !");
            }
        } else {
            throw new IllegalArgumentException("Ce fichier est illisible !");
        }
    }
}
  