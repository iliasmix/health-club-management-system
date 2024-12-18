package Testing_Modules;
import modules.*;
import modules.Member;
import modules.Subscription;
import java.text.SimpleDateFormat;
import java.util.Date;



public class testMember {
 
    public static void main(String[] args) {
        
        try {
            // Create a subscription
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2024-12-15");
            Date endDate = dateFormat.parse("2025-06-15");
            Subscription subscription = new Subscription(startDate, endDate);

            // Create a member and set details
            Member member = new Member("testUser", "testPassword", "m-1");
            member.setCoachId("c-1");
            member.setSchedule("s-1");
            
            // Attach the subscription to the member
            member.viewSubscriptionEndDate(); // Before attaching a subscription
            
            System.out.println("\nAssigning subscription...");
            member.checkSubscriptionStatus(); // Checks status before assignment
            
            // Simulate saving subscription data
            subscription.saveSubscriptionData(member.getID(), member.getUsername(), member.getCoachId(), member.getSchedule());
            member.viewSubscriptionEndDate(); // Check subscription expiry warnings
            member.checkSubscriptionStatus(); // After assigning

            // Test notification functionality
            System.out.println("\nAdding notifications...");
            member.checkSubscriptionStatus(); // Will send an expiration notification if expired
            member.viewCoachAndSchedule();   // Test coach and schedule loading

            // Renew the subscription
            System.out.println("\nRenewing subscription...");
            Date newEndDate = dateFormat.parse("2026-06-15");
            subscription.renewSubscription(newEndDate);
            member.viewSubscriptionEndDate();

        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
        }
    }
}




