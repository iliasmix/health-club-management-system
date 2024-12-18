package modules;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import services.Billing;
import services.FileHandler;


public class Admin extends User{
    // Constructor
    public Admin(String username, String password, String ID) {
        super(username, password, ID);
    }

    // Methods to manage members
    public void addMember(Member member) {
        if (member == null) {
            return;
        }
        // Save data
        FileHandler.saveMemberData(member);
        }

    public void removeMember(Member member) {
        // Check if the member is null
        if (member == null) {
            return;
        }
    
        // Check if the member exists in the list

        try {
            if (!FileHandler.isMemberAlreadyInTheSystem(member.ID)) {

            return;
        }
        FileHandler.deleteMember(member.ID);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    

    public void updateMember(Member member, String newUsername, String password) {
        // Validate the member object
        if (member == null) {
            return;
        }
    
        try {
            // Retrieve the member's ID
            String memberId = member.ID;  
            // Check if the member exists in the list
            if (!FileHandler.isMemberAlreadyInTheSystem(member.ID)) {
                System.out.println("member with ID " + member.ID + " already exists in the system.");
                return; // If member is not found, exit
            }
    
            // Update member information
            if (newUsername != null && !newUsername.isEmpty()) {
                member.setUsername(newUsername);
                // Update member info in the system
                FileHandler.updateMemberInfo(member.ID, newUsername, password);
            }
    
        } catch (Exception e) {
            // Handle any exceptions that occur
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    

    // Methods to manage coaches
    public void addCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return; // Exit if the coach object is invalid
        }
        try {
            // Check if the coach already exists in the system
            if (FileHandler.isCoachAlreadyInTheSystem(coach.ID)) {
                System.out.println("Coach with ID " + coach.ID + " already exists in the system.");
                return; // Exit if the coach already exists
            }
            // Save the coach data if they don't already exist
            FileHandler.saveCoachData(coach);
        } catch (Exception e) {
            // Handle any exceptions and log the error
            System.out.println("An error occurred while adding the coach: " + e.getMessage());
        }
    }
    

    public void removeCoach(Coach coach) {
        // Validate coach
        if (coach == null) {
            return;
        }
        // Check if the member exists in the list
        // Remove coach assignments from members
        //need this if any thing happend to coach data type in member class
        // for (Member member : coach.getMembers()) {
        //     // Check if the member is assigned to the coach
        //     if (member.getCoach() != null && member.getCoach().equals(coach.getName())) {
        //         member.setCoach(null); // Clear the coach assignment for the member
        //     }
        // }
        // Save data
        FileHandler.deleteCoach(coach.ID);//coach.ID till we found out how we will handle this
    }

    public void updateCoach(Coach coach, String newUsername, String password) {
        // Validate coach
        if (coach == null) {
            return;
        }
        try {
            // Check if the coach already exists in the system
            if (FileHandler.isCoachAlreadyInTheSystem(coach.ID)) {
                System.out.println("Coach with ID " + coach.ID + " already exists in the system.");
                return; // Exit if the coach already exists
            }
            // Save the coach data if they don't already exist
            FileHandler.updateCoachInfo(coach.ID, newUsername,password);

        } catch (Exception e) {
            // Handle any exceptions and log the error
            System.out.println("An error occurred while adding the coach: " + e.getMessage());
        }
        // Update coach information
        coach.setName(newUsername);
        // Save data
    }

    // Member-Coach assignment
    public void assignMemberToCoach(Member member, Coach coach) throws FileNotFoundException {
        try {
            // Check if the coach already exists in the system
            if (FileHandler.isCoachAlreadyInTheSystem(coach.ID)) {
                System.out.println("Coach with ID " + coach.ID + " already exists in the system.");
                return; // Exit if the coach already exists
                }
            if (!FileHandler.isMemberAlreadyInTheSystem(member.ID)) {
                System.out.println("member with ID " + member.ID + " already exists in the system.");
                    return; // If member is not found, exit
                }
            coach.assignMember(member);
            member.setCoach(coach);    
            FileHandler.saveCoachData(coach);
            FileHandler.saveMemberData(member);
            }
            catch (Exception e) {
                // Handle any exceptions and log the error
                System.out.println("An error occurred while adding the coach: " + e.getMessage());
            }
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
        try {
            matchingMembers=FileHandler.searchMembers(keyword);
        } catch (Exception e) {
            System.out.println("An error occurred while adding the coach: " + e.getMessage());
        }
        
        return matchingMembers; // Return the matching members list
    }
    
    public ArrayList<Coach> searchCoaches(String keyword) {
        ArrayList<Coach> matchingCoaches = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchingCoaches; // Return empty list if keyword is invalid
        }
    
        keyword = keyword.toLowerCase(); // Convert to lowercase for case-insensitive search
        try {
            matchingCoaches=FileHandler.searchCoach(keyword);
        } catch (Exception e) {
            System.out.println("An error occurred while adding the coach: " + e.getMessage());
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
    // public ArrayList<Member> getMembers() {
    //     return members;
    // }

    // public ArrayList<Coach> getCoaches() {
    //     return coaches;
    // }

    // public ArrayList<Billing> getBills() {
    //     return bills;
    // }
}
