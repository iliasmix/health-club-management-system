package gui.MemberDashboard;

import javax.swing.*;
import java.awt.*;
import modules.Member;

public class MemberDashboard extends JFrame {
    private Member member;
    private JTextArea outputArea;

    public MemberDashboard(Member member) {
        this.member = member;

        // Initialize frame
        setTitle("Health Club Management System - Member Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + member.getUsername() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        createAndAddButtons(buttonPanel);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(600, 150));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private void createAndAddButtons(JPanel buttonPanel) {
        String[] buttonLabels = {
                "View Subscription End Date",
                "View Coach and Schedule",
                "Logout"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            styleButton(button);
            button.addActionListener(e -> handleButtonClick(label));
            buttonPanel.add(button);
        }
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(240, 240, 240));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
    }

    private void handleButtonClick(String action) {
        switch (action) {
            case "View Subscription End Date":
                viewSubscriptionEndDate();
                break;
            case "View Coach and Schedule":
                viewCoachAndSchedule();
                break;
            case "Logout":
                dispose();
                break;
        }
    }

    private void viewSubscriptionEndDate() {
        try {
            outputArea.setText(""); // Clear previous output
            outputArea.append("=== Subscription Information ===\n");
            member.viewSubscriptionEndDate(member.getID());
            outputArea.append("=============================\n");
        } catch (Exception e) {
            showError("Error retrieving subscription information: " + e.getMessage());
        }
    }

    private void viewCoachAndSchedule() {
        try {
            outputArea.setText(""); // Clear previous output
            outputArea.append("=== Coach and Schedule Information ===\n");
            member.viewCoachAndSchedule(member.getID());
            outputArea.append("==================================\n");
        } catch (Exception e) {
            showError("Error retrieving coach and schedule information: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}