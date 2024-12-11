package modules;
import java.util.ArrayList;

public class Member extends User {
    private Coach coach;
    private String schedule;
    private Subscription subscription;
    private ArrayList<String> notifications;
    private ArrayList<String> trainingPlans;

    // Constructor
    // Add coach
    public Member(String username, String password, String email) {
        super(username, password, email);
        // Initialize lists
        this.notifications = new ArrayList<>();
        this.trainingPlans = new ArrayList<>();
    }

    // Getters and Setters
    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    // Subscription methods
    public void viewSubscriptionDetails() {
        // Display subscription information
        // Show end date
        // Show expiry warning if applicable
    }

    // Schedule and training plan methods
    public void viewSchedule() {
        // Display member's training schedule
    }

    // public void viewTrainingPlan() {
    //     // Display all training plans
    // }

    // Notification methods
    // public void receiveMessage(String message) {
    //     // Add message to notifications
    //     // Display new message
    // }

    public void viewNotifications() {
        // Display all notifications
    }

    // // Training plan methods
    // public void addTrainingPlan(String plan) {
    //     // Add new training plan
    // }

    // public void clearTrainingPlans() {
    //     // Clear all training plans
    // }
}
