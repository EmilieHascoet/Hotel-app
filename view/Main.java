package view;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.UIManager;

import model.*;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        // HOTEL
        Hotel hotel = new Hotel();

        // CLIENTS
        Client c1 = new Client("Nathan", "Bapin", "06 12 50 64 53");
        Client c2 = new Client("Martin", "Dupond", "06 26 84 25 85");
        Client c3 = new Client("Lucie", "Lupin", "06 35 64 12 28");
        Client c4 = new Client("Emilie", "Hascoët", "06 42 12 69 58");
        Client c5 = new Client("Mayu", "Kawai", "06 52 54 78 59");

        hotel.addClient(c1);
        hotel.addClient(c2);
        hotel.addClient(c3);
        hotel.addClient(c4);
        hotel.addClient(c5);

        // OPTIONS
        Option o1 = new Option("douche", 5);
        Option o2 = new Option("salle de bain", 10);
        Option o3 = new Option("vue sur la mer", 5);
        Option o4 = new Option("grand lit", 1);

        hotel.addOption(o1);
        hotel.addOption(o2);
        hotel.addOption(o3);
        hotel.addOption(o4);

        // CHAMBRES
        Chambre ch1 = new Chambre("101", 6);
        ch1.addOption(o1);
        ch1.addOption(o2);
        ch1.addOption(o3);
        Chambre ch2 = new Chambre("102", 4);
        ch2.addOption(o4);
        ch2.addOption(o2);
        Chambre ch3 = new Chambre("103", 3);
        ch3.addOption(o1);
        Chambre ch4 = new Chambre("104", 2);
        Chambre ch5 = new Chambre("205", 2);
        Chambre ch6 = new Chambre("106", 8);
        Chambre ch7 = new Chambre("307", 1);
        Chambre ch8 = new Chambre("108", 5);
        Chambre ch9 = new Chambre("109", 6);

        hotel.addChambre(ch1);
        hotel.addChambre(ch2);
        hotel.addChambre(ch3);
        hotel.addChambre(ch4);
        hotel.addChambre(ch5);
        hotel.addChambre(ch6);
        hotel.addChambre(ch7);
        hotel.addChambre(ch8);
        hotel.addChambre(ch9);


        // RESERVATIONS
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		Date date2 = f.parse("5-05-2023");
		Date date3 = f.parse("10-05-2023");
        Date today = new Date();
		Date date4 = f.parse("15-06-2023");

		Reservation res1 = new Reservation(date2, today);
		res1.addChambre(ch1);
        hotel.addRes(res1, c1);
		
		Reservation res2 = new Reservation(date3, date4);
		res2.addChambre(ch2);
        hotel.addRes(res2, c2);
        
        // SEJOURS
		Reservation res3 = new Reservation(today, date4);
		res3.addChambre(ch4);
        res3.addChambre(ch6);
        hotel.addRes(res3, c3);
        hotel.check_in(res3);
        
		Reservation res4 = new Reservation(date3, today);
		res4.addChambre(ch5);
        hotel.addRes(res4, c4);
        hotel.check_in(res4);
        
        // produits
        Produit p1 = new Produit("Petit dej", 5);
        Produit p2 = new Produit("Resto à volonté", 50);
        Produit p3 = new Produit("Ménage", 20);
        Produit p4 = new Produit("Accès ilimité mur escalade", 50);

        hotel.listProd.add(p1);
        hotel.listProd.add(p2);
        hotel.listProd.add(p3);
        hotel.listProd.add(p4);
        res3.sejour.listProduit.add(p1);
        res3.sejour.listProduit.add(p2);
        
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            } 
        catch (Exception e) {
            e.printStackTrace();
        }

        main = new MainPage(hotel);
    }
}
