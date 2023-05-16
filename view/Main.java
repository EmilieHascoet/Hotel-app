package view;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import model.*;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        // HOTEL
        Hotel hotel = new Hotel();

        // CLIENTS
        Client c1 = new Client("Chat", "Bapin", "061");
        Client c2 = new Client("Chaton", "Hascoët", "062");
        Client c3 = new Client("Nathan", "Bapin", "063");
        Client c4 = new Client("Emilie", "Hascoët", "064");
        Client c5 = new Client("Mayu", "Hascoët", "065");

        hotel.addClient(c1);
        hotel.addClient(c2);
        hotel.addClient(c3);
        hotel.addClient(c4);
        hotel.addClient(c5);

        // OPTIONS
        Option o1 = new Option("douche", 5);
        Option o2 = new Option("salle de bain", 10);
        Option o3 = new Option("vue sur la mer", 10);
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
        Chambre ch2 = new Chambre("102", 6);
        ch2.addOption(o4);
        ch2.addOption(o2);
        Chambre ch3 = new Chambre("103", 6);
        ch3.addOption(o1);
        Chambre ch4 = new Chambre("104", 6);
        Chambre ch5 = new Chambre("105", 6);
        Chambre ch6 = new Chambre("106", 6);
        Chambre ch7 = new Chambre("107", 6);
        Chambre ch8 = new Chambre("108", 6);
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
        Date today = new Date();
		Date date2 = f.parse("25-05-2023");
		Date date3 = f.parse("04-05-2023");
		Date date4 = f.parse("20-05-2023");

		Reservation res1 = new Reservation(today, date2);
		res1.addChambre(ch1);
        hotel.addRes(res1, c1);
		
		Reservation res2 = new Reservation(date3, today);
		res2.addChambre(ch2);
        hotel.addRes(res2, c2);
        hotel.check_in(res2);

		Reservation res3 = new Reservation(date3, date4);
		res3.addChambre(ch4);
        res3.addChambre(ch6);
        hotel.addRes(res3, c3);

        Produit p1 = new Produit("Petit dej", 5);
        Produit p2 = new Produit("Resto à volonté", 50);
        Produit p3 = new Produit("Ménage", 20);
        Produit p4 = new Produit("Test", 200);

        hotel.listProd.add(p1);
        hotel.listProd.add(p2);
        hotel.listProd.add(p3);
        hotel.listProd.add(p4);
        res2.sejour.listProduit.add(p1);
        res2.sejour.listProduit.add(p2);
        
        // UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        // for (UIManager.LookAndFeelInfo look : looks) {
        //     System.out.println(look.getClassName());
        // }

        // try {
        //     UIManager.setLookAndFeel( new FlatLightLaf() );
        // } catch( Exception ex ) {
        //     System.err.println( "Failed to initialize LaF" );
        // }

        main = new MainPage(hotel);
    }
}
