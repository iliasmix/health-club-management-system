package services;
import modules.TrainingPlan;
import modules.Admin;
import modules.Member;

import java.io.*;
import java.util.*;

public class TestNotifications {
    public static void main(String[] args) throws FileNotFoundException {
        Admin a = new Admin("Admin", "1111", "a-1");
        Member m1 = new Member("Ahmad", "1234", "m-1");
        //Member m2 = new Member("Mohammad", "1122", "m-2");
        NotificationSystem.greet(a);
        NotificationSystem.greet(m1);
//        NotificationSystem.sendSubscriptionExpiryNotification(a, m1);
//        NotificationSystem.sendSubscriptionExpiryNotification(a, m2);

        //NotificationSystem.sendMessage("a-1","m-1", "Hi there m1!");
        //NotificationSystem.sendMessage("a-1", "m-2", "Hi there m2!");

        //System.out.println(FileHandler.isMemberAlreadyInTheSystem("m-2"));
        //System.out.println(FileHandler.isCoachAlreadyInTheSystem("c-5"));
        //System.out.println(FileHandler.isCoachAlreadyInTheSystem("c-1"));

        if(!NotificationSystem.isSubscriptionActive("m-1")) {
            NotificationSystem.sendSubscriptionExpiryNotification("m-1");
        }
        if(!NotificationSystem.isSubscriptionActive("m-2")) {
            NotificationSystem.sendSubscriptionExpiryNotification("m-2");
        }

    }
}
