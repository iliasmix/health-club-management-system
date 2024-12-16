import java.util.Scanner;
import java.io.*;
import java.util.*;

public class MemberModule {
    private Scanner scanner = new Scanner(System.in);
    private static final String SCHEDULES_FILE = "schedules.txt";
    private static final String SUBSCRIPTIONS_FILE = "subscriptions.txt";

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View Subscription Status");
            System.out.println("2. View Training Schedule");
            System.out.println("3. View Coach Details");
            System.out.println("4. Update Personal Information");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    viewSubscriptionStatus();
                    break;
                case 2:
                    viewTrainingSchedule();
                    break;
                case 3:
                    viewCoachDetails();
                    break;
                case 4:
                    updatePersonalInfo();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void viewSubscriptionStatus() {
        System.out.println("Viewing subscription status...");
        // Implementation for viewing subscription status
    }

    private void viewTrainingSchedule() {
        System.out.println("Viewing training schedule...");
        // Implementation for viewing training schedule
    }

    private void viewCoachDetails() {
        System.out.println("Viewing coach details...");
        // Implementation for viewing coach details
    }

    private void updatePersonalInfo() {
        System.out.println("Updating personal information...");
        // Implementation for updating personal information
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}