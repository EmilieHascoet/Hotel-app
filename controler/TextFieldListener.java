package controler;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextFieldListener implements DocumentListener {
    JTextField textField1, textField2, textField3;
    JButton button;

    public TextFieldListener(JTextField t1, JTextField t2, JTextField t3, JButton b) { textField1=t1; textField2=t2; textField3=t3; button=b; }


    public void insertUpdate(DocumentEvent e) {
        updateText(e);
    }

    public void removeUpdate(DocumentEvent e) {
        updateText(e);
    }

    public void changedUpdate(DocumentEvent e) {
        // Ne sera pas appelé pour les champs de texte non éditables
    }

    private void updateText(DocumentEvent e) {
        if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty()) {
            button.setEnabled(false);
        }
        else {
            button.setEnabled(true);
        }
    }
}

