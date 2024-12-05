import java.util.ArrayList;

public class NotificationSystem {
    private static ArrayList<String> notifications = new ArrayList<>();

    public static void sendSubscriptionExpiryNotification(Member member) {
        // Create expiry notification message
        // Add to notifications list
        // Send notification to member
    }

    public static void sendCoachMessage(Coach coach, String message) {
        // Send message to all coach's members
        // Add to notifications list
    }

    public static ArrayList<String> getNotifications() {
        return notifications;
    }

    public static void clearNotifications() {
        notifications.clear();
    }
}
