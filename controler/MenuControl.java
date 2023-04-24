package controler;
import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.*;

public class MenuControl implements ActionListener {
    JLabel titre;
    Container contain;
    public MenuControl(JLabel t, Container c) { titre = t; contain=c; }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)contain.getLayout();
        String name = ((JMenuItem)e.getSource()).getText();
        titre.setText(name);
        card.show(contain, name);
    }
}
