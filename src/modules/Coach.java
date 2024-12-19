package modules;

import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;

import services.FileHandler;
import services.NotificationSystem;

import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

// import javax.management.Notification;

// inheritance
public class Coach extends User {
    private FileHandler fileHandler;

    // public void assignMember(Member member) {
    //     // Check if member exists
    //     // Add member to list
    // }

    // public void removeMember(Member member) {
    //     // Remove member from list
    //     // Remove associated training plan
    // }

    public Coach(String username, String password) {
        super(username, password, generateCoachID());
        this.username = username;
        this.password = password;
    }

    private static int coachCounter = 0;

    private static String generateCoachID() {
        coachCounter++; // Increment the counter
        return "c-" + coachCounter; // Generate the ID in the format a-1, a-2, etc.
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
    
    // done here
    public static void sendMessageToAllMembers(String coachId, String message) {
        File membersFile = new File("resources\\Members.txt");
    
        try (Scanner membersScan = new Scanner(membersFile)) {
            // Skip header if it exists
            if (membersScan.hasNextLine()) {
                membersScan.nextLine();
            }
    
            while (membersScan.hasNextLine()) {
                String[] parts = membersScan.nextLine().split("/");
    
                if (parts.length > 3 && parts[3].equals(coachId)) {
                    String memberId = parts[0]; // Receiver ID (3rd column)
                    System.out.println("Sending message to Member ID: " + memberId);
                    NotificationSystem.sendMessage(coachId, memberId, message);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Members file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
        return String.format("%s/%s/%s", getID(), getUsername(), getPassword());
    }
}
