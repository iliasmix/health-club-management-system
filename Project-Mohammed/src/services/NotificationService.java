package services;

import models.Member;
import utils.FileUtils;
import java.util.Date;

public class NotificationService {
    private static final String NOTIFICATION_FILE = "data/notifications.txt";

    public void sendSubscriptionNotification(Member member) {
        if (!isSubscriptionActive(member)) {
            String message = String.format("Subscription expired for member: %s", member.getUsername());
            FileUtils.writeToFile(NOTIFICATION_FILE, message, true);
        }
    }

    private boolean isSubscriptionActive(Member member) {
        Date currentDate = new Date();
        return member.getSubscriptionEndDate() != null && 
               member.getSubscriptionEndDate().after(currentDate);
    }
}