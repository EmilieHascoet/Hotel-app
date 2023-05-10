package model;
import java.util.*;

public class Reservation {

    // Attributes
    public static int i = 0;

    public Date dateDeb;
    public Date dateFin;
    public int id;
    
    public Client client;
    public Vector<Chambre> listChambre = new Vector<Chambre>();
    public Sejour sejour;

    // Constructors
    public Reservation(Date deb, Date fin) { dateDeb = deb; dateFin = fin; id = ++i;}

    public void setClient(Client c) { client = c; }
    public void setSejour(Sejour s) { sejour = s; }
    
    //Methods
    public void addChambre(Chambre ch) { listChambre.add(ch); }
    
    
}