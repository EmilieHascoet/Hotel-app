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

    public ChambreControl(JPanel p) { pane=p; }

    public ChambreControl(Hotel h, JPanel p, JPanel cb, JPanel gp, ButtonGroup g, JTextField n, JTextField pl) { 
        hotel = h; pane = p; checkBoxPane=cb; groupPane=gp; group=g; num = n; places=pl; typeAction="ajouter";
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
                String numText = num.getText();
                String placesText = places.getText();
                Component[] checkBoxs = checkBoxPane.getComponents();

                num.setText("");
                places.setText("");

                if (numText.length() > 0 && placesText.length() > 0) {
                    //convertir String en int
                    int numInt = Integer.parseInt(numText);
                    int placesInt = Integer.parseInt(placesText);

                    //Pour le model
                    Chambre newCh = new Chambre(numInt, placesInt);
                    hotel.addChambre(newCh);

                    //Pour l'affichage
                    JRadioButton RadioButton = new JRadioButton("Chambre n° : " + newCh.num);
                    RadioButton.setActionCommand(newCh.num + ";" + newCh.nbrPlaces + ";" + newCh.prix);
                    group.add(RadioButton);
                    groupPane.add(RadioButton);

                    for (Component c : checkBoxs) {
                        JCheckBox tmp = (JCheckBox)c;
                        if (tmp.isSelected()) {
                            splitedText = tmp.getActionCommand().split(";");

                            Option newOpt = new Option(splitedText[0], Double.parseDouble(splitedText[1]));
                            newCh.addOption(newOpt);

                            String actionCommand = RadioButton.getActionCommand();
                            RadioButton.setActionCommand(actionCommand + ";" + splitedText[0] + ";" + splitedText[1]);

                            tmp.setSelected(false);
                        }
                    }

                }
            }
        }

        if (typeAction.equals("modifier")) {
            String text = ((JButton)e.getSource()).getText();
            if (text.equals("Modifier")) {
                if (group != null && group.getSelection() != null) {
                    // Trouve le RadioButton selectionné et récupère ses données
                    text = group.getSelection().getActionCommand();
                    splitedText = text.split(";");
                    num.setText(splitedText[0]);
                    places.setText(splitedText[1]);
                    price.setText(splitedText[2]);
    
                    //Pour afficher les options
                    infoChOption.removeAll();
    
                    for (Option o : hotel.listOption) {
                        JCheckBox tmp = new JCheckBox(o.type + ", " + o.prix + "€");
                        tmp.setActionCommand(o.type + ";" + o.prix);
                        for (int i=3; i<splitedText.length; i+=2) {
                            if (splitedText[i].equals(o.type)) tmp.setSelected(true); 
                        } 
                        infoChOption.add(tmp);
                    }
                }
            }

            if (text.equals("Modifier !")) {
                ButtonModel chButton = group.getSelection();
                text = group.getSelection().getActionCommand();
                splitedText = text.split(";");

                //Mettre la méthode searchChambre dans Hotel
                for (Chambre ch : hotel.listChambre) {
                    if (ch.num == Integer.parseInt(splitedText[0])) {
                        ch.nbrPlaces = Integer.parseInt(places.getText());
                        ch.prix = Double.parseDouble(price.getText());
                        ch.listOption.removeAllElements();

                        chButton.setActionCommand(ch.num + ";" + ch.nbrPlaces + ";" + ch.prix);

                        Component[] checkBoxs = infoChOption.getComponents();
                        for (Component c : checkBoxs) {
                            
                            JCheckBox tmp = (JCheckBox)c;
                            if (tmp.isSelected()) {
                                
                                splitedText = tmp.getActionCommand().split(";");
    
                                Option newOpt = new Option(splitedText[0], Double.parseDouble(splitedText[1]));
                                ch.addOption(newOpt);
    
                                String actionCommand = chButton.getActionCommand();
                                chButton.setActionCommand(actionCommand + ";" + splitedText[0] + ";" + splitedText[1]);
                                
                            }

                        }
                    }
                }
            }
        }
        
        
    }
}
