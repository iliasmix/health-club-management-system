package main;

import java.util.Scanner;

/**
 * Entry point of the Health Club Management System.
 * This class initializes the system, displays the menu, and handles user
 * interactions.
 */
import modules.Admin;
import modules.Member;
import modules.Coach;
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
                    Admin admin = new Admin(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- addMember \n2- removeMember \n3- updateMember \n4- addCoach \n5- removeCoach \n6- updateCoach \n7- assignMemberToCoach ");

                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's userName ,password,ID");
                            admin.addMember(new Member(input.nextLine(), input.nextLine(), input.nextLine()));
                            break;
                        case 2:
                            System.out.println("Enter members's ID");
                            // admin.removeMember(input.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter members's ID,newUserName ,Email");
                            // admin.updateMember(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter coach's userName ,password,ID");
                            admin.addCoach(new Coach(input.nextLine(), input.nextLine(), input.nextLine()));
                            break;
                        case 5:
                            System.out.println("Enter coach's ID");
                            // admin.removeCoach(input.nextLine());
                            break;
                        case 6:
                            System.out.println("Enter coach's ID,newUserName ,Email");
                            // admin.updateCoach(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 7:
                            System.out.println("Enter member's ID and coach's ID");
                            // admin.assignMemberToCoach(input.nextLine(), input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter userName ,password,ID");
                    Coach coach = new Coach(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- create Training Plan \n2- add Exercise To Training Plan \n3- set Member Schedule \n4- send Message To Coach Members \n");
                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's ID ,start date,end date");
                            // coach.createTrainingPlan(input.nextLine(), input.nextLine(),
                            // input.nextLine());
                            break;
                        case 2:
                            System.out.println("Enter members's ID,exercise ");
                            // coach.addExerciseToTrainingPlan(input.nextLine(),
                            // input.nextLine(),input.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter members's ID,schedule");
                            // coach.setMemberSchedule(input.nextLine(), input.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter message,coach's ID");
                            // coach.sendMessageToCoachMembers(input.nextLine(), input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Enter userName ,password,ID");
                    Member member = new Member(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- view Subscription Details \n2- view Schedule \n3- view Training Plan \n");
                    switch (getUserInput()) {
                        case 1:
                            // member.viewSubscriptionDetails();
                            break;
                        case 2:
                            // member.viewSchedule();
                            break;
                        case 3:
                            // member.viewTrainingPlan();
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
