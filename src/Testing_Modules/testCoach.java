package Testing_Modules;
import modules.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class testCoach {
   




    public static void main(String[] args) {
        // Test Coach creation
        Coach coach = new Coach("coach1", "password123", "C001");
        System.out.println("Coach created: " + coach.toString());

        // Test sending message to all members
        System.out.println("\nTesting sending message to all members:");
        coach.sendMessageToAllMembers("Reminder: Training session tomorrow at 9 AM.");

        // Test setting schedules for all members
        System.out.println("\nTesting setting schedules for all members:");
        LocalDate startDate = LocalDate.now();
        coach.setSchedulesForAllMembers("Morning Session", startDate, 4);

        // Test saving the coach data
        System.out.println("\nTesting saving coach data:");
        coach.saveCoach();

        // Test deleting the coach data
        System.out.println("\nTesting deleting coach data:");
        coach.deleteCoach();

        // Test setting and getting coach name
        System.out.println("\nTesting setting and getting coach name:");
        coach.setName("NewCoachName");
        System.out.println("Coach name after update: " + coach.getName());

        // Test assigning and removing members (assuming Member class and methods are implemented)
        System.out.println("\nTesting assigning and removing members:");
        Member member = new Member("member1", "password1", "M001");
        coach.assignMember(member);
        coach.removeMember(member);
    }


}
