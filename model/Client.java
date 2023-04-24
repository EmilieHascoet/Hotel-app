package model;
import java.util.*;

public class Client {
	//Attributes
    public String nom;
    public Date dateNaiss;
    
    public Vector<Reservation> listRes = new Vector<Reservation>();

    //Constructors
    public Client(String n, Date naiss) { nom=n; dateNaiss=naiss; }

    //Methods
    public void addRes(Reservation res) { listRes.add(res); }
    
    public void suppRes(Reservation res) { listRes.remove(res); }
    
    
}