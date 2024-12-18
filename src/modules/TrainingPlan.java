package modules;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.time.LocalDate;

public class TrainingPlan {
    private static int idCounter = 0; // Static counter for auto-incrementing IDs
    private String scheduleId;
    private String coachId;
    private Date startDate;
    private Date endDate;
    private static String schedule; // Training schedule placeholder
    private static final String SCHEDULE_FILE_PATH = "resources\\Schedules.txt";
    ArrayList<String> exercises = new ArrayList<>(); // Initialize exercises list

    // Constructor
    public TrainingPlan(String coachId, LocalDate startDate, int weeksnum) {
        this.scheduleId = generateScheduleId(); // Auto-increment Schedule ID

        this.coachId = coachId;
        this.startDate = java.sql.Date.valueOf(startDate); // Convert LocalDate to Date
        this.endDate = java.sql.Date.valueOf(calculateEndDate(startDate, weeksnum)); // Calculate and assign endDate
        //this.schedule = ""; 

        //ArrayList<String> exercises = new ArrayList<>(); // Initialize exercises list
    }
    
    // Generate auto-incremented schedule ID
    private String generateScheduleId() {
        return "s-" + (++idCounter);
    }
    // Method to calculate end date by adding weeks to start date
    private LocalDate calculateEndDate(LocalDate startDate, int weeksnum) {
        return startDate.plusWeeks(weeksnum);
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
    
    // Method to load the schedule from a text file
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
    // Method to save the schedule and exercises to a text file in a specific format
    public void saveScheduleAndExercisesToFile() throws IOException {
        try (FileWriter writer = new FileWriter(SCHEDULE_FILE_PATH)) {
            for (String exercise : exercises) {
                String line = this.scheduleId + "/" + this.coachId + "/" + exercise + "/" + this.coachId + "/" + this.startDate + "/" + this.endDate ;
                writer.write(line);
            }
        }
    }
    
    // Method to add a new exercise to the list
    public void addExercise(String exercise) {
        if (exercise == null || exercise.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise cannot be null or empty");
        }
        this.exercises.add(exercise);
    }

    // Method to display all exercises
    public void displayExercisePlan() {
        System.out.println("Exercise Plan:");
        for (String exercise : exercises) {
            System.out.println("/" + exercise);
        }
    }

    // Getter for schedule
    public static String getSchedule() {
        return schedule;
    }

    // Getters for other attributes
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


/*public static void main(String[] args) {
    try {
        TrainingPlan plan = new TrainingPlan("c-1", LocalDate.of(2024, 12, 17), 4);

        plan.addExercise("Chest and Triceps/Warm-up: 10 minutes treadmill");
        plan.addExercise("Chest and Triceps/Bench Press: 4 sets of 12 reps");
        plan.addExercise("Chest and Triceps/Incline Dumbbell Press: 4 sets of 10 reps");
        plan.addExercise("Chest and Triceps/Triceps Dips: 3 sets of 15 reps");
        plan.addExercise("Back and Biceps/Warm-up: 10 minutes rowing machine");
        plan.addExercise("Back and Biceps/Pull-ups: 3 sets of 10 reps");
        plan.addExercise("Back and Biceps/Barbell Rows: 4 sets of 10 reps");
        plan.addExercise("Back and Biceps/Biceps Curl: 4 sets of 12 reps");

        plan.saveScheduleAndExercisesToFile();
        System.out.println("Schedule saved successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}*/
