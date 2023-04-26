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

        new MainPage(hotel);
    }
}
