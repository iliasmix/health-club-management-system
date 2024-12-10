package modules;
import java.util.Date;

public class Subscription {
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    // Constructor
    public Subscription(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    // Getters and Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Methods
    public void renewSubscription(Date newEndDate) {
        this.endDate = newEndDate;
        this.isActive = true;
    }

    public boolean checkIfExpired() {
        Date currentDate = new Date();
        return currentDate.after(endDate);
    }
}
