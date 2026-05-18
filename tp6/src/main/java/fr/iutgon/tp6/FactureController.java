package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.MoneyTableCell;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;
  public NumberExpression sommeTotal = new SimpleIntegerProperty(0);

  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      table.setEditable(true);
      qte.setCellValueFactory(
              new PropertyValueFactory<>("qte")
      );
      qte.setCellFactory(
              TextFieldTableCell.forTableColumn(new IntegerStringConverter())
      );
      qte.setOnEditCommit(event -> {
          Ligne ligne = event.getRowValue();
          ligne.setQte(event.getNewValue());
      });
      produit.setCellValueFactory(
              cellData -> cellData.getValue().produitProperty()
      );
      StringConverter<Produit> converter = new StringConverter<>() {
          @Override
          public String toString(Produit p) {
              return p == null ? "" : p.getNom();
          }
          @Override
          public Produit fromString(String s) {
              return FabriqueProduits.getProduits()
                      .stream()
                      .filter(p -> p.getNom().equals(s))
                      .findFirst()
                      .orElse(null);
          }
      };
      produit.setCellFactory(
              ChoiceBoxTableCell.forTableColumn(
                      converter,
                      FXCollections.observableArrayList(
                              FabriqueProduits.getProduits()
                      )
              )
      );
      produit.setOnEditCommit(event -> {
          Ligne ligne = event.getRowValue();
          ligne.setProduit(event.getNewValue());
      });
      prixUnitaire.setCellValueFactory(
              cellData ->
                      Bindings.selectFloat(
                              cellData.getValue().produitProperty(),
                              "prix"
                      )
      );
      totalHT.setCellValueFactory(
              cellData -> cellData.getValue().totalHTProperty()
      );
      totalTTC.setCellValueFactory(
              cellData -> cellData.getValue().totalTTCProperty()
      );
      sommeFacture.textProperty().bind(
    	        sommeTotal.asString()   
    	);
      prixUnitaire.setCellFactory(MoneyTableCell.forTableColumn());
      totalHT.setCellFactory(MoneyTableCell.forTableColumn());
      totalTTC.setCellFactory(MoneyTableCell.forTableColumn());
  }
  
  public void onAjouter(ActionEvent actionEvent) {
	  Random r = new Random();
	    Produit p = FabriqueProduits.getProduits().getFirst();
	    int quantite = r.nextInt(10) + 1;
	    Ligne ligne = new Ligne(quantite, p);
	    table.getItems().add(ligne);
	    sommeTotal = Bindings.add(sommeTotal, ligne.totalTTCProperty());
	      sommeFacture.textProperty().bind(
	    	        sommeTotal.asString("%.2f")   
	    	);
  }
}
