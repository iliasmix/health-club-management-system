package services;
import java.util.ArrayList;
import modules.Coach;
import modules.*;
import java.io.*;
import java.util.*;

public class NotificationSystem {
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

    public static void sendMessage(String senderId, String receiverId, String message ) throws FileNotFoundException {

    }
}
