package models;

public class Admin extends User {
    private String adminId;

    public Admin(String username, String password, String adminId) {
        super(username, password, "ADMIN");
        this.adminId = adminId;
    }

    public String getAdminId() { return adminId; }
}