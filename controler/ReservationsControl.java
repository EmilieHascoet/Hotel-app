package controler;
import model.*;

import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.Chambre;

public class ReservationsControl implements ActionListener {
    Hotel hotel;
    JFrame frame;
    JPanel paneCh, paneOpt;
    JCheckBox checkBox;
    Vector<Chambre> listChDispo, listChSelected;
    Vector<Option> listFiltre;
    JSlider slider;
    int nbrPlaces;
    JDateChooser startDateChooser, endDateChooser;
    Date startDate, endDate;
    String textButton;
    // Constructeur selectionne une chambre
    public ReservationsControl(JCheckBox cb, Vector<Chambre> listCh) {
        checkBox = cb; listChSelected = listCh;
    }
    // Constructeur choix date
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JDateChooser sd, JDateChooser ed) {
        hotel = h; paneCh = p; listChDispo = listCh; listFiltre = listF; nbrPlaces = places;;
        startDateChooser = sd; endDateChooser = ed;
    }
    // Constructeur filtre
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JPanel pOpt, JSlider s) {
        hotel = h; paneCh = p; listChDispo = listCh; listFiltre = listF; nbrPlaces = places;
        paneOpt = pOpt; slider = s;
    }

    public void addChamberToView(Vector<Chambre> listChambres, JPanel panelCh) {
        for(Chambre ch : listChambres) {
            // panel chambre
            JPanel panel = new JPanel();
            Border border = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);
            EmptyBorder padding = new EmptyBorder(7, 10, 7, 10);
            panel.setLayout(new GridLayout(6, 1));
            panel.setBorder(BorderFactory.createCompoundBorder(border, padding));
            // Labels
            JLabel label = new JLabel("Etage : " + ch.etage);
            JLabel label2 = new JLabel("Places : " + ch.nbrPlaces);
            JLabel label3 = new JLabel("Prix : " + ch.prix + "/nuit");
            JLabel label4 = new JLabel("Liste options :");
            label4.setForeground(Color.blue);
            // comboBox contenant les options
            JComboBox<String> optionList = new JComboBox<String>();
            for (Option opt : ch.listOption) {
                optionList.addItem(opt.type);
            }
            // checkBox
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setActionCommand(ch.num + "");
            // Ajout des objets au panel chambre
            panel.add(label);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(optionList);
            panel.add(checkBox);
            // Ajout du panel chambre au panel principal 
            panelCh.add(panel);
            // Action checkbox selected
            ReservationsControl ctrChSelected = new ReservationsControl(checkBox, listChSelected);
            checkBox.addActionListener(ctrChSelected);
        }
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
                    listChDispo = hotel.searchChamberDispo(startDate, endDate);
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
                nbrPlaces = slider.getValue();
            }
            // Affiche les chambres lorsque les filtre ou chambres dispo ont été modifié
            paneCh.removeAll();
            paneCh.revalidate();
            paneCh.repaint();

            // Chambres ayant tous les options souhaitées
            Vector<Chambre> listChAffichage1 = hotel.everyOption(listChDispo, listFiltre, nbrPlaces);
            // JLabel label = new JLabel("Chambres avec tous les filtres selectionné");
            // paneCh.add(label);
            addChamberToView(listChAffichage1, paneCh);
            
            // Chambres ayant une ou plusiseurs options souhaitées
            Vector<Chambre> listChAffichage2 = hotel.someOption(listChDispo, listFiltre, nbrPlaces);
            // JLabel label2 = new JLabel("Chambres qui pourrait vous plaire");
            // paneCh.add(label2);
            addChamberToView(listChAffichage2, paneCh);

            int nbrChambre = listChAffichage1.size() + listChAffichage2.size();
            paneCh.setLayout(new GridLayout(nbrChambre/2+1, 2, 20, 20));
        }
    }
}
