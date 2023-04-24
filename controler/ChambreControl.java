package controler;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;
import model.*;

public class ChambreControl implements ActionListener {
    JPanel pane;
    ButtonGroup group;
    String text;
    String[] splitedText;
    JTextField num, places, price;
    public ChambreControl(JPanel p, JTextField n, JTextField pl, JTextField pr) { pane = p; num = n; places=pl; price = pr;}
    public ChambreControl(JPanel p, ButtonGroup g, JTextField n, JTextField pl, JTextField pr) { 
        pane = p; group = g; num = n; places=pl; price = pr; 
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        String name = ((JButton)e.getSource()).getName();
        card.show(pane, name);

        if (group != null && group.getSelection() != null) {
            // Trouve le RadioButton selectionné et récupère ses données
            text = group.getSelection().getActionCommand();
            splitedText = text.split(" ");
            num.setText(splitedText[0]);
            places.setText(splitedText[1]);
            price.setText(splitedText[2]);
        }

        String text = ((JButton)e.getSource()).getText();
        if (text.equals("Ajouter !")) {
            String numText = num.getText();
            String placesText = num.getText();

            if (numText!=null && placesText!=null) {
                //convertir String en int
                Chambre newCh = new Chambre(num.getText(), places.getText());
            }
            
        }
        
    }
}
