package gui.MemberDashboard;
import gui.*;
import modules.Member;
import javax.swing.*;
public class MemberDashboardTest {
    public static void main(String[] args) {
        // For testing purposes, create a sample member
        Member testMember = new Member("testUser", "testPass");
        
        SwingUtilities.invokeLater(() -> {
            MemberDashboard dashboard = new MemberDashboard(testMember);
            dashboard.setVisible(true);
        });
    }
}

