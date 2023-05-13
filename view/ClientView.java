package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import controler.*;
import model.*;

public class ClientView extends JPanel {
    //Panel de droite
    JLabel sej = new JLabel("Sejours");
    JLabel res = new JLabel("Reservations");

    //Panel Ajouter
    TitledBorder addClientTitle = BorderFactory.createTitledBorder("Ajout de client");
    JLabel addLastNameLabel = new JLabel("Nom : ");
    JLabel addFirstNameLabel = new JLabel("Prénom : ");
    JLabel addTelLabel = new JLabel("Numéro de tel : ");
    //JLabel addDateNaissLabel = new JLabel("Date de naissance : ");
    
    JTextField addLastNameTextField = new JTextField();
    JTextField addFirstNameTextField = new JTextField();
    JTextField addTelTextField = new JTextField();
    //JTextField dateN = new JTextField();
    JButton addClientRightButton = new JButton("Ajouter !");

    // consulter chambre
    JPanel paneConsultClient = new JPanel();
    TitledBorder titleConsultClient = BorderFactory.createTitledBorder("Liste des clients");
    JPanel paneInnerScrollClient = new JPanel();
    ButtonGroup groupClient = new ButtonGroup();
    JButton addClientLeftButton = new JButton("Ajouter un client");

    public ClientView(Hotel hotel) {
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(new EmptyBorder(10, 20, 30, 30));

        JPanel PDroit = new JPanel(new CardLayout());

        JPanel PDroit1 = new JPanel(new GridLayout(2,1));

        //PANEL RESERVATIONS ET SEJOUR DU CLIENT
        JPanel consultInfoClient = new JPanel(new GridLayout(2, 1));
        JPanel resClientGlobalPane = new JPanel(new BorderLayout());

        JPanel resClientInnerPane = new JPanel();
        JPanel sejClient = new JPanel(new BorderLayout(0,15));
        TitledBorder titleResClient = BorderFactory.createTitledBorder("Réservations");
        TitledBorder titleSejClient = BorderFactory.createTitledBorder("Séjour");

        JPanel resButtonPane = new JPanel(new GridLayout(1, 3));
        JButton addResButton = new JButton("Ajouter");
        JButton suppResButton = new JButton("Supprimer");
        
        //SOUTH
        resButtonPane.add(addResButton);
        resButtonPane.add(suppResButton);
        
        //NORTH
        resClientInnerPane.setLayout(new BoxLayout(resClientInnerPane, BoxLayout.Y_AXIS));

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
        
        paneConsultClient.setLayout(new BorderLayout(0,15));
        paneConsultClient.setBorder(new EmptyBorder(0, 0, 0, 20));

        // Récupère la liste des chambres et créé des RadioBouttons puis les ajoutent dans un groupe
        // Met les information de chaque chambre dans l'ActionCommand du boutton correspondant
        paneInnerScrollClient.setLayout(new BoxLayout(paneInnerScrollClient, BoxLayout.Y_AXIS));
        //paneInnerScrollClient.add(search);

        ClientControl buttonControl = new ClientControl(hotel, PDroit, resClientInnerPane, sejClient, groupClient, suppResButton);

        for (Client c : hotel.listClient) {
            JRadioButton ClientButton = new JRadioButton(c.prenom + " "+ c.nom);
            
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
        paneConsultClient.add(BorderLayout.CENTER, paneScrollConsultClient);
        paneConsultClient.add(BorderLayout.SOUTH, addClientLeftButton);


        //Panel de droite
        JPanel addClientGlobalPanel = new JPanel(new BorderLayout(0,15));
        

        JPanel ajoutClient = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Modification du format du titre
        ajoutClient.setBorder(addClientTitle);

        c.insets = new Insets(5, 10, 5,5);  // Pading
        c.fill = GridBagConstraints.HORIZONTAL; // étend l'élément dans les deux directions
        c.weightx = 0.3; // agrandit horizontalement
        c.weighty = 1.0; 

        c.gridx = 0;
        c.gridy = 0;
        
        ajoutClient.add(addFirstNameLabel,c);

        c.gridx = 0;
        c.gridy = 1;
        ajoutClient.add(addLastNameLabel,c);
        c.gridy = 2;
        ajoutClient.add(addTelLabel,c);

        c.weightx = 1.0; 
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;       //Largeur de l'élément
        c.ipady = 20;       //Hauteur de l'élément
        ajoutClient.add(addFirstNameTextField,c);
        c.gridy = 1;
        ajoutClient.add(addLastNameTextField,c);
        c.gridy = 2;
        ajoutClient.add(addTelTextField,c);
        
        // c.gridx = 0;
        // c.gridy = 5;
        // c.gridwidth = 2;        //Pour que ça prenne les 2 cases
        // ajoutClient.add(add2,c);
    
        addClientGlobalPanel.add(BorderLayout.CENTER ,ajoutClient);
        addClientGlobalPanel.add(BorderLayout.SOUTH ,addClientRightButton);

        addClientRightButton.setEnabled(false);

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
        PDroit.add(addClientGlobalPanel,"Ajouter");

        this.add(paneConsultClient);
        this.add(PDroit);
        this.setVisible(true);

        //Controler
        addClientLeftButton.setName("Ajouter");
        addClientRightButton.setName("Consulter");

        ClientControl ctrAddLeftButton = new ClientControl(PDroit,groupClient);
        addClientLeftButton.addActionListener(ctrAddLeftButton);

        ClientControl ctrAddRightButton = new ClientControl(hotel, PDroit, paneInnerScrollClient, buttonControl, groupClient,  addFirstNameTextField, addLastNameTextField, addTelTextField);
        addClientRightButton.addActionListener(ctrAddRightButton);

        //TextField controler
        TextFieldListener ctrTextField = new TextFieldListener(addFirstNameTextField, addLastNameTextField, addTelTextField, addClientRightButton);
        addFirstNameTextField.getDocument().addDocumentListener(ctrTextField);
        addLastNameTextField.getDocument().addDocumentListener(ctrTextField);
        addTelTextField.getDocument().addDocumentListener(ctrTextField);

        //Test JDialog 
        JFrame mainFrame = Main.main;
        JDialog popUp = new JDialog(mainFrame, true);
        JPanel confirm = new JPanel(new BorderLayout());
        JPanel yesNo = new JPanel(new GridLayout(1,2));
        JLabel sure = new JLabel("Êtes-vous sûr de vouloir supprimer cette réservation ?");
        JButton yes = new JButton("OUI");
        JButton no = new JButton("NON");

        sure.setHorizontalAlignment(JLabel.CENTER);
        yesNo.add(yes);
        yesNo.add(no);
        confirm.add(sure, BorderLayout.CENTER);
        confirm.add(yesNo, BorderLayout.SOUTH);

        popUp.add(confirm);
        popUp.setSize(400, 200);
        //test2.setVisible(true);

        ClientControl suppCtr = new ClientControl(hotel,PDroit,resClientInnerPane,groupClient,5);
        suppResButton.addActionListener(suppCtr);
        suppResButton.setEnabled(false);
        

        ClientControl addResCtr = new ClientControl(hotel,PDroit,groupClient);
        addResButton.addActionListener(addResCtr);
    }
}
