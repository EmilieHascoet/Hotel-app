package controler;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import model.*;
import view.*;

public class ClientControl implements ActionListener{
    Hotel hotel;
    JPanel pane, infosPane, clientGroupPane;
    ClientControl buttonClientControl;
    ButtonGroup clientButtonGroup; 
    String actionType, name;
    JTextField firstName, lastName, tel;
    Client c;
    static ButtonGroup resButtonGroup;

    public ClientControl(Hotel h, JPanel p, JPanel resP, ButtonGroup clientBG, int i) {hotel=h; pane=p; infosPane=resP; clientButtonGroup=clientBG; actionType="SuppRes"; }

    public ClientControl(Hotel h, JPanel p, ButtonGroup clientBG) {hotel=h; pane=p; clientButtonGroup=clientBG; actionType="AddRes";}

    public ClientControl(JPanel p, ButtonGroup clientBG) { pane=p; clientButtonGroup=clientBG; actionType="AddClientLeft";}

    public ClientControl(Hotel h, JPanel p, JPanel gP, ClientControl cBG, ButtonGroup clientBG, JTextField firstN, JTextField lastN, JTextField t) { 
        hotel=h; pane=p; clientGroupPane=gP; buttonClientControl=cBG; clientButtonGroup=clientBG; firstName=firstN; lastName=lastN; tel=t; actionType="AddClientRight";
    }

    public ClientControl(Hotel h, JPanel p, JPanel infoP, ButtonGroup clientBG) {hotel=h; pane=p; infosPane=infoP; clientButtonGroup=clientBG; actionType="Consulter";}


    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        if (e.getSource() instanceof JRadioButton) {
            name = ((JRadioButton)e.getSource()).getName();   
        }
        else {
            name = ((JButton)e.getSource()).getName();
        }
        card.show(pane, name);

        if (clientButtonGroup != null && clientButtonGroup.getSelection() != null) {
            // Trouve le Client correspondant au boutton sélectionné
            c = hotel.searchClient(clientButtonGroup.getSelection().getActionCommand());
        }
        
        if (actionType.equals("Consulter")) {
            
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
                resButtonGroup = new ButtonGroup();

                infosPane.removeAll();
                for (Reservation res : c.listRes) {
                    String dateDebStr = formatter.format(res.dateDeb);
                    String dateFinStr = formatter.format(res.dateFin);
                    JRadioButton infoRes = new JRadioButton("Du " + dateDebStr + " au " + dateFinStr);
                    infoRes.setActionCommand(res.id + "");
                    resButtonGroup.add(infoRes);
                    infosPane.add(infoRes);            
                }
                infosPane.revalidate();
                infosPane.repaint();
        }

        else if (actionType.equals("AddClientLeft")) {
            clientButtonGroup.clearSelection();
        }

        else if (actionType.equals("AddClientRight")) {
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String telText = tel.getText();

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
            if (clientButtonGroup != null && clientButtonGroup.getSelection() != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    
                if (confirm == JOptionPane.YES_OPTION) {
                    Reservation resToSupp = c.searchRes(Integer.parseInt(resButtonGroup.getSelection().getActionCommand()));
                    c.suppRes(resToSupp);
                }
            }
        } 

        else if (actionType.equals("AddRes")) {
            JDialog newRes = new JDialog(view.Main.main, "Créer une reservation pour " + c.prenom + " " + c.nom,true);
            ReservationsView resPane = new ReservationsView(hotel, c, newRes);

            newRes.setSize(new Dimension(700,500));
            newRes.setLocationRelativeTo(null);
            newRes.add(resPane);
            newRes.setVisible(true);
        }
    }
}
