package gui.MemberDashboard;
import gui.*;
import javax.swing.*;
import java.awt.*;

public class DashboardHeader extends JPanel {
    private final JLabel welcomeLabel;

    public DashboardHeader(String username) {
        welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(welcomeLabel);
    }
}