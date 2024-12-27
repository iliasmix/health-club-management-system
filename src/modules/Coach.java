package modules;

import java.io.*;

import services.FileHandler;
import services.NotificationSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach extends modules.User {
    private FileHandler fileHandler;

    public Coach(String username, String password) {
        super(username, password, generateCoachID());
        this.username = username;
        this.password = password;
    }

    private static int coachCounter = 0;

    private static String generateCoachID() {
        coachCounter++;
        return "c-" + coachCounter;
    }
    public String getID() {return this.ID;}

    public void setSchedulesForAllMembers(String schedule, LocalDate startDate, int weeksnum) {
        ArrayList<modules.Member> members = FileHandler.loadMemberData();
        boolean hasAssignedMembers = false;

        for (modules.Member member : members) {
            if (this.getID().equals(member.getCoachId())) {
                hasAssignedMembers = true;

                modules.TrainingPlan trainingPlan = new modules.TrainingPlan(this.getID(), startDate, weeksnum);
                trainingPlan.setSchedule(schedule);

                try {
                    trainingPlan.saveScheduleToFile();
                } catch (Exception e) {
                    System.err.println("Error saving schedule for member: " + member.getUsername());
                }

                member.setSchedule(schedule);
                FileHandler.saveMemberData(member);
            }
        }

        if (hasAssignedMembers) {
            System.out.println("Schedules successfully assigned to all members.");
        } else {
            System.out.println("No members assigned to this coach.");
        }
    }
    public ArrayList<modules.TrainingPlan> getTrainingPlans() {
        // Λογική για την επιστροφή όλων των Training Plans του Coach
        return FileHandler.loadTrainingPlansForCoach(this.getID());
    }

    public static void sendMessageToAllMembers(String coachId, String message) {
        File membersFile = new File("resources\\Members.txt");

        try (Scanner membersScan = new Scanner(membersFile)) {
            if (membersScan.hasNextLine()) {
                membersScan.nextLine();
            }

            while (membersScan.hasNextLine()) {
                String[] parts = membersScan.nextLine().split("/");

                if (parts.length > 3 && parts[3].equals(coachId)) {
                    String memberId = parts[0];
                    System.out.println("Sending message to Member ID: " + memberId);
                    NotificationSystem.sendMessage(coachId, memberId, message);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Members file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void saveCoach() {
        FileHandler.saveCoachData(this);
    }

    public void deleteCoach() {
        FileHandler.deleteCoach(this.getID());
    }

    public void setName(String name) {
        this.setUsername(name);
    }

    public String getName() {
        return this.getUsername();
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", getID(), getUsername(), getPassword());
    }
}
