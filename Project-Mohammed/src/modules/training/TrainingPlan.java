package modules.training;

import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class TrainingPlan {
    private String scheduleId;
    private String coachId;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> exercises;
    private static final String SCHEDULE_FILE_PATH = "resources/Schedules.txt";

    public TrainingPlan(String scheduleId, String coachId, Date startDate, Date endDate) {
        this.scheduleId = scheduleId;
        this.coachId = coachId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(String exercise) {
        if (exercise == null || exercise.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise cannot be null or empty");
        }
        exercises.add(exercise);
    }

    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCHEDULE_FILE_PATH, true))) {
            writer.write(String.format("%s/%s/%s/%s/%s\n",
                scheduleId,
                coachId,
                startDate,
                endDate,
                String.join(";", exercises)));
        }
    }

    // Getters
    public String getScheduleId() { return scheduleId; }
    public String getCoachId() { return coachId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public ArrayList<String> getExercises() { return new ArrayList<>(exercises); }
}