package modules;

import services.NotificationSystem;

import java.io.*;
import java.text.SimpleDateFormat; // for formatting the message simple module.. 
import java.util.ArrayList;
import java.util.Date;

public class Coach extends User {
    private ArrayList<Member> members;
    private String scheduleId;

    public Coach(String username, String password, String ID) {
        super(username, password, ID);
        this.username = username;
        this.password = password;
    }
    // -----------------------Logical error here------------------------------
    // !!!!!!!! this methods should be in admin.. 3nd mohammed 
    // public void assignMember(Member member) {
    // // Check if member exists
    // // Add member to list
    // }

    // public void removeMember(Member member) {
    // // Remove member from list
    // // Remove associated training plan
    // }
    // -------------------------------------------------------
    public void createTrainingPlan(Member member, Date startDate, Date endDate) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        String trainingPlan = String.format("Training Plan for %s: Start Date: %s, End Date: %s",
                member.getUsername(),
                new SimpleDateFormat("yyyy-MM-dd").format(startDate),
                new SimpleDateFormat("yyyy-MM-dd").format(endDate));

        //member.addTrainingPlan(trainingPlan);
        System.out.println("Training plan created successfully for " + member.getUsername());
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

        //member.addTrainingPlan("Exercise: " + exercise);
        System.out.println("Exercise added to " + member.getUsername() + "'s training plan.");
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

        //member.addTrainingPlan("Schedule: " + schedule);
        System.out.println("Schedule updated for " + member.getUsername() + ".");
    }

    public void sendMessageToCoachMembers(String message, String coachId) {
        if (message == null || message.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }
    
        File membersFile = new File("resources\\Members.txt");
    
        try (BufferedReader reader = new BufferedReader(new FileReader(membersFile))) {
    
            String line;
            boolean messageSent = false;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("/"); // Splitting the line into columns.
    
                if (columns.length >= 4) {
                    String memberId = columns[0];
                    String memberCoachId = columns[3];
    
                    // Check if the Member's Coach ID matches the provided Coach ID.
                    if (coachId.equals(memberCoachId)) {
                        try {
                            NotificationSystem.sendMessage(coachId, memberId, message);
                            messageSent = true;
                        } catch (FileNotFoundException e) {
                            System.out.println("Error sending message to member " + memberId + ": " + e.getMessage());
                        }
                    }
                }
            }
    
            if (messageSent) {
                System.out.println("Message sent to all members associated with Coach ID: " + coachId);
            } else {
                System.out.println("No members found for Coach ID: " + coachId);
            }
    
        } catch (IOException e) {
            System.out.println("Error processing members file: " + e.getMessage());
        }
    }
    

    // public TrainingPlan getTrainingPlan(Member member) {
    // // return trainingPlans.get(member.getUsername());
    // }

    public void setName(String name) {
        this.setUsername(name);
    }

    public String getName() {
        return this.getUsername();
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s",
            getID(),              // Coach ID
            getUsername(),        // Coach Username
            getPassword(),        // Coach Pass
            scheduleId != null ? scheduleId : ""  // Schedule ID
        );
    }
}
