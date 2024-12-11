package modules;

import java.util.ArrayList;
import java.util.Date;

public class Coach extends User {
    private ArrayList<Member> members;
    // private HashMap<String, TrainingPlan> trainingPlans;

    // Constructor
    public Coach(String username, String password, String email) {
        super(username, password, email);
        // Initialize coach fields
    }

    // !!!!!!!!!!!!!!!!!!!!! Member management methods
    // public void assignMember(Member member) {
    // // Check if member exists
    // // Add member to list
    // }

    // !!!!!!!!!!!!!!!!!!!!!!!! public void removeMember(Member member) {
    // // Remove member from list
    // // Remove associated training plan
    // }

    // Training plan methods
    public void createTrainingPlan(Member member, Date startDate, Date endDate) {
        // Validate member is assigned to coach
        // Create new training plan
        // Store plan in store in file
    }

    public void addExerciseToTrainingPlan(Member member, String exercise) {
        // Find member's training plan
        // Add exercise to plan
    }

    public void setMemberSchedule(Member member, String schedule) {
        // Find member's training plan
        // Update schedule in plan
        // Update member's schedule
    }

    // Communication methods
    public void sendMessageToAllMembers(String message) {
        // Format coach message
        // Send to all members
    }

    // Getters and setters
    public ArrayList<Member> getMembers() {
        return members;
    }

    // public TrainingPlan getTrainingPlan(Member member) {
    // return trainingPlans.get(member.getUsername());
    // }

    // public void setName(String name) {
    //     this.setUsername(name);
    // }

    // public String getName() {
    //     return this.getUsername();
    // }
}
