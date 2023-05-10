package model;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.*;

public class Main {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		Date test = f.parse("10-05-2023");
		Date test2 = f.parse("12-05-2023");
		
		Hotel H = new Hotel();
		Chambre ch1 = new Chambre("103", 3);
		H.addChambre(ch1);
		Chambre ch2 = new Chambre("305", 1);
		H.addChambre(ch2);
		
		Client c1 = new Client("Chat", "test", "06");
		H.addClient(c1);
		
		Reservation res1 = new Reservation(test, test2);
		res1.addChambre(ch1);
		res1.addChambre(ch2);

		c1.addRes(res1);
	}
}





