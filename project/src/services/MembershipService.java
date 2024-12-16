package services;

import models.Member;
import utils.FileUtils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class MembershipService {
    private static final String MEMBERSHIP_FILE = "data/memberships.txt";

    public void renewMembership(Member member, int months) {
        Date endDate = new Date();
        // Add months to current date
        endDate.setMonth(endDate.getMonth() + months);
        member.setSubscriptionEndDate(endDate);
        saveMembershipData(member);
    }

    public boolean isSubscriptionActive(Member member) {
        Date currentDate = new Date();
        return member.getSubscriptionEndDate() != null && 
               member.getSubscriptionEndDate().after(currentDate);
    }

    private void saveMembershipData(Member member) {
        String data = String.format("%s,%s,%s", 
            member.getMemberId(), 
            member.getSubscriptionEndDate(),
            member.getAssignedCoach() != null ? member.getAssignedCoach().getCoachId() : "none"
        );
        FileUtils.writeToFile(MEMBERSHIP_FILE, data, true);
    }
}