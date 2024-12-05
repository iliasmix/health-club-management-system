/**
 * Entry point of the Health Club Management System.
 * This class initializes the system, displays the menu, and handles user
 * interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Create the central HealthClubSystem instance that manages all operations.
        HealthClubSystem system = new HealthClubSystem();

        // The system needs to be initialized before starting (e.g., loading data).
        // This includes loading members, coaches, and admin data from files.
        system.initializeSystem();

        // A loop ensures that the system runs continuously until the user decides to
        // exit.
        boolean isRunning = true;
        while (isRunning) {
            // Show the main menu options to the user.
            displayMenu();

            // Get the user's choice from the console input (e.g., Admin, Coach, Member, or
            // Exit).
            int choice = getUserInput();

            // Depending on the user's choice, direct them to the appropriate module:
            switch (choice) {
                case 1: // If the user selects Admin.
                    // Direct the user to the admin operations, such as managing members or
                    // generating reports.
                    system.adminOperations();
                    break;

                case 2: // If the user selects Coach.
                    // Allow the coach to manage schedules, send messages, or view member details.
                    system.coachOperations();
                    break;

                case 3: // If the user selects Member.
                    // Allow the member to view their coach, subscription details, or schedules.
                    system.memberOperations();
                    break;

                case 0: // If the user selects Exit.
                    // Set the `isRunning` flag to false to break the loop and close the system.
                    isRunning = false;
                    System.out.println("Exiting the Health Club Management System. Goodbye!");
                    break;

                default:
                    // Inform the user if they enter an invalid option and prompt them to try again.
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Once the loop exits, perform any necessary cleanup, such as saving data to
        // files.
        system.cleanup();
    }

    /**
     * Displays the main menu options to the user.
     * The menu includes options for admin, coach, and member functionalities, as
     * well as exiting.
     */
    private static void displayMenu() {
        System.out.println("\n==== Health Club Management System ====");
        System.out.println("1. Admin Login");
        System.out.println("2. Coach Login");
        System.out.println("3. Member Login");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Reads and validates the user's input from the console.
     * Ensures the input is a valid integer corresponding to menu options.
     * 
     * @return the validated choice as an integer.
     */
    private static int getUserInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;

        try {
            // Read the input and parse it as an integer.
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // If the input is invalid (not an integer), default to -1 (invalid option).
            choice = -1;
        }

        return choice;
    }
}
