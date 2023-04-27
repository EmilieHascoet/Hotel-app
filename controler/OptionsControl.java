package controler;
import model.*;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.util.Enumeration;

public class OptionsControl implements ActionListener {
    Hotel hotel;
    JPanel pane, paneFromChView, paneInnerScroll;
    ButtonGroup group;
    JTextField nameTF, priceTF;
    Option option;
    String typeAction, text, oldType, newType, oldPrix, newPrix;
    // constructeur pour changer de cards
    public OptionsControl(JPanel p) { typeAction = "change"; pane = p; }
    // constructeur pour modifier option
    public OptionsControl(Hotel h, JPanel p, ButtonGroup g, JTextField n, JTextField pr) { 
        typeAction = "modify"; 
        hotel=h; pane = p; group = g; nameTF = n; priceTF = pr; 
    }
    // constructeur pour ajouter une option chambre
    public OptionsControl(Hotel h, JPanel p, JPanel pCh, ButtonGroup g, JPanel ps, JTextField n, JTextField pr) { 
        typeAction = "addCh"; 
        hotel=h; pane = p;  paneFromChView = pCh; group = g; paneInnerScroll = ps; nameTF = n; priceTF = pr; 
    }
    // constructeur pour ajouter une option sejour
    public OptionsControl(Hotel h, JPanel p, ButtonGroup g, JPanel ps, JTextField n, JTextField pr) { 
        typeAction = "add"; 
        hotel=h; pane = p; group = g; paneInnerScroll = ps; nameTF = n; priceTF = pr; 
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        // Affiche la card
        CardLayout card = (CardLayout)pane.getLayout();
        String nameButton = ((JButton)e.getSource()).getName();
        card.show(pane, nameButton);
        // Modifie une option
        if (typeAction.equals("modify")) {
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
        // Ajout une option
        if (typeAction.startsWith("add")) {
            newType = nameTF.getText();
            newPrix = priceTF.getText();
            // ajout au model
            new Option(newType, Double.parseDouble(newPrix));
            hotel.addOption(option);
            // ajout à la view
            JRadioButton RadioButton = new JRadioButton(newType + ", " + newPrix + "€");
            // RadioButton.addActionListener();
            RadioButton.setActionCommand(newType + " " + newPrix);
            group.add(RadioButton);
            paneInnerScroll.add(RadioButton);
            nameTF.setText("");
            priceTF.setText("");
            // Ajout à la view dans GestionChambre
            if (typeAction.equals("addCh")) {
                JCheckBox tmp = new JCheckBox(newType);
                paneFromChView.add(tmp);
            }
        }
    }
}
