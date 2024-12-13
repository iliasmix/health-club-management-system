package services;
import java.util.ArrayList;
import modules.Coach;
import modules.*;
import java.io.*;
import java.util.*;

public class NotificationSystem {
    //private static ArrayList<String> notifications = new ArrayList<>();
    public static void sendSubscriptionExpiryNotification(Admin admin, Member member) throws FileNotFoundException {
        // Create expiry notification message
        //System.out.println("Your subscription has expired!");
        // Add to notifications list
        File file = new File("resources\\Notifications.txt");
        try(PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
            output.println("0/" + member.getID() + "/Your subscription has expired!/" + new Date());
            output.println("0/" + admin.getID() + "/Member " + member.getID() + "'s subscription has expired!/" + new Date());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // Send the notification to the admin
        // Send notification to member
    }

    public static void sendCoachMessage(Coach coach, String message) {
        // Send message to all coach's members
        // Add to notifications list
    }

    // public static ArrayList<String> getNotifications() {
    //     return notifications;
    // }
}
