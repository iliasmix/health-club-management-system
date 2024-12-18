package services;

import utils.FileUtils;
import java.util.List;

public class AuthenticationService {
    private static final String USERS_FILE = "data/users.txt";

    public static boolean authenticate(String username, String password, String role) {
        List<String> users = FileUtils.readFromFile(USERS_FILE);
        return users.stream()
            .map(line -> line.split(","))
            .anyMatch(parts -> parts.length == 3 &&
                parts[0].equals(username) &&
                parts[1].equals(password) &&
                parts[2].equals(role));
    }
}