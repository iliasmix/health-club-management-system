package services;
import modules.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class NotificationSystem {
    private static int notificationId = getNextNotificationIdNum();

    public static void sendSubscriptionExpiryNotification(String memberId) throws FileNotFoundException {
        if(!NotificationSystem.isSubscriptionActive(memberId)) {
            // Create expiry notification message (Pop up)
            System.out.println("Pop up message!");

            //Save messages to Notifications.txt
            File file = new File("resources\\Notifications.txt");
            try(PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
                // Send notification to member
                output.println("n-" + notificationId + "/0/" + memberId + "/Your subscription has expired!/" + new Date());
                notificationId++;
                // Send the notification to the admin
                output.println("n-" + notificationId + "/0/" + "a-1" + "/Member " + memberId+ "'s subscription has expired!/" + new Date());
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static boolean isSubscriptionActive(String memberId) {
        File membersFile = new File("resources\\Members.txt");
            try (Scanner membersScan = new Scanner(membersFile)) {

                if (membersScan.hasNext()) {
                    membersScan.nextLine();

                    while (membersScan.hasNext()) {

                        String[] parts = membersScan.nextLine().split("/");

                        if (memberId.equals(parts[0])) {
                            String expiryDateString = parts[5];

                            String[] dateParts = expiryDateString.split("-");
                            int expiryYear = Integer.parseInt(dateParts[0]);
                            int expiryMonth = Integer.parseInt(dateParts[1]);
                            int expiryDay = Integer.parseInt(dateParts[2]);

                            LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, expiryDay);
                            LocalDate today = LocalDate.now();

                            if (expiryDate.isAfter(today)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // If the member wasn't found
        return false;
    }

    public static void sendMessage(String senderId, String receiverId, String message) throws FileNotFoundException {
        File notificationsFile = new File("resources\\Notifications.txt");

        try(PrintWriter output = new PrintWriter(new FileWriter(notificationsFile, true))) {
            output.println("n-"+ notificationId + "/" + senderId + "/" + receiverId + "/" + message + "/" + new Date());
            notificationId++;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int getNextNotificationIdNum() {
        File notificationsFile = new File("resources\\Notifications.txt");

        if(!notificationsFile.exists()) {
            return 1; //Start with ID 1 if the bills file doesn't exist
        }

        try(Scanner notificationsScanner = new Scanner(notificationsFile)) {



            //Skip the first line as it is the guideline.
            if(notificationsScanner.hasNextLine()) {
                notificationsScanner.nextLine();

                //Check if the file is empty
                if(!notificationsScanner.hasNext()) {
                    return 1; //Start with bill ID 1
                }
            }

            String lastId = "";
            while(notificationsScanner.hasNext()) {
                String[] parts = notificationsScanner.nextLine().split("/");
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
    
    // polymorphism
    public static void greet(User u) throws FileNotFoundException {
        if(u instanceof Admin) {
            NotificationSystem.sendMessage("0", u.getID(), "Welcome, admin " + u.getUsername() + "!");
        }
        else if(u instanceof Member) {
            NotificationSystem.sendMessage("0", u.getID(), "Welcome, member " + u.getUsername() + "!");
        }
        else if(u instanceof Coach) {
            NotificationSystem.sendMessage("0", u.getID(), "Welcome, coach " + u.getUsername() + "!");
        }
        else
            return;
    }
}
