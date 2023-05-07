package controler;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import model.*;

public class ClientControl implements ActionListener{
    Hotel hotel;
    JPanel pane, infosPane;
    ButtonGroup clientButtonGroup;

    public ClientControl(Hotel h, JPanel p, JPanel infoP, ButtonGroup clientBG) {hotel=h; pane=p; infosPane=infoP; clientButtonGroup=clientBG;}

    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        String name = ((JRadioButton)e.getSource()).getName();   //Changer pour gérer button et radioButton
        card.show(pane, name);

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
}
