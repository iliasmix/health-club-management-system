package Main;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point of the Health Club Management System.
 * This class initializes the system, displays the menu, and handles user
 * interactions.
 */

import modules.*;
import services.FileHandler;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            // Show the main menu options to the user.
            displayMenu();
            // Get the user's choice from the console input (e.g., Admin, Coach, Member, or
            // Exit).
            // Depending on the user's choice, direct them to the appropriate module:

            switch (getUserInput()) {
                case 1:
                    System.out.println("Enter userName and password:");
                    String userName = input.next();
                    String password = input.next();

                    Admin admin = new Admin(userName, password);

                    while (!(User.login(userName, password))) {
                        System.out.println("INVALID userName and password. Please try again:");
                        userName = input.next();
                        password = input.next();
                    }

                    boolean exit = false;
                    /* ============================================================ */
                    /*=================== USER AUTHOROIZATION (2.5 marks) ===================== */
                    while (!exit) {
                        System.out.println("Choose an operation:\n" +
                                "---------------------------------\n" +
                                "1 - Add Member\n" +
                                "2 - Remove Member\n" +
                                "3 - Update Member\n" +
                                "4 - Add Coach\n" +
                                "5 - Remove Coach\n" +
                                "6 - Update Coach\n" +
                                "7 - Assign Member to Coach\n" +
                                "8 - Search Members\n" +
                                "9 - List All Members\n" +
                                "10 - List All Coaches\n" +
                                "11 - Generate reports\n" +
                                "0 - Log out\n" +
                                "---------------------------------");

                        switch (getUserInput()) {
                            case 1:
                                System.out.println("Enter members's userName ,password");
                                admin.addMember(new Member(input.next(), input.next()));
                                break;
                            case 2:
                                System.out.println("Enter members's ID");
                                admin.removeMember(input.next());
                                break;
                            case 3:
                                System.out.println("Enter members's ID,newUserName, password");
                               //  admin.updateMember(input.next(), input.next(), input.next());
                                break;
                            case 4:
                                System.out.println("Enter coach's userName ,password");
                                admin.addCoach(new Coach(input.next(), input.next()));
                                break;
                            case 5:
                                System.out.println("Enter coach's ID");
                                admin.removeCoach(input.next());
                                break;
                            case 6:
                                System.out.println("Enter coach's ID,newUsername ,Email");
                                admin.updateCoach(input.next(), input.next(), input.next());
                                break;
                            case 7:
                                System.out.println("Enter member's ID and coach's ID");
                                admin.assignMemberToCoach(input.next(), input.next());
                                break;
                            case 8:
                                System.out.println("Enter a keyword to search for members:");
                                String keyword = input.next();

                                ArrayList<Member> matchingMembers = admin.searchMembers(keyword);

                                if (matchingMembers.isEmpty()) {
                                    System.out.println("No members found matching the keyword: " + keyword);
                                } else {
                                    System.out.println("Matching members:");
                                    for (Member member : matchingMembers) {
                                        System.out.println(member); // Assumes Member class has a meaningful toString
                                    }
                                }
                                break;
                            case 9:
                                FileHandler.loadMemberData();
                                break;
                            case 10:
                                FileHandler.loadCoachData();
                                break;
                            case 11:
                                admin.generateReports();
                                break;
                            case 0:
                                exit = true;
                                System.out.println("Exiting to main menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Hi, Please Enter User Name and Password: ");
                    String userName2 = input.next();
                    String password2 = input.next();

                    Coach coach = new Coach(userName2, password2);

                    while (!(User.login(userName2, password2))) {
                        System.out.println("INVALID userName and password. Please try again:");
                        userName2 = input.next();
                        password2 = input.next();
                    }

                    boolean exit2 = false;
                    while (!exit2) {
                        System.out.println(
                                "Choose operation:\n" +
                                        "---------------------------------\n" +
                                        "1 - Set Member Schedule\n" +
                                        "2 - Send Message to Coach Members\n" +
                                        "0 - Log out\n" +
                                        "---------------------------------");

                        int choice = getUserInput();
                        switch (choice) {
                            case 1:
                                System.out.println(
                                        "Enter member's Schedule, start date (Year, Month, Day), and week number:");
                                LocalDate date = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                                System.out.println("Enter the schedules you want to set and number of weeks ");
                                coach.setSchedulesForAllMembers(input.next(), date, input.nextInt());
                                break;
                            case 2:
                                System.out.println("===================-------==================");
                                System.out.println(
                                        "Enter the message separated by spaces:");
                                input.nextLine();
                                String message = input.nextLine();
                                String coachID  = input.nextLine();
                                Coach.sendMessageToAllMembers(coachID, message);
                                break;
                            case 0:
                                exit = true;
                                System.out.println("Exiting to main menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter userName, password:");
                    String userName3 = input.next();
                    String password3 = input.next();

                    Member member = new Member(userName3, password3);

                    // Login Loop
                    while (!(User.login(userName3, password3))) {
                        System.out.println("Invalid User Name and Password. Please try again:");
                        userName3 = input.next();
                        password3 = input.next();
                        member = new Member(userName3, password3);
                    }

                    boolean exitMemberMenu = false;

                    // Operations Menu Loop
                    while (!exitMemberMenu) {
                        System.out.println("Choose an operation:\n" +
                                "---------------------------------\n" +
                                "1 - View Subscription End Date\n" +
                                "2 - View Coach and Schedule\n" +
                                "0 - Log out\n" +
                                "---------------------------------");

                        switch (getUserInput()) {
                            case 1:
                                member.viewSubscriptionEndDate(member.getID());
                                break;
                            case 2:
                                member.viewCoachAndSchedule(member.getID());
                                break;
                            case 0:
                                exitMemberMenu = true;
                                System.out.println("Exiting to main menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;

                case 0:
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
        // system.cleanup();
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
        System.out.println("---------------");
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
