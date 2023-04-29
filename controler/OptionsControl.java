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
    Produit produit;
    String typeAction, text, oldType, newType, oldPrix, newPrix;
    JButton button;
    boolean isOption;
    // constructeur pour changer de cards
    public OptionsControl(JPanel p) { typeAction = "change"; pane = p; }
    // constructeur pour rendre le button clickable lorsqu'un jRadioButton est selctionné
    public OptionsControl(JButton b)  { typeAction = "enable"; button = b; }
    // constructeur pour modifier option
    public OptionsControl(Hotel h, String str, JPanel p, ButtonGroup g, JTextField n, JTextField pr) { 
        typeAction = "modify"+str+" change"; 
        hotel=h; pane = p; group = g; nameTF = n; priceTF = pr; 
    }
    // constructeur pour ajouter une option chambre
    public OptionsControl(Hotel h, JPanel p, JPanel pCh, ButtonGroup g, JPanel ps, JTextField n, JTextField pr, JButton b) { 
        typeAction = "addCh change"; 
        hotel=h; pane = p;  paneFromChView = pCh; group = g; paneInnerScroll = ps; nameTF = n; priceTF = pr; button = b;
    }
    // constructeur pour ajouter une option sejour
    public OptionsControl(Hotel h, JPanel p, ButtonGroup g, JPanel ps, JTextField n, JTextField pr, JButton b) { 
        typeAction = "addSej change"; 
        hotel=h; pane = p; group = g; paneInnerScroll = ps; nameTF = n; priceTF = pr; button = b;
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        // Affiche la card
        if (typeAction.endsWith("change")) {
            CardLayout card = (CardLayout)pane.getLayout();
            String nameButton = ((JButton)e.getSource()).getName();
            card.show(pane, nameButton);
        }

        // Rend le button clickable
        if (typeAction.equals("enable")) { button.setEnabled(true); }

        // Modifie une option
        if (typeAction.startsWith("modify")) {
            /* Trouve le RadioButton selectionné 
            et récupère les anciennes données de l'option */
            String nameButton = ((JButton)e.getSource()).getName();
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
                        if (typeAction.startsWith("modifyCh")) {
                            option = hotel.searchOption(oldType, Double.parseDouble(oldPrix));
                            hotel.changeOption(option, newType, Double.parseDouble(newPrix));
                        }
                        if (typeAction.startsWith("modifySej")) {
                            produit = hotel.searchProd(oldType, Double.parseDouble(oldPrix));
                            hotel.changeProd(produit, newType, Double.parseDouble(newPrix));
                        }
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
            // ajout à la view
            JRadioButton RadioButton = new JRadioButton(newType + ", " + newPrix + "€");
            RadioButton.setActionCommand(newType + " " + newPrix);
            RadioButton.addActionListener(new OptionsControl(button));
            group.add(RadioButton);
            paneInnerScroll.add(RadioButton);
            nameTF.setText("");
            priceTF.setText("");
            // ajout au model
            new Option(newType, Double.parseDouble(newPrix));
            hotel.addOption(option);
            // Ajout à la view dans GestionChambre
            if (typeAction.equals("addCh")) {
                JCheckBox tmp = new JCheckBox(newType);
                paneFromChView.add(tmp);
            }
        }
    }
}
