package modules;

import java.io.Serializable;
import java.util.Date;

public class Billing implements Serializable {
    private String billId;
    private String memberId;
    private double amount;
    private Date billingDate;
    private boolean isPaid;

    public Billing(String billId, String memberId, double amount) {
        this.billId = billId;
        this.memberId = memberId;
        this.amount = amount;
        this.billingDate = new Date();
        this.isPaid = false;
    }

    public void processPayment() {
        this.isPaid = true;
    }

    public String generateInvoice() {
        return String.format("Invoice #%s\nMember: %s\nAmount: $%.2f\nDate: %s\nStatus: %s",
                billId, memberId, amount, billingDate, isPaid ? "Paid" : "Unpaid");
    }

    // Getters and setters
    public String getBillId() {
        return billId;
    }

    public String getMemberId() {
        return memberId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
