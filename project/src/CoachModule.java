import java.util.Scanner;
import java.io.*;
import java.util.*;

public class CoachModule {
    private Scanner scanner = new Scanner(System.in);
    private static final String SCHEDULES_FILE = "schedules.txt";

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Coach Menu ===");
            System.out.println("1. Create Training Plan");
            System.out.println("2. Update Schedule");
            System.out.println("3. Send Message to Members");
            System.out.println("4. View Assigned Members");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    createTrainingPlan();
                    break;
                case 2:
                    updateSchedule();
                    break;
                case 3:
                    sendMessage();
                    break;
                case 4:
                    viewAssignedMembers();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void createTrainingPlan() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.println("Enter training plan details:");
        String plan = scanner.nextLine();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULES_FILE, true))) {
            writer.println(memberId + "," + plan);
            System.out.println("Training plan created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating training plan: " + e.getMessage());
        }
    }

    private void updateSchedule() {
        System.out.println("Updating schedule...");
        // Implementation for updating schedule
    }

    private void sendMessage() {
        System.out.println("Sending message to members...");
        // Implementation for sending messages
    }

    private void viewAssignedMembers() {
        System.out.println("Viewing assigned members...");
        // Implementation for viewing assigned members
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}