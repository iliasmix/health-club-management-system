package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modules.Coach;
import modules.Member;
import services.FileHandler;

public class CoachGUI {

    private Coach coach;

    public CoachGUI(Coach coach) {
        this.coach = coach;
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Coach Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(250, 250, 250));

        // Coach Details Panel
        JPanel coachDetailsPanel = new JPanel(new GridBagLayout());
        coachDetailsPanel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Coach Name:");
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        JTextField nameField = new JTextField(coach.getName(), 20);
        nameField.setEditable(false);
        JButton editCoachButton = new JButton("Edit Coach");

        gbc.gridx = 0; gbc.gridy = 0;
        coachDetailsPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        coachDetailsPanel.add(nameField, gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        coachDetailsPanel.add(editCoachButton, gbc);

        // Add "Message to Members"
        JLabel messageLabel = new JLabel("Message to Members:");
        JTextField messageField = new JTextField(20);
        JButton sendMessageButton = new JButton("Send Message");

        gbc.gridx = 0; gbc.gridy = 1;
        coachDetailsPanel.add(messageLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        coachDetailsPanel.add(messageField, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        coachDetailsPanel.add(sendMessageButton, gbc);

        mainPanel.add(coachDetailsPanel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new BorderLayout());

        // Left Buttons: View and Create Training Plans
        JPanel leftButtonsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton viewMembersButton = new JButton("View Members");
        JButton createTrainingPlanButton = new JButton("Create New Training Plan");
        leftButtonsPanel.add(viewMembersButton);
        leftButtonsPanel.add(createTrainingPlanButton);

        buttonsPanel.add(leftButtonsPanel, BorderLayout.WEST);
        JButton viewTrainingPlansButton = new JButton("View Training Plans");
        leftButtonsPanel.add(viewTrainingPlansButton);
        viewTrainingPlansButton.addActionListener(e -> openViewTrainingPlansDialog(frame));


        // Right Buttons: Save and Delete Coach
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveCoachButton = new JButton("Save Coach");
        JButton deleteCoachButton = new JButton("Delete Coach");
        rightButtonsPanel.add(saveCoachButton);
        rightButtonsPanel.add(deleteCoachButton);

        buttonsPanel.add(rightButtonsPanel, BorderLayout.EAST);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Action Listeners
        sendMessageButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                Coach.sendMessageToAllMembers(coach.getID(), message);
                JOptionPane.showMessageDialog(frame, "Message sent successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Message cannot be empty.");
            }
        });

        createTrainingPlanButton.addActionListener(e -> openCreateTrainingPlanDialog(frame));

        viewMembersButton.addActionListener(e -> openViewMembersDialog(frame));

        saveCoachButton.addActionListener(e -> {
            try {
                coach.saveCoach();
                JOptionPane.showMessageDialog(frame, "Coach saved successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving coach: " + ex.getMessage());
            }
        });

        deleteCoachButton.addActionListener(e -> {
            try {
                coach.deleteCoach();
                JOptionPane.showMessageDialog(frame, "Coach deleted successfully.");
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error deleting coach: " + ex.getMessage());
            }
        });

        editCoachButton.addActionListener(e -> openEditCoachDialog(frame));
    }

    private void openCreateTrainingPlanDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Create New Training Plan", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Labels and Fields for Training Plan Details
        JLabel scheduleLabel = new JLabel("Schedule:");
        scheduleLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        JTextField scheduleField = new JTextField(20);

        JLabel weeksLabel = new JLabel("Number of Weeks:");
        weeksLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        JComboBox<Integer> weeksComboBox = new JComboBox<>();
        for (int i = 1; i <= 52; i++) {
            weeksComboBox.addItem(i);
        }

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<Integer> dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });
        JComboBox<Integer> yearComboBox = new JComboBox<>();
        for (int i = 2023; i <= 2030; i++) {
            yearComboBox.addItem(i);
        }
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);

        // Add components to the dialog
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(scheduleLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(scheduleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(weeksLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(weeksComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(startDateLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(datePanel, gbc);

        // Buttons for Save and Cancel
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);

        // Action Listeners
        saveButton.addActionListener(e -> {
            String schedule = scheduleField.getText();
            int weeks = (int) weeksComboBox.getSelectedItem();
            int day = (int) dayComboBox.getSelectedItem();
            int month = monthComboBox.getSelectedIndex() + 1; // 0-based indexing
            int year = (int) yearComboBox.getSelectedItem();

            String startDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

            if (!schedule.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Training Plan Created:\nSchedule: " + schedule + "\nWeeks: " + weeks + "\nStart Date: " + startDate);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Schedule cannot be empty.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
    private void openViewTrainingPlansDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "View Training Plans", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));

        DefaultListModel<String> trainingPlanListModel = new DefaultListModel<>();
        ArrayList<modules.TrainingPlan> trainingPlans = coach.getTrainingPlans();

        for (modules.TrainingPlan plan : trainingPlans) {
            String trainingPlanDetails = "Schedule ID: " + plan.getScheduleId() +
                    ", Schedule: " + plan.getSchedule() +
                    ", Start Date: " + plan.getStartDate() +
                    ", End Date: " + plan.getEndDate();
            trainingPlanListModel.addElement(trainingPlanDetails);
        }

        JList<String> trainingPlanList = new JList<>(trainingPlanListModel);
        JScrollPane scrollPane = new JScrollPane(trainingPlanList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }



    private void openViewMembersDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "View Members", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));

        DefaultListModel<String> memberListModel = new DefaultListModel<>();
        ArrayList<Member> members = FileHandler.loadMemberData();
        for (Member member : members) {
            if (member.getCoachId().equals(coach.getID())) {
                memberListModel.addElement(member.getID() + " - " + member.getUsername());
            }
        }
        JList<String> memberList = new JList<>(memberListModel);
        JScrollPane scrollPane = new JScrollPane(memberList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton removeMemberButton = new JButton("Remove Member");
        removeMemberButton.addActionListener(e -> {
            String selectedMember = memberList.getSelectedValue();
            if (selectedMember != null) {
                String memberId = selectedMember.split(" - ")[0];
                FileHandler.deleteMember(memberId);
                memberListModel.removeElement(selectedMember);
                JOptionPane.showMessageDialog(dialog, "Member removed successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a member to remove.");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(removeMemberButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void openEditCoachDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Edit Coach", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Select an action:");
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        JButton setNameButton = new JButton("Set New Name");
        JButton setPasswordButton = new JButton("Set New Password");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(label, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(setNameButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(setPasswordButton, gbc);

        dialog.add(panel);

        // Action Listeners
        setNameButton.addActionListener(e -> {
            dialog.dispose();
            openSetNewNameDialog(parentFrame);
        });

        setPasswordButton.addActionListener(e -> {
            dialog.dispose();
            openSetNewPasswordDialog(parentFrame);
        });

        dialog.setVisible(true);
    }

    private void openSetNewNameDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Set New Name", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Enter New Name:");
        JTextField nameField = new JTextField(20);
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(nameField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);

        saveButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            if (!newName.isEmpty()) {
                coach.setName(newName);
                JOptionPane.showMessageDialog(dialog, "Name updated successfully.");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Name cannot be empty.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void openSetNewPasswordDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Set New Password", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Enter New Password:");
        JTextField passwordField = new JPasswordField(20);
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);

        saveButton.addActionListener(e -> {
            String newPassword = passwordField.getText().trim();
            if (!newPassword.isEmpty()) {
                coach.setPassword(newPassword);
                JOptionPane.showMessageDialog(dialog, "Password updated successfully.");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Password cannot be empty.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Coach coach = new Coach("JohnDoe", "password123");
            new CoachGUI(coach);
        });
    }
}
