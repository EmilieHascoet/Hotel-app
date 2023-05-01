package controler;
import model.*;

import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import model.Chambre;

public class ReservationsControl implements ActionListener {
    Hotel hotel;
    JFrame frame;
    JPanel pane, paneOpt;
    JCheckBox checkBox;
    Vector<Chambre> listChDispo, listChSelected;
    Vector<Option> listFiltre;
    JDateChooser startDateChooser, endDateChooser;
    Date startDate, endDate;
    String textButton;
    // Constructeur selectionne une chambre
    public ReservationsControl(JCheckBox cb, Vector<Chambre> listCh) {
        checkBox = cb; listChSelected = listCh;
    }
    // Constructeur choix date
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, JDateChooser sd, JDateChooser ed) {
        hotel = h; pane = p; listChDispo = listCh; listFiltre = listF;
        startDateChooser = sd; endDateChooser = ed;
    }
    // Constructeur filtre
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, JPanel pOpt) {
        hotel = h; pane = p; listChDispo = listCh; listFiltre = listF;
        paneOpt = pOpt;
        
    }
    
    public void actionPerformed(ActionEvent e) {
        // Coche un checkButton
        if (checkBox != null) {
            // cherche la chambre correspondante
            checkBox.getActionCommand();
            if (checkBox.isSelected()) {
                // ajoute à la liste
            }
            else {
                // retire de la liste
            }
        }
        // Intéraction avec un bouton
        else {
            textButton = ((JButton)e.getSource()).getText();
            // Choix date
            if (textButton.equals("Valider")) {
                // Initialise la date de début et de fin avec ce qu'à rentré l'utilisateur
                startDate = startDateChooser.getDate();
                endDate = endDateChooser.getDate();
                // update le calendrier à hier pour le test de la date déjà passée
                Calendar calendrier = Calendar.getInstance();
                calendrier.add(Calendar.DATE, -1);
    
                // Affiche un message d'erreur si la date de début excède celle de fin
                System.out.println(startDate + " " + endDate);
                if (startDate.compareTo(endDate) >= 0) {
                    JOptionPane.showMessageDialog(frame,
                    "Veuillez choisir une date de début inférieur à celle de fin.",
                    "Erreur de date !",
                    JOptionPane.ERROR_MESSAGE);
                }
                // Affiche un message d'erreur si la date est déjà passé
                else if (startDate.compareTo(calendrier.getTime()) < 0) {
                    SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
                    JOptionPane.showMessageDialog(frame,
                    "Veuillez choisir une date futur.\nLe " + formatter.format(startDate) + " est déjà passé",
                    "Erreur de date !",
                    JOptionPane.ERROR_MESSAGE);
                    System.out.println(startDate);
                }
                // Actualise les chambres dispo si il n'y a aucune erreur de date
                else {
                    listChDispo = hotel.searchChamber(startDate, endDate);
                }
                System.out.println(listChDispo);
            }
            // Filtre 
            if (textButton.equals("Filtrer")) {
                // Vide la liste de filtre
                listFiltre.removeAllElements();
                // Parcours tous les checkbox options
                Component[] checkBoxes = paneOpt.getComponents();
                for (Component c : checkBoxes) {
                    JCheckBox cb = (JCheckBox)c;
                    // Si le checkbox est coché, ajoute l'option à la liste de filtre
                    if (cb.isSelected()) {
                        String text = cb.getActionCommand();                 
                        String type = text.substring(0, text.lastIndexOf(" "));
                        String prix = text.substring(text.lastIndexOf(" ") + 1);
                        Option opt = hotel.searchOption(type, Double.parseDouble(prix));
                        listFiltre.add(opt);
                    }
                }
            }
            // Affiche les chambres lorsque les filtre ou chambres dispo ont été modifié
            pane.removeAll();
            Vector<Chambre> listChAffichage1 = hotel.everyOption(listChDispo, listFiltre);
            // JLabel = new JLabel("Chambres avec tous les filtres selectionné")
            for(Chambre ch : listChAffichage1) {
                // Affiche la chambre
            }
            Vector<Chambre> listChAffichage2 = hotel.someOption(listChDispo, listFiltre);
            // JLabel = new JLabel("Chambres qui pourrait vous plaire")
            for(Chambre ch : listChAffichage2) {
                // Affiche la chambre
            }

        }
    }
}
