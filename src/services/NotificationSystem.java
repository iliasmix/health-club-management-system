package services;
import modules.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class NotificationSystem {
    private static int notificationId = 1;

    public static void sendSubscriptionExpiryNotification(Admin admin, Member member) throws FileNotFoundException {
        // Create expiry notification message (Pop up)
        System.out.println("Pop up message!");

        //Save messages to Notifications.txt
        File file = new File("resources\\Notifications.txt");
        try(PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
            // Send notification to member
            output.println("0/" + member.getID() + "/Your subscription has expired!/" + new Date());
            // Send the notification to the admin
            output.println("0/" + admin.getID() + "/Member " + member.getID() + "'s subscription has expired!/" + new Date());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean isSubscriptionActive(String memberId) {
        File membersFile = new File("resources\\Members.txt");
        try {
            Scanner membersScan = new Scanner(membersFile);

            if(membersScan.hasNext()) {
                membersScan.nextLine();

                while(membersScan.hasNext()) {

                    String[] parts = membersScan.nextLine().split("/");

                    if(memberId.equals(parts[0])) {
                        String expiryDateString = parts[5];

                        String[] dateParts = expiryDateString.split("-");
                        int expiryYear = Integer.parseInt(dateParts[0]);
                        int expiryMonth = Integer.parseInt(dateParts[1]);
                        int expiryDay = Integer.parseInt(dateParts[2]);

                        LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, expiryDay);
                        LocalDate today = LocalDate.now();

                        return expiryDate.isAfter(today);
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
}
