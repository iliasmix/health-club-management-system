package modules.subscription;

import java.util.Date;

public class Subscription {
    private String memberId;
    private Date startDate;
    private Date endDate;
    private boolean active;
    private double price;

    public Subscription(String memberId, Date startDate, Date endDate, double price) {
        this.memberId = memberId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.active = true;
    }

    public String getMemberId() {
        return memberId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }
}