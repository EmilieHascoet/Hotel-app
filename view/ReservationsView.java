package view;
import model.Chambre;
import model.*;
import controler.ReservationsControl;

import com.toedter.calendar.JDateChooser;
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ReservationsView extends JPanel {
    // Attributs du model
    Hotel hotel;
    Client client;
    JDialog dialog;
    int nbrPlacesMin, nbrPlacesMax, nbrPlaces = 0;      // util pour filtre
    // liste des chambres disponibles par rapport à la date donnée
    public static Vector<Chambre> listChDispo = new Vector<Chambre>();
    Calendar calendrier = Calendar.getInstance();
    public static Vector<Option> listFiltre = new Vector<Option>();

    // Attributs objets graphique
    // padding inner scroll
    EmptyBorder padding10 = new EmptyBorder(10, 10, 10, 10);
    // padding de 20 pixel
    EmptyBorder padding20 = new EmptyBorder(20, 20, 20, 20);
    // padding de 30 pixel
    EmptyBorder padding30 = new EmptyBorder(30, 30, 30, 30);
    // Partie choix date
    JPanel paneNorth = new JPanel();
    EmptyBorder paddingPaneNorth = new EmptyBorder(20, 10, 20, 10);
    Border bottomBorder = BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
    CompoundBorder borderPaneNorth = BorderFactory.createCompoundBorder(bottomBorder, paddingPaneNorth);
    JLabel labelStartDate = new JLabel("Date de début :");
    JDateChooser startDateChooser = new JDateChooser();
    public static Date startDate, endDate;
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
    JSlider slider = new JSlider();
    // Partie affichage des chambres
    JPanel paneCenter = new JPanel();
    JPanel paneInnerScrollCh = new JPanel();
    JScrollPane paneScrollCh = new JScrollPane(paneInnerScrollCh);
    JPanel paneReserver = new JPanel();
    Border topBorder = BorderFactory.createMatteBorder(1,0,0,0,Color.BLACK);
    CompoundBorder borderPaneReserver = BorderFactory.createCompoundBorder(topBorder, padding10);
    JButton buttonReserver = new JButton("Reserver");

    //public ReservationsView() throws ParseException {
    public ReservationsView(Hotel h, Client cl, JDialog d) {
        hotel = h;
        client = cl;
        dialog = d;
        // nbr places par rapport au model
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
        startDate = calendrier.getTime();
        startDateChooser.setDate(startDate);
        calendrier.add(Calendar.DATE, 1);
        endDate = calendrier.getTime();
        endDateChooser.setDate(endDate);
        // Cherche toutes les chambres disponibles
        listChDispo = hotel.searchChamberDispo(startDateChooser.getDate(), endDateChooser.getDate());
        // Change le format affiché
        startDateChooser.setDateFormatString("dd/MM/yyyy");
        endDateChooser.setDateFormatString("dd/MM/yyyy");
        startDateChooser.setPreferredSize(new Dimension(110, 25));
        endDateChooser.setPreferredSize(new Dimension(110, 25));
        // Cherche toutes les chambres disponibles
        listChDispo = hotel.searchChamberDispo(startDateChooser.getDate(), endDateChooser.getDate());
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
        paneInnerScrollOpt.setBorder(new EmptyBorder(10, 5, 0, 0));
        paneScrollOptions.setPreferredSize(new Dimension(160, 200));
        paneScrollOptions.getVerticalScrollBar().setUnitIncrement(10);
        paneScrollOptions.setBorder(titleOptions);
        
        // Ajout des options dans le "panel scroll"
        for (Option opt : hotel.listOption) {
            JCheckBox checkBox = new JCheckBox(opt.type);
            checkBox.setActionCommand(opt.type + " " + opt.prix);
            ReservationsControl ctrOption = new ReservationsControl(hotel, paneInnerScrollCh, nbrPlaces, checkBox);
            checkBox.addActionListener(ctrOption);
            paneInnerScrollOpt.add(checkBox);
        }
        
        // Dimension du label
        labelSlider.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // Créer un slider
        slider.setMinimum(nbrPlacesMin);
        slider.setMaximum(nbrPlacesMax);
        slider.setValue(nbrPlacesMin);
        slider.setPreferredSize(new Dimension(160, 50));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                nbrPlaces = slider.getValue();
                ReservationsControl crtSlider = new ReservationsControl(hotel, paneInnerScrollCh, nbrPlaces, slider);
                crtSlider.filtreChamber();
            }
        });
        // Peindre les ticks et l'étiquette 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
        // Définir l'espacement
        slider.setMajorTickSpacing(1);
        
        // Ajout des objets au panel principal
        paneWest.add(labelFiltres);
        paneWest.add(Box.createVerticalGlue());
        paneWest.add(paneScrollOptions);
        paneWest.add(Box.createVerticalGlue());
        paneWest.add(labelSlider);
        paneWest.add(Box.createRigidArea(new Dimension(0, 10)));
        paneWest.add(slider);


                            // ********** PANEL CHAMBRES DISPO  ********** //
        
        // Définition du LayoutManager du panel
        paneCenter.setLayout(new BoxLayout(paneCenter, BoxLayout.Y_AXIS));
        paneScrollCh.setBorder(null);
        paneScrollCh.getVerticalScrollBar().setUnitIncrement(10);
        paneInnerScrollCh.setLayout(new GridLayout(listChDispo.size()/2+1, 2, 20, 20));
        paneInnerScrollCh.setBorder(padding30);

        // Ajout des chambres dans le "panel scroll"
        for (Chambre ch : listChDispo) {
            // panel chambre
            JPanel panel = new JPanel();
            Border border = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);
            EmptyBorder padding = new EmptyBorder(7, 10, 7, 10);
            panel.setLayout(new GridLayout(7, 1));
            panel.setBorder(BorderFactory.createCompoundBorder(border, padding));

            // Labels
            JLabel label = new JLabel("Chambre n° : " + ch.num);
            JLabel label1 = new JLabel("Etage : " + ch.etage);
            JLabel label2 = new JLabel("Places : " + ch.nbrPlaces);
            JLabel label3 = new JLabel("Prix : " + ch.prix + "/nuit");
            JLabel label4 = new JLabel("Liste options :");
            label4.setForeground(Color.blue);

            // comboBox contenant les options
            JComboBox<String> optionList = new JComboBox<String>();
            for (Option opt : ch.listOption) {
                optionList.addItem(opt.type);
            }
            
            // checkBox
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setActionCommand(ch.num + "");

            // Ajout des objets au panel chambre
            panel.add(label);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(optionList);
            panel.add(checkBox);

            // Ajout du panel chambre au panel principal 
            paneInnerScrollCh.add(panel);
        }

        // Ajout du button au panel
        paneReserver.setBorder(borderPaneReserver);
        paneReserver.add(buttonReserver);

        // Ajout des objets au panel principal
        paneCenter.add(BorderLayout.NORTH, paneScrollCh);
        paneCenter.add(BorderLayout.SOUTH, paneReserver);
        

                            // *************** CONTROLER  *************** //

        // Choix date
        ReservationsControl ctrDate = new ReservationsControl(hotel, paneInnerScrollCh, nbrPlaces, startDateChooser, endDateChooser);
        buttonValider.addActionListener(ctrDate);
        // Reserver les chambres selectionné
        ReservationsControl ctrRes = new ReservationsControl(hotel, client, paneInnerScrollCh, dialog);
        buttonReserver.addActionListener(ctrRes);
    }
}
                    