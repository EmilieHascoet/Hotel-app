package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import controler.*;
import model.*;

public class ClientView extends JPanel {
    //Panel de gauche
    JTextField search = new JTextField();
    JLabel client = new JLabel(" client1....");
    JButton ajouter = new JButton("Ajouter");

    //Panel de droite
    JLabel sej = new JLabel("Sejours");
    JLabel res = new JLabel("Reservations");

    //Panel Ajouter
    JLabel titre = new JLabel("Ajout de clients");
    JLabel nom = new JLabel("Nom : ");
    JLabel prenom = new JLabel("Prénom : ");
    JLabel tel = new JLabel("Numéro de tel : ");
    JLabel dateNaiss = new JLabel("Date de naissance : ");
    
    JTextField no = new JTextField();
    JTextField preno = new JTextField();
    JTextField te = new JTextField();
    JTextField dateN = new JTextField();
    JButton add2 = new JButton("Ajouter !");

    // consulter chambre
    JPanel paneConsultClient = new JPanel();
    TitledBorder titleConsultClient = BorderFactory.createTitledBorder("Liste des clients");
    JPanel paneInnerScrollClient = new JPanel();
    ButtonGroup groupClient = new ButtonGroup();
    JButton addButtonClient = new JButton("Ajouter");

    public ClientView(Hotel hotel) {
        this.setLayout(new GridLayout(1, 2));

        JPanel PGauche = new JPanel(new BorderLayout(10, 10));
        JPanel PDroit = new JPanel(new CardLayout());

        JPanel PDroit1 = new JPanel(new GridLayout(2,1));
        JPanel PDroit2 = new JPanel();
        
        //Panel de gauche
        /* 
        JPanel NORTH = new JPanel(new FlowLayout());
        JPanel SOUTH = new JPanel(new FlowLayout());
        JPanel listClients = new JPanel(new GridLayout());

        search.setPreferredSize(new Dimension(200, 25));
        ajouter.setPreferredSize(new Dimension(200, 25));
        
        NORTH.add(search);
        SOUTH.add(ajouter);
        PGauche.add(BorderLayout.NORTH, NORTH);
        //PGauche.add(BorderLayout.CENTER, client);
        PGauche.add(BorderLayout.SOUTH,SOUTH);
        PGauche.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
        */

        //PANEL RESERVATIONS ET SEJOUR DU CLIENT
        JPanel consultInfoClient = new JPanel(new GridLayout(2, 1));
        JPanel resClientGlobalPane = new JPanel(new BorderLayout());

        JPanel resClientInnerPane = new JPanel();
        JPanel sejClient = new JPanel();
        TitledBorder titleResClient = BorderFactory.createTitledBorder("Réservations");
        TitledBorder titleSejClient = BorderFactory.createTitledBorder("Séjour");

        JPanel resButtonPane = new JPanel(new GridLayout(1, 3));
        JButton addResButton = new JButton("Ajouter");
        JButton modifResButton = new JButton("Modifier");
        JButton suppResButton = new JButton("Supprimer");
        
        //SOUTH
        modifResButton.setEnabled(false);    //Quand je sélectionne une réservation passe en true
        resButtonPane.add(addResButton);
        resButtonPane.add(modifResButton);
        resButtonPane.add(suppResButton);
        
        //NORTH
        resClientInnerPane.setLayout(new BoxLayout(resClientInnerPane, BoxLayout.Y_AXIS));
        sejClient.setLayout(new BoxLayout(sejClient, BoxLayout.Y_AXIS));

        sejClient.setBorder(titleSejClient);

        //Scroll pane
        JScrollPane resClientScrollPane = new JScrollPane(resClientInnerPane);
        resClientScrollPane.setBorder(new EmptyBorder(5,0,0,0));

        //Ajout dans les panels
        resClientGlobalPane.setBorder(titleResClient);
        resClientGlobalPane.add(BorderLayout.CENTER, resClientScrollPane);
        resClientGlobalPane.add(BorderLayout.SOUTH, resButtonPane);
        consultInfoClient.add(resClientGlobalPane);
        consultInfoClient.add(sejClient);

        // PANEL CONSULTER CLIENT
        
        paneConsultClient.setLayout(new BorderLayout());
        paneConsultClient.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Récupère la liste des chambres et créé des RadioBouttons puis les ajoutent dans un groupe
        // Met les information de chaque chambre dans l'ActionCommand du boutton correspondant
        paneInnerScrollClient.setLayout(new BoxLayout(paneInnerScrollClient, BoxLayout.Y_AXIS));
        //paneInnerScrollClient.add(search);

        ClientControl buttonControl = new ClientControl(hotel, PDroit, resClientInnerPane, groupClient);

        for (Client c : hotel.listClient) {
            JRadioButton ClientButton = new JRadioButton(c.nom);
            
            ClientButton.setActionCommand(c.tel);
            ClientButton.setName("Consulter");
            ClientButton.addActionListener(buttonControl);

            groupClient.add(ClientButton);
            paneInnerScrollClient.add(ClientButton);
        }

        // Crée le scroll panel 
        JScrollPane paneScrollConsultClient = new JScrollPane(paneInnerScrollClient);
        paneScrollConsultClient.setPreferredSize(new Dimension(200, 267));
        titleConsultClient.setTitleJustification(TitledBorder.CENTER);
        paneScrollConsultClient.setBorder(titleConsultClient);

        // Ajout du scrollpanel et du bouton au panel
        paneConsultClient.add(BorderLayout.NORTH, paneScrollConsultClient);
        paneConsultClient.add(BorderLayout.SOUTH, addButtonClient);


        //Panel1 de droite

        JPanel ajoutClient = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Modification du format du titre
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setForeground(Color.RED);
        Font font = titre.getFont();
        float newSize = font.getSize() + 6;
        titre.setFont(font.deriveFont(newSize));

        c.insets = new Insets(5, 10, 5,5);  // Pading
        c.fill = GridBagConstraints.HORIZONTAL; // étend l'élément dans les deux directions
        c.weightx = 0.3; // agrandit horizontalement
        c.weighty = 1.0; 

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        
        ajoutClient.add(titre,c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        ajoutClient.add(nom,c);
        c.gridy = 2;
        ajoutClient.add(prenom,c);
        c.gridy = 3;
        ajoutClient.add(tel,c);
        c.gridy = 4;
        ajoutClient.add(dateNaiss,c);

        c.weightx = 1.0; 
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 100;       //Largeur de l'élément
        c.ipady = 20;       //Hauteur de l'élément
        ajoutClient.add(no,c);
        c.gridy = 2;
        ajoutClient.add(preno,c);
        c.gridy = 3;
        ajoutClient.add(te,c);
        c.gridy = 4;
        ajoutClient.add(dateN,c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;        //Pour que ça prenne les 2 cases
        ajoutClient.add(add2,c);
    
        //Panel2 de droite
        JPanel sejour = new JPanel(new BorderLayout());
        JPanel reservation = new JPanel(new BorderLayout());

        sej.setHorizontalAlignment(JLabel.CENTER);
        res.setHorizontalAlignment(JLabel.CENTER);

        sejour.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        sejour.add(sej,BorderLayout.NORTH);
        reservation.add(res,BorderLayout.NORTH);

        PDroit1.add(sejour);
        PDroit1.add(reservation);

        //Panel de droite

        PDroit.add(consultInfoClient, "Consulter");
        PDroit.add(ajoutClient,"Ajouter");

        this.add(paneConsultClient);
        this.add(PDroit);
        this.setVisible(true);

        //Controler
        addButtonClient.setName("Ajouter");

        //ClientControl ctr = new ClientControl(PDroit);
        //addButtonClient.addActionListener(ctr);
    }
}
