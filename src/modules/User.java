package modules;

import java.io.*; // import all of the built in functions of the input output
import java.util.Scanner;


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
   
    public void login(String username, String password) {
        try {
            // Open the file for reading
            File file = new File("user.txt");
            Scanner scanner = new Scanner(file);

            boolean isAuthenticated = false;

            // Read file line by line to find matching credentials
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] credentials = line.split(" "); // Assuming username and password are separated by a space

                if (credentials.length == 2) {
                    String storedUsername = credentials[0].trim();
                    String storedPassword = credentials[1].trim();

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        isAuthenticated = true;
                        this.username = username; // Set current logged-in user
                        System.out.println("Login successful. Welcome, " + username + "!");
                        break;
                    }
                }
            }

            if (!isAuthenticated) {
                System.out.println("Invalid username or password.");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
        }
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

    public void updateProfile(String newUsername, String newPassword, String newEmail) {
        if (this.username == null) {
            System.out.println("No user is currently logged in to update the profile.");
            return;
        }

        try {
            File inputFile = new File("user.txt");
            File tempFile = new File("temp_user.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean isUpdated = false;

            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(" ");
                if (credentials.length == 2 && credentials[0].trim().equals(this.username)) {
                    writer.write(newUsername + " " + newPassword + " " + newEmail);
                    writer.newLine();
                    isUpdated = true;
                    this.username = newUsername; // Update current username
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (isUpdated) {
                if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                    System.out.println("Error updating profile.");
                    return;
                }
                System.out.println("Profile updated successfully.");
            } else {
                tempFile.delete();
                System.out.println("No matching user found to update.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the profile.");
        }
    }
}
