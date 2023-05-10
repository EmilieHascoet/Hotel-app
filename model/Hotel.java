package model;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
    public void addRes(Reservation res, Client c) { 
		listRes.add(res); res.setClient(c); c.addRes(res);
		for (Chambre ch : res.listChambre) {
			ch.addRes(res);
		}
	}
    public void addProduit(Produit p) { listProd.add(p); }
    public void addOption(Option o) { listOption.add(o); }
    
    public void suppRes(Reservation res) { 
		listRes.remove(res); res.client.suppRes(res);
		for (Chambre ch : res.listChambre) {
			ch.addRes(res);
		}
	}
    
	// Methods reservation
	public Vector<Chambre> everyOption(Vector<Chambre> listCh, Vector<Option> listOptions, int places) { 
		Vector<Chambre> listChFilte = new Vector<Chambre>();
		for(Chambre ch : listCh) {
			if(ch.nbrPlaces >= places && ch.listOption.containsAll(listOptions)) listChFilte.add(ch);
		}
		return listChFilte;
	}

	public Vector<Chambre> someOption(Vector<Chambre> listCh, Vector<Option> listOptions, int places) { 
		Vector<Chambre> listChFilte = new Vector<Chambre>();
		for(Chambre ch : listCh) {
			if(ch.nbrPlaces >= places && !(ch.listOption.containsAll(listOptions))) {
				for(Option opt : listOptions){
					if(ch.listOption.contains(opt)) {
						listChFilte.add(ch);
						break;
					}
				}
			}
		}
		return listChFilte;
	}

	public int nbrPlacesMin() {
		int min = 0;
		for(Chambre ch : listChambre) {
			min = ch.nbrPlaces < min ? ch.nbrPlaces : min;
		}
		return min;
	}

	public int nbrPlacesMax() {
		int max = 0;
		for(Chambre ch : listChambre) {
			max = ch.nbrPlaces > max ? ch.nbrPlaces : max;
		}
		return max;
	}
	
	// public Vector<Chambre> triChambres(Vector<Chambre> ch, Vector<Option> o) { 
		// Tri les chambres en fonction des préférences : du plus au moins d'option
		/* tailleListOpt=5
		Créé une liste et setSize(tailleListOpt)
		nbrOptCommun=3
		5-3
		Add à la sous-liste à la position 2 si elle existe, sinon créé la sous-liste
		nbrOpt=5
		5-5
		Add à la sous-liste à la position 0 si elle existe, sinon créé la sous-liste
		Renvoie une liste de liste 
		return new Vector<Chambre>();
	}*/

    public Vector<Chambre> searchChamberDispo(Date start, Date end) { 
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

	public Chambre searchChamber(String num) {
		for (Chambre ch : listChambre) {
            if (num.equals(ch.num)) { return ch; }
        }
        return null;
	}
    
	// Methods client
    public Vector<Client> searchClients(String str) {
		Vector<Client> lClients = new Vector<Client>();
		for (Client c : listClient) {
			String nomPrenom = c.nom + " " + c.prenom;
				if (nomPrenom.contains(str)) { lClients.add(c); }
		}
        return lClients;
    }

    public Client searchClient(String t) {
		for (Client c : listClient) {
				if (t.equals(c.tel)) { return c; }
		}
        return null;
    }
    

	// Methods enregistrement
	public Vector<Reservation> arrivees(String str) {
		Vector<Client> lClients = listClient;
		if (!str.isEmpty()) {
			lClients = searchClients(str);
		}
		Vector<Reservation> lReservations = new Vector<Reservation>();
		Date today = new Date();
		for (Client cl : lClients) {
			for (Reservation res : cl.listRes) {
				// si la date d'aujourd'hui est égale ou dépasse la date de debut de la reservation
				if (today.compareTo(res.dateDeb) >= 0 && res.sejour==null && today.compareTo(res.dateFin) < 0) {
					lReservations.add(res);
				}
			}
		}
		return lReservations;
	}

	public Vector<Reservation> departs(String str) {
		Vector<Client> lClients = listClient;
		if (!str.isEmpty()) {
			lClients = searchClients(str);
		}
		Vector<Reservation> lReservations = new Vector<Reservation>();
		Date today = new Date();
		for (Client cl : lClients) {
			if (cl.sejour != null && today.compareTo(cl.sejour.reservation.dateFin) >= 0) {
				lReservations.add(cl.sejour.reservation);
			}
		}
		return lReservations;
	}

    public void check_in(Reservation res) {
		// Calcul le nombe de jours entre la date de début et de fin
		long diffInMillies = res.dateFin.getTime() - res.dateDeb.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		//ajoute le prix de chaque chambre au prix du séjour
		double prix = 0;
		for (Chambre ch : res.listChambre) { prix += ch.prix * diffInDays; }
		Sejour sej = new Sejour(prix);
		sej.setReservation(res);
		res.setSejour(sej);
		res.client.setSejour(sej);
    }
    
	public void check_out(String t) { /* Cherche le séjour, le supprime et facture le client */ }


	public Vector<Produit> chooseProduit() { /* Demande après le check-in si le client veut des
	options pour son séjour */
		return new Vector<Produit>();
	}

	// Methods option and produit
	public Option searchOption(String oldType , double oldPrix) {
		for(Option opt : listOption) {
			if(opt.type.equals(oldType) && opt.prix == oldPrix) {
				return opt;
			}
		}
		return null;
	}

	public void changeOption(Option opt, String newType, double newPrix) {
		opt.type = newType;
		opt.prix = newPrix;
	}

	public Produit searchProd(String oldType , double oldPrix) {
		for(Produit prod : listProd) {
			if(prod.type.equals(oldType) && prod.prix == oldPrix) {
				return prod;
			}
		}
		return null;
	}

	public void changeProd(Produit prod, String newType, double newPrix) {
		prod.type = newType;
		prod.prix = newPrix;
	}
}









