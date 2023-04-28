package view;

import com.toedter.calendar.JCalendar;
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
        JLabel labelStartDate = new JLabel("Date de début :");
        JCalendar calendarStartDate = new JCalendar();
        JLabel labelEndDate = new JLabel("Date de début :");
        JCalendar calendarEndDate = new JCalendar();
        JButton buttonValider = new JButton("Valider");
        // Partie affichage des chambres
        JPanel paneCenter = new JPanel();

    }
}
