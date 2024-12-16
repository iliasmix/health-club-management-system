package utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getStringInput() {
        return scanner.nextLine().trim();
    }

    public static String getPassword() {
        return scanner.nextLine().trim();
    }
}