package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RadioButtonListener implements ActionListener {
    JButton button;
    public RadioButtonListener(JButton b) {
        button = b;
    }
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(true);
    }
}
