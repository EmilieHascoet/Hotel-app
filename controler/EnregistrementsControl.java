package controler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;

import model.*;
import view.CheckInView;

public class EnregistrementsControl implements ActionListener {
    Hotel hotel;
    JPanel panel;
    JTextField textField;
    ButtonGroup group;
    JButton button1, button2, button3;
    String nameButton;
    // Constructeurs filtre par nom
    public EnregistrementsControl(Hotel h, JTextField tf, JPanel p, JButton b1, JButton b2, JButton b3) {
        hotel = h; textField = tf; panel = p; button1 = b1; button2 = b2; button3 = b3;
    }
    // Constructeur check in et check out
    public EnregistrementsControl(Hotel h, ButtonGroup g, JPanel p, JButton b1, JButton b2, JButton b3) {
        hotel = h; group = g; panel = p; button1 = b1; button2 = b2; button3 = b3;
    }

    public void actionPerformed(ActionEvent e) {
        // désacive les boutons
        button1.setEnabled(false);
        if (button2 != null) { button2.setEnabled(false); }
        if (button3 != null) { button3.setEnabled(false); }
        if (((JButton)e.getSource()).getName() == null) { 
            nameButton = ((JButton)e.getSource()).getText();
            if (nameButton.equals("Check-in")) {
                for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        int id = Integer.parseInt(button.getActionCommand());
                        Reservation res = hotel.searchRes(id);
                        hotel.check_in(res);
                        // remove button
                        panel.remove(button);
                        panel.revalidate();
                        panel.repaint();
                        
                        // Jdialog avec choix des options de séjour
                        JDialog dialog = new JDialog(view.Main.main, "Creation d'un séjour", true);
                        CheckInView paneCheckIn = new CheckInView(hotel, res.sejour, dialog);
    
                        dialog.setSize(new Dimension(450,350));
                        dialog.setResizable(false);
                        dialog.setLocationRelativeTo(null);
                        dialog.add(paneCheckIn);
                        dialog.setVisible(true);
                    }
                }
            }
            if(nameButton.equals("Supprimer")){
                for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        int id = Integer.parseInt(button.getActionCommand());
                        Reservation res = hotel.searchRes(id);
                        // JOptionPane
                        int userChoice = JOptionPane.showConfirmDialog(
                        null,
                        "Infos réservation :\nClient : "+res.client.prenom+" "+res.client.nom+
                        "\nTéléphone : "+res.client.tel+"\n\nDate : "+res.datesToString()+
                        "\nChambres : "+res.chambresToString()+
                        "\n\nEtes vous sur de vouloir supprimer la reservation ?",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                        );
                        if (userChoice == JOptionPane.OK_OPTION) {
                            // supprime la reservation
                            hotel.suppRes(res, 0);
                            // remove button
                            panel.remove(button);
                            panel.revalidate();
                            panel.repaint();
                        }
                    }
                }
            }
            if(nameButton.equals("Infos")){
                for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        int id = Integer.parseInt(button.getActionCommand());
                        Reservation res = hotel.searchRes(id);
                        // JOptionPane
                        JOptionPane.showMessageDialog(
                        null,
                        "Infos réservation :\nClient : "+res.client.prenom+" "+res.client.nom+
                        "\nTéléphone : "+res.client.tel+"\n\nDate : "+res.datesToString()+
                        "\nChambres : "+res.chambresToString()
                        );
                    }
                }
            }
        }
        // Filtre les reservations, Barre de recherche
        else { 
            nameButton = ((JButton)e.getSource()).getName(); 
            if (nameButton.equals("arrivees")) {
                // Vide le conteneur
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                // Remplis le conteneur des arrivants d'aujourd'hui filtrés
                for (Reservation res : hotel.arrivees(textField.getText())) {
                    JRadioButton RadioButton = new JRadioButton(res.client.nom + " " + res.client.prenom);
                    // instance d'evenement pour rendre le button clickable
                    RadioButton.addActionListener(new RadioButtonListener(button1));
                    RadioButton.addActionListener(new RadioButtonListener(button2));
                    RadioButton.setActionCommand(res.id+"");
                    panel.add(RadioButton);
                }
            }
            if (nameButton.equals("departs")) {
                // Vide le conteneur
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                // Remplis le conteneur des départs d'aujourd'hui filtrés
                for (Sejour sej : hotel.departs(textField.getText())) {
                    JRadioButton RadioButton = new JRadioButton(sej.reservation.client.nom + " " + sej.reservation.client.prenom);
                    // instance d'evenement pour rendre le button clickable
                    RadioButton.addActionListener(new RadioButtonListener(button1));
                    RadioButton.setActionCommand(sej.reservation.id+"");
                    panel.add(RadioButton);
                }
            }
        }
    }
}