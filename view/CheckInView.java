package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controler.CheckInControl;
import model.*;

public class CheckInView extends JPanel {
    Hotel hotel;
    JLabel labelPrix = new JLabel();
    Sejour sejour;
    JDialog dialog;
    public CheckInView(Hotel h, Sejour sej, JDialog d) {
        hotel = h;
        sejour = sej;
        dialog = d;
        labelPrix.setText("Prix du séjour (à payer lors du départ) : " + sejour.prix + "€");
        // LABELS
        JLabel labelTitle = new JLabel("Sejour crée !");
        labelTitle.setForeground(Color.red);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        Font font = labelTitle.getFont();
        float newSize = font.getSize() + 2;
        labelTitle.setFont(font.deriveFont(newSize));

        // chambres
        Vector<String> chambresStr = sej.reservation.chambresToString();
        JLabel labelChamber = new JLabel("Chambres : " + chambresStr);

        // dates
        // Change le format des dates pour l'affichage
        JLabel labelDate = new JLabel(sej.reservation.datesToString());

        // panel labels
        JPanel paneLabels = new JPanel(new GridLayout(4, 1, 0, 7));
        paneLabels.add(labelTitle);
        paneLabels.add(labelDate);
        paneLabels.add(labelChamber);
        
        // choix options
        JLabel chooseOps = new JLabel("Choix des options de séjour :");
        chooseOps.setForeground(Color.BLUE);
        chooseOps.setAlignmentX(CENTER_ALIGNMENT);
        Font font2 = chooseOps.getFont();
        float newSize2 = font.getSize() + 1;
        chooseOps.setFont(font2.deriveFont(newSize2));

        // Panel scroll
        CheckInControl ctrProduit = new CheckInControl(hotel, labelPrix, sejour);
        JPanel innerScroll = new JPanel();
        innerScroll.setLayout(new BoxLayout(innerScroll, BoxLayout.Y_AXIS));
        for (Produit p : hotel.listProd) {
            JCheckBox checkBox = new JCheckBox(p.type + ", " + p.prix + "€");
            checkBox.setActionCommand(p.type + " " + p.prix);
            checkBox.addActionListener(ctrProduit);
            innerScroll.add(checkBox);
        }
        JScrollPane paneScroll = new JScrollPane(innerScroll);
        paneScroll.setBorder(BorderFactory.createTitledBorder("Produits"));

        // panel center
        JPanel outerScroll = new JPanel();
        outerScroll.setLayout(new BoxLayout(outerScroll, BoxLayout.Y_AXIS));
        outerScroll.add(chooseOps);
        outerScroll.add(paneScroll);

        // Bouton
        CheckInControl ctrButton = new CheckInControl(dialog);
        JButton button = new JButton("Valider");
        button.addActionListener(ctrButton);
        JPanel paneButton = new JPanel(new GridLayout(2, 1));
        paneButton.add(labelPrix);
        paneButton.add(button);


        // Ajout des objets au panel
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.add(BorderLayout.NORTH, paneLabels);
        this.add(BorderLayout.CENTER, outerScroll);
        this.add(BorderLayout.SOUTH, paneButton);
    }
}
