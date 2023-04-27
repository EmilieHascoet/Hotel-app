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
    JPanel infoCh;
    ButtonGroup group;
    String text;
    String[] splitedText;
    JTextField num, places, price;
    String typeAction;

    public ChambreControl(JPanel p) { pane=p; }

    public ChambreControl(Hotel h, JPanel p, JPanel cb, JPanel gp, ButtonGroup g, JTextField n, JTextField pl, JTextField pr) { 
        hotel = h; pane = p; checkBoxPane=cb; groupPane=gp; group=g; num = n; places=pl; price = pr; typeAction="ajouter";
    }

    public ChambreControl(JPanel p, JPanel info, ButtonGroup g, JTextField n, JTextField pl, JTextField pr) { 
        pane = p; infoCh=info; group = g; num = n; places=pl; price = pr; typeAction="modifier";
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
                        }
                    }

                }
            }
        }

        if (typeAction.equals("modifier")) {
            if (group != null && group.getSelection() != null) {
                // Trouve le RadioButton selectionné et récupère ses données
                text = group.getSelection().getActionCommand();
                splitedText = text.split(";");
                num.setText(splitedText[0]);
                places.setText(splitedText[1]);
                price.setText(splitedText[2]);

                // Supprimer tout de infoCh
                for (int i=3; i<splitedText.length; i++) {
                    JCheckBox tmp = new JCheckBox(splitedText[i] + ", " + splitedText[++i] + "€");
                    infoCh.add(tmp);
                }
        
            }
        }
        
        
    }
}
