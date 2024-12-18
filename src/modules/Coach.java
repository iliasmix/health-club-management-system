package modules;

import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;

import services.FileHandler;
import java.time.LocalDate;
import java.util.ArrayList;

// inheritance
public class Coach extends User {
    private FileHandler fileHandler;

    public void assignMember(Member member) {
        // Check if member exists
        // Add member to list
    }

    public void removeMember(Member member) {
        // Remove member from list
        // Remove associated training plan
    }

    public Coach(String username, String password, String ID) {
        super(username, password, ID);
        this.username = username;
        this.password = password;
        this.fileHandler = new FileHandler();
    }

    // Assign a training schedule to all members associated with the coach
    public void setSchedulesForAllMembers(String schedule, LocalDate startDate, int weeksnum) {
        ArrayList<Member> members = FileHandler.loadMemberData(); // Load all member data
        boolean hasAssignedMembers = false;

        for (Member member : members) {
            if (this.getID().equals(member.getCoachId())) { // Check if member is assigned to this coach
                hasAssignedMembers = true;

                // Create a training plan for this member
                TrainingPlan trainingPlan = new TrainingPlan(this.getID(), startDate, weeksnum);
                trainingPlan.setSchedule(schedule);

                // Save the schedule to the file
                try {
                    trainingPlan.saveScheduleToFile();
                } catch (Exception e) {
                    System.err.println("Error saving schedule for member: " + member.getUsername());
                }

                // Update the member's schedule
                member.setSchedule(schedule);
                FileHandler.saveMemberData(member); // Save updated member data
            }
        }

        if (hasAssignedMembers) {
            System.out.println("Schedules successfully assigned to all members.");
        } else {
            System.out.println("No members assigned to this coach.");
        }
    }

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

    public void saveCoach() {
        FileHandler.saveCoachData(this);
    }

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
