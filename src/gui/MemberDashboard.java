package gui;

import modules.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDashboard {
    private Member member;

    public MemberDashboard(Member member) {
        this.member = member;
        createDashboard();
    }

    private void createDashboard() {
        // Frame setup
        JFrame frame = new JFrame("Member Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Buttons
        JButton viewNotificationsButton = new JButton("View Notifications");
        JButton addTrainingPlanButton = new JButton("Add Training Plan");
        JButton viewTrainingPlansButton = new JButton("View Training Plans");
        JButton viewScheduleButton = new JButton("View Schedule");
        JButton exitButton = new JButton("Exit");

        // Button actions
        viewNotificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // showNotifications();
            }
        });

        addTrainingPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrainingPlan();
            }
        });

        viewTrainingPlansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // showTrainingPlans();
            }
        });

        viewScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSchedule();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add buttons to panel
        panel.add(viewNotificationsButton);
        panel.add(addTrainingPlanButton);
        panel.add(viewTrainingPlansButton);
        panel.add(viewScheduleButton);
        panel.add(exitButton);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // private void showNotifications() {
    //     StringBuilder notifications = new StringBuilder();
    //     for (String notification : member.notifications) {
    //         notifications.append(notification).append("\n");
    //     }
    //     if (notifications.length() == 0) {
    //         notifications.append("No notifications.");
    //     }
    //     JOptionPane.showMessageDialog(null, notifications.toString(), "Notifications", JOptionPane.INFORMATION_MESSAGE);
    // }

    private void addTrainingPlan() {
        String plan = JOptionPane.showInputDialog(null, "Enter the training plan:", "Add Training Plan", JOptionPane.PLAIN_MESSAGE);
        if (plan != null && !plan.isEmpty()) {
            member.addTrainingPlan(plan);
            JOptionPane.showMessageDialog(null, "Training plan added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Training plan cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // private void showTrainingPlans() {
    //     StringBuilder trainingPlans = new StringBuilder();
    //     for (String plan : member.trainingPlans) {
    //         trainingPlans.append(plan).append("\n");
    //     }
    //     if (trainingPlans.length() == 0) {
    //         trainingPlans.append("No training plans available.");
    //     }
    //     JOptionPane.showMessageDialog(null, trainingPlans.toString(), "Training Plans", JOptionPane.INFORMATION_MESSAGE);
    // } 

    private void showSchedule() {
        String schedule = member.getSchedule();
        if (schedule == null || schedule.isEmpty()) {
            schedule = "No schedule is available.";
        }
        JOptionPane.showMessageDialog(null, schedule, "Schedule", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create a dummy Member instance for testing
        Member member = new Member("testUser", "password123", "001");
        member.setSchedule("Monday: 10 AM - 12 PM\nWednesday: 2 PM - 4 PM");
        member.receiveMessage("Welcome to the training program!");
        member.addTrainingPlan("Plan 1: Cardio for 30 minutes");
        
        // Launch the dashboard
        new MemberDashboard(member);
    }
}
