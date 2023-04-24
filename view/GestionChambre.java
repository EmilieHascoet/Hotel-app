package view;

import javax.swing.*;
import java.awt.*;
import controler.*;

public class GestionChambre extends JPanel{
    JButton consult = new JButton("Consulter");
    JButton modif = new JButton("Modifier");
    JButton add = new JButton("Ajouter");

    //Panel Ajouter
    JLabel nbrPlace = new JLabel("Nombre de places : ");
    JLabel etage = new JLabel("Etage : ");
    JLabel prix = new JLabel("Prix : ");
    JTextField nbrP = new JTextField();
    JTextField etag = new JTextField();
    JTextField pri = new JTextField();
    JButton add2 = new JButton("Ajouter !");

    //Panel Modifier
    JLabel num = new JLabel("Numéro de la chambre à modifier : ");
    JTextField nu = new JTextField();

    public GestionChambre() {
        this.setLayout(new GridLayout(1, 2, 20, 0));

        //Buttons Consulter, Modifier, Ajouter
        JPanel p1 = new JPanel(new GridLayout(3,1));

        p1.add(consult);
        p1.add(modif);
        p1.add(add);
        this.add(p1);

        //Fenetre qui s'ouvre quand on clique sur Ajouter (faire la meme chose pour les deux autres)

        JPanel ajout = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5,5);  // Pading
        c.fill = GridBagConstraints.BOTH; // étend l'élément dans les deux directions
        c.weightx = 1.0; // agrandit horizontalement
        c.weighty = 1.0; // agrandit verticalement

        c.gridx = 0;
        c.gridy = 0;
        ajout.add(nbrPlace,c);
        c.gridy = 1;
        ajout.add(etage,c);
        c.gridy = 2;
        ajout.add(prix,c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;       //Largeur des l'élément
        ajout.add(nbrP,c);
        c.gridy = 1;
        ajout.add(etag,c);
        c.gridy = 2;
        ajout.add(pri,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;        //Pour que ça prenne les 2 cases
        ajout.add(add2,c);
        
        //Fenetre qui s'ouvre quand on clique sur Modifier

        JPanel modifier = new JPanel(new GridLayout(4,2,10,10));
        modifier.add(num);
        modifier.add(nu);

        //Pour qu'ils se superposent au même endroit
        JPanel content = new JPanel(new CardLayout());
        
        content.add(ajout,"ajout");        //Nom pour pouvoir spécifier dans show()
        content.add(modifier,"modifier");

        //Controler
        Test ctr = new Test(content);
        add.addActionListener(ctr);
        modif.addActionListener(ctr);
                
        
        this.add(content);

        this.setVisible(true);
    }
}
