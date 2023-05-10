package controler;
import javax.swing.*;

import model.Hotel;
import view.*;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.*;

public class MenuControl implements ActionListener {
    Hotel hotel;
    JLabel titre;
    Container contain;
    public MenuControl(Hotel h, JLabel t, Container c) { hotel = h; titre = t; contain=c; }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        String name = ((JMenuItem)e.getSource()).getText();
        titre.setText(name);
        contain.removeAll();
        contain.revalidate();
        contain.repaint();
        switch (name) {
            case "Enregistrements" :
            EnregistrementsView p1 = new EnregistrementsView(hotel);
            contain.add(p1);
            p1.setBounds(0, 0, 800, 455);
            break;

            case "Clients" :
            ClientView p2 = new ClientView(hotel);
            contain.add(p2);
            p2.setBounds(0, 0, 800, 455);
            break;

            case "Chambres" :
            GestionChambre p3 = new GestionChambre(hotel);
            contain.add(p3);
            p3.setBounds(0, 0, 800, 455);
            break;

            case "Options" :
            OptionsView p4 = new OptionsView(hotel);
            contain.add(p4);
            p4.setBounds(0, 0, 800, 455);
            break;
        }
    }
}
