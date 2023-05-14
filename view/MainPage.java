package view;
import controler.*;
import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainPage extends JFrame {
  JMenuItem e1, e2, e3, e4;
  JLabel titre;
  public static JLabel profit;
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
    profit = new JLabel("Profit : 0");
    profit.setBorder(new EmptyBorder(0,0,0,10));
    // Ajoute le menu et les labels au barre de menu
    menubar.add(menu);
    menubar.add(Box.createHorizontalGlue());
    menubar.add(titre);
    menubar.add(Box.createHorizontalGlue());
    menubar.add(profit);

    // Récupère le panel de base et ajoute le panel enregistrements
    Container c = this.getContentPane();
    EnregistrementsView p1 = new EnregistrementsView(hotel);
    c.add(p1);
    p1.setBounds(0, 0, 785, 440);

    // Ajoute la barre de menu au frame
    this.setJMenuBar(menubar);
    this.setTitle("Hotel");
    this.setLayout(null);
    this.pack();
    this.setSize(800,500);
    this.setResizable(false);
    // Centre la fenêtre
    this.setLocationRelativeTo (null);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Creation dune instance de gestionnaire d'evenement
    MenuControl ctr = new MenuControl(hotel, titre, c);
    e1.addActionListener(ctr);
    e2.addActionListener(ctr);
    e3.addActionListener(ctr);
    e4.addActionListener(ctr);
  }
}