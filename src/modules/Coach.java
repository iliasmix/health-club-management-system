package modules;

import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
import java.util.Date;
import services.FileHandler;

public class Coach extends User {
    private FileHandler fileHandler;

    public Coach(String username, String password, String ID) {
        super(username, password, ID);
        this.username = username;
        this.password = password;
        this.fileHandler = new FileHandler();
    }

    public void assignMember(Member member) {
        // Check if member exists
        // Add member to list
    }

    public void removeMember(Member member) {
        // Remove member from list
        // Remove associated training plan
    }

    // Add exercise to a member's training plan
    public void addExerciseToTrainingPlan(Member member, String exercise) {
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
    }

    // Save coach data using FileHandler
    public void saveCoach() {
        FileHandler.saveCoachData(this);
    }

    // Delete coach using FileHandler
    public void deleteCoach() {
        FileHandler.deleteCoach(this.getID());
    }

    public void setName(String name) {
        this.setUsername(name);
    }

    public String getName() {
        return this.getUsername();
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", getUsername(), getPassword(), getID());
    }
}