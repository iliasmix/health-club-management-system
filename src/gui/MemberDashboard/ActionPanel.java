package gui.MemberDashboard;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private final JButton viewSubscriptionBtn;
    private final JButton viewCoachScheduleBtn;
    private final JButton logoutBtn;

    public ActionPanel() {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        viewSubscriptionBtn = new JButton("View Subscription End Date");
        viewCoachScheduleBtn = new JButton("View Coach and Schedule");
        logoutBtn = new JButton("Logout");

        add(viewSubscriptionBtn);
        add(viewCoachScheduleBtn);
        add(logoutBtn);
    }

    public void addSubscriptionListener(ActionListener listener) {
        viewSubscriptionBtn.addActionListener(listener);
    }

    public void addCoachScheduleListener(ActionListener listener) {
        viewCoachScheduleBtn.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }
}