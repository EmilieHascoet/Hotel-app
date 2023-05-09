package view;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

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
    JTextField searchTFArr = new JTextField(15);
    TitledBorder titleArr = BorderFactory.createTitledBorder("Arrivées");
    JPanel innerScrollArr = new JPanel();
    JScrollPane scrollArr = new JScrollPane(innerScrollArr);
    JPanel paneButtonArr = new JPanel();
    JButton checkIn = new JButton("Check-in");
    JButton supprimer = new JButton("Supprimer");
    // panel des departs
    JPanel paneEast = new JPanel();
    JPanel searchBarDep = new JPanel();
    JButton searchButtonDep = new JButton(resizedIcon);
    JTextField searchTFDep = new JTextField(15);
    TitledBorder titleDep = BorderFactory.createTitledBorder("Departs");
    JPanel innerScrollDep = new JPanel();
    JScrollPane scrollDep = new JScrollPane(innerScrollDep);
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
        searchButtonArr.setPreferredSize(new Dimension(25, 25));
        searchBarArr.add(searchButtonArr);
        searchBarArr.add(searchTFArr);
        // scroll panel des arrivants
        scrollArr.setBorder(titleArr);
        scrollArr.setPreferredSize(new Dimension(100, 300));
        // panel des bouttons d'action
        paneButtonArr.setBorder(buttonPadding);
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
        searchButtonDep.setPreferredSize(new Dimension(25, 25));
        searchBarDep.add(searchButtonDep);
        searchBarDep.add(searchTFDep);
        // scroll panel des arrivants
        scrollDep.setBorder(titleDep);
        scrollDep.setPreferredSize(new Dimension(100, 300));
        // panel des bouttons d'action
        paneButtonDep.setBorder(buttonPadding);
        paneButtonDep.add(facturer);

        // Ajout des objets au panel
        paneEast.add(searchBarDep);
        paneEast.add(scrollDep);
        paneEast.add(paneButtonDep);

        this.add(BorderLayout.NORTH, paneNorth);
        this.add(BorderLayout.CENTER, paneCenter);
    }
}