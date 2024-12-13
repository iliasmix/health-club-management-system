package services;

import modules.Admin;
import modules.Member;

import java.io.*;
import java.util.*;

public class TestNotifications {
    public static void main(String[] args) throws FileNotFoundException {
        Admin a = new Admin("Admin", "1111", "a-1");
        Member m1 = new Member("Ahmad", "1234", "m-1");
        Member m2 = new Member("Mohammad", "1122", "m-2");
        NotificationSystem.sendSubscriptionExpiryNotification(a, m1);
        NotificationSystem.sendSubscriptionExpiryNotification(a, m2);

        File file = new File("resources/Notifications.txt");
        Scanner input = new Scanner(file);
        while(input.hasNext()) {
            String[] parts = input.nextLine().split("/");
            if(parts[1].equals("m-1")) {
                System.out.println(parts[2]);
            }
        }
        input.close();
    }
}
