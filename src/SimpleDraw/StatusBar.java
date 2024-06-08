package SimpleDraw;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();

    public StatusBar() {
        setLayout(new BorderLayout());
        add(label1, BorderLayout.WEST);
        add(label2, BorderLayout.EAST);
        setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4, 4, 4, 4)));

    }

    public void setMode(String text) {
        label1.setText(text);
    }

    public void setState(Status status) {
        label2.setText(status.name());
    }
}
