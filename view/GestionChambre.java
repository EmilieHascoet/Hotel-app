package view;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.image.TileObserver;

import controler.*;
import model.*;

public class GestionChambre extends JPanel{
    JButton consult = new JButton("Consulter");
    JButton modif = new JButton("Modifier");
    JButton add = new JButton("Ajouter");

    //Panel Ajouter
    JLabel numero = new JLabel("Numéro de la chambre : ");
    JLabel nbrPlace = new JLabel("Nombre de places : ");
    JLabel prix = new JLabel("Prix : ");
    JTextField numer = new JTextField();
    JTextField nbrP = new JTextField();
    JTextField pri = new JTextField();
    JButton add2 = new JButton("Ajouter !");

    //Panel Modifier
    JLabel num = new JLabel("Numéro de la chambre à modifier : ");
    JTextField nu = new JTextField();

    // consulter chambre
    JPanel paneConsultCh = new JPanel();
    TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Liste des chambre");
    JPanel paneInnerScrollCh = new JPanel();
    ButtonGroup groupCh = new ButtonGroup();
    JButton modifChInConsultCh = new JButton("Modifier");

    // (modifier chambre)
    JPanel paneModifCh = new JPanel();
    JPanel paneFormCh = new JPanel();
    TitledBorder titleModifCh = BorderFactory.createTitledBorder("Modifier l'option de chambre");
    JLabel numChLabel = new JLabel("N° :");
    JTextField numChTextField = new JTextField();
    JLabel placesChLabel = new JLabel("Nombre de places :");
    JTextField placesChTextField = new JTextField();
    JLabel prixChLabel = new JLabel("Prix :");
    JTextField prixChTextField = new JTextField();
    JButton modifCh = new JButton("Modifier !");

    //Pour chaton
    JPanel buttonsPane = new JPanel();

    // panels de droite
    JPanel paneR = new JPanel();

    public GestionChambre(Hotel hotel) {
        this.setLayout(new GridLayout(1, 2, 20, 0));

        //Buttons Consulter, Modifier, Ajouter
        JPanel p1 = new JPanel(new GridLayout(2,1));

        p1.add(consult);
        p1.add(add);
        this.add(p1);

        //Fenetre qui s'ouvre quand on clique sur Ajouter (faire la meme chose pour les deux autres)

        JPanel ajout = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        TitledBorder title = BorderFactory.createTitledBorder("Ajouter une chambre");
        EmptyBorder pading = new EmptyBorder(5, 0, 7, 7);

        title.setTitleJustification(TitledBorder.CENTER);
        CompoundBorder fusion = BorderFactory.createCompoundBorder(pading, title);
        ajout.setBorder(fusion);

        //////////

        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));

        for (Option o : hotel.listOption) {
            JCheckBox tmp = new JCheckBox(o.type + ", " + o.prix + "€");
            tmp.setActionCommand(o.type + ";" + o.prix);
            buttonsPane.add(tmp);
        }

        JScrollPane buttonsScrollPane = new JScrollPane(buttonsPane);
        //////////

        c.insets = new Insets(5, 5, 5,5);  // Pading
        c.fill = GridBagConstraints.BOTH; // étend l'élément dans les deux directions
        c.weightx = 1.0; // agrandit horizontalement
        c.weighty = 1.0; // agrandit verticalement

        c.gridx = 0;
        c.gridy = 0;
        ajout.add(numero,c);
        c.gridy = 1;
        ajout.add(nbrPlace,c);
        // c.gridy = 2;
        // ajout.add(prix,c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;       //Largeur des l'élément
        ajout.add(numer,c);
        c.gridy = 1;
        ajout.add(nbrP,c);
        // c.gridy = 2;
        // ajout.add(pri,c);
        
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        //c.ipadx = 200;
        ajout.add(buttonsScrollPane,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;        //Pour que ça prenne les 2 cases
        ajout.add(add2,c);
           
        // PANELS DE DROITE
        // Définir le panel de droite comme un CardLayout
        paneR.setLayout(new CardLayout());
        // Donne un nom, correspondant au panel à afficher, aux boutons de droite
        modifChInConsultCh.setName("modifCh");

        // PANEL CONSULTER OPTIONS CHAMBRE
        paneConsultCh.setLayout(new BorderLayout());
        paneConsultCh.setBorder(new EmptyBorder(15, 20, 15, 20));
        // Récupère la liste des options de chambres et les affiche dans le scrollpanel
        paneInnerScrollCh.setLayout(new BoxLayout(paneInnerScrollCh, BoxLayout.Y_AXIS));
        for (Chambre ch : hotel.listChambre) {
            JRadioButton RadioButton = new JRadioButton("Chambre n° : " + ch.num);
            RadioButton.setActionCommand(ch.num + ";" + ch.nbrPlaces + ";" + ch.prix);

            String actionCommand;
            for (Option o : ch.listOption) {
                actionCommand = RadioButton.getActionCommand();
                RadioButton.setActionCommand(actionCommand + ";" + o.type + ";" + o.prix);
            }
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

        // PANEL MODIFIER OPTION CHAMBRE
        paneModifCh.setLayout(new BorderLayout());
        paneModifCh.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Ajout des label et textFiel au panel formulaire
        paneFormCh.setLayout(new GridBagLayout());
        paneFormCh.setBorder(titleModifCh);
        titleModifCh.setTitleJustification(TitledBorder.CENTER);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;

        paneFormCh.add(numChLabel,c);
        c.gridy = 1;
        paneFormCh.add(placesChLabel,c);
        c.gridy = 2;
        paneFormCh.add(prixChLabel,c);
        c.gridy = 0;
        c.gridx = 1;
        c.ipadx = 100;
        paneFormCh.add(numChTextField,c);
        c.gridy = 1;
        paneFormCh.add(placesChTextField,c);
        c.gridy = 2;
        paneFormCh.add(prixChTextField,c);

        JPanel optionsInnerPane = new JPanel();
        TitledBorder optionTitle = BorderFactory.createTitledBorder("Options");
        optionsInnerPane.setLayout(new BoxLayout(optionsInnerPane, BoxLayout.Y_AXIS));

        JScrollPane optionsScrollPane = new JScrollPane(optionsInnerPane);
        optionsScrollPane.setBorder(optionTitle);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 50;
        paneFormCh.add(optionsScrollPane,c);

        // Ajout du formulaire et du bouton au panel modif chambre
        paneModifCh.add(BorderLayout.NORTH, paneFormCh);
        paneModifCh.add(BorderLayout.SOUTH, modifCh);

        add.setName("Ajouter");
        consult.setName("consultCh");
        add2.setName("consultCh");
        modifCh.setName("consultCh");
        
        paneR.add("consultCh", paneConsultCh);
        paneR.add("modifCh", paneModifCh);
        paneR.add("Ajouter" ,ajout);
        
        ChambreControl changePane = new ChambreControl(paneR);

        ChambreControl ctrModifCh = new ChambreControl(hotel, paneR, buttonsPane, optionsInnerPane, groupCh, numChTextField, placesChTextField, prixChTextField, 5);
        modifChInConsultCh.addActionListener(ctrModifCh);
        modifCh.addActionListener(ctrModifCh);

        ChambreControl ctrAddCh = new ChambreControl(hotel, paneR, buttonsPane, paneInnerScrollCh, groupCh, numer, nbrP, prixChTextField);
        consult.addActionListener(ctrAddCh);
        add.addActionListener(ctrAddCh);
        add2.addActionListener(ctrAddCh);

        this.add(paneR);
        this.setVisible(true);

    }
}
