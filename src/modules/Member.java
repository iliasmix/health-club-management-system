package modules;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Member extends User {
    private Coach coach;
    private String schedule;
    private Subscription subscription;
    private ArrayList<String> notifications; // why if there is file handling ?!
    private ArrayList<String> trainingPlans;

    // Constructor
    // Add coach
    public Member(String username, String password) {
        super(username, password);
        this.notifications = new ArrayList<>();
        this.trainingPlans = new ArrayList<>();
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    // Subscription methods
    public void viewSubscriptionDetails() {
        System.out.print("Your start date is: ");
        subscription.getStartDate();

        // --------------------
        System.out.print("Your end date is: ");
        subscription.getEndDate();

        if (subscription.checkIfExpired()) {
            System.out.println("Your subscription is expired!");
        }
        // will add more deatils inshallah..

    }

    // Schedule and training plan methods
    public void viewSchedule() {
        if (schedule != null && !schedule.isEmpty()) {
            System.out.println("Your current schedule: " + schedule);
        } else {
            System.out.println("No schedule is available.");
        }
    }

    public void viewTrainingPlan() {
        if (trainingPlans.isEmpty()) {
            System.out.println("No training plans available.");
        } else {
            System.out.println("Your Training Plans:");
            for (String plan : trainingPlans) {
                System.out.println("- " + plan);
            }
        }
    }

    private void loadTrainingPlansFromFile() {
        File file = new File("TrainingPlan.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    trainingPlans.add(scanner.nextLine().trim());
                }
            } catch (IOException e) {
                System.out.println("Error loading training plans: " + e.getMessage());
            }
        }
    }

    private void saveTrainingPlansToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TrainingPlans.txt"))) {
            for (String plan : trainingPlans) {
                writer.write(plan);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving training plans: " + e.getMessage());
        }
    }

    // Notification Methods
    public void receiveMessage(String message) {
        notifications.add(message);
        System.out.println("New message received: " + message);
        saveNotificationsToFile();
    }

    public void viewNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications.");
        } else {
            System.out.println("Your Notifications:");
            for (String notification : notifications) {
                System.out.println("- " + notification);
            }
        }
    }

    private void loadNotificationsFromFile() {
        File file = new File("Notifications.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    notifications.add(scanner.nextLine().trim());
                }
            } catch (IOException e) {
                System.out.println("Error loading notifications: " + e.getMessage());
            }
        }
    }

    private void saveNotificationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Notifications.txt"))) {
            for (String notification : notifications) {
                writer.write(notification);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving notifications: " + e.getMessage());
        }
    }
}
