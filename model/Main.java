package model;
import java.util.*;
import java.text.*;

public class Main {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		Date test = f.parse("26-03-2023");
		Date test2 = f.parse("31-03-2023");
		Date test3 = f.parse("31-03-2023");
		Date test4 = f.parse("01-04-2023");
		
		Hotel H = new Hotel();
		Chambre ch1 = new Chambre("103", 3);
		H.addChambre(ch1);
		Chambre ch2 = new Chambre("305", 1);
		H.addChambre(ch2);
		
		Client c1 = new Client("Chat", "test", "06");
		H.addClient(c1);
		
		
		System.out.println(H.searchChamberDispo(test, test2));
		Reservation res1 = new Reservation(test, test2);
		res1.addChambre(ch1);
		ch1.addRes(res1);
		c1.addRes(res1);
		//Regrouper tout ça
		//Add prix dans res en multipliant le prix de ch par le nbr de jour (à calculer)
		
		H.check_in("06");
		System.out.println(res1.sejour);
		
		
	}
}





