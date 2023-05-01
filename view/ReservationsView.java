package view;
import model.Chambre;
import model.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controler.ReservationsControl;

import java.util.*;
import java.text.*;
import java.time.LocalDate;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ReservationsView extends JPanel {
    // Attributs du model
    Hotel hotel;
    Client client;
    // list des chambres disponibles par rapport à la date donnée
    Vector<Chambre> listChambres = new Vector<Chambre>();
    Calendar calendrier = Calendar.getInstance();
    Vector<Option> listFiltre = new Vector<Option>();

    // Attributs objets graphique
    // padding de 20 10 pixel
    EmptyBorder paddingPaneNorth = new EmptyBorder(20, 10, 20, 10);
    // Partie choix date
    JPanel paneNorth = new JPanel();
    Border bottomBorder = BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
    CompoundBorder borderPaneNorth = BorderFactory.createCompoundBorder(bottomBorder, paddingPaneNorth);
    JLabel labelStartDate = new JLabel("Date de début :");
    JDateChooser startDateChooser = new JDateChooser();
    JLabel labelEndDate = new JLabel("Date de fin :");
    JDateChooser endDateChooser = new JDateChooser();
    JButton buttonValider = new JButton("Valider");
    // Partie filtre
    JPanel paneWest = new JPanel();
    TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Filtres");
    JPanel paneFilter = new JPanel();
    JLabel jLabelOption = new JLabel();
    JPanel paneCheckBoxes = new JPanel();
    JButton butonFilter = new JButton("Filtrer");
    // Partie affichage des chambres
    JPanel paneCenter = new JPanel();
    JPanel paneInnerScroll = new JPanel();

    //public ReservationsView() throws ParseException {
    public ReservationsView(Hotel h, Client cl) {
        hotel = h;
        client = cl;

                            // ********** PANEL PRINCIPAL ********** //

        // Définit le layout manager et ajoute les panels enfants
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, paneNorth);
        this.add(BorderLayout.WEST, paneWest);
        this.add(BorderLayout.CENTER, paneCenter);
        // Ajout des bordures des panels
        paneWest.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));


                            // ********** PANEL CHOIX DATE ********** //

        // Fix les dates pour correspondre à aujourd'hui et le lendemain
        calendrier.set(Calendar.MILLISECOND, 0);
        calendrier.set(Calendar.SECOND, 0);
        calendrier.set(Calendar.MINUTE, 0);
        calendrier.set(Calendar.HOUR_OF_DAY, 0);
        startDateChooser.setDate(calendrier.getTime());
        calendrier.add(Calendar.DATE, 1);
        endDateChooser.setDate(calendrier.getTime());
        // Change le format affiché
        startDateChooser.setDateFormatString("dd/MM/yyyy");
        endDateChooser.setDateFormatString("dd/MM/yyyy");
        startDateChooser.setPreferredSize(new Dimension(110, 25));
        endDateChooser.setPreferredSize(new Dimension(110, 25));
        // Définit le layout manager et ajoute les objets au panel
        paneNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        paneNorth.setBorder(borderPaneNorth);
        paneNorth.add(labelStartDate);
        paneNorth.add(startDateChooser);
        paneNorth.add(labelEndDate);
        paneNorth.add(endDateChooser);
        paneNorth.add(buttonValider);

                        // ********** PANEL CHAMBRES DISPO  ********** //

        listChambres = hotel.searchChamber(startDateChooser.getDate(), endDateChooser.getDate());
        for (Chambre ch : listChambres) {
            
        }

        // Creation des instances de gestionnaire d'evenement
        ReservationsControl ctr = new ReservationsControl(hotel, paneInnerScroll, listChambres, startDateChooser, endDateChooser);
        buttonValider.addActionListener(ctr);
    }
}
