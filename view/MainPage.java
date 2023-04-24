package view;
import controler.*;
import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class MainPage extends JFrame {
  JMenuItem e1, e2, e3, e4;
  JLabel titre, gain;
  Hotel hotel;
  MainPage(Hotel h) {
    hotel = h;
    // Crée la barre de menu
    JMenuBar menubar = new JMenuBar();
    // menubar.setBackground(new Color(14, 40, 63));
    // Crée le menu
    JMenu menu = new JMenu("Menu");
    // Crée les éléments du menu
    e1 = new JMenuItem("Enregistrements");
    e2 = new JMenuItem("Clients");
    e3 = new JMenuItem("Chambres");
    e4 = new JMenuItem("Options");
    // Ajoute les éléments au menu
    menu.add(e1);
    menu.addSeparator();
    menu.add(e2);
    menu.add(e3);
    menu.addSeparator();
    menu.add(e4);
    // Crée les label
    titre = new JLabel("Enregistrements");
    titre.setForeground(Color.red);
    gain = new JLabel("Gain : 0");
    gain.setBorder(new EmptyBorder(0,0,0,10));
    // Ajoute le menu et les labels au barre de menu
    menubar.add(menu);
    menubar.add(Box.createHorizontalGlue());
    menubar.add(titre);
    menubar.add(Box.createHorizontalGlue());
    menubar.add(gain);

    // Ajoute la barre de menu au frame
    this.setJMenuBar(menubar);
    this.setTitle("Hotel");
    this.setLayout(null);
    this.pack();
    this.setVisible(true);
    this.setSize(600,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Crée le container principal de la page (CardLayout)
    Container c = this.getContentPane();
    c.setLayout(new CardLayout());
    // Crée les panels (cards)
    //GestionEnregistrements p1 = new GestionEnregistrements();
    //GestionClient p2 = new GestionClient();
    //GestionChambre p3 = new GestionChambre();
    OptionsView p4 = new OptionsView(hotel);

    // Ajoute les cards au container principal
    //c.add("Enregistrements", p1);
    //c.add("Clients", p2);
    //c.add("Chambres", p3);
    c.add("Options", p4);

    // Creation dune instance de gestionnaire d'evenement
    MenuControl ctr = new MenuControl(titre, c);
    e1.addActionListener(ctr);
    e2.addActionListener(ctr);
    e3.addActionListener(ctr);
    e4.addActionListener(ctr);
  }
  public static void main(String args[]) {

  }
}