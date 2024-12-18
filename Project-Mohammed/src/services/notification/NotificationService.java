package services.notification;

import java.io.*;
import java.util.Date;

public class NotificationService {
    private static final String NOTIFICATIONS_FILE = "resources/Notifications.txt";
    private static int notificationCounter = 1;

    public static void sendNotification(String senderId, String receiverId, String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOTIFICATIONS_FILE, true))) {
            String notificationId = "n-" + notificationCounter++;
            writer.println(String.format("%s/%s/%s/%s/%s",
                notificationId,
                senderId,
                receiverId,
                message,
                new Date()));
        } catch (IOException e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
    }

    public static void sendSubscriptionExpiryNotification(String memberId) {
        sendNotification("SYSTEM", memberId, "Your subscription has expired!");
        sendNotification("SYSTEM", "a-1", "Member " + memberId + "'s subscription has expired!");
    }
}