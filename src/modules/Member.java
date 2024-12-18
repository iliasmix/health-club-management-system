package modules;
import modules.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import services.FileHandler;

/* public void addExerciseToTrainingPlan(Member member, String exercise) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        if (exercise == null || exercise.isEmpty()) {
            System.out.println("Exercise cannot be empty.");
            return;
        }

        try {
            // Create or get existing training plan
            TrainingPlan trainingPlan = getOrCreateTrainingPlan(member);

            // Update the schedule with the new exercise
            String currentSchedule = trainingPlan.getSchedule();
            String updatedSchedule = currentSchedule.isEmpty() ? exercise : currentSchedule + "\n" + exercise;

            trainingPlan.setSchedule(updatedSchedule);
            trainingPlan.saveScheduleToFile();

            System.out.println("Exercise added to " + member.getUsername() + "'s training plan.");
        } catch (IOException e) {
            System.out.println("Error updating training plan: " + e.getMessage());
        }
    }

    // Set a member's schedule and associate it with their training plan
    public void setMemberSchedule(Member member, String schedule) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        if (schedule == null || schedule.isEmpty()) {
            System.out.println("Schedule cannot be empty.");
            return;
        }

        try {
            // Create or get existing training plan
            TrainingPlan trainingPlan = getOrCreateTrainingPlan(member);

            // Set the new schedule
            trainingPlan.setSchedule(schedule);
            trainingPlan.saveScheduleToFile();

            System.out.println("Schedule updated for " + member.getUsername() + ".");
        } catch (IOException e) {
            System.out.println("Error updating schedule: " + e.getMessage());
        }
    }

    // Helper method to get or create a training plan for a member
    private TrainingPlan getOrCreateTrainingPlan(Member member) {
        // Create a new training plan with current date as start date and 3 months later
        // as end date
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (90L * 24 * 60 * 60 * 1000)); // 90 days later

        return new TrainingPlan(
                "plan-" + member.getID(), // Generate plan ID based on member ID
                member.getID(),
                this.getID(),
                startDate,
                endDate);
    }

    // Send a message to all members
    public void sendMessageToAllMembers(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }

        File file = new File("Notifications.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Coach: " + message);
            writer.newLine();
            System.out.println("Message sent to all members.");
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    // Update coach's own profile using FileHandler
    public boolean updateProfile(String newUsername, String newPassword) {
        FileHandler.updateCoachInfo(this.getID(), newUsername, newPassword);
        this.username = newUsername;
        this.password = newPassword;
        return true;
    }

    // Check if coach exists in system using FileHandler
    public static boolean isCoachExists(String coachID) {
        try {
            return FileHandler.isCoachAlreadyInTheSystem(coachID);
        } catch (FileNotFoundException e) {
            System.out.println("Error checking coach existence: " + e.getMessage());
            return false;
        }
    } */

public class Member extends User {
    private Coach coach;
    private String coachId;
    private String schedule;
    private Subscription subscription;
    private ArrayList<String> notifications;
    private FileHandler fileHandler;

    public Member(String username, String password, String ID) {
        super(username, password, ID);
        this.notifications = new ArrayList<>();
        this.fileHandler = new FileHandler();
        FileHandler.loadMemberData();
        loadNotifications();
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
    public void viewSubscriptionEndDate() {
        if (subscription != null && subscription.getEndDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Your subscription ends on: " + dateFormat.format(subscription.getEndDate()));
            
            // Check if subscription is about to expire
            Date currentDate = new Date();
            long daysUntilExpiry = (subscription.getEndDate().getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24);
            
            if (daysUntilExpiry <= 7 && daysUntilExpiry > 0) {
                System.out.println("WARNING: Your subscription will expire in " + daysUntilExpiry + " days!");
            } else if (daysUntilExpiry <= 0) {
                System.out.println("WARNING: Your subscription has expired!");
            }
        } else {
            System.out.println("No active subscription found.");
        }
    }

    // View coach and schedule
    public void viewCoachAndSchedule() {
        // Load coach information
        if (coachId != null && !coachId.isEmpty()) {
            ArrayList<String[]> schedules = fileHandler.loadScheduleData();
            System.out.println("\nYour Training Schedule:");
            System.out.println("Coach ID: " + coachId);
            
            boolean foundSchedule = false;
            String currentDay = "";
            
            for (String[] scheduleData : schedules) {
                if (scheduleData[1].equals(coachId)) {
                    foundSchedule = true;
                    if (!currentDay.equals(scheduleData[2])) {
                        currentDay = scheduleData[2];
                        System.out.println("\n" + currentDay + ":");
                    }
                    System.out.println("- " + scheduleData[3]);
                }
            }
            
            if (!foundSchedule) {
                System.out.println("No schedule found for your coach.");
            }
        } else {
            System.out.println("No coach assigned.");
        }
    }

    // Load notifications
    private void loadNotifications() {
        try {
            File file = new File("Notifications.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                // Skip header
                if (scanner.hasNextLine()) scanner.nextLine();
                
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
        String startDate = subscription != null && subscription.getStartDate() != null ? 
            dateFormat.format(subscription.getStartDate()) : "";
        String endDate = subscription != null && subscription.getEndDate() != null ? 
            dateFormat.format(subscription.getEndDate()) : "";
        String status = subscription != null ? String.valueOf(subscription.isActive()) : "";
        
        return String.format("%s/%s/%s/%s/%s/%s/%s/%s",
            getID(),
            getUsername(),
            getPassword(),
            coachId != null ? coachId : "",
            startDate,
            endDate,
            status,
            schedule != null ? schedule : ""
        );
    }
}