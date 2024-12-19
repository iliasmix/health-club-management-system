package gui.MemberDashboard;
import gui.*;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private final JTextArea displayArea;

    public DisplayPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        displayArea.setWrapStyleWord(true);
        displayArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setDisplayText(String text) {
        displayArea.setText(text);
    }

    public void appendText(String text) {
        displayArea.append(text + "\n");
    }

    public void clearDisplay() {
        displayArea.setText("");
    }
}

