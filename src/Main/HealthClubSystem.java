package Main;

import java.util.ArrayList;
import modules.*;

public class HealthClubSystem {
    // The admin of the system and lists of coaches and members
    private Admin admin;
    private ArrayList<Coach> coaches;
    private ArrayList<Member> members;

    // Constructor to initialize the system with the admin
    public HealthClubSystem(Admin admin) {
        this.admin = admin;
        this.coaches = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // // Method to add a new member to the system
    // public void addMember(Member member) {
    // members.add(member);
    // }

    // // Method to add a new coach to the system
    // public void addCoach(Coach coach) {
    // coaches.add(coach);
    // }
}
