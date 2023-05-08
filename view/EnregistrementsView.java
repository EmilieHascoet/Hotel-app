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
    CompoundBorder borderPaneNorth = BorderFactory.createCompoundBorder(bottomBorder, padding20);
    // panel du titre
    JPanel paneNorth = new JPanel();
    JLabel titre = new JLabel("Enregistrements d'aujourd'hui");
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

        // PANEL DES ARRIVEES
        paneWest.setLayout(new BoxLayout(paneWest, BoxLayout.Y_AXIS));
        // search panel
        searchButtonArr.setPreferredSize(new Dimension(25, 25));
        searchBarArr.add(searchButtonArr);
        searchBarArr.add(searchTFArr);
        // scroll panel des arrivants
        scrollArr.setBorder(titleArr);
        // panel des bouttons d'action
        paneButtonArr.add(checkIn);
        paneButtonArr.add(supprimer);

        // Ajout des objets au panel
        paneWest.add(searchBarArr);
        paneWest.add(scrollArr);
        paneWest.add(paneButtonArr);

        this.add(BorderLayout.NORTH, paneNorth);
        this.add(BorderLayout.WEST, paneWest);
        this.add(BorderLayout.EAST, paneEast);
    }
}