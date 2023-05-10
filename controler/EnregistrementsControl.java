package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.*;

public class EnregistrementsControl implements ActionListener {
    Hotel hotel;
    JPanel panel;
    JTextField textField;
    ButtonGroup group;
    // Constructeur filtre par nom
    public EnregistrementsControl(Hotel h, JTextField tf, JPanel p, ButtonGroup g) {
        hotel = h; textField = tf; panel = p; group = g;
    }
    public void actionPerformed(ActionEvent e) {
        String nameButton = ((JButton)e.getSource()).getName();
        if (nameButton.equals("arrivees")) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            for (Reservation res : hotel.arrivees(textField.getText())) {
                JRadioButton RadioButton = new JRadioButton(res.client.nom + " " + res.client.prenom);
                // instance d'evenement pour rendre le button clickable
                //RadioButton.addActionListener(new EnregistrementsControl(checkIn));
                //RadioButton.setActionCommand(res);
                group.add(RadioButton);
                panel.add(RadioButton);
            }
        }
        if (nameButton.equals("departs")) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            for (Reservation res : hotel.departs(textField.getText())) {
                JRadioButton RadioButton = new JRadioButton(res.client.nom + " " + res.client.prenom);
                // instance d'evenement pour rendre le button clickable
                //RadioButton.addActionListener(new EnregistrementsControl(checkIn));
                //RadioButton.setActionCommand(res);
                group.add(RadioButton);
                panel.add(RadioButton);
            }
        }

    }
}
