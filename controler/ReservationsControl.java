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
    Client client;
    JFrame frame;
    JPanel paneCh;
    JCheckBox checkBox;
    Vector<Chambre> listChDispo, listChSelected;
    Vector<Option> listFiltre;
    JSlider slider;
    int nbrPlaces;
    JDateChooser startDateChooser, endDateChooser;
    Date startDate, endDate;
    String textButton;
    // Constructeur selectionne une option
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JCheckBox cb) {
        hotel = h; paneCh = p; listChDispo = listCh; nbrPlaces = places;
        listFiltre = listF; checkBox = cb;
    }
    // Constructeur bouge le slider
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JSlider s) {
        hotel = h; paneCh = p; listChDispo = listCh; listFiltre = listF;
        nbrPlaces = places; slider = s;
    }
    // Constructeur choix date  ADD START DATE AND END DATE FOR RESERVER
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JDateChooser sd, JDateChooser ed, Date s, Date e) {
        hotel = h; paneCh = p; listChDispo = listCh; listFiltre = listF; nbrPlaces = places;;
        startDateChooser = sd; endDateChooser = ed; startDate = s; endDate = e;
    }
    // Constructeur reserver les chambres
    public ReservationsControl(Hotel h, Client c, JPanel p, Date s, Date e) {
        hotel = h; client = c; paneCh = p; startDate = s; endDate = e;
    }

    // Ajoute les chambres filtré au panel chambre
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
        }
    }

    // Filtre les chambres selon les options puis les affichent
    public void filtreChamber() {
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

    
    public void actionPerformed(ActionEvent e) {
        // Coche un checkButton
        if (checkBox != null) {
            // Récupère les infos de l'option
            String text = checkBox.getActionCommand();                 
            String type = text.substring(0, text.lastIndexOf(" "));
            String prix = text.substring(text.lastIndexOf(" ") + 1);
            Option opt = hotel.searchOption(type, Double.parseDouble(prix));
            // Si le checkbox est coché, ajoute l'option à la liste d'options
            if (checkBox.isSelected()) {
                listFiltre.add(opt);
            }
            // Sinon le retire de la liste
            else {
                listFiltre.remove(opt);
            }
            filtreChamber();
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
                    filtreChamber();
                }
            }
            // Reserver chambre
            else {
                // Crée une réservation
                Reservation res = new Reservation(startDate, endDate);
                res.setClient(client);
                // Parcours la liste des component du panel contenant chaque chambre
                for (Component panel : paneCh.getComponents()) {
                    // les chambres sont de type panel
                    JPanel pane = (JPanel)panel;
                    Component[] panelC = pane.getComponents();
                    // Parcours la liste des component du panel contenant les infos de la chambre
                    // à la recherche du checkbox
                    for(Component component : panelC) {
                        if(component instanceof JCheckBox) {
                            JCheckBox cb = (JCheckBox)component;
                            if (cb.isSelected()) {
                                // Récupère les infos de la chambre
                                String num =  cb.getActionCommand();
                                Chambre chambre = hotel.searchChamber(num);
                                res.addChambre(chambre);
                            }
                        }
                    }
                }
                // message d'erreur si reservation contient aucune chambre
                if (res.listChambre.size() == 0) {

                }
                else { hotel.addRes(res); client.addRes(res);}
                System.out.println(hotel.listRes);
            }
        }
    }
}
