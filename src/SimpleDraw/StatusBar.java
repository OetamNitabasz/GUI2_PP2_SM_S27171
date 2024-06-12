package SimpleDraw;

import javax.swing.*;

import java.awt.*;

public class StatusBar extends JToolBar {
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();

    public StatusBar() {
        setLayout(new BorderLayout());
        add(label1, BorderLayout.WEST);
        add(label2, BorderLayout.EAST);
    }

    public void setMode(String text) {
        label1.setText(text);
    }

    public void setState(Status status) {
        label2.setText(status.name());
    }
}
