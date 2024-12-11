package modules;
import services.Billing;
import java.util.ArrayList;
import services.Billing;

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
    }
    

    public void updateMember(Member member, String newUsername, String newEmail) {
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

            /*There is no setUsername in the Member class. */
            member.setUsername(newUsername);
        }
    
        if (newEmail != null && !newEmail.isEmpty()) {

            /* There is not setEmail in the Member class. */
            member.setEmail(newEmail);
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
    }

    public void updateCoach(Coach coach, String newName, String newSpecialization) {
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
        coach.setSpecialization(newSpecialization);
        // Save data
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
    public void createBill(Member member, double amount) {
        // Create new bill
        Billing biil=new Billing(getPassword(), getEmail(), amount)
        // Add to bills list
    }

    public void processPayment(String billId) {
        // Find bill by ID
        // Process payment
    }

    // Search functionality
    public ArrayList<Member> searchMembers(String keyword) {
        // Search members by username or email
        // Return matching members
        return null;
    }

    public ArrayList<Coach> searchCoaches(String keyword) {
        // Search coaches by name or specialization
        // Return matching coaches
        return null;
    }

    // Reporting
    public void generateReports() {
        // Generate member statistics
        // Generate subscription statistics
        // Generate coach statistics
        // Generate billing statistics
    }

    // Subscription management
    public void checkSubscriptions() {
        // Check for expired subscriptions
        // Send notifications for expired subscriptions
    }

    // Getters
    public ArrayList<Member> getMembers() {
        return members;
    }

    // public ArrayList<Coach> getCoaches() {
    //     return ;
    // }

    public ArrayList<Billing> getBills() {
        return bills;
    }
}
