package services;

import java.io.FileNotFoundException;
import java.util.*;

public class TestBills {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------");
        System.out.println("Create a Bill:");
        System.out.println("--------------");


        String memberID;
        do {
            System.out.print("Enter the member's ID (m-x), 0 to terminate: ");
             memberID = input.next();

             if(memberID.equals("0")) {
                 System.out.println("Ending the process...");
                 System.exit(0);
             }
             else if(!memberID.matches("m-\\d+$")) {
                 System.out.println("The member ID must start with an m- then a number!!");
             }
             else if(!FileHandler.isMemberAlreadyInTheSystem(memberID)) {
                 System.out.println("There is no member with ID " + memberID + " in the system!");
             }
             else if(NotificationSystem.isSubscriptionActive(memberID)) {
                 System.out.println("This member's subscription is active!");
             }
        } while (!memberID.matches("m-\\d+$") || NotificationSystem.isSubscriptionActive(memberID) || !FileHandler.isMemberAlreadyInTheSystem(memberID));

        int planMonths;
        do {
            System.out.print("Enter the plan months (6 or 12): ");
            planMonths = input.nextInt();

            if(planMonths != 6 && planMonths != 12) {
                System.out.println("Invalid input!");
            }
        } while(planMonths != 6 && planMonths != 12);

        int startYear;
        do {
            System.out.print("Enter the plan start year: ");
            startYear = input.nextInt();

            if(startYear < 2024) {
                System.out.println("This gym was founded in 2024, how are you attempting to add a year before 2024!!!");
            }
        } while (startYear < 2024);

        int startMonth;
        do {
            System.out.print("Enter the plan start month: ");
            startMonth = input.nextInt();

            if(startMonth < 1 || startMonth > 12) {
                System.out.println("Invalid input! Only 1 to 12 is accepted!");
            }
        } while(startMonth < 1 || startMonth > 12);

        int startDay;
        do {
            System.out.print("Enter the plan start day: ");
            startDay = input.nextInt();

            if(startDay < 1 || startDay > 31) {
                System.out.println("Invalid input! Only 1 to 31 is accepted!");
            }
        } while(startDay < 1 || startDay > 31);


        Billing.createBill(memberID, planMonths, startYear, startMonth, startDay);
    }
}
