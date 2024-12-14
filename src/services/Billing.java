package services;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Billing {

    private static int billIdIncrementor = getNextBillIdNumber();

    public static void createBill(String memberId, int planMonths, int year, int month, int day) {
        String billId = "b-" + billIdIncrementor;
        billIdIncrementor++;

        //Check if the member's subscription is not active


        int price = getPlanPrice(planMonths);
        if(price == -1) {
            System.err.println("Failed to create a bill for member " + memberId);
        }

        LocalDate startDate = LocalDate.of(year, month, day);
        LocalDate endDate = startDate.plusMonths(planMonths);

        File billsFile = new File("resources\\Bills.txt");
        try(PrintWriter billsOutput = new PrintWriter(new FileWriter(billsFile, true))) {
            billsOutput.println(billId + "/" + memberId + "/" + planMonths + (planMonths > 1? " months": " month") + "/" + startDate + "/" + endDate + "/" + price + "/" + new Date());
            System.out.println("Bill created successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getPlanPrice(int planMonths) {
        File subscriptionsFile = new File("resources\\Subscriptions.txt");

        if(!subscriptionsFile.exists()) {
            return -1; //Let the default be 1 month
        }

        try(Scanner subscriptionsScan = new Scanner(subscriptionsFile)) {

            //Skip the first line as it is the file guideline.
            if(subscriptionsScan.hasNextLine()) {
                subscriptionsScan.nextLine();
            }

            while(subscriptionsScan.hasNext()) {
                String[] parts = subscriptionsScan.nextLine().split("/");

                //If the first part (Months count) equals plan months return the second part (the price)
                if(parts[0].equals(planMonths + "")) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!" + e.getMessage());
            return -1;
        }

        return -1; //If everything fails
    }
    private static int getNextBillIdNumber() {
        File billsFile = new File("resources\\Bills.txt");

        if(!billsFile.exists()) {
            return 1; //Start with ID 1 if the bills file doesn't exist
        }

        try(Scanner billsScanner = new Scanner(billsFile)) {



            //Skip the first line as it is the guideline.
            if(billsScanner.hasNextLine()) {
                billsScanner.nextLine();

                //Check if the file is empty
                if(!billsScanner.hasNext()) {
                    return 1; //Start with bill ID 1
                }
            }

            String lastId = "";
            while(billsScanner.hasNext()) {
                String[] parts = billsScanner.nextLine().split("/");
                lastId = parts[0]; //This is b-x, where x is an integer
            }

            String[] idParts = lastId.split("-"); //Split b-x into b and x

            //return x after converting it from a string to an int and add 1 to go to the next ID
            int lastIdNum = Integer.parseInt(idParts[1]);
            return lastIdNum + 1;

        } catch (FileNotFoundException ex) {
            System.err.println("Unable to read: File not found " + ex.getMessage());
            return 0;
        }
    }
}
