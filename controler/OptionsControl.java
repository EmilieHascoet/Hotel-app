package controler;
import model.*;
import view.*;
import view.OptionsView;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.util.Enumeration;

public class OptionsControl implements ActionListener {
    Hotel hotel;
    JPanel pane;
    ButtonGroup group;
    JTextField nameTF, priceTF;
    Option option;
    String text, oldType, newType, oldPrix, newPrix;
    public OptionsControl(JPanel p) { pane = p; }
    public OptionsControl(Hotel h, JPanel p, ButtonGroup g, JTextField n, JTextField pr) { 
        hotel=h; pane = p; group = g; nameTF = n; priceTF = pr; 
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        // Affiche la card
        CardLayout card = (CardLayout)pane.getLayout();
        String nameButton = ((JButton)e.getSource()).getName();
        System.out.println(nameButton);
        card.show(pane, nameButton);
        if (group != null && group.getSelection() != null) {
            /* Trouve le RadioButton selectionné 
            et récupère les anciennes données de l'option */
            for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    text = button.getActionCommand();                 
                    oldType = text.substring(0, text.lastIndexOf(" "));
                    oldPrix = text.substring(text.lastIndexOf(" ") + 1);
                    // modifie l'option avec les nouvelles données
                    if (nameButton.equals("consultCh")) {
                        newType = nameTF.getText();
                        newPrix = priceTF.getText();
                        // modifie le model
                        option = hotel.searchOption(oldType, Double.parseDouble(oldPrix));
                        hotel.changeOption(option, newType, Double.parseDouble(newPrix));
                        // modifie la view
                        button.setText(newType + ", " + newPrix + "€");
                        button.setActionCommand(newType + " " + newPrix);
                    }
                    // ou auto remplit le formulaire en fonction du button actionné
                    else {
                        nameTF.setText(oldType);
                        priceTF.setText(oldPrix);
                    }
                    break;
                }
            }
        }
    }
}
