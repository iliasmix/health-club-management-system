package modules;

import modules.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import services.FileHandler;


// Inheritance
public class Member extends User {
    private Coach coach;
    private String coachId;
    private String schedule;
    private Subscription subscription;
    private ArrayList<String> notifications;
    private FileHandler fileHandler;

    public Member(String username, String password) {
        super(username, password, generateCoachID());
    }

    private static int memberCounter = 0;

    private static String generateCoachID() {
        memberCounter++; // Increment the counter
        return "m-" + memberCounter; // Generate the ID in the format a-1, a-2, etc.
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    // View subscription end date
    /*public void viewSubscriptionEndDate() {
        if (subscription != null && subscription.getEndDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Your subscription ends on: " + dateFormat.format(subscription.getEndDate()));

            // Check if subscription is about to expire
            Date currentDate = new Date();
            long daysUntilExpiry = (subscription.getEndDate().getTime() - currentDate.getTime())
                    / (1000 * 60 * 60 * 24);

            if (daysUntilExpiry <= 7 && daysUntilExpiry > 0) {
                System.out.println("WARNING: Your subscription will expire in " + daysUntilExpiry + " days!");
            } else if (daysUntilExpiry <= 0) {
                System.out.println("WARNING: Your subscription has expired!");
            }
        } else {
            System.out.println("No active subscription found.");
        }
    }*/

        public void viewSubscriptionEndDate(String memberId) throws FileNotFoundException {
            File membersFile = new File("resources\\Members.txt");
            try(Scanner membersScan = new Scanner(membersFile)) {
                if (membersScan.hasNextLine()) {
                    membersScan.nextLine(); // Skip header if present
                }

                while (membersScan.hasNextLine()) {
                    String[] parts = membersScan.nextLine().split("/");
                    if(memberId.equals(parts[0])) {
                        System.out.println(parts[5]);
                        return;
                    }
                }
            }
        }

    public void viewCoachAndSchedule(String memberId) throws FileNotFoundException {
        File membersFile = new File("resources\\Members.txt");
        try(Scanner membersScan = new Scanner(membersFile)) {
            if (membersScan.hasNextLine()) {
                membersScan.nextLine(); // Skip header if present
            }

            while (membersScan.hasNextLine()) {
                String[] parts = membersScan.nextLine().split("/");
                if(memberId.equals(parts[0])) {
                    System.out.println("Coach: " + parts[3] + ", schedule: " + parts[6]);
                    return;
                }
            }
        }
    }

    // View coach and schedule
    // public void viewCoachAndSchedule(String coachID) {
    //     // Load coach information
    //     if (coachId != null && !coachId.isEmpty()) {
    //         ArrayList<String[]> schedules = fileHandler.loadScheduleData();
    //         System.out.println("\nYour Training Schedule:");
    //         System.out.println("Coach ID: " + coachId);

    //         boolean foundSchedule = false;
    //         String currentDay = "";

    //         for (String[] scheduleData : schedules) {
    //             if (scheduleData[1].equals(coachID)) {
    //                 foundSchedule = true;
    //                 if (!currentDay.equals(scheduleData[2])) {
    //                     currentDay = scheduleData[2];
    //                     System.out.println("\n" + currentDay + ":");
    //                 }
    //                 System.out.println("- " + scheduleData[3]);
    //             }
    //         }

    //         if (!foundSchedule) {
    //             System.out.println("No schedule found for your coach.");
    //         }
    //     } else {
    //         System.out.println("No coach assigned.");
    //     }
    // }

    // Load notifications
    private void loadNotifications() {
        try {
            File file = new File("Notifications.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                // Skip header
                if (scanner.hasNextLine())
                    scanner.nextLine();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split("/");
                    // Check if notification is for this member
                    if (data[2].equals(this.getID())) {
                        notifications.add(data[3]); // Add message content
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading notifications: " + e.getMessage());
        }
    }

    // Check subscription status and send notification if expired
    public void checkSubscriptionStatus() {
        if (subscription != null && subscription.getEndDate() != null) {
            Date currentDate = new Date();
            if (currentDate.after(subscription.getEndDate())) {
                // Add expiration notification
                String notification = "Your subscription has expired!";
                notifications.add(notification);

                // Save notification to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Notifications.txt", true))) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    writer.write(String.format("n-%d/0/%s/%s/%s\n",
                            notifications.size() + 1,
                            this.getID(),
                            notification,
                            dateFormat.format(currentDate)));
                } catch (IOException e) {
                    System.out.println("Error saving notification: " + e.getMessage());
                }
            }
        }
    }

    // Other existing methods...

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = subscription != null && subscription.getStartDate() != null
                ? dateFormat.format(subscription.getStartDate())
                : "";
        String endDate = subscription != null && subscription.getEndDate() != null
                ? dateFormat.format(subscription.getEndDate())
                : "";

        return String.format("%s/%s/%s/%s/%s/%s/%s",
                getID(),
                getUsername(),
                getPassword(),
                coachId != null ? coachId : "",
                startDate,
                endDate,
                schedule != null ? schedule : "");
    }

    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor

    // Getters and Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setSubscriptionStart(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setSubscriptionEnd(Date endDate) {
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
        // FileHandler.saveSubscriptionData(); // Automatically update file after
        // renewal.
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
                scheduleId);
        FileHandler.saveSubscriptionData(subscriptionData);
    }

    /**
     * Loads subscription data for a specific Member ID.
     * 
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