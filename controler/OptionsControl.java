package controler;
import model.*;
import view.*;
import view.OptionsView;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;

public class OptionsControl implements ActionListener {
    Hotel hotel;
    JPanel pane;
    ButtonGroup group;
    JTextField nameTF, priceTF;
    Option option;
    String oldType, newType, oldPrix, newPrix;
    public OptionsControl(JPanel p) { pane = p; }
    public OptionsControl(Hotel h, JPanel p, ButtonGroup g, JTextField n, JTextField pr) { 
        hotel=h; pane = p; group = g; nameTF = n; priceTF = pr; 
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        // Affiche la card
        CardLayout card = (CardLayout)pane.getLayout();
        String nameButton = ((JButton)e.getSource()).getName();
        card.show(pane, nameButton);
        if (group != null && group.getSelection() != null) {
            /* Trouve le RadioButton selectionné 
            et récupère les anciennes données de l'option */
            String text = group.getSelection().getActionCommand();
            String[] splitedText = text.split(" ");
            oldType = splitedText[0];
            oldPrix = splitedText[1];
            // modifie l'option avec les nouvelles données
            if (nameButton.equals("consultCh")) {
                newType = nameTF.getText();
                newPrix = priceTF.getText();
                option = hotel.searchOption(oldType, Double.parseDouble(oldPrix));
                hotel.changeOption(option, newType, Double.parseDouble(newPrix));
                group.getSelection().setText(newType + ", " + newPrix + "€");
            }
            // ou auto remplit le formulaire
            else {
                nameTF.setText(oldType);
                priceTF.setText(oldPrix);
            }
        }
        for (Option o : hotel.listOption) {
            System.out.println(o.type);
        }
    }
}
