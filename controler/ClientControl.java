package controler;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ClientControl implements ActionListener{
    JPanel panel;

    public ClientControl(JPanel p) { panel=p; }

    public void actionPerformed(ActionEvent e) {
        CardLayout deck = (CardLayout)panel.getLayout();
        String name = ((JButton)e.getSource()).getText();
        deck.show(panel,name);
    }
}
