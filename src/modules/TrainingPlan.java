package modules;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class TrainingPlan {
    private String planId;
    private String memberId;
    private String coachId;
    private Date startDate;
    private Date endDate;
    private String schedule; // Training schedule placeholder
    private static final String SCHEDULE_FILE_PATH = "G:\\Health\\health-club-management-system\\resources\\Schedules.txt";

    // Constructor
    public TrainingPlan(String planId, String memberId, String coachId, Date startDate, Date endDate) {
        this.planId = planId;
        this.memberId = memberId;
        this.coachId = coachId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = ""; // Initialize schedule as empty
    }

    // Method to set or update the training schedule
    public void setSchedule(String schedule) {
        if (schedule == null || schedule.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule cannot be null or empty");
        }
        this.schedule = schedule;
    }

    // Method to save the schedule to a text file
    public void saveScheduleToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCHEDULE_FILE_PATH))) {
            writer.write(this.schedule);
        }
    }
    
    public void loadScheduleFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEDULE_FILE_PATH))) {
            StringBuilder scheduleBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                scheduleBuilder.append(line).append(System.lineSeparator());
            }
            this.schedule = scheduleBuilder.toString().trim();
        }
    }
    

    // Getter for schedule
    public String getSchedule() {
        return schedule;
    }

    // Getters for other attributes
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

