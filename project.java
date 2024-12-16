// modules Folder
// ---------------------------------------------------------------------------
// Admin.java
package modules;
import java.util.ArrayList;
import services.Billing;
import services.FileHandler;


public class Admin extends User{
    private ArrayList<Member> members;
    private ArrayList<Coach> coaches;
    private ArrayList<Billing> bills;

    // Constructor
    public Admin(String username, String password, String email) {
        super(username, password, email);
        // Initialize ArrayLists
        this.members = new ArrayList<Member>();
        this.coaches = new ArrayList<Coach>();
        this.bills = new ArrayList<Billing>();
    }

    // Methods to manage members
    public void addMember(Member member) {
        if (member == null) {
            return;
        }
        // Add member to list
        members.add(member);
        // Save data
        FileHandler.saveMemberData(member);
        }

    public void removeMember(Member member) {
        // Check if the member is null
        if (member == null) {
            return;
        }
    
        // Check if the member exists in the list
        if (!members.contains(member)) {
            return;
        }
    
        // Remove the member from the list
        members.remove(member);    
        FileHandler.deleteMember(member.ID); //member.ID till we found out how we will handle this
    }
    

    public void updateMember(Member member, String newUsername) {
        // Validate the member object
        if (member == null) {
            return;
        }
    
        // Check if the member exists in the list
        if (!members.contains(member)) {
            return;
        }
    
        // Update member information
        if (newUsername != null && !newUsername.isEmpty()) {

            member.setUsername(newUsername);
        }
    }
    

    // Methods to manage coaches
    public void addCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return;
        }
        // Add coach to list
        coaches.add(coach);
        // Save data
        FileHandler.saveCoachData(coach);
    }

    public void removeCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return;
        }

        // Check if the member exists in the list
        if (!coaches.contains(coach)) {
            return;
        }
        // Remove coach assignments from members
        //need this if any thing happend to coach data type in member class
        // for (Member member : coach.getMembers()) {
        //     // Check if the member is assigned to the coach
        //     if (member.getCoach() != null && member.getCoach().equals(coach.getName())) {
        //         member.setCoach(null); // Clear the coach assignment for the member
        //     }
        // }
        // Remove the coach from the list
        coaches.remove(coach);
        // Save data (this could be saving to a database, file, etc.)
        // Save data
        FileHandler.deleteCoach(coach.ID);//coach.ID till we found out how we will handle this
    }

    public void updateCoach(Coach coach, String newName,String newPassword) {
        // Validate coach
        if (coach == null) {
            return;
        }
        // Check if the member exists in the list
        if (!coaches.contains(coach)) {
            return;
        }        
        // Update coach information
        coach.setName(newName);
        // Save data
        FileHandler.updateCoachInfo(coach.ID, newName,newPassword);
    }

    // Member-Coach assignment
    public void assignMemberToCoach(Member member, Coach coach) {
        // Validate coach
        if (coach == null || !coaches.contains(coach)) {
            return;
        }
    
        // Validate member
        if (member == null || !members.contains(member)) {
            return;
        }
    
        // Assign member to coach
        coach.assignMember(member);
    
        // Update member's coach
        member.setCoach(coach);
    
        // Save data (implementation needed)    
        }

    // Billing management
    public void createBill(Member member, int planMonths, int year, int month, int day) {
        Billing.createBill(ID, planMonths, year, month, day);
    }
    

    public ArrayList<Member> searchMembers(String keyword) {
        ArrayList<Member> matchingMembers = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchingMembers; // Return empty list if keyword is invalid
        }
    
        keyword = keyword.toLowerCase(); // Convert to lowercase for case-insensitive search
    
        for (Member member : members) {
            // Check for partial match in username or email
            if (member.getUsername().toLowerCase().contains(keyword)){
                matchingMembers.add(member);
            }
        }
    
        return matchingMembers; // Return the matching members list
    }
    
    public ArrayList<Coach> searchCoaches(String keyword) {
        ArrayList<Coach> matchingCoaches = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchingCoaches; // Return empty list if keyword is invalid
        }
    
        keyword = keyword.toLowerCase(); // Convert to lowercase for case-insensitive search
    
        for (Coach coach : coaches) {
            // Check for partial match in name or specialization
            if (coach.getName().toLowerCase().contains(keyword) ) {
                matchingCoaches.add(coach);
            }
        }
    
        return matchingCoaches; // Return the matching coaches list
    }
    
    // Reporting
    public void generateReports() {
        // Generate member statistics
        // Generate subscription statistics
        // Generate coach statistics
        // Generate billing statistics
    }

    // Subscription management
    
    // Getters
    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

    public ArrayList<Billing> getBills() {
        return bills;
    }
}

// --------------------------------------------------
// Coach.java
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
// --------------------------------------------------
// Member.java
package modules;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import services.FileHandler;

public class Member extends User {
    private Coach coach;
    private String coachId;
    private String schedule;
    private Subscription subscription;
    private ArrayList<String> notifications;
    private FileHandler fileHandler;

    public Member(String username, String password, String ID) {
        super(username, password, ID);
        this.notifications = new ArrayList<>();
        this.fileHandler = new FileHandler();
        FileHandler.loadMemberData();
        loadNotifications();
    }
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
      public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    // View subscription end date
    public void viewSubscriptionEndDate() {
        if (subscription != null && subscription.getEndDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Your subscription ends on: " + dateFormat.format(subscription.getEndDate()));
            
            // Check if subscription is about to expire
            Date currentDate = new Date();
            long daysUntilExpiry = (subscription.getEndDate().getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24);
            
            if (daysUntilExpiry <= 7 && daysUntilExpiry > 0) {
                System.out.println("WARNING: Your subscription will expire in " + daysUntilExpiry + " days!");
            } else if (daysUntilExpiry <= 0) {
                System.out.println("WARNING: Your subscription has expired!");
            }
        } else {
            System.out.println("No active subscription found.");
        }
    }

    // View coach and schedule
    public void viewCoachAndSchedule() {
        // Load coach information
        if (coachId != null && !coachId.isEmpty()) {
            ArrayList<String[]> schedules = fileHandler.loadScheduleData();
            System.out.println("\nYour Training Schedule:");
            System.out.println("Coach ID: " + coachId);
            
            boolean foundSchedule = false;
            String currentDay = "";
            
            for (String[] scheduleData : schedules) {
                if (scheduleData[1].equals(coachId)) {
                    foundSchedule = true;
                    if (!currentDay.equals(scheduleData[2])) {
                        currentDay = scheduleData[2];
                        System.out.println("\n" + currentDay + ":");
                    }
                    System.out.println("- " + scheduleData[3]);
                }
            }
            
            if (!foundSchedule) {
                System.out.println("No schedule found for your coach.");
            }
        } else {
            System.out.println("No coach assigned.");
        }
    }

    // Load notifications
    private void loadNotifications() {
        try {
            File file = new File("Notifications.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                // Skip header
                if (scanner.hasNextLine()) scanner.nextLine();
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split("/");
                    // Check if notification is for this member
                    if (data[2].equals(this.getID())) {
                        notifications.add(data[3]); // Add message content
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading notifications: " + e.getMessage());
        }
    }

    // Check subscription status and send notification if expired
    public void checkSubscriptionStatus() {
        if (subscription != null && subscription.getEndDate() != null) {
            Date currentDate = new Date();
            if (currentDate.after(subscription.getEndDate())) {
                // Add expiration notification
                String notification = "Your subscription has expired!";
                notifications.add(notification);
                
                // Save notification to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Notifications.txt", true))) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    writer.write(String.format("n-%d/0/%s/%s/%s\n", 
                        notifications.size() + 1, 
                        this.getID(), 
                        notification,
                        dateFormat.format(currentDate)));
                } catch (IOException e) {
                    System.out.println("Error saving notification: " + e.getMessage());
                }
            }
        }
    }

    // Other existing methods...

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = subscription != null && subscription.getStartDate() != null ? 
            dateFormat.format(subscription.getStartDate()) : "";
        String endDate = subscription != null && subscription.getEndDate() != null ? 
            dateFormat.format(subscription.getEndDate()) : "";
        String status = subscription != null ? String.valueOf(subscription.isActive()) : "";
        
        return String.format("%s/%s/%s/%s/%s/%s/%s/%s",
            getID(),
            getUsername(),
            getPassword(),
            coachId != null ? coachId : "",
            startDate,
            endDate,
            status,
            schedule != null ? schedule : ""
        );
    }
}
// ---------------------------------------------------------------------------------------
// User.java
package modules;

import java.io.*; // import all of the built in functions of the input output
import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;


//  NOTE :  I removed all the email functions as you asked because its unnecessary and will complicate the porject
public class User {
    // it was private, the reason for mohamed huissen errors, you can NOT inherit private..!
    protected String username;
    protected String password;
    protected String ID;
        

    public User(String username, String password, String ID) {
        this.username = username;
        this.password = password;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {return this.ID;}
   
    public static String[] login(String username, String password) {
        // Try Members first
        try (BufferedReader reader = new BufferedReader(new FileReader("Members.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Member"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Members file: " + e.getMessage());
        }

        // Try Coaches
        try (BufferedReader reader = new BufferedReader(new FileReader("Coaches.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Coach"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Coaches file: " + e.getMessage());
        }

        // Try Admins
        try (BufferedReader reader = new BufferedReader(new FileReader("Admins.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Admin"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Admins file: " + e.getMessage());
        }

        return null; // Return null if no match found
    }

    // Logout function
    public void logout() {
        if (this.username != null) {
            System.out.println("User " + this.username + " has been logged out.");
            this.username = null; // Clear the current logged-in user
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public static boolean updateProfile(String userId, String newUsername, String newPassword, String userType) {
        String fileName;
        switch (userType.toLowerCase()) {
            case "member":
                fileName = "Members.txt";
                break;
            case "coach":
                fileName = "Coaches.txt";
                break;
            case "admin":
                fileName = "Admins.txt";
                break;
            default:
                return false;
        }

        ArrayList<String> fileContent = new ArrayList<>();
        boolean updated = false;

        // Read and update the file content
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            fileContent.add(reader.readLine()); // Add header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                if (data[0].equals(userId)) {
                    // Update username and password while keeping ID and other fields unchanged
                    data[1] = newUsername;
                    data[2] = newPassword;
                    line = String.join("/", data);
                    updated = true;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        // Write back to file if updated
        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : fileContent) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    // Example usage
    // public static void main(String[] args) {
    //     // Test login
    //     String[] loginResult = login("member1", "1234");
    //     if (loginResult != null) {
    //         System.out.println("Login successful!");
    //         System.out.println("User ID: " + loginResult[0]);
    //         System.out.println("User Type: " + loginResult[1]);

    //         // Test update profile with both username and password
    //         boolean updateResult = updateProfile(loginResult[0], "newUsername", "newPassword", loginResult[1]);
    //         if (updateResult) {
    //             System.out.println("Profile updated successfully!");
    //         } else {
    //             System.out.println("Failed to update profile.");
    //         }
    //     } else {
    //         System.out.println("Login failed!");
    //     }
    // }
}

// ---------------------------------------------------------------------------------------
// Subscription.java
package modules;
import java.util.Date;

public class Subscription {
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    // Constructor
    public Subscription(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    // Getters and Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Methods
    public void renewSubscription(Date newEndDate) {
        this.endDate = newEndDate;
        this.isActive = true;
    }

    public boolean checkIfExpired() {
        Date currentDate = new Date();
        return currentDate.after(endDate);
    }
}

// --------------------------------------------------------------------
// TrainingPlan.java
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
    private static String schedule; // Training schedule placeholder
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
    public static String getSchedule() {
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

// --------------------------------------------------------------------

// -----------------------------------------------------------------------------------------------------
// Billing.java
package services;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Billing {

    private static int billIdIncrementor = getNextBillIdNumber();

    public static void createBill(String memberId, int planMonths, int year, int month, int day) {
        String billId = "b-" + billIdIncrementor;
        billIdIncrementor++;

        //Check if the member's subscription is already active
        if(NotificationSystem.isSubscriptionActive(memberId)) {
            System.out.println("Member's subscription is already active!");
            return;
        }

        int price = getPlanPrice(planMonths);
        if(price == -1) {
            System.err.println("Failed to create a bill for member " + memberId);
        }

        LocalDate startDate = LocalDate.of(year, month, day);
        LocalDate endDate = startDate.plusMonths(planMonths);

        File billsFile = new File("resources\\Bills.txt");
        try(PrintWriter billsOutput = new PrintWriter(new FileWriter(billsFile, true))) {
            billsOutput.println(billId + "/" + memberId + "/" + planMonths + (planMonths > 1? " months": " month") + "/" + startDate + "/" + endDate + "/" + price + "/" + new Date());
            System.out.println("Bill created successfully!");
            Billing.updateMemberSubscriptionData(memberId, startDate, endDate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getPlanPrice(int planMonths) {
        File subscriptionsFile = new File("resources\\Subscriptions.txt");

        if(!subscriptionsFile.exists()) {
            return -1; //Let the default be 1 month
        }

        try(Scanner subscriptionsScan = new Scanner(subscriptionsFile)) {

            //Skip the first line as it is the file guideline.
            if(subscriptionsScan.hasNextLine()) {
                subscriptionsScan.nextLine();
            }

            while(subscriptionsScan.hasNext()) {
                String[] parts = subscriptionsScan.nextLine().split("/");

                //If the first part (Months count) equals plan months return the second part (the price)
                if(parts[0].equals(planMonths + "")) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!" + e.getMessage());
            return -1;
        }

        return -1; //If everything fails
    }

    public static void updateMemberSubscriptionData(String memberId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException {
        File membersFile = new File("resources\\Members.txt");
        Scanner membersScan = new Scanner(membersFile);

        if(NotificationSystem.isSubscriptionActive(memberId)) {
            return;
        }

        if(membersScan.hasNextLine()) {
            membersScan.nextLine();
        }

        File tempMembers = new File("resources\\tempMembers.txt");
        try (PrintWriter tempOutput = new PrintWriter(new FileWriter(tempMembers, true))) {
            tempOutput.println("Member ID/Member Username/Member Pass/Member's Coach ID/Subscription Start Date/Subscription End Date/Schedule ID");
            while(membersScan.hasNext()) {
                String[] parts = membersScan.nextLine().split("/");
                if(memberId.equals(parts[0])) {
                    parts[4] = startDate.toString();
                    parts[5] = endDate.toString();
                }
                tempOutput.println(String.join("/", parts));
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        membersScan.close();

        if(!membersFile.delete()) {
            System.out.println("Failed to delete members file!");
        }
        if(!tempMembers.renameTo(membersFile)) {
            System.out.println("Failed to rename temp to members.");
        }
    }

    private static int getNextBillIdNumber() {
        File billsFile = new File("resources\\Bills.txt");

        if(!billsFile.exists()) {
            return 1; //Start with ID 1 if the bills file doesn't exist
        }

        try(Scanner billsScanner = new Scanner(billsFile)) {



            //Skip the first line as it is the guideline.
            if(billsScanner.hasNextLine()) {
                billsScanner.nextLine();

                //Check if the file is empty
                if(!billsScanner.hasNext()) {
                    return 1; //Start with bill ID 1
                }
            }

            String lastId = "";
            while(billsScanner.hasNext()) {
                String[] parts = billsScanner.nextLine().split("/");
                lastId = parts[0]; //This is b-x, where x is an integer
            }

            String[] idParts = lastId.split("-"); //Split b-x into b and x

            //return x after converting it from a string to an int and add 1 to go to the next ID
            int lastIdNum = Integer.parseInt(idParts[1]);
            return lastIdNum + 1;

        } catch (FileNotFoundException ex) {
            System.err.println("Unable to read: File not found " + ex.getMessage());
            return 0;
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// FileHandler.java
package services;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import modules.Coach;
import modules.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * FileHandler class manages all file operations for the Health Club Management System.
 * This class provides methods to save and load data for all system entities.
 * 
 * File Format Specifications:
 * - All data is stored in forward-slash separated format
 * - Members format: Member ID/Member Username/Member Pass/Member's Coach ID/Subscription Start Date/Subscription End Date/Subscription Status/Schedule ID
 * - Coaches format: Coach ID/Coach Username/Coach Pass/Schedule ID
 * - Schedules format: Schedule ID/Coach ID/Day/Exercise/Schedule Start Date/Schedule End Date
 * - Bills format: Bill ID/Member ID/Plan/Start Date/End Date/Price/Bill Generation Time
 */
public class FileHandler {
    // ==================== File Path Constants ====================
    private static final String MEMBERS_FILE = "resources\\Members.txt";
    private static final String COACHES_FILE = "resources\\Coaches.txt";
    private static final String SCHEDULES_FILE = "resources\\Schedules.txt";
    private static final String BILLING_FILE = "resources\\Bills.txt";
    private static final String SUBSCRIPTIONS_FILE = "resources\\Subscriptions.txt";

    // ==================== Member Operations ====================
    public static void saveMemberData(Member member) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE, true))) {
            writer.println(member.toString());
        } catch (IOException e) {
            System.err.println("Error saving member data: " + e.getMessage());
        }
    }

    public static ArrayList<Member> loadMemberData() {
        ArrayList<Member> members = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MEMBERS_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split("/");
                    if (data.length == 8) {
                        Member member = new Member(data[1], data[2], data[0]);
                        member.setCoachId(data[3]);
                        member.setSchedule(data[7]);
                        members.add(member);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading member data: " + e.getMessage());
        }
        return members;
    }

    public static void deleteMember(String memberId) {
        try {
            File inputFile = new File(MEMBERS_FILE);
            File tempFile = new File("resources\\TempMembers.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and write lines, skipping the member to be deleted
            boolean memberFound = false;
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (!data[0].equals(memberId)) {
                        writer.println(currentLine);
                    } else {
                        memberFound = true;
                    }
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (memberFound) {
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("Member not found!");
            }

        } catch (IOException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }

    // ==================== Coach Operations ====================
    public static void saveCoachData(Coach coach) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COACHES_FILE, true))) {
            writer.println(coach.toString());
        } catch (IOException e) {
            System.err.println("Error saving coach data: " + e.getMessage());
        }
    }

    public static ArrayList<Coach> loadCoachData() {
        ArrayList<Coach> coaches = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COACHES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split("/");
                    if (data.length == 4) {
                        Coach coach = new Coach(data[1], data[2], data[0]);
                        //coach.setScheduleId(data[3]);
                        coaches.add(coach);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading coach data: " + e.getMessage());
        }
        return coaches;
    }

    public static void deleteCoach(String coachId) {
        try {
            File inputFile = new File(COACHES_FILE);
            File tempFile = new File("resources\\TempCoaches.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and write lines, skipping the coach to be deleted
            boolean coachFound = false;
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (!data[0].equals(coachId)) {
                        writer.println(currentLine);
                    } else {
                        coachFound = true;
                    }
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (coachFound) {
                System.out.println("Coach deleted successfully!");
            } else {
                System.out.println("Coach not found!");
            }

        } catch (IOException e) {
            System.out.println("Error deleting coach: " + e.getMessage());
        }
    }

    // ==================== Schedule Operations ====================
    public static void saveScheduleData(String scheduleData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULES_FILE, true))) {
            writer.println(scheduleData);
        } catch (IOException e) {
            System.err.println("Error saving schedule data: " + e.getMessage());
        }
    }

    public ArrayList<String[]> loadScheduleData() {
        ArrayList<String[]> schedules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SCHEDULES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] scheduleData = line.split("/");
                    schedules.add(scheduleData);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading schedule data: " + e.getMessage());
        }
        return schedules;
    }

    // ==================== Billing Operations ====================
    public static void saveBillingData(String billingData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BILLING_FILE, true))) {
            writer.println(billingData);
        } catch (IOException e) {
            System.err.println("Error saving billing data: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadBillingData() {
        ArrayList<String> bills = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(BILLING_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    bills.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading billing data: " + e.getMessage());
        }
        return bills;
    }

    // ==================== Subscription Operations ====================
    public static void saveSubscriptionData(String subscriptionData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SUBSCRIPTIONS_FILE, true))) {
            writer.println(subscriptionData);
        } catch (IOException e) {
            System.err.println("Error saving subscription data: " + e.getMessage());
        }
    }

    public static ArrayList<String[]> loadSubscriptionData() {
        ArrayList<String[]> subscriptions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SUBSCRIPTIONS_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] subscriptionData = line.split("/");
                    subscriptions.add(subscriptionData);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading subscription data: " + e.getMessage());
        }
        return subscriptions;
    }

    // ==================== Admin Operations ====================

    public static void updateMemberInfo(String memberId, String newUsername, String newPassword) {
        try {
            File inputFile = new File(MEMBERS_FILE);
            File tempFile = new File("resources\\TempMembers.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and update data
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (data[0].equals(memberId)) {
                        // Update member info
                        data[1] = newUsername;
                        data[2] = newPassword;
                        currentLine = String.join("/", data);
                    }
                    writer.println(currentLine);
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("Member information updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
    }

    public static void updateCoachInfo(String coachId, String newUsername, String newPassword) {
        try {
            File inputFile = new File(COACHES_FILE);
            File tempFile = new File("resources\\TempCoaches.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and update data
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (data[0].equals(coachId)) {
                        // Update coach info
                        data[1] = newUsername;
                        data[2] = newPassword;
                        currentLine = String.join("/", data);
                    }
                    writer.println(currentLine);
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("Coach information updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating coach: " + e.getMessage());
        }
    }

    
    public static boolean isMemberAlreadyInTheSystem(String memberID) throws FileNotFoundException {
        File membersFile = new File(MEMBERS_FILE);
        try(Scanner membersScan = new Scanner(membersFile)) {
            if (membersScan.hasNextLine()) {
                membersScan.nextLine();
            }
            while (membersScan.hasNext()) {
                String[] parts = membersScan.nextLine().split("/");
                if (memberID.equals(parts[0])) return true;
            }
        }
        return false;
    }

    public static boolean isCoachAlreadyInTheSystem(String coachID) throws FileNotFoundException {
        File coachesFile = new File(COACHES_FILE);
        try(Scanner coachesScan = new Scanner(coachesFile)) {
            if (coachesScan.hasNextLine()) {
                coachesScan.nextLine();
            }
            while (coachesScan.hasNext()) {
                String[] parts = coachesScan.nextLine().split("/");
                if (coachID.equals(parts[0])) return true;
            }
        }
        return false;
    }
}

// ----------------------------------------------------------------------------------------
// NotificationSystem.java
package services;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class NotificationSystem {
    private static int notificationId = getNextNotificationIdNum();

    public static void sendSubscriptionExpiryNotification(String memberId) throws FileNotFoundException {
        // Create expiry notification message (Pop up)
        System.out.println("Pop up message!");

        //Save messages to Notifications.txt
        File file = new File("resources\\Notifications.txt");
        try(PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
            // Send notification to member
            output.println("n-" + notificationId + "/0/" + memberId + "/Your subscription has expired!/" + new Date());
            notificationId++;
            // Send the notification to the admin
            output.println("n-" + notificationId + "/0/" + "a-1" + "/Member " + memberId+ "'s subscription has expired!/" + new Date());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean isSubscriptionActive(String memberId) {
        File membersFile = new File("resources\\Members.txt");
            try (Scanner membersScan = new Scanner(membersFile)) {

                if (membersScan.hasNext()) {
                    membersScan.nextLine();

                    while (membersScan.hasNext()) {

                        String[] parts = membersScan.nextLine().split("/");

                        if (memberId.equals(parts[0])) {
                            String expiryDateString = parts[5];

                            String[] dateParts = expiryDateString.split("-");
                            int expiryYear = Integer.parseInt(dateParts[0]);
                            int expiryMonth = Integer.parseInt(dateParts[1]);
                            int expiryDay = Integer.parseInt(dateParts[2]);

                            LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, expiryDay);
                            LocalDate today = LocalDate.now();

                            if (expiryDate.isAfter(today)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // If the member wasn't found
        return false;
    }

    public static void sendMessage(String senderId, String receiverId, String message) throws FileNotFoundException {
        File notificationsFile = new File("resources\\Notifications.txt");

        try(PrintWriter output = new PrintWriter(new FileWriter(notificationsFile, true))) {
            output.println("n-"+ notificationId + "/" + senderId + "/" + receiverId + "/" + message + "/" + new Date());
            notificationId++;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int getNextNotificationIdNum() {
        File notificationsFile = new File("resources\\Notifications.txt");

        if(!notificationsFile.exists()) {
            return 1; //Start with ID 1 if the bills file doesn't exist
        }

        try(Scanner notificationsScanner = new Scanner(notificationsFile)) {



            //Skip the first line as it is the guideline.
            if(notificationsScanner.hasNextLine()) {
                notificationsScanner.nextLine();

                //Check if the file is empty
                if(!notificationsScanner.hasNext()) {
                    return 1; //Start with bill ID 1
                }
            }

            String lastId = "";
            while(notificationsScanner.hasNext()) {
                String[] parts = notificationsScanner.nextLine().split("/");
                lastId = parts[0]; //This is b-x, where x is an integer
            }

            String[] idParts = lastId.split("-"); //Split b-x into b and x

            //return x after converting it from a string to an int and add 1 to go to the next ID
            int lastIdNum = Integer.parseInt(idParts[1]);
            return lastIdNum + 1;

        } catch (FileNotFoundException ex) {
            System.err.println("Unable to read: File not found " + ex.getMessage());
            return 0;
        }
    }
}
// ----------------------------------------------------------------------------------------
// TestBills.java
package services;

import java.io.FileNotFoundException;
import java.util.*;

public class TestBills {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------");
        System.out.println("Create a Bill:");
        System.out.println("--------------");


        String memberID;
        do {
            System.out.print("Enter the member's ID (m-x), 0 to terminate: ");
             memberID = input.next();

             if(memberID.equals("0")) {
                 System.out.println("Ending the process...");
                 System.exit(0);
             }
             else if(!memberID.matches("m-\\d+$")) {
                 System.out.println("The member ID must start with an m- then a number!!");
             }
             else if(!FileHandler.isMemberAlreadyInTheSystem(memberID)) {
                 System.out.println("There is no member with ID " + memberID + " in the system!");
             }
             else if(NotificationSystem.isSubscriptionActive(memberID)) {
                 System.out.println("This member's subscription is active!");
             }
        } while (!memberID.matches("m-\\d+$") || NotificationSystem.isSubscriptionActive(memberID) || !FileHandler.isMemberAlreadyInTheSystem(memberID));

        int planMonths;
        do {
            System.out.print("Enter the plan months (1 or 2 or 3 or 6 or 12): ");
            planMonths = input.nextInt();

            if(planMonths != 1 && planMonths != 2 && planMonths != 3 && planMonths != 6 && planMonths != 12) {
                System.out.println("Invalid input!");
            }
        } while(planMonths != 1 && planMonths != 2 && planMonths != 3 && planMonths != 6 && planMonths != 12);

        int startYear;
        do {
            System.out.print("Enter the plan start year: ");
            startYear = input.nextInt();

            if(startYear < 2024) {
                System.out.println("This gym was founded in 2024, how are you attempting to add a year before 2024!!!");
            }
        } while (startYear < 2024);

        int startMonth;
        do {
            System.out.print("Enter the plan start month: ");
            startMonth = input.nextInt();

            if(startMonth < 1 || startMonth > 12) {
                System.out.println("Invalid input! Only 1 to 12 is accepted!");
            }
        } while(startMonth < 1 || startMonth > 12);

        int startDay;
        do {
            System.out.print("Enter the plan start day: ");
            startDay = input.nextInt();

            if(startDay < 1 || startDay > 31) {
                System.out.println("Invalid input! Only 1 to 31 is accepted!");
            }
        } while(startDay < 1 || startDay > 31);


        Billing.createBill(memberID, planMonths, startYear, startMonth, startDay);
    }
}

// ----------------------------------------------------------------------------------------
// TestNotifications.java
package services;

import modules.Admin;
import modules.Member;

import java.io.*;
import java.util.*;

public class TestNotifications {
    public static void main(String[] args) throws FileNotFoundException {
        //Admin a = new Admin("Admin", "1111", "a-1");
        //Member m1 = new Member("Ahmad", "1234", "m-1");
        //Member m2 = new Member("Mohammad", "1122", "m-2");

//        NotificationSystem.sendSubscriptionExpiryNotification(a, m1);
//        NotificationSystem.sendSubscriptionExpiryNotification(a, m2);

        //NotificationSystem.sendMessage("a-1","m-1", "Hi there m1!");
        //NotificationSystem.sendMessage("a-1", "m-2", "Hi there m2!");

        //System.out.println(FileHandler.isMemberAlreadyInTheSystem("m-2"));
        //System.out.println(FileHandler.isCoachAlreadyInTheSystem("c-5"));
        //System.out.println(FileHandler.isCoachAlreadyInTheSystem("c-1"));

        if(!NotificationSystem.isSubscriptionActive("m-1")) {
            NotificationSystem.sendSubscriptionExpiryNotification("m-1");
        }
        if(!NotificationSystem.isSubscriptionActive("m-2")) {
            NotificationSystem.sendSubscriptionExpiryNotification("m-2");
        }

    }
}
// -----------------------------------------------------------------------------------------------
//////////////////////////////////////// Main.java ////////////////////////////////////////////////
package main;

import java.util.Scanner;

/**
 * Entry point of the Health Club Management System.
 * This class initializes the system, displays the menu, and handles user
 * interactions.
 */
import modules.Admin;
import modules.Member;
import modules.Coach;
import services.FileHandler;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            // Show the main menu options to the user.
            displayMenu();
            // Get the user's choice from the console input (e.g., Admin, Coach, Member, or
            // Exit).

            // Depending on the user's choice, direct them to the appropriate module:
            switch (getUserInput()) {
                case 1:
                    System.out.println("Enter userName ,password,ID");
                    Admin admin = new Admin(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- addMember \n2- removeMember \n3- updateMember \n4- addCoach \n5- removeCoach \n6- updateCoach \n7- assignMemberToCoach ");

                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's userName ,password,ID");
                            admin.addMember(new Member(input.nextLine(), input.nextLine(), input.nextLine()));
                            break;
                        case 2:
                            System.out.println("Enter members's ID");
                            // admin.removeMember(input.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter members's ID,newUserName ,Email");
                            // admin.updateMember(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter coach's userName ,password,ID");
                            admin.addCoach(new Coach(input.nextLine(), input.nextLine(), input.nextLine()));
                            break;
                        case 5:
                            System.out.println("Enter coach's ID");
                            // admin.removeCoach(input.nextLine());
                            break;
                        case 6:
                            System.out.println("Enter coach's ID,newUserName ,Email");
                            // admin.updateCoach(input.nextLine(), input.nextLine(), input.nextLine());
                            break;
                        case 7:
                            System.out.println("Enter member's ID and coach's ID");
                            // admin.assignMemberToCoach(input.nextLine(), input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter userName ,password,ID");
                    Coach coach = new Coach(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- create Training Plan \n2- add Exercise To Training Plan \n3- set Member Schedule \n4- send Message To Coach Members \n");
                    switch (getUserInput()) {
                        case 1:
                            System.out.println("Enter members's ID ,start date,end date");
                            // coach.createTrainingPlan(input.nextLine(), input.nextLine(),
                            // input.nextLine());
                            break;
                        case 2:
                            System.out.println("Enter members's ID,exercise ");
                            // coach.addExerciseToTrainingPlan(input.nextLine(),
                            // input.nextLine(),input.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter members's ID,schedule");
                            // coach.setMemberSchedule(input.nextLine(), input.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter message,coach's ID");
                            // coach.sendMessageToCoachMembers(input.nextLine(), input.nextLine());
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Enter userName ,password,ID");
                    Member member = new Member(input.nextLine(), input.nextLine(), input.nextLine());
                    System.out.println(
                            "choose operation : \n1- view Subscription Details \n2- view Schedule \n3- view Training Plan \n");
                    switch (getUserInput()) {
                        case 1:
                            // member.viewSubscriptionDetails();
                            break;
                        case 2:
                            // member.viewSchedule();
                            break;
                        case 3:
                            // member.viewTrainingPlan();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Exiting the Health Club Management System. Goodbye!");
                    break;

                default:
                    // Inform the user if they enter an invalid option and prompt them to try again.
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Once the loop exits, perform any necessary cleanup, such as saving data to
        // files.
        // system.cleanup();
    }

    /**
     * Displays the main menu options to the user.
     * The menu includes options for admin, coach, and member functionalities, as
     * well as exiting.
     */
    private static void displayMenu() {
        System.out.println("\n==== Health Club Management System ====");
        System.out.println("1. Admin Login");
        System.out.println("2. Coach Login");
        System.out.println("3. Member Login");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Reads and validates the user's input from the console.
     * Ensures the input is a valid integer corresponding to menu options.
     * 
     * @return the validated choice as an integer.
     */
    private static int getUserInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;

        try {
            // Read the input and parse it as an integer.
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // If the input is invalid (not an integer), default to -1 (invalid option).
            choice = -1;
        }

        return choice;
    }

}
/////////////////////////////////////////////////////// Text Files for data (raw data format) ///////////////////////////////////////////////////////////
Admin.txt File: Admin ID/Admin Username/Admin Pass
Bills.txt File: Bill ID (b-1, b-2, ...etc)/Member ID/Plan (3 months - 6months ... etc.)/Start Date/End Date/Price/Bill Generation Time
Coach.txt File: Coach ID/Coach Username/Coach Pass/Schedule ID
Member.txt File: Member ID/Member Username/Member Pass/Member's Coach ID/Subscription Start Date/Subscription End Date/Schedule ID
Notification.txt File: Notification ID (n-1 n-2, ... etc.)/sender ID/Receiver ID/Notification Message/Notification Date
Report.txt File: Report ID (rp-1, rp-2, ... etc)/Report Content/Admin ID
Schedule.txt File: Schedule ID/Coach ID/Day/Exercise/Schedule Start Date/Schedule End Date
subscriptions.txt File: month(s)/price
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// readme FIle
# Health Club Management System 💪

![](./resources/image.png)

## Description 📝

The **Health Club Management System** is a Java-based application designed to help health clubs manage their operations. It includes features for tracking members, assigning coaches, and managing subscriptions. The system does not use a database but instead uses text files for storing data. 📂

The system includes:

- **Admin functionalities**: Add new members, assign coaches, and manage subscriptions.
- **Member functionalities**: Add, update, and display member details.
- **Coach functionalities**: Track which members are assigned to which coaches.
- **Subscription management**: Track and manage membership status. 📅

## Features ✨

- **Admin Features:**

  - 🆕 Add new members.
  - 💪🏻 Assign coaches to members.
  - 💳 Manage subscriptions (add, update, delete).

- **Member Features:**

  - ✍️ Add, update, or display member data.
  - 📅 Track subscription end dates.

- **Coach Features:**

  - 🧑‍🏫 Assign multiple members to coaches.
  - 📋 Display coach schedules (could be added in future versions).

- **File-based storage**: Data is stored in text files (e.g., `MemberData.txt`, `CoachData.txt`, `AdminData.txt`). 📄

## Folder Structure 📁

The project follows this folder structure:

```scss
health-club-management-system/
├── src/                            # Source code folder
│   ├── models/                       # Data model classes (defines entities)
│   │   ├── User.java                 # Base class for users (Admin, Coach, Member)
│   │   ├── Member.java               # Member class (manages subscription, coach assignments)
│   │   ├── Coach.java                # Coach class (manages training plans, schedules)
│   │   ├── Admin.java                # Admin class (system management, billing, reports)
│   │   ├── Subscription.java         # Subscription class (handles start/end, renewal)
│   │   └── TrainingPlan.java         # TrainingPlan class (manages workout schedules)
│   ├── services/                   # Business logic and services
│   │   ├── Billing.java              # Manages member payments and subscriptions
│   │   ├── NotificationSystem.java   # Sends system notifications (e.g., expiring subscriptions)
│   │   └── FileHandler.java          # Handles file I/O for persisting data
│   ├── gui/                        # GUI-related components and forms
│   │   ├── LoginForm.java            # Login form GUI
│   │   ├── AdminDashboard.java       # Admin dashboard GUI
│   │   ├── CoachDashboard.java       # Coach dashboard GUI
│   │   ├── MemberDashboard.java      # Member dashboard GUI
│   │   └── MainMenu.java             # Main menu or landing page GUI
│   ├── HealthClubSystem.java       # Integrates and coordinates system functionality
│   └── Main.java                   # Entry point to initialize and run the system
├── resources/                      # Data files for persistence
│   ├── MemberData.txt                # Stores member information
│   ├── CoachData.txt                 # Stores coach information
│   └── AdminData.txt                 # Stores admin credentials and data
├── .gitignore                      # Specifies files/folders to be ignored by Git
└── README.md                       # Project documentation and setup instructions
```

## **Description of the Folder Structure:**

### **1. `src/`** — Source Code Folder 🖥️

- **models/**: Contains the data model classes that define the entities of the system, encapsulating the core properties and behaviors.
  - **User.java**: Serves as the base class for different types of users (Admin, Coach, Member), managing common properties such as name and ID.
  - **Member.java**: Represents a member of the health club, managing subscription details, coach assignments, and membership-related functions.
  - **Coach.java**: Manages coach-specific tasks such as creating and assigning training plans to members, and tracking their schedule.
  - **Admin.java**: Defines the admin role, including functionalities like managing users, generating reports, and overseeing system activities.
  - **Subscription.java**: Manages subscription-related logic, including the start, end, renewal, and status of member subscriptions.
  - **TrainingPlan.java**: Defines training plans for members, specifying workout routines, goals, and schedules.
  
- **services/**: Contains the business logic and services that implement the core functionalities of the system.
  - **Billing.java**: Manages the financial transactions within the system, including handling payments and managing subscription fees.
  - **NotificationSystem.java**: Sends notifications to users, such as reminders for expiring subscriptions, payment alerts, or system updates.
  - **FileHandler.java**: Handles reading and writing data to text files, ensuring that member, coach, and admin information is persisted.

- **gui/**: GUI-Related Components and Forms 🖼️
  - **LoginForm.java**: Provides the login interface for all users (Admin, Coach, Member) to securely access the system. It includes fields for entering credentials and authentication logic.
  - **AdminDashboard.java**: Represents the main interface for administrators, where they can manage members, assign coaches, and handle subscriptions.
  - **CoachDashboard.java**: Serves as the main interface for coaches, allowing them to view and manage their assigned members and training schedules.
  - **MemberDashboard.java**: Provides an interface for members to view their profiles, update details, and check subscription statuses.
  - **MainMenu.java**: Acts as the main entry point for navigating the system. It includes options for accessing specific dashboards based on the user role (Admin, Coach, or Member).

- **HealthClubSystem.java**: The main controller of the system, integrating the various components (users, subscriptions, billing, etc.) and orchestrating the system’s operations.
  
- **Main.java**: The entry point of the application that initializes the system and runs the main program logic.

### **2. `resources/`** — Data Storage Files 💾

- **MemberData.txt**: Stores member data such as personal information, subscription status, and expiry dates. It helps in persistent storage of member records.
- **CoachData.txt**: Stores data related to coaches, such as their qualifications, schedules, and the members they are assigned to train.
- **AdminData.txt**: Stores admin-related credentials and configurations, including login information and system settings.

## Why This Structure?

- **Separation of Concerns**: By organizing the code into logical sections based on user roles and functionalities (Admin, Coach, Member), it simplifies maintenance and development.
- **Data Persistence with File-Based Storage**: Storing data in text files is simple and avoids the complexity of database setup, making it easy to manage for a small-scale system.
- **Clear Navigation**: The folder structure is clear and intuitive, allowing developers to easily find and modify specific components.
- **Scalability**: The modular approach allows for easy scalability, where new functionalities or user roles can be added with minimal disruption to the existing codebase.

This structure ensures a clean, organized, and scalable health club management system that is easy to understand and work with for developers. 🚀


## Technologies 🛠️

- **Programming Language**: Java ☕
- **Storage**: Text files for storing member, coach, and admin data (no database). 📂
- **GUI Framework**: Java Swing using NetBeans Design Tool for creating graphical interfaces. 🖼️
- **Libraries/Tools**:
  - Standard Java libraries for file I/O and system operations.
  - NetBeans IDE for GUI design and development.

## Getting Started 🚀

### Prerequisites 🖥️

- Java Development Kit (JDK) version 8 or higher installed.
- JavaFX SDK installed ([Download](https://openjfx.io)).
- A GitHub account (for version control and collaboration).

### Installation 🔧

1. Clone the repository:
   ```bash
   git clone https://github.com/DORMODO/health-club-management-system.git
   ```
2. Set up the JavaFX SDK in your IDE.
3. Run the application:
     - Use the Main.java file as the entry point.

























































     