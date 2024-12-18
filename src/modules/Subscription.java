package modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import services.*;; // Assuming FileHandler is in this package.

public class Subscription {
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    public Subscription(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    // Getters and Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Methods
    public void renewSubscription(Date newEndDate) {
        this.endDate = newEndDate;
        this.isActive = true;
        // FileHandler.saveSubscriptionData(); // Automatically update file after renewal.
    }

    public boolean checkIfExpired() {
        Date currentDate = new Date();
        return currentDate.after(endDate);
    }

    /**
     * Saves subscription data to the file for a specific member.
     */
    public void saveSubscriptionData(String memberId, String memberUsername, String coachId, String scheduleId) {
        String subscriptionData = String.join("/",
                memberId,
                memberUsername,
                coachId,
                dateFormat.format(startDate),
                dateFormat.format(endDate),
                scheduleId
        );
        FileHandler.saveSubscriptionData(subscriptionData);
    }

    /**
     * Loads subscription data for a specific Member ID.
     * @param memberId The ID of the member whose subscription is to be loaded.
     * @return A Subscription object if found; otherwise null.
     */
    public static Subscription loadSubscriptionByMemberId(String memberId) {
        ArrayList<String[]> subscriptions = FileHandler.loadSubscriptionData();
        for (String[] subscriptionData : subscriptions) {
            if (subscriptionData[0].equals(memberId)) {
                try {
                    Date startDate = dateFormat.parse(subscriptionData[4]);
                    Date endDate = dateFormat.parse(subscriptionData[5]);
                    return new Subscription(startDate, endDate);
                } catch (ParseException e) {
                    System.err.println("Error parsing dates for member " + memberId + ": " + e.getMessage());
                }
            }
        }
        System.out.println("Subscription for Member ID " + memberId + " not found.");
        return null;
    }
}
