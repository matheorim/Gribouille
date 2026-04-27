package iut.gon.TP3;

import javafx.beans.property.SimpleStringProperty;

public class GrilleModel {
	private SimpleStringProperty[][] tableau = new SimpleStringProperty[3][3];
	public GrilleModel() {
		tableau[0][0] = new SimpleStringProperty("A");
		tableau[0][1] = new SimpleStringProperty("B");
		tableau[0][2] = new SimpleStringProperty("C");
		tableau[1][0] = new SimpleStringProperty("D");
		tableau[1][1] = new SimpleStringProperty("E");
		tableau[1][2] = new SimpleStringProperty("F");
		tableau[2][0] = new SimpleStringProperty("G");
		tableau[2][1] = new SimpleStringProperty("H");
		tableau[2][2] = new SimpleStringProperty("I");
	}
	public SimpleStringProperty getCase(int lg,int col) {
		return tableau[lg][col];
	}
	public void setCase(int lg, int col, String texte) {
		tableau[lg][col].set(texte);
	}
}