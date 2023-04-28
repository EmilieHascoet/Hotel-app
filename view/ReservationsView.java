package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ReservationsView extends JFrame {
    public ReservationsView() {
        // Partie filtre
        JPanel paneWest = new JPanel();
        TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Filtres");
        JPanel paneFilter = new JPanel();
        JLabel jLabelOption = new JLabel();
        JPanel paneCheckBoxes = new JPanel();
        JButton butonFilter = new JButton("Filtrer");
        // Partie choix date
        JPanel paneNorth = new JPanel();
        // Partie affichage des chambres
        JPanel paneCenter = new JPanel();

    }
}
