package gui;

import javax.swing.*;
import java.awt.*;
import modules.*;
import gui.MemberDashboard.MemberDashboard;

public class MainMenu extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> initializeGUI());
    }

    private static void initializeGUI() {
        JFrame frame = new JFrame("Health Club Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to Health Club Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton adminLoginButton = new JButton("Admin Login");
        JButton coachLoginButton = new JButton("Coach Login");
        JButton memberLoginButton = new JButton("Member Login");
        JButton exitButton = new JButton("Exit");

        // Style buttons
        styleButton(adminLoginButton);
        styleButton(coachLoginButton);
        styleButton(memberLoginButton);
        styleButton(exitButton);

        panel.add(welcomeLabel);
        panel.add(adminLoginButton);
        panel.add(coachLoginButton);
        panel.add(memberLoginButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);

        // Admin Login Action
        adminLoginButton.addActionListener(e -> {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(frame, loginPanel, "Admin Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Admin admin = new Admin(username, password);
                if (User.login(username, password)) {
                    new AdminDashboard(admin);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login credentials.");
                }
            }
        });

        // Coach Login Action
        coachLoginButton.addActionListener(e -> {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(frame, loginPanel, "Coach Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Coach coach = new Coach(username, password);
                if (User.login(username, password)) {
                    new CoachGUI(coach);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login credentials.");
                }
            }
        });

        // Member Login Action
        memberLoginButton.addActionListener(e -> {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(frame, loginPanel, "Member Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Member member = new Member(username, password);
                if (User.login(username, password)) {
                    new MemberDashboard(member);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login credentials.");
                }
            }
        });

        // Exit Action
        exitButton.addActionListener(e -> frame.dispose());
    }

    private static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
    }
}
