package modules;

import java.util.Date;
// intrface 
// ==================== all OOP reqirements DONE =========================
public interface SubscriptionInterface {
    Date getStartDate();
    void setStartDate(Date startDate);
    
    Date getEndDate();
    void setEndDate(Date endDate);
    
    boolean isActive();
    void setActive(boolean active);
    
    void renewSubscription(Date newEndDate);
    boolean checkIfExpired();
    
    void saveSubscriptionData(String memberId, String memberUsername, String coachId, String scheduleId);
}
