module fr.iutgon.tp6 {
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires javafx.base;


  opens fr.iutgon.tp6 to javafx.fxml;
  exports fr.iutgon.tp6;
  exports fr.iutgon.tp6.modele;
}