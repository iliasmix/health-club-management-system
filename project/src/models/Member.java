package models;

import java.util.Date;

public class Member extends User {
    private String memberId;
    private Coach assignedCoach;
    private Date subscriptionEndDate;
    private String schedule;

    public Member(String username, String password, String memberId) {
        super(username, password, "MEMBER");
        this.memberId = memberId;
    }

    // Getters and setters
    public String getMemberId() { return memberId; }
    public Coach getAssignedCoach() { return assignedCoach; }
    public void setAssignedCoach(Coach coach) { this.assignedCoach = coach; }
    public Date getSubscriptionEndDate() { return subscriptionEndDate; }
    public void setSubscriptionEndDate(Date date) { this.subscriptionEndDate = date; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
}