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
    static JFrame main;
    public static void main(String args[]) throws ParseException {
        Hotel hotel = new Hotel();

        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        Date date = f.parse("26-03-2023");
        Date date2 = f.parse("30-03-2023");
        Client client = new Client("Chat", "Test", "0624205906");
        Client c1 = new Client("Client 1", "Test", "061");
        Client c2 = new Client("Client 2", "Test", "062");
        Client c3 = new Client("Client 3", "Test", "063");
        Client c4 = new Client("Client 4", "Test", "064");
        Client c5 = new Client("Client 5", "Test", "065");

        Reservation r1 = new Reservation(date, date2);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        c1.addRes(r1);
        hotel.addClient(c1);
        hotel.addClient(c2);
        hotel.addClient(c3);
        hotel.addClient(c4);
        hotel.addClient(c5);

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
        JPanel reservation = new ReservationsView(hotel, client);
        JDialog d = new JDialog(main, "Créer une reservation pour " + client.nom, true);
        d.add(reservation);
        d.setSize(700, 500);
        //d.setVisible(true);
    }
}
