package modules;
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
