package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class radioButtonListener implements ActionListener {
    JButton button;
    public radioButtonListener(JButton b) {
        button = b;
    }
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(true);
    }
}
