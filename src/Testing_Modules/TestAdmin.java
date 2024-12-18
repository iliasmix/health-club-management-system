package Testing_Modules;

import modules.*;
import services.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*public class TestAdmin {
    public static void main(String[] args) {
        // Create an Admin object
        Admin admin = new Admin("Admin1", "admin123", "a-1");
        
        // Test adding a member
        Member member1 = new Member("John", "pass123", "m-1");
        admin.addMember(member1);

        // Test adding a coach
        Coach coach1 = new Coach("Coach1", "coachpass", "c-1");
        admin.addCoach(coach1);

        // Test updating a member
        admin.updateMember(member1, "JohnUpdated", "newPass123");

        // Test removing a member
        admin.removeMember(member1);

        // Test assigning a member to a coach
        try {
            admin.assignMemberToCoach(member1, coach1);
        } catch (FileNotFoundException e) {
            System.out.println("Error assigning member to coach: " + e.getMessage());
        }

        // Test generating reports
        try {
            admin.generateReports();
        } catch (FileNotFoundException e) {
            System.out.println("Error generating reports: " + e.getMessage());
        }

        // Test searching members
        ArrayList<Member> foundMembers = admin.searchMembers("John");
        System.out.println("Found members:");
        for (Member m : foundMembers) {
            System.out.println(m);
        }

        // Test searching coaches
        ArrayList<Coach> foundCoaches = admin.searchCoaches("Coach");
        System.out.println("Found coaches:");
        for (Coach c : foundCoaches) {
            System.out.println(c);
        }
    }
}*/
