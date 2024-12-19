package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import services.FileHandler;
import services.NotificationSystem;
import modules.Coach;

public class CoachGUI {

    private Coach coach;

    public CoachGUI(Coach coach) {
        this.coach = coach;
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Coach Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Coach Name:");
        JTextField nameField = new JTextField(coach.getName());

        JLabel scheduleLabel = new JLabel("Schedule:");
        JTextField scheduleField = new JTextField();

        JLabel startDateLabel = new JLabel("Start Date (yyyy-mm-dd):");
        JTextField startDateField = new JTextField();

        JLabel weeksLabel = new JLabel("Number of Weeks:");
        JTextField weeksField = new JTextField();

        JLabel messageLabel = new JLabel("Message to Members:");
        JTextField messageField = new JTextField();

        JButton setScheduleButton = new JButton("Set Schedule");
        JButton sendMessageButton = new JButton("Send Message");
        JButton saveCoachButton = new JButton("Save Coach");
        JButton deleteCoachButton = new JButton("Delete Coach");
        JButton updateNameButton = new JButton("Update Name");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(scheduleLabel);
        panel.add(scheduleField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(weeksLabel);
        panel.add(weeksField);
        panel.add(messageLabel);
        panel.add(messageField);

        panel.add(setScheduleButton);
        panel.add(sendMessageButton);
        panel.add(saveCoachButton);
        panel.add(deleteCoachButton);
        panel.add(updateNameButton);

        frame.add(panel);
        frame.setVisible(true);

        // Action Listeners
        setScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String schedule = scheduleField.getText();
                    LocalDate startDate = LocalDate.parse(startDateField.getText());
                    int weeks = Integer.parseInt(weeksField.getText());

                    coach.setSchedulesForAllMembers(schedule, startDate, weeks);
                    JOptionPane.showMessageDialog(frame, "Schedule set for all members.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error setting schedule: " + ex.getMessage());
                }
            }
        });

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String message = messageField.getText();
                    Coach.sendMessageToAllMembers(coach.getID(), message);
                    JOptionPane.showMessageDialog(frame, "Message sent to all members.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error sending message: " + ex.getMessage());
                }
            }
        });

        saveCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coach.saveCoach();
                    JOptionPane.showMessageDialog(frame, "Coach saved successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving coach: " + ex.getMessage());
                }
            }
        });

        deleteCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coach.deleteCoach();
                    JOptionPane.showMessageDialog(frame, "Coach deleted successfully.");
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error deleting coach: " + ex.getMessage());
                }
            }
        });

        updateNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newName = nameField.getText();
                    coach.setName(newName);
                    JOptionPane.showMessageDialog(frame, "Coach name updated successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating name: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Coach coach = new Coach("JohnDoe", "password123");
            new CoachGUI(coach);
        });
    }
}
