package model;
import java.util.*;

public class Sejour {

    // Attributes
    public double prix;
    
    public Reservation reservation;
    public Vector<Produit> listProduit = new Vector<Produit>();
    
    // Constructors
    public Sejour(double p) { prix = p; }

    public void setReservation(Reservation res) { reservation = res; }
    public void addProduit(Produit p) { listProduit.add(p); prix+=p.prix; }
    public void removeProduit(Produit p) { listProduit.remove(p); prix-=p.prix; }
}