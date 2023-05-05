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
    int nbrPlacesMin = 0;   // util pour filtre
    int nbrPlacesMax = 0;
    // liste des chambres disponibles par rapport à la date donnée
    Vector<Chambre> listChDispo = new Vector<Chambre>();
    // liste des chambres selectionné par l'utilisateur
    Vector<Chambre> listChSelected = new Vector<Chambre>();
    Calendar calendrier = Calendar.getInstance();
    Vector<Option> listFiltre = new Vector<Option>();

    // Attributs objets graphique
    // padding de 20 10 pixel
    EmptyBorder paddingPaneNorth = new EmptyBorder(20, 10, 20, 10);
    // padding inner scroll
    EmptyBorder paddingScroll = new EmptyBorder(10, 5, 0, 0);
    // padding de 20 pixel
    EmptyBorder padding20 = new EmptyBorder(20, 20, 20, 20);
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
    Border rightBorder = BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK);
    CompoundBorder borderPaneWest = BorderFactory.createCompoundBorder(rightBorder, padding20);
    JLabel labelFiltres = new JLabel("Filtres");
    JPanel paneInnerScrollOpt = new JPanel();
    TitledBorder titleOptions = BorderFactory.createTitledBorder("Options");
    JScrollPane paneScrollOptions = new JScrollPane(paneInnerScrollOpt);
    JLabel labelSlider = new JLabel("Nombre de places");
    JButton buttonFiltre = new JButton("Filtrer");
    // Partie affichage des chambres
    JPanel paneCenter = new JPanel();
    JPanel paneInnerScrollCh = new JPanel();
    JScrollPane paneScrollCh = new JScrollPane(paneInnerScrollCh);
    JButton buttonReserver = new JButton("Reserver");

    //public ReservationsView() throws ParseException {
    public ReservationsView(Hotel h, Client cl) {
        hotel = h;
        client = cl;
        nbrPlacesMin = hotel.nbrPlacesMin();
        nbrPlacesMax = hotel.nbrPlacesMax();


                            // ********** PANEL PRINCIPAL ********** //
        
        // Définit le layout manager et ajoute les panels enfants
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, paneNorth);
        this.add(BorderLayout.WEST, paneWest);
        this.add(BorderLayout.CENTER, paneCenter);
        
        
                            // ************ PANEL CHOIX DATE ************ //
        
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
        

                            // *********** PANEL CHOIX FILTRES *********** //
        
        // Modification du format du titres
        labelFiltres.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelFiltres.setForeground(Color.RED);
        Font font = labelFiltres.getFont();
        float newSize = font.getSize() + 4;
        labelFiltres.setFont(font.deriveFont(newSize));

        // Définition des propriétés graphiques des panels
        paneWest.setLayout(new BoxLayout(paneWest, BoxLayout.Y_AXIS));
        paneWest.setBorder(borderPaneWest);
        paneInnerScrollOpt.setLayout(new BoxLayout(paneInnerScrollOpt, BoxLayout.Y_AXIS));
        paneInnerScrollOpt.setBorder(paddingScroll);
        paneScrollOptions.setPreferredSize(new Dimension(160, 200));
        paneScrollOptions.setBorder(titleOptions);
        
        // Ajout des options dans le "panel scroll"
        for (Option opt : hotel.listOption) {
            JCheckBox checkBox = new JCheckBox(opt.type);
            checkBox.setActionCommand(opt.type + " " + opt.prix);
            paneInnerScrollOpt.add(checkBox);
        }

        // Dimension du label
        labelSlider.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // Créer un slider 
        JSlider slider = new JSlider(nbrPlacesMin, nbrPlacesMax, nbrPlacesMin); 
        // Peindre les ticks et l'étiquette 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
        // Définir l'espacement
        slider.setMajorTickSpacing(1);

        // Centre le bouton
        buttonFiltre.setAlignmentX(CENTER_ALIGNMENT);
        
        // Ajout des objets au panel principal
        paneWest.add(labelFiltres);
        paneWest.add(Box.createVerticalGlue());
        paneWest.add(paneScrollOptions);
        paneWest.add(Box.createVerticalGlue());
        paneWest.add(labelSlider);
        paneWest.add(Box.createRigidArea(new Dimension(0, 10)));
        paneWest.add(slider);
        paneWest.add(Box.createVerticalGlue());
        paneWest.add(buttonFiltre);


                            // ********** PANEL CHAMBRES DISPO  ********** //
        
        // Définition du LayoutManager du panel
        paneCenter.setLayout(new BoxLayout(paneCenter, BoxLayout.Y_AXIS));
        // Cherche toutes les chambres disponibles
        listChDispo = hotel.searchChamberDispo(startDateChooser.getDate(), endDateChooser.getDate());
        paneInnerScrollCh.setLayout(new GridLayout(listChDispo.size()/2, 6, 0, 10));
        
        // Ajout des chambres dans le "panel scroll"
        for (Chambre ch : listChDispo) {
            JPanel panel= new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 0));
            JPanel panelLabel = new JPanel();
            panelLabel.setLayout(new GridLayout(3,1, 0, 5));

            JLabel label = new JLabel("Chambre numéro : " + ch.num);
            JLabel label2 = new JLabel("Etage : " + ch.etage + ", Places : " + ch.nbrPlaces);
            JLabel label3 = new JLabel("Prix : " + ch.prix + "/nuit");
            JCheckBox checkBox = new JCheckBox();
            checkBox.setActionCommand(ch.num + "");
            
            panelLabel.add(label);
            panelLabel.add(label2);
            panelLabel.add(label3);
            panel.add(checkBox);
            panel.add(panelLabel);
            paneInnerScrollCh.add(panel);

            ReservationsControl ctrChSelected = new ReservationsControl(checkBox, listChSelected);
            checkBox.addActionListener(ctrChSelected);
        }

        // Ajout des objets au panel principal
        paneCenter.add(paneScrollCh);
        // Centre le button
        buttonReserver.setAlignmentX(CENTER_ALIGNMENT);
        paneCenter.add(buttonReserver);
        

                            // *************** CONTROLER  *************** //

        // Choix date
        ReservationsControl ctrDate = new ReservationsControl(hotel, paneInnerScrollCh, listChDispo, listFiltre, startDateChooser, endDateChooser);
        buttonValider.addActionListener(ctrDate);
        // Filtrer chambre
        ReservationsControl crtFiltre = new ReservationsControl(hotel, paneInnerScrollCh, listChDispo, listFiltre, paneInnerScrollOpt);
    }
}
                    