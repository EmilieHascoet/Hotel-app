package view;
import controler.*;
import model.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
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
    // padding de 10 pixel
    EmptyBorder padding = new EmptyBorder(20, 20, 20, 20);
    // consulter chambre
    JPanel paneConsultCh = new JPanel();
    TitledBorder titleConsultCh = BorderFactory.createTitledBorder("Liste des options de chambre");
    CompoundBorder borderConsultCh = BorderFactory.createCompoundBorder(padding, titleConsultCh);
    JPanel paneInnerScrollCh = new JPanel();
    ButtonGroup groupCh = new ButtonGroup();
    JButton modifChInConsultCh = new JButton("Modifier");
    // (modifier chambre)
    JPanel paneModifCh = new JPanel();
    JPanel paneFormCh = new JPanel();
    TitledBorder titleModifCh = BorderFactory.createTitledBorder("Modifier l'option de chambre");
    CompoundBorder borderModifCh = BorderFactory.createCompoundBorder(titleModifCh, padding);
    JLabel nameChLabel = new JLabel("Nom :");
    JTextField nameChTextField = new JTextField();
    JLabel prixChLabel = new JLabel("Prix :");
    JTextField prixChTextField = new JTextField();
    JButton modifCh = new JButton("Modifier");
    // ajouter chambre
    JPanel paneAddCh = new JPanel();
    JPanel paneFormAddCh = new JPanel();
    TitledBorder titleAddCh = BorderFactory.createTitledBorder("Ajouter une option de chambre");
    CompoundBorder borderAddCh = BorderFactory.createCompoundBorder(titleAddCh, padding);
    JLabel nameAddChLabel = new JLabel("Nom :");
    JTextField nameAddChTextField = new JTextField();
    JLabel prixAddChLabel = new JLabel("Prix :");
    JTextField prixAddChTextField = new JTextField();
    JButton addChR = new JButton("Ajouter");

    // consulter sejours
    JPanel paneConsultSej = new JPanel();
    TitledBorder titleConsultSej = BorderFactory.createTitledBorder("Liste des options de chambre");
    CompoundBorder borderConsultSej = BorderFactory.createCompoundBorder(padding, titleConsultSej);
    JPanel paneInnerScrollSej = new JPanel();
    ButtonGroup groupSej = new ButtonGroup();
    JButton modifSejInConsultSej = new JButton("Modifier");
    // (modifier sejours)
    JPanel paneModifSej = new JPanel();
    JPanel paneFormSej = new JPanel();
    TitledBorder titleModifSej = BorderFactory.createTitledBorder("Modifier l'option de sejour");
    CompoundBorder borderModifSej = BorderFactory.createCompoundBorder(titleModifSej, padding);
    JLabel nameSejLabel = new JLabel("Nom :");
    JTextField nameSejTextField = new JTextField();
    JLabel prixSejLabel = new JLabel("Prix :");
    JTextField prixSejTextField = new JTextField();
    JButton modifSej = new JButton("Modifier");
    // ajouter séjours
    JPanel paneAddSej = new JPanel();
    JPanel paneFormAddSej = new JPanel();
    TitledBorder titleAddSej = BorderFactory.createTitledBorder("Ajouter une option de sejour");
    CompoundBorder borderAddSej = BorderFactory.createCompoundBorder(titleAddSej, padding);
    JLabel nameAddSejLabel = new JLabel("Nom :");
    JTextField nameAddSejTextField = new JTextField();
    JLabel prixAddSejLabel = new JLabel("Prix :");
    JTextField prixAddSejTextField = new JTextField();
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
        float newSize = font.getSize() + 5;
        labelCh.setFont(font.deriveFont(newSize));

        labelSej.setHorizontalAlignment(JLabel.CENTER);
        labelSej.setForeground(Color.RED);
        Font font2 = labelSej.getFont();
        float newSize2 = font2.getSize() + 5;
        labelSej.setFont(font2.deriveFont(newSize2));
        
        // Design des panels de gauche contenant les bouttons
        paneL1Button.setLayout(new GridLayout(1, 2, 30, 0));
        paneL1Button.setBorder(new EmptyBorder(0, 30, 30, 30));
        paneL2Button.setLayout(new GridLayout(1, 2, 30, 0));
        paneL2Button.setBorder(new EmptyBorder(0, 30, 30, 30));
        
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
        modifSejInConsultSej.setName("modifSej");
        modifSej.setName("consultSej");
        addSejR.setName("consultSej");
        // met les boutons à false
        modifCh.setEnabled(false);
        addChR.setEnabled(false);
        modifSej.setEnabled(false);
        addSejR.setEnabled(false);

        // Crée les panels (cards)

        // PANEL CONSULTER OPTIONS CHAMBRE
        paneConsultCh.setLayout(new BorderLayout());
        titleConsultCh.setTitleJustification(TitledBorder.CENTER);
        paneConsultCh.setBorder(borderConsultCh);
        // Récupère la liste des options de chambres et les affiche dans le scrollpanel
        paneInnerScrollCh.setLayout(new BoxLayout(paneInnerScrollCh, BoxLayout.Y_AXIS));
        for (Option o : hotel.listOption) {
            JRadioButton RadioButton = new JRadioButton(o.type + ", " + o.prix + "€");
            // instance d'evenement pour rendre le button clickable
            RadioButton.addActionListener(new RadioButtonListener(modifChInConsultCh));
            RadioButton.setActionCommand(o.type + " " + o.prix);
            groupCh.add(RadioButton);
            paneInnerScrollCh.add(RadioButton);
        }
        // Crée le scroll panel 
        JScrollPane paneScrollConsultCh = new JScrollPane(paneInnerScrollCh);
        paneScrollConsultCh.getVerticalScrollBar().setUnitIncrement(10);
        paneScrollConsultCh.setBorder(null);
        // Ajout du scrollpanel et du bouton au panel
        paneConsultCh.add(BorderLayout.CENTER, paneScrollConsultCh);
        paneConsultCh.add(BorderLayout.SOUTH, modifChInConsultCh);
        // Le bouton n'est pas clickable si aucun radio button n'est selectionné
        modifChInConsultCh.setEnabled(false);

        // PANEL MODIFIER OPTION CHAMBRE
        paneModifCh.setLayout(new BorderLayout());
        paneModifCh.setBorder(new EmptyBorder(115, 20, 20, 20));
        // Ajout des label et textField au panel formulaire
        paneFormCh.setLayout(new GridLayout(2, 2, 0, 10));
        titleModifCh.setTitleJustification(TitledBorder.CENTER);
        paneFormCh.setBorder(borderModifCh);

        paneFormCh.add(nameChLabel);
        paneFormCh.add(nameChTextField);
        paneFormCh.add(prixChLabel);
        paneFormCh.add(prixChTextField);
        // Ajout du formulaire et du bouton au panel modif chambre
        paneModifCh.add(BorderLayout.NORTH, paneFormCh);
        paneModifCh.add(BorderLayout.SOUTH, modifCh);

        // PANEL AJOUTER OPTION CHAMBRE
        paneAddCh.setLayout(new BorderLayout());
        paneAddCh.setBorder(new EmptyBorder(115, 20, 20, 20));
        // Ajout des label et textField au panel formulaire
        paneFormAddCh.setLayout(new GridLayout(2, 2, 0, 10));
        titleAddCh.setTitleJustification(TitledBorder.CENTER);
        paneFormAddCh.setBorder(borderAddCh);

        paneFormAddCh.add(nameAddChLabel);
        paneFormAddCh.add(nameAddChTextField);
        paneFormAddCh.add(prixAddChLabel);
        paneFormAddCh.add(prixAddChTextField);
        // Ajout du formulaire (le même que modifCh) et du bouton au panel add chambre
        paneAddCh.add(BorderLayout.NORTH, paneFormAddCh);
        paneAddCh.add(BorderLayout.SOUTH, addChR);


        // PANEL CONSULTER OPTIONS SEJOURS
        paneConsultSej.setLayout(new BorderLayout());
        titleConsultSej.setTitleJustification(TitledBorder.CENTER);
        paneConsultSej.setBorder(borderConsultSej);
        // Récupère la liste des options de sejour et les affiche dans le scrollpanel
        paneInnerScrollSej.setLayout(new BoxLayout(paneInnerScrollSej, BoxLayout.Y_AXIS));
        for (Produit p : hotel.listProd) {
            JRadioButton RadioButton = new JRadioButton(p.type + ", " + p.prix + "€");
            RadioButton.addActionListener(new RadioButtonListener(modifSejInConsultSej));
            RadioButton.setActionCommand(p.type + " " + p.prix);
            groupSej.add(RadioButton);
            paneInnerScrollSej.add(RadioButton);
        }
        // Crée le scroll panel 
        JScrollPane paneScrollConsultSej = new JScrollPane(paneInnerScrollSej);
        paneScrollConsultSej.getVerticalScrollBar().setUnitIncrement(10);
        paneScrollConsultSej.setBorder(null);
        // Ajout du scrollpanel et du bouton au panel
        paneConsultSej.add(BorderLayout.CENTER, paneScrollConsultSej);
        paneConsultSej.add(BorderLayout.SOUTH, modifSejInConsultSej);
        // Le bouton n'est pas clickable si aucun radio button n'est selectionné
        modifSejInConsultSej.setEnabled(false);

        // PANEL MODIFIER OPTION SEJOUR
        paneModifSej.setLayout(new BorderLayout());
        paneModifSej.setBorder(new EmptyBorder(115, 20, 20, 20));
        // Ajout des label et textField au panel formulaire
        paneFormSej.setLayout(new GridLayout(2, 2, 0, 10));
        titleModifSej.setTitleJustification(TitledBorder.CENTER);
        paneFormSej.setBorder(borderModifSej);

        paneFormSej.add(nameSejLabel);
        paneFormSej.add(nameSejTextField);
        paneFormSej.add(prixSejLabel);
        paneFormSej.add(prixSejTextField);
        // Ajout du formulaire et du bouton au panel modif sejour
        paneModifSej.add(BorderLayout.NORTH, paneFormSej);
        paneModifSej.add(BorderLayout.SOUTH, modifSej);

        // PANEL AJOUTER OPTION SEJOUR
        paneAddSej.setLayout(new BorderLayout());
        paneAddSej.setBorder(new EmptyBorder(115, 20, 20, 20));
        // Ajout des label et textField au panel formulaire
        paneFormAddSej.setLayout(new GridLayout(2, 2, 0, 10));
        titleAddSej.setTitleJustification(TitledBorder.CENTER);
        paneFormAddSej.setBorder(borderAddSej);

        paneFormAddSej.add(nameAddSejLabel);
        paneFormAddSej.add(nameAddSejTextField);
        paneFormAddSej.add(prixAddSejLabel);
        paneFormAddSej.add(prixAddSejTextField);
        // Ajout du formulaire (le même que modifSej) et du bouton au panel add sejour
        paneAddSej.add(BorderLayout.NORTH, paneFormAddSej);
        paneAddSej.add(BorderLayout.SOUTH, addSejR);


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

        // Creation dune instance de gestionnaire d'evenement
        // Seulement modifier la card
        OptionsControl ctrChangeCard = new OptionsControl(paneR);
        consultCh.addActionListener(ctrChangeCard);
        addCh.addActionListener(ctrChangeCard);
        consultSej.addActionListener(ctrChangeCard);
        addSej.addActionListener(ctrChangeCard);

        // Modifier options chambres
        OptionsControl ctrModifCh = new OptionsControl(hotel, "Ch", paneR, groupCh, nameChTextField, prixChTextField);
        modifChInConsultCh.addActionListener(ctrModifCh);
        modifCh.addActionListener(ctrModifCh);
        // Ajouter options chambres
        OptionsControl ctrAddCh = new OptionsControl(hotel, "Ch", paneR, groupCh, paneInnerScrollCh, 
        nameAddChTextField, prixAddChTextField, modifChInConsultCh);
        addChR.addActionListener(ctrAddCh);

        // Modifier options sejours
        OptionsControl ctrModifSej = new OptionsControl(hotel, "Sej", paneR, groupSej, nameSejTextField, prixSejTextField);
        modifSejInConsultSej.addActionListener(ctrModifSej);
        modifSej.addActionListener(ctrModifSej);
        // Ajouter options sejours
        OptionsControl ctrAddSej = new OptionsControl(hotel, "Sej", paneR, groupSej, paneInnerScrollSej, 
        nameAddSejTextField, prixAddSejTextField, modifSejInConsultSej);
        addSejR.addActionListener(ctrAddSej);

        //TextField controler
        TextFieldListener ctrTextField1 = new TextFieldListener(nameChTextField, prixChTextField, modifCh);
        nameChTextField.getDocument().addDocumentListener(ctrTextField1);
        prixChTextField.getDocument().addDocumentListener(ctrTextField1);

        TextFieldListener ctrTextField2 = new TextFieldListener(nameAddChTextField, prixAddChTextField, addChR);
        nameAddChTextField.getDocument().addDocumentListener(ctrTextField2);
        prixAddChTextField.getDocument().addDocumentListener(ctrTextField2);

        TextFieldListener ctrTextField3 = new TextFieldListener(nameSejTextField, prixSejTextField, modifSej);
        nameSejTextField.getDocument().addDocumentListener(ctrTextField3);
        prixSejTextField.getDocument().addDocumentListener(ctrTextField3);

        TextFieldListener ctrTextField4 = new TextFieldListener(nameAddSejTextField, prixAddSejTextField, addSejR);
        nameAddSejTextField.getDocument().addDocumentListener(ctrTextField4);
        prixAddSejTextField.getDocument().addDocumentListener(ctrTextField4);

    }
}
