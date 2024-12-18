package modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import services.Billing;
import services.FileHandler;

public class Admin extends User {
    // Constructor

    private static int adminCounter = 0; // Static counter for auto-incrementing IDs

    // Constructor with auto-incrementing ID
    public Admin(String username, String password) {
        super(username, password, generateAdminID());
    }

    // Method to generate a new admin ID
    private static String generateAdminID() {
        adminCounter++; // Increment the counter
        return "a-" + adminCounter; // Generate the ID in the format a-1, a-2, etc.
    }

    // Methods to manage members
    public void addMember(Member member) {
        if (member == null) {
            return;
        }
        // Save data
        FileHandler.saveMemberData(member);
    }

    public void removeMember(String ID) {
        // Check if the member is null
        if (ID == null) {
            return;
        }

        // Check if the member exists in the list

        try {
            if (!FileHandler.isMemberAlreadyInTheSystem(ID)) {

                return;
            }
            FileHandler.deleteMember(ID);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void updateMember(String ID, String newUsername, String password) {

        FileHandler.updateMemberInfo(ID, newUsername, password);
        // // Validate the member object
        // if (member == null) {
        // return;
        // }

        // try {
        // // Retrieve the member's ID
        // String memberId = member.ID;
        // // Check if the member exists in the list
        // if (!FileHandler.isMemberAlreadyInTheSystem(member.ID)) {
        // System.out.println("member with ID " + member.ID + " already exists in the
        // system.");
        // return; // If member is not found, exit
        // }

        // // Update member information
        // if (newUsername != null && !newUsername.isEmpty()) {
        // member.setUsername(newUsername);
        // // Update member info in the system
        // FileHandler.updateMemberInfo(member.ID, newUsername, password);
        // }

        // } catch (Exception e) {
        // // Handle any exceptions that occur
        // System.out.println("An error occurred: " + e.getMessage());
        // }
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

    public void removeCoach(String ID) {
        // Validate coach
        if (ID == null) {
            return;
        }
        // Check if the member exists in the list
        // Remove coach assignments from members
        // need this if any thing happend to coach data type in member class
        // for (Member member : coach.getMembers()) {
        // // Check if the member is assigned to the coach
        // if (member.getCoach() != null && member.getCoach().equals(coach.getName())) {
        // member.setCoach(null); // Clear the coach assignment for the member
        // }
        // }
        // Save data
        FileHandler.deleteCoach(ID);// coach.ID till we found out how we will handle this
    }

    public void updateCoach(String ID, String newUsername, String password) {
        FileHandler.updateCoachInfo(ID, newUsername, password);
        // // Validate coach
        // if (coach == null) {
        // return;
        // }
        // try {
        // // Check if the coach already exists in the system
        // if (FileHandler.isCoachAlreadyInTheSystem(coach.ID)) {
        // System.out.println("Coach with ID " + coach.ID + " already exists in the
        // system.");
        // return; // Exit if the coach already exists
        // }
        // // Save the coach data if they don't already exist
        // FileHandler.updateCoachInfo(coach.ID, newUsername, password);

        // } catch (Exception e) {
        // // Handle any exceptions and log the error
        // System.out.println("An error occurred while adding the coach: " +
        // e.getMessage());
        // }
        // // Update coach information
        // coach.setName(newUsername);
        // // Save data
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
            member.setCoach(coach);
            FileHandler.saveMemberData(member);
        } catch (FileNotFoundException e) {
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
            matchingMembers = FileHandler.searchMembers(keyword);
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
            matchingCoaches = FileHandler.searchCoach(keyword);
        } catch (Exception e) {
            System.out.println("An error occurred while adding the coach: " + e.getMessage());
        }
        return matchingCoaches; // Return the matching coaches list
    }

    // Reporting
    public void generateReports() throws FileNotFoundException {
        // Initialize counters
        int counter3 = 0;
        int counter6 = 0;

        // Specify the file to read from
        File nillsFile = new File("resources\\Bills.txt");

        try (Scanner billsScan = new Scanner(nillsFile)) {
            // Skip the header if there is one
            if (billsScan.hasNextLine()) {
                billsScan.nextLine();
            }

            // Process each line in the file
            while (billsScan.hasNext()) {
                String[] parts = billsScan.nextLine().split("/");

                // Check if the duration (in parts[2]) is "6 months" (case-insensitive)
                if (parts[2].equalsIgnoreCase("6 months")) {
                    counter6++; // Increment for 6 months
                } else if (parts[2].equalsIgnoreCase("3 months")) {
                    counter3++; // Increment for other durations (optional)
                }
            }

            // Output the results
            System.out.println("Bills with 6 months duration: " + counter6);
            System.out.println("Bills with other durations: " + counter3);

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        }
    }
}
// Subscription management

// Getters
// public ArrayList<Member> getMembers() {
// return members;
// }

// public ArrayList<Coach> getCoaches() {
// return coaches;
// }

// public ArrayList<Billing> getBills() {
// return bills;
// }