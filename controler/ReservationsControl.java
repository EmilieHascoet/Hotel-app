package controler;
import model.*;
import view.*;

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
import view.ReservationsView;

public class ReservationsControl implements ActionListener {
    Hotel hotel;
    Client client;
    JDialog dialog;
    JFrame frame;
    JPanel paneCh;
    JCheckBox checkBox;
    Vector<Chambre> listChDispo, listChSelected;
    Vector<Option> listFiltre;
    JSlider slider;
    int nbrPlaces;
    JDateChooser startDateChooser, endDateChooser;
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
    // Constructeur choix date
    public ReservationsControl(Hotel h, JPanel p, Vector<Chambre> listCh, Vector<Option> listF, int places, JDateChooser sd, JDateChooser ed) {
        hotel = h; paneCh = p; listChDispo = listCh; listFiltre = listF; nbrPlaces = places;
        startDateChooser = sd; endDateChooser = ed;
    }
    // Constructeur reserver les chambres
    public ReservationsControl(Hotel h, Client c, JPanel p, JDialog d) {
        hotel = h; client = c; paneCh = p; dialog = d;
    }

    // Ajoute les chambres filtré au panel chambre
    public void addChamberToView(Vector<Chambre> listChambres, JPanel panelCh) {
        for(Chambre ch : listChambres) {
            // panel chambre
            JPanel panel = new JPanel();
            Border border = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);
            EmptyBorder padding = new EmptyBorder(7, 10, 7, 10);
            panel.setLayout(new GridLayout(7, 1));
            panel.setBorder(BorderFactory.createCompoundBorder(border, padding));
            // Labels
            JLabel label = new JLabel("Chambre n° : " + ch.num);
            JLabel label1 = new JLabel("Etage : " + ch.etage);
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
            panel.add(label1);
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
                ReservationsView.startDate = startDateChooser.getDate();
                ReservationsView.endDate = endDateChooser.getDate();

                // update le calendrier à hier pour le test de la date déjà passée
                Calendar calendrier = Calendar.getInstance();
                calendrier.add(Calendar.DATE, -1);
    
                // Affiche un message d'erreur si la date de début excède celle de fin
                if (ReservationsView.startDate.compareTo(ReservationsView.endDate) >= 0) {
                    JOptionPane.showMessageDialog(frame,
                    "Veuillez choisir une date de début inférieur à celle de fin.",
                    "Erreur de date !",
                    JOptionPane.ERROR_MESSAGE);
                }
                // Affiche un message d'erreur si la date est déjà passé
                else if (ReservationsView.startDate.compareTo(calendrier.getTime()) < 0) {
                    SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
                    JOptionPane.showMessageDialog(frame,
                    "Veuillez choisir une date futur.\nLe " + formatter.format(ReservationsView.startDate) + " est déjà passé",
                    "Erreur de date !",
                    JOptionPane.ERROR_MESSAGE);
                }
                // Actualise les chambres dispo si il n'y a aucune erreur de date
                else {
                    listChDispo = hotel.searchChamberDispo(ReservationsView.startDate, ReservationsView.endDate);
                    filtreChamber();
                }
            }
            // Reserver chambre
            else {
                // Crée une réservation
                Reservation res = new Reservation(ReservationsView.startDate, ReservationsView.endDate);
                // Chaine de caractère des numéros de chambre
                Vector<String> chambresStr = new Vector<String>();
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
                                chambresStr.add("Chambre n°"+ num);
                            }
                        }
                    }
                }
                // message d'erreur si reservation contient aucune chambre
                if (res.listChambre.size() == 0) {
                    JOptionPane.showMessageDialog(frame,
                    "Vous n'avez selectionné aucune chambre",
                    "Erreur de reservation !",
                    JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // ajout de la reservation au model
                    hotel.addRes(res, client);
                    // Change le format des dates pour l'affichage
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy");
                    String dateDebStr = formatter.format(res.dateDeb);
                    String dateFinStr = formatter.format(res.dateFin);
                    
                    String message = "Détails de la réservation :\nClient : " 
                    + client.nom + " " + client.prenom + 
                    "\nDate : du " + dateDebStr + " au " + dateFinStr + 
                    "\nChambres : " + chambresStr + "\n\nLa caution s'élève à " + res.getCaution() + "€" +
                    "\nVous pouvez annuler la réservation jusqu'à la dernière minute et vous serez rembourser." +
                    "\nSouhaitez vous payer la caution ou annuler la réservation ?";
                    
                    int optionType = JOptionPane.YES_NO_OPTION;
                    int messageType = JOptionPane.QUESTION_MESSAGE;
                    Object[] options = {"Payer", "Annuler"};
                    String title = "Confirmation";
                    
                    int userChoice = JOptionPane.showOptionDialog(null, message, title, optionType, messageType,
                    null, options, options[0]);
                    
                    if (userChoice == JOptionPane.YES_OPTION) {
                        // Ajout de l'argent de la caution à l'hotel
                        hotel.credit(res.getCaution());
                        MainPage.profit.setText("Profit : " + hotel.getProfit());
                        // ferme la fenêtre
                        dialog.dispose();
                    } else {
                        // Supprime la reservation
                        hotel.suppRes(res);
                    }
                }
            }
        }
    }
}