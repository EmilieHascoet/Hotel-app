package controler;
import model.*;

import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import model.Chambre;

public class ReservationsControl implements ActionListener {
    Hotel hotel;
    JFrame frame;
    JPanel pane;
    Vector<Chambre> listChambres;
    JDateChooser startDateChooser, endDateChooser;
    Date startDate, endDate;
    String textButton;
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, JDateChooser sd, JDateChooser ed) {
        hotel = h; pane = p; listChambres = listCh;
        startDateChooser = sd; endDateChooser = ed;
    }
    public void actionPerformed(ActionEvent e) {
        textButton = ((JButton)e.getSource()).getText();
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
                listChambres = hotel.searchChamber(startDate, endDate);
                // Ajoute les chambre au panel
                pane.removeAll();
            }
            System.out.println(listChambres);
        }
        // Filtre 
    }
}
