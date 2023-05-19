package controler;

import javax.swing.*;
import java.awt.*;
import java.awt.CardLayout;
import java.awt.event.*;
import model.*;

public class ChambreControl implements ActionListener {
    Hotel hotel;
    JPanel pane;
    JPanel groupPane;
    JPanel checkBoxPane;
    JPanel infoChOption;
    ButtonGroup group;
    String text;
    String[] splitedText;
    JTextField num, places, price;
    String typeAction;
    JButton modifButton;

    public ChambreControl(JPanel p) { pane=p; }

    public ChambreControl(Hotel h, JPanel p, JPanel cb, JPanel gp, ButtonGroup g, JTextField n, JTextField pl, JButton b) { 
        hotel = h; pane = p; checkBoxPane=cb; groupPane=gp; group=g; num = n; places=pl; modifButton=b; typeAction="ajouter";
    }

    public ChambreControl(Hotel h, JPanel p, JPanel cb, JPanel info, ButtonGroup g, JTextField n, JTextField pl, JTextField pr) { 
        hotel=h; pane = p; checkBoxPane=cb; infoChOption=info; group = g; num = n; places=pl; price = pr; typeAction="modifier";
    }

    // Event, clic du menuItem
    public void actionPerformed(ActionEvent e) {
        CardLayout card = (CardLayout)pane.getLayout();
        String name = ((JButton)e.getSource()).getName();
        card.show(pane, name);

        if (typeAction.equals("ajouter")) {
            String text = ((JButton)e.getSource()).getText();

            if (text.equals("Ajouter !")) {

                if (hotel.listChambre.contains(hotel.searchChamber(num.getText()))) {
                    card.show(pane, "addCh");
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un autre numéro de chambre", "Chambre déjà existante", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //Gestion des éventuelles erreurs
                    String numText = num.getText();
                    String placesText = places.getText();

                    try { Integer.parseInt(numText); }
                    catch (NumberFormatException nE) {
                        card.show(pane, "addCh");
                        JOptionPane.showMessageDialog(null, "Veuillez saisir un entier", "Numéro de chambre invalide", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
 
                    try { Integer.parseInt(placesText); }
                    catch (NumberFormatException nE) {
                        card.show(pane, "addCh");
                        JOptionPane.showMessageDialog(null, "Veuillez saisir un entier", "Nombre de places invalide", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    num.setText("");
                    places.setText("");
                    int placesInt = Integer.parseInt(placesText);
    
                    //Pour le model
                    Chambre newCh = new Chambre(numText, placesInt);
                    hotel.addChambre(newCh);
    
                    //Pour l'affichage
                    JRadioButton newChRadioButton = new JRadioButton("Chambre n° : " + newCh.num);
                    newChRadioButton.setActionCommand(newCh.num);
                    newChRadioButton.addActionListener(new RadioButtonListener(modifButton));
                    group.add(newChRadioButton);
                    groupPane.add(newChRadioButton);
    
                    Component[] checkBoxs = checkBoxPane.getComponents();
                    for (Component c : checkBoxs) {
                        JCheckBox tmp = (JCheckBox)c;
                        if (tmp.isSelected()) {
                            splitedText = tmp.getActionCommand().split(";");
    
                            Option newOpt = new Option(splitedText[0], Double.parseDouble(splitedText[1]));
                            newCh.addOption(newOpt);
    
                            tmp.setSelected(false);
                        }
                    }
                }
                

                
            }
        }

        if (typeAction.equals("modifier")) {
            String text = ((JButton)e.getSource()).getText();
    
            // Trouve la chambre correspondant au RadioButton sélectionné
            String numChToEdit = group.getSelection().getActionCommand();
            Chambre chToEdit = hotel.searchChamber(numChToEdit);

            if (text.equals("Modifier")) {
                    num.setText(chToEdit.num);
                    places.setText(chToEdit.nbrPlaces + "");
                    price.setText(chToEdit.prix + "");

                    num.setEnabled(false);
                    //Pour afficher les options
                    infoChOption.removeAll();
    
                    for (Option o : hotel.listOption) {
                        JCheckBox tmp = new JCheckBox(o.type + ", " + o.prix + "€");
                        tmp.setActionCommand(o.type + ";" + o.prix);
                        for (Option op : chToEdit.listOption) {
                            if (o.type.equals(op.type)) tmp.setSelected(true); 
                        } 
                        infoChOption.add(tmp);
                    }
                
            }

            if (text.equals("Modifier !")) {
                ButtonModel chButton = group.getSelection();
                //Mettre la méthode searchChambre dans Hotel
                try { Integer.parseInt(places.getText()); }
                catch (NumberFormatException nE) {
                    card.show(pane, "modifCh");
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un entier", "Nombre de places invalide", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try { Double.parseDouble(price.getText()); }
                catch (NumberFormatException nE) {
                    card.show(pane, "modifCh");
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un entier ou un double", "Prix invalide", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                chToEdit.nbrPlaces = Integer.parseInt(places.getText());
                chToEdit.prix = Double.parseDouble(price.getText());
                chToEdit.listOption.removeAllElements();

                chButton.setActionCommand(chToEdit.num);

                Component[] checkBoxs = infoChOption.getComponents();
                for (Component c : checkBoxs) { 
                    JCheckBox optionCheckBox = (JCheckBox)c;
                    if (optionCheckBox.isSelected()) {
                        
                        splitedText = optionCheckBox.getActionCommand().split(";");

                        Option newOpt = new Option(splitedText[0], Double.parseDouble(splitedText[1]));
                        chToEdit.addOption(newOpt);                  
                    }
                }
            }
        } 
    }
}
