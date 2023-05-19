package model;
import java.util.*;

public class Chambre {
	//Attributes
    public String num;
    public String etage;
    public int nbrPlaces;
    public double prix;
    
    public Vector<Reservation> listRes = new Vector<Reservation>();
    public Vector<Option> listOption = new Vector<Option>();
    
    //Constructors
    public Chambre(String n, int places) { 
    	num=n; nbrPlaces=places; 
        etage = n.substring(0, 1);
    	prix=15+5*places;
    }
    
    //Methods
    public void addRes (Reservation res) { listRes.add(res); }
    public void suppRes(Reservation res) { listRes.remove(res) ;}
    public void addOption (Option opt) { listOption.add(opt); prix+=opt.prix; }

    
}