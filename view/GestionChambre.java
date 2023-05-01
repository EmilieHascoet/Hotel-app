package view;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

import controler.*;
import model.*;

public class GestionChambre extends JPanel{
    //Bouttons du panel de gauche
    JPanel consultChLeftPanel = new JPanel(new GridLayout(2,1));
    JButton consultChLeftButton = new JButton("Consulter");
    JButton addChLeftButton = new JButton("Ajouter");

    //Panel Ajouter //
        // Panel et mise en forme 
    JPanel addChRightPane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    TitledBorder addChTitledBorder = BorderFactory.createTitledBorder("Ajouter une chambre");
    EmptyBorder addChMargin = new EmptyBorder(5, 0, 7, 7);
    CompoundBorder addChCompoundBorder = BorderFactory.createCompoundBorder(addChMargin, addChTitledBorder);
    TitledBorder addChOptionTitledBorder = BorderFactory.createTitledBorder("Options");
        // Contenu 
    JLabel numAddChLabel = new JLabel("Numéro de la chambre : ");
    JLabel placesAddChLabel = new JLabel("Nombre de places : ");
    JLabel prixAddChLabel = new JLabel("Prix : ");
    JTextField numAddChTextField = new JTextField();
    JTextField placesAddChTextField = new JTextField();
    JTextField prixAddChTextField = new JTextField();
    JButton addChActionButton = new JButton("Ajouter !");

    
    // consulter chambre
    JPanel paneConsultCh = new JPanel();
    TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Liste des chambre");
    JPanel paneInnerScrollCh = new JPanel();
    ButtonGroup groupCh = new ButtonGroup();
    JButton modifChInConsultCh = new JButton("Modifier");

    // (modifier chambre)
    JPanel paneModifCh = new JPanel();
    JPanel modifChInnerPane = new JPanel();
    TitledBorder titleModifCh = BorderFactory.createTitledBorder("Modifier l'option de chambre");
    JPanel optionsInnerPane = new JPanel();
    TitledBorder optionTitle = BorderFactory.createTitledBorder("Options");

    JLabel numModifChLabel = new JLabel("N° :");
    JTextField numModifChTextField = new JTextField();
    JLabel placesModifChLabel = new JLabel("Nombre de places :");
    JTextField placesModifChTextField = new JTextField();
    JLabel prixModifChLabel = new JLabel("Prix :");
    JTextField prixModifChTextField = new JTextField();
    JButton modifChActionButton = new JButton("Modifier !");

    //Pour chaton
    JPanel addOptionButtonsPane = new JPanel();

    // panels de droite
    JPanel paneR = new JPanel();

    public GestionChambre(Hotel hotel) {
        // Layout du frame
        this.setLayout(new GridLayout(1, 2, 20, 0));

                                                // **************** PANEL DE GAUCHE ***************** //

        //Buttons Consulter, Ajouter
        consultChLeftPanel.add(consultChLeftButton);
        consultChLeftPanel.add(addChLeftButton);
        this.add(consultChLeftPanel);

                                                // **************** PANEL DE DROITE ***************** //

        // Définir le panel de droite comme un CardLayout
        paneR.setLayout(new CardLayout());

        // DIFFERENTS PANELS DE DROITE
        // PANEL AJOUTER CHAMBRE
        addChTitledBorder.setTitleJustification(TitledBorder.CENTER);
        addChRightPane.setBorder(addChCompoundBorder);

        //Ajout des option dans le panel des bouttons
        addOptionButtonsPane.setLayout(new BoxLayout(addOptionButtonsPane, BoxLayout.Y_AXIS));

        for (Option o : hotel.listOption) {
            JCheckBox tmp = new JCheckBox(o.type + ", " + o.prix + "€");
            tmp.setActionCommand(o.type + ";" + o.prix);
            addOptionButtonsPane.add(tmp);
        }

        JScrollPane buttonsScrollPane = new JScrollPane(addOptionButtonsPane);
        buttonsScrollPane.setBorder(addChOptionTitledBorder);

            //Ajout des tous les éléments dans le panel
        c.insets = new Insets(5, 5, 5,5);  // Pading
        c.fill = GridBagConstraints.BOTH; // étend l'élément dans les deux directions
        c.weightx = 1.0; // agrandit horizontalement
        c.weighty = 1.0; // agrandit verticalement

        c.gridx = 0;
        c.gridy = 0;
        addChRightPane.add(numAddChLabel,c);
        c.gridy = 1;
        addChRightPane.add(placesAddChLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;       //Largeur des l'élément
        addChRightPane.add(numAddChTextField,c);
        c.gridy = 1;
        addChRightPane.add(placesAddChTextField,c);
        
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;     //Pour que ça prenne les 2 cases
        addChRightPane.add(buttonsScrollPane,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;        
        addChRightPane.add(addChActionButton,c);
           
        // PANEL CONSULTER OPTIONS CHAMBRE
        paneConsultCh.setLayout(new BorderLayout());
        paneConsultCh.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Récupère la liste des chambres et créé des RadioBouttons puis les ajoutent dans un groupe
        // Met les information de chaque chambre dans l'ActionCommand du boutton correspondant
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
        modifChInnerPane.setLayout(new GridBagLayout());
        modifChInnerPane.setBorder(titleModifCh);
        titleModifCh.setTitleJustification(TitledBorder.CENTER);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        modifChInnerPane.add(numModifChLabel,c);
        c.gridy = 1;
        modifChInnerPane.add(placesModifChLabel,c);
        c.gridy = 2;
        modifChInnerPane.add(prixModifChLabel,c);
        c.gridy = 0;
        c.gridx = 1;
        c.ipadx = 180;
        modifChInnerPane.add(numModifChTextField,c);
        c.gridy = 1;
        modifChInnerPane.add(placesModifChTextField,c);
        c.gridy = 2;
        modifChInnerPane.add(prixModifChTextField,c);

        //Création du scroll panel pour les options du panel modifier
        optionsInnerPane.setLayout(new BoxLayout(optionsInnerPane, BoxLayout.Y_AXIS));
        JScrollPane optionsScrollPane = new JScrollPane(optionsInnerPane);
        optionsScrollPane.setBorder(optionTitle);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 35;
        modifChInnerPane.add(optionsScrollPane,c);

        // Ajout du formulaire et du bouton au panel modif chambre
        paneModifCh.add(BorderLayout.NORTH, modifChInnerPane);
        paneModifCh.add(BorderLayout.SOUTH, modifChActionButton);

        // Donne un nom, correspondant au panel à afficher, aux boutons
        modifChInConsultCh.setName("modifCh");

        addChLeftButton.setName("addCh");
        consultChLeftButton.setName("consultCh");
        addChActionButton.setName("consultCh");
        modifChActionButton.setName("consultCh");
        
        // Donne le nom correspondant aux panel
        paneR.add("consultCh", paneConsultCh);
        paneR.add("modifCh", paneModifCh);
        paneR.add("addCh" ,addChRightPane);
        
        // Controller
        ChambreControl ctrModifCh = new ChambreControl(hotel, paneR, addOptionButtonsPane, optionsInnerPane, groupCh, numModifChTextField, placesModifChTextField, prixModifChTextField);
        modifChInConsultCh.addActionListener(ctrModifCh);
        modifChActionButton.addActionListener(ctrModifCh);

        ChambreControl ctrAddCh = new ChambreControl(hotel, paneR, addOptionButtonsPane, paneInnerScrollCh, groupCh, numAddChTextField, placesAddChTextField);
        consultChLeftButton.addActionListener(ctrAddCh);
        addChLeftButton.addActionListener(ctrAddCh);
        addChActionButton.addActionListener(ctrAddCh);

        this.add(paneR);
        this.setVisible(true);

    }
}
