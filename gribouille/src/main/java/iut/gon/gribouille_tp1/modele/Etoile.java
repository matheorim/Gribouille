package iut.gon.gribouille_tp1.modele;

import java.io.PrintWriter;
import java.util.Scanner;

public class Etoile extends Figure {
    private Point centre;

    public Etoile(int epaisseur, String couleur, double xc, double yc) {
        super(epaisseur, couleur);
        centre = new Point(xc, yc);
    }

    Etoile(Scanner scan) {
        super(scan);
        double x = scan.nextDouble();
        double y = scan.nextDouble();
        centre = new Point(x, y);
    }

    @Override
    public void sauve(PrintWriter out) {
        out.print("E ");
        super.sauve(out);
        out.printf("%.1f %.1f\n", centre.getX(), centre.getY());
    }

    public Point getCentre() { return centre; }

    @Override
    public Figure changeCouleur(String nouvelleCouleur) {
        return new Etoile(epaisseur, nouvelleCouleur, centre.getX(), centre.getY());
    }

    @Override
    public Figure changeEpaisseur(int nouvelleEpaisseur) {
        return new Etoile(nouvelleEpaisseur, couleur, centre.getX(), centre.getY());
    }
}