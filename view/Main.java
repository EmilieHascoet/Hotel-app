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
    public static void main(String args[]) throws ParseException {
        Hotel hotel = new Hotel();

        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        Date date = f.parse("26-03-2023");
        Client client = new Client("Chat", date, "0624205906");
        hotel.addClient(client);

        Client client2 = new Client("Chaton", date, "0624205906");
        hotel.addClient(client2);

        Option o1 = new Option("douche", 5);
        Option o2 = new Option("salle de bain", 10);
        Option o3 = new Option("vue sur la mer", 10);
        Option o4 = new Option("grand lit", 1);
        hotel.addOption(o1);
        hotel.addOption(o2);
        hotel.addOption(o3);
        hotel.addOption(o4);

        Chambre ch1 = new Chambre("100", 6);
        Chambre ch2 = new Chambre("100", 6);
        Chambre ch3 = new Chambre("100", 6);
        Chambre ch4 = new Chambre("100", 6);
        Chambre ch5 = new Chambre("100", 6);
        Chambre ch6 = new Chambre("100", 6);
        Chambre ch7 = new Chambre("100", 6);
        Chambre ch8 = new Chambre("100", 6);
        Chambre ch9 = new Chambre("100", 6);


        ch1.addOption(o1);
        ch1.addOption(o2);
        ch1.addOption(o3);
        ch2.addOption(o4);
        ch2.addOption(o2);
        ch3.addOption(o1);

        hotel.addChambre(ch1);
        hotel.addChambre(ch2);
        hotel.addChambre(ch3);
        hotel.addChambre(ch4);
        hotel.addChambre(ch5);
        hotel.addChambre(ch6);
        hotel.addChambre(ch7);
        hotel.addChambre(ch8);
        hotel.addChambre(ch9);

		Date date1 = f.parse("10-05-2023");
		Date date2 = f.parse("12-05-2023");
		Date date3 = f.parse("04-05-2023");
		Date date4 = f.parse("10-05-2023");
		
		Reservation res1 = new Reservation(date1, date2);
		res1.addChambre(ch1);
        res1.setClient(client);
        client.addRes(res1);
		
		Reservation res2 = new Reservation(date3, date4);
		res2.addChambre(ch2);
        res2.setClient(client2);
        client.addRes(res2);
        hotel.check_in(res2);

        
        // UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        // for (UIManager.LookAndFeelInfo look : looks) {
        //     System.out.println(look.getClassName());
        // }

        // try {
        //     UIManager.setLookAndFeel( new FlatLightLaf() );
        // } catch( Exception ex ) {
        //     System.err.println( "Failed to initialize LaF" );
        // }

        JFrame main = new MainPage(hotel);
        JPanel reservation = new ReservationsView(hotel, client);
        JDialog d = new JDialog(main, "Créer une reservation pour " + client.nom, true);
        d.add(reservation);
        d.setSize(700, 500);
        // d.setVisible(true);
    }
}
