package modules.auth;

import modules.user.User;
import services.FileHandler;
import java.io.*;

public class AuthenticationService {
    public static String[] login(String username, String password) {
        // Try Members first
        String[] memberResult = checkCredentials("Members.txt", username, password);
        if (memberResult != null) {
            return new String[]{memberResult[0], "Member"};
        }

        // Try Coaches
        String[] coachResult = checkCredentials("Coaches.txt", username, password);
        if (coachResult != null) {
            return new String[]{coachResult[0], "Coach"};
        }

        // Try Admins
        String[] adminResult = checkCredentials("Admins.txt", username, password);
        if (adminResult != null) {
            return new String[]{adminResult[0], "Admin"};
        }

        return null;
    }

    private static String[] checkCredentials(String filename, String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return data;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
        return null;
    }

    public static void logout(User user) {
        if (user != null && user.getUsername() != null) {
            System.out.println("User " + user.getUsername() + " has been logged out.");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}