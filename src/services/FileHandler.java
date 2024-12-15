package services;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import modules.Coach;
import modules.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FileHandler class manages all file operations for the Health Club Management System.
 * This class provides methods to save and load data for all system entities.
 * 
 * File Format Specifications:
 * - All data is stored in forward-slash separated format
 * - Members format: Member ID/Member Username/Member Pass/Member's Coach ID/Subscription Start Date/Subscription End Date/Subscription Status/Schedule ID
 * - Coaches format: Coach ID/Coach Username/Coach Pass/Schedule ID
 * - Schedules format: Schedule ID/Coach ID/Day/Exercise/Schedule Start Date/Schedule End Date
 * - Bills format: Bill ID/Member ID/Plan/Start Date/End Date/Price/Bill Generation Time
 */
public class FileHandler {
    // ==================== File Path Constants ====================
    private static final String MEMBERS_FILE = "resources/Members.txt";
    private static final String COACHES_FILE = "resources/Coaches.txt";
    private static final String SCHEDULES_FILE = "resources/Schedules.txt";
    private static final String SUBSCRIPTIONS_FILE = "resources/Subscriptions.txt";
    private static final String NOTIFICATIONS_FILE = "resources/Notifications.txt";
    private static final String REPORTS_FILE = "resources/Reports.txt";
    private static final String ADMINS_FILE = "resources/Admins.txt";
    private static final String BILLING_FILE = "resources/Bills.txt";

    // ==================== File Headers ====================
    private static final String MEMBERS_HEADER = "Member ID/Member Username/Member Pass/Member's Coach ID/Subscription Start Date/Subscription End Date/Subscription Status/Schedule ID";
    private static final String COACHES_HEADER = "Coach ID/Coach Username/Coach Pass/Schedule ID";
    private static final String SCHEDULES_HEADER = "Schedule ID/Coach ID/Day/Exercise/Schedule Start Date/Schedule End Date";
    private static final String BILLS_HEADER = "Bill ID/Member ID/Plan/Start Date/End Date/Price/Bill Generation Time";

    // ==================== Member Operations ====================
    public static void saveMemberData(ArrayList<Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            writer.println(MEMBERS_HEADER);
            for (Member member : members) {
                writer.println(member.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving member data: " + e.getMessage());
        }
    }

    public static ArrayList<Member> loadMemberData() {
        ArrayList<Member> members = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MEMBERS_FILE))) {
            // Skip header line
            if (scanner.hasNextLine()) scanner.nextLine();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split("/");
                    if (data.length == 8) {
                        Member member = new Member(data[1], data[2], data[0]);
                        member.setCoachId(data[3]);
                        member.setSchedule(data[7]);
                        members.add(member);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading member data: " + e.getMessage());
        }
        return members;
    }

    // ==================== Coach Operations ====================
    public static void saveCoachData(ArrayList<Coach> coaches) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COACHES_FILE))) {
            writer.println(COACHES_HEADER);
            for (Coach coach : coaches) {
                writer.println(coach.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving coach data: " + e.getMessage());
        }
    }

    public static ArrayList<Coach> loadCoachData() {
        ArrayList<Coach> coaches = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COACHES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine()) scanner.nextLine();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split("/");
                    if (data.length == 4) {
                        Coach coach = new Coach(data[1], data[2], data[0]);
                        coach.setScheduleId(data[3]);
                        coaches.add(coach);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading coach data: " + e.getMessage());
        }
        return coaches;
    }

    // ==================== Schedule Operations ====================
    public static void saveScheduleData(String scheduleData) {
        boolean isNewFile = !new File(SCHEDULES_FILE).exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULES_FILE, true))) {
            if (isNewFile) {
                writer.println(SCHEDULES_HEADER);
            }
            writer.println(scheduleData);
        } catch (IOException e) {
            System.err.println("Error saving schedule data: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadScheduleData() {
        ArrayList<String> schedules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SCHEDULES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine()) scanner.nextLine();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    schedules.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading schedule data: " + e.getMessage());
        }
        return schedules;
    }

    // ==================== Billing Operations ====================
    public static void saveBillingData(String billingData) {
        boolean isNewFile = !new File(BILLING_FILE).exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(BILLING_FILE, true))) {
            if (isNewFile) {
                writer.println(BILLS_HEADER);
            }
            writer.println(billingData);
        } catch (IOException e) {
            System.err.println("Error saving billing data: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadBillingData() {
        ArrayList<String> bills = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(BILLING_FILE))) {
            // Skip header line
            if (scanner.hasNextLine()) scanner.nextLine();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    bills.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading billing data: " + e.getMessage());
        }
        return bills;
    }
}