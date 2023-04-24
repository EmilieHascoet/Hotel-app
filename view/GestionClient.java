package view;

import javax.swing.*;
import java.awt.*;
import controler.*;

public class GestionClient extends JPanel {
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

    public GestionClient() {
        this.setLayout(new GridLayout(1, 2));

        JPanel PGauche = new JPanel(new BorderLayout(10, 10));
        JPanel PDroit = new JPanel(new CardLayout());

        JPanel PDroit1 = new JPanel(new GridLayout(2,1));
        JPanel PDroit2 = new JPanel();
        
        //Panel de gauche
        JPanel NORTH = new JPanel(new FlowLayout());
        JPanel SOUTH = new JPanel(new FlowLayout());
        JPanel listClients = new JPanel(new GridLayout());

        search.setPreferredSize(new Dimension(200, 25));
        ajouter.setPreferredSize(new Dimension(200, 25));
        
        NORTH.add(search);
        SOUTH.add(ajouter);
        PGauche.add(BorderLayout.NORTH, NORTH);
        PGauche.add(BorderLayout.CENTER, client);
        PGauche.add(BorderLayout.SOUTH,SOUTH);
        PGauche.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));

        //Panel1 de droite

        // nom.setHorizontalAlignment(JLabel.CENTER);
        // prenom.setHorizontalAlignment(JLabel.CENTER);
        // tel.setHorizontalAlignment(JLabel.CENTER);
        // dateNaiss.setHorizontalAlignment(JLabel.CENTER);

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

        PDroit.add(ajoutClient,"Ajouter");
        PDroit.add(PDroit2,"InfoClient");

        this.add(PGauche);
        this.add(PDroit);
        this.setVisible(true);

        //Controler
        ClientControl ctr = new ClientControl(PDroit);
        ajouter.addActionListener(ctr);
    }
}
