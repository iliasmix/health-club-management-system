package Testing_Modules;
// important line
import modules.*;

// test. java done testing
public class testUser {
    public static void main(String[] args) {
        // Test Case 1: Create a user instance
        User testUser = new User("coach1", "cPass1", "C001");
        System.out.println("Username: " + testUser.getUsername());
        System.out.println("Password: " + testUser.getPassword());
        System.out.println("ID: " + testUser.getID());

        // Test Case 2: Test login functionality
        String[] loginResult = User.login("coach1", "cPass1"); // Change to actual credentials
        if (loginResult != null) {
            System.out.println("Login successful!");
            System.out.println("User ID: " + loginResult[0]);
            System.out.println("User Type: " + loginResult[1]);
        } else {
            System.out.println("Login failed!");
        }

        // Test Case 3: Test profile update
        if (loginResult != null) {
            boolean updateResult = User.updateProfile(loginResult[0], "coach4", "cPass4", loginResult[1]);
            if (updateResult) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile.");
            }
        }

        // Test Case 4: Test logout functionality
        testUser.logout();
    }


}
