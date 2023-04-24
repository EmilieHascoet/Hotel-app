package model;
import java.util.*;
import java.text.*;

public class Hotel {
	//Attributes
    public double gain=0;

    public Vector<Chambre> listChambre = new Vector<Chambre>();
    public Vector<Client> listClient = new Vector<Client>();;
    public Vector<Sejour> listSejour = new Vector<Sejour>();;
    public Vector<Reservation> listRes = new Vector<Reservation>();
    public Vector<Produit> listProd = new Vector<Produit>();
    public Vector<Option> listOption = new Vector<Option>();

    //Methods
    public void addChambre(Chambre ch) { listChambre.add(ch); }
    public void addClient(Client c) { listClient.add(c); }
    public void addRes(Reservation res) { listRes.add(res); }
    public void addProduit(Produit p) { listProd.add(p); }
    public void addOption(Option o) { listOption.add(o); }
    
    public void suppRes(Reservation res) { listRes.remove(res); }
    
    public Vector<Chambre> searchChamber(Date start, Date end) { 
    	Vector<Chambre> rep = new Vector<Chambre>();
    	boolean dispo;
    	for (Chambre c : listChambre) {
    		dispo = true;
    		for (Reservation r : c.listRes) {
                if (0 <= start.compareTo(r.dateDeb) && start.compareTo(r.dateFin) < 0
                || 0 < end.compareTo(r.dateDeb) && end.compareTo(r.dateFin) <= 0
                || start.compareTo(r.dateDeb) <= 0 && end.compareTo(r.dateFin) >= 0) { 
                	dispo = false; break; 
                }
    		}
    		if (dispo==true) rep.add(c);
    	}
    	return rep;
    }
    
    public Client searchClient(String t) {
        for (Client c : listClient) {
            if (t.equals(c.tel)) { return c; }
        }
        return null;
    }
    
    public void check_in(String tel) {
    	Client c = searchClient(tel);
    	Date today = new Date();
    	for (Reservation r : c.listRes) {
    		// si la date d'aujourd'hui est égale ou dépasse la date de debut de la reservation
    		if (today.compareTo(r.dateDeb) >= 0 && r.sejour==null && today.compareTo(r.dateFin) < 0) {  
    			double prix = 0;
    			//ajoute le prix de chaque chambre au prix du séjour
    			for (Chambre ch : r.listChambre) { prix += ch.prix; }
    			Sejour s = new Sejour(prix);
    			s.setReservation(r);
    			listSejour.add(s);
    			r.setSejour(s);
    			System.out.println("Sejour cree !");
    		}
    	}
    }
    
    public void check_out(String t) { /* Cherche le séjour et facture le client */ }
    
    public Vector<Option> chooseOption() { 
    	/* Demande au moment de la réservation si le client veut des options pour sa chambre */ 
    	return new Vector<Option>();
    }
    
    public Vector<Chambre> triChambres(Vector<Chambre> ch, Vector<Option> o) { 
    	// Tri les chambres en fonction des préférences : du plus au moins d'option
    	/* tailleListOpt=5
    	Créé une liste et setSize(tailleListOpt)
    	nbrOptCommun=3
    	5-3
    	Add à la sous-liste à la position 2 si elle existe, sinon créé la sous-liste
    	nbrOpt=5
    	5-5
    	Add à la sous-liste à la position 0 si elle existe, sinon créé la sous-liste
    	Renvoie une liste de liste */
    	return new Vector<Chambre>();
    }
    
    public Vector<Produit> chooseProduit() { /* Demande après le check-in si le client veut des
    options pour son séjour */
    	return new Vector<Produit>();
    }
}








