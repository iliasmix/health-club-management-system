package services;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import modules.Coach;
import modules.Member;

public class FileHandler {
    // ==================== File Path Constants ====================
    private static final String MEMBERS_FILE = "resources\\Members.txt";
    private static final String COACHES_FILE = "resources\\Coaches.txt";
    private static final String SCHEDULES_FILE = "resources\\Schedules.txt";
    private static final String BILLING_FILE = "resources\\Bills.txt";
    private static final String SUBSCRIPTIONS_FILE = "resources\\Subscriptions.txt";

    // ==================== Member Operations ====================
    public static void saveMemberData(Member member) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE, true))) {
            writer.println(member.toString());
        } catch (IOException e) {
            System.err.println("Error saving member data: " + e.getMessage());
        }
    }

    public ArrayList<Member> loadMemberData() {
        ArrayList<Member> members = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MEMBERS_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

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

    public static void deleteMember(String memberId) {
        try {
            File inputFile = new File(MEMBERS_FILE);
            File tempFile = new File("resources\\TempMembers.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and write lines, skipping the member to be deleted
            boolean memberFound = false;
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (!data[0].equals(memberId)) {
                        writer.println(currentLine);
                    } else {
                        memberFound = true;
                    }
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (memberFound) {
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("Member not found!");
            }

        } catch (IOException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }

    // ==================== Coach Operations ====================
    public static void saveCoachData(Coach coach) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COACHES_FILE, true))) {
            writer.println(coach.toString());
        } catch (IOException e) {
            System.err.println("Error saving coach data: " + e.getMessage());
        }
    }

    public static ArrayList<Coach> loadCoachData() {
        ArrayList<Coach> coaches = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COACHES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] data = line.split("/");
                    if (data.length == 4) {
                        Coach coach = new Coach(data[1], data[2], data[0]);
                        //coach.setScheduleId(data[3]);
                        coaches.add(coach);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading coach data: " + e.getMessage());
        }
        return coaches;
    }

    public static void deleteCoach(String coachId) {
        try {
            File inputFile = new File(COACHES_FILE);
            File tempFile = new File("resources\\TempCoaches.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and write lines, skipping the coach to be deleted
            boolean coachFound = false;
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (!data[0].equals(coachId)) {
                        writer.println(currentLine);
                    } else {
                        coachFound = true;
                    }
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (coachFound) {
                System.out.println("Coach deleted successfully!");
            } else {
                System.out.println("Coach not found!");
            }

        } catch (IOException e) {
            System.out.println("Error deleting coach: " + e.getMessage());
        }
    }

    // ==================== Schedule Operations ====================
    public static void saveScheduleData(String scheduleData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULES_FILE, true))) {
            writer.println(scheduleData);
        } catch (IOException e) {
            System.err.println("Error saving schedule data: " + e.getMessage());
        }
    }

    public ArrayList<String[]> loadScheduleData() {
        ArrayList<String[]> schedules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SCHEDULES_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] scheduleData = line.split("/");
                    schedules.add(scheduleData);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading schedule data: " + e.getMessage());
        }
        return schedules;
    }

    // ==================== Billing Operations ====================
    public static void saveBillingData(String billingData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BILLING_FILE, true))) {
            writer.println(billingData);
        } catch (IOException e) {
            System.err.println("Error saving billing data: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadBillingData() {
        ArrayList<String> bills = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(BILLING_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

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

    // ==================== Subscription Operations ====================
    public static void saveSubscriptionData(String subscriptionData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SUBSCRIPTIONS_FILE, true))) {
            writer.println(subscriptionData);
        } catch (IOException e) {
            System.err.println("Error saving subscription data: " + e.getMessage());
        }
    }

    public static ArrayList<String[]> loadSubscriptionData() {
        ArrayList<String[]> subscriptions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SUBSCRIPTIONS_FILE))) {
            // Skip header line
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] subscriptionData = line.split("/");
                    subscriptions.add(subscriptionData);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading subscription data: " + e.getMessage());
        }
        return subscriptions;
    }

    // ==================== Admin Operations ====================

    public static void updateMemberInfo(String memberId, String newUsername, String newPassword) {
        try {
            File inputFile = new File(MEMBERS_FILE);
            File tempFile = new File("resources\\TempMembers.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and update data
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (data[0].equals(memberId)) {
                        // Update member info
                        data[1] = newUsername;
                        data[2] = newPassword;
                        currentLine = String.join("/", data);
                    }
                    writer.println(currentLine);
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("Member information updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
    }

    public static void updateCoachInfo(String coachId, String newUsername, String newPassword) {
        try {
            File inputFile = new File(COACHES_FILE);
            File tempFile = new File("resources\\TempCoaches.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            // Copy header
            if (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }

            // Process and update data
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                if (!currentLine.isEmpty()) {
                    String[] data = currentLine.split("/");
                    if (data[0].equals(coachId)) {
                        // Update coach info
                        data[1] = newUsername;
                        data[2] = newPassword;
                        currentLine = String.join("/", data);
                    }
                    writer.println(currentLine);
                }
            }

            scanner.close();
            writer.close();

            // Delete old file and rename temp file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("Coach information updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating coach: " + e.getMessage());
        }
    }


    public static boolean isMemberAlreadyInTheSystem(String memberID) throws FileNotFoundException {
        File membersFile = new File(MEMBERS_FILE);
        Scanner membersScan = new Scanner(membersFile);
        if(membersScan.hasNextLine()) {
            membersScan.nextLine();
        }
        while(membersScan.hasNext()) {
            String[] parts = membersScan.nextLine().split("/");
            if(memberID.equals(parts[0])) return true;
        }
        return false;
    }

    public static boolean isCoachAlreadyInTheSystem(String coachID) throws FileNotFoundException {
        File coachesFile = new File(COACHES_FILE);
        Scanner coachesScan = new Scanner(coachesFile);
        if(coachesScan.hasNextLine()) {
            coachesScan.nextLine();
        }
        while(coachesScan.hasNext()) {
            String[] parts = coachesScan.nextLine().split("/");
            if(coachID.equals(parts[0])) return true;
        }
        return false;
    }
}