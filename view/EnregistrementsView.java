package view;

import javax.swing.*;
import javax.swing.border.*;

import controler.EnregistrementsControl;
import controler.radioButtonListener;

import java.awt.*;
import java.util.Vector;

import model.*;

public class EnregistrementsView extends JPanel {
    // Attribut du model 
    Hotel hotel;

    // Attributs objets graphique

    // Icons
    ImageIcon icon = new ImageIcon("./icons/search.png");
    // modifie la taille de l'icone pour qu'elle cover le background du bouton
    Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(img);
    // Bordures
    Border bottomBorder = BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
    EmptyBorder padding20 = new EmptyBorder(20, 20, 20, 20);
    EmptyBorder centerPadding = new EmptyBorder(10, 20, 10, 20);
    EmptyBorder buttonPadding = new EmptyBorder(10, 0, 0, 0);
    CompoundBorder borderPaneNorth = BorderFactory.createCompoundBorder(bottomBorder, padding20);
    // panel du titre
    JPanel paneNorth = new JPanel();
    JLabel titre = new JLabel("Enregistrements d'aujourd'hui");
    // panel central 
    JPanel paneCenter = new JPanel();
    // panel des arrivees
    JPanel paneWest = new JPanel();
    JPanel searchBarArr = new JPanel();
    JButton searchButtonArr = new JButton(resizedIcon);
    JTextField searchTFArr = new JTextField("Entrez nom, prenom", 20);
    TitledBorder titleArr = BorderFactory.createTitledBorder("Arrivées");
    JPanel innerScrollArr = new JPanel();
    JScrollPane scrollArr = new JScrollPane(innerScrollArr);
    ButtonGroup groupArr = new ButtonGroup();
    JPanel paneButtonArr = new JPanel();
    JButton checkIn = new JButton("Check-in");
    JButton supprimer = new JButton("Supprimer");
    // panel des departs
    JPanel paneEast = new JPanel();
    JPanel searchBarDep = new JPanel();
    JButton searchButtonDep = new JButton(resizedIcon);
    JTextField searchTFDep = new JTextField("Entrez nom, prenom", 20);
    TitledBorder titleDep = BorderFactory.createTitledBorder("Departs");
    JPanel innerScrollDep = new JPanel();
    JScrollPane scrollDep = new JScrollPane(innerScrollDep);
    ButtonGroup groupDep = new ButtonGroup();
    JPanel paneButtonDep = new JPanel();
    JButton facturer = new JButton("Facturer");

    public EnregistrementsView(Hotel h) {
        hotel = h;

        this.setLayout(new BorderLayout());
        

        // PANEL TITRE
        paneNorth.setBorder(borderPaneNorth);
        paneNorth.setBackground(new Color(161,177,152));
        // Modification du format du titres
        titre.setForeground(Color.WHITE);
        Font font = titre.getFont();
        float newSize = font.getSize() + 7;
        titre.setFont(font.deriveFont(newSize));
        paneNorth.add(titre);


        // PANEL CENTRAL
        paneCenter.setLayout(new GridLayout(1, 2));
        paneCenter.add(paneWest);
        paneCenter.add(paneEast);

        // PANEL DES ARRIVEES
        paneWest.setLayout(new BoxLayout(paneWest, BoxLayout.Y_AXIS));
        paneWest.setBorder(centerPadding);
        // search panel
        searchButtonArr.setName("arrivees");
        searchButtonArr.setPreferredSize(new Dimension(25, 25));
        searchBarArr.add(searchButtonArr);
        searchBarArr.add(searchTFArr);
        // scroll panel des arrivants
        scrollArr.setBorder(titleArr);
        scrollArr.setPreferredSize(new Dimension(100, 300));
        innerScrollArr.setLayout(new BoxLayout(innerScrollArr, BoxLayout.Y_AXIS));
        innerScrollArr.setBorder(new EmptyBorder(10, 5, 0, 0));
        // liste des arrivees prevues aujourd'hui
        for (Reservation res : hotel.arrivees("")) {
            JRadioButton RadioButton = new JRadioButton(res.client.nom + " " + res.client.prenom);
            // instance d'evenement pour rendre le button clickable
            RadioButton.addActionListener(new radioButtonListener(checkIn));
            RadioButton.addActionListener(new radioButtonListener(supprimer));
            RadioButton.setActionCommand(res.id+"");
            groupArr.add(RadioButton);
            innerScrollArr.add(RadioButton);
        }
        
        // panel des bouttons d'action
        paneButtonArr.setBorder(buttonPadding);
        checkIn.setEnabled(false);
        supprimer.setEnabled(false);
        paneButtonArr.add(checkIn);
        paneButtonArr.add(supprimer);
        
        // Ajout des objets au panel
        paneWest.add(searchBarArr);
        paneWest.add(scrollArr);
        paneWest.add(paneButtonArr);
        
        
        // PANEL DES DEPARTS
        paneEast.setLayout(new BoxLayout(paneEast, BoxLayout.Y_AXIS));
        paneEast.setBorder(centerPadding);
        // search panel
        searchButtonDep.setName("departs");
        searchButtonDep.setPreferredSize(new Dimension(25, 25));
        searchBarDep.add(searchButtonDep);
        searchBarDep.add(searchTFDep);
        // scroll panel des départs
        scrollDep.setBorder(titleDep);
        scrollDep.setPreferredSize(new Dimension(100, 300));
        innerScrollDep.setLayout(new BoxLayout(innerScrollDep, BoxLayout.Y_AXIS));
        innerScrollDep.setBorder(new EmptyBorder(10, 5, 0, 0));
        // Liste des départs prevues aujourd'hui
        for (Sejour sej : hotel.departs("")) {
            JRadioButton RadioButton = new JRadioButton(sej.reservation.client.nom + " " + sej.reservation.client.prenom);
            // instance d'evenement pour rendre le button clickable
            RadioButton.addActionListener(new radioButtonListener(facturer));
            RadioButton.setActionCommand(sej.reservation.id+"");
            groupDep.add(RadioButton);
            innerScrollDep.add(RadioButton);
        }
        
        // panel des bouttons d'action
        paneButtonDep.setBorder(buttonPadding);
        facturer.setEnabled(false);
        paneButtonDep.add(facturer);
        
        // Ajout des objets au panel
        paneEast.add(searchBarDep);
        paneEast.add(scrollDep);
        paneEast.add(paneButtonDep);
        
        this.add(BorderLayout.NORTH, paneNorth);
        this.add(BorderLayout.CENTER, paneCenter);
        


                            // *************** CONTROLER  *************** //

        // button rechercher
        EnregistrementsControl ctrSearchArr = new EnregistrementsControl(hotel, searchTFArr, innerScrollArr, checkIn, supprimer);
        searchButtonArr.addActionListener(ctrSearchArr);
        EnregistrementsControl ctrSearchDep = new EnregistrementsControl(hotel, searchTFDep, innerScrollDep, facturer);
        searchButtonDep.addActionListener(ctrSearchDep);

        // button check in
        EnregistrementsControl ctrCheckIn = new EnregistrementsControl(hotel, groupArr, innerScrollArr);
        checkIn.addActionListener(ctrCheckIn);
    }
}