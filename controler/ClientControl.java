package controler;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.*;
import view.*;

public class ClientControl implements ActionListener{
    Hotel hotel;
    JPanel pane, resInfoPane, sejInfoPane, clientGroupPane, produitsPane;
    ClientControl buttonClientControl;
    ButtonGroup clientButtonGroup; 
    String actionType, name;
    JTextField firstName, lastName, tel;
    Client c;
    Reservation res;
    JLabel prixSej;
    JButton suppResButton;
    static ButtonGroup resButtonGroup;

    public ClientControl(Hotel h, JPanel p, JPanel resP, ButtonGroup clientBG, int i) {hotel=h; pane=p; resInfoPane=resP; clientButtonGroup=clientBG; actionType="SuppRes"; }

    public ClientControl(Hotel h, JPanel p, ButtonGroup clientBG) {hotel=h; pane=p; clientButtonGroup=clientBG; actionType="AddRes";}

    public ClientControl(JPanel p, ButtonGroup clientBG) { pane=p; clientButtonGroup=clientBG; actionType="AddClientLeft";}

    public ClientControl(Hotel h, JPanel p, JPanel gP, ClientControl cBG, ButtonGroup clientBG, JTextField firstN, JTextField lastN, JTextField t) { 
        hotel=h; pane=p; clientGroupPane=gP; buttonClientControl=cBG; clientButtonGroup=clientBG; firstName=firstN; lastName=lastN; tel=t; actionType="AddClientRight";
    }

    public ClientControl(Hotel h, JPanel p, JPanel produitP, Reservation r, JLabel prix) { hotel=h; pane=p; produitsPane=produitP; res=r; prixSej=prix; actionType="AddProduits";}

    public ClientControl(Hotel h, JPanel p, JPanel infoP, JPanel sejP, ButtonGroup clientBG, JButton supp) {
        hotel=h; pane=p; resInfoPane=infoP; sejInfoPane=sejP; clientButtonGroup=clientBG; suppResButton=supp; actionType="Consulter";
    }


    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        if (e.getSource() instanceof JRadioButton) {
            name = ((JRadioButton)e.getSource()).getName();   
        }
        else {
            name = ((JButton)e.getSource()).getName();
        }
        card.show(pane, name);

        if (clientButtonGroup != null && clientButtonGroup.getSelection() != null && !actionType.equals("AddClientLeft")) {
            // Trouve le Client correspondant au boutton sélectionné
            c = hotel.searchClient(clientButtonGroup.getSelection().getActionCommand());
        }
        
        if (actionType.equals("Consulter")) {
            
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
                resButtonGroup = new ButtonGroup();

                resInfoPane.removeAll();
                sejInfoPane.removeAll();

                suppResButton.setEnabled(false);
                for (Reservation res : c.listRes) {
                    String dateDebStr = formatter.format(res.dateDeb);
                    String dateFinStr = formatter.format(res.dateFin);

                    if (res.sejour == null) {
                        JRadioButton infoRes = new JRadioButton("Du " + dateDebStr + " au " + dateFinStr);
                        infoRes.setActionCommand(res.id + "");
                        infoRes.addActionListener(new RadioButtonListener(suppResButton));
                        resButtonGroup.add(infoRes);
                        resInfoPane.add(infoRes);      
                    }
                    else {
                        JLabel sejInfo = new JLabel("Du " + dateDebStr + " au " + dateFinStr);
                        JLabel sejPrix = new JLabel("Prix du séjour : " + res.sejour.prix);
                        JButton addProduits = new JButton("Ajouter des produtis");
                        JPanel south = new JPanel(new GridLayout(1,2));
                        
                        south.add(sejPrix);
                        south.add(addProduits);

                        sejInfo.setHorizontalAlignment(JLabel.CENTER);
                        sejPrix.setHorizontalAlignment(JLabel.CENTER);

                        JPanel sejProduitsInnerPane = new JPanel();
                        JScrollPane sejProduitsScrollPane = new JScrollPane(sejProduitsInnerPane);
                        
                        sejProduitsInnerPane.setLayout(new BoxLayout(sejProduitsInnerPane, BoxLayout.Y_AXIS));
                        sejProduitsScrollPane.setBorder(new TitledBorder("Produits"));

                        for (Produit p : hotel.listProd) {
                            JCheckBox tmp = new JCheckBox(p.type + ", " + p.prix + "€");
                            tmp.setActionCommand(p.type);
                            if (res.sejour.listProduit.contains(p)) {tmp.setSelected(true); tmp.setEnabled(false);}

                            sejProduitsInnerPane.add(tmp);
                        }

                        ClientControl addProdCtr = new ClientControl(hotel,pane,sejProduitsInnerPane,res,sejPrix);
                        addProduits.addActionListener(addProdCtr);

                        sejInfoPane.add(BorderLayout.NORTH, sejInfo);
                        sejInfoPane.add(BorderLayout.CENTER, sejProduitsScrollPane);
                        sejInfoPane.add(BorderLayout.SOUTH, south);  
                    }
                }
                resInfoPane.revalidate();
                resInfoPane.repaint();
                sejInfoPane.revalidate();
                sejInfoPane.repaint();    
        }

        else if (actionType.equals("AddProduits")) {
            for (Component c : produitsPane.getComponents()) {
                JCheckBox cb = (JCheckBox)c;
                if (cb.isEnabled() && cb.isSelected()) {
                    for (Produit p : hotel.listProd) {
                        if (cb.getActionCommand().equals(p.type)) {
                            res.sejour.addProduit(p);
                            cb.setEnabled(false);
                            prixSej.setText("Prix du séjour : " + res.sejour.prix);
                            break;
                        }
                    }
                } 
            }
        }

        else if (actionType.equals("AddClientLeft")) {
            clientButtonGroup.clearSelection();
        }

        else if (actionType.equals("AddClientRight")) {
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String telText = tel.getText();

            try { Integer.parseInt(telText); }
            catch (NumberFormatException nE) {
                card.show(pane, "Ajouter");
                JOptionPane.showMessageDialog(null, "Veuillez saisir un entier", "Numéro de téléphone invalide", JOptionPane.ERROR_MESSAGE);
                return;
            }

            firstName.setText("");
            lastName.setText("");
            tel.setText("");

            //Pour le model
            Client newClient = new Client(firstNameText, lastNameText, telText);
            hotel.addClient(newClient);

            //Pour l'affichage
            JRadioButton newClientRadioButton = new JRadioButton(firstNameText + " " + lastNameText);
            newClientRadioButton.setActionCommand(telText);
            newClientRadioButton.setName("Consulter");
            newClientRadioButton.addActionListener(buttonClientControl);

            clientButtonGroup.add(newClientRadioButton);
            clientGroupPane.add(newClientRadioButton);
        }   

        else if (actionType.equals("SuppRes")) {
            if (clientButtonGroup != null && clientButtonGroup.getSelection() != null && resButtonGroup.getSelection()!=null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    
                if (confirm == JOptionPane.YES_OPTION) {
                    Reservation resToSupp = c.searchRes(Integer.parseInt(resButtonGroup.getSelection().getActionCommand()));
                    hotel.suppRes(resToSupp, resToSupp.getCaution());
                    MainPage.profit.setText("Profit : " + hotel.getProfit());
                }
            }
        } 

        else if (actionType.equals("AddRes")) {
            JDialog newRes = new JDialog(view.Main.main, "Créer une reservation pour " + c.prenom + " " + c.nom,true);
            ReservationsView resPane = new ReservationsView(hotel, c, newRes);

            newRes.setSize(new Dimension(700,500));
            newRes.setResizable(false);
            newRes.setLocationRelativeTo(null);
            newRes.add(resPane);
            newRes.setVisible(true);
        }
    }
}
