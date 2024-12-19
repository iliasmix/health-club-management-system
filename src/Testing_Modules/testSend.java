package Testing_Modules;

import services.NotificationSystem;
import modules.*;
public class testSend {
    public static void main(String[] args) {
        
        // Testing the sendMessageToAllMembers method
        String testCoachId = "c-1"; // Replace with an actual coach ID in your Members.txt file
        String testMessage = "This is a test message for all members.";

        Coach.sendMessageToAllMembers(testCoachId, testMessage);
    }
}
