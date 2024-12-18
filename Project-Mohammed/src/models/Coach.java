package models;

import java.util.ArrayList;
import java.util.List;

public class Coach extends User {
    private String coachId;
    private List<Member> assignedMembers;
    private String specialization;

    public Coach(String username, String password, String coachId) {
        super(username, password, "COACH");
        this.coachId = coachId;
        this.assignedMembers = new ArrayList<>();
    }

    public void assignMember(Member member) {
        if (!assignedMembers.contains(member)) {
            assignedMembers.add(member);
            member.setAssignedCoach(this);
        }
    }

    // Getters and setters
    public String getCoachId() { return coachId; }
    public List<Member> getAssignedMembers() { return assignedMembers; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}