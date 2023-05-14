package controler;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import model.*;
public class CheckInControl implements ActionListener {
    Hotel hotel;
    JLabel label;
    Sejour sejour;
    JDialog dialog;
    String action;
    public CheckInControl(JDialog d) {
        action = "valider"; dialog = d;
    }
    public CheckInControl(Hotel h, JLabel l, Sejour sej){
        hotel = h; label = l; sejour = sej; action = "checkBox";
    }
    public void actionPerformed(ActionEvent e) {
        // checkBox cochée
        if (action.equals("checkBox")) {
            JCheckBox checkbox = (JCheckBox)e.getSource();
            String text = checkbox.getActionCommand();
            String type = text.substring(0, text.lastIndexOf(" "));
            String prix = text.substring(text.lastIndexOf(" ") + 1);
            Produit produit = hotel.searchProd(type, Double.parseDouble(prix));
            if (checkbox.isSelected()) { sejour.addProduit(produit); }
            else { sejour.removeProduit(produit); }
            label.setText("Prix du séjour (à payer lors du départ) : " + sejour.prix + "€");
        }
        // bouton valider
        else {
            dialog.dispose();
        }
    }
}
