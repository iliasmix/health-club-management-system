package modules;

import java.io.*; // import all of the built in functions of the input output
import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;


//  NOTE :  I removed all the email functions as you asked because its unnecessary and will complicate the porject
public class User {
    // it was private, the reason for mohamed huissen errors, you can NOT inherit private..!
    protected String username;
    protected String password;
    protected String ID;
        

    public User(String username, String password, String ID) {
        this.username = username;
        this.password = password;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {return this.ID;}
   
    public static String[] login(String username, String password) {
        // Try Members first
        try (BufferedReader reader = new BufferedReader(new FileReader("Members.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Member"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Members file: " + e.getMessage());
        }

        // Try Coaches
        try (BufferedReader reader = new BufferedReader(new FileReader("Coaches.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Coach"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Coaches file: " + e.getMessage());
        }

        // Try Admins
        try (BufferedReader reader = new BufferedReader(new FileReader("Admins.txt"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] data = line.split("/");
                if (data[1].equals(username) && data[2].equals(password)) {
                    return new String[]{data[0], "Admin"}; // Return ID and type
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Admins file: " + e.getMessage());
        }

        return null; // Return null if no match found
    }

    // Logout function
    public void logout() {
        if (this.username != null) {
            System.out.println("User " + this.username + " has been logged out.");
            this.username = null; // Clear the current logged-in user
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public static boolean updateProfile(String userId, String newUsername, String newPassword, String userType) {
        String fileName;
        switch (userType.toLowerCase()) {
            case "member":
                fileName = "Members.txt";
                break;
            case "coach":
                fileName = "Coaches.txt";
                break;
            case "admin":
                fileName = "Admins.txt";
                break;
            default:
                return false;
        }

        ArrayList<String> fileContent = new ArrayList<>();
        boolean updated = false;

        // Read and update the file content
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            fileContent.add(reader.readLine()); // Add header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                if (data[0].equals(userId)) {
                    // Update username and password while keeping ID and other fields unchanged
                    data[1] = newUsername;
                    data[2] = newPassword;
                    line = String.join("/", data);
                    updated = true;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        // Write back to file if updated
        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : fileContent) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    // Example usage
    // public static void main(String[] args) {
    //     // Test login
    //     String[] loginResult = login("member1", "1234");
    //     if (loginResult != null) {
    //         System.out.println("Login successful!");
    //         System.out.println("User ID: " + loginResult[0]);
    //         System.out.println("User Type: " + loginResult[1]);

    //         // Test update profile with both username and password
    //         boolean updateResult = updateProfile(loginResult[0], "newUsername", "newPassword", loginResult[1]);
    //         if (updateResult) {
    //             System.out.println("Profile updated successfully!");
    //         } else {
    //             System.out.println("Failed to update profile.");
    //         }
    //     } else {
    //         System.out.println("Login failed!");
    //     }
    // }
}
