package Main;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Entry point of the Health Club Management System.
 * This class initializes the system, displays the menu, and handles user
 * interactions.
 */

import modules.*;

import services.FileHandler;

public class Main {
    public static void main(String[] args) {
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
                    System.out.println("Enter userName ,password,ID");
                    String userName = input.nextLine();
                    String password = input.nextLine();
                    Admin admin = new Admin(userName, password);
                    while (!(User.login(userName, password))) {
                        System.out.println("INVALID userName and password : ");
                    }

                    System.out.println(
                            "choose operation : \n1- addMember \n2- removeMember \n3- updateMember \n4- addCoach \n5- removeCoach \n6- updateCoach \n7- assignMemberToCoach ");

                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's userName ,password,ID");
                            admin.addMember(new Member(input.nextLine(), input.nextLine()));
                            break;
                        case 2:
                            System.out.println("Enter members's ID");
                            admin.removeMember(input.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter members's ID,newUserName ,Email");
                            admin.updateMember(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter coach's userName ,password,ID");
                            admin.addCoach(new Coach(input.nextLine(), input.nextLine()));
                            break;
                        case 5:
                            System.out.println("Enter coach's ID");
                            admin.removeCoach(input.nextLine());
                            break;
                        case 6:
                            System.out.println("Enter coach's ID,newUserN9ame ,Email");
                            admin.updateCoach(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 7:
                            System.out.println("Enter member's ID and coach's ID");
                            admin.assignMemberToCoach(input.nextLine(), input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter userName ,password,ID");
                    String userName2 = input.nextLine();
                    String password2 = input.nextLine();
                    Coach coach = new Coach(userName2, password2);
                    while (!(User.login(userName2, password2))) {
                        System.out.println("INVALID userName and password : ");
                    }
                    System.out.println(
                            "choose operation : \n1 - set Member Schedule \n2 - send Message To Coach Members \n");
                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's Schedule ,start date(Year,Month,Day) ,week number");
                            LocalDate date = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                            coach.setSchedulesForAllMembers(input.nextLine(), date, input.nextInt());
                            break;
                        case 2:
                            System.out.println("Enter members's Message : ");
                            coach.sendMessageToAllMembers(input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Enter userName ,password,ID");
                    String userName3 = input.nextLine();
                    String password3 = input.nextLine();
                    Member member = new Member(userName3, password3);
                    while (!(User.login(userName3, password3))) {
                        System.out.println("INVALID userName and password : ");
                    }
                    System.out.println(
                            "choose operation : \n1- view Subscription end date \n2- view coache and Schedule \n");
                    switch (getUserInput()) {
                        case 1:
                            member.viewSubscriptionEndDate();
                            break;
                        case 2:
                            member.viewCoachAndSchedule();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
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
