package view;
import model.*;

public class Main {
    public static void main(String args[]) {
        Hotel hotel = new Hotel();
        Option o1 = new Option("douche", 5);
        Option o2 = new Option("salle de bain", 10);
        Option o3 = new Option("vue sur la mer", 10);
        Option o4 = new Option("grand lit", 1);
        hotel.addOption(o1);
        hotel.addOption(o2);
        hotel.addOption(o3);
        hotel.addOption(o4);

        // Chambre ch1 = new Chambre(1, 4);
        // Chambre ch2 = new Chambre(2, 4);
        // Chambre ch3 = new Chambre(3, 4);
        // Chambre ch4 = new Chambre(4, 4);
        // Chambre ch5 = new Chambre(5, 4);
        // Chambre ch6 = new Chambre(6, 4);
        // Chambre ch7 = new Chambre(7, 4);
        // Chambre ch8 = new Chambre(8, 4);
        // Chambre ch9 = new Chambre(9, 4);

        // hotel.addChambre(ch1);
        // hotel.addChambre(ch2);
        // hotel.addChambre(ch3);
        // hotel.addChambre(ch4);
        // hotel.addChambre(ch5);
        // hotel.addChambre(ch6);
        // hotel.addChambre(ch7);
        // hotel.addChambre(ch8);
        // hotel.addChambre(ch9);
        // hotel.addChambre(ch1);
        // hotel.addChambre(ch2);
        // hotel.addChambre(ch3);
        // hotel.addChambre(ch4);
        // hotel.addChambre(ch5);
        // hotel.addChambre(ch6);
        // hotel.addChambre(ch7);
        // hotel.addChambre(ch8);
        // hotel.addChambre(ch9);

        new MainPage(hotel);
    }
}
