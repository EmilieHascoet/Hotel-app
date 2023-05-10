package model;
import java.util.*;

public class Client {
	//Attributes
    public String nom;
    public Date dateNaiss;
    public String tel;
    public Sejour sejour;
    
    public Vector<Reservation> listRes = new Vector<Reservation>();

    //Constructors
    public Client(String n, Date naiss, String t) { nom=n; dateNaiss=naiss; tel=t; }

    //Methods
    public void addRes(Reservation res) { listRes.add(res); }
    public void suppRes(Reservation res) { listRes.remove(res); }
    public void setSejour(Sejour sej) { sejour = sej; }
    
    
}