package controler;
import java.awt.event.*;
import javax.swing.*;


public class Test implements ActionListener{
    JPanel panel;

    public Test(JPanel p) { panel=p; }

    public void actionPerformed(ActionEvent e) {
        //if (((JButton)e.getSource()).getText().equals("Ajouter")) { panel.setVisible(true); }

        //Comme on va faire la meme chose pour les trois bouttons je me dis qu'on n'est pas obligé de mettre un test
        panel.setVisible(true);
    }
}
