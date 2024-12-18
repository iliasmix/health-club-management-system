package modules;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class TrainingPlan {
    private static int idCounter = 0; // Static counter for auto-incrementing IDs
    private String scheduleId;
    private String coachId;
    private Date startDate;
    private Date endDate;
    private  static String schedule; // Training schedule placeholder
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
       File file = new File(SCHEDULE_FILE_PATH);
       if(!file.exists()) {
        return ; 
    } 
       try (Scanner reader = new Scanner(file)) {
        if (reader.hasNextLine()){
            reader.nextLine();
        }
            StringBuilder scheduleBuilder = new StringBuilder();
            while (reader.hasNextLine()) {
                scheduleBuilder.append(reader.nextLine()).append(System.lineSeparator());
            }
            this.schedule = scheduleBuilder.toString().trim();
            System.out.println("Schedule loaded successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    // Method to save the schedule and exercises to a text file in a specific format
    public void saveScheduleAndExercisesToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULE_FILE_PATH,true))) {
            for (String exercise : exercises) {
                String line = this.scheduleId + "/" + this.coachId + "/" + exercise + "/" + this.startDate + "/" + this.endDate ;
                writer.println(line);
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
     
     
     
         public static void main(String[] args) {
             try {
                 TrainingPlan plan = new TrainingPlan("c-1", LocalDate.of(2024, 12, 17), 4);
         
                 // System.out.println("Adding exercises...");
                 // plan.addExercise("Warm-up: 10 minutes treadmill");
                 // plan.addExercise("Bench Press: 4 sets of 12 reps");
                 // plan.addExercise("Incline Dumbbell Press: 4 sets of 10 reps");
                 // plan.addExercise("Triceps Dips: 3 sets of 15 reps");
         
                 // System.out.println("Saving schedule and exercises to file...");
                 plan.loadScheduleFromFile();
                 
                 System.out.println("Loaded Schedule: \n"+ TrainingPlan.getSchedule());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
