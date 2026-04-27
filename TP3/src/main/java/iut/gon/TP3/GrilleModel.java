package iut.gon.TP3;

public class GrilleModel {
	private String[][] tableau = new String[3][3];
	public GrilleModel() {
		tableau[0][0] = "A";
		tableau[0][1] = "B";
		tableau[0][2] = "C";
		tableau[1][0] = "D";
		tableau[1][1] = "E";
		tableau[1][2] = "F";
		tableau[2][0] = "G";
		tableau[2][1] = "H";
		tableau[2][2] = "I";
	}
	public String getCase(int lg,int col) {
		return tableau[lg][col];
	}
	public void setCase(int lg, int col, String texte) {
		tableau[lg][col] = texte;
	}
}