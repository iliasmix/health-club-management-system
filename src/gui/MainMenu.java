package gui;

import modules.Admin;
import modules.Coach;
import modules.Member;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private final JPanel mainPanel;
    private final JLabel titleLabel;
    private final JButton adminButton;
    private final JButton coachButton;
    private final JButton memberButton;
    private final JButton exitButton;

    public MainMenu() {
        // Setup frame
        setTitle("Health Club Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize components
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        titleLabel = new JLabel("Health Club Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        adminButton = createStyledButton("Admin Login");
        coachButton = createStyledButton("Coach Login");
        memberButton = createStyledButton("Member Login");
        exitButton = createStyledButton("Exit");

        setupLayout();
        setupListeners();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    private void setupLayout() {
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(adminButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(coachButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(memberButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(exitButton);

        add(mainPanel);
    }

    private void setupListeners() {
        adminButton.addActionListener(e -> showLoginDialog("Admin"));
        coachButton.addActionListener(e -> showLoginDialog("Coach"));
        memberButton.addActionListener(e -> showLoginDialog("Member"));
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void showLoginDialog(String userType) {
        JDialog loginDialog = new JDialog(this, userType + " Login", true);
        loginDialog.setLayout(new BorderLayout());
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(this);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        loginDialog.add(loginPanel, BorderLayout.CENTER);
        loginDialog.add(buttonPanel, BorderLayout.SOUTH);
        loginDialog.add(statusLabel, BorderLayout.NORTH);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (handleLogin(userType, username, password)) {
                loginDialog.dispose();
            } else {
                statusLabel.setText("Invalid username or password");
            }
        });

        cancelButton.addActionListener(e -> loginDialog.dispose());

        loginDialog.setVisible(true);
    }

    private boolean handleLogin(String userType, String username, String password) {
        if (!modules.User.login(username, password)) {
            return false;
        }

        switch (userType) {
            case "Admin":
                Admin admin = new Admin(username, password);
                SwingUtilities.invokeLater(() -> {
                    AdminDashboard dashboard = new AdminDashboard(admin);
                    dashboard.setVisible(true);
                });
                break;
            case "Coach":
                Coach coach = new Coach(username, password);
                SwingUtilities.invokeLater(() -> {
                    CoachDashboard dashboard = new CoachDashboard(coach);
                    dashboard.setVisible(true);
                });
                break;
            case "Member":
                Member member = new Member(username, password);
                SwingUtilities.invokeLater(() -> {
                    MemberDashboard dashboard = new MemberDashboard(member);
                    dashboard.setVisible(true);
                });
                break;
        }
        this.setVisible(false);
        return true;
    }
}
