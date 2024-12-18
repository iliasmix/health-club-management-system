package services.billing;

import java.io.*;
import java.time.LocalDate;
import services.notification.NotificationService;

public class BillingService {
    private static final String BILLS_FILE = "resources/Bills.txt";
    private static int billCounter = 1;

    public static void createBill(String memberId, int planMonths, LocalDate startDate) {
        if (!isValidPlanDuration(planMonths)) {
            throw new IllegalArgumentException("Invalid plan duration");
        }

        String billId = "b-" + billCounter++;
        LocalDate endDate = startDate.plusMonths(planMonths);
        double price = calculatePrice(planMonths);

        try (PrintWriter writer = new PrintWriter(new FileWriter(BILLS_FILE, true))) {
            writer.println(String.format("%s/%s/%d months/%s/%s/%.2f/%s",
                billId,
                memberId,
                planMonths,
                startDate,
                endDate,
                price,
                new Date()));
            
            updateMemberSubscription(memberId, startDate, endDate);
        } catch (IOException e) {
            System.err.println("Error creating bill: " + e.getMessage());
        }
    }

    private static boolean isValidPlanDuration(int months) {
        return months == 1 || months == 2 || months == 3 || months == 6 || months == 12;
    }

    private static double calculatePrice(int months) {
        // Implementation of price calculation based on subscription duration
        // This should be moved to a separate pricing service in a real application
        return months * 50.0; // Basic calculation for example
    }

    private static void updateMemberSubscription(String memberId, LocalDate startDate, LocalDate endDate) {
        // Update member's subscription details in Members.txt
        // Implementation details...
    }
}