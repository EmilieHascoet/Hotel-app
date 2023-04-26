package view;
import controler.*;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class OptionsView extends JPanel {
    // Attributs du model
    Hotel hotel;
    // Attributs objets graphique
    // panels de gauche
    JPanel paneL = new JPanel();
    // options chambre
    JPanel paneL1 = new JPanel();
    JPanel paneL1Button = new JPanel();
    JLabel labelCh = new JLabel("Actions options de chambres");
    JButton consultCh = new JButton("Consulter");
    JButton addCh = new JButton("Ajouter");
    // options séjours
    JPanel paneL2 = new JPanel();
    JPanel paneL2Button = new JPanel();
    JLabel labelSej = new JLabel("Actions options de séjours");
    JButton consultSej = new JButton("Consulter");
    JButton addSej = new JButton("Ajouter");

    // panels de droite
    JPanel paneR = new JPanel();
    // consulter chambre
    JPanel paneConsultCh = new JPanel();
    TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Liste des options de chambre");
    JPanel paneInnerScrollCh = new JPanel();
    ButtonGroup groupCh = new ButtonGroup();
    JButton modifChInConsultCh = new JButton("Modifier");
    // (modifier chambre)
    JPanel paneModifCh = new JPanel();
    JPanel paneFormCh = new JPanel();
    TitledBorder titleModifCh = BorderFactory.createTitledBorder("Modifier l'option de chambre");
    JLabel nameChLabel = new JLabel("Nom :");
    JTextField nameChTextField = new JTextField();
    JLabel prixChLabel = new JLabel("Prix :");
    JTextField prixChTextField = new JTextField();
    JButton modifCh = new JButton("Modifier");
    // ajouter chambre
    JPanel paneAddCh = new JPanel();
    JPanel paneFormAddCh = new JPanel();
    TitledBorder titleAddCh = BorderFactory.createTitledBorder("Ajouter une option de chambre");
    JLabel nameAddChLabel = new JLabel("Nom :");
    JTextField nameAddChTextField = new JTextField();
    JLabel prixAddChLabel = new JLabel("Prix :");
    JTextField prixAddChTextField = new JTextField();
    JButton addChR = new JButton("Ajouter");
    // consulter sejours
    JPanel paneConsultSej = new JPanel();
    TitledBorder titleConsultSej = BorderFactory.createTitledBorder("Liste des options de chambre");
    JPanel paneInnerScrollSej = new JPanel();
    ButtonGroup groupSej = new ButtonGroup();
    JButton modifSejInConsultSej = new JButton("Modifier");
    // (modifier sejours)
    JPanel paneModifSej = new JPanel();
    JLabel nameSejLabel = new JLabel("Nom :");
    JTextField nameSejTextField = new JTextField();
    JLabel prixSejLabel = new JLabel("Prix :");
    JTextField prixSejTextField = new JTextField();
    JButton modifSej = new JButton("Modifier");
    // ajouter séjours
    JPanel paneAddSej = new JPanel();
    JButton addSejR = new JButton("Ajouter");

    public OptionsView(Hotel h) {
        hotel = h;
        this.setLayout(new GridLayout(1, 2));
        // PANELS DE GAUCHE
        // Ajout des bordures des panels
        paneL.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
        paneL1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));

        //Modification du format des titres
        labelCh.setHorizontalAlignment(JLabel.CENTER);
        labelCh.setForeground(Color.RED);
        Font font = labelCh.getFont();
        float newSize = font.getSize() + 6;
        labelCh.setFont(font.deriveFont(newSize));

        labelSej.setHorizontalAlignment(JLabel.CENTER);
        labelSej.setForeground(Color.RED);
        Font font2 = labelSej.getFont();
        float newSize2 = font2.getSize() + 6;
        labelSej.setFont(font2.deriveFont(newSize2));
        
        // Design des panels de gauche contenant les bouttons
        paneL1Button.setLayout(new GridLayout(1, 2, 30, 0));
        paneL1Button.setBorder(new EmptyBorder(0, 30, 30, 30));
        paneL2Button.setLayout(new GridLayout(2, 1, 0, 15));
        paneL2Button.setBorder(new EmptyBorder(0, 20, 15, 20));
        
        // Donne un nom, correspondant au panel à afficher, aux bouttons de gauche
        consultCh.setName("consultCh");
        addCh.setName("addCh");
        consultSej.setName("consultSej");
        addSej.setName("addSej");

        // Ajout du label et des boutons aux 2 panels de gauche
        paneL1.setLayout(new GridLayout(2,1));
        paneL1Button.add(consultCh);
        paneL1Button.add(addCh);
        paneL1.add(labelCh);
        paneL1.add(paneL1Button);

        paneL2.setLayout(new GridLayout(2,1));
        paneL2Button.add(consultSej);
        paneL2Button.add(addSej);
        paneL2.add(labelSej);
        paneL2.add(paneL2Button);

        // Ajout des panels au panel de gauche principal
        paneL.setLayout(new GridLayout(2, 1));
        paneL.add(paneL1);
        paneL.add(paneL2);


        // PANELS DE DROITE
        // Définir le panel de droite comme un CardLayout
        paneR.setLayout(new CardLayout());
        // Donne un nom, correspondant au panel à afficher, aux boutons de droite
        modifChInConsultCh.setName("modifCh");
        modifCh.setName("consultCh");
        addChR.setName("consultCh");
        // modifSejInConsultSej.setName("modifSej");
        //addSejR.setName("addSejR");
        // Crée les panels (cards)

        // PANEL CONSULTER OPTIONS CHAMBRE
        paneConsultCh.setLayout(new BorderLayout());
        paneConsultCh.setBorder(new EmptyBorder(15, 20, 15, 20));
        // Récupère la liste des options de chambres et les affiche dans le scrollpanel
        paneInnerScrollCh.setLayout(new BoxLayout(paneInnerScrollCh, BoxLayout.Y_AXIS));
        for (Option o : hotel.listOption) {
            JRadioButton RadioButton = new JRadioButton(o.type + ", " + o.prix + "€");
            // RadioButton.addActionListener();
            RadioButton.setActionCommand(o.type + " " + o.prix);
            groupCh.add(RadioButton);
            paneInnerScrollCh.add(RadioButton);
        }
        // Crée le scroll panel 
        JScrollPane paneScrollConsultCh = new JScrollPane(paneInnerScrollCh);
        paneScrollConsultCh.setPreferredSize(new Dimension(200, 267));
        titleConsultCh.setTitleJustification(TitledBorder.CENTER);
        paneScrollConsultCh.setBorder(titleConsultCh);
        // Ajout du scrollpanel et du bouton au panel
        paneConsultCh.add(BorderLayout.NORTH, paneScrollConsultCh);
        paneConsultCh.add(BorderLayout.SOUTH, modifChInConsultCh);
        // Le bouton n'est pas clickable si aucun radio button n'est selectionné
        //modifChInConsultCh.setEnabled(false);

        // PANEL MODIFIER OPTION CHAMBRE
        paneModifCh.setLayout(new BorderLayout());
        paneModifCh.setBorder(new EmptyBorder(115, 20, 15, 20));
        // Ajout des label et textField au panel formulaire
        paneFormCh.setLayout(new GridLayout(2, 2, 0, 10));
        paneFormCh.setBorder(titleModifCh);
        titleModifCh.setTitleJustification(TitledBorder.CENTER);

        paneFormCh.add(nameChLabel);
        paneFormCh.add(nameChTextField);
        paneFormCh.add(prixChLabel);
        paneFormCh.add(prixChTextField);
        // Ajout du formulaire et du bouton au panel modif chambre
        paneModifCh.add(BorderLayout.NORTH, paneFormCh);
        paneModifCh.add(BorderLayout.SOUTH, modifCh);

        // PANEL AJOUTER OPTION CHAMBRE
        paneAddCh.setLayout(new BorderLayout());
        paneAddCh.setBorder(new EmptyBorder(115, 20, 15, 20));
        // Ajout des label et textField au panel formulaire
        paneFormAddCh.setLayout(new GridLayout(2, 2, 0, 10));
        paneFormAddCh.setBorder(titleAddCh);
        titleAddCh.setTitleJustification(TitledBorder.CENTER);

        paneFormAddCh.add(nameAddChLabel);
        paneFormAddCh.add(nameAddChTextField);
        paneFormAddCh.add(prixAddChLabel);
        paneFormAddCh.add(prixAddChTextField);
        // Ajout du formulaire (le même que modifCh) et du bouton au panel add chambre
        paneAddCh.add(BorderLayout.NORTH, paneFormAddCh);
        paneAddCh.add(BorderLayout.SOUTH, addChR);


        // Ajoute les cards au panel de droite
        paneR.add("consultCh", paneConsultCh);
        paneR.add("modifCh", paneModifCh);
        paneR.add("addCh", paneAddCh);
        paneR.add("consultSej", paneConsultSej);
        paneR.add("modifSej", paneModifSej);
        paneR.add("addSej", paneAddSej);

        // Ajout du panel gauche et droite au panel principal
        this.add(paneL);
        this.add(paneR);

        // Creation dune instance de gestionnaire d'evenement (panel de gauche)
        OptionsControl ctrChangeCard = new OptionsControl(paneR);
        consultCh.addActionListener(ctrChangeCard);
        addCh.addActionListener(ctrChangeCard);
        consultSej.addActionListener(ctrChangeCard);
        addSej.addActionListener(ctrChangeCard);
        // Modifier options chambres
        OptionsControl ctrModifCh = new OptionsControl(hotel, paneR, groupCh, nameChTextField, prixChTextField);
        modifChInConsultCh.addActionListener(ctrModifCh);
        modifCh.addActionListener(ctrModifCh);
        // Ajouter options chambres
        OptionsControl ctrAddCh = new OptionsControl(hotel, paneR, groupCh,paneInnerScrollCh, nameAddChTextField, prixAddChTextField);
        addChR.addActionListener(ctrAddCh);
        // Modifier options chambres
        OptionsControl ctrModifSej = new OptionsControl(hotel, paneR, groupSej, nameSejTextField, prixSejTextField);
        // modifSejInConsultSej.addActionListener(ctrModifSej);

    }
}
