package model;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {

    // Attributes
    public static int i = 0;

    public Date dateDeb;
    public Date dateFin;
    public int id;
    public double prix;
    
    public Client client;
    public Vector<Chambre> listChambre = new Vector<Chambre>();
    public Sejour sejour;

    // Constructors
    public Reservation(Date deb, Date fin) { dateDeb = deb; dateFin = fin; id = ++i;}

    public void setClient(Client c) { client = c; }
    public void setSejour(Sejour s) { sejour = s; }
    
    //Methods
    public void addChambre(Chambre ch) { listChambre.add(ch); }
    public double getCaution() { return prix*0.2; }
    public Vector<String> chambresToString(){
        Vector<String> chambresStr = new Vector<String>();
            for(Chambre ch : listChambre) {
                chambresStr.add("Chambre n°" + ch.num);
            }
        return chambresStr;
    }
    public String datesToString(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
        String dateDebStr = formatter.format(dateDeb);
        String dateFinStr = formatter.format(dateFin);
        return "du " + dateDebStr + " au " + dateFinStr;
    }
    
    
}