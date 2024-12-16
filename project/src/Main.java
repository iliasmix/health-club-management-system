import java.util.Scanner;
import java.io.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserModule userModule = new UserModule();
    private static AdminModule adminModule = new AdminModule();
    private static CoachModule coachModule = new CoachModule();
    private static MemberModule memberModule = new MemberModule();

    public static void main(String[] args) {
        initializeSystem();
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleCoachLogin();
                    break;
                case 3:
                    handleMemberLogin();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using Health Club Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSystem() {
        createDataFiles();
        System.out.println("Health Club Management System initialized successfully!");
    }

    private static void createDataFiles() {
        String[] files = {"users.txt", "members.txt", "coaches.txt", "schedules.txt", "subscriptions.txt"};
        for (String file : files) {
            try {
                File f = new File(file);
                if (!f.exists()) {
                    f.createNewFile();
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + file);
                e.printStackTrace();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Health Club Management System ===");
        System.out.println("1. Admin Login");
        System.out.println("2. Coach Login");
        System.out.println("3. Member Login");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void handleAdminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userModule.authenticate(username, password, "ADMIN")) {
            adminModule.showMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void handleCoachLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userModule.authenticate(username, password, "COACH")) {
            coachModule.showMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void handleMemberLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userModule.authenticate(username, password, "MEMBER")) {
            memberModule.showMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }
}