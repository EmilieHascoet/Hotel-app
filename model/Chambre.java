package model;
import java.util.*;

public class Chambre {
	//Attributes
    public int num;
    public int etage;
    public int nbrPlaces;
    public double prix;
    
    public Vector<Reservation> listRes = new Vector<Reservation>();
    public Vector<Option> listOption = new Vector<Option>();
    
    //Constructors
    public Chambre(int n, int places) { 
    	num=n; nbrPlaces=places; 
        etage = n/100;
    	prix=20+10*places;
    }
    
    //Methods
    public void addRes (Reservation res) { listRes.add(res); }
    public void addOption (Option opt) { listOption.add(opt); prix+=opt.prix; }

    
}