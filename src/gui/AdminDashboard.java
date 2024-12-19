package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modules.Admin;
import modules.Member;
import modules.Coach;

public class AdminDashboard extends JFrame {
    private Admin admin;
    private JTextField usernameField, passwordField, idField;
    private JTextArea outputArea;

    public AdminDashboard(Admin admin) {
        this.admin = admin;

        // Initialize frame
        setTitle("Health Club Management System - Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input panel for user credentials
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("ID (for operations):"));
        idField = new JTextField();
        inputPanel.add(idField);

        // Create buttons panel with improved layout
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        createAndAddButtons(buttonPanel);

        // Create output area with improved visibility
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(800, 200));

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private void createAndAddButtons(JPanel buttonPanel) {
        String[] buttonLabels = {
                "Add Member",
                "Remove Member",
                "Update Member",
                "Add Coach",
                "Remove Coach",
                "Update Coach",
                "Assign Member to Coach",
                "Search Members",
                "List All Members",
                "List All Coaches",
                "Generate Reports",
                "Logout"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(new Color(240, 240, 240));
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));

            button.addActionListener(e -> handleButtonClick(label));
            buttonPanel.add(button);
        }
    }

    private void handleButtonClick(String action) {
        switch (action) {
            case "Add Member":
                addMember();
                break;
            case "Remove Member":
                removeMember();
                break;
            case "Update Member":
                updateMember();
                break;
            case "Add Coach":
                addCoach();
                break;
            case "Remove Coach":
                removeCoach();
                break;
            case "Update Coach":
                updateCoach();
                break;
            case "Assign Member to Coach":
                assignMemberToCoach();
                break;
            case "Search Members":
                searchMembers();
                break;
            case "List All Members":
                listMembers();
                break;
            case "List All Coaches":
                listCoaches();
                break;
            case "Generate Reports":
                generateReports();
                break;
            case "Logout":
                dispose();
                break;
        }
    }

    private void addMember() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Member",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                Member member = new Member(username, password);
                admin.addMember(member);
                outputArea.append(
                        "Member added successfully!\nUsername: " + username + "\nMember ID: " + member.getID() + "\n");
                clearOutputAfterDelay(3000);
            } else {
                showError("Username and password cannot be empty");
            }
        }
    }

    private void updateMember() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();

        panel.add(new JLabel("Member ID:"));
        panel.add(idField);
        panel.add(new JLabel("New Username:"));
        panel.add(newUsernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Member",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String newUsername = newUsernameField.getText();
            String newPassword = new String(newPasswordField.getPassword());

            if (!id.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty()) {
                admin.updateMember(id, newUsername, newPassword);
                outputArea.append("Member updated successfully!\nID: " + id + "\nNew Username: " + newUsername + "\n");
                clearOutputAfterDelay(3000);
            } else {
                showError("All fields are required");
            }
        }
    }

    private void removeMember() {
        String id = JOptionPane.showInputDialog(this, "Enter Member ID to remove:");
        if (id != null && !id.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove member with ID: " + id + "?",
                    "Confirm Removal", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                admin.removeMember(id);
                outputArea.append("Member removed successfully!\nID: " + id + "\n");
                clearOutputAfterDelay(3000);
            }
        } else if (id != null) {
            showError("Member ID cannot be empty");
        }
    }

    private void addCoach() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Coach",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                Coach coach = new Coach(username, password);
                admin.addCoach(coach);
                outputArea.append(
                        "Coach added successfully!\nUsername: " + username + "\nCoach ID: " + coach.getID() + "\n");
                clearOutputAfterDelay(3000);
            } else {
                showError("Username and password cannot be empty");
            }
        }
    }

    private void updateCoach() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();

        panel.add(new JLabel("Coach ID:"));
        panel.add(idField);
        panel.add(new JLabel("New Username:"));
        panel.add(newUsernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Coach",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String newUsername = newUsernameField.getText();
            String newPassword = new String(newPasswordField.getPassword());

            if (!id.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty()) {
                admin.updateCoach(id, newUsername, newPassword);
                outputArea.append("Coach updated successfully!\nID: " + id + "\nNew Username: " + newUsername + "\n");
                clearOutputAfterDelay(3000);
            } else {
                showError("All fields are required");
            }
        }
    }

    private void removeCoach() {
        String id = JOptionPane.showInputDialog(this, "Enter Coach ID to remove:");
        if (id != null && !id.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove coach with ID: " + id + "?",
                    "Confirm Removal", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                admin.removeCoach(id);
                outputArea.append("Coach removed successfully!\nID: " + id + "\n");
                clearOutputAfterDelay(3000);
            }
        } else if (id != null) {
            showError("Coach ID cannot be empty");
        }
    }

    private void assignMemberToCoach() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField memberIdField = new JTextField();
        JTextField coachIdField = new JTextField();
        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);
        panel.add(new JLabel("Coach ID:"));
        panel.add(coachIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Assign Member to Coach",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String memberId = memberIdField.getText();
            String coachId = coachIdField.getText();

            if (!memberId.isEmpty() && !coachId.isEmpty()) {
                admin.assignMemberToCoach(memberId, coachId);
                outputArea.append("Assignment successful!\nMember ID: " + memberId + "\nCoach ID: " + coachId + "\n");
                clearOutputAfterDelay(3000);
            } else {
                showError("Both Member ID and Coach ID are required");
            }
        }
    }

    private void searchMembers() {
        String keyword = JOptionPane.showInputDialog(this, "Enter search keyword:");
        if (keyword != null && !keyword.isEmpty()) {
            ArrayList<Member> members = admin.searchMembers(keyword);
            outputArea.append("\n=== Search Results for '" + keyword + "' ===\n");
            if (members.isEmpty()) {
                outputArea.append("No members found.\n");
            } else {
                for (Member member : members) {
                    outputArea.append(member.toString() + "\n");
                }
            }
            outputArea.append("============================\n");
        }
    }

    private void listMembers() {
        ArrayList<Member> members = admin.listMembers();
        outputArea.append("\n=== All Members ===\n");
        if (members.isEmpty()) {
            outputArea.append("No members found.\n");
        } else {
            for (Member member : members) {
                outputArea.append(member.toString() + "\n");
            }
        }
        outputArea.append("=================\n");
    }

    private void listCoaches() {
        outputArea.append("\n=== Loading Coach Data ===\n");
        try {
            services.FileHandler.loadCoachData();
            outputArea.append("Coach data loaded successfully!\n");
        } catch (Exception e) {
            showError("Error loading coach data: " + e.getMessage());
        }
        outputArea.append("========================\n");
    }

    private void generateReports() {
        try {
            admin.generateReports();
            outputArea.append("\n=== Reports Generated ===\n");
            outputArea.append("Reports have been generated successfully!\n");
            outputArea.append("======================\n");
        } catch (Exception e) {
            showError("Error generating reports: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearOutputAfterDelay(int milliseconds) {
        Timer timer = new Timer(milliseconds, e -> outputArea.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
}