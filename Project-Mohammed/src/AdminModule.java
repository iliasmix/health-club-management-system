import java.util.Scanner;
import java.io.*;
import java.util.*;

public class AdminModule {
    private Scanner scanner = new Scanner(System.in);
    private static final String MEMBERS_FILE = "data\\members.txt";
    private static final String COACHES_FILE = "data\\coaches.txt";

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Coaches");
            System.out.println("3. Generate Reports");
            System.out.println("4. Manage Billing");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    manageMembersMenu();
                    break;
                case 2:
                    manageCoachesMenu();
                    break;
                case 3:
                    generateReports();
                    break;
                case 4:
                    manageBilling();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void manageMembersMenu() {
        System.out.println("\n=== Manage Members ===");
        System.out.println("1. Add Member");
        System.out.println("2. Remove Member");
        System.out.println("3. Update Member");
        System.out.println("4. List Members");
        System.out.println("5. Back");
        
        int choice = getChoice();
        switch (choice) {
            case 1:
                addMember();
                break;
            case 2:
                removeMember();
                break;
            case 3:
                updateMember();
                break;
            case 4:
                listMembers();
                break;
        }
    }

    private void manageCoachesMenu() {
        System.out.println("\n=== Manage Coaches ===");
        System.out.println("1. Add Coach");
        System.out.println("2. Remove Coach");
        System.out.println("3. Update Coach");
        System.out.println("4. List Coaches");
        System.out.println("5. Back");
        
        int choice = getChoice();
        switch (choice) {
            case 1:
                addCoach();
                break;
            case 2:
                removeCoach();
                break;
            case 3:
                updateCoach();
                break;
            case 4:
                listCoaches();
                break;
        }
    }

    private void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE, true))) {
            writer.println(id + "," + name);
            System.out.println("Member added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    private void removeMember() {
        System.out.print("Enter member ID to remove: ");
        String id = scanner.nextLine();
        
        List<String> lines = new ArrayList<>();
        boolean removed = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(id + ",")) {
                    lines.add(line);
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading members file: " + e.getMessage());
            return;
        }
        
        if (removed) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
                for (String line : lines) {
                    writer.println(line);
                }
                System.out.println("Member removed successfully!");
            } catch (IOException e) {
                System.out.println("Error updating members file: " + e.getMessage());
            }
        } else {
            System.out.println("Member not found!");
        }
    }

    private void listMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            System.out.println("\nMember List:");
            System.out.println("ID | Name");
            System.out.println("------------");
            while ((line = reader.readLine()) != null) {
                System.out.println(line.replace(",", " | "));
            }
        } catch (IOException e) {
            System.out.println("Error reading members: " + e.getMessage());
        }
    }

    private void generateReports() {
        System.out.println("\nGenerating reports...");
        // Implementation for generating reports
    }

    private void manageBilling() {
        System.out.println("\nManaging billing...");
        // Implementation for managing billing
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}