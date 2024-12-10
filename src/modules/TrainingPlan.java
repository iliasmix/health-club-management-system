package modules;
import java.util.ArrayList;
import java.util.Date;

public class TrainingPlan {
    private String planId;
    private String memberId;
    private String coachId;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> exercises;
    private String schedule;

    // Constructor
    public TrainingPlan(String planId, String memberId, String coachId, Date startDate, Date endDate) {
        // Initialize training plan details
    }

    // Plan management methods
    public void addExercise(String exercise) {
        // Add exercise to plan
    }

    public void setSchedule(String schedule) {
        // Set training schedule
    }

    // Getters
    public String getSchedule() {
        return schedule;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public String getPlanId() {
        return planId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getCoachId() {
        return coachId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
