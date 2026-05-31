package iut.gon.gribouille_tp1.modele;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Figure {
    protected int epaisseur;
    protected String couleur;
    protected List<Point> points = new ArrayList<>();

    public Figure(int epaisseur, String couleur) {
        this.epaisseur = epaisseur;
        this.couleur = couleur;
    }

    public Figure(Scanner scan) {
        epaisseur = scan.nextInt();
        couleur = scan.next();
        points = new ArrayList<>();
        int nb = scan.nextInt();
        while (nb > 0) {
            double x = scan.nextDouble();
            double y = scan.nextDouble();
            points.add(new Point(x, y));
            --nb;
        }
    }

    public static Figure charge(String line) {
        Scanner scan = new Scanner(line);
        switch (scan.next()) {
            case "T": return new Trace(scan);
            case "E": return new Etoile(scan);
            default: throw new IllegalArgumentException("Type de figure inconnu");
        }
    }

    public void sauve(PrintWriter out) {
        out.printf("%d %s %d ", epaisseur, couleur, points.size());
        points.forEach(p -> out.printf("%.1f %.1f ", p.getX(), p.getY()));
    }

    public int getEpaisseur() { return epaisseur; }
    public String getCouleur() { return couleur; }
    public List<Point> getPoints() { return points; }
    public void addPoint(Point nouveau) { points.add(nouveau); }
    public void addPoint(double x, double y) { addPoint(new Point(x, y)); }

    public abstract Figure changeCouleur(String nouvelleCouleur);
    public abstract Figure changeEpaisseur(int nouvelleEpaisseur);
}