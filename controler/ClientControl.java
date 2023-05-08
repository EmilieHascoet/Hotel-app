package controler;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import model.*;

public class ClientControl implements ActionListener{
    Hotel hotel;
    JPanel pane, infosPane, groupPane;
    ClientControl buttonClientControl;
    ButtonGroup clientButtonGroup;
    String actionType, name;
    JTextField firstName, lastName, tel;

    public ClientControl(JPanel p, ButtonGroup clientBG) { pane=p; clientButtonGroup=clientBG; actionType="AddClientLeft";}

    public ClientControl(Hotel h, JPanel p, JPanel gP, ClientControl cBG, ButtonGroup clientBG, JTextField firstN, JTextField lastN, JTextField t) { 
        hotel=h; pane=p; groupPane=gP; buttonClientControl=cBG; clientButtonGroup=clientBG; firstName=firstN; lastName=lastN; tel=t; actionType="AddClientRight";
    }

    public ClientControl(Hotel h, JPanel p, JPanel infoP, ButtonGroup clientBG) {hotel=h; pane=p; infosPane=infoP; clientButtonGroup=clientBG; actionType="Consulter";}

    public ClientControl(Hotel h, JPanel p, ButtonGroup clientBG) {hotel=h; pane=p; clientButtonGroup=clientBG; actionType="Ajouter";}

    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        if (e.getSource() instanceof JRadioButton) {
            name = ((JRadioButton)e.getSource()).getName();   
        }
        else {
            name = ((JButton)e.getSource()).getName();
        }
        card.show(pane, name);

        
            if (actionType.equals("Consulter")) {
                if (clientButtonGroup != null && clientButtonGroup.getSelection() != null) {
                    // Trouve le Client correspondant au boutton sélectionné
                    Client c = hotel.searchClient(clientButtonGroup.getSelection().getActionCommand());
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
                    ButtonGroup resButtonGroup = new ButtonGroup();

                    infosPane.removeAll();
                    for (Reservation res : c.listRes) {
                        String dateDebStr = formatter.format(res.dateDeb);
                        String dateFinStr = formatter.format(res.dateFin);
                        JRadioButton infoRes = new JRadioButton("Du " + dateDebStr + " au " + dateFinStr);
                        resButtonGroup.add(infoRes);
                        infosPane.add(infoRes);              
                    }
                    infosPane.revalidate();
                    infosPane.repaint();
                }
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
                groupPane.add(newClientRadioButton);
                
            }   
    }
}
