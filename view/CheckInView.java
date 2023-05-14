package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.*;

public class CheckInView extends JPanel {
    Hotel hotel;
    public CheckInView(Hotel h, Sejour sej) {
        hotel = h;
        // LABELS
        JLabel labelTitle = new JLabel("Sejour crée !");
        labelTitle.setForeground(Color.red);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        Font font = labelTitle.getFont();
        float newSize = font.getSize() + 2;
        labelTitle.setFont(font.deriveFont(newSize));

        // chambres
        Vector<String> chambresStr = new Vector<String>();
        for(Chambre ch : sej.reservation.listChambre) {
            chambresStr.add("Chambre n°" + ch.num);
        }
        JLabel labelChamber = new JLabel("Chambres : " + chambresStr);
        JLabel labelPrix = new JLabel("Prix du séjour (à payer lors du départ) : " + sej.prix + "€");

        // dates
        // Change le format des dates pour l'affichage
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
        String dateDebStr = formatter.format(sej.reservation.dateDeb);
        String dateFinStr = formatter.format(sej.reservation.dateFin);
        JLabel labelDate = new JLabel("Date : du " + dateDebStr + " au " + dateFinStr);

        // choix options
        JLabel chooseOps = new JLabel("Choix des options de séjour :");
        chooseOps.setForeground(Color.BLUE);
        chooseOps.setAlignmentX(CENTER_ALIGNMENT);
        Font font2 = chooseOps.getFont();
        float newSize2 = font.getSize() + 1;
        chooseOps.setFont(font2.deriveFont(newSize2));

        // panel labels
        JPanel paneLabels = new JPanel(new GridLayout(5, 1, 0, 7));
        paneLabels.add(labelTitle);
        paneLabels.add(labelDate);
        paneLabels.add(labelChamber);
        paneLabels.add(labelPrix);

        // Panel scroll
        JPanel innerScroll = new JPanel();
        innerScroll.setLayout(new BoxLayout(innerScroll, BoxLayout.Y_AXIS));
        for (Produit p : hotel.listProd) {
            JCheckBox checkBox = new JCheckBox(p.type + ", " + p.prix + "€");
            checkBox.setActionCommand(p.type + " " + p.prix);
            innerScroll.add(checkBox);
        }
        JScrollPane paneScroll = new JScrollPane(innerScroll);
        paneScroll.setBorder(BorderFactory.createTitledBorder("Produits"));
        JPanel outerScroll = new JPanel();
        outerScroll.setLayout(new BoxLayout(outerScroll, BoxLayout.Y_AXIS));
        outerScroll.add(chooseOps);
        outerScroll.add(paneScroll);

        // Bouton
        JButton button = new JButton("Valider");
        // Ajout des objets au panel
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.add(BorderLayout.NORTH, paneLabels);
        this.add(BorderLayout.CENTER, outerScroll);
        this.add(BorderLayout.SOUTH, button);
    }
}
