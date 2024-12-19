package gui.MemberDashboard;

import gui.*;
import modules.Member;

import javax.swing.*;
import java.awt.*;

public class MemberDashboard extends JFrame {
    private final Member member;
    private final DisplayPanel displayPanel;
    private final ActionPanel actionPanel;

    public MemberDashboard(Member member) {
        this.member = member;
        this.displayPanel = new DisplayPanel();
        this.actionPanel = new ActionPanel();

        setupFrame();
        setupComponents();
        setupListeners();
    }

    private void setupFrame() {
        setTitle("Member Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void setupComponents() {
        setLayout(new BorderLayout(10, 10));

        // Add header
        add(new DashboardHeader(member.getUsername()), BorderLayout.NORTH);

        // Add action panel (buttons)
        add(actionPanel, BorderLayout.WEST);

        // Add display panel
        add(displayPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        // Subscription button listener
        actionPanel.addSubscriptionListener(e -> {
            try {
                member.viewSubscriptionEndDate(member.getID());
                displayPanel.setDisplayText("Fetching subscription details...\n");
            } catch (Exception ex) {
                displayPanel.setDisplayText("Error: " + ex.getMessage());
            }
        });

        // Coach schedule button listener
        actionPanel.addCoachScheduleListener(e -> {
            try {
                member.viewCoachAndSchedule(member.getID());
                displayPanel.setDisplayText("Fetching coach and schedule details...\n");
            } catch (Exception ex) {
                displayPanel.setDisplayText("Error: " + ex.getMessage());
            }
        });

        // Logout button listener
        actionPanel.addLogoutListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }
}