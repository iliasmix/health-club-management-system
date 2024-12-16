import java.io.*;
import java.util.*;

public class UserModule {
    private static final String USERS_FILE = "data\\users.txt";

    public boolean authenticate(String username, String password, String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && 
                    parts[0].equals(username) && 
                    parts[1].equals(password) && 
                    parts[2].equals(role)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return false;
    }

    public void updatePersonalInfo(String username, String newPassword) {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    lines.add(parts[0] + "," + newPassword + "," + parts[2]);
                    updated = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
            return;
        }

        if (updated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
                for (String line : lines) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error writing to users file: " + e.getMessage());
            }
        }
    }
}