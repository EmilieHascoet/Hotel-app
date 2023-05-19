package model;
import java.util.*;

public class Client {
	//Attributes
    public String prenom;
    public String nom;
    //public Date dateNaiss;
    public String tel;
    public Sejour sejour;
    
    public Vector<Reservation> listRes = new Vector<Reservation>();

    //Constructors
    public Client(String firstN, String lastN, String t) { prenom=firstN; nom=lastN; tel=t; }

    //Methods
    public void addRes(Reservation res) { listRes.add(res); }
    public void suppRes(Reservation res) { listRes.remove(res); }
    public void setSejour(Sejour sej) { sejour = sej; }
    
    public Reservation searchRes(int i) { 
        for (Reservation r : listRes) {
            if (r.id == i) return r;
        }
        return null;
    }
    
    
}